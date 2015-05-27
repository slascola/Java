
import java.util.function.LongConsumer;
import java.lang.Math;
 public class Actions extends Point
{
	 final static int BLOB_RATE_SCALE = 4;
	   final static int BLOB_ANIMATION_RATE_SCALE = 50;
	   final static int BLOB_ANIMATION_MIN = 1;
	   final static int BLOB_ANIMATION_MAX = 3;

	   final static int ORE_CORRUPT_MIN = 20000;
	   final static int ORE_CORRUPT_MAX = 30000;

	   final static int QUAKE_STEPS = 10;
	   final static int QUAKE_DURATION = 1100;
	   final static int QUAKE_ANIMATION_RATE = 100;

	   final int VEIN_SPAWN_DELAY = 500;
	   final static int VEIN_RATE_MIN = 8000;
	   final static int VEIN_RATE_MAX = 17000;
   public Actions(int x, int y)
   {
	   super(x, y);
	   
   }
     public static int sign(int x)
     {
    	 if (x < 0)
    	 {
    		 return -1;
    	 }
    	 else if(x > 0)
    	 {
    		 return 1;
    	 }
    	 else
    	 {
    		 return 0;
    	 }
     }
     public static boolean adjacent(Point pt1, Point pt2)
     {
	   int x1 = pt1.x;
	   int x2 = pt2.x;
	   int y1 = pt1.y;
	   int y2 = pt2.y;
	   return ((x1 == x2 && Math.abs(y1 - y2) == 1) ||
			   (y1 == y2 && Math.abs(x1 - x2) == 1));
      }
     public static Point next_position(WorldModel world, Point entity_pt, Point dest_pt)
     {
    	 //if null return current postion
    	 //put a star in world model and then call here
    	int pt = dest_pt.x - entity_pt.x;
    	double horiz = Math.signum(pt);
    	int horiz2 = (int)horiz;
        int x = entity_pt.x + horiz2;
        int y = entity_pt.y;
    	Point newpoint = new Point(x, y);
    	if (horiz == 0 || WorldModel.is_occupied(world, newpoint))
    	{
    		int y1 = dest_pt.y - entity_pt.y;
    		double vert = Math.signum(y1);
    		int vert2 = (int)vert;
    		int newx = entity_pt.x;
    		int newy = entity_pt.y  + vert2;
    		newpoint = new Point(newx, newy);
    		if (vert2 == 0 || WorldModel.is_occupied(world, newpoint))
    		{
    			int fx = entity_pt.x;
    			int fy = entity_pt.y;
    			newpoint = new Point(fx, fy);
    		}
    	}
    	
    	return newpoint;
    	
     }
     public static Point blob_next_position(WorldModel world, Point entity_pt, Point dest_pt)
     {
    	int pt = dest_pt.x - entity_pt.x;
     	double horiz = Math.signum(pt);
     	int horiz2 = (int)horiz;
         int x = entity_pt.x + horiz2;
         int y = entity_pt.y;
     	Point newpoint = new Point(x, y);
     	if (horiz == 0 || WorldModel.is_occupied(world, newpoint) && (world.get_tile_occupant(newpoint) instanceof Ore == false))
     	{
     		int y1 = dest_pt.y - entity_pt.y;
     		double vert = Math.signum(y1);
     		int vert2 = (int)vert;
     		int newx = entity_pt.x;
     		int newy = entity_pt.y  + vert2;
     		newpoint = new Point(newx, newy);
     		if (vert2 == 0 || WorldModel.is_occupied(world, newpoint) && (world.get_tile_occupant(newpoint) instanceof Ore == false))
     		{
     			int fx = entity_pt.x;
     			int fy = entity_pt.y;
     			newpoint = new Point(fx, fy);
     		}
     	}
     	
     	return newpoint;
     	
    	
     }
     public static void remove_entity(Entity entity, WorldModel world)
     {
    	 for(LongConsumer action : ((ActionItems) entity).get_pending_actions())
    	 {
    		 world.unschedule_action(action);
    		 
    	 }
    	 ((ActionItems) entity).clear_pending_actions();
    	 world.remove_entity(entity);
     }
     public static Point find_open_around(WorldModel world, Point pt, int distance)
     {
    	 for(int dy = -distance; dy < distance +1; dy++)
    	 {
    		 for(int dx = -distance; dx < distance +1; dx++)
    		 {
    			 Point new_pt = new Point(pt.x + dx, pt.y + dy);
    			 if(world.within_bounds(new_pt) &&
    					 !(WorldModel.is_occupied(world, new_pt)))
    			 {
    				 return new_pt;
    			 }
    		 }
    	 }
    	 return null;
     }
     public static void schedule_action(WorldModel world, Entity entity, LongConsumer action, long time)
     {
     	((ActionItems) entity).add_pending_actions(action);
     	world.schedule_action(action, time);
     	
     	
     }
     
     public static void schedule_animation(WorldModel world, Entity entity, int repeat_count)
     {
    	 repeat_count = 0;
    	 if (entity instanceof Miner)
    	 {
    		 schedule_action(world, entity, create_animation_action(world, entity, repeat_count),
  					((Miner) entity).get_animation_rate()); 
    	 }
    	 else if(entity instanceof OreBlob)
    	 {
    		 schedule_action(world, entity, create_animation_action(world, entity, repeat_count),
  					((OreBlob) entity).get_animation_rate());
    	 }
    	 else if(entity instanceof Quake)
    	 {
    		 schedule_action(world, entity, create_animation_action(world, entity, repeat_count),
  					((Quake) entity).get_animation_rate());
    	 }
     	
     	
     }

     public static LongConsumer create_animation_action(WorldModel world, Entity entity,
     		                                           int repeat_count)
     {
     LongConsumer[] action = { null };
     action[0] = (long current_ticks) -> 
     {

     	((ActionItems) entity).remove_pending_action(action[0]);
     	entity.next_image();
     	//System.out.println(repeat_count);
     	if(repeat_count != 1)
     	{
     		
     		if (entity instanceof Quake)
     		{
     			schedule_action(world, entity, 
     					create_animation_action(world, entity, Math.max(repeat_count -1, 0)),
     					current_ticks + ((Quake) entity).get_animation_rate());
     		}
     		else if(entity instanceof Miner)
     		{
     			//System.out.println("it came herree");
     			schedule_action(world, entity, 
     					create_animation_action(world, entity, Math.max(repeat_count -1, 0)),
     					current_ticks + ((Miner) entity).get_animation_rate());
     		}
     		else if(entity instanceof OreBlob)
     		{
     			schedule_action(world, entity, 
     					create_animation_action(world, entity, Math.max(repeat_count -1, 0)),
     					current_ticks + ((OreBlob) entity).get_animation_rate());
     		}
     	}
     	

     };

     return action[0];
     }

}

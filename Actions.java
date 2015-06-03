
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     public static int distance_hx(Node current, Node goal)
     {
  	   Point p1 = new Point(current.getNodeX(), current.getNodeY());
  	   Point p2 = new Point(goal.getNodeX(), goal.getNodeY());
  	   int hx_distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
  	   return hx_distance;
     }
     public static int dist_between(Node current, Node neighbor)
     {
  	   Point c_pt = new Point(current.getNodeX(), current.getNodeY());
  	   Point n_pt = new Point(neighbor.getNodeX(), neighbor.getNodeY());
  	   int gx_distance = Math.abs(c_pt.x - n_pt.x) + Math.abs(c_pt.y - n_pt.y);
  	   return gx_distance;
     }
     public static List<Point> neighbor_nodes(Node current)
     {
  	   List<Point> valid_neighbors = new ArrayList<Point>();
  	   
  	   Point left = new Point(current.getNodeX() - 1, current.getNodeY());
  	   Point right = new Point(current.getNodeX() + 1, current.getNodeY());
  	   Point down = new Point(current.getNodeX(), current.getNodeY() +1);
  	   Point up = new Point(current.getNodeX(), current.getNodeY() -1);
  	   
  	   valid_neighbors.add(left);
  	   valid_neighbors.add(right);
  	   valid_neighbors.add(up);
  	   valid_neighbors.add(down);
  	   
  	   return valid_neighbors;
     }
     public static List<Point> aStar(Node start, Node goal, WorldModel world, Node[][] node_grid, Entity thisentity)
     {
  	   
  	   
  	   OpenSetList openset = new OpenSetList();
  	   
  	   
  	   int g_score = 0;
  	   int f_score = g_score + distance_hx(start, goal);
  	   
  	   openset.insert(start, f_score);
  	   
  	   while(openset.size() != 0)
  	   {
  		   OpenSetItem current= openset.head();
  		   Node current_node = current.getNode();
  		   
  		   if(adjacent(current_node, goal))
  		   {
  			   return reconstruct_path(current_node);
  		   }
  		   
  		  
  		   current_node.setClosedSet(true);
  		   openset.remove(current_node);
  		   
  		   
  		   List<Point> nbr_nodes = neighbor_nodes(current_node);
  		   for(Point neighbor : nbr_nodes)
  		   {
  			   
  			   if((!world.within_bounds(neighbor)) || node_grid[neighbor.y][neighbor.x].getClosedSet() == true 
  					   || (world.get_occupancy(neighbor) != null && !(thisentity).canMove(world.get_occupancy(neighbor))))
  			   {
  				   continue;
  			   }
  			   
  			   int current_g_score = current_node.getG() + 1;
  			   current_node.setG(current_g_score);
  			   
  			   Node neighbor_node = node_grid[neighbor.y][neighbor.x];
  			   int tentative_g_score = current_g_score + dist_between(current_node, neighbor_node);
  			   
  			  
  			   
  			   if(!(openset.contains(neighbor_node)) || tentative_g_score < neighbor_node.getG())
  					   
  			   {
  				   
  				   neighbor_node.setCameFrom(current_node);
  			  	  // System.out.println("Setting came from " + neighbor_node.getNodeX() + "," + neighbor_node.getNodeY() + " to " + current_node.getNodeX() + "," + current_node.getNodeY());


  				   
  				   neighbor_node.setG(tentative_g_score);
  				   
  				   
  				   int newfscore = tentative_g_score + distance_hx(neighbor_node, goal);
  				   neighbor_node.setF(newfscore);
  				   
  				   
  				   
  				  
  				   
  				   if(!(openset.contains(neighbor_node)))
  				   {
  					   openset.insert(neighbor_node, newfscore);
  				   }
  				   
  				   
  			   }
  			   //check if need to change to neighbor_node
  			   /*
  			   if(world.get_occupancy(current_node) instanceof MinerNotFull)
  			   {
  				   	Entity e = world.get_occupancy(current_node);
  				   	((MinerNotFull) e).setOpenSet(openset);
  				   //	System.out.println(((MinerNotFull) e).getOpenSet().equals(openset));
  				   	
  				   	
  				   	
  			   }
  			  if(world.get_occupancy(current_node) instanceof MinerFull)
 			   {
 				   	Entity e = world.get_occupancy(current_node);
 				   	((MinerFull) e).setOpenSet(openset);
 				   	
 			   }
  			  if(world.get_occupancy(current_node) instanceof OreBlob)
 			   {
 				   	Entity e = world.get_occupancy(current_node);
 				   	((OreBlob) e).setOpenSet(openset);
 				   	
 			   }
 			   */
  			   
  		   }
  	   }
  	   return null;
  	   
     }
     
     public static List<Point> reconstruct_path(Node current)
     {
  	   List<Point> total_path = new ArrayList<Point>();
  	   
  	   total_path.add(current);
  	   //System.out.println(current.getNodeX() + "," + current.getNodeY() + " -- " + current.getCameFrom());
  	   while(current.getCameFrom() != null)
  	   {
  		   current = current.getCameFrom(); 
  		   Point cur_point = new Point(current.getNodeX(), current.getNodeY());
  		   total_path.add(0, cur_point);
  	  	   //System.out.println(current.getNodeX() + "," + current.getNodeY() + " -- " + current.getCameFrom());
  		   
  	   }
  	   return total_path;
     }
     public static Point next_position(WorldModel world, Point entity_pt, Point dest_pt, Node[][]node_grid)
     {
    	 
    	 Node entity_node = new Node(entity_pt.x, entity_pt.y, false);
    	 Node dest_node = new Node(dest_pt.x, dest_pt.y, false);
    	 Entity entity = world.get_occupancy(entity_node);
    	 List<Point> list_pts = aStar(entity_node, dest_node, world, node_grid, entity);
    	 
    	 if(list_pts == null || list_pts.size() == 0 || entity_node.equals(dest_node))
    	 {
    		 return entity_pt;
    		 
    	 }
    	 
    	 if (list_pts.size() > 1){
    		 Point p = list_pts.get(1);
    		 return p;
    	 }   	 
    	 
    	 
    	return entity_pt;
     }
     public static Point blob_next_position(WorldModel world, Point entity_pt, Point dest_pt, Node[][]node_grid)
     {
    	 Node entity_node = new Node(entity_pt.x, entity_pt.y, false);
    	 Node dest_node = new Node(dest_pt.x, dest_pt.y, false);
    	 Entity entity = world.get_occupancy(entity_node);
    	
    	 List<Point> list_pts = aStar(entity_node, dest_node, world, node_grid, entity);
    	 
    	 if(list_pts == null || list_pts.size() == 0 || entity_node.equals(dest_node))
    	 {
    		 return entity_pt;
    		 
    	 }
    	
    		 Point p = list_pts.get(1);
    		 return p;
    	 
    	 
    	
     	
    	
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
    	 else if(entity instanceof Fairy)
    	 {
    		 schedule_action(world, entity, create_animation_action(world, entity, repeat_count),
   					((Fairy) entity).get_animation_rate());
    	 }
    	 else if (entity instanceof Star)
    	 {
    		 schedule_action(world, entity, create_animation_action(world, entity, repeat_count),
   					((Star) entity).get_animation_rate());
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
     		else if(entity instanceof Fairy)
     		{
     			schedule_action(world, entity, 
     					create_animation_action(world, entity, Math.max(repeat_count -1, 0)),
     					current_ticks + ((Fairy) entity).get_animation_rate());
     		}
     		else if(entity instanceof Star)
     		{
     			schedule_action(world, entity, 
     					create_animation_action(world, entity, Math.max(repeat_count -1, 0)),
     					current_ticks + ((Star) entity).get_animation_rate());
     		}
     	}
     	

     };

     return action[0];
     }

}

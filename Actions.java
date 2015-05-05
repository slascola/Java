
//adjacent and remove entity for sure
//maybe next position and blob next position
import java.lang.Math;
 public class Actions extends Point
{
   public Actions(int x, int y)
   {
	   super(x, y);
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
    	 
     }
  
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class AStar 
{
   public int distance_hx(Point p1, Point p2)
   {
	   int hx_distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	   return hx_distance;
   }
   public int dist_between(Node current, Node neighbor)
   {
	   Point c_pt = new Point(current.getNodeX(), current.getNodeY());
	   Point n_pt = new Point(neighbor.getNodeX(), neighbor.getNodeY());
	   int gx_distance = Math.abs(c_pt.x - n_pt.x) + Math.abs(c_pt.y - n_pt.y);
	   return gx_distance;
   }
   public List aStar(Point start, Point goal)
   {
	   //closed set is now implemented in grid
	   OpenSetList openset = new OpenSetList();//ordered by fvalue or just list make new class
	   //came from is implemented in grid
	   
	   final Node[][]nodes;
	   
	   int g_score = 0;//this is now apart of the grid
	   int f_score = g_score + distance_hx(start, goal);
	   
	   while(openset.size() != 0)
	   {
		   //first thing in ordered list or finding index of lowestone
		   OpenSetItem current= openset.head();
		   Point current_pt = current.getPoint();
		   if(current_pt == goal)
		   {
			   return reconstruct_path(came_from, goal);
		   }
		   openset.remove(current_pt);
		   closedset.add(current);//create method neigbornodes based on grid with current all around it are neighbors
		   
		   for( Node neighbor : neighbor_nodes(current))//neighbor nodes contains neighbors which are points with gscore?
		   {
			   //are neighbornodes initialized here?
			   if(closedset == true)
			   {
				   continue;
			   }
			   int tentative_g_score = current.gx + dist_between(current, neighbor);
			   if(!(openset.contains(neighbor_pt)) || tentative_g_score < neighbor.getGX())
			   {
				   came_from.put(neighbor, current);
				   int newgscore = neighbor.getGX();
				   newgscore = tentative_g_score;//set gscore
				   int newfscore = neighbor.getFX();
				   Point neighborpt = new Point(neighbor.getNodeX(), neighbor.getNodeY());//setting gscore 
				   newfscore = neighbor.getGX() + distance_hx(neighbor, goal);
				   
				   if(!(openset.contains(neighbor)))
				   {
					   openset.add(neighbor);
				   }
				   
			   }
		   }
	   }
	   //return failure
	   
   }
   public List<Point> reconstruct_path(HashMap came_from, Node current)//create new Path class?
   {
	   List<Point> total_path = new ArrayList<Point>();
	   while(came_from.containsValue(current))
	   {
		   current = came_from(current); 
		   total_path.add(current);
		   
	   }
	   return total_path;
   }
}

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
   public Function aStar(Point start, Point goal)
   {
	   List closedset = new ArrayList<>();
	   OrderedList openset = new OrderedList();
	   HashMap came_from = new HashMap<String, Node>();
	   final Node[][]nodes;
	   
	   int g_score = 0;
	   int f_score = g_score + distance_hx(start, goal);
	   
	   while(openset.size() != null)
	   {
		   Node current= getFirstInOpen();//says try next node? but its the lowest fscore...
		   if(current == goal)
		   {
			   return reconstruct_path(came_from, goal);
		   }
		   openset.remove(current);
		   closedset.add(current);
		   
		   for( Node neighbor : neighbor_nodes(current))//neighbor nodes contains neighbors which are points with gscore?
		   {
			   //are neighbornodes initialized here?
			   if(closedset.contains(neighbor))
			   {
				   continue;
			   }
			   int tentative_g_score = current.gx + dist_between(current, neighbor);
			   if(!(openset.contains(neighbor)) || tentative_g_score < neighbor.getGX())
			   {
				   came_from.put(neighbor, current);
				   int newgscore = neighbor.getGX();
				   newgscore = tentative_g_score;
				   //what is f_score is it apart of the node??
				   int newfscore = neighbor.getFX();
				   Point neighborpt = new Point(neighbor.getNodeX(), neighbor.getNodeY());
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
   public Path reconstruct_path(HashMap came_from, Node current)//create new Path class?
   {
	   Path total_path = new Path();
	   while(came_from.containsValue(current))
	   {
		   current = came_from(current); //is this updating current
		   total_path.append(current);
		   
	   }
	   return total_path;
   }
}

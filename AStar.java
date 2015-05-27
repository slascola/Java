import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class AStar 
{
   public int distance_hx(Node current, Node goal)//heuristic distance
   {
	   Point p1 = new Point(current.getNodeX(), current.getNodeY());
	   Point p2 = new Point(goal.getNodeX(), goal.getNodeY());
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
   public List<Point> neighbor_nodes(Node current)
   {
	   List<Point> valid_neighbors = new ArrayList<Point>();
	   
	   Point left = new Point(current.getNodeX() - 1, current.getNodeY());
	   Point right = new Point(current.getNodeX() + 1, current.getNodeY());
	   Point down = new Point(current.getNodeX(), current.getNodeY() +1);
	   Point up = new Point(current.getNodeX() + 1, current.getNodeY() -1);
	   
	   valid_neighbors.add(left);
	   valid_neighbors.add(right);
	   valid_neighbors.add(up);
	   valid_neighbors.add(down);
	   
	   return valid_neighbors;
   }
   public List<Point> aStar(Node start, Node goal, WorldModel world)
   {
	   int num_rows = world.getNumRows();
	   int num_cols = world.getNumCols();
	   Node[][] node_grid = new Node[num_rows][num_cols];
	   
	   for(int y = 0; y< num_rows; y++)
	   {
		   for(int x = 0; x < num_cols; x++)
		   {
			   Node node = new Node(x, y, false);
			   node_grid[y][x] = node;
		   }
	   }
	   //closed set is now implemented in grid
	   OpenSetList openset = new OpenSetList();//ordered by fvalue or just list make new class
	   //came from is implemented in grid
	   
	   
	   HashMap<Node, Node> came_from = new HashMap<Node, Node>();
	   int g_score = 0;//this is now apart of the grid
	   int f_score = g_score + distance_hx(start, goal);
	   
	   openset.insert(start, f_score);
	   
	   while(openset.size() != 0)
	   {
		   OpenSetItem current= openset.head();
		   Node current_node = current.getNode();
		   
		   if(current_node == goal)//do we have to define an equals method?
		   {
			   return reconstruct_path(came_from, goal);
		   }
		   
		   boolean cur_closed_set = current_node.getClosedSet();
		   cur_closed_set = true;
		   openset.remove(current_node);
		   
		   //create method neigbornodes based on grid with current all around it are neighbors
		   List<Point> nbr_nodes = neighbor_nodes(current_node);
		   for(Point neighbor : neighbor_nodes(current_node))
		   {
			   //need to check for other entities surrounding it
			   if(node_grid[neighbor.y][neighbor.x].getClosedSet() == true || (!world.within_bounds(neighbor)))
			   {
				   continue;
			   }
			   
			   //if(world.background[neighbor.y][neighbor.x] instanceof Ore)
				    
			   int current_g_score = dist_between(start, current_node);
			   Node neighbor_node = node_grid[neighbor.y][neighbor.x];
			   int tentative_g_score = current_g_score + dist_between(current_node, neighbor_node);
			   
			   int neighbor_g_score = dist_between(start, neighbor_node);
			   
			   if(!(openset.contains(neighbor_node)) || tentative_g_score < neighbor_g_score)
			   {
				   came_from.put(neighbor_node, current_node);
				   
				   neighbor_g_score = tentative_g_score;//set gscore
				   
				   int newfscore = neighbor_g_score + distance_hx(neighbor_node, goal);
				   
				   
				   //do we insert new f score into openset
				   
				   if(!(openset.contains(neighbor_node)))
				   {
					   openset.insert(neighbor_node, newfscore);
				   }
				   
			   }
		   }
	   }
	   return null;
	   
   }
   public List<Point> reconstruct_path(HashMap<Node, Node> came_from, Node current)
   {
	   List<Point> total_path = new ArrayList<Point>();
	   while(came_from.containsValue(current))
	   {
		   current = came_from.get(current); //is this current node passed now the current node from the map?
		   Point cur_point = new Point(current.getNodeX(), current.getNodeY());
		   total_path.add(cur_point);
		   
	   }
	   return total_path;
   }
}

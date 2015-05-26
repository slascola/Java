import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class AStar 
{
   public Function aStar(int start, int goal)
   {
	   List closedset = new ArrayList<>();
	   OrderedList openset = new OrderedList();
	   HashMap came_from = new HashMap<String, Node>();
	   final Node[][]nodes;
	   
	   int g_score = 0;
	   int f_score = g_score + hx_cost(start, goal);
	   
	   while(openset.size() != null)
	   {
		   Node current= getFirstInOpen();
		   if(current == nodes[ty][tx])
		   {
			   return reconstrust_path(came_from, goal);
		   }
	   }
	   
   }
}

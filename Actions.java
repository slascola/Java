
//adjacent and remove entity for sure
//maybe next position and blob next position
import java.lang.Math;
public class Actions extends Point
{
   public Actions(int x, int y)
   {
	   super(x, y);
   }
     public boolean adjacent(Point pt1, Point pt2)
     {
	   int x1 = pt1.x;
	   int x2 = pt2.x;
	   int y1 = pt1.y;
	   int y2 = pt2.y;
	   return ((x1 == x2 && Math.abs(y1 - y2) == 1) ||
			   (y1 == y2 && Math.abs(x1 - x2) == 1));
      }
  
}

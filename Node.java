
public class Node extends Point
{
   private int gx;
   private int hx;
   public Node(int x, int y, int gx, int hx)
   {
	   super(x, y);
	   this.gx = gx;
	   this.hx = hx;
   }
   public int getNodeX()
   {
	    return this.x;
   }
   public int getNodeY()
   {
	   return this.y;
   }
   public int getGX()
   {
	   return this.gx;
   }
   public int getHX()
   {
	   return this.hx;
   }
   public int distance_gx(Point p1, Point p2)
   {
	   int x_sq = (p1.x - p2.x) * (p1.x - p2.x);
	   int y_sq = (p1.y - p2.y) * (p1.y - p2.y);
	   return (int)Math.sqrt(x_sq + y_sq);
   }
  
   public void overlay_nodes(int[][] world, int num_cols, int num_rows, Point start, Point goal) 
	//change return type to ??
	{
		for(int y = 0; y < num_rows; y++)
		{
			for(int x = 0; x < num_cols; x++)
			{
				Point cur = new Point(x, y);
				int gx = this.distance_gx(start, cur);
				int hx = this.distance_hx(cur, goal);
				Node node = new Node(x, y, gx, hx);
			}
		}
		//now add to overlay grid
		
	}

}

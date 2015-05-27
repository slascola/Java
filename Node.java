
public class Node extends Point
{
   private int gx;
   
   private boolean closedset = false;
   public Node(int x, int y, int gx,  boolean closesdset)
   {
	   super(x, y);
	   this.gx = gx;
	   this.closedset = closedset;
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
   public boolean getClosedSet()
   {
	   return this.closedset;
   }
  
  
   public void overlay_nodes(int[][] world, int num_cols, int num_rows, Point start, Point goal) 
	//change return type to ??
	{
		for(int y = 0; y < num_rows; y++)
		{
			for(int x = 0; x < num_cols; x++)
			{
				Point cur = new Point(x, y);
				int gx = 0; //compute the gx in astar
				
				Node node = new Node(x, y, gx, closedset);
			}
		}
		//now add to overlay grid
		
	}

}

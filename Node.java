
public class Node extends Point
{
   private boolean closedset = false;
   public Node(int x, int y, boolean closesdset)
   {
	   super(x, y);
	  
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
   
   public boolean getClosedSet()
   {
	   return this.closedset;
   }
  
  
  

}

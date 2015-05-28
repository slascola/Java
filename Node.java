
public class Node extends Point
{
   private boolean closedset = false;
   private int g;
   private int f;
   private Node came_from;
   
   public Node(int x, int y, boolean closesdset)
   {
	   super(x, y);
	  
	   this.closedset = closedset;
	   this.g = 0;
	   this.f = 0;
	   this.came_from = null;
   }
   public int getNodeX()
   {
	    return this.x;
   }
   public int getNodeY()
   {
	   return this.y;
   }
   public Node getCameFrom()
   {
	   return this.came_from;
   }
   public void setCameFrom(Node node)
   {
	 this.came_from = node;  
   }
   public boolean getClosedSet()
   {
	   return this.closedset;
   }
   public void setClosedSet(boolean value)
   {
	    this.closedset = value;
   }
   public int getG()
   {
	   return this.g;
	  
   }
   public void setG(int newg)
   {
	   this.g = newg;
   }
   public void setF(int newf)
   {
	   this.f = newf;
   }
   
   public int getF()
   {
	   return this.f;
   }
   public boolean equals(Object o)
   {
	   Node other = (Node) o;
	   return this.getNodeX() == other.getNodeX() && this.getNodeY() == other.getNodeY();
   }
  
  
  

}


public class Entity 
{
   private String name;
   private Point position;
   public Entity(String name, Point position)
   {
	   this.name = name;
	   this.position = position;
   }
   protected void set_position(Point point)
   {
	   this.position = point;
   }
   protected Point get_position()
   {
	   return this.position;
   }
   protected String get_name()
   {
	   return this.name;
   }
}

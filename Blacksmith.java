
public class Blacksmith extends Dudes
{
   private int resource_count;
   private int resource_distance;
   public Blacksmith(String name, Point position, int resource_limit, int rate, int resource_distance)
   {
	   super(name, position, rate, resource_limit);
	   this.resource_count = 0;
	   this.resource_distance = resource_distance;
	   
   }
   protected String entity_string()
   {
	   return 
   }
}


public class Blacksmith extends Dudes
{
   private int resource_count;
   private int resource_distance;
   public Blacksmith(String name, Point position, int resource_limit, int rate, int resource_distance, int resource_count)
   {
	   super(name, position, rate, resource_limit, resource_count);
	   this.resource_count = 0;
	   this.resource_distance = 1;
	   
   }
   protected String entity_string()
   {
	   String blacksmithString;
	   blacksmithString = "blacksmith" + this.get_name() + String.valueOf(this.get_position()) 
			   + Integer.toString(this.get_resource_limit()) +
			   Integer.toString(this.get_rate()) + Integer.toString(this.resource_distance);
	   return blacksmithString;
   }
}


public class Vein extends Rate
{
   private int resource_distance;
   public Vein(String name, int rate, Point position, int resource_distance)
   {
	   super(name, position, rate);
	   this.resource_distance = 1;
	   
   }
   protected int get_resource_distance()
   {
	   return this.resource_distance;
   }
   protected String entity_string()
   {
	   String vein_string;
	   vein_string = "vein" + this.get_name() + String.valueOf(this.get_position()) + 
			   Integer.toString(this.get_rate()) + Integer.toString(this.get_resource_distance());
	   return vein_string;
   }
}

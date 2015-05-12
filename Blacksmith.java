import java.util.List;
import processing.core.*;
public class Blacksmith extends Dudes
{
   private int resource_count;
   private int resource_distance;
   public Blacksmith(String name, Point position, List<PImage> imgs, int resource_limit, int rate, int resource_distance, 
		   int resource_count)
   {
	   super(name, position, imgs, rate, resource_limit, resource_count);
	   this.resource_count = 0;
	   this.resource_distance = 1;
	   
   }
   protected String entity_string()
   {
	   String blacksmithString;
	   blacksmithString = "blacksmith" + " " + this.get_name() + " " + (this.get_position().x) + " "
			   + this.get_position().y + " " + (this.get_resource_limit()) + " " +
			   (this.get_rate()) + " " + (this.resource_distance);
	   return blacksmithString;
   }
}

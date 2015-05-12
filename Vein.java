import java.util.List;
import processing.core.*;
public class Vein extends Rate
{
   private int resource_distance;
   public Vein(String name, int rate, Point position, int resource_distance, List<PImage> imgs)
   {
	   super(name, position, imgs, rate);
	   this.resource_distance = 1;
	   
   }
   protected int get_resource_distance()
   {
	   return this.resource_distance;
   }
   protected String entity_string()
   {
	   String vein_string;
	   vein_string = "vein" + " " + this.get_name() + " " + this.get_position().x + " "+ 
	           this.get_position().y + " " + this.get_rate() + " "+ this.get_resource_distance();
	   return vein_string;
   }
}

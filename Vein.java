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
   protected void schedule_vein(WorldModel world, long ticks, 
           HashMap<String, PImage> i_store)
{
Actions.schedule_action(world, this, create_vein_action(world, i_store), 
				ticks + this.get_rate());
}
}

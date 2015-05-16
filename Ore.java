import java.util.List;
import java.util.HashMap;
import processing.core.*;
public class Ore extends Rate
{
   private int rate;
   public Ore(String name, Point position, int rate, List<PImage> imgs)
   {
	   super(name, position, imgs, rate); 
	   this.rate = 5000;
   }
   protected String entity_string()
   {
	   String ore_string;
	   ore_string = "ore" + " " + this.get_name() + " " + this.get_position().x + " " 
			  + this.get_position().y + " " + (this.get_rate());
	   return ore_string;
   }
   protected void schedule_ore(WorldModel world, long ticks, HashMap<String, PImage> i_store)
	{
		Actions.schedule_action(world, this, create_ore_transform_action(world, i_store),
								ticks + this.get_rate());
	}
}

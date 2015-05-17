import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.function.LongConsumer;

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
           HashMap<String, List<PImage>> map)
{
	   Actions.schedule_action(world, this, create_vein_action(world, map), 
				ticks + this.get_rate());
}
   protected LongConsumer create_vein_action(WorldModel world, HashMap<String, List<PImage>> i_store)
   {
	   LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point open_pt = Actions.find_open_around(world, this.get_position(), this.get_resource_distance());
	    	  if(open_pt != null)
	    	  {
	    		  Ore ore = this.create_ore(world, "ore -" + this.get_name() + " - " + current_ticks, open_pt,
	    				  current_ticks, i_store);
	    		  world.add_entity(ore);
	    		  
	    	  }
	    	  Actions.schedule_action(world, this, this.create_vein_action(world, i_store), current_ticks + this.get_rate());
	      };
	      return action[0];
   }
   protected Ore create_ore(WorldModel world, String name, Point pt, long ticks, HashMap<String, List<PImage>> i_store)
   {
	   Random rand = new Random();
	   Ore ore = new Ore(name, pt, rand.nextInt(Actions.ORE_CORRUPT_MAX - Actions.ORE_CORRUPT_MIN 
			   + 1) + Actions.ORE_CORRUPT_MIN, Main.get_images(i_store, "ore"));
	   return ore;
	   
   }
}

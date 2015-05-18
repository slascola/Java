import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.function.LongConsumer;

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
   
   protected OreBlob create_blob(WorldModel world, String name, Point pt, int Rate, long ticks, 
           HashMap<String, List<PImage>> i_store)		              
   	{
	   Random rand = new Random();
	   OreBlob blob = new OreBlob(name, pt, rate, ((rand.nextInt(Actions.BLOB_ANIMATION_MAX - 
			   Actions.BLOB_ANIMATION_MIN + 1) + Actions.BLOB_ANIMATION_MIN) * Actions.BLOB_ANIMATION_RATE_SCALE),
			   Main.get_images(i_store, "blob"));
	   blob.schedule_blob(world, ticks, i_store);
	   return blob;

   	}
	protected LongConsumer create_ore_transform_action(WorldModel world, HashMap<String, List<PImage>> i_store)
	{
		LongConsumer[] action = { null };
		action[0] = (long current_ticks) -> 
		{
			//System.out.println("ore going to be transformed");
			this.remove_pending_action(action[0]);
			OreBlob blob = this.create_blob(world, this.get_name() + " -- blob",
					this.get_position(), this.get_rate() / Actions.BLOB_RATE_SCALE,
					current_ticks, i_store);
			
			Actions.remove_entity(this, world);
			world.add_entity(blob);
			
		};
		
		return action[0];
	  }
   protected void schedule_ore(WorldModel world, long ticks, HashMap<String, List<PImage>> i_store)
	{
	   //System.out.println("it came herrree");
		Actions.schedule_action(world, this, create_ore_transform_action(world, i_store),
								ticks + this.get_rate());
	}
}

import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class OreBlob extends Rate
{
	private int animation_rate;
   public OreBlob(String name, Point position, int rate, int animation_rate, List<PImage> imgs)
   {
	   super(name, position, imgs, rate);
	   this.animation_rate = animation_rate;
   }
   protected int get_animation_rate()
   {
	   return this.animation_rate;
   }
   
   protected boolean blob_to_vein(WorldModel world, Vein vein)
   {
	   Point entity_pt = this.get_position();
	   if(vein == null)
	   {
		   return false;
	   }
	   Point vein_pt = vein.get_position();
	   if (Actions.adjacent(entity_pt, vein_pt))
	   {
		   Actions.remove_entity(vein, world);
		   return true;
	   }
	   else
	   {
		   Point new_pt = Actions.blob_next_position(world, entity_pt, vein_pt);
		   Entity old_entity = world.get_tile_occupant(new_pt);
		   if (old_entity instanceof Ore)
           {
			 Actions.remove_entity(old_entity, world);
		   }
		   world.move_entity(old_entity, entity_pt);
		   return false; 
	   }
   }
	protected Quake create_quake(WorldModel world, Point pt, long ticks, 
			 HashMap<String, List<PImage>> i_store)
	{
		Quake quake = new Quake("quake", pt, Actions.QUAKE_ANIMATION_RATE, Main.get_images(i_store, "quake"));
		quake.schedule_quake(world, ticks);
		return quake;
	}
	protected LongConsumer create_ore_blob_action(WorldModel world, HashMap <String, List<PImage>> i_store)
	{
		LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point entity_pt = this.get_position();
	    	  Vein vein = (Vein) world.find_nearest(entity_pt, Vein.class);
	    	  
	    	  boolean found = this.blob_to_vein(world, vein);
	    	  
	    	  long next_time = current_ticks + this.get_rate();
	    	  
	    	  if(found)
	    	  {
    		    Quake quake = this.create_quake(world, entity_pt, current_ticks, i_store);
    		    world.add_entity(quake);
    		    next_time = current_ticks + this.get_rate() * 2;
	    	  }
	    	  Actions.schedule_action(world, this, this.create_ore_blob_action(world, i_store), next_time);
	    	    
	      };
		return action[0];
	}
	
	protected void schedule_blob(WorldModel world, long ticks, HashMap<String, List<PImage>> i_store)
	{
		System.out.println("blob here");
		Actions.schedule_action(world, this, create_ore_blob_action(world, i_store),
								ticks + this.get_rate());
		Actions.schedule_animation(world, this, 0); //just added in 0. keep eye on this
	}
	
	
	
	
   
   
}

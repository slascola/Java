import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class MinerNotFull extends Miner
{

	private int current_img;
	private int resource_count;
	   public MinerNotFull(String name, int resource_limit, Point position, 
	      int rate, List<PImage> imgs, int animation_rate, int resource_count)
	   {
		  super(name, position, rate, resource_limit, resource_count, animation_rate, imgs);
	      this.current_img = 0;
		  this.resource_count = 0;
	   }
	   public String entity_string()
	   {
		   String miner_string;
		   miner_string = "miner" + " " + this.get_name() + " " + (this.get_position().x) + " " +
		   (this.get_position().y) + " " + (this.get_resource_limit()) + " " +
		   (this.get_rate()) + " " + (this.get_animation_rate());
		   return miner_string;
	   }
	   
	   protected Miner try_transform_miner_not_full(WorldModel world)
	   {
	      if (this.resource_count < this.get_resource_limit())
		  {
	    	  return this;

		  }
	      else
		  {
			 MinerFull new_entity =  new MinerFull(
	            this.get_name(), this.get_resource_limit(),
	            this.get_position(), this.get_rate(),
	            this.get_images(), this.get_animation_rate(), this.get_resource_count());
	         return new_entity; 
		  }
	   }
	   
	         
	   
	   protected boolean miner_to_ore(WorldModel world, Ore ore)
	   {
		   Point entity_pt = this.get_position();
		   if(ore == null)
		   {
			   return false;
		   }
		   Point ore_pt = ore.get_position();
		   if (Actions.adjacent(entity_pt, ore_pt))
		   {
			   this.set_resource_count(1 + this.get_resource_count());
			   Actions.remove_entity(ore, world);
			   return true;
		   }
		   else
		   {
			   Point new_pt = Actions.next_position(world, entity_pt, ore_pt); 
			   world.move_entity(this, new_pt);
			   return false;
		   }
	   }
	   protected LongConsumer create_miner_action(WorldModel world, HashMap<String, List<PImage>> i_store)
	   {
		   LongConsumer[] action = { null };
		      action[0] = (long current_ticks) -> {
		    	  this.remove_pending_action(action[0]);
		    	  
		    	  Point entity_pt = this.get_position();
		    	  Ore ore = (Ore) world.find_nearest(entity_pt, Ore.class);
		    	  
		    	  boolean found = this.miner_to_ore(world, ore);
		    	  //System.out.println(found);
		    	  
		    	  Miner new_entity = this;
		    	  
		    	  if (found == true)
		    	  {
		    		  //System.out.println("ayyy miner");
		    		  new_entity = this.try_transform_miner(world, this :: try_transform_miner_not_full);
		    		  //System.out.println(new_entity);
		    	  }
		    	  Actions.schedule_action(world, new_entity, new_entity.create_miner_action(world, i_store),
		    	  current_ticks + new_entity.get_rate());
		      };
		      return action[0];
	   }
	   
}



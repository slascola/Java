import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class MinerFull extends Miner
{

   private int current_img;
   private int resource_count;
   public MinerFull(String name, int resource_limit, Point position, 
		      int rate, List<PImage> imgs, int animation_rate, int resource_count)
   {
	   super(name, position, rate, resource_limit, resource_count, animation_rate, imgs);
	   this.current_img = 0;
	   this.resource_count = resource_limit;
   }
   
   protected boolean miner_to_smith(WorldModel world, Blacksmith smith)
   {
	   Point entity_pt = this.get_position();
	   //System.out.println(smith == null);
	   if(smith == null)
	   {
		   return false;
	   }
	   Point smith_pt = smith.get_position();
	   if (Actions.adjacent(entity_pt, smith_pt))
	   {
		   smith.set_resource_count(smith.get_resource_count() + this.get_resource_count());
		   this.set_resource_count(0);
		   return true;
	   }
	   else
	   {
		   Point new_pt = Actions.next_position(world, entity_pt, smith_pt);
		   world.move_entity(this, new_pt);
		   return false;
	   }
   }
   
   protected Miner try_transform_miner_full(WorldModel world)
   {
	   MinerNotFull new_entity = new MinerNotFull(this.get_name(), 
			   this.get_resource_limit(), this.get_position(),
			   this.get_rate(), this.get_images(), this.get_animation_rate(), this.get_resource_count());
	   return new_entity;
   }
   protected LongConsumer create_miner_action(WorldModel world, HashMap <String, List<PImage>> i_store)
   {
	   LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point entity_pt = this.get_position();
	    	  Blacksmith smith = (Blacksmith) world.find_nearest(entity_pt, Blacksmith.class);
	    	  //System.out.println(smith);
	    	  boolean found = this.miner_to_smith(world, smith);
	    	  
	    	  Miner new_entity  = this;
	    	  
	    	  if (found)
	    	  {
	    		  new_entity = this.try_transform_miner(world, this :: try_transform_miner_full);
	    		  
	    	  }
	    	  Actions.schedule_action(world, new_entity, new_entity.create_miner_action(world, i_store), current_ticks + new_entity.get_rate());
	      };
	      return action[0];
	      
   }
}

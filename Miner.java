import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.*;

import processing.core.*;
public class Miner extends Dudes
{
   private int resource_count;
   private int resource_limit;
   private int animation_rate;
   private int current_img;
   private List<LongConsumer> pending_actions;
   public Miner(String name, Point position, int rate, int resource_limit, int resource_count, 
		   int animation_rate, List<PImage> imgs)
   {
	   super(name, position, imgs, rate, resource_limit, resource_count);
	   this.current_img = 0;
	   this.resource_count = 0;
	   this.resource_limit = resource_limit;
	   this.animation_rate = animation_rate;
	   
   }
   
   protected int get_animation_rate()
   {
	  return this.animation_rate;
   }
   protected void clear_pending_actions_new(WorldModel world)
   {
	   for(LongConsumer action : this.get_pending_actions())
	   {
		  world.unschedule_action(action);
	   }
	   this.clear_pending_actions();
   }
   protected Miner try_transform_miner(WorldModel world, Function<WorldModel, Miner> transform)
   {
	   Miner new_entity = transform.apply(world);
	   if (this != new_entity)
	   {
		   this.clear_pending_actions_new(world);
		   world.remove_entity_at(this.get_position());
		   world.add_entity(new_entity);
		   Actions.schedule_animation(world, new_entity,0);
	   }
	   return new_entity;
   }
   protected LongConsumer create_miner_action(WorldModel world, HashMap<String, List<PImage>> i_store)
   {
	   return null;
   }
   
   protected void schedule_miner(WorldModel world, long ticks, HashMap<String, List<PImage>> i_store)
   {
	   Actions.schedule_action(world, this, this.create_miner_action(world, i_store),  ticks + this.get_rate());
	   Actions.schedule_animation(world, this, 0);
   }
 
  
   
}

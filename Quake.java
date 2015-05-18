import java.util.List;
import java.util.ArrayList;
import java.util.function.LongConsumer;
import processing.core.*;
public class Quake extends ActionItems
{
	private int animation_rate;
   public Quake(String name, Point position, int animation_rate, List<PImage> imgs)
   {
	   super(name, position, imgs);
	   this.animation_rate = animation_rate;
	   
   }
   protected int get_animation_rate()
   {
	   return this.animation_rate;
   }
   protected void schedule_quake(WorldModel world, long ticks)
	{
		Actions.schedule_animation(world, this, Actions.QUAKE_STEPS);
		Actions.schedule_action(world, this, create_entity_death_action(world),
									ticks + Actions.QUAKE_DURATION);
	}
	
	protected LongConsumer create_entity_death_action(WorldModel world)
	   {
		   LongConsumer[] action = { null };
	       action[0] = (long current_ticks) -> {
	       
	    	   this.remove_pending_action(action[0]);
	    	   Point pt = this.get_position();
	    	   ArrayList<Point> pt_list = new ArrayList<Point>();
	    	   Actions.remove_entity(this, world);
	    	   pt_list.add(pt);
	    	   
	    	   
	       };

	    return action[0];
	    
	 }
}

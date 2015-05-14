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
	   this.pending_actions = new ArrayList<LongConsumer>();
   }
   
   protected int get_animation_rate()
   {
	  return this.animation_rate;
   }
   protected void clear_pending_actions_new(WorldModel world)
   {
	   for (LongConsumer action : this.get_pending_actions())
	   {
		   
	   }
   }
   
}

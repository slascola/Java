
public class Miner extends Dudes
{
   private int resource_count;
   private int resource_limit;
   private int animation_rate;
   public Miner(String name, Point position, int rate, int resource_limit, int resource_count, int animation_rate)
   {
	   super(name, position, rate, resource_limit, resource_count);
	   this.resource_count = 0;
	   this.resource_limit = resource_limit;
	   this.animation_rate = animation_rate;
   }
   
   protected int get_animation_rate()
   {
	  return this.animation_rate;
   }
   
   
}

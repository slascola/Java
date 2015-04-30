
public class Quake extends Entity
{
	private int animation_rate;
   public Quake(String name, Point position, int animation_rate)
   {
	   super(name, position);
	   this.animation_rate = animation_rate;
	   
   }
   protected int get_animation_rate()
   {
	   return this.animation_rate;
   }
}

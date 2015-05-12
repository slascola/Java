import java.util.List;
import processing.core.*;
public class Quake extends Entity
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
}

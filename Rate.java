import java.util.List;
import processing.core.*;
public class Rate extends Entity
{
   private int rate;
   public Rate(String name, Point position, List<PImage> imgs, int rate)
   {
	   super(name, position, imgs);
	   this.rate = rate;
   }
   protected int get_rate()
   {
	   return this.rate;
   }
}

import java.util.List;
import processing.core.*;
public class Dudes extends ActionItems
{
   private int rate;
   private int resource_limit;
   private int resource_count;
   public Dudes(String name, Point position, List<PImage> imgs, int rate, int resource_limit, int resource_count)
   {
	   super(name, position, imgs);
	   this.rate = rate;
	   this.resource_limit = resource_limit;
	   this.resource_count = 0;
   }
   protected void set_resource_count(int n)
   {
	   this.resource_count = n;
   }
   protected int get_resource_count()
   {
	   return this.resource_count;
	   
   }
   protected int get_resource_limit()
   {
	   return this.resource_limit;
   }
   protected int get_rate()
   {
	   return this.rate;
   }
}

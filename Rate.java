
public class Rate extends Entity
{
   private int rate;
   public Rate(String name, Point position, int rate)
   {
	   super(name, position);
	   this.rate = rate;
   }
   protected int get_rate()
   {
	   return this.rate;
   }
}

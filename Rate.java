
public class Rate extends Entity
{
   private double rate;
   public Rate(Entity name, Entity position, double rate)
   {
	   super(name, position);
	   this.rate = rate;
   }
   protected double get_rate()
   {
	   return this.rate;
   }
}

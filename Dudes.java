
public class Dudes extends Entity
{
   private double rate;
   private double resource_limit;
   public Dudes(Entity name, Entity position, double rate, double resource_limit)
   {
	   super(name, position);
	   this.rate = rate;
	   this.resource_limit = resource_limit;
   }
   protected void set_resource_count(double n)
   {
	   this.resource_count = n;
   }
   protected int get_resource_count()
   {
	   return this.resource_count;
	   
   }
   protected double get_resource_limit()
   {
	   return this.resource_limit;
   }
   protected double get_rate()
   {
	   return this.rate;
   }
}

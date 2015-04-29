//Object declaration original like String or from class
public class Miner extends Dudes
{
   private double resource_count;
   private double resource_limit;
   public Miner(Entity name, Dudes resource_limit, Entity position, Dudes rate)
   {
	   super(name, position, rate, resource_limit);
	   this.resource_count = 0.0;
	   this.resource_limit = resource_limit;
   }
   
}

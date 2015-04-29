
public class Miner extends Dudes
{
   private int resource_count;
   private int resource_limit;
   public Miner(String name, Point position, int rate, int resource_limit, int resource_count)
   {
	   super(name, position, rate, resource_limit, resource_count);
	   this.resource_count = 0;
	   this.resource_limit = resource_limit;
   }
   
}

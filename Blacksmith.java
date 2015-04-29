
public class Blacksmith extends Dudes
{
   private double resource_count;
   private double resource_distance;
   public Blacksmith(Entity name, Entity position, Dudes resource_limit, Dudes rate, double resource_distance)
   {
	   super(name, position, rate, resource_limit);
	   this.resource_count = 0.0;
	   this.resource_distance = resource_distance;
	   
   }
   protected String entity_string()
   {
	   return 
   }
}

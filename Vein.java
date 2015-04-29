
public class Vein extends Rate
{
   private double resource_distance;
   public Vein(Entity name, Rate rate, Entity Position, double resource_distance)
   {
	   super(name, position, rate)
	   this.resource_distance = 1.0;
	   
   }
   protected double get_resource_distance()
   {
	   return this.resource_distance;
   }
   protected String entity_string()
   {
	   //help
   }
}

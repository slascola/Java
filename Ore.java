
public class Ore extends Rate
{
   private double rate;
   public Ore(Entity name, Enitity position, Rate rate)
   {
	   super(name, position, rate); //redefining rate?
	   this.rate = 5000.0;
   }
   protected String entity_string()
   {
	   //what
   }
}

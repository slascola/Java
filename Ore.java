
public class Ore extends Rate
{
   private int rate;
   public Ore(String name, Point position, int rate)
   {
	   super(name, position, rate); //redefining rate?
	   this.rate = 5000;
   }
   protected String entity_string()
   {
	   //what
   }
}

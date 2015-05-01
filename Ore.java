
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
	   String ore_string;
	   ore_string = "ore" + this.get_name() + String.valueOf(this.get_position()) + 
			   Integer.toString(this.get_rate());
	   return ore_string;
   }
}

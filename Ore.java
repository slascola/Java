
public class Ore extends Rate
{
   private int rate;
   public Ore(String name, Point position, int rate)
   {
	   super(name, position, rate); 
	   this.rate = 5000;
   }
   protected String entity_string()
   {
	   String ore_string;
	   ore_string = "ore" + " " + this.get_name() + " " + this.get_position().x + " " 
			  + this.get_position().y + " " + (this.get_rate());
	   return ore_string;
   }
}

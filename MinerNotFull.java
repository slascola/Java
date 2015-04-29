
public class MinerNotFull extends Miner
{

	private int current_img;
	private int resource_count;
	   public MinerNotFull(String name, int resource_limit, Point position, 
	      int rate, String imgs, int animation_rate)
	   {
		  super(name, position, rate, resource_limit, animation_rate);
	      this.current_img = 0;
		  this.resource_count = 0;
	   }
	   public String entity_string()
	   {
		   String miner_string;
		   miner_string = "miner" + this.name + toString(this.position.x) +
		   toString(this.position.y) + toString(this.resource_limit) + 
		   toString(this.rate) + toString(this.animation_rate);
		   return miner_string;
	   }
	   protected MinerNotFull try_transform_miner_not_full(WorldModel world)
	   {
	      if (this.resource_count < this.resource_limit)
		  {
			  return this;
		  }
	      else
		  {
			 MinerFull new_entity =  new MinerFull(
	            this.get_name(), this.get_resource_limit(),
	            this.get_position(), this.get_rate(),
	            this.get_images(), this.get_animation_rate());
	         return new_entity; 
		  }
	         
	   }
	   
	   protected boolean miner_to_ore(WorldModel world, Ore ore)
	   {
		   Point entity_pt = this.get_position();
		   
	   }
}



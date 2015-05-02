
public class MinerNotFull extends Miner
{

	private int current_img;
	private int resource_count;
	   public MinerNotFull(String name, int resource_limit, Point position, 
	      int rate, String imgs, int animation_rate, int resource_count)
	   {
		  super(name, position, rate, resource_limit, resource_count, animation_rate);
	      this.current_img = 0;
		  this.resource_count = 0;
	   }
	   public String entity_string()
	   {
		   String miner_string;
		   miner_string = "miner" + " " + this.get_name() + " " + (this.get_position().x) + " " +
		   (this.get_position().y) + " " + (this.get_resource_limit()) + " " +
		   (this.get_rate()) + " " + (this.get_animation_rate());
		   return miner_string;
	   }
	   protected Miner try_transform_miner_not_full(WorldModel world)
	   {
	      if (this.resource_count < this.get_resource_limit())
		  {
			  return this;
		  }
	      else
		  {
			 Miner new_entity =  new MinerFull(
	            this.get_name(), this.get_resource_limit(),
	            this.get_position(), this.get_rate(),
	            this.get_images(), this.get_animation_rate());
	         return new_entity; 
		  }
	         
	   }
	   
	   protected boolean miner_to_ore(WorldModel world, Ore ore)
	   {
		   Point entity_pt = this.get_position();
		   if(ore != null)
		   {
			   return false;
		   }
		   Point ore_pt = ore.get_position();
		   if (actions.adjacent(entity_pt, ore_pt))
		   {
			   this.set_resource_count(1 + this.get_resource_count());
			   actions.remove_entity(ore, world);
			   return true;
		   }
		   else
		   {
			   Point new_pt = actions.next_position(world, entity_pt, ore_pt); 
			   world.move_entity(this, new_pt);
			   return false;
		   }
	   }
}



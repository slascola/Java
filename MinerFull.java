
public class MinerFull extends Miner
{

   private int current_img;
   private int resource_count;
   public MinerFull(String name, int resource_limit, Point position, 
		      int rate, String imgs, int animation_rate)
   {
	   super(name, position, rate, resource_limit, animation_rate);
	   this.current_img = 0;
	   this.resource_count = resource_limit;
   }
   protected MinerNotFull try_transform_miner_full(WorldModel world)
   {
	   MinerNotFull new_entity = new MinerNotFull(this.get_name(), 
			   this.get_resource_limit(), this.get_position(),
			   this.get_rate(), this.get_images(), this.get_animation_rate());
	   return new_entity;
   }
}

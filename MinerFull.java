//is name from Entity or Point like in Entity.java
public class MinerFull extends Miner
{

   private int current_img;
   private int resource_count;
   public MinerFull(Entity name, Dudes resource_limit, Entity position, 
		      Dudes rate, Entity imgs, Miner animation_rate)
   {
	   super(name, resource_limit, position, rate, imgs, animation_rate);
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

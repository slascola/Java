
public class MinerFull extends Miner
{

   private int current_img;
   private int resource_count;
   public MinerFull(String name, int resource_limit, Point position, 
		      int rate, String imgs, int animation_rate, int resource_count)
   {
	   super(name, position, rate, resource_limit, resource_count, animation_rate);
	   this.current_img = 0;
	   this.resource_count = resource_limit;
   }
   protected boolean miner_to_smith(WorldModel world, Blacksmith smith)
   {
	   Point entity_pt = this.get_position();
	   if(smith != null)
	   {
		   return false;
	   }
	   Point smith_pt = smith.get_position();
	   if (Actions.adjacent(entity_pt, smith_pt))
	   {
		   smith.set_resource_count(smith.get_resource_count() + this.get_resource_count());
		   this.set_resource_count(0);
		   return true;
	   }
	   else
	   {
		   Point new_pt = Actions.next_position(world, entity_pt, smith_pt);
		   world.move_entity(this, new_pt);
		   return false;
	   }
   }
   protected MinerNotFull try_transform_miner_full(WorldModel world)
   {
	   MinerNotFull new_entity = new MinerNotFull(this.get_name(), 
			   this.get_resource_limit(), this.get_position(),
			   this.get_rate(), this.get_images(), this.get_animation_rate());
	   return new_entity;
   }
}

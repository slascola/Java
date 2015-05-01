
public class OreBlob extends Rate
{
	private int animation_rate;
   public OreBlob(String name, Point position, int rate, int animation_rate)
   {
	   super(name, position, rate);
	   this.animation_rate = animation_rate;
   }
   protected int get_animation_rate()
   {
	   return this.animation_rate;
   }
   protected boolean blob_to_vein(WorldModel world, Vein vein)
   {
	   Point entity_pt = this.get_position();
	   if(vein != null)
	   {
		   return false;
	   }
	   Point vein_pt = vein.get_position();
	   if (actions.adjacent(entity_pt, vein_pt))
	   {
		   actions.remove_entity(vein, world);
		   return true;
	   }
	   else
	   {
		   Point new_pt = actions.blob_next_position(world, entity_pt, vein_pt);
		   Entity old_entity = world.get_title_occupant(new_pt);
		   if (old_entity instanceof Ore)
           {
			 actions.remove_entity(old_entity, world);
		   }
		   return false; 
	   }
   }
}

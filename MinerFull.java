import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class MinerFull extends Miner
{

   private int current_img;
   private int resource_count;
   private Node[][] nodegrid;
   private OpenSetList openset;
   public MinerFull(String name, int resource_limit, Point position, 
		      int rate, List<PImage> imgs, int animation_rate, int resource_count)
   {
	   super(name, position, rate, resource_limit, resource_count, animation_rate, imgs);
	   this.current_img = 0;
	   this.resource_count = resource_limit;
   }
   public void setOpenSet(OpenSetList newopen)
   {
	   this.openset = newopen;
   }
   public OpenSetList getOpenSet()
   {
	   return this.openset;
   }
   public Node[][] getGrid()
   {
	   return this.nodegrid;
   }
   protected boolean miner_to_smith(WorldModel world, Blacksmith smith)
   {
	   Point entity_pt = this.get_position();
	   
	   if(smith == null)
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
		   int num_rows = world.getNumRows();
	  	   int num_cols = world.getNumCols();
	  	   nodegrid = new Node[num_rows][num_cols];
	  	   
	  	   for(int y = 0; y< num_rows; y++)
	  	   {
	  		   for(int x = 0; x < num_cols; x++)
	  		   {
	  			   Node node = new Node(x, y, false);
	  			   nodegrid[y][x] = node;
	  		   }
	  	   }
		   Point new_pt = Actions.next_position(world, entity_pt, smith_pt, nodegrid);
		   world.move_entity(this, new_pt);
		   return false;
	   }
   }
   
   protected Miner try_transform_miner_full(WorldModel world)
   {
	   MinerNotFull new_entity = new MinerNotFull(this.get_name(), 
			   this.get_resource_limit(), this.get_position(),
			   this.get_rate(), this.get_images(), this.get_animation_rate(), this.get_resource_count());
	   return new_entity;
   }
   protected LongConsumer create_miner_action(WorldModel world, HashMap <String, List<PImage>> i_store)
   {
	   LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point entity_pt = this.get_position();
	    	  Blacksmith smith = (Blacksmith) world.find_nearest(entity_pt, Blacksmith.class);
	    	  
	    	  boolean found = this.miner_to_smith(world, smith);
	    	  
	    	  Miner new_entity  = this;
	    	  
	    	  if (found)
	    	  {
	    		  new_entity = this.try_transform_miner(world, this :: try_transform_miner_full);
	    		  
	    	  }
	    	  Actions.schedule_action(world, new_entity, new_entity.create_miner_action(world, i_store), current_ticks + new_entity.get_rate());
	      };
	      return action[0];
	      
   }
}

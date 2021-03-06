import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class MinerNotFull extends Miner
{

	private int current_img;
	private Node[][] node_grid;
	private OpenSetList openset;
	   public MinerNotFull(String name, int resource_limit, Point position, 
	      int rate, List<PImage> imgs, int animation_rate, int resource_count)
	   {
		  super(name, position, rate, resource_limit, resource_count, animation_rate, imgs);
	      this.current_img = 0;
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
		   return this.node_grid;
	   }
	   public List<PImage> magnet_images()
	   {
		   String key = "magnet";
		   HashMap<String, List<PImage>> map = Main.getMap();
		   if(map.containsKey(key))
			{
				
	           List<PImage> object_image = map.get(key);	
				return object_image;
				
			}
		   else {
				 List<PImage> object_image = map.get("background_default");
				 return object_image;
				 
			}
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
		  //System.out.println(this.get_resource_count() + ":" + this.get_resource_limit());
	      if (this.get_resource_count() < this.get_resource_limit())
		  {
	    	  return this;

		  }
	      else if(Main.getMousePressed() == true)
	      {
	    	  Magnet new_entity =  new Magnet(this.get_name(), this.get_resource_limit(), this.get_position(), this.get_rate(),
	    			  this.magnet_images(), this.get_animation_rate(), this.get_resource_count());

	          return new_entity; 
	      }
	      else
		  {
			 MinerFull new_entity =  new MinerFull(
	            this.get_name(), this.get_resource_limit(),
	            this.get_position(), this.get_rate(),
	            this.get_images(), this.get_animation_rate(), this.get_resource_count());
	         return new_entity; 
		  }
	   }
	   
	         
	   
	   protected boolean miner_to_ore(WorldModel world, Ore ore)
	   {
		   Point entity_pt = this.get_position();
		   if(ore == null)
		   {
			   return false;
		   }
		   Point ore_pt = ore.get_position();
		   if (Actions.adjacent(entity_pt, ore_pt))
		   {
			   this.set_resource_count(1 + this.get_resource_count());
			   
			   Actions.remove_entity(ore, world);
			   return true;
		   }
		   else
		   {
			   int num_rows = world.getNumRows();
		  	   int num_cols = world.getNumCols();
		  	   node_grid = new Node[num_rows][num_cols];
		  	   
		  	   for(int y = 0; y< num_rows; y++)
		  	   {
		  		   for(int x = 0; x < num_cols; x++)
		  		   {
		  			   Node node = new Node(x, y, false);
		  			   node_grid[y][x] = node;
		  		   }
		  	   }
			   Point new_pt = Actions.next_position(world, entity_pt, ore_pt, node_grid); 
			   world.move_entity(this, new_pt);
			   return false;
		   }
	   }
	   protected LongConsumer create_miner_action(WorldModel world, HashMap<String, List<PImage>> i_store)
	   {
		   LongConsumer[] action = { null };
		      action[0] = (long current_ticks) -> {
		    	  this.remove_pending_action(action[0]);
		    	  
		    	  Point entity_pt = this.get_position();
		    	  Ore ore = (Ore) world.find_nearest(entity_pt, Ore.class);
		    	  
		    	  boolean found = this.miner_to_ore(world, ore);
		    	  
		    	  
		    	  Miner new_entity = this;
		    	  
		    	  if (found == true)
		    	  {
		    		 
		    		  new_entity = this.try_transform_miner(world, this :: try_transform_miner_not_full);
		    		  
		    	  }
		    	  Actions.schedule_action(world, new_entity, new_entity.create_miner_action(world, i_store),
		    	  current_ticks + new_entity.get_rate());
		      };
		      return action[0];
	   }
	   
}



import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class OreBlob extends Rate
{
	private int animation_rate;
	private Node[][] node_grid;
	private OpenSetList openset;
   public OreBlob(String name, Point position, int rate, int animation_rate, List<PImage> imgs)
   {
	   super(name, position, imgs, rate);
	   this.animation_rate = animation_rate;
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
   protected int get_animation_rate()
   {
	   return this.animation_rate;
   }
   
   protected boolean blob_to_vein(WorldModel world, Vein vein)
   {
	   Point entity_pt = this.get_position();
	   if(vein == null)
	   {
		   return false;
	   }
	   Point vein_pt = vein.get_position();
	   if (Actions.adjacent(entity_pt, vein_pt))
	   {
		   Actions.remove_entity(vein, world);
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
		   Point new_pt = Actions.blob_next_position(world, entity_pt, vein_pt, node_grid);
		   Entity old_entity = world.get_tile_occupant(new_pt);
		   if (old_entity instanceof Ore)
           {
			 Actions.remove_entity(old_entity, world);
		   }
		   world.move_entity(this, new_pt);
		   return false; 
	   }
   }
	protected Quake create_quake(WorldModel world, Point pt, long ticks, 
			 HashMap<String, List<PImage>> i_store)
	{
		Quake quake = new Quake("quake", pt, Actions.QUAKE_ANIMATION_RATE, Main.get_images(i_store, "quake"));
		quake.schedule_quake(world, ticks);
		return quake;
	}
	protected LongConsumer create_ore_blob_action(WorldModel world, HashMap <String, List<PImage>> i_store)
	{
		LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point entity_pt = this.get_position();
	    	  Vein vein = (Vein) world.find_nearest(entity_pt, Vein.class);
	    	  Point vein_pt = vein.get_position();
	    	  boolean found = this.blob_to_vein(world, vein);
	    	  
	    	  long next_time = current_ticks + this.get_rate();
	    	  
	    	  if(found)
	    	  {
    		    Quake quake = this.create_quake(world, vein_pt, current_ticks, i_store);
    		    world.add_entity(quake);
    		    next_time = current_ticks + this.get_rate() * 2;
	    	  }
	    	  Actions.schedule_action(world, this, this.create_ore_blob_action(world, i_store), next_time);
	    	    
	      };
		return action[0];
	}
	
	protected void schedule_blob(WorldModel world, long ticks, HashMap<String, List<PImage>> i_store)
	{
		
		Actions.schedule_action(world, this, create_ore_blob_action(world, i_store),
								ticks + this.get_rate());
		Actions.schedule_animation(world, this, 0); //just added in 0. keep eye on this
	}
	
	
	
	
   
   
}

import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

import processing.core.*;
public class Fairy extends Rate
{
	private int animation_rate;
	private Node[][] node_grid;
	private OpenSetList openset;
   public Fairy(String name, Point position, int rate, int animation_rate, List<PImage> imgs)
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
   
   protected boolean fairy_to_oreblob(WorldModel world, OreBlob oreblob)
   {
	   Point entity_pt = this.get_position();
	   if(oreblob == null)
	   {
		   return false;
	   }
	   Point oreblob_pt = oreblob.get_position();
	   if (Actions.adjacent(entity_pt, oreblob_pt))
	   {
		   Actions.remove_entity(oreblob, world);
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
		   Point new_pt = Actions.blob_next_position(world, entity_pt, oreblob_pt, node_grid);
		   //Entity old_entity = world.get_tile_occupant(new_pt);
		   
		   world.move_entity(this, new_pt);
		   return false; 
	   }
   }
   
	protected Star create_star(WorldModel world, Point pt, long ticks, 
			 HashMap<String, List<PImage>> i_store)//get images from map
	{
		Star star = new Star("quake", pt, Actions.QUAKE_ANIMATION_RATE, Main.get_images(i_store, "quake"));
		star.schedule_star(world, ticks);
		return star;
	}
	protected LongConsumer create_ore_blob_action(WorldModel world, HashMap <String, List<PImage>> i_store)
	{
		LongConsumer[] action = { null };
	      action[0] = (long current_ticks) -> {
	    	  this.remove_pending_action(action[0]);
	    	  
	    	  Point entity_pt = this.get_position();
	    	  OreBlob oreblob = (OreBlob) world.find_nearest(entity_pt, OreBlob.class);
	    	  
	    	  Point oreblob_pt = null;
	    	  if(oreblob != null)
	    	  {
	    		  oreblob_pt = oreblob.get_position(); 
	    	  }
	    	  
	    	  boolean found = this.fairy_to_oreblob(world, oreblob);
	    	  
	    	  long next_time = current_ticks + this.get_rate();
	    	  
	    	  if(found)
	    	  {
    		    Star star = this.create_star(world, oreblob_pt, current_ticks, i_store);
    		    world.add_entity(star);
    		    next_time = current_ticks + this.get_rate() * 2;
	    	  }
	    	  Actions.schedule_action(world, this, this.create_ore_blob_action(world, i_store), next_time);
	    	   
	      };
		return action[0];
	}
	/*
	protected void schedule_blob(WorldModel world, long ticks, HashMap<String, List<PImage>> i_store)
	{
		
		Actions.schedule_action(world, this, create_ore_blob_action(world, i_store),
								ticks + this.get_rate());
		Actions.schedule_animation(world, this, 0); //just added in 0. keep eye on this
	}
	*/
	
	
	
	
   
   
}


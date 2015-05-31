import java.util.List;
import processing.core.*;
public class WorldEvent extends Entity {
	
	   public WorldEvent (String name, Point position, List<PImage> imgs)
	   {
		   super(name, position, imgs);
	   }
	   protected String entity_string()
	   {
		   String obstacle_entity;
		   obstacle_entity = "worldevent" +  " " + this.get_name() + " " + (this.get_position().x) + 
				   " " + this.get_position().y;
		   return obstacle_entity;
	   }
	}




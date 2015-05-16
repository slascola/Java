import java.util.List;
import java.util.function.*;
import processing.core.*;
public class Entity 
{
   private String name;
   private Point position;
   private List<PImage> imgs;
   private int current_img;
   public Entity(String name, Point position, List<PImage> imgs)
   {
	   this.name = name;
	   this.position = position;
	   this.imgs = imgs;
	   this.current_img = 0;
   }
   protected void set_position(Point point)
   {
	   this.position = point;
   }
   protected Point get_position()
   {
	   return this.position;
   }
   protected String get_name()
   {
	   return this.name;
   }
   
   protected List<PImage> get_images()
   {
	   return this.imgs;
   }
   protected PImage get_image()
   {
	   return this.imgs.get(this.current_img);
   }
   protected void next_image()
   {
	   this.current_img = (this.current_img + 1) % this.imgs.size(); 
   }
  
}

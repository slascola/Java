import processing.core.*;
import java.util.List;
public class Background 
{
   private String name;
   private List<PImage> imgs;
   private int current_img;
   public Background(String name,  List<PImage>imgs)
   {
	   this.name = name;
	   this.imgs = imgs;
	   this.current_img = 0;
	   
	   
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

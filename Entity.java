
public class Entity 
{
   private String name;
   private Point position;
   private int[] imgs;
   private int current_img;
   public Entity(String name, Point position, int[] imgs)
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
   
   protected int[] get_images()
   {
	   return this.imgs;
   }
   protected int get_image()
   {
	   return this.imgs[this.current_img];
   }
   protected void next_image()
   {
	   this.current_img = (this.current_img + 1) % this.imgs.length; 
   }
}

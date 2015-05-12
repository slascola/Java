
public class Background 
{
   private String name;
   private int[] imgs;
   private int current_img;
   public Background(String name, int []imgs)
   {
	   this.name = name;
	   this.imgs = imgs;
	   this.current_img = 0;
	   
	   
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

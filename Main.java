import processing.core.*;
public class Main extends PApplet {
	//put image_store into main??
	//load images and files is that scanner?
	final boolean RUN_AFTER_LOAD = true;
	final String IMAGE_LIST_FILE_NAME = "imagelist";
	final String WORLD_FILE = "gaia.sav";
	
	final int WORLD_WIDTH_SCALE = 2;
	final int WORLD_HEIGHT_SCALE = 2;
	final int SCREEN_WIDTH = 640;
	final int SCREEN_HEIGHT= 480;
	final int TILE_WIDTH = 32;
	final int TILE_HEIGHT = 32;
	
	public Background create_default_background(PImage img)
	{
		Background b = new Background(image_store.DEFAULT_IMAGE_NAME, img);
		return b;
	}
	//load world?
	public void setup()
	{
		//random.seed()
		//pygame.init()
		PApplet screen = SCREEN_WIDTH, SCREEN_HEIGHT;
		//i_store
		int num_cols = SCREEN_WIDTH / TILE_WIDTH * WORLD_WIDTH_SCALE;
		int num_rows = SCREEN_HEIGHT / TILE_HEIGHT * WORLD_HEIGHT_SCALE;
		
		Background default_background = create_default_background(
				image_store.get_images(i_store, image_store.DEFAULT_IMAGE_NAME));
		
		WorldModel world = new WorldModel(num_rows, num_cols, default_background);
		WorldView view = new WorldView(SCREEN_WIDTH/TILE_WIDTH, SCREEN_HEIGHT/TILE_HEIGHT,
				screen, world, TILE_WIDTH, TILE_HEIGHT, null);
	}
	public static void main(String[] args)
	   {
	      PApplet.main("Main");
	   }
}

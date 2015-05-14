import processing.core.*;
import java.util.List;
import java.util.ArrayList;
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
	private WorldView view;
	private List<PImage>img;
	private List<PImage>e;
	
	public Background create_default_background(List<PImage> img)
	{
		Background b = new Background("Pietro", img);
		return b;
	}
	//load world?
	public void setup()
	{
		//store as map 
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		PApplet screen = this;
		//i_store
		int num_cols = SCREEN_WIDTH / TILE_WIDTH * WORLD_WIDTH_SCALE;
		int num_rows = SCREEN_HEIGHT / TILE_HEIGHT * WORLD_HEIGHT_SCALE;
		
		img = new ArrayList<PImage>();
		img.add(loadImage("v.png"));
		System.out.println(img.get(0));
		
		e = new ArrayList<PImage>();
		e.add(loadImage("YAS.png"));
		
		Background default_background = create_default_background(
				img);
		
		WorldModel world = new WorldModel(num_rows, num_cols, default_background);
	    view = new WorldView(SCREEN_WIDTH/TILE_WIDTH, SCREEN_HEIGHT/TILE_HEIGHT,
				screen, world, TILE_WIDTH, TILE_HEIGHT);
	    //world.add entity
	}
	public void draw()
	{
	   view.draw_viewport();	
	   
	}
	public static void main(String[] args)
	   {
	      PApplet.main("Main");
	   }
}

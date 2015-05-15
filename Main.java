import processing.core.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Main extends PApplet {
	//put image_store into main??
	//load images and files is that scanner?
	final boolean RUN_AFTER_LOAD = true;
	final String IMAGE_LIST_FILE_NAME = "imagelist";
	final static String WORLD_FILE = "gaia.sav";
	
	final int WORLD_WIDTH_SCALE = 2;
	final int WORLD_HEIGHT_SCALE = 2;
	final int SCREEN_WIDTH = 640;
	final int SCREEN_HEIGHT= 480;
	final int TILE_WIDTH = 32;
	final int TILE_HEIGHT = 32;
	private WorldView view;
	private List<PImage>img;
	private List<PImage>e;
	private static final int FILE_IDX = 0;
	private static final int MIN_ARGS = 1;
	
	final int PROPERTY_KEY = 0;

    final static String BGND_KEY = "background";
	final int BGND_NUM_PROPERTIES = 4;
	final int BGND_NAME = 1;
	final int BGND_COL = 2;
	final int BGND_ROW = 3;

	final String MINER_KEY = "miner";
	final int MINER_NUM_PROPERTIES = 7;
	final int MINER_NAME = 1;
	final int MINER_LIMIT = 4;
	final int MINER_COL = 2;
	final int MINER_ROW = 3;
	final int MINER_RATE = 5;
	final int MINER_ANIMATION_RATE = 6;

	final String OBSTACLE_KEY = "obstacle";
	final int OBSTACLE_NUM_PROPERTIES = 4;
	final int OBSTACLE_NAME = 1;
	final int OBSTACLE_COL = 2;
	final int OBSTACLE_ROW = 3;

	final String ORE_KEY = "ore";
	final int ORE_NUM_PROPERTIES = 5;
	final int ORE_NAME = 1;
	final int ORE_COL = 2;
	final int ORE_ROW = 3;
	final int ORE_RATE = 4;

	final String SMITH_KEY = "blacksmith";
	final int SMITH_NUM_PROPERTIES = 7;
	final int SMITH_NAME = 1;
	final int SMITH_COL = 2;
	final int SMITH_ROW = 3;
	final int SMITH_LIMIT = 4;
	final int SMITH_RATE = 5;
	final int SMITH_REACH = 6;

	final String VEIN_KEY = "vein";
	final int VEIN_NUM_PROPERTIES = 6;
	final int VEIN_NAME = 1;
	final int VEIN_RATE = 4;
	final int VEIN_COL = 2;
	final int VEIN_ROW = 3;
	final int VEIN_REACH = 5;
	
	public Background create_default_background(List<PImage> img)
	{
		Background b = new Background("Pietro", img);
		return b;
	}
	
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
		
		
		e = new ArrayList<PImage>();
		e.add(loadImage("YAS.png"));
		
		Background default_background = create_default_background(
				img);
		
		WorldModel world = new WorldModel(num_rows, num_cols, default_background);
	    view = new WorldView(SCREEN_WIDTH/TILE_WIDTH, SCREEN_HEIGHT/TILE_HEIGHT,
				screen, world, TILE_WIDTH, TILE_HEIGHT);
	    Point newp = new Point(1,1);
	    Entity newe = new Entity("Dean", newp, e);
	    world.add_entity(newe);
	    
	}
	private static boolean verifyArguments(String [] args)
	{
		return args.length >= MIN_ARGS;
	}
	private static void useScanner(Scanner in)
	{
		while(in.hasNextLine())
		{
			String [] properties = in.nextLine().split("\\");
			if(properties != null)
			{
				if(properties[PROPERTY_KEY] == BGND_KEY)
				{
					add_background(world, properties, WORLD_FILE);
				}
				else
				{
					add_entity(world, properties, images);
				}
			}
			
		}
		
	}
	public void add_background(WorldModel world, String[] properties, List<PImage>i_store)
	{
		if(properties.length >= BGND_NUM_PROPERTIES)
		{
			Point pt = new Point(BGND_COL, BGND_ROW);
			String name = properties[BGND_NAME];
			Background b = new Background(name, i_store);
			world.set_background(pt, b);
		}
	}
	public void add_entity(WorldModel world, String[] properties, List<PImage>i_store,  boolean run)
	{
		Entity new_entity = create_from_properties(properties, i_store);
		if(new_entity != null)
		{
			world.add_entity(new_entity);
			if(run)
			{
				schedule_entity(world, new_entity, i_store);
			}
			
		}	
				
	}
	public Entity create_from_properties(String[] properties, List<PImage> i_store)
	{
		String key = properties[PROPERTY_KEY];
		if(properties != null)
		{
			if(key == MINER_KEY)
			{
				return create_miner(properties, i_store);
			}
			else if(key == VEIN_KEY)
			{
				return create_vein(properties, i_store);
			}
			else if(key == ORE_KEY)
			{
				return create_ore(properties, i_store);
			}
			else if(key == SMITH_KEY)
			{
				return create_blacksmith(properties, i_store);
			}
			else if(key == OBSTACLE_KEY)
			{
				return create_obstacle(properties, i_store);
			}
		}
		return null;
	}
	public Miner create_miner(String[] properties, List<PImage> i_store)
	{
		if(properties.length == MINER_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[MINER_COL]), Integer.parseInt(properties[MINER_ROW]));
			Miner miner = new Miner(properties[MINER_NAME], p, Integer.parseInt(properties[MINER_RATE]), Integer.parseInt(properties[MINER_LIMIT]),
					  0,Integer.parseInt(properties[MINER_ANIMATION_RATE]), i_store);
			return miner;
		}
		else
		{
			return null;
		}
			
	}
	public Vein create_vein(String[] properties, List<PImage> i_store)
	{
		if(properties.length == VEIN_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[VEIN_COL]), Integer.parseInt(properties[VEIN_ROW]));
			Vein vein = new Vein(properties[VEIN_NAME], Integer.parseInt(properties[VEIN_RATE]), p,
					Integer.parseInt(properties[VEIN_REACH]), i_store);
			return vein;
		}
		else
		{
			return null;
		}
		
	}
	public Ore create_ore(String[] properties, List<PImage> i_store)
	{
		if(properties.length == ORE_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[ORE_COL]), Integer.parseInt(properties[ORE_ROW]));
			Ore ore = new Ore(properties[ORE_NAME], p, Integer.parseInt(properties[ORE_RATE]), i_store);
			return ore;
		}
		else
		{
			return null;
		}
	}
	public Blacksmith create_blacksmith(String[] properties, List<PImage>i_store)
	{
		if(properties.length == SMITH_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[SMITH_COL]), Integer.parseInt(properties[SMITH_ROW]));
			Blacksmith smith = new Blacksmith(properties[SMITH_NAME], p, i_store, Integer.parseInt(properties[SMITH_LIMIT]),
					Integer.parseInt(properties[SMITH_RATE]), Integer.parseInt(properties[SMITH_REACH]), 0);
			return smith;
		}
		else
		{
			return null;
		}
	}
	public Obstacle create_obstacle(String[] properties, List<PImage> i_store)
	{
		if(properties.length == OBSTACLE_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[OBSTACLE_COL]), Integer.parseInt(properties[OBSTACLE_ROW]));
			Obstacle obstacle = new Obstacle(properties[OBSTACLE_NAME],p, i_store);
			return obstacle;
		}
		else
		{
			return null;
		}
	}
	public void schedule_entity(WorldModel world, Entity entity,List<PImage> i_store)
	{
		if(entity instanceof MinerNotFull)
		{
			entity.schedule_miner(world, 0, i_store);
		}
		else if(entity instanceof Vein)
		{
			entity.schedule_vein(world, 0, i_store);
		}
		else if(entity instanceof Ore)
		{
			entity.schedule_ore(world, 0, i_store);
		}
		
	
	
	
	
	public void draw()
	{
	   view.draw_viewport();	
	   
	}
	public static void main(String[] args)
	   {
	      PApplet.main("Main");
	      try
	      {
	         if (verifyArguments(args))
	         {
	            Scanner in = new Scanner(new FileInputStream(args[FILE_IDX]));
	            useScanner(in);
	         }
	         else
	         {
	            System.err.println("missing filename");
	         }
	      }
	      catch (FileNotFoundException e)
	      {
	         System.err.println(e.getMessage());
	      }
	   }
	   }
}

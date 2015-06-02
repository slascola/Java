import processing.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.util.function.*;

public class Main extends PApplet {
	
	private int x_delta;
	private int y_delta;
	
	private PImage world_event_image;
	private List<PImage>fire_imgs;
	private int current_image;
	
	private static boolean mouse_pressed = false;
	
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
	private WorldModel world;
	private List<PImage>img;
	private List<PImage>e;
	
	private static HashMap <String, List<PImage>> map;
	
	private static final int MIN_ARGS = 1;
	
	final static String DEFAULT_IMAGE_NAME = "background_default";
	
	private boolean run;
	
	final int PROPERTY_KEY = 0;

    private String BGND_KEY = "background";
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
	//setting up time
	private long next_time;
	private static final int ANIMATION_TIME = 175;
    //setting up background
	private int num_cols;
	private int num_rows;
	private Background default_background;
	   private static final int COLOR_MASK = 0xffffff;
	   private int[][] overlay;
	   private PImage black;
	
	
	public static HashMap <String, List<PImage>> getMap()
	{
		return map;
	}
	public Background create_default_background(List<PImage> img)
	{
		Background b = new Background(DEFAULT_IMAGE_NAME, img);
		return b;
	}
	
	public void setup()
	{
		
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		PApplet screen = this;
		
		num_cols = SCREEN_WIDTH / TILE_WIDTH * WORLD_WIDTH_SCALE;
		num_rows = SCREEN_HEIGHT / TILE_HEIGHT * WORLD_HEIGHT_SCALE;

		
		
		  try {
				map = map_initializer(IMAGE_LIST_FILE_NAME);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		  black =loadImage("v.png");
		default_background = create_default_background(
				get_images(map, DEFAULT_IMAGE_NAME));
		
		world = new WorldModel(num_rows, num_cols, default_background);
		
	    view = new WorldView(SCREEN_WIDTH/TILE_WIDTH, SCREEN_HEIGHT/TILE_HEIGHT,
				screen, world, TILE_WIDTH, TILE_HEIGHT);
	   
	    fire_imgs = new ArrayList<PImage>();
	      fire_imgs.add(loadImage("fire1.gif"));
	      fire_imgs.add(loadImage("fire2.gif"));
	      fire_imgs.add(loadImage("fire3.gif"));
	      fire_imgs.add(loadImage("fire4.gif"));
	      fire_imgs.add(loadImage("fire5.gif"));
	      fire_imgs.add(loadImage("fire6.gif"));
	      fire_imgs.add(loadImage("fire7.gif"));
	      fire_imgs.add(loadImage("fire8.gif"));
	      fire_imgs.add(loadImage("fire9.gif"));
	      fire_imgs.add(loadImage("fire10.gif"));
	      
	      current_image = 0;
	      
	      	
	    
	    try
	      {
	         
	            Scanner in = new Scanner(new FileInputStream(WORLD_FILE));
	            useScanner(in, map);
	         
	         
	      }
	      catch (FileNotFoundException e)
	      {
	         System.err.println(e.getMessage());
	      }
	    try
	    {
	      
	          Scanner im = new Scanner(new FileInputStream(IMAGE_LIST_FILE_NAME));
	          useScannerImages(im);
	      
	    }
	    catch (FileNotFoundException e)
	    {
	       System.err.println(e.getMessage());
	    }
	    
	    
	    
	    
	}
	protected HashMap<String, List<PImage>> map_initializer(String filename) throws FileNotFoundException
	{
		 File file2 = new File(IMAGE_LIST_FILE_NAME);
		 Scanner new_scan = new Scanner(file2);
		 map = useScannerImages(new_scan);
		 return map;
		
	}
	
	protected void useScanner(Scanner in, HashMap<String, List<PImage>> map) throws FileNotFoundException
	{
		while(in.hasNextLine())
		{
			
			String [] properties;
			properties = in.nextLine().split("\\s");
			
			if(properties != null)
			{
				File file = new File(IMAGE_LIST_FILE_NAME);
				Scanner new_scan = new Scanner(file);
				if(properties[PROPERTY_KEY].equals(BGND_KEY))
				{
				    
					add_background(world, properties, map);
					
				}
				else
				{
					
					add_entity(world, properties, useScannerImages(new_scan), RUN_AFTER_LOAD);//when run is true it adds ore
				}
			}
			
		}
		
	}
	

	protected HashMap<String, List<PImage>> useScannerImages(Scanner in)
	{
		 map = new HashMap<String, List<PImage>>();
		while(in.hasNextLine())
		{
			
			String [] line = in.nextLine().split("\\s");
			process_image_line(map, line);
			
			
		}
		if(!(map.containsKey(DEFAULT_IMAGE_NAME)))
		{
			PImage default_image = create_default_image(TILE_WIDTH, TILE_HEIGHT);
			List<PImage> this_default_image = new ArrayList<PImage>();
			this_default_image.add(default_image);
			map.put(DEFAULT_IMAGE_NAME, this_default_image);
		}
		
		return map;
	}
	protected PImage create_default_image(int tile_width, int tile_height)
	{
		PImage surf = createImage(tile_width, tile_height, RGB);
		surf.loadPixels();
		for (int i = 0; i < surf.pixels.length; i++) {
		  surf.pixels[i] = color(128, 128, 128, 0); 
		}
		surf.updatePixels();
		return surf;
		
	}
	protected void process_image_line(HashMap<String, List<PImage>> map, String[] line)
	{
		String key;
		String image_file_name;
		PImage image;
		
		
		
		key = line[0];
		image_file_name = line[1];
		image = loadImage(image_file_name);
		
		
		
		if (image != null)
		{
			
			
			List<PImage>images = get_images_internal(map, key);
			images.add(image);
			map.put(key, images);
			
			
			
			
			
			if(line.length == 6)
			{
			   int r = Integer.parseInt(line[2]);
			   int g = Integer.parseInt(line[3]);
			   int b = Integer.parseInt(line[4]);
			   int a = Integer.parseInt(line[5]);
			   setAlpha(image, color(r, g, b), a);
			   //look at the color coding thing on webste for this part
			}
		}
		
	}
	   // Called with color for which alpha should be set and alpha value.
	   //PImage img = setAlpha(loadImage("wyvern1.bmp"), color(255, 255, 255), 0));
	   private static PImage setAlpha(PImage img, int maskColor, int alpha)
	   {
	      int alphaValue = alpha << 24;
	      int nonAlpha = maskColor & COLOR_MASK;
	      img.format = PApplet.ARGB;
	      img.loadPixels();
	      for (int i = 0; i < img.pixels.length; i++)
	      {
	         if ((img.pixels[i] & COLOR_MASK) == nonAlpha)
	         {
	            img.pixels[i] = alphaValue | nonAlpha;
	         }
	      }
	      img.updatePixels();
	      return img;
	   }
	private List<PImage> get_images_internal(HashMap<String, List<PImage>> map, String key)
	{
		List<PImage> this_object_list = new ArrayList<PImage>();
		
		if(map.containsKey(key))
		{
			List<PImage> object_image = map.get(key);
			
			return object_image;
			
		}
		else
		{
			return this_object_list;
		}
		
	}
	public static List<PImage> get_images(HashMap<String, List<PImage>> map, String key)
	{
		
		if(map.containsKey(key))
		{
			
           List<PImage> object_image = map.get(key);	
			return object_image;
			
		}
		else {
			 List<PImage> object_image = map.get(DEFAULT_IMAGE_NAME);
			 return object_image;
			 
		}
	}
	
	public void add_background(WorldModel world, String[] properties, HashMap <String, List<PImage>> map)
	{
		if(properties.length >= BGND_NUM_PROPERTIES)
		{
			
			Point pt = new Point(Integer.parseInt(properties[BGND_COL]), Integer.parseInt(properties[BGND_ROW]));
			String name = properties[BGND_NAME];
			Background b = new Background(name, get_images(map, name));
			world.set_background(pt, b);
		}
	}
	public void add_entity(WorldModel world, String[] properties, HashMap <String, List<PImage>> map,  boolean run)
	{
		Entity new_entity = create_from_properties(properties, map);
		
		if(new_entity != null)
		{
			
			world.add_entity(new_entity);
			
			
			if(run)
			{
				schedule_entity(world, new_entity, map);
			}
			
		
			
			//if mouse pressed create new entity fairy
			
		}	
				
	}
	public Entity create_from_properties(String[] properties, HashMap <String, List<PImage>> map)
	{
		String key = properties[PROPERTY_KEY];
	
		
		if(properties != null)
		{
			if(key.equals(MINER_KEY))
			{
				
				return create_miner(properties, map);
			}
			else if(key.equals(VEIN_KEY))
			{
				return create_vein(properties, map);
			}
			else if(key.equals(ORE_KEY))
			{
				//System.out.println(key);
				return create_ore(properties, map);
			}
			else if(key.equals(SMITH_KEY))
			{
				
				return create_blacksmith(properties, map);
			}
			else if(key.equals(OBSTACLE_KEY))
			{
				return create_obstacle(properties, map);
			}
		}
	    
		return null;
	}
	public MinerNotFull create_miner(String[] properties, HashMap <String, List<PImage>> map)
	{
		
		if(properties.length == MINER_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[MINER_COL]), Integer.parseInt(properties[MINER_ROW]));
			MinerNotFull miner = new MinerNotFull(properties[MINER_NAME], Integer.parseInt(properties[MINER_LIMIT]), p, Integer.parseInt(properties[MINER_RATE]), get_images(map, properties[PROPERTY_KEY]) ,
					  0,Integer.parseInt(properties[MINER_ANIMATION_RATE]));
			return miner;
		}
		else
		{
			return null;
		}
			
	}
	public Vein create_vein(String[] properties, HashMap <String, List<PImage>> map)
	{
		if(properties.length == VEIN_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[VEIN_COL]), Integer.parseInt(properties[VEIN_ROW]));
			Vein vein = new Vein(properties[VEIN_NAME], Integer.parseInt(properties[VEIN_RATE]), p,
					Integer.parseInt(properties[VEIN_REACH]), get_images(map, properties[PROPERTY_KEY]));
			return vein;
		}
		else
		{
			return null;
		}
		
	}
	public Ore create_ore(String[] properties, HashMap <String, List<PImage>> map)
	{
		if(properties.length == ORE_NUM_PROPERTIES)
		{
			
			Point p = new Point(Integer.parseInt(properties[ORE_COL]), Integer.parseInt(properties[ORE_ROW]));
			Ore ore = new Ore(properties[ORE_NAME], p, Integer.parseInt(properties[ORE_RATE]), get_images(map, properties[PROPERTY_KEY]));
			
			return ore;
			
		}
		else
		{
			
			return null;
		}
	}
	public Blacksmith create_blacksmith(String[] properties, HashMap <String, List<PImage>> map)
	{
		if(properties.length == SMITH_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[SMITH_COL]), Integer.parseInt(properties[SMITH_ROW]));
			Blacksmith smith = new Blacksmith(properties[SMITH_NAME], p, get_images(map, properties[PROPERTY_KEY]), Integer.parseInt(properties[SMITH_LIMIT]),
					Integer.parseInt(properties[SMITH_RATE]), Integer.parseInt(properties[SMITH_REACH]), 0);
			//System.out.println(smith);
			return smith;
		}
		else
		{
			return null;
		}
	}
	public Obstacle create_obstacle(String[] properties, HashMap <String, List<PImage>> map)
	{
		if(properties.length == OBSTACLE_NUM_PROPERTIES)
		{
			Point p = new Point(Integer.parseInt(properties[OBSTACLE_COL]), Integer.parseInt(properties[OBSTACLE_ROW]));
			Obstacle obstacle = new Obstacle(properties[OBSTACLE_NAME],p, get_images(map, properties[PROPERTY_KEY]));
			return obstacle;
		}
		else
		{
			return null;
		}
	}
	public void schedule_entity(WorldModel world, Entity entity, HashMap <String, List<PImage>> map)
	{
		long newtick = System.currentTimeMillis();
		if(entity instanceof MinerNotFull)
		{
			
			((Miner) entity).schedule_miner(world, newtick, map);
		}
		else if(entity instanceof Vein)
		{
			//System.out.println("a new vein");
			((Vein) entity).schedule_vein(world, newtick, map);
		}
		else if(entity instanceof Ore)
		{
			//System.out.println("it got here");
			((Ore) entity).schedule_ore(world, newtick, map);
		}
	}
	public void draw_world_event()
	{
		for(int y = 2; y<7; y++)
		{
			
				Point p = new Point(2, y);
				WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
				world.set_occupancy(p, newworldevent);	
		}
		for(int x = 1; x < 4; x++)
		{
			Point p = new Point(x, 7);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int y = 3; y < 7; y++)
		{
			Point p = new Point(5, y);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int y = 3; y < 7; y++)
		{
			Point p = new Point(8, y);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int x = 6; x < 8; x++)
		{
			Point p = new Point(x, 2);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int x = 6; x < 8; x++)
		{
			Point p = new Point(x, 7);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int x = 10; x < 14; x++)
		{
			Point p = new Point(x, 7);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int x = 11; x < 13; x++)
		{
			Point p = new Point(x, 5);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int x = 11; x < 13; x++)
		{
			Point p = new Point(x, 2);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int y= 3; y < 5; y++)
		{
			Point p = new Point(13, y);
			WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
			world.set_occupancy(p, newworldevent);	
		}
		for(int y = 1; y< 7; y++)
		{
			for(int x = 1; x < 13; x++)
			{
				Point p = new Point(1, 3);
				WorldEvent newworldevent = new WorldEvent("world event", p, fire_imgs);
				world.set_occupancy(p, newworldevent);
				
				Point pt = new Point(10, 3);
				WorldEvent newworldevent2 = new WorldEvent("world event", pt, fire_imgs);
				world.set_occupancy(pt, newworldevent2);
				
				Point p3 = new Point(10, 6);
				WorldEvent newworldevent3 = new WorldEvent("world event", p3, fire_imgs);
				world.set_occupancy(p3, newworldevent3);
			}
		}
	}
	 private void next_image()
	   {
	      current_image = (current_image + 1) % fire_imgs.size();
	      
	      
	   }
	
		
	
	
	
	
	public void draw()
	{
		long time = System.currentTimeMillis();
	      if (time >= next_time)
	      {
	    	  next_image();
	         world.update_on_time(time);
	         next_time = time + ANIMATION_TIME;
	      }
		
	   view.draw_viewport();
	   for(int y = 0; y < world.getNumRows(); y++)
	   {
		   for(int x = 0; x <world.getNumCols(); x++)
		   {
			   Point newp = new Point(x,y);
			   Point v_pt = view.world_to_viewport(view.get_viewport(), newp);
			   if(world.get_occupancy(newp) instanceof WorldEvent)
			   {
				   image(fire_imgs.get(current_image), v_pt.x*32, v_pt.y*32);
			   }
		   }
	   }
	   /*
	    for(Entity e : world.get_entities())
	    {
	    	Point entity_pt = e.get_position();
	    	Point mouse_loc = new Point(mouseX /32, mouseY /32);
	    	Point mouse_pt = WorldView.viewport_to_world(view.get_viewport(), mouse_loc);
	    	 
	    	
	    	overlay = new int[num_rows][num_cols];
	    	if((mouse_loc.x == entity_pt.x && mouse_loc.y == entity_pt.y) && (e instanceof MinerFull))
	    	{
	    		for(int y = 0; y < num_rows; y++)
	    		{
	    			for( int x = 0; x < num_cols; x++)
	    			{
	    				if(((MinerFull) e).getOpenSet().contains(((MinerFull) e).getGrid()[y][x]))
	    				{
	    					image(black, x* 32, y*32);
	    				}
	    			}
	    		}
	    	}
	    }
	    */
	   //use getters for the node grid in oreblob, mnf, and mf
	   //if point at that node is instance of ob, mnf, mf and point is equal to getLocation() of mouse pointer 
	   //and the node is in open set .... draw black square
	   //if that node is in path ...draw red square
	   
	   
	   
	}
	public static boolean getMousePressed()
	{
		return mouse_pressed;
	}
	//variable static and static method starts out false
	public void mouseClicked()
	{
		draw_world_event();
		mouse_pressed = true;
		
		long ticks = System.currentTimeMillis();
		Point p = new Point(mouseX/32, mouseY/32);
		Fairy new_fairy = new Fairy("fairy", p, 500, 100, get_images(map, "fairy"));
		world.add_entity(new_fairy);
		new_fairy.schedule_fairy(world, ticks, map);
	}
	
	 public void left()
	   {
		  x_delta =0;
		  y_delta = 0;
	      int newx_delta = x_delta - 1;
	     // System.out.println(newx_delta);
	      view.update_view(newx_delta, y_delta);
	      
	   }
	   public void right()
	   {
		  x_delta = 0;
		  y_delta = 0;
		  int newx_delta = x_delta + 1;
		  //System.out.println(newx_delta);
		  view.update_view(newx_delta, y_delta);
		         
	   }
	   public void up()
	   {
		 x_delta =0;
		 y_delta = 0;
	     int newy_delta = y_delta -1;
	     view.update_view(x_delta, newy_delta);
	   }
	   public void down()
	   {
		  x_delta =0;
		  y_delta = 0;
	      int newy_delta = abs(y_delta +1);
	      //System.out.println(newy_delta);
	      view.update_view(x_delta, newy_delta);
	   }
	 public void keyPressed(KeyEvent event)
	   {
	      switch(event.getKeyCode())
	      {
	         case KeyEvent.VK_LEFT:
	            left();
	            
	            break; 
	         case KeyEvent.VK_UP:
	            up();
	            
	            break;
	         case KeyEvent.VK_DOWN:
	           down();
	           
	            break;
	         case KeyEvent.VK_RIGHT:
	            right();
	            break;
	         
	       }
	   }
	public static void main(String[] args)
	   {
	      PApplet.main("Main");
	      
	   }
	   
}


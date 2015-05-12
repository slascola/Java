import processing.core.*;
import java.util.List;
import java.util.ArrayList;

public class WorldView extends PApplet {
	
    private Rectangle viewport;
    private Point mouse_pt;
    private WorldModel world;
    private int tile_width;
    private int tile_height;
    private int num_rows;
    private int num_cols;
    private PApplet screen;
    private PImage mouse_img;
    private int view_cols;
    private int view_rows;
    
	public WorldView(int view_cols, int view_rows, PApplet screen, WorldModel world, 
			int tile_width, int tile_height, PImage mouse_image)
			{
				this.viewport = new Rectangle(0, 0, view_cols, view_rows);
				this.screen = screen;
				this.mouse_pt = new Point(0, 0);
				this.world = world;
				this.tile_width = tile_width;
				this.tile_height = tile_height;
				this.tile_height = tile_height;
				this.num_rows = world.getNumRows();
				this.num_cols = world.getNumCols();
				this.mouse_img = mouse_img;
					
			}
	
	public void draw_background()
	{
		for(int y = 0; y < this.viewport.getViewCols(); y++)
		{
			for(int x = 0; x< this.viewport.getViewRows(); x++)
			{
				Point p = new Point(x, y);
				Point w_pt = viewport_to_world(this.viewport, p);
				PImage img = this.world.get_background_image(w_pt);
				this.screen.image(img, x*this.tile_width, y*this.tile_height);
				
				
			}
		}
	}
	public void draw_entities()
	{
		for(Entity entity : this.world.entities)//make getter
		{
			if(this.viewport.collidepoint(entity.get_position().x, entity.get_position().y))
			{
				Point v_pt = world_to_viewport(this.viewport, entity.get_position());
				this.screen.image(entity.get_image(), v_pt.x*this.tile_width, v_pt.y*this.tile_height);
			}
		}
	}
	public void draw_viewport()
	{
		this.draw_background();
		this.draw_entities();
	}
	public void update_view(Point view_delta, PImage mouse_img)
	{
		view_delta = new Point(0,0);//fix
		mouse_img = null;
		this.viewport = create_shifted_viewport(this.viewport, view_delta, this.getNumRows(), this.getNumCols());
		this.mouse_img = mouse_img;
		this.draw_viewport();
		//pygame.display.update()
		this.mouse_move(this.mouse_pt);
		
	}
	public void update_view_tiles(List<Point> tiles)
	{
	   List<Rectangle> rects = new ArrayList<Rectangle>();
	   for(Point tile : tiles)
	   {
		   if(this.viewport.collidepoint(tile.x, tile.y))
		   {
			   Point v_pt = world_to_viewport(this.viewport, tile);
			   PImage img = this.get_tile_image(v_pt);
			   rects.add(this.update_tile(v_pt, img));
			   if(this.mouse_pt.x == v_pt.x && this.mouse_pt.y == v_pt.y)
			   {
				   rects.add(this.update_mouse_cursor());
			   }
		   }
	   }
	   
	}
	public Rectangle update_tile(Point view_tile_pt, PImage surface)
	{
		int abs_x = view_tile_pt.x * this.tile_width;
		int abs_y = view_tile_pt.y * this.tile_height;
		this.screen.image(surface, abs_x, abs_y);
		Rectangle final_rectangle = new Rectangle(abs_x, abs_y, this.tile_width, this.tile_height);
		return final_rectangle;
	}
	public PImage get_tile_image(Point view_tile_pt)
	{
		Point pt = viewport_to_world(this.viewport, view_tile_pt);
		PImage bgnd = this.world.get_background_image(pt);
		Entity occupant = this.world.get_tile_occupant(pt);
		if(occupant != null)
		{
			PImage img = Surface(this.tile_width, this.tile_height); //what is Surface?
			image(bgnd, 0, 0);//original img.blit(bgnd, 0, 0)
			image(occupant.get_image(), 0, 0);
			return img;	
		}
		else
		{
			return bgnd;
		}
	}
	public Rectangle update_mouse_cursor()
	{
		return (this.update_tile(this.mouse_pt, 
				this.create_mouse_surface(
						WorldModel.is_occupied(this.world, 
								viewport_to_world(this.viewport, this.mouse_pt)))));
	}
	public void mouse_move(Point new_mouse_pt)
	{
		List<Rectangle> rects = new ArrayList<Rectangle>();
		
		rects.add(this.update_tile(this.mouse_pt,
				this.get_tile_image(this.mouse_pt)));
		
		if(this.viewport.collidepoint(new_mouse_pt.x + this.viewport.left,
				new_mouse_pt.y + this.viewport.top))
		{
			this.mouse_pt = new_mouse_pt;
		}
		rects.add(this.update_mouse_cursor());
		
		//pygame.display.update(rects)???
		
	}
	public static Point viewport_to_world(Rectangle viewport, Point pt)
	{
		Point p = new Point(pt.x + viewport.left, pt.y - viewport.top);
		return p;
	}
	public static Point world_to_viewport(Rectangle viewport, Point pt)
	{
		Point p = new Point(pt.x - viewport.left, pt.y - viewport.top);
		return p;
	}
	//help with clamp
	protected static int clamp(int v, int low, int high)
	{
		return min(high, max(v, low));
	}
	//help with create_shifted_viewport
	//what is delta?
	protected static Rectangle create_shifted_viewport(Rectangle viewport, int deltax, int deltay, int num_rows, int num_cols)
	{
		int new_x = clamp(viewport.left + deltax, 0, num_cols - viewport.getViewCols());
		int new_y = clamp(viewport.top + deltay, 0, num_rows - viewport.getViewRows());
		Rectangle r = new Rectangle(new_x, new_y, viewport.getViewCols(), viewport.getViewRows());
		return r;
	}
	

}

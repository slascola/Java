import java.util.List;
import java.util.ArrayList;

public class WorldModel {

	private int num_rows;
	private int num_cols;
	private List<Entity> entities;
	private Grid background;
	private int[][] occupancy;
	private OrderedList action_queue; 
	
	public WorldModel(int num_rows, int num_cols, Grid background)
	{
		this.background = Grid(num_cols, num_rows, background);
		this.num_rows = num_rows;
		this.num_cols = num_cols;
		this.occupancy = occupancy;
		this.entities = //array list?
	    this.action_queue = OrderedList();
		
	}
	protected boolean within_bounds(Point point)
	{
		int x;
		int y;
		Point pt = new Point(x, y);
		return pt.x >= 0 && pt.x < this.num_cols && 
				pt.y >= 0 && pt.y < this.num_rows;
	}
	protected Entity find_nearest(Point point, Class type)
	{
		List<Entity> oftype = new ArrayList();
		for (Entity e : this.entities)
		{
			if (type.isInstance(e))
			{
				oftype.add(e);
			}
			int dist = distance_sq(point, e.get_position());
			oftype.add(dist);//e outside for loop?
		}
		return nearest_entity(oftype);
			
	}
	protected void add_entity(Entity entity)
	{
		
	}
	protected Point[] move_entity(Entity entity, Point pt)
	{
		Point tiles[] = new Point[0];//making new array
		if (this.within_bounds(pt))
		{
			Point old_pt = entity.get_position();
			this.occupancy[old_pt.y][old_pt.x] = null;//sets grid position to none
			tiles.add(old_pt);
			this.occupancy[pt.y][pt.x] = entity;
			tiles.add(pt);
			entity.set_position(pt);
			
		}
		return tiles; 
	}
	protected void remove_entity(Entity entity)
	{
		this.remove_entity_at(entity.get_position());
	}
	protected void remove_entity_at(Point pt)
	{
	   if(this.within_bounds(pt) && this.occupancy[pt.y][pt.x] != null)
	   {
		   Entity entity = this.occupancy[pt.y][pt.x];
		   Point point1 = new Point(-1, -1);
		   entity.set_position(point1);
		   this.entities.remove(entity);
		   this.occupancy[pt.y][pt.x] = null;
	   }
	}
	protected int[][] get_background(Point pt)//returning grid?
	{
		if(this.within_bounds(pt))
		{
			return this.get_cell(pt);
		}
	}
	protected void set_background(Point pt, Grid bgnd)
	{
		if (this.within_bounds(pt))
		{
			this.background.set_sell(pt, bgnd);
		}
	}
	protected int[][] get_title_occupant(Point pt)
	{
		if (this.within_bounds(pt))
		{
			return this.occupancy[pt.y][pt.x];
		}
	}
	protected List<Entity> get_entities()
	{
		return this.entities;
	}
	
	protected static boolean is_occupied(WorldModel world, Point pt)
	{
		return (world.within_bounds(pt) && world.occupancy[pt.y][pt.x] != null);
	}
	public static Entity nearest_entity(int[] entity_dists)
	{
		if (entity_dists.length > 0)
		{
			int[] pair = entity_dists[0];
			for (other : entity_dists)
			{
				if(other[1] < pair[1])
				{
					pair = other;
				}
				else
				{
					Entity nearest = null;
				}
			}
			
		}
		return nearest;
	}
	public static int distance_sq(Point p1, Point p2)
	{
		int pxfinal = (p1.x - p2.x) * (p1.x - p2.x);
		int pyfinal = (p1.y - p2.y) * (p1.y - p2.y);
		return pxfinal + pyfinal;
	}
	
	
	
}

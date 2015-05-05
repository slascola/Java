import java.util.List;
import java.util.ArrayList;

public class WorldModel {

	private int num_rows;
	private int num_cols;
	private List<Entity> entities;
	private Background[][] background;
	private Entity[][] occupancy;
	
	
	public WorldModel(int num_rows, int num_cols)
	{
		this.background = new Background[num_rows][num_cols];
		this.num_rows = num_rows;
		this.num_cols = num_cols;
		this.occupancy = new Entity[num_rows][num_cols];
		this.entities = new ArrayList<Entity>();
		
	}
	protected boolean within_bounds(Point point)
	{
		
		return point.x >= 0 && point.x < this.num_cols && 
				point.y >= 0 && point.y < this.num_rows;
	}
	protected Entity find_nearest(Point point, Class type)
	{
		List<Entity> oftype = new ArrayList<Entity>();
		for (Entity e : this.entities)
		{
			if (type.isInstance(e))
			{
				oftype.add(e);
			}
			
		}
		return nearest_entity(oftype, point);
			
	}
	protected Entity get_occupancy(Point pt)
	{
		if(this.within_bounds(pt))
		{
			return this.occupancy[pt.y][pt.x];
		}
		return null;
	}
	protected void set_occupancy(Point pt, Entity entity)
	{
		if (this.within_bounds(pt))
		{
			this.occupancy[pt.y][pt.x] = entity;
		}
	}
	protected void add_entity(Entity entity)
	{
		Point pt = entity.get_position();
		if (this.within_bounds(pt))
		{
			this.occupancy[pt.y][pt.x] = entity;
			this.entities.add(entity);
		}
		
	}
	protected void move_entity(Entity entity, Point pt)
	{
		if (this.within_bounds(pt))
		{
			Point old_pt = entity.get_position();
			this.occupancy[old_pt.y][old_pt.x] = null;
			this.occupancy[pt.y][pt.x] = entity;
			entity.set_position(pt);
			
		} 
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
	protected Background get_background(Point pt)
	{
		if(this.within_bounds(pt))
		{
			return this.background[pt.y][pt.x];
		}
		return null;
	}
	protected void set_background(Point pt, Background bgnd)
	{
		if (this.within_bounds(pt))
		{
			this.background[pt.y][pt.x] = bgnd;
		}
	}
	protected Entity get_tile_occupant(Point pt)
	{
		if (this.within_bounds(pt))
		{
			return this.occupancy[pt.y][pt.x];
		}
		return null;
	}
	protected List<Entity> get_entities()
	{
		return this.entities;
	}
	
	protected static boolean is_occupied(WorldModel world, Point pt)
	{
		return (world.within_bounds(pt) && world.occupancy[pt.y][pt.x] != null);
	}
	public static Entity nearest_entity(List<Entity> l1, Point point)
	{
		Entity nearest = null;
		if (l1.size() > 0)
		{
			nearest = l1.get(0);
			
			for (Entity other : l1)
			{
				int dist = distance_sq(point, nearest.get_position());
				int otherdist = distance_sq(point, other.get_position());
				if(otherdist < dist)
				{
					nearest = other;
					
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

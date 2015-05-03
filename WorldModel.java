import java.util.List;

public class WorldModel {

	private int num_rows;
	private int num_cols;
	private List<Entity> entities;
	private Grid background;
	private Grid occupancy;
	private OrderedList action_queue; 
	
	public WorldModel(num_rows, num_cols, background)
	{
		this.background = Grid(num_cols, num_rows, background);
		this.num_rows = num_rows;
		this.num_cols = num_cols;
		this.occupancy = Grid(num_cols, num_rows, background);
		this.entities = //array list?
	    this.action_queue = OrderedList();
		
	}
	
	
	
}

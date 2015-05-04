import java.util.List;
import java.util.ArrayList;
public class Grid {
	private int width;
	private int height;
	private List cells;
	private int occupancy_value;

	public Grid(int width, int height, Entity occupancy_value)
	{
		this.width = width;
		this.height = height;
		this.cells = new ArrayList();
		
		for(int row=0; row<this.height; row++)
		{
			this.cells.add([]);
			for(int col=0; col<this.width; col++)
			{
				this.cells[row].add(occupancy_value);
			}
		}
	}
	protected void set_cell(Point point, int value)
	{
		this.cells[point.y][point.x] = value;
	}
	protected int get_cell(Point point)
	{
		return this.cells[point.y][point.x];
	}
	
	

}

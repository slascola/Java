
public class Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
	public Rectangle(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	protected int getLeft()
	{
		return this.x;
	}
	protected int getTop()
	{
		return this.y;
	}
	protected int getViewCols()
	{
		return this.width;
	}
	protected int getViewRows()
	{
		return this.height;
	}
	protected boolean collidepoint(int x, int y)
	{
		return (x >= 0) && (x < this.width) && 
				(y >= 0) && (y < this.height);
	}

}

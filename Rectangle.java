
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
		return false;
	}

}

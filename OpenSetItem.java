
public class OpenSetItem {
	private int f_value;
	private Point pt;
	public OpenSetItem(Point pt, int f_value)
	{
		this.pt = pt;
		this.f_value = f_value;
	}
	public Point getPoint()
	{
		return this.pt;
	}
	public int getFValue()
	{
		return this.f_value;
	}

}


public class OpenSetItem {
	private int f_value;
	private Node node;
	public OpenSetItem(Node node, int f_value)
	{
		this.node = node;
		this.f_value = f_value;
	}
	public Node getNode()
	{
		return this.node;
	}
	public int getFValue()
	{
		return this.f_value;
	}
	

}

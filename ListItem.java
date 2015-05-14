import java.util.function.*;
public class ListItem {
	private LongConsumer item;
	private int ord;
	public ListItem(LongConsumer item, int ord)
	{
		this.item = item;
		this.ord = ord;
	}
	public LongConsumer getItem()
	{
		return this.item;
	}
	public int getOrd()
	{
		return this.ord;
	}
	public boolean equals(ListItem a, ListItem b)
	{
		return (a.item == b.item && a.ord == b.ord);
	}
}

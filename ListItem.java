import java.util.function.*;
public class ListItem {
	private LongConsumer item;
	private long ord;
	public ListItem(LongConsumer item, long ord)
	{
		this.item = item;
		this.ord = ord;
	}
	public LongConsumer getItem()
	{
		return this.item;
	}
	public long getOrd()
	{
		return this.ord;
	}
	public boolean equals(ListItem a, ListItem b)
	{
		return (a.item == b.item && a.ord == b.ord);
	}
}

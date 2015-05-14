import java.util.function.*;
import java.util.List;
import java.util.ArrayList;

public class OrderedList {
	private List<ListItem>list;

	public OrderedList()
	{
		this.list = new ArrayList<ListItem>();
	}
	
	public void insert(LongConsumer item, int ord)
	{
		int size = this.list.size();
		int idx = 0;
		while(idx < size && this.list.get(idx).getOrd() < ord)
		{
			idx++;
		}
		this.list.add(item);
		this.list.add(ord);
	}
	public void remove(LongConsumer item)
	{
		int size = this.list.size();
		int idx = 0;
		while(idx < size && this.list.get(idx).getItem() != item)
		{
			idx++;
		}
		if (idx < size)
		{
			this.list.remove(idx);
			this.list.remove(idx+1);
		}
	}
	public ListItem head()
	{
		if (this.list != null)
		{
			return this.list.get(0);
		}
		else
		{
			return null;
		}
	}
	//pop?
}

import java.util.function.*;
import java.util.List;
import java.util.ArrayList;

public class OrderedList {
	private List<ListItem>list;

	public OrderedList()
	{
		this.list = new ArrayList<ListItem>();
	}
	
	public void insert(LongConsumer item, long ord)
	{
		int size = this.list.size();
		
		int idx = 0;
		while(idx < size && this.list.get(idx).getOrd() < ord)
		{
			idx++;
		}
		ListItem newlist = new ListItem(item, ord);
		//System.out.println(newlist.getItem());
		this.list.add(idx, newlist);
		
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
			
		}
	}
	public ListItem head()
	{
		//System.out.println(this.list.size());
		if (this.list.size() != 0)
		{
			
			return this.list.get(0);
		}
		else
		{
			return null;
		}
	}
	public void pop()
	{
		if (this.list.size() != 0)
		{
			this.list.remove(0);
		}
	}
	
}

import java.util.List;
import java.util.ArrayList;
public class OpenSetList {
	

		private List<OpenSetItem>list;

		public OpenSetList()
		{
			this.list = new ArrayList<OpenSetItem>();
		}
		
		public void insert(Point pt,  int f_value)
		{
			int size = this.list.size();
			
			int idx = 0;
			while(idx < size && this.list.get(idx).getFValue() < f_value)
			{
				idx++;
			}
			OpenSetItem newlist = new OpenSetItem(pt, f_value);
			this.list.add(idx, newlist);
			
		}
		public void remove(Point pt)
		{
			int size = this.list.size();
			int idx = 0;
			while(idx < size && this.list.get(idx).getPoint() != pt)
			{
				idx++;
			}
			if (idx < size)
			{
				this.list.remove(idx);
				
			}
		}
		public OpenSetItem head()
		{
		
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
		public int size()
		{
			return this.list.size();
		}
		
	}


}

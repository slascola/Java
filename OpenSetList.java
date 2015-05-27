import java.util.List;
import java.util.ArrayList;
public class OpenSetList {
	

		private List<OpenSetItem>list;

		public OpenSetList()
		{
			this.list = new ArrayList<OpenSetItem>();
		}
		
		public void insert(Node node,  int f_value)
		{
			int size = this.list.size();
			
			int idx = 0;
			while(idx < size && this.list.get(idx).getFValue() < f_value)
			{
				idx++;
			}
			OpenSetItem newlist = new OpenSetItem(node, f_value);
			this.list.add(idx, newlist);
			
		}
		public void remove(Node node)
		{
			int size = this.list.size();
			int idx = 0;
			while(idx < size && this.list.get(idx).getNode() != node)
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
		public boolean contains(Node node)
		{
			for(int i = 0; i < this.list.size(); i++)
			{
				
			   if(this.list.get(i).getNode() == node)
			    {
				return true;
				
			    }
			   else if(!(node instanceof Node))
			   {
				return false;
			   }
			}
		}
		
	}


}

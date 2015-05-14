import java.util.function.*;//long consumer for lambda expressions
import java.util.List;
import java.util.ArrayList;
import processing.core.*;
public class ActionItems extends Entity
{
	private List<LongConsumer> pending_actions;
	public ActionItems(String name, Point position, List<PImage> imgs)
	{
		super(name, position, imgs);
		this.pending_actions = new ArrayList<LongConsumer>();
	}
	protected void remove_pending_action(LongConsumer action)
	{
	   this.pending_actions.remove(action);
	}

	protected void add_pending_actions(LongConsumer action)
	{
		this.pending_actions.add(action);
	}
	protected List<LongConsumer> get_pending_actions()
	{
		return this.pending_actions;
	}
	protected void clear_pending_actions()
	{
		this.pending_actions = new ArrayList<LongConsumer>();
	}
}

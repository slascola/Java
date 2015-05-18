
public class Controller extends Point
{
	private int KEY_DELAY = 400;
	private int KEY_INTERVAL = 100;
	
	private int TIMER_FREQUENCY = 100;
	
	public Controller(int x, int y)
	{
		super(x, y);
	}
	
	public static Point mouse_to_tile(Point pos, int tile_width, int tile_height)
	{
		Point pt = new Point(pos[0] / tile_width, pos[1] / tile_height);
		return pt;
	}
	public static void handle_timer_event(WorldModel world, WorldView view)
	{
		world.update_on_time(System.currentTimeMillis());
	}
	public static void handle_mouse_motion(WorldView view, event) //what is event
	{
		Point mouse_pt = mouse_to_tile(event.pos, view.tile_width, view.tile_height);
		view.mouse_move(mouse_pt);
	}
	public static void handle_keydown(WorldView view)
	{
		//key pressed like in lab
	}
	public static void activity_loop(WorldView view, WorldModel world)
	{
		
	}

}

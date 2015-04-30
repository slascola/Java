
public class Obstacle extends Entity
{
   public Obstacle(String name, Point position)
   {
	   super(name, position);
   }
   protected String entity_string()
   {
	   String obstacle_entity;
	   obstacle_entity = "obstacle" + this.get_name() + toString(this.get_position());
	   return obstacle_entity;
   }
}

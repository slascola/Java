import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

public class TestCases {
	private double DELTA = .0001;
	
	@Test
	public void testGetName()
	{
		Point pos = new Point(1,2);
		Entity e = new Entity("Ore", pos);
		assertEquals("Ore", e.get_name());
	}

}

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
	
	@Test
	public void testBackgroundGetName()
	{
		Background b = new Background("Supernatural");
		assertEquals("Supernatural", b.get_name());
	}
	
	@Test
	public void testDudesSetResourceCount()
	{
		Point pos = new Point(2, 4);
		Dudes d = new Dudes("Dean", pos, 5, 9, 8);
		d.set_resource_count(34);
		assertEquals(34, d.get_resource_count(), DELTA);
	}
	
	@Test
	public void testDudesGetResourceCount()
	{
		Point pos = new Point(6, 9);
		Dudes d = new Dudes("Sammy", pos, 14, 5, 0);
		assertEquals(0, d.get_resource_count(), DELTA);
	}
	
	@Test
	public void testDudesGetResourceLimit()
	{
		Point pos = new Point(5, 8);
		Dudes d = new Dudes("Sammy", pos, 5, 12, 0);
		assertEquals(12, d.get_resource_limit(), DELTA);
	}
	
	@Test
	public void testDudesGetRate()
	{
		Point pos = new Point(9, 2);
		Dudes d = new Dudes("Kevin", pos, 9, 8, 0);
		assertEquals(9, d.get_rate(), DELTA);
	}
	
	@Test
	public void testMinerGetAnimationRate()
	{
		Point pos = new Point(6, 8);
		Miner m = new Miner("Crowley", pos, 8, 15, 0, 6);
		assertEquals(6, m.get_animation_rate(), DELTA);
	}
	
	@Test
	public void testRateGetRate()
	{
		Point pos = new Point(85, 67);
		Rate r = new Rate("Dat Rate", pos, 9);
		assertEquals(9, r.get_rate(), DELTA);
	}

}

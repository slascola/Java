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
		Dudes d = new Dudes("Garth", pos, 5, 12, 0);
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
		Miner m = new Miner("Crowley", pos, 8, 15, 0, 6, null);
		assertEquals(6, m.get_animation_rate(), DELTA);
	}
	
	@Test
	public void testRateGetRate()
	{
		Point pos = new Point(85, 67);
		Rate r = new Rate("Dat Rate", pos, 9);
		assertEquals(9, r.get_rate(), DELTA);
	}
	@Test
	public void testMNFString()
	{
		Point pos = new Point(2, 6);
		MinerNotFull mnf = new MinerNotFull("Charlie", 10, pos, 14, null, 10, 0);
		String m_string = "miner Charlie 2 6 10 14 10";
		assertEquals(m_string, mnf.entity_string());
	}
	@Test
	public void testTryTransformMNF1()
	{
		Point pos = new Point(4, 5);
		MinerNotFull mnf = new MinerNotFull("Jody", 2, pos, 7, null, 5, 1);
		assertEquals(mnf, mnf.try_transform_miner_not_full());
	}
	@Test
	public void testTryTransformMNF2()
	{
		Point pos = new Point(8,7);
		MinerNotFull mnf = new MinerNotFull("Gabriel", 5, pos, 8, null, 1, 10);
		MinerFull mf = new MinerFull("Gabriel", 5, pos, 8, null, 1, 10);
		Miner haha = mnf.try_transform_miner_not_full();
		assertEquals("Gabriel", haha.get_name());
		assertEquals(5, haha.get_resource_limit(), DELTA);
		assertEquals(pos, haha.get_position());
		assertEquals(8, haha.get_rate(), DELTA);
		assertEquals(null, haha.get_images());
		assertEquals(1, haha.get_animation_rate(), DELTA);
		assertEquals(0, haha.get_resource_count(), DELTA);
		
	}
	@Test
	public void testsetPosition()
	{
		Point pos = new Point(1, 2);
		Entity e = new Entity("MinerNotFull", pos);
		Point newPos = new Point(2, 3);
		assertEquals(newPos ,e.set_position(pos));
	}
	
	@Test 
	public void getPosition()
	{
		Point pos = new Point(3,4);
		Entity e = new Entity("Ore", pos);
		assertEquals(pos, e.get_position());
		
	}
	
	@Test
	public void testGetName1()
	{
		Point pos = new Point(1, 2);
		Entity e = new Entity("Ore", pos);
		assertEquals("Ore", e.get_name());
		
	}
	
	@Test 
	public void testAdjacentFirstTrue()
	{
		Point p1 = new Point(1, 4);
		Point p2 = new Point(1, 3);
		assertTrue(Actions.adjacent(p1, p2));
		
	}
	
	@Test
	public void testAdjacentBothFalse()
	{
		Point p1 = new Point(6, 3);
		Point p2 = new Point(2, 7);
		assertFalse(Actions.adjacent(p1, p2));
	}
	
	@Test 
	public void testAdjacentSecondTrue()
	{
		Point p1 = new Point(5, 3);
		Point p2 = new Point(4, 3);
		assertTrue(Actions.adjacent(p1, p2));
	}
	
	@Test
	public void testBlacksmithString()
	{
		Point pos = new Point(1, 2);
		Blacksmith b = new Blacksmith("Blacksmith", pos, 1, 5000, 3, 2); 
		String str = new String("blacksmith Blacksmith 1 2 1 5000 1");
		assertEquals(str, b.entity_string());
		
		
	}
	
	@Test
	public void testObstacleString()
	{
	Point pos = new Point(2, 3);
	Obstacle o = new Obstacle("Obstacle", pos);
	String o_str = new String("obstacle Obstacle 2 3");
	assertEquals(o_str, o.entity_string());
	}
	
	
	@Test
	public void testOreString()
	{
		Point pos = new Point(3, 7);
		Ore ore = new Ore("Ore", pos, 5000);
		String ore_str = new String("ore Ore 3 7 5000");
		assertEquals(ore_str, ore.entity_string());
	}
	
	@Test
	public void testOreBlobGetAnimation()
	{
	
		Point pos = new Point(1, 2);
		OreBlob ob = new OreBlob("OreBlob", pos, 500, 20);
		assertEquals(20, ob.get_animation_rate());
	}
	
	@Test
	public void testQuake()
	{
		Point pos = new Point(1, 2);
		Quake q = new Quake("Quake", pos, 500);
		assertEquals(500, q.get_animation_rate());

		
	}
	
	@Test
	public void testVeinString()
	{
		Point pos = new Point(8, 5);
		Vein v = new Vein("Vein", 5, pos, 1);
		String v_str = new String("vein Vein 8 5 5 1");
		assertEquals(v_str, v.entity_string());
	}
	
	@Test
	public void testVeinGetResourceDist()
	{
		Point pos = new Point(4, 17);
		Vein v = new Vein("Vein", 5, pos, 1);
		assertEquals(1, v.get_resource_distance());
	}
	@Test
	public void testRemoveEntity()
	{
		Point pos = new Point(6, 8);
		Entity e = new Entity("Kayla", pos);
		
	}
	@Test
	public boolean testWithinBoundsAllTrue()
	{
		Grid background = new Grid(4, 4, int[]);
		int[][] occupancy; //need defining
		WorldModel world = new WorldModel(background, 4, 5, );
		Point point = new Point(1, 2);
		int x = 0;
		int y = 2;
		assertTrue(point);
	}
	
	@Test
	public boolean testWithinBoundsFalse()
	{
		Grid background = new Grid(6, 6, int[]);
		int[][] occupancy;//need defining
		WorldModel world = new WorldModel(7, 7, background);
		Point point = new Point(-1, -5);
		assertFalse(world.within_bounds(point));
	}
	
	@Test
	public int testDistanceSq()
	{
		Point p1 = new Point(1, 3);
		Point p2 = new Point(2, 1);
		assertEquals(5, WorldModel.distance_sq(p1, p2));
	}


	

}

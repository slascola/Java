import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
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
		e.set_position(newPos);
		assertEquals(newPos ,e.get_position());
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
	public void testWithinBoundsAllTrue()
	{
		WorldModel world = new WorldModel(4, 4);
		Point point = new Point(1, 2);
		assertTrue(world.within_bounds(point));
	}
	
	@Test
	public void testWithinBoundsFalse()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(-1, -5);
		assertFalse(world.within_bounds(pt));
	}
	
	@Test
	public void testDistanceSq()
	{
		Point p1 = new Point(1, 3);
		Point p2 = new Point(2, 1);
		assertEquals(5, WorldModel.distance_sq(p1, p2));
	}
	
	@Test
	public void testMinertoSmith1()
	{
		Point pos1 = new Point(1,2);
		WorldModel world = new WorldModel(4,5);
		Point pos = new Point(4,9);
		MinerFull m = new MinerFull("Jo", 8, pos, 4, null, 7, 10);
		Point pos2 = new Point(5, 8);
		Blacksmith b = new Blacksmith("Kayla", pos2, 1, 5000, 1, 0);
		assertFalse(m.miner_to_smith(world, null));
	}
    @Test
    public void testMinertoSmith2()
    {
    	Point pos = new Point(1, 4);
    	MinerFull m = new MinerFull("Donna", 4, pos, 9, null, 1, 7);
    	WorldModel world = new WorldModel(8, 7);
    	Point bs = new Point(1, 3);
    	Blacksmith b = new Blacksmith("Daniel", bs, 7, 1, 1, 0);
    	assertTrue(m.miner_to_smith(world, b));
    	assertEquals(0, m.get_resource_count(), DELTA);
    	
    	
    }
    @Test
    public void testMinertoSmith3()
    {
    	Point pos = new Point(6,3);
    	MinerFull m = new MinerFull("Cain", 8, pos, 6, null, 4, 8);
    	Point pos1 = new Point(5,7);
    	WorldModel world = new WorldModel(8, 4);
    	Point bs = new Point(2,7);
    	Blacksmith b = new Blacksmith("Adam", bs, 6, 1, 1, 0);
    	assertFalse(m.miner_to_smith(world, b));
    	
    }
    @Test
    public void testMinertoOre1()
    {
    	Point pos = new Point(9,1);
    	MinerNotFull m = new MinerNotFull("Rowena", 1, pos, 9, null, 3, 7);
    	WorldModel world = new WorldModel(1, 2);
    	Point pos2 = new Point(2, 5);
    	Ore ore = new Ore("Stephanie", pos2, 5000);
    	assertFalse(m.miner_to_ore(world, null));
    }
    @Test
    public void testMinertoOre2()
    {
    	Point pos = new Point(2, 5);
    	MinerNotFull m = new MinerNotFull("Cap", 2, pos, 4, null, 9, 1);
    	WorldModel world = new WorldModel(2, 5);
    	Point os = new Point(2, 4);
    	Ore ore = new Ore("Samandriel", os, 4);
    	assertTrue(m.miner_to_ore(world, ore));
    	assertEquals(1, m.get_resource_count(), DELTA);
    	assertNull(world.get_occupancy(os));
    	
    }
    @Test
    public void testMinertoOre3()
    {
    	Point pos = new Point(5, 8);
    	MinerNotFull m = new MinerNotFull("Blackwidow", 6, pos, 8, null, 1, 0);
    	Point pos1 = new Point(2, 0);
    	WorldModel world = new WorldModel(1, 5);
    	Point os = new Point(100, 50);
    	Ore ore = new Ore("Tony Stark", os, 1);
    	assertFalse(m.miner_to_ore(world, ore));
    	
    }
    @Test
	public void testFindNearest()
	{
		WorldModel world = new WorldModel(8, 8);
		Point p = new Point(1, 2);
		List<Entity> entities = new ArrayList<Entity>();
		
		Point pos1 = new Point(3,4);
		Ore o = new Ore("closest", pos1, 5000); //closest
		world.add_entity(o);
		
		Point pos2 = new Point(7,7);
		world.add_entity(new Ore("farthest", pos2, 5000));
		
		
		assertEquals(o, world.find_nearest(p, Ore.class));	
	}
    @Test 
	public void testGet_backgroundWorldModel()
	{
    	WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		Background background = new Background("name");
		world.set_background(pt, background);
		assertEquals(background, world.get_background(pt));
	}


    @Test 
	public void  testGet_background() //return null???
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(-1, -5);
		assertNull(world.get_background(pt));
		
	}
    @Test
    public void testaddEntity()
    {
    	Point pos = new Point(1, 2);
    	Entity e = new Entity("Lolz", pos);
    	WorldModel world = new WorldModel(4, 4);
		world.add_entity(e);
		world.set_occupancy(pos, e);
		List<Entity> entitylist = new ArrayList<Entity>();
		entitylist.add(e);
		assertEquals(e, entitylist.get(0));
		assertEquals(e, world.get_occupancy(pos));
		
		
    }
    @Test
    public void testMoveEntity()
    {
    	Point pos = new Point(5, 7);
    	Entity e = new Entity("Hi", pos);
    	Point newpoint = new Point(1, 2);
    	WorldModel world = new WorldModel(4, 4);
    	e.set_position(newpoint);
    	assertEquals(newpoint, e.get_position());	
    	
    }
    @Test
    public void testRemoveEntityAt()
    {
    	WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		world.set_occupancy(pt, null);
		
		Point pos = new Point(3, 4);
		Point newpos = new Point(-1, -1);
		Entity entity = new Entity("lol", pos);
		entity.set_position(newpos);
		
		assertEquals(null, world.get_occupancy(pt));
		assertEquals(newpos, entity.get_position());	
    	
    	
    }
    @Test
    public void testRemoveEntity()
    {
    	Point pos = new Point(1, 2);
    	Entity e = new Entity("Yo", pos);
    	WorldModel world = new WorldModel(4, 4);
    	world.remove_entity(e);
    	assertNull(world.get_occupancy(pos));
    }

    @Test
	public void testgetEntites() 
	{
		WorldModel world = new WorldModel(4, 4);
		assertEquals(0, world.get_entities().size());
	}
    @Test 
	public void testGetTileOccupant() //
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		Entity e = new Entity("snoop", pt);
		world.set_occupancy(pt, e);
		assertEquals(e, world.get_occupancy(pt));
		
	}
    @Test
    public void testNearestEntity()
    {
    	WorldModel world = new WorldModel(8,8);
    	List<Entity> l1 = new ArrayList<Entity>(10);
    	Point pos = new Point(1, 2);
    	Point opoint = new Point(5, 5);
    	Ore o = new Ore("Me", opoint, 5000);
    	Point o2 = new Point(4, 4);
    	Ore ore2 = new Ore("o2", o2, 5000);
    	l1.add(o);
    	l1.add(ore2);
    	assertEquals(ore2, world.nearest_entity(l1, pos));
    	
    	
    }
    @Test
	public void testNearestEntityNull()
	{
		List<Entity> l1 = new ArrayList<Entity>(0);
		Point point = new Point (1, 2);
		WorldModel world = new WorldModel(8,8);
		assertNull(world.nearest_entity(l1, point));
		
	}
    @Test
	public void testIsOccupied()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		Point pos = new Point(3, 4);
		Entity e = new Entity("name", pos);
		world.set_occupancy(pt, e);
		assertTrue(world.is_occupied(world, pt));
	}
	
	@Test
	public void testIsOccupied2()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		Point pos = new Point(3, 4);
		world.set_occupancy(pt, null);
		assertFalse(world.is_occupied(world, pt));
	}
	
	
	@Test
	public void testNextPosition()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(0, 0);
		Point dest = new Point(0, 4);
		Point returned = new Point(0, 1);
		assertEquals(0, Actions.next_position(world, pt, dest).x);
		assertEquals(1, Actions.next_position(world, pt, dest).y);
		
	}
	@Test
	public void testNextPosition2()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 4);
		Entity e = new Entity("Colin", pt);
		Point dest = new Point(5, 2);
		Point newpoint = new Point(1, 3);
		world.set_occupancy(pt, e);
		assertEquals(2, Actions.next_position(world, pt, dest).x);
		assertEquals(4, Actions.next_position(world, pt, dest).y);
	}
	@Test
	public void testNextPosition3()
	{
		WorldModel world = new WorldModel(4, 4);
		Point pt = new Point(1, 2);
		Point dest = new Point(1, 2);
		assertEquals(1, Actions.next_position(world, pt, dest).x);
		assertEquals(2, Actions.next_position(world, pt, dest).y);
	}
	@Test
	public void testBlobNextPosition()
	{
		WorldModel world = new WorldModel(5, 5);
		Point pt = new Point(2, 2);
		Point dest = new Point(2, 3);
		assertEquals(2, Actions.blob_next_position(world, pt, dest).x);
		assertEquals(3, Actions.blob_next_position(world, pt, dest).y);
		
	}
	@Test
	public void testBlobNextPosition2()
	{
		WorldModel world = new WorldModel(6, 6);
		Point pt = new Point(2, 4);
		Point dest = new Point(3, 1);
	    assertEquals(3, Actions.blob_next_position(world, pt, dest).x);
	    assertEquals(4, Actions.blob_next_position(world, pt, dest).y);
	    
	}
	@Test
	public void testBlobNextPosition3()
	{
		WorldModel world = new WorldModel(7, 7);
		Point pt = new Point(5, 1);
		Point dest = new Point(5, 1);
		assertEquals(5, Actions.blob_next_position(world, pt, dest).x);
		assertEquals(1, Actions.blob_next_position(world, pt, dest).y);
		
	}

    
    
    

	

}

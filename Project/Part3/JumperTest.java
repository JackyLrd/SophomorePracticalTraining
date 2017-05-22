import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

public class JumperTest
{
	public Jumper jumper = new Jumper();
	@Test
	public void testMove()
	{
		ActorWorld world = new ActorWorld();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(4, 5), new Flower());
		jumper.move();
		assertEquals(new Location(3, 5), jumper.getLocation());
	}

	@Test
	public void testTurn()
	{
		ActorWorld world = new ActorWorld();
		world.add(new Location(5, 5), jumper);
		jumper.turn();
		assertEquals(45, jumper.getDirection());
	}

	@Test
	public void testCanMove()
	{
		ActorWorld world = new ActorWorld();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Rock());
		jumper.act();
		assertEquals(45, jumper.getDirection());
		jumper.act();
		assertEquals(new Location(3,7), jumper.getLocation());
		assertEquals(true, jumper.canMove());
		jumper.act();
		assertEquals(false, jumper.canMove());
	}
}
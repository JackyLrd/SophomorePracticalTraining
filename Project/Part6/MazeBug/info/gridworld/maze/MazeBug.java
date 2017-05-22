package info.gridworld.maze;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug 
{
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;

	public int[] d = {1, 1, 1, 1};

	//final message has been shown
	boolean hasShown = false;
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() 
	{
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	public Location getMostChoosedLocation(ArrayList<Location> locs)
	{
		Location loc = getLocation();
		int k = loc.getDirectionToward(locs.get(0)) / 90;
		int max = d[k];
		k = 0;
		for(int i = 1; i < locs.size(); i++)
		{
			int j = loc.getDirectionToward(locs.get(i)) / 90;
			if(max < d[j])
			{
				max = d[j];
				k = i;
			}
		}
		return locs.get(k);
	}

	public void increaseWeight(int direction)
	{
		d[direction / 90]++;
	}

	public void decreaseWeight(int direction)
	{
		d[direction / 90]--;
	}

	public int[] getWeight()
	{
		return d;
	}

	public int getCount()
	{
		return stepCount;
	}
	/**
	 * Moves to the next location of the square.
	 */
	public void act() 
	{
		if (stepCount == 0)
		{
			ArrayList<Location> locs = new ArrayList<Location>();
			locs.add(getLocation());
			crossLocation.push(locs);
		}
		boolean willMove = canMove();
		if (isEnd == true) 
		{
		//to show step count when reach the goal		
			if (hasShown == false) 
			{
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} 
		else if (willMove) 
		{
			move();
			//increase step count when move 
			stepCount++;
		} 
		else
		{
			goBack();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) 
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		if (crossLocation.size() > 0)
		{
			ArrayList<Location> f = crossLocation.pop();
			ArrayList<Location> s = null;
			if (crossLocation.size() > 0)
			{
				s = crossLocation.peek();
				for (int i = 0; i < 4; i++)
				{
					Location n = loc.getAdjacentLocation(getDirection() + Location.RIGHT * i);
					Actor actor = null;
					if(gr.isValid(n))
					{
						if(actor instanceof Rock && !actor.getColor().equals(new Color(255, 0, 0)))
						{
							continue;
						}
						actor = (Actor)gr.get(n);
						if(actor instanceof Rock && actor.getColor().equals(new Color(255, 0, 0)))
						{
							valid = new ArrayList<Location>();
							valid.add(n);
							return valid;
						}
						else if(actor == null)
						{
							if(!f.contains(n))
							{
								valid.add(n);
							}
						}
					}
				}
			}
			if(valid.size() == 0)
			{
				for(int i = 0; i < 4; i++)
				{
					Location n = loc.getAdjacentLocation(getDirection() + Location.RIGHT * i);
					if(gr.isValid(n))
					{
						Actor actor = (Actor)gr.get(n);
						if(actor instanceof Rock && !actor.getColor().equals(new Color(255, 0, 0)))
						{
							continue;
						}
						if(actor instanceof Flower)
						{
							if(s != null && !s.contains(n) && !f.contains(n))
							{
								valid.add(n);
							}
						}
						else if(s == null && !f.contains(n))
						{
							if(!f.contains(n))
							{
								valid.add(n);
							}
						}
					}
				}
			}
			crossLocation.add(f);
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() 
	{
		if(getValid(getLocation()).size() > 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() 
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		ArrayList<Location> locs = getValid(getLocation());
		Location n = getMostChoosedLocation(locs);

		if (gr.isValid(n)) 
		{
			if(gr.get(n) instanceof Rock && gr.get(n).getColor().equals(new Color(255, 0, 0)))
			{
				isEnd = true;
			}
			setDirection(getLocation().getDirectionToward(n));
			moveTo(n);
			increaseWeight(getDirection());
		} 
		else
		{
			removeSelfFromGrid();
			return;
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
		locs = crossLocation.pop();
		locs.add(n);
		crossLocation.push(locs);
		locs = new ArrayList<Location>();
		locs.add(n);
		crossLocation.push(locs);
	}

	public void goBack()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		if(crossLocation.size() > 0)
		{
			crossLocation.pop();
			if(crossLocation.size() > 0)
			{
				Location loc = getLocation();
				ArrayList<Location> p = crossLocation.peek();
				Location n = p.get(0);
				if(gr.isValid(n))
				{
					setDirection(loc.getDirectionToward(n));
					if(d[getDirection() / 90] > 1)
					{
						decreaseWeight(getDirection());
					}
					moveTo(n);
				}
				else
				{
					removeSelfFromGrid();
					return;
				}
				Flower flower = new Flower(getColor());
				flower.putSelfInGrid(gr, loc);
			}
		}
	}
}

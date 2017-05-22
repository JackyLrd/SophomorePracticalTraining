/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private static final double BRIGHTENING_FACTOR = 0.05;
    private int courage;

    public BlusterCritter(int c)
    {
        courage = c;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        Location loc = getLocation();
        int r = loc.getRow();
        int c = loc.getCol();
        for(int i = r - 2; i <= r + 2; i++)
        {
            for(int j = c - 2; j <= c + 2; j++)
            {
                if(i != r || j != c)
                {
                    if(getGrid().isValid(new Location(i, j)))
                    {
                        if(getGrid().get(new Location(i, j)) != null)
                        {
                            neighbors.add(getGrid().get(new Location(i, j)));
                        }
                    }  
                }
            }
        }
        return neighbors;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        int count = actors.size();
        if(count < courage)
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 + BRIGHTENING_FACTOR));
            if(red > 255)
            {
                red = 255;
            }
            int green = (int) (c.getGreen() * (1 + BRIGHTENING_FACTOR));
            if(green > 255)
            {
                green = 255;
            }
            int blue = (int) (c.getBlue() * (1 + BRIGHTENING_FACTOR));
            if(blue > 255)
            {
                blue = 255;
            }
            setColor(new Color(red, green, blue));
        }
        else
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
    }
}
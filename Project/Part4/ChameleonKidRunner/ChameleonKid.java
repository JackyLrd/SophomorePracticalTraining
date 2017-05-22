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
import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter
{
    private static final double DARKENING_FACTOR = 0.05;

    public void processActors(ArrayList<Actor> actors)
    {
        ArrayList<Actor> newactors = new ArrayList<Actor>();
        for(Actor a : actors)
        {
            if(getLocation().getDirectionToward(a.getLocation()) == getDirection() || 
                getLocation().getDirectionToward(a.getLocation()) == (getDirection() + 180) || 
                    getLocation().getDirectionToward(a.getLocation()) == (getDirection() - 180))
            {
                newactors.add(a);
            }
        }
        int size = newactors.size();
        if(size == 0)
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            return;
        }

        int r = (int)(Math.random() * size);
        Actor a = newactors.get(r);
        setColor(a.getColor());
    }
}
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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;

import java.awt.Color;

public class Jumper extends Actor
{
    //Constructors.
    public Jumper()
    {
        setColor(Color.RED);
    }
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        if (canMove())
        {
            move();
        }
        else
        {
            turn();
        }
    }
    //Just the same as Bug.
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT); //Turn 45 degrees.
    }
    //imitate the Bug class.
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if(gr == null)
        {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()); //1 step forward.
        Location next2 = next.getAdjacentLocation(getDirection()); //2 steps forward.
        if(gr.isValid(next2))
        {
            moveTo(next2);
        }
        else
        {
            removeSelfFromGrid();
        }
    }

    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if(gr == null)
        {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()); //1 step forward.
        Location next2 = next.getAdjacentLocation(getDirection()); //2 steps forward.
        if(!gr.isValid(next2))
        {
            return false;
        }
        Actor neighbor = gr.get(next2);
        return (neighbor == null) || (neighbor instanceof Flower); //The jumper can step of a flower.
    }
}
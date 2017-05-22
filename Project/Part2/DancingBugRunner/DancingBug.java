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

import info.gridworld.actor.Bug;
import java.util.Arrays;
/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class DancingBug extends Bug
{
    private int[] dancemove;
    private int mark;

    public DancingBug(int[] array)
    {
        dancemove = array != null ? Arrays.copyOf(array, array.length) : null; //copy the array.
        mark = 0; //reset the mark
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if(canMove())
        {
            setDirection(getDirection() + dancemove[mark] * 45); //set the direction.
            if(canMove())
            {
                move();
                mark++;
                if(mark == dancemove.length)
                {
                    mark = 0; //reset the mark.
                }
            }
        }
        else
        {
            turn();
        }
    }
}
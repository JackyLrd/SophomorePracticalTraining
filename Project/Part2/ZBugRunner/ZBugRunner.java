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

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(6);
        alice.setColor(Color.ORANGE);
        ZBug bob = new ZBug(4);
        ZBug lqh = new ZBug(4);
        ZBug ltj = new ZBug(4);
        ZBug lyb = new ZBug(4);
        world.add(new Location(0, 0), bob); //left up.
        world.add(new Location(0, 5), lqh); //to cover all situation
        world.add(new Location(6, 0), ltj);
        world.add(new Location(6, 6), lyb);
        world.show();
    }
}
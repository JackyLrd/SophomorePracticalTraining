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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    public QuickCrab()
    {
        setColor(Color.RED);
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        Grid gr = getGrid();
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            if(gr.get(loc) == null)
            {
                if(gr.isValid(loc.getAdjacentLocation(getDirection() + Location.LEFT)))       
                {
                    if(gr.get(loc.getAdjacentLocation(getDirection() + Location.LEFT)) == null)
                    {
                        locs.add(loc.getAdjacentLocation(getDirection() + Location.LEFT));
                    }
                    
                }
                else if (gr.isValid(loc.getAdjacentLocation(getDirection() + Location.RIGHT)))
                {                   
                    if(gr.get(loc.getAdjacentLocation(getDirection() + Location.RIGHT)) == null)
                    {
                        locs.add(loc.getAdjacentLocation(getDirection() + Location.RIGHT));
                    }
                }
            }
        }
        return locs;
    }   
}


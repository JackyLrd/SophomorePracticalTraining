/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import java.util.ArrayList;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
// A class used as node
class OccupantInCol
{
    public Object occupant;
    public int col;
    public OccupantInCol next;
    OccupantInCol(Object o, int cols)
    {
        occupant = o;
        col = cols;
        next = null;
    }
}

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private OccupantInCol[] sparseArray; 
    // the array storing the grid elements
    private int col;
    private int row;
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
        {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0)
        {
            throw new IllegalArgumentException("cols <= 0");
        }
        sparseArray = new OccupantInCol[rows];
        col = cols;
        row = rows;
    }

    public int getNumRows()
    {
        return row;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return col;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            OccupantInCol node = sparseArray[r];
            while(node != null)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, node.col);
                if (get(loc) != null)
                {
                    theLocations.add(loc);
                }
                node = node.next;
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        OccupantInCol node = sparseArray[loc.getRow()];
        while(node != null)
        {
            if(node.col == loc.getCol())
            {
                return(E)node.occupant;
            }
            node = node.next;
        }
        return null;
        // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null)
        {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        OccupantInCol newnode = new OccupantInCol(obj, loc.getCol());
        newnode.next = sparseArray[loc.getRow()];
        sparseArray[loc.getRow()] = newnode;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        OccupantInCol node = sparseArray[loc.getRow()];
        if(node.col == loc.getCol())
        {
            sparseArray[loc.getRow()] = node.next;
            return r;
        }
        while(node != null && node.next != null)
        {
            if(node.next.col == loc.getCol())
            {
                node.next = node.next.next;
                return r;
            }
            node = node.next;
        }
        return r;
    }
}

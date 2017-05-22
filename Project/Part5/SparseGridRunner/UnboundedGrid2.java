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

import java.util.ArrayList;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements
    private int col;
    private int row;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in UnboundedGrid2
     * @param cols number of columns in UnboundedGrid2
     */
    public UnboundedGrid2()
    {
        occupantArray = new Object[16][16];
        row = 16;
        col = 16;
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < row; r++)
        {
            for (int c = 0; c < col; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                {
                    theLocations.add(loc);
                }
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
        if(loc.getRow() >= row || loc.getCol() >= col)
        {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
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
        if(loc.getRow() >= row || loc.getCol() >= col)
        {        
            ArrayList<Location> oldlocs = getOccupiedLocations();
            Object[][] array = new Object[row * 2][col * 2];
            for(Location l : oldlocs)
            {
                array[l.getRow()][l.getCol()] = get(l);
            }
            occupantArray = array;
            row = row * 2;
            col = col * 2;
            put(loc, obj);
        }
        else
        {
            occupantArray[loc.getRow()][loc.getCol()] = obj;
        }     
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
        if(loc.getRow() < row)
        {
            occupantArray[loc.getRow()][loc.getCol()] = null;
        }
        return r;
    }
}

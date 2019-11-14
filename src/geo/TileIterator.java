/**
 * TileIterator.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package geo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tiles.Tile;

/**
 * @author Alex
 *
 */
public class TileIterator implements Iterator<Tile> {

	int x, y;
	int width, height;
	TileBoard board;
	
	public TileIterator(TileBoard board) {
		x = 0;
		y = 0;
		width = board.getTileWidth();
		height = board.getTileHeight();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		x++;
		if (x == width) {
			x = 0;
			y++;
			if (y == height) { 
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Tile next() {
		if (hasNext()) {
			return board.getTileAt(x, y);
		}
		throw new NoSuchElementException();
	}
}

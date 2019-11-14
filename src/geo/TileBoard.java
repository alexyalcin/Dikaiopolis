/**
 * TileBoard.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 * Adapted from code by Kenan Mayer-Patel
 */

package geo;

import java.util.Iterator;

import tiles.Tile;

public interface TileBoard extends Iterable<Tile> {
	int getTileWidth();
 
	int getTileHeight();

	Tile getTileAt(int x, int y);
	
	public default Iterator<Tile> iterator() {
		return new TileIterator(this);
	}
}
/**
 * TileMap.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package geo;

import java.util.HashMap;
import java.util.Map;

import tiles.GameTile;

/**
 * @author Alex
 *
 */
public class TileMap {
	private Map<Coord, GameTile> tiles;
	private int height, width;
	
	public TileMap(String path) {
		this(ReadMap.getTiles(path));
	}
	
	public TileMap(GameTile[][] tls) {
		if (tls == null) {
			throw new IllegalArgumentException();
		}
		if (tls.length < 1) {
			throw new IllegalArgumentException();
		}
		height = tls.length;
		width = tls[0].length;
		
		tiles = new HashMap<Coord, GameTile>();		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles.put(Coord.newCoord(x, y), tls[x][y]);
			}
		}
	}
	
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	public GameTile getTile(int x, int y) {
		return tiles.get(Coord.newCoord(x, y));
	}
	
	public GameTile[] getRow(int startX, int startY, int length) {
		GameTile[] row = new GameTile[length]; //creates a row of tiles of the right length.
		Coord currentCoord = Coord.newCoord(startX, startY); //starts off at the first coordinate.
		boolean row_done = false; //there are still coordinates left in the row.
		for (int i = 0; i < length; i++) {
			if (!row_done) { // there are more tiles in the row...
				row[i] = tiles.get(currentCoord); //add the next tile to the row.
				currentCoord = currentCoord.add(Coord.newCoord(0, 1)); //increase the tile by one.
				System.out.println(currentCoord);
				if (currentCoord.y() >= width) {
					row_done = true;
				}
			} else {
				row[i] = null;
			}
		}
		return row;
	}
	
	public GameTile[] getColumn(int startX, int startY, int length) {
		GameTile[] column = new GameTile[length];
		int currentTileNum = startX * height + startY;
		boolean column_done = false;
		for (int i = 0; i < column.length; i++) {
			if (!column_done) {
				column[i] = tiles.get(currentTileNum);
				currentTileNum++;
				if (currentTileNum >= height * width) {
					column_done = true;
				}
			} else {
				column[i] = null;
			}
		}
		return column;
	}
	
	public GameTile[][] getArea(int startX, int startY, int x_len, int y_len) {
		GameTile[][] area = new GameTile[x_len][y_len];
		for (int x = 0; x < x_len; x++) {
			if (startX + x < 0) { 
				area[x] = new GameTile[x_len];
			} else {
				area[x] = getRow(startX + x, startY, x_len);
			}
		}
		return area;
	}
	
}
/**
 * MappedTileBoard.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.geo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import engine.Camera;
import engine.Enums;
import engine.Tile;
import engine.gameobjects.GameTile;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Alex
 *
 */
public class MappedTileBoard implements TileBoard{
	
	private static final int DEFAULT_HEIGHT = 11;
	private static final int DEFAULT_WIDTH = 11;
	private static final int BUFFER = 5;
	
	private int width, height;
	private int screen_width, screen_height;
	
	private Coord upperRight;
	private GameTile[][] current_spots; // buffered;
	private TileMap tileMap;
		
	public MappedTileBoard() {
		
	}

	public MappedTileBoard(TileMap tm) {
		this(tm, Coord.newCoord(0, 0), DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public MappedTileBoard(TileMap tm, Coord startLoc, int w, int h) {
		width = w;
		height = h;
		upperRight = startLoc;
		tileMap = tm;
		current_spots = tileMap.getArea(upperRight.x() - BUFFER, upperRight.y() - BUFFER, width + BUFFER * 2, height + BUFFER * 2);
	}

	public int getTileWidth() {
		return screen_width / width;
	}

	public int getTileHeight() {
		return screen_height / height;
	}

	public void shift(Enums.Direction d) {
		upperRight = upperRight.add(Coord.newCoord(Enums.direction_factor.get(d)[0], Enums.direction_factor.get(d)[1]));
		current_spots = tileMap.getArea(upperRight.x() - BUFFER, upperRight.y() - BUFFER, width + BUFFER * 2, height + BUFFER * 2);
	}
	
	public TileMap getMap() {
		return tileMap;
	}
	
	public GameTile[][] getCurrentSpots() {
		return current_spots;
	}
	/* (non-Javadoc)
	 * @see geo.TileBoard#getTileAt(int, int)
	 */
	public Tile getTileAt(int x, int y) {
		return current_spots[x][y];
	}	

	public static Image importImage(String loc) {
		File file = new File(loc);
		Image bufferedImage = null;
		try {
			 bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedImage;
	}

}

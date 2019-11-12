/**
 * MappedTileBoard.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package geo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tiles.GameTile;

/**
 * @author Alex
 *
 */
public class MappedTileBoard extends JPanel implements TileBoard{
	
	private static final int DEFAULT_SCREEN_WIDTH = 1600;
	private static final int DEFAULT_SCREEN_HEIGHT = 900;
	private static final int DEFAULT_HEIGHT = 15;
	private static final int DEFAULT_WIDTH = 20;
	
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	private int width, height;
	private int screen_width, screen_height;
	
	private Coord upperRight;
	private GameTile[][] current_spots;
	private TileMap tileMap;
	public MappedTileBoard() {
		
	}

	public MappedTileBoard(TileMap tm, Coord startLoc) {
		this(tm, startLoc, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public MappedTileBoard(TileMap tm, Coord startLoc, int w, int h) {
		this(tm, startLoc, w, h, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);

	}
	
	public MappedTileBoard(TileMap tm, Coord startLoc, int w, int h, int screen_w, int screen_h) {
		screen_width = screen_w;
		screen_height = screen_h;
		width = w;
		height = h;
		upperRight = startLoc;
		tileMap = tm;
		
		setPreferredSize(new Dimension(screen_w, screen_h));
		current_spots = tileMap.getArea(upperRight.x(), upperRight.y(), width, height);
	}
	
	@Override
	public void paintComponent (Graphics g) {
		// Super class paintComponent will take care of 
		// painting the background.
		paintTiles(g);
	}
	
	private void paintTiles(Graphics g) {
		int tile_width = screen_width / width;
		int tile_height = screen_height / height;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (y + upperRight.y() < tileMap.getHeight() && x + upperRight.x() < tileMap.getWidth()
						&& x + upperRight.x() >= 0 && y + upperRight.y() >= 0) {
					if (current_spots[x][y] == null) {
						g.setColor(Color.BLACK);
						g.fillRect(x * tile_width, y * tile_height, tile_width, tile_height);
					} else {
						List<Image> tile_images = current_spots[x][y].getImages();
						for (Image i : tile_images) {
							g.drawImage(i, x * tile_width, y * tile_height, tile_width, tile_height, null);
						}
					}
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(x * tile_width, y * tile_height, tile_width, tile_height);
				}
			}
		}
	}

	//gets tile width
	@Override
	public int getTileWidth() {
		return width;
	}

	//get tile height
	@Override
	public int getTileHeight() {
		return height;
	}

	public void shift(Direction d) {
		
	}
	/* (non-Javadoc)
	 * @see geo.TileBoard#getTileAt(int, int)
	 */
	public Tile getTileAt(int x, int y) {
		return current_spots[x][y];
	}
	
	
	private void trigger_update() {		
		repaint();

		// Not sure why, but need to schedule a call
		// to repaint for a little bit into the future
		// as well as the one we just did above
		// in order to make sure that we don't end up
		// with visual artifacts due to race conditions.
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
				repaint();
			}
		}).start();

	}
//	
	public static Image importBG(String loc) {
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

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

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
	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 10;
	private static final int BUFFER = 3;
	
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	public Map<Direction, int[]> direction_factor;
	
	private int width, height;
	private int screen_width, screen_height;
	
	private Coord upperRight;
	private GameTile[][] current_spots; // buffered;
	private TileMap tileMap;
	private int offsetY, offsetX;
	
	private static Timer shiftTimer = null;
	
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
		offsetY = 0;
		offsetX = 0;
		
		//factors for directions.
		direction_factor = new HashMap<Direction, int[]>();
		direction_factor.put(Direction.UP, new int[] {0, 1});
		direction_factor.put(Direction.DOWN, new int[] {0, -1});
		direction_factor.put(Direction.RIGHT, new int[] {-1, 0});
		direction_factor.put(Direction.LEFT, new int[] {1, 0});
		
		shift(Direction.RIGHT);
		shift(Direction.DOWN);
		setPreferredSize(new Dimension(screen_w, screen_h));
		current_spots = tileMap.getArea(upperRight.x() - BUFFER, upperRight.y() - BUFFER, width + BUFFER * 2, height + BUFFER * 2);
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
		for (int x = 0; x < width + 2 * BUFFER; x++) {
			for (int y = 0; y < height + 2 * BUFFER; y++) {
				if (y + upperRight.y()  < tileMap.getHeight() + 2 && x + upperRight.x() < tileMap.getWidth() + 2
						&& x + upperRight.x() >= 0 && y + upperRight.y() >= 0) {
					if (current_spots[x][y] == null) {
						g.setColor(Color.BLACK);
						g.fillRect(offsetX + (x - BUFFER) * tile_width, offsetY + (y - BUFFER) * tile_height, tile_width, tile_height);
					} else {
						List<Image> tile_images = current_spots[x][y].getImages();
						for (Image i : tile_images) {
							g.drawImage(i, offsetX + (x - BUFFER) * tile_width, offsetY + (y - BUFFER) * tile_height, tile_width, tile_height, null);
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
		upperRight = upperRight.add(Coord.newCoord(direction_factor.get(d)[0], direction_factor.get(d)[1]));
		current_spots = tileMap.getArea(upperRight.x() - BUFFER, upperRight.y() - BUFFER, width + BUFFER * 2, height + BUFFER * 2);
		animateShift(d);
	}
	/* (non-Javadoc)
	 * @see geo.TileBoard#getTileAt(int, int)
	 */
	public Tile getTileAt(int x, int y) {
		return current_spots[x][y];
	}
	
	public void animateShift(Direction d) {
		int tile_width = screen_width / width;
		int tile_height = screen_height / height;
		offsetX += tile_width * direction_factor.get(d)[0];
		offsetY += tile_height * direction_factor.get(d)[1];
		
		if (shiftTimer== null) {
			shiftTimer = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (offsetY != 0) {
						offsetY -= 5 * (offsetY / Math.abs(offsetY));
					}
					if (offsetX != 0) {
						offsetX -= 5 * (offsetX / Math.abs(offsetX));
					}
					repaint();
				}
			});
			shiftTimer.start();
		}
		if (!shiftTimer.isRunning()) {
			shiftTimer.restart();
		}
		if ((Math.abs(offsetY) <= 5 && Math.abs(offsetX) <= 5)) {
			offsetY = 0;
			offsetX = 0;
			shiftTimer.stop();
		}
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

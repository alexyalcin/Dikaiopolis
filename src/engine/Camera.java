/**
 * Camera.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import geo.Coord;
import geo.MappedTileBoard;
import geo.TileMap;
import tiles.GameTile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Alex
 *
 */
public class Camera extends JPanel {
	
	private static final int DEFAULT_SCREEN_WIDTH = 750;
	private static final int DEFAULT_SCREEN_HEIGHT = 450;
	private static final int DEFAULT_HEIGHT = 9;
	private static final int DEFAULT_WIDTH = 15;
	private static final int BUFFER = 5;
	
	MappedTileBoard background; 
	private GameObject target;
	private Set<GameObject> objects;
	
	private int width, height;
	private int screen_width, screen_height;
	
	private Coord upperRight;
	private int offsetY, offsetX;
	
	private Coord target_previous;
	private static Timer shiftTimer = null;
	
	public Camera(String background) { 
		this(new GameObject(Coord.newCoord(0, 0)), background);
	}
	
	public Camera(GameObject target, String background) {
		this.target = target;
		target_previous = target.getLocation();
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		this.background = new MappedTileBoard(new TileMap(background), 
				target.getLocation().add(Coord.newCoord(-(width - 1) / 2, - (height - 1) / 2)), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		objects = new TreeSet<GameObject>();
		
		screen_width = DEFAULT_SCREEN_WIDTH;
		screen_height = DEFAULT_SCREEN_HEIGHT;
		
		//factors for directions.
		
		setPreferredSize(new Dimension(screen_width, screen_height));

		offsetY = 0;
		offsetX = 0;
		trigger_update();
	}
	
	public void setTarget(GameObject target) {
		this.target = target;
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	public void addObjects(Iterable<GameObject> o) {
		for (GameObject object : o) {
			objects.add(object);
		}
	}
	
	public void followTarget() {
		int x_change = 0;
		int y_change = 0;
		x_change = target.getLocation().x() - target_previous.x();
		y_change = target.getLocation().y() - target_previous.y();
		target_previous = target.getLocation();
		
		if (x_change != 0) {
			Enums.Direction xDir = (x_change < 0) ? Enums.Direction.RIGHT : Enums.Direction.LEFT;
			while (x_change != 0) {
				shift(xDir);
				x_change -= (x_change) / Math.abs(x_change);
			}
		}
		if (y_change != 0) {
			Enums.Direction yDir = (y_change < 0) ? Enums.Direction.DOWN : Enums.Direction.UP;
			while (y_change != 0) {
				shift(yDir);
				y_change-= (y_change) / Math.abs(y_change);
			}
		}
	}
	
	@Override
	public void paintComponent (Graphics g) {
		// Super class paintComponent will take care of 
		// painting the background.
		followTarget();
		paintBackground(g);
		paintObjects(g);
	}
	
	public int[] getTileDims() {
		return new int[] {screen_width / width, screen_height / height};
	}
	
	private void paintObjects(Graphics g) {
		int tile_width = screen_width / width;
		int tile_height = screen_height / height;
		final int x_radius = (width - 1) / 2;
		final int y_radius = (height - 1) / 2;
		for (GameObject item : objects) {
			Coord c = item.getLocation();
			if (c.x() < (target.getLocation().x() - x_radius) || c.x() > (target.getLocation().x() + x_radius)
					|| c.y() < (target.getLocation().y() - y_radius) || c.y() > (target.getLocation().y() + y_radius)) {
				continue;
			} else {
				g.drawImage(item.getImage(), 
						target.getMovementOffset()[0] - (item.getMovementOffset()[0]) 
						+ (item.getLocation().x() - target.getLocation().x() + x_radius) * tile_width,
						target.getMovementOffset()[1] - (item.getMovementOffset()[1]) 
						+ (item.getLocation().y() - target.getLocation().y() + y_radius) * tile_height, 
						(int) (tile_width * item.getWidth()), (int) (tile_height * item.getHeight()), null);
			}
		}
	}

	private void paintBackground(Graphics g) {
		int tile_width = screen_width / width;
		int tile_height = screen_height / height;
		GameTile[][] current_spots = background.getCurrentSpots();
		final int x_radius = (width - 1) / 2 + BUFFER;
		final int y_radius = (height - 1) / 2 + BUFFER;
		for (int x = 0; x < current_spots.length; x++) {
			for (int y = 0; y < current_spots[0].length; y++) {
					List<Image> tile_images = current_spots[x][y].getImages();
					for (Image i : tile_images) {
						g.drawImage(i, target.getMovementOffset()[0] + (x - BUFFER) * tile_width,
								target.getMovementOffset()[1] + (y - BUFFER) * tile_height, tile_width, tile_height, null);
				}
			}
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
	
	public MappedTileBoard getTileBackground() {
		return background;
	}
	
	public void shift(Enums.Direction d) {
		background.shift(d);
	}
	
}

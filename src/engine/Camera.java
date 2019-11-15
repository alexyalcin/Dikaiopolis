/**
 * Camera.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import engine.gameobjects.GameObject;
import engine.gameobjects.GameTile;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;
import engine.geo.TileMap;

/**
 * @author Alex
 *
 */
public class Camera {
	private static final int BUFFER = 5;
	
	MappedTileBoard background; 
	private GameObject target;
	private Set<GameObject> objects;
	
	private int width, height;
	private int screen_width, screen_height;
	
	private Coord target_previous;
	
	public Camera(TileMap tm, int s_h, int s_w, int w, int h) { 
		this(new GameObject(Coord.newCoord(0, 0)), tm, s_h, s_w, w, h);
	}
	
	public Camera(GameObject target, TileMap tm, int s_h, int s_w, int w, int h) {
		this.target = target;
		target_previous = target.getLocation();
		width = w;
		height = h;
		this.background = new MappedTileBoard(tm, target.getLocation().add(Coord.newCoord(-(width - 1) / 2, - (height - 1) / 2)), w, h);
		objects = new TreeSet<GameObject>();
		
		screen_width = s_h;
		screen_height = s_w;
	}
	
	public void setObjectsReference(Set<GameObject> objects) {
		this.objects = objects;
	}
	
	public void setTarget(GameObject target) {
		this.target = target;
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
	
	
	public Coord getTile(Point p) {
		final int x_radius = (width - 1) / 2;
		final int y_radius = (height - 1) / 2;
		return Coord.newCoord((p.x / getTileDims()[0]) + target.getLocation().x() - x_radius, 
				(p.y / getTileDims()[1]) + target.getLocation().y() - y_radius);

	}
	
	
	public Image paintToImage() {
		Image i = new BufferedImage(screen_width, screen_height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = i.getGraphics();
		followTarget();
		paintBackground(g);
		paintObjects(g);
		return i;
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
			if (c.x() < (target.getLocation().x() - x_radius) - 1 || c.x() > (target.getLocation().x() + x_radius) + 1
					|| c.y() < (target.getLocation().y() - y_radius) - 1|| c.y() > (target.getLocation().y() + y_radius) + 1) {
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
	
	public MappedTileBoard getTileBackground() {
		return background;
	}
	
	public void shift(Enums.Direction d) {
		background.shift(d);
	}
	
}

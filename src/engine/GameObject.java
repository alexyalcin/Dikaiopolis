/**
 * GameObject.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import engine.Enums.Direction;
import geo.Coord;
import tiles.CollisionTile;

/**
 * @author Alex
 *
 */
public class GameObject implements Drawable, Collider{
	protected Coord location;
	private int[] movementOffset;
	private Timer offsetTimer;
	protected double height, width; // in terms of blocks.
	public boolean hasCollider;
	private Collider c;
	
	public GameObject() {
		this(Coord.newCoord(-1, -1));
	}
	
	public GameObject(Coord c) {
		location = c;
		movementOffset = new int[] {0, 0};
		height = 0;
		width = 0;
	}
	
	public void addCollider(Enums.Direction[] sidesBlocked) {
		this.c = new CollisionTile(this, location, sidesBlocked);
		hasCollider = true;
	}
	
	public void removeCollider() {
		hasCollider = false;
	}
	
	public boolean hasCollider() {
		return hasCollider;
	}
	
	public Coord getLocation() {
		return location;
	}
	
	public void setLocation(Coord location) {
		this.location = location;
	}
	
	public void move(Enums.Direction dir, int[] unitPixels, int speed) {
		int[] direction_fac = Enums.direction_factor.get(dir);
		movementOffset[0] += direction_fac[0] * unitPixels[0];
		movementOffset[1] += direction_fac[1] * unitPixels[1];
		location = location.add(Coord.newCoord(direction_fac[0], direction_fac[1]));
		animateShift(dir, speed);
	}
	
	public int[] getMovementOffset() {
		return movementOffset;
	}
	
	public void animateShift(Enums.Direction d, int speed) {
		if (offsetTimer == null) {
			offsetTimer = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if ((Math.abs(movementOffset[1]) <= speed && Math.abs(movementOffset[0]) <= speed)) {
						
						offsetTimer.stop();
					}
					if (Math.abs(movementOffset[0]) > speed) {
						movementOffset[0] -= speed * (movementOffset[0] / Math.abs(movementOffset[0]));
					} else {
						movementOffset[0] = 0;
					}
					if (Math.abs(movementOffset[1]) > speed) {
						movementOffset[1] -= speed * (movementOffset[1] / Math.abs(movementOffset[1]));
					} else {
						movementOffset[1] = 0;
					}
				}
			});
			offsetTimer.start();
		}
		if (!offsetTimer.isRunning()) {
			offsetTimer.restart();
		}
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}

	public Image getImage() {
		return new BufferedImage(0,0, BufferedImage.TYPE_INT_RGB);
	}

	public boolean canMove(Collider tile, Enums.Direction dir, int dist) {
		if (hasCollider) return c.canMove(tile, dir, dist);
		return true;
	}

	/* (non-Javadoc)
	 * @see engine.Collider#blockedFrom(engine.Enums.Direction)
	 */
	@Override
	public boolean blockedFrom(Direction dir) {
		if (hasCollider) return c.blockedFrom(dir);
		return false;
	}
}

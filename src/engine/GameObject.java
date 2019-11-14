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

import engine.Camera.Direction;
import geo.Coord;

/**
 * @author Alex
 *
 */
public class GameObject implements Drawable{
	protected Coord location;
	private int[] movementOffset;
	private Timer offsetTimer;
	
	
	public GameObject(Coord c) {
		location = c;
		movementOffset = new int[] {0, 0};
	}
	
	public Coord getLocation() {
		return location;
	}
	
	public void setLocation(Coord location) {
		this.location = location;
	}
	
	public void move(Camera.Direction dir, int[] unitPixels, int speed) {
		int[] direction_fac = Camera.direction_factor.get(dir);
		movementOffset[0] += direction_fac[0] * unitPixels[0];
		movementOffset[1] += direction_fac[1] * unitPixels[1];
		System.out.println("" + movementOffset[0] + " " + movementOffset[1]);
		location = location.add(Coord.newCoord(direction_fac[0], direction_fac[1]));
		animateShift(dir, speed);
	}
	
	public int[] getMovementOffset() {
		return movementOffset;
	}
	
	public void animateShift(Direction d, int speed) {
		if (offsetTimer == null) {
			offsetTimer = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("" + movementOffset[0] + " " + movementOffset[1]);
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
		return 0;
	}
	
	public double getWidth() {
		return 0;
	}

	public Image getImage() {
		return new BufferedImage(0,0, BufferedImage.TYPE_INT_RGB);
	}
}

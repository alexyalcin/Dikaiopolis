/**
 * UIElement.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import geo.Coord;

/**
 * @author Alex
 *
 */
public abstract class UIElement implements CustomListener{
	public int width, height;
	public Point loc;
	Point previousMouse;
	Point currentMouse;
	
	public UIElement() {
		previousMouse = new Point (-1, -1);
		currentMouse = new Point(-1, -1);
	}

	public abstract Image getImage();
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public Point getLoc() {
		return loc;
	}
	/* (non-Javadoc)
	 * @see ui.CustomListener#getPreviousMouse()
	 */
	@Override
	public Point getPreviousMouse() {
		return previousMouse;
	}
	/* (non-Javadoc)
	 * @see ui.CustomListener#getCurrentMouse()
	 */
	@Override
	public Point getCurrentMouse() {
		return currentMouse;
	}
	/* (non-Javadoc)
	 * @see ui.CustomListener#setPreviousMouse(java.awt.Point)
	 */
	@Override
	public void setPreviousMouse(Point mouse) {
		previousMouse = mouse;
		
	}
	/* (non-Javadoc)
	 * @see ui.CustomListener#setCurrentMouse(java.awt.Point)
	 */
	@Override
	public void setCurrentMouse(Point mouse) {
		currentMouse = mouse;
	}

}

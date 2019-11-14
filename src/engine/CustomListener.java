/**
 * CustomListener.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Point;

/**
 * @author Alex
 *
 */
public interface CustomListener {
	public Point getLoc();
	public int getHeight();
	public int getWidth();
	
	public Point getPreviousMouse();
	public Point getCurrentMouse();
	public void setPreviousMouse(Point mouse);
	public void setCurrentMouse(Point mouse);
	
	//define
	public  void onMouseEnter();
	public void onMouseExit();
	public void onMouseClick();
	
	public default boolean mouseEntered() {
		return (!mouseOver(getPreviousMouse()) && mouseOver(getCurrentMouse()));
	}
	public default boolean mouseExited() {
		return (mouseOver(getPreviousMouse()) && !mouseOver(getCurrentMouse()));
	}
	
	public default boolean mouseOver(Point mouse) {
		return (mouse.x > getLoc().x && mouse.x < getWidth() + getLoc().x
				&& mouse.y > getLoc().y && mouse.y < getLoc().y + getHeight());
	}
	
	public default void mouseAt(Point p) {
		setPreviousMouse(getCurrentMouse());
		setCurrentMouse(p);
		if (mouseEntered()) {
			onMouseEnter();
		}
		if (mouseExited()) {
			onMouseExit();
		}
	}
	
	public default void mouseClicked() {
		if (mouseOver(getCurrentMouse())) {
			onMouseClick();
		}
	}

}

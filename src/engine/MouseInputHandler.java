/**
 * InputHandler.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Dimension;
import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex
 *
 */
public class MouseInputHandler implements MouseListener {
	
	public static Point mouse_location;
	EngineCombiner engine;
	
	public Set<CustomListener> toListen;
	
	public MouseInputHandler(EngineCombiner engine) {
		this.engine = engine;
		toListen = new HashSet<CustomListener>();
		updateMouseLocation();
	}
	
	public void updateMouseLocation() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		Point topLeft;
		try {
			topLeft = engine.getLocationOnScreen();
		} catch(IllegalComponentStateException i) {
			topLeft = new Point (-1, -1);
		}
		mouse_location = new Point(mouse.x - topLeft.x, mouse.y - topLeft.y);
	}
	
	public void update() {
		for (CustomListener m : toListen) {
			m.mouseAt(mouse_location);
		}
	}
	
	public void add(CustomListener m) {
		toListen.add(m);
	}
	
	public void addAll(Collection<CustomListener> m) {
		toListen.addAll(m);
	}
	
	public void remove(CustomListener m) {
		System.out.println(m);
		toListen.remove(m);
	}
	
	public void removeAll(Collection<CustomListener> m) {
		toListen.removeAll(m);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		for (CustomListener m : toListen) {
			m.mouseClicked();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		//doNothing
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		//doNothing
	}

}

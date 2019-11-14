/**
 * MainController.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import Items.Sword;
import geo.Coord;
import geo.MappedTileBoard;
import engine.Camera;
import engine.GameObject;

/**
 * @author Alex
 *
 */
public class MainController implements KeyListener {
	Camera camera;
	GameObject character;
	int[] tileDims;

	public MainController(Camera c) {
		tileDims = c.getTileDims();
		camera = c;
		character = new Sword(1, 100);
		character.setLocation(Coord.newCoord(5, 5));
		c.addObject(character);
		
		GameObject sword2 = new Sword(0, 100);
		sword2.setLocation(Coord.newCoord(8, 8));
		c.addObject(sword2);
		c.setTarget(character);
		c.followTarget();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'w') {
			character.move(Camera.Direction.DOWN, tileDims, 3);
		} else if (key == 's') {
			character.move(Camera.Direction.UP, tileDims, 3);
		} else if (key == 'a') {
			character.move(Camera.Direction.RIGHT, tileDims, 3);
		} else if (key == 'd') {
			character.move(Camera.Direction.LEFT, tileDims, 3);
		}
		camera.followTarget();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}

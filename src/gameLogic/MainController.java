/**
 * MainController.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import Items.PlayerCharacter;
import Items.Sword;
import geo.Coord;
import geo.MappedTileBoard;
import geo.TileMap;
import tiles.GameTile;
import engine.Camera;
import engine.Enums;
import engine.GameObject;

/**
 * @author Alex
 *
 */
public class MainController implements KeyListener {
	Camera camera;
	TileMap tiles;
	GameObject character;
	int[] tileDims;

	public MainController(Camera c) {
		tileDims = c.getTileDims();
		camera = c;
		character = new PlayerCharacter();
		character.setLocation(Coord.newCoord(5, 5));
		c.addObject(character);
		tiles = c.getTileBackground().getMap();
		
		GameObject sword2 = new Sword(1, 100);
		sword2.setLocation(Coord.newCoord(11, 8));
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
		Enums.Direction d = null;
		if (key == 'w') {
			d = Enums.Direction.DOWN;
		} else if (key == 's') {
			d = Enums.Direction.UP;
		} else if (key == 'a') {
			d = Enums.Direction.RIGHT;
		} else if (key == 'd') {
			d = Enums.Direction.LEFT;
		}
		
		if (d != null) {
			GameTile t1 = (tiles.getTile(character.getLocation().x() + Enums.direction_factor.get(d)[0],
					character.getLocation().y() + Enums.direction_factor.get(d)[1]));
			GameTile t = (tiles.getTile(character.getLocation().x(), character.getLocation().y()));
			if (!character.canMove(t1, d, 1) || !(character.canMove(t, d, 0))) {
				return;
			}
			character.move(d, tileDims, 3);
		}
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

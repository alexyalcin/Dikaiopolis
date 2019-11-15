/**
 * MainController.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import characters.PlayerCharacter;
import engine.Camera;
import engine.EngineCombiner;
import engine.Enums;
import engine.gameobjects.GameObject;
import engine.gameobjects.GameTile;
import engine.gameobjects.Structure;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;
import engine.geo.TileMap;
import engine.physics.PhysicsHandler;
import items.Sword;

/**
 * @author Alex
 *
 */
public class MainController implements KeyListener {
	Camera camera;
	TileMap tiles;
	GameObject character;
	EngineCombiner engine;
	PhysicsHandler physics;
	
	int[] tileDims;

	public MainController(EngineCombiner comb) {
		engine = comb;
		physics = engine.getPhysics();
		this.camera = engine.getCamera();
		tileDims = camera.getTileDims();
		character = new PlayerCharacter(Coord.newCoord(9, 9));
		engine.addObject(character);
		GameObject gate = new Structure(Coord.newCoord(10, 12), "assets/structures/gate.xml");
		engine.addObject(gate);
		tiles = camera.getTileBackground().getMap();
		
		GameObject sword2 = new Sword(1, 100);
		sword2.setLocation(Coord.newCoord(11, 8));
		engine.addObject(sword2);
		camera.setTarget(character);
		camera.followTarget();
		camera.setTarget(gate);
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
//			GameTile t1 = (tiles.getTile(character.getLocation().x() + Enums.direction_factor.get(d)[0],
//					character.getLocation().y() + Enums.direction_factor.get(d)[1]));
//			GameTile t = (tiles.getTile(character.getLocation().x(), character.getLocation().y()));
//			if (!character.canMove(t1, d, 1) || !(character.canMove(t, d, 0))) {
//				return;
//			}
			if (physics.canMove(character, d)) {
				character.move(d, tileDims, 3);
			}
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

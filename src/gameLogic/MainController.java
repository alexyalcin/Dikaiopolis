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
import java.util.ArrayList;
import java.util.List;

import characters.PlayerCharacter;
import engine.Camera;
import engine.EngineCombiner;
import engine.Enums;
import engine.GameEventHandler;
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
	EngineCombiner engine;

	GameObject gate;
	GameObject character;

	private Enums.Direction lastKeyDown = null;
	Enums.Direction toMove = null;
	
	private ArrayList<Enums.Direction> keysDown;

	public MainController(EngineCombiner comb) {
		engine = comb;		
		keysDown = new ArrayList<Enums.Direction>();
				
		character = new PlayerCharacter(Coord.newCoord(0, 0));
		engine.addObject(character);
		engine.getCamera().setTarget(character);
		gate = new Structure(Coord.newCoord(1, 4), "assets/structures/gate.xml");

		engine.getEvents().add(new CollisionEvent(engine.getPhysics(), gate, character) {
			@Override
			public void performAction() {
				engine.initialize_map("src/bridge_map.txt");
				engine.addObject(character);
				engine.getCamera().setTarget(character);
			}
		});

		engine.addObject(gate);
		//engine.removeObject(gate);
		
		GameObject sword2 = new Sword(1, 100);
		sword2.setLocation(Coord.newCoord(11, 8));
		engine.addObject(sword2);
		engine.enableUI();
		engine.getCamera().setTarget(gate);
		//engine.enableUI();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
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
			if (keysDown.contains(d)) {
				keysDown.remove(d);
			}
			keysDown.add(0, d);
			lastKeyDown = d;
		}
	}
	
	

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
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
		if (keysDown.contains(d)) {
			keysDown.remove(d);
		}
		if (keysDown.size() == 0) {
			lastKeyDown = null;
		} else {
			lastKeyDown = keysDown.get(0);
		}
	}
	
	public void move(Enums.Direction dir, int[] d, double s) {
		System.out.println(engine.getPhysics().collides(character, gate));
		character.move(dir, d, s);
	}
	
	/**
	 * 
	 */
	public void update() {
		toMove = lastKeyDown;
		if (toMove == null) return;
		if (!character.isMoving()) {
			if (engine.getPhysics().canMove(character, toMove)) {
				if (toMove != null) move(toMove, engine.getCamera().getTileDims(), 4.5 * (9.0 / EngineCombiner.DEFAULT_HEIGHT) * EngineCombiner.DEFAULT_SCREEN_WIDTH / 750);
			}
		}
	}
	
}

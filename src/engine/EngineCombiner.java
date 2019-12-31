/**
 * EngineCombiner.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

import engine.gameobjects.GameObject;
import engine.gameobjects.GameTile;
import engine.geo.Coord;
import engine.geo.TileMap;
import engine.physics.PhysicsHandler;
import engine.ui.UIElement;
import engine.ui.UIHandler;

/**
 * @author Alex
 *
 */
public class EngineCombiner extends JPanel {
	
	public static final int DEFAULT_SCREEN_WIDTH = 900;
	public static final int DEFAULT_SCREEN_HEIGHT = 900;
	public static final int DEFAULT_HEIGHT = 30;
	public static final int DEFAULT_WIDTH = 30;
	
	int screen_width, screen_height;
	int height, width;
	
	public boolean game_paused = false;
	
	Camera camera;
	UIHandler ui;
	TileMap tiles;
	MouseInputHandler input;
	PhysicsHandler physics;
	GameEventHandler events;
	
	private Set<GameObject> objects;
	
	public EngineCombiner(String map) {
		screen_width = DEFAULT_SCREEN_WIDTH;
		screen_height = DEFAULT_SCREEN_HEIGHT;
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		input = new MouseInputHandler(this);
		ui = new UIHandler(EngineCombiner.DEFAULT_SCREEN_WIDTH, EngineCombiner.DEFAULT_SCREEN_HEIGHT, camera, objects);
		disableUI();
		for (UIElement u : ui.getUIElements()) {
			input.add(u);
		}
		initialize_map(map);
		setPreferredSize(new Dimension(screen_width, screen_height));
	}
	
	public void initialize_map(String map) {
		this.addMouseListener(input);
		objects = new HashSet<GameObject>();
		tiles = new TileMap(map);
		camera = new Camera(tiles, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		camera.setObjectsReference(objects);
		physics = new PhysicsHandler();
		events = new GameEventHandler();
		for (GameObject o : objects) {
			physics.add(o);
		}
		for (int x1 = -5; x1 < tiles.getWidth() + 5; x1++) {
			for (int y1 = -5; y1 < tiles.getHeight() + 5; y1++) {
				GameTile c = tiles.getTile(x1, y1);
				physics.add(tiles.getTile(c.getLocation().x(), c.getLocation().y()));
			}
		}
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public UIHandler getui() {
		return ui;
	}
	
	public void disableUI() {
		Set<UIElement> uielements = ui.getUIElements();
		for (CustomListener u: uielements) {
			input.remove(u);
		}
		ui.isActive = false;
	}
	
	public void enableUI() {
		ui.isActive = true;
	}
	
	public PhysicsHandler getPhysics() {
		return physics;
	}
	
	public GameEventHandler getEvents() {
		return events;
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
		physics.add(o);
	}
	
	public void addObjects(Iterable<GameObject> o) {
		for (GameObject object : o) {
			addObject(object);
		}
	}
	
	public void removeObject(GameObject o) {
		objects.remove(o);
		physics.remove(o);
	}
	
	public void update() {
		events.update();
		input.updateMouseLocation();
		input.update();
	}
	
	@Override
	public void paintComponent (Graphics g) {
		// Super class paintComponent will take care of 
		// painting the background.
		Image cam = camera.paintToImage();
		g.drawImage(cam, 0, 0, screen_width, screen_height, null);
		if (ui.isActive) {
			Image ui_image = ui.paintToImage();
			g.drawImage(ui_image, 0, 0, screen_width, screen_height, null);
		}
	}

}

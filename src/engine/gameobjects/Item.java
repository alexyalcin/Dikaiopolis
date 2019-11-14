/**
 * Item.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.gameobjects;

import java.awt.Image;

import engine.geo.Coord;
import engine.physics.Occupies;
import items.Sword;

/**
 * @author Alex
 *
 */
public abstract class Item extends GameObject implements Occupies {
	
	/**
	 * @param c
	 */
	public Item() {
		super(Coord.newCoord(-1, -1));
		height = 1;
		width = 1;
		// TODO Auto-generated constructor stub
	}

	public static Item createItem(String type, int model) {
		if (type.equals("Sword")) {
			return new Sword(model, 100);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see game_assets.Occupies#getType()
	 */
	@Override
	public String getType() {
		return "Item";
	}

}

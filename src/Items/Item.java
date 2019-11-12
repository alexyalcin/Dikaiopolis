/**
 * Item.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package Items;

import java.awt.Image;

import game_assets.Occupies;

/**
 * @author Alex
 *
 */
public abstract class Item implements Occupies {
	
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

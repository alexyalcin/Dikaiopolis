/**
 * NullTile.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.gameobjects;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import engine.Enums;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;
import engine.physics.Collider;
import engine.physics.Occupies;

/**
 * @author Alex
 *
 */
public class NullTile extends GameTile {
	
	private static Image bg = null;
	
	private static Image getBG() {
		if ((bg == null)) {
			bg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
			bg.getGraphics().setColor(Color.BLACK);
			bg.getGraphics().drawRect(0, 0, 50, 50);
		}
		return bg;
	}

	/**
	 * @param l
	 * @param o
	 * @param bg
	 */
	public NullTile(Coord l) {
		super(l, null, getBG());
		this.addCollider(new Enums.Direction[] {Enums.Direction.UP, Enums.Direction.DOWN, Enums.Direction.RIGHT, Enums.Direction.LEFT});
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tiles.GameTile#getType()
	 */
	@Override
	public String getType() {
		return "Null";
	}

}

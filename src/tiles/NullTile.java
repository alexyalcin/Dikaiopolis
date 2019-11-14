/**
 * NullTile.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import engine.Occupies;
import geo.Coord;
import geo.MappedTileBoard;

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

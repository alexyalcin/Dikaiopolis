/**
 * RockTile.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Image;

import engine.gameobjects.GameObject;
import engine.gameobjects.GameTile;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;
import engine.physics.Occupies;

/**
 * @author Alex
 *
 */
public class RockTile extends GameTile {

	private static final String bg_loc = "assets/bg2.jpg";
	private static Image bg_image;
	private static boolean image_imported = false;
	
	private static Image getBG() {
		if (!image_imported) {
			bg_image = MappedTileBoard.importImage(bg_loc);
			image_imported = true;
		}
		return bg_image;
	}

	public RockTile(Coord l, GameObject[] o) {
		super(l, o, getBG());
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tiles.GameTile#getType()
	 */
	@Override
	public String getType() {
		return "Rock";
	}

}

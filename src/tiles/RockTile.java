/**
 * RockTile.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Image;

import game_assets.Occupies;
import geo.Coord;
import geo.MappedTileBoard;

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
			bg_image = MappedTileBoard.importBG(bg_loc);
			image_imported = true;
		}
		return bg_image;
	}

	public RockTile(Coord l, Occupies[] o) {
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

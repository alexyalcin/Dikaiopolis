/**
 * TileFactory.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import game_assets.Occupies;
import geo.Coord;

/**
 * @author Alex
 *
 */
public class TileFactory {
	public static GameTile createTile(Coord c, Occupies[] o, int id) {
		if (id == 0) {
			return new GrassTile(c, o);
		} else if (id == 1) {
			return new SandTile(c, o);
		} else if (id == 3) {
			return new RockTile(c, o);
		}
		return null;
	}

}

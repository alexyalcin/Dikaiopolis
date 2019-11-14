/**
 * TileFactory.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import engine.gameobjects.GameObject;
import engine.gameobjects.GameTile;
import engine.geo.Coord;
import engine.physics.Occupies;

/**
 * @author Alex
 *
 */
public class TileFactory {
	public static GameTile createTile(Coord c, GameObject[] o, int id) {
		if (id == 0) {
			return new GrassTile(c, o);
		} else if (id == 1) {
			return new SandTile(c, o);
		} else if (id == 2) {
			return new RockTile(c, o);
		} else if (id ==3) {
			return new BridgeTile(c, o);
		}
		return null;
	}

}

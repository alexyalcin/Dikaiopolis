/**
 * Collider.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.util.HashSet;
import java.util.Set;

import geo.Coord;
import tiles.CollisionTile;

/**
 * @author Alex
 *
 */
public interface Collider {
	public Coord getLocation();

	public default boolean collides(Collider item) {
		return item.getLocation() == getLocation();
	}
	
	public default boolean wouldCollide(Collider item, Enums.Direction dir, int dist) { 
		return (item.getLocation().add(Coord.newCoord(dist * Enums.direction_factor.get(dir)[0], 
				dist * Enums.direction_factor.get(dir)[1]))) == getLocation();
	}
	
	public boolean canMove(Collider tile, Enums.Direction dir, int dist);
	
	public boolean blockedFrom(Enums.Direction dir);
}

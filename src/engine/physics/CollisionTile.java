/**
 * CollisionTile.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.physics;

import java.util.HashSet;
import java.util.Set;

import engine.Enums;
import engine.Enums.Direction;
import engine.gameobjects.GameObject;
import engine.geo.Coord;

/**
 * @author Alex
 *
 */
public class CollisionTile implements Collider {
	
	private Set<Enums.Direction> blocked;
	GameObject parent;
	
	public CollisionTile(GameObject parent, Coord loc, Enums.Direction[] blocked_sides) {
		this.parent = parent;
		blocked = new HashSet<Enums.Direction>();
		for (Enums.Direction s: blocked_sides) {
			blocked.add(s);
		}
	}
	@Override
	public Coord getLocation() {
		return parent.getLocation();
	}
	
	@Override
	public boolean canMove(Collider tile, Enums.Direction dir, int dist) {
		int xDist = getLocation().x() - tile.getLocation().x();
		int yDist = getLocation().y() - tile.getLocation().y();
		
		for (Enums.Direction d : blocked) {
			if (wouldCollide(tile, d, dist) && dir == Enums.getOpposite.get(d) && tile.blockedFrom(d)) {
				return false;
			}
		}
		return true;
	}

	public boolean blockedFrom(Direction dir) {
		return (blocked.contains(Enums.getOpposite.get(dir)));
	}
	/* (non-Javadoc)
	 * @see engine.Collider#blockedFrom(engine.Enums.Direction)
	 */

}

/**
 * PhysicsHandler.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.physics;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.physics.Collider;
import engine.CustomListener;
import engine.Enums;
import engine.geo.Coord;

/**
 * @author Alex
 *
 */
public class PhysicsHandler {
	public Map<Coord, Set<Collider>> colliders;
	
	public PhysicsHandler() {
		colliders = new HashMap<Coord, Set<Collider>>();
	}
	
	public void add(Collider m) {
		for (int x = 0; x < (int) m.getWidth(); x++) {
			for (int y = 0; y < (int) m.getHeight(); y++) {
				Coord loc = m.getLocation().add(Coord.newCoord(x, y));
				if (!colliders.containsKey(loc)) {
					colliders.put(loc, new HashSet<Collider>());
				}
				Collider c = m.getColliderAt(m.getLocation().add(Coord.newCoord(x, y)).x(), m.getLocation().add(Coord.newCoord(x, y)).y());
				if (c!= null) {
					colliders.get(loc).add(c);

				}
			}
		}
	}
	
	public void addAll(Collection<Collider> m) {
		for (Collider c : m) {
			add(c);
		}
	}
	
	public void remove(Collider m) {
		for (int x = 0; x < (int) m.getWidth(); x++) {
			for (int y = 0; y < (int) m.getHeight(); y++) {
				Coord loc = m.getLocation().add(Coord.newCoord(x, y));
				if (!colliders.containsKey(loc)) {
					colliders.put(loc, new HashSet<Collider>());
				}
				Collider c = m.getColliderAt(m.getLocation().add(Coord.newCoord(x, y)).x(), m.getLocation().add(Coord.newCoord(x, y)).y());
				if (c!= null) {
					colliders.get(loc).remove(c);
				}
			}
		}	
	}
	
	public void removeAll(Collection<Collider> m) {
		for (Collider c : m) {
			remove(c);
		}
	}
	
	public boolean canMove(Collider c1, Enums.Direction d) {
		try {
			Set<Collider> onNextTile = new HashSet<Collider>();
			onNextTile.addAll(colliders.get(c1.getLocation().add(Coord.newCoord(
					Enums.direction_factor.get(d)[0], Enums.direction_factor.get(d)[1]))));
			Set<Collider> onThisTile = new HashSet<Collider>();
			onThisTile.addAll(colliders.get(c1.getLocation()));
			onThisTile.remove(c1);
			for (Collider t : onNextTile) {
				if (!c1.canMove(t, d, 1)) return false;
			}
			for (Collider t : onThisTile) {
				if (!c1.canMove(t, d, 0)) return false;
			}
			return true;
		} catch (NullPointerException i) {
			return false;
		}

	}
	
	public boolean collides(Collider c1, Collider c2) {
		if (!(colliders.get(c1.getLocation()).contains(c1.getColliderAt(c1.getLocation().x(), c1.getLocation().y())) || 
				colliders.get(c2.getLocation()).contains(c2.getColliderAt(c2.getLocation().x(), c2.getLocation().y())))) {
			return false;
		}
		return (c1.collides(c2) || c2.collides(c1));
	}
}

/**
 * Structure.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package items;

import engine.Enums.Direction;
import engine.geo.Coord;
import engine.physics.Collider;

/**
 * @author Alex
 *
 */
public class Structure implements Collider {
	
	public Structure (Coord c1) {
		
	}

	/* (non-Javadoc)
	 * @see engine.physics.Collider#getLocation()
	 */
	@Override
	public Coord getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see engine.physics.Collider#canMove(engine.physics.Collider, engine.Enums.Direction, int)
	 */
	@Override
	public boolean canMove(Collider tile, Direction dir, int dist) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see engine.physics.Collider#blockedFrom(engine.Enums.Direction)
	 */
	@Override
	public boolean blockedFrom(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

}

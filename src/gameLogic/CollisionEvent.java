/**
 * CollisionEvent.java 1.0 Nov 15, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

import engine.Enums.Direction;
import engine.geo.Coord;
import engine.physics.Collider;
import engine.physics.PhysicsHandler;

/**
 * @author Alex
 *
 */
public abstract class CollisionEvent implements GameEvent, Collider{
	private Collider c1, c2;
	private PhysicsHandler physics;
	
	public CollisionEvent(PhysicsHandler physics, Collider c1, Collider c2) {
		this.physics = physics;
		this.c1 = c1;
		this.c2 = c2;
	}

	/* (non-Javadoc)
	 * @see gameLogic.GameEvent#conditionSatisfied()
	 */
	@Override
	public boolean conditionSatisfied() {
		return physics.collides(c1, c2);
	}

	/* (non-Javadoc)
	 * @see gameLogic.GameEvent#performAction()
	 */
	@Override
	public abstract void performAction();

	@Override
	public Coord getLocation() {
		return c1.getLocation();
	}

	/* (non-Javadoc)
	 * @see engine.physics.Collider#canMove(engine.physics.Collider, engine.Enums.Direction, int)
	 */
	@Override
	public boolean canMove(Collider tile, Direction dir, int dist) {
		return true;
	}

	/* (non-Javadoc)
	 * @see engine.physics.Collider#blockedFrom(engine.Enums.Direction)
	 */
	@Override
	public boolean blockedFrom(Direction dir) {
		return false;
	}

}

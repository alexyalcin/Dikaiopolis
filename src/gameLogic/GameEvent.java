/**
 * GameEvent.java 1.0 Nov 15, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

/**
 * @author Alex
 *
 */
public interface GameEvent {
	public boolean conditionSatisfied();
	public void performAction();
}

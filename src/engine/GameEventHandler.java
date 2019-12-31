/**
 * GameEventHandler.java 1.0 Nov 15, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Event;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.geo.Coord;
import engine.physics.Collider;
import gameLogic.GameEvent;

/**
 * @author Alex
 *
 */
public class GameEventHandler {
		public Set<GameEvent> events;
		
		public GameEventHandler() {
			events = new HashSet<GameEvent>();
		}
		
		public void add(GameEvent m) {
			events.add(m);
		}
		
		public void addAll(Collection<GameEvent> m) {
			for (GameEvent e : m) {
				add(e);
			}
		}
		
		public void remove(GameEvent m) {
			if (events.contains(m)) {
				events.remove(m);
			}
		}
		
		public void removeAll(Collection<GameEvent> m) {
			for (GameEvent e : m) {
				events.remove(e);
			}
		}
		
		public void update() {
			for (GameEvent event: events) {
				if (event.conditionSatisfied()) {
					System.out.println("event called");
					event.performAction(); 
					events.remove(event);
				}
			}
		}
}

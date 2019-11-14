/**
 * Enums.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 *
 */
public class Enums {
	public enum Side {LEFT, RIGHT, TOP, BOTTOM};
	public static  enum Direction {LEFT, RIGHT, UP, DOWN};
	public static Map<Direction, int[]> direction_factor;
	public static Map<Direction, Direction> getOpposite;
			static {
				getOpposite = new HashMap<Direction, Direction>();
				getOpposite.put(Direction.LEFT, Direction.RIGHT);
				getOpposite.put(Direction.RIGHT, Direction.LEFT);
				getOpposite.put(Direction.DOWN, Direction.UP); 
				getOpposite.put(Direction.UP, Direction.DOWN);
				
				direction_factor = new HashMap<Direction, int[]>();
				direction_factor.put(Direction.UP, new int[] {0, 1});
				direction_factor.put(Direction.DOWN, new int[] {0, -1});
				direction_factor.put(Direction.RIGHT, new int[] {-1, 0});
				direction_factor.put(Direction.LEFT, new int[] {1, 0});
			}
}

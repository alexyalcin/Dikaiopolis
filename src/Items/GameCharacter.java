/**
 * Character.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package Items;

import java.awt.Image;

import engine.Enums;
import engine.GameObject;

/**
 * @author Alex
 *
 */
public abstract class GameCharacter extends GameObject{
	protected Image[] sprite_sheet;
	protected int current_phase;
	protected int phase_dir;
	
	protected String name;
	protected int level;
	protected int health;
	
	public GameCharacter() {
		current_phase = 0;
		phase_dir = 1;
		addCollider(new Enums.Direction[] {Enums.Direction.UP, Enums.Direction.DOWN, Enums.Direction.RIGHT, Enums.Direction.LEFT});
	}
	
	
	public abstract boolean isKillable();
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void nextPhase() {
		if (sprite_sheet.length <= 1) {
			return;
		}
		current_phase += phase_dir;
		if (current_phase - 1 == sprite_sheet.length || current_phase == 0) {
			phase_dir *= -1;
		}
	}
	
	@Override
	public Image getImage() {
		return sprite_sheet[current_phase];
	}
	
}

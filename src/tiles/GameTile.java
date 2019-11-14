/**
 * GameTile.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import engine.GameObject;
import engine.Occupies;
import geo.Coord;

/**
 * @author Alex
 *
 */
public abstract class GameTile extends GameObject implements Tile {
	Set<Occupies> onTile;
	Image background;
	
	public GameTile(Coord l, Occupies[] o, Image bg) {
		super(l);
		onTile = new TreeSet<Occupies>();
		background = bg;
		if (o == null) {
			return;
		}
		for (Occupies item : o) {
			onTile.add(item);
		}
	}
	
	public abstract String getType();
	
	public String toString() {
		return getType() + " tile. Loc: " + getX() + "," + getY();
	}
	/* (non-Javadoc)
	 * @see geo.Tile#getX()
	 */
	@Override
	public int getX() {
		return location.x();
	}

	/* (non-Javadoc)
	 * @see geo.Tile#getY()
	 */
	@Override
	public int getY() {
		return location.y();
	}
	
	public void addObject(Occupies o) {
		onTile.add(o);
	}
	
	public boolean removeObject(Occupies o) {
		if (onTile.contains(o)) {
			onTile.remove(o);
			return true;
		}
		return false;
	}
	
	Set<Occupies> getOnTile() {
		return onTile;
	}
	
	public List<Image> getImages() {
		List<Image> images = new ArrayList<Image>();
		images.add(background);
		for (Occupies i : getOnTile()) {
			images.add(i.getImage());
		}
		return images;
	}
	
	

}

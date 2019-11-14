/**
 * TileHighlight.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import engine.gameobjects.GameObject;
import engine.geo.Coord;

/**
 * @author Alex
 *
 */
public class TileHighlight extends GameObject {
	private static Image hl;
	static {
		hl = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		hl.getGraphics().setColor(Color.YELLOW);
		hl.getGraphics().drawRect(0, 0, 100, 100);
	}
	public TileHighlight(Coord tile) {
		super(tile);
		this.width = 1;
		this.height = 1;
		
	}
	
	public int getPriority() {
		return 99;
	}
	
	@Override
	public Image getImage() {
		return hl;
	}
}

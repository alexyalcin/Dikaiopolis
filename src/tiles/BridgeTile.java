/**
 * BridgeTile.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;

import engine.Enums;
import engine.GameObject;
import engine.Occupies;
import geo.Coord;
import geo.MappedTileBoard;

/**
 * @author Alex
 *
 */
public class BridgeTile extends GameTile{
	
	private static final String bg_loc = "assets/bridge.png";
	private static final String bg_loc2 = "assets/bg3.jpg";
	
	private static Image bg_image;
	private static boolean image_imported = false;
	
	private static Image getBG() {
		if (!image_imported) {
			bg_image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			Image bg_image1 = MappedTileBoard.importImage(bg_loc2);
			Image bg_image2 = MappedTileBoard.importImage(bg_loc);
			bg_image.getGraphics().drawImage(bg_image1, 0, 0, 100, 100, null);
			bg_image.getGraphics().drawImage(bg_image2, -10, 0, 150, 125, null);
			image_imported = true;
		}
		return bg_image;
	}

	/**
	 * @param l
	 * @param o
	 * @param bg
	 */
	public BridgeTile(Coord l, GameObject[] o) {
		super(l, o, getBG());
		addCollider(new Enums.Direction[] {Enums.Direction.UP, Enums.Direction.DOWN});
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tiles.GameTile#getType()
	 */
	@Override
	public String getType() {
		return "Bridge";
	}

}


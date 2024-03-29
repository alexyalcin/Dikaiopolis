/**
 * PlayerCharacter.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package characters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import engine.file.XMLReader;
import engine.gameobjects.GameCharacter;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;

/**
 * @author Alex
 *
 */
public class PlayerCharacter extends GameCharacter {

	@Override
	public boolean isKillable() {
		return true;
	}
	
	public PlayerCharacter(Coord c) { // for testing
		this("savegame.txt", c);
	}
	
	public PlayerCharacter(String savefile, Coord c) {
		super(c);
		height = 1;
		width = 1;
		
		XMLReader reader = new XMLReader(new File(savefile));
		name = reader.getTag("name");
		level = Integer.parseInt(reader.getTag("level"));
		
		//This will change once sprite sheets are implemented;
		String imgloc = reader.getTag("sprite");
		sprite_sheet = new Image[1];
		System.out.println(imgloc);
		sprite_sheet[0] = MappedTileBoard.importImage(imgloc);
	}
	
	@Override
	public int getPriority() {
		return 15;
	}

}

/**
 * Sword.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package items;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import engine.gameobjects.Item;
import engine.geo.Coord;
import engine.physics.Occupies;

/**
 * @author Alex
 *
 */
public class Sword extends Item {
	
	private static final String sword_info = "assets/sword_info.txt";
	Image image;
	String name;
	double strength;
	double speed;
	int endurance;
	
	private static Map<Integer, Image> imported_swords = null;
	public Sword(int model) {
		this(model, 100);
	}
		
	public Sword(int model, int endurance) {
		if (imported_swords == null) {
			imported_swords = new HashMap<Integer, Image>();
		}
		File info_file = new File(sword_info);
		Scanner sc = null; 
		try {
			sc = new Scanner(info_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < model; i++) {
			sc.nextLine();
		}
		String info = sc.nextLine();
		
		Scanner parse_info = new Scanner(info);
		parse_info.useDelimiter(",");
		name = parse_info.next();
		strength = parse_info.nextDouble();
		speed = parse_info.nextDouble();
		if (imported_swords.containsKey(model)) {
			image = imported_swords.get(model);
		} else {
			try {
				image = ImageIO.read(new File(parse_info.next()));
				imported_swords.put(model, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.endurance = endurance;
	}

	/* (non-Javadoc)
	 * @see game_assets.Drawable#getImage()
	 */
	@Override
	public Image getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}

}

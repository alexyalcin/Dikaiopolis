/**
 * ReadMap.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package geo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Items.Item;
import tiles.GameTile;
import tiles.TileFactory;

/**
 * @author Alex
 *
 */
public class ReadMap {
	
	public static GameTile[][] getTiles(String loc) {
		int width = 0;
		int height = 0;
		File f = new File(loc);
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line1 = sc.nextLine();
		Scanner line1_scanner = new Scanner(line1);
		line1_scanner.useDelimiter(" ");
		width = line1_scanner.nextInt();
		height = line1_scanner.nextInt();
		GameTile[][] tiles = new GameTile[width][height];

		for (int x = 0; x < width; x++) {
			String next_line = sc.nextLine();
			Scanner row_scanner = new Scanner(next_line);
			row_scanner.useDelimiter(" ");
			for (int y = 0; y < height; y++)  {
				GameTile t = TileFactory.createTile(Coord.newCoord(y, x), null, row_scanner.nextInt());
				tiles[y][x] = t;
			}
		}
		boolean in_items = false;
		while (sc.hasNext()) {
			String nextLine = sc.nextLine();
			if (nextLine.contains("<Items>")) {
				in_items = true;
				continue;
			} else if (nextLine.contains("</Items>")) {
				in_items = false;
				continue;
			}
			if (in_items) {
				Scanner row_scanner = new Scanner(nextLine);
				row_scanner.useDelimiter(",");
				String type = row_scanner.next();
				int model = row_scanner.nextInt();
				int x = row_scanner.nextInt();
				int y = row_scanner.nextInt();
				tiles[x][y].addObject(Item.createItem(type, model));
			}
		}
		
		return tiles;
	}
	

}

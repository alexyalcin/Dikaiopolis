/**
 * Structure.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import engine.EngineCombiner;
import engine.Enums;
import engine.Enums.Direction;
import engine.file.XMLReader;
import engine.geo.Coord;
import engine.geo.MappedTileBoard;
import engine.physics.Collider;

/**
 * @author Alex
 *
 */
public class Structure extends GameObject {
	private String name;
	private Set<GameTile> blockSet;
	int xDim, yDim;
	
	public Structure(Coord loc, String file_loc) {
		super(loc);
		initialize(file_loc);
	}
	
	private void initialize(String file_loc) {
		XMLReader fileReader = new XMLReader(new File(file_loc));
		name = fileReader.getTag("name");
		Set<String> block_info = fileReader.getAllOfTag("block");
		blockSet = new HashSet<GameTile>();
		int xMax = 0;
		int yMax = 0;
		int xMin = 0;
		int yMin = 0;
		for (String s : block_info) {
			XMLReader string_reader = new XMLReader(s);
			String coord_string = string_reader.getTag("loc");
			Scanner coordScanner = new Scanner(coord_string);
			coordScanner.useDelimiter(",");
			int x = coordScanner.nextInt();
			xMin = Math.min(xMin, x);
			xMax = Math.max(xMax, x);
			int y = coordScanner.nextInt();
			yMin = Math.min(yMin, y);
			yMin = Math.max(yMax, y);
			GameTile t = new GameTile(Coord.newCoord(x + location.x(), y + location.y()),
					null, MappedTileBoard.importImage((string_reader.getTag("img")))) {
				
				@Override
				public String getType() {
					return null;
				}
				public Image getImage() {
					return background;
					
				}
			};
			String collider_info = string_reader.getTag("collision");
			Scanner collider_scanner = new Scanner(collider_info);
			collider_scanner.useDelimiter(",");
			List<Enums.Direction> dirs= new ArrayList<Enums.Direction>();
			if (collider_scanner.nextInt() == 1) {
				dirs.add(Enums.Direction.RIGHT);
			}
			if (collider_scanner.nextInt() == 1) {
				dirs.add(Enums.Direction.LEFT);
			}
			if (collider_scanner.nextInt() == 1) {
				dirs.add(Enums.Direction.UP);
			}
			if (collider_scanner.nextInt() == 1) {
				dirs.add(Enums.Direction.DOWN);
			}
			
			Enums.Direction[] sidesBlocked = new Enums.Direction[dirs.size()];
			for (int i = 0; i < dirs.size(); i++) {
				sidesBlocked[i] = dirs.get(i);
			}
			t.addCollider(sidesBlocked);
			blockSet.add(t);
		}
		width = xMax - xMin + 1;
		height = yMax - yMin + 1;
	}
	
	@Override
	public Image getImage() {
		Image i = new BufferedImage((int) ( 100 * width), (int) (100 * height), BufferedImage.TYPE_INT_ARGB);
		Graphics g = i.getGraphics();
		for (GameTile tile : blockSet) {
			g.drawImage(tile.getImage(), 100 * (tile.getX() - this.location.x()), 100 * (tile.getY() - this.location.y()), 100, 100, null);
			g.setColor(Color.YELLOW);
			g.drawOval( 100 * tile.getX(), 100 * tile.getY(), 10, 10);

		}
		return i;
	}
	
	@Override
	public Collider getColliderAt(int x, int y) {
		for (GameObject block: blockSet) {
			System.out.println(block.getLocation());
			if (block.getLocation().x() == x && block.getLocation().y() == y) {
				return block.getColliderAt(x, y);
			}
		}
		return null;
	}
	
	
	@Override
	public boolean canMove(Collider tile, Enums.Direction dir, int dist) {
		System.out.println("hello");
		for (GameTile t : blockSet) {
			System.out.println(t.hasCollider);
			if (!t.canMove(tile, dir, dist)) return false;
		}
		return true;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}

	public String getName() {
		return name;
	}

}

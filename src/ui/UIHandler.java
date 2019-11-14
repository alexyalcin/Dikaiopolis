/**
 * UIHandler.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import engine.Camera;
import engine.GameObject;
import geo.Coord;
import tiles.TileHighlight;

/**
 * @author Alex
 *
 */
public class UIHandler {
	Camera camera;
	private int screenw, screenh;
	
	private static Color highlight_color = Color.YELLOW;
	private Set<GameObject> objects;
	private Set<UIElement> uiElements;
	private GameObject mouseOverHighlight;
	
	public boolean isActive = false;
	
	public UIHandler(int s_w, int s_h, Camera c, Set<GameObject> o){
		objects = o;
		camera = c;
		screenw = s_w;
		screenh = s_h;
		mouseOverHighlight = new GameObject();
		uiElements = new HashSet<UIElement>();
		uiElements.add(new StartButton());
		uiElements.add(new UIButton("Hello", 300, 100, new Point(300, 300)));
	}
	
	public void setObjectsReference(Set<GameObject> objects) {
		this.objects = objects;
	}
	
	public Image paintToImage() {
		Image i = new BufferedImage(screenw, screenh, BufferedImage.TYPE_INT_ARGB);
		Graphics g = i.getGraphics();
		for (UIElement element : uiElements) {
			g.drawImage(element.getImage(), element.getLoc().x, element.getLoc().y,
					element.getWidth(), element.getHeight(), null);
		}
		return i;
	}
	
	public Set<UIElement> getUIElements() {
		return uiElements;
	}

}

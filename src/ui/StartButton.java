/**
 * StartButton.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import gameLogic.EngineCombiner;
import geo.Coord;

/**
 * @author Alex
 *
 */
public class StartButton extends UIElement {
	static Font greekfont;
	static Image button;
	
	static {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     greekfont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/greek_font.ttf"));
		     greekfont = greekfont.deriveFont(90f);
		     ge.registerFont(greekfont);
		} catch (FontFormatException | IOException e) {
		     //Handle exception
		}
	}
	
	public StartButton() {
		height = 100;
		width = 300;
		loc = new Point(EngineCombiner.DEFAULT_SCREEN_WIDTH / 2 - 250, 25);
		button = new BufferedImage(400, 200, BufferedImage.TYPE_INT_ARGB);
		Graphics g = button.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(greekfont);
		g.drawString("START", 20, 80);
	}

	/* (non-Javadoc)
	 * @see ui.UIElement#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return button;
	}

	/* (non-Javadoc)
	 * @see ui.CustomListener#onMouseEnter()
	 */
	@Override
	public void onMouseEnter() {
		Graphics g = button.getGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.setFont(greekfont);
		g.drawString("START", 20, 80);
	}

	/* (non-Javadoc)
	 * @see ui.CustomListener#onMouseExit()
	 */
	@Override
	public void onMouseExit() {
		Graphics g = button.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(greekfont);
		g.drawString("START", 20, 80);
		
	}
	
	public void onMouseClick() {
		System.out.println("Start!");
	}


}

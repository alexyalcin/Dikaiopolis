/**
 * UIButton.java 1.0 Nov 14, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Alex
 *
 */
public class UIButton extends UIElement {
	
	static Font font;
	Image button;
	
	static {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/greek_font.ttf"));
		     font = font.deriveFont(90f);
		     ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
		     //Handle exception
		}
	}
	public static Color COLOR1 = Color.BLACK;
	public static Color COLOR2 = Color.RED;
	public static Color COLOR3 = Color.WHITE;
	
	private Color color1, color2, color3;
	private String text;
	
	public UIButton(String text, int width, int height, Point l) {
		this(text, width, height, l, COLOR1, COLOR2, COLOR3);
	}
	
	public UIButton (String text, int width, int height, Point l, Color c1, Color c2, Color c3) {
		this.height = height;
		this.width = width;
		this.text = text;
		color1 = c1;
		color2 = c2;
		color3 = c3;
		
		this.loc = l;
		button = new BufferedImage(80 * text.length() + 80, 200, BufferedImage.TYPE_INT_ARGB);
		Graphics g = button.getGraphics();
		g.setColor(color1);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(color2);
		g.setFont(font);
		g.drawString(text, 20, 80);
	}

	/* (non-Javadoc)
	 * @see ui.CustomListener#onMouseEnter()
	 */
	@Override
	public void onMouseEnter() {
		Graphics g = button.getGraphics();
		g.setColor(color2);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(color3);
		g.setFont(font);
		g.drawString(text, 20, 80);
	}

	/* (non-Javadoc)
	 * @see ui.CustomListener#onMouseExit()
	 */
	@Override
	public void onMouseExit() {
		Graphics g = button.getGraphics();
		g.setColor(color1);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(color2);
		g.setFont(font);
		g.drawString(text, 20, 80);
	}

	/* (non-Javadoc)
	 * @see ui.UIElement#getImage()
	 */
	@Override
	public Image getImage() {
		return button;
	}

	/* (non-Javadoc)
	 * @see ui.CustomListener#onMouseClick()
	 */
	@Override
	public void onMouseClick() {
		System.out.println("Clicked on " + this.text);
		
	}
	

}

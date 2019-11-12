/**
 * MainController.java 1.0 Nov 12, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package gameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import geo.MappedTileBoard;

/**
 * @author Alex
 *
 */
public class MainController implements KeyListener {
	MappedTileBoard board;

	public MainController(MappedTileBoard b) {
		board = b;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'w') {
			board.shift(MappedTileBoard.Direction.DOWN);
		} else if (key == 's') {
			board.shift(MappedTileBoard.Direction.UP);
		} else if (key == 'a') {
			board.shift(MappedTileBoard.Direction.RIGHT);
		} else if (key == 'd') {
			board.shift(MappedTileBoard.Direction.LEFT);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}

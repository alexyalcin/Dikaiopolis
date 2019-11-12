package geo;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * RunGame.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */

/**
 * @author Alex
 *
 */
public class RunGame {
	
	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Dikaiopolis");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Create panel for content. Uses BorderLayout. */
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);
		
		MappedTileBoard board = new MappedTileBoard(new TileMap("src/example_map.txt"), Coord.newCoord(0, 0));
		top_panel.add(board, BorderLayout.CENTER);

		/* Pack main frame and make visible. */
		
		main_frame.pack();
		main_frame.setVisible(true);		
	}

}

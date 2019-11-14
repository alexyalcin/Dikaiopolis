package gameLogic;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import engine.Camera;


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
	
	public static Camera c; 
	static {
		c = new Camera("src/example_map.txt");

			Timer gameTimer = new Timer(1000 / 60, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.repaint();
				}
			});
			gameTimer.start();
	}
	
	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Dikaiopolis");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Create panel for content. Uses BorderLayout. */
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		
		main_frame.addKeyListener(new MainController(c));
		top_panel.add(c, BorderLayout.CENTER);

		/* Pack main frame and make visible. */
		
		main_frame.pack();
		main_frame.setVisible(true);		
	}

}
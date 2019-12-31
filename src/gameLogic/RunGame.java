package gameLogic;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import engine.Camera;
import engine.EngineCombiner;
import engine.ui.UIHandler;


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
	private static String map = "src/example_map.txt";
	
	private static MainController controller;
	public static EngineCombiner engine;
	
	static {
		engine = new EngineCombiner(map);
		controller = new MainController(engine);
			Timer gameTimer = new Timer(1000 / 60, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.update();
					engine.update();
					engine.repaint();
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
		
		main_frame.addKeyListener(controller);
		top_panel.add(engine, BorderLayout.CENTER);

		/* Pack main frame and make visible. */
		
		main_frame.pack();
		main_frame.setVisible(true);		
	}

}

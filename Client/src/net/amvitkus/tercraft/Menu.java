package net.amvitkus.drugcraft;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Starting class menu with buttons
public class Menu extends JFrame {

	private int width = 400;
	private int height = 400;

	private JPanel window = new JPanel();
	private JFrame frame = new JFrame();

	private JButton play, options, about, quit;
	private Rectangle Rplay, Roptions, Rabout, Rquit;

	private byte buttonWidth = 80;
	private byte buttonHeight = 40;

	private String title = "2D Drugcraft";

	public Menu(Component component) {
		frame.setTitle(title);
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(component);
		frame.getContentPane().add(window);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setIconImage(new ImageIcon("res/tile_icon.png").getImage());
		window.setLayout(null);

		drawButtons();

		frame.repaint();

	}

	private void drawButtons() {

		Component component = new Component();

		play = new JButton("Play");
		Rplay = new Rectangle((width / 2) - (buttonWidth / 2), 120,
				buttonWidth, buttonHeight);
		play.setBounds(Rplay);
		window.add(play);

		options = new JButton("Options");
		Roptions = new Rectangle((width / 2) - (buttonWidth / 2), 170,
				buttonWidth, buttonHeight);
		options.setBounds(Roptions);
		window.add(options);

		about = new JButton("About");
		Rabout = new Rectangle((width / 2) - (buttonWidth / 2), 220,
				buttonWidth, buttonHeight);
		about.setBounds(Rabout);
		window.add(about);

		quit = new JButton("Quit");
		Rquit = new Rectangle((width / 2) - (buttonWidth / 2), 270,
				buttonWidth, buttonHeight);
		quit.setBounds(Rquit);
		window.add(quit);

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = new Component();
				frame.dispose();
				component.start();

			}

		});

		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Options");

			}

		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("About");

			}

		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});

	}

}

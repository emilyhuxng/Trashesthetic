/** 
 * this template can be used for a start menu
 * for your final project
**/

//Imports

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

//import GameFrameTwo.MyKeyListener;

import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class GameWon extends JFrame {

	JFrame thisFrame;
	BufferedImage sb;

	// Constructor - this runs first
	GameWon() {
		super("Game Won Screen");
		this.thisFrame = this; // lol

		// configure the window
		this.setSize(900, 900);
		this.setLocationRelativeTo(null); // start the frame in the center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// Display the frame without border and invisible
		this.setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));

		// Create a Panel for stuff
		JPanel decPanel = new DecoratedPanel();
		decPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(0, 0, 0, 0));
		mainPanel.setPreferredSize(new Dimension(900, 900));

		// Create a JButton for the centerPanel
		/*
		 * try{ sb = ImageIO.read(new File("MainMenu.png")); }catch (IOException ex) {
		 * ex.printStackTrace(); }
		 */

		// Resize menu
		ImageIcon sb = new ImageIcon(getClass().getResource("Succeeded.png"));
		// ImageIcon newMap = sb.getScaledInstance(1200, 1200, Image.SCALE_DEFAULT);

		JButton startButton = new JButton(sb);
		startButton.setBackground(new Color(0, 0, 0, 0));
		startButton.setRolloverIcon(new ImageIcon(getClass().getResource("Succeeded.png")));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setFocusPainted(false);
		startButton.addActionListener(new StartButtonListener());

		MyKeyListener keyListener = new MyKeyListener();
		this.addKeyListener(keyListener);

		this.setFocusable(true);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(0, 0, 0, 0));
		bottomPanel.add(startButton);

		// Create a JButton for the centerPanel
		JLabel startLabel = new JLabel("<HTML><H1><font color='white'>Welome to some game or something</H1></HTML>");

		// Add all panels to the mainPanel according to border layout
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(startLabel, BorderLayout.CENTER);

		decPanel.add(mainPanel);
		// add the main panel to the frame
		this.add(decPanel);

		// Start the app
		this.setVisible(true);
	}

	// INNER CLASS - Overide Paint Component for JPANEL
	private class DecoratedPanel extends JPanel {

		DecoratedPanel() {
			this.setBackground(new Color(0, 0, 0, 0));
		}
		/*
		 * public void paintComponent(Graphics g) { super.paintComponent(g); Image pic =
		 * new ImageIcon( "paint.png" ).getImage(); g.drawImage(pic,0,0,null); }
		 */

	}

	// This is an inner class that is used to detect a button press
	class StartButtonListener implements ActionListener { // this is the required class definition
		public void actionPerformed(ActionEvent event) {
			// System.out.println("Starting new Game");
			thisFrame.dispose();
			// new StartingFrameTwo(); //create a new FunkyFrame (another file that extends
			// JFrame)

		}

	}

	private class MyKeyListener implements KeyListener {
		// Box area (all num has to be mutiples of moveSpace)

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // If ESC is pressed
				System.exit(0);

			}
		}

		public void keyReleased(KeyEvent e) {

		}

	}

	// Main method starts this application
	public static void main(String[] args) {
		new GameWon();

	}

}
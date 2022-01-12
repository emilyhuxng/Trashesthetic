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
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

  
class Rules extends JFrame {

  JFrame thisFrame;
  //BufferedImage sb;
  
  //Constructor - this runs first
  Rules() { 
    super("Start Screen");
    this.thisFrame = this; //lol  
    
    //configure the window
    this.setSize(900,900);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setResizable (false);
    
    //Display the frame without border and invisible
    this.setUndecorated(true);
    setBackground(new Color(0,0,0,0));
         
    //Create a Panel for stuff
    JPanel decPanel = new DecoratedPanel();
    decPanel.setBorder(new EmptyBorder(300, 350, 0, 0));

    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(0, 0, 0, 0));
    mainPanel.setPreferredSize(new Dimension(640,480));
      
    //Create a JButton for the centerPanel
    ImageIcon sb =new ImageIcon("Start Button.png");

    JButton startButton = new JButton(sb);
    startButton.setBackground(Color.WHITE);
    startButton.setRolloverIcon(new ImageIcon("Start Button Pressed.png"));
    startButton.setBorder(BorderFactory.createEmptyBorder());
    startButton.setFocusPainted(false);
    startButton.addActionListener(new StartButtonListener());
    
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0, 0, 0, 0));
    bottomPanel.add(startButton);
 
     //Create a JButton for the centerPanel
    JLabel startLabel = new JLabel("");
    
    //Add all panels to the mainPanel according to border layout
    mainPanel.add(bottomPanel,BorderLayout.NORTH);
    mainPanel.add(startLabel,BorderLayout.CENTER);
    
    decPanel.add(mainPanel);
    //add the main panel to the frame
    this.add(decPanel);
    
    //Start the app
    this.setVisible(true);
  }
  
  //INNER CLASS - Overide Paint Component for JPANEL
  private class DecoratedPanel extends JPanel {
    
    DecoratedPanel() {
      this.setBackground(new Color(0,0,0,0));
    }
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g);
      Image background = new ImageIcon("Rules.png").getImage();
      g.drawImage(background, 0, 0, this);
      
      g.setColor(Color.WHITE);
      g.setFont(new Font("Arial", Font.BOLD, 20));
      g.drawString("Use arrows to move", 125, 640);
      g.drawString("Use space to shoot/select items", 490, 725);
      g.drawString("Use P to", 515, 520);
      g.drawString("access pocket", 485, 550);
      g.drawString("Use ESC to", 660, 520);
      g.drawString("exit game", 670, 550);
      g.drawString("The purpose of the game", 75, 280);
      g.drawString("is to rid your village", 75, 305);
      g.drawString("of trash. You will need", 75, 330);
      g.drawString("to fight multiple enemies", 75, 355);
      g.drawString("to make your village", 75, 380);
      g.drawString("clean again. Store items", 75, 405);
      g.drawString("in your pockets and talk", 75, 430);
      g.drawString("to the other villagers.", 75, 455);
      g.drawString("Use the keys indicated", 75, 505);
      g.drawString("below to navigate the", 75, 530);
      g.drawString("world", 75, 555);
      g.setColor(Color.BLACK);
      g.drawRect(65, 255, 255, 310);
    }
   
  }
  //This is an inner class that is used to detect a button press
 class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  { 
      thisFrame.dispose();
      new GameFrameTwo(); //create a new FunkyFrame (another file that extends JFrame)

    }

  }
  
}
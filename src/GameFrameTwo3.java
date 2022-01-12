///* Anne Hua
// * Jan 2, 2020
// * Trash Quest
// * Main map moving character
// */
//
////Graphics &GUI imports
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.Toolkit;
//import java.awt.Graphics;
//import java.awt.Color;
//import java.awt.Image;
//import java.awt.Font;
//import javax.swing.ImageIcon;  
//import javax.swing.border.EmptyBorder;
//
//
////maybe remove
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//
////Keyboard imports
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
////Mouse imports
//import java.awt.event.MouseListener;
//import java.awt.event.MouseEvent;
//
//import java.awt.Rectangle;
//
//class GameFrameTwo extends JFrame { 
//  
//  //class variable (non-static)
//  static double x, y;
//  static GameAreaPanel gamePanel;
//  MovingBox box;
//  FrameRate frameRate;
//  Clock clock;
//  boolean direction[];
//  boolean collision;
//  boolean space;
//  int lastPressed;
//  int num;
//  String mapImage;
//  
//  Character character;
//  Villager enemy;
//  Bullet bullet[];
//  
//  Pocket pocket;
//  
//  Villager villager1;
//  Villager villager2;
//  Villager villager3;
//  Villager villager4;
//  
//  Paper paper1;
//  
//  BufferedImage map;
//  BufferedImage mainCharacter;
//  BufferedImage bulletSprite;
//  BufferedImage bulletSprite2;
//  BufferedImage bulletSprite3;
//  BufferedImage bulletSprite4;
//  BufferedImage paperSprite;
//  
//  Image newMainCharacter;
//  Image newMap;
//  Image newBulletSprite;
//  
//  //Main menu
//  boolean menu = false;
//      
//  //Inside house maps
//  boolean houseOneMap = true;
//  boolean houseTwoMap = false;
//  boolean houseThreeMap = false;
//  boolean houseFourMap = false;
//  boolean houseFiveMap = false;
//  
//  //Main map
//  boolean mainMap = false;
//      
//  //Comeplete level or not 
//  boolean houseOneComplete = true;
//  boolean houseTwoComplete = false;
//  boolean houseThreeComplete = false;
//  boolean houseFourComplete = false;
//  boolean houseFiveComplete = false;
//  
//  //Constructor - this runs first
//  GameFrameTwo() { 
//    
//    super("My Game");  
//    // Set the frame to full screen 
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    //this.setLocationRelativeTo(null); //start the frame in the center of the screen
//    this.setSize(900,900);
//    this.setUndecorated(false);  //Set to true to remove title bar
//    this.setBackground(new Color(255, 255, 255));
//    
//    frameRate = new FrameRate();
//    clock = new Clock();
//    direction = new boolean[4];
//    character = new Character(400, 400, null, 100, 100, 100);
//    enemy = new Villager(390, 450, null, 50, 50, "name");
//    bullet = new Bullet[1000];
//    num = 0;
//    collision = false;
//    
//    pocket = new Pocket();
//    
//    //teletubby names
//    villager1 = new Villager(0, 0, null, 10, 10, "Tinky-Winky");
//    villager2 = new Villager(0, 0, null, 10, 10, "Dipsy");
//    villager3 = new Villager(0, 0, null, 10, 10, "Laa Laa");
//    villager4 = new Villager(0, 0, null, 10, 10, "Po");
//    
//    villager1.setFood(new Food(0, 0, null, "lemonade", 5));
//    villager2.setFood(new Food(0, 0, null, "chocolate", 10));
//    villager3.setFood(new Food(0, 0, null, "apple", 15));
//    villager4.setFood(new Food(0, 0, null, "sandwich", 20));
//    
//    villager1.setKey(new Key(0, 0, null, "peanut butter", 1));
//    villager2.setKey(new Key(0, 0, null, "book", 2));
//    villager3.setKey(new Key(0, 0, null, "cookies", 3));
//    villager4.setKey(new Key(0, 0, null, "key", 4));
//    
//    //Enemies
//    paper1 = new Paper(0, 0, null, 50, 50, 30);
//    
//    //Set up the game panel (where we put our graphics)
//    gamePanel = new GameAreaPanel();
//    gamePanel.setBackground(new Color(87, 65, 200, 150));
//    
//    this.add(new GameAreaPanel());
//    
//    MyKeyListener keyListener = new MyKeyListener();
//    this.addKeyListener(keyListener);
//    
//    MyMouseListener mouseListener = new MyMouseListener();
//    this.addMouseListener(mouseListener);
//    
//    this.requestFocusInWindow(); //make sure the frame has focus   
//    
//    this.setResizable(true);
//    this.setVisible(true);
//    
//    //Start the game loop in a separate thread
//    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
//    t.start();
//    
//  } //End of Constructor
//  
//  //the main gameloop - this is where the game state is updated
//  public void animate() { 
//    
//    while(true){
//      /*this.x = (Math.random()*1024)+200;  //update coords
//       this.y = (Math.random()*768)+200;*/
//      //try{ Thread.sleep(500);} catch (Exception exc){}  //delay
//      this.repaint();
//      //System.out.println(x+" "+y);
//    }    
//  }
//  
//  /** --------- INNER CLASSES ------------- **/
//  
//  // Inner class for the the game area - This is where all the drawing of the screen occurs
//  private class GameAreaPanel extends JPanel {
//    
//    public void paintComponent(Graphics g) {   
//      // super.paintComponent(g); //removed to keep transparent panel
//      
//      clock.update();
//      frameRate.update();
//      
//      //Move main map when main map is true
//      if(mainMap){
//        if(lastPressed == 0 && direction[0]){
//          character.setPositionY(character.getPositionY() +2*clock.getElapsedTime()*100);        
//        }else if(lastPressed == 1 && direction[1]){
//          character.setPositionY(character.getPositionY() +-2*clock.getElapsedTime()*100);
//        }else if(lastPressed == 2 && direction[2]){
//          character.setPositionX(character.getPositionX() +-2*clock.getElapsedTime()*100);
//        }else if(lastPressed == 3 && direction[3]){
//          character.setPositionX(character.getPositionX() +2*clock.getElapsedTime()*100);
//        }
//      //Move character
//      }else{      
//        if(lastPressed == 0 && direction[0]){
//          character.setPositionY(character.getPositionY() +-1*clock.getElapsedTime()*100);        
//        }else if(lastPressed == 1 && direction[1]){
//          character.setPositionY(character.getPositionY() +1*clock.getElapsedTime()*100);
//        }else if(lastPressed == 2 && direction[2]){
//          character.setPositionX(character.getPositionX() +1*clock.getElapsedTime()*100);
//        }else if(lastPressed == 3 && direction[3]){
//          character.setPositionX(character.getPositionX() +-1*clock.getElapsedTime()*100);
//        }
//      }
//      
//      
//      if (num > 0) {
//        for (int i = 0; i < num; i ++) {
//          if (bullet[i] != null) {
//            if((bullet[i].getPositionY() > 610) || (bullet[i].getPositionX() > 700) ||(bullet[i].getPositionY() < 350) || (bullet[i].getPositionX() < 190)) {
//              bullet[i] = null;
//              for (int j = i; j < num; j ++) {
//                bullet[j] = bullet[j + 1];
//                bullet[j + 1] = null;
//                num --;
//                System.out.println(num);
//              }
//            }else if(bullet[i].getDirection() == 0){
//              bullet[i].setPositionY(bullet[i].getPositionY() +-1*clock.getElapsedTime()*100);
//            }else if(bullet[i].getDirection() == 1){
//              bullet[i].setPositionY(bullet[i].getPositionY() +1*clock.getElapsedTime()*100);
//            }else if(bullet[i].getDirection()  == 2){
//              bullet[i].setPositionX(bullet[i].getPositionX() +1*clock.getElapsedTime()*100);
//            }else if(bullet[i].getDirection()  == 3){
//              bullet[i].setPositionX(bullet[i].getPositionX() +-1*clock.getElapsedTime()*100);
//            }
//          }
//        }
//      }
//      
//      bulletCollision(bullet, enemy);
//      
//      //box.update(clock.getElapsedTime());
//      setDoubleBuffered(true); 
//      //g.setColor(Color.BLUE); //There are many graphics commands that Java can use
//      //g.fillRect((int)box.xPosition, (int)box.yPosition, 50, 50); //notice the x,y variables that we control from our animate method      
//      
//      //Get images
//      try {
//        
//        if (houseOneMap == true){
//          mapImage = "C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\House1Battle.png";
//        }
//        
//        if (houseTwoMap == true){
//          mapImage = "House2Battle.png";
//        }
//
//        //When main Map is true all other map is false
//        if (mainMap == true){
//          mapImage = "C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\World.png";
//          houseOneMap = false;
//          houseTwoMap = false;
//          houseThreeMap = false;
//          houseFourMap = false;
//          houseFiveMap = false;
//        }
//        
//        //House one to map
//        //Position of door g.drawRect(520, 610, 90, 50);
//        if((houseOneMap == true) && (character.getPositionX() > 520 && character.getPositionX() < 610 && character.getPositionY() > 610)){
//          mainMap = true;
//          houseOneMap = false;
//          System.out.println("main map is true");
//        }
//        
//        map = ImageIO.read(new File(mapImage));
//        
//        //Get main character image
//        String mainImage = "C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\CharacterHouse2.png";
//        mainCharacter = ImageIO.read(new File(mainImage));
//        character.setImage(mainCharacter);
//        
//        paperSprite = ImageIO.read(new File("C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\paper.png"));
//        
//        bulletSprite = ImageIO.read(new File("C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\Bullet1.png"));
//        bulletSprite2 = ImageIO.read(new File("C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\Bullet2.png"));
//        bulletSprite3 = ImageIO.read(new File("C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\Bullet3.png"));
//        bulletSprite4 = ImageIO.read(new File("C:\\Users\\Jeffray\\eclipse-workspace\\Final Project\\src\\Bullet4.png"));
//        
//      } catch (IOException ex) {
//        ex.printStackTrace();
//      }
//      
//      //Resize images
//      //Main map resize
//      if(mainMap == true){
//        newMap = map.getScaledInstance(2500, 2500, Image.SCALE_DEFAULT); //Resize image for main map
//      }
//      //Main character resize
//      newMainCharacter = mainCharacter.getScaledInstance(16*3, 29*3, Image.SCALE_DEFAULT);
//      
//      //House one resize
//      if(houseOneMap == true){
//        newMap = map.getScaledInstance(1200, 1200, Image.SCALE_DEFAULT);
//      }
//      
//      if (space) {
//        bullet[num] = new Bullet((int)character.getPositionX(), (int)character.getPositionY() + 45, bulletSprite, lastPressed);
//        num ++;
//        space = false;
//      }
//      
//      //Draw Images
//      
//      //When its main map from house one
//      if(mainMap){
//        if(houseOneComplete == true){
//          g.drawImage(newMap, (int)character.getPositionX()-1100, (int)character.getPositionY()-2030,this);    
//          //g.drawImage(newMainCharacter, 400, 400,this);
//        }//1250 2200
//        if(houseTwoComplete == true){
//          g.drawImage(newMap, (int)character.getPositionX()-1050, (int)character.getPositionY()-2100,this);    
//          g.drawImage(newMainCharacter, 400, 400,this);    
//        }
//      }
//      
//      
//      //When in houses
//      if (mainMap == false){
//        //For house one map hitboxes remove when done
//        g.drawImage(newMap,-160,-150,this);    
//        g.drawImage(newMainCharacter, (int)character.getPositionX(), (int)character.getPositionY(),this); 
//        /*g.drawRect(520, 610, 90, 50);
//        g.drawRect(190, 350, 510, 260);
//        g.setColor(Color.BLUE);
//        if (collision) {
//          g.setColor(Color.RED);
//        }
//        g.fillRect((int)enemy.getPositionX(), (int)enemy.getPositionY(), 50, 50);*/
//    	
//    	  if(paper1.moveDirection(character.getPositionX(), character.getPositionY())==3){
//	        paper1.setPositionX(paper1.getPositionX()+-0.5*clock.getElapsedTime()*100);
//	      }else if(paper1.moveDirection(character.getPositionX(), character.getPositionY())==2){
//	        paper1.setPositionX(paper1.getPositionX()+0.5*clock.getElapsedTime()*100);
//	      }else if(paper1.moveDirection(character.getPositionX(), character.getPositionY())==1){
//	        paper1.setPositionY(paper1.getPositionY()+0.5*clock.getElapsedTime()*100);
//	      }else if(paper1.moveDirection(character.getPositionX(), character.getPositionY())==0){
//	        paper1.setPositionY(paper1.getPositionY()+-0.5*clock.getElapsedTime()*100);
//	      }
//    	g.drawImage(paperSprite, (int)paper1.getPositionX(), (int)paper1.getPositionY(), (int)paper1.getWidth(), (int)paper1.getHeight(), null);
//    	//System.out.println(paper1.getPositionX()+" "+paper1.getPositionY());
//        if (num > 0) {
//          for (int i = 0; i < num; i ++) {
//            if (bullet[i] != null) {
//              if(bullet[i].getDirection() == 0){
//                newBulletSprite = bulletSprite.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
//              }else if(bullet[i].getDirection() == 1){
//                newBulletSprite = bulletSprite3.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
//              }else if(bullet[i].getDirection()  == 2){
//                newBulletSprite = bulletSprite2.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
//              }else if(bullet[i].getDirection()  == 3){
//                newBulletSprite = bulletSprite4.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
//              }
//              g.drawImage(newBulletSprite, (int)bullet[i].getPositionX(), (int)bullet[i].getPositionY(), this);
//            }
//          }
//        }
//      }
//      
//      
//      g.setColor(Color.GRAY);
//      g.fillRect(0, 0, 450, 45);
//      g.setColor(Color.BLACK);
//      g.fillRect(30, 10, 400, 25);
//      g.setFont(new Font("TimesRoman", Font.BOLD, 15));
//      g.drawString("HP", 5, 28);
//      g.setColor(Color.RED);
//      g.fillRect(30, 10, character.getHealth()*4, 25);
//      
//    }
//    
//    public void bulletCollision(Bullet bullet[], Villager enemy) {
//      for (int i = 0; i < 100; i ++) {
//        if (bullet[i] != null) {
//          if ((bullet[i].getPositionX() + 7.5 > enemy.getPositionX()) && (bullet[i].getPositionX() + 7.5 < enemy.getPositionX() + enemy.getWidth()) && (bullet[i].getPositionY() + 7.5 > enemy.getPositionY()) && (bullet[i].getPositionY() + 7.5 < enemy.getPositionY() + enemy.getHeight())) {
//            collision = true;
//            bullet[i] = null;
//            for (int j = i; j < num; j ++) {
//              bullet[j] = bullet[j + 1];
//              bullet[j + 1] = null;
//              num --;
//              System.out.println(num);
//            }
//          }
//        }
//      }
//    }
//  }
//  
//  // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
//  private class MyKeyListener implements KeyListener {
//    //Box area (all num has to be mutiples of moveSpace)
//    int moveSpace = 3;
//    int boxStartX = 345;
//    int boxEndX = 807;
//    int boxStartY = 504;
//    int boxEndY = 681;
//    
//    public void keyTyped(KeyEvent e) { 
//    }
//    
//    
//    public void keyPressed(KeyEvent e) {      
//      if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
//        System.out.println("YIKES D KEY!");
//      } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
//        System.out.println("YIKES ESCAPE KEY!"); //close frame & quit
//        System.exit(0);
//      }else if(e.getKeyCode() == KeyEvent.VK_UP){
//        direction[0] = true;
//        lastPressed = 0;        
//      }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//        direction[1] = true;
//        lastPressed = 1;
//      }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//        direction[2] = true;
//        lastPressed = 2;
//      }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
//        direction[3] = true;
//        lastPressed = 3;
//      }
//    }    
//    
//    public void keyReleased(KeyEvent e) {
//      if(e.getKeyCode() == KeyEvent.VK_UP){
//        direction[0] = false;
//      }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//        direction[1] = false;
//      }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//        direction[2] = false;
//      }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
//        direction[3] = false;
//      }else if (e.getKeyCode() == KeyEvent.VK_SPACE){
//        space = true;
//      }
//    }
//  } //end of keyboard listener
//  
//  // -----------  Inner class for the keyboard listener - This detects mouse movement & clicks and runs the corresponding methods 
//  private class MyMouseListener implements MouseListener {
//    
//    public void mouseClicked(MouseEvent e) {
//      System.out.println("Mouse Clicked");
//      System.out.println("X:"+e.getX() + " y:"+e.getY());
//    }
//    
//    public void mousePressed(MouseEvent e) {
//    }
//    
//    public void mouseReleased(MouseEvent e) {
//    }
//    
//    public void mouseEntered(MouseEvent e) {
//    }
//    
//    public void mouseExited(MouseEvent e) {
//    }
//  } //end of mouselistener
//  
//}
////A class to track time
//
//class Clock {
//  long elapsedTime;
//  long lastTimeCheck;
//  
//  public Clock() { 
//    lastTimeCheck=System.nanoTime();
//    elapsedTime=0;
//  }
//  
//  public void update() {
//    long currentTime = System.nanoTime();  //if the computer is fast you need more precision
//    elapsedTime=currentTime - lastTimeCheck;
//    lastTimeCheck=currentTime;
//  }
//  
//  //return elapsed time in milliseconds
//  public double getElapsedTime() {
//    return elapsedTime/1.0E9;
//  }
//}
//
//
////A class to represent the object moving around on the screen
//class MovingBox {
//  double xPosition,yPosition;
//  double xSpeed;
//  int height,width;
//  Rectangle boundingBox; //rectangle that is used for collision detection
//  
//  public MovingBox(int x, int y, int w, int h) {
//    xPosition = x;
//    yPosition = y;
//    width=w;
//    height=h;
//    xSpeed=1;
//    boundingBox=new Rectangle((int)xPosition, (int)yPosition, width, height);
//  }
//  
//  public void update(double elapsedTime){
//    //update the content
//    if (xPosition<0) xSpeed=1;
//    else if (xPosition>1000) xSpeed=-1;
//    xPosition= xPosition+xSpeed*elapsedTime*100;  //d = d0 + vt
//    boundingBox.x=(int)xPosition;
//    //System.out.println(elapsedTime*10+"\n");
//  }
//  
//  public void draw(Graphics g) { 
//    g.setColor(Color.BLUE); //There are many graphics commands that Java can use
//    g.fillRect((int)xPosition, (int)yPosition, width, height); //notice the y is a variable that we control from our animate method          
//  }
//}
//
////Better to abstract the FrameRate stuff
//class FrameRate { 
//  
//  String frameRate; //to display the frame rate to the screen
//  long lastTimeCheck; //store the time of the last time the time was recorded
//  long deltaTime; //to keep the elapsed time between current time and last time
//  int frameCount; //used to cound how many frame occurred in the elasped time (fps)
//  
//  public FrameRate() { 
//    lastTimeCheck = System.currentTimeMillis();
//    frameCount=0;
//    frameRate="0 fps";
//  }
//  
//  public void update() { 
//    long currentTime = System.currentTimeMillis();  //get the current time
//    deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
//    lastTimeCheck = currentTime; //update the last time var
//    frameCount++; // everytime this method is called it is a new frame
//    if (deltaTime>=1000) { //when a second has passed, update the string message
//      frameRate = frameCount + " fps" ;
//      frameCount=0; //reset the number of frames since last update
//      deltaTime=0;  //reset the elapsed time     
//    }
//  }
//  
//  public void draw(Graphics g, int x, int y) {
//    g.drawString(frameRate,x,y); //display the frameRate
//  }
//  
//  
//}

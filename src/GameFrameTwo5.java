/* Anne Hua
 * Jan 2, 2020
 * Trash Quest
 * Main map moving character
 */

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Rectangle;

class GameFrameTwo extends JFrame {

	JFrame thisFrame;
	// Class variable (non-static)
	static GameAreaPanel gamePanel;
	Clock clock;
	boolean direction[];
	boolean space;
	int lastPressed;
	int num;
	int houseNameNum;
	int walkSpeed;
	int walkSpeedHouse;
	int bulletSpeed;
	int bananaSpeed;

	boolean gameOver = false;
	boolean resized = false;
	boolean enemies = false;

	// Images
	BufferedImage map;
	BufferedImage mainCharacter;

	BufferedImage pocketImage;

	BufferedImage bulletSprite;
	BufferedImage bulletSprite2;
	BufferedImage bulletSprite3;
	BufferedImage bulletSprite4;

	// food buffered images
	BufferedImage lemonadeImage;
	BufferedImage chocolateImage;
	BufferedImage appleImage;
	BufferedImage sandwichImage;

	// key buffered images
	BufferedImage peanutButterImage;
	BufferedImage bookImage;
	BufferedImage cookiesImage;
	BufferedImage keyImage;

	BufferedImage paperSprite;
	BufferedImage bananaSprite;
	BufferedImage trashCanSprite;
	BufferedImage sodaCanSprite;
	BufferedImage skunkoonSprite;

	Image newMainCharacter;
	Image newMap;
	Image newBulletSprite;
	Image newPocketImage;

	Image newItem;

	// Character
	Character character;

	MainMap gameMainMap;

	Pocket pocket;
	boolean pocketOpen;
	int pocketNum;
	boolean itemUsed;
	Item item;
	int keyNum;

	// Arrays//

	// Bullet array
	Bullet bullet[];
	EnemyBullet enemyBullets[];
	int shootSprite;

	// Door hitboxes array
	Rectangle insideDoor[];
	Hitbox outsideDoor[];

	Rectangle walls1[];
	Rectangle walls2[];
	Rectangle walls3[];
	Rectangle walls4[];
	Rectangle walls5[];

	// Grass hitbox array
	Hitbox grassHitbox[];

	// Maps images array
	BufferedImage mapImage[];

	// Image name array
	/*
	 * String houseName[] = { "House1Battle.png", "House2Battle.png",
	 * "House3Battle.png", "House4Battle.png", "House5Battle.png", "World.png" };
	 */
	String houseName[] = { "House1Battle.png", "House2Battle.png", "House3Battle.png", "House4Battle.png",
			"House5Battle.png", "World.png" };

	// Character hitbox
	Rectangle characterBox;

	// Villagers
	Villager villager1;
	Villager villager2;
	Villager villager3;
	Villager villager4;

	Food lemonade;
	Food chocolate;
	Food apple;
	Food sandwich;

	Key peanutButter;
	Key book;
	Key cookies;
	Key key;

	// Enemies
	Paper paper1;
	Banana banana1;
	Banana banana2;
	TrashCan trashCan1;
	SodaCan sodaCan1;
	SodaCan sodaCan2;
	Skunkoon skunkoon;
	// Start game from beginning
	boolean begin = false;

	// Inside house maps
	boolean houseOneMap = false;
	boolean houseTwoMap = false;
	boolean houseThreeMap = false;
	boolean houseFourMapLeft = false;
	boolean houseFourMapRight = false;
	boolean houseFiveMap = false;

	// Main map
	boolean mainMap = true;

	// Comeplete level or not
	boolean houseOneComplete = false;
	boolean houseTwoComplete = false;
	boolean houseThreeComplete = false;
	boolean houseFourCompleteRight = false;
	boolean houseFourCompleteLeft = false;
	boolean houseFiveComplete = false;

	// Constructor - this runs first
	GameFrameTwo() {

		super("My Game");
		// Set the frame to full screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null); //start the frame in the center of the
		// screen
		this.setSize(900, 900);
		this.setLocationRelativeTo(null); // start the frame in the center of the screen
		this.setUndecorated(false); // Set to true to remove title bar
		this.setBackground(new Color(255, 255, 255));

		thisFrame = this;

		clock = new Clock();
		direction = new boolean[4];
		character = new Character(400, 400, null, 30, 60, 100);
		gameMainMap = new MainMap(0, -1530, null, 100, 100);
		bullet = new Bullet[1000];
		enemyBullets = new EnemyBullet[1000];
		num = 0;
		houseNameNum = 0;
		shootSprite = 0;
		walkSpeed = 1;
		walkSpeedHouse = 3;
		bulletSpeed = 8;
		bananaSpeed = 6;

		mapImage = new BufferedImage[6];

		// Character box size??
		characterBox = new Rectangle(412, 478, 26, 10); // (400, 477, 30, 10);

		// Hitbox arrays//

		// Door hitbox arrays
		insideDoor = new Rectangle[6];
		outsideDoor = new Hitbox[6];

		// wall hitbox arrays
		walls1 = new Rectangle[5];
		walls2 = new Rectangle[5];
		walls3 = new Rectangle[7];
		walls4 = new Rectangle[6];
		walls5 = new Rectangle[7];

		// Grass hitbox array
		grassHitbox = new Hitbox[33];

		// Inside door hitboxes
		// Inside door hitboxes
		insideDoor[0] = new Rectangle(505, 590, 75, 10);
		insideDoor[1] = new Rectangle(420, 650, 75, 10);
		insideDoor[2] = new Rectangle(350, 660, 200, 50);
		// left
		insideDoor[3] = new Rectangle(265, 775, 65, 50);
		// right
		insideDoor[4] = new Rectangle(570, 775, 65, 50);
		insideDoor[5] = new Rectangle(320, 780, 260, 50);

		// Outside door hitboxes
		outsideDoor[0] = new Hitbox(946, 1895, 77, 10);
		outsideDoor[1] = new Hitbox(1767, 1830, 80, 10);
		outsideDoor[2] = new Hitbox(1540, 1445, 160, 10);
		outsideDoor[3] = new Hitbox(770, 1012, 90, 10);
		outsideDoor[4] = new Hitbox(980, 1012, 90, 10);
		outsideDoor[5] = new Hitbox(1700, 803, 165, 10);

		// wall hitboxes
		walls1[0] = new Rectangle(250, 270, 10, 325);
		walls1[1] = new Rectangle(650, 270, 10, 325);//
		walls1[2] = new Rectangle(225, 570, 260, 10);// 200
		walls1[3] = new Rectangle(630, 570, 75, 10);// 575
		walls1[4] = new Rectangle(250, 320, 410, 10);

		walls2[0] = new Rectangle(540, 640, 275, 10);// 485
		walls2[1] = new Rectangle(130, 640, 275, 10);// 145
		walls2[2] = new Rectangle(750, 240, 10, 420);// right
		walls2[3] = new Rectangle(140, 240, 10, 420);// left wall
		walls2[4] = new Rectangle(180, 310, 650, 10);

		walls3[0] = new Rectangle(110, 660, 240, 10);
		walls3[1] = new Rectangle(550, 660, 240, 10);
		walls3[2] = new Rectangle(120, 380, 10, 280);
		walls3[3] = new Rectangle(770, 380, 10, 280);
		walls3[4] = new Rectangle(120, 200, 160, 240);// top top wall
		walls3[5] = new Rectangle(625, 190, 160, 240);// top right and small right top
		walls3[6] = new Rectangle(280, 190, 340, 10); // top left and small left top

		walls4[0] = new Rectangle(150, 775, 115, 10);
		walls4[1] = new Rectangle(635, 775, 115, 10);
		walls4[2] = new Rectangle(330, 775, 240, 240);
		walls4[3] = new Rectangle(140, 80, 10, 695);
		walls4[4] = new Rectangle(750, 80, 10, 695);
		walls4[5] = new Rectangle(150, 120, 600, 10);//

		walls5[0] = new Rectangle(80, 780, 240, 10);
		walls5[1] = new Rectangle(580, 780, 240, 10);
		walls5[2] = new Rectangle(80, 220, 10, 560);
		walls5[3] = new Rectangle(800, 220, 10, 560);// left big wall
		walls5[4] = new Rectangle(80, 75, 180, 220);// 60
		walls5[5] = new Rectangle(640, 75, 180, 220);// 700
		walls5[6] = new Rectangle(200, 70, 480, 10);

		// Grass hitboxes

		grassHitbox[0] = new Hitbox(100, 1905, 846, 50);
		grassHitbox[1] = new Hitbox(100, 2035, 2000, 10);
		grassHitbox[2] = new Hitbox(1024, 1755, 554, 200);
		grassHitbox[3] = new Hitbox(1655, 1519, 112, 437);
		grassHitbox[4] = new Hitbox(1845, 1830, 10, 210);
		grassHitbox[5] = new Hitbox(1530, 1519, 50, 156);
		grassHitbox[6] = new Hitbox(1355, 1445, 185, 231);
		grassHitbox[7] = new Hitbox(1700, 1445, 10, 75);
		grassHitbox[8] = new Hitbox(1072, 1474, 280, 10);
		grassHitbox[9] = new Hitbox(760, 1564, 510, 10);
		grassHitbox[10] = new Hitbox(1260, 1564, 10, 200);
		grassHitbox[11] = new Hitbox(759, 1023, 10, 552);
		grassHitbox[12] = new Hitbox(1072, 883, 665, 136);
		grassHitbox[13] = new Hitbox(860, 1012, 117, 10);
		grassHitbox[14] = new Hitbox(1072, 1097, 750, 10);
		grassHitbox[15] = new Hitbox(1072, 1097, 10, 380);
		grassHitbox[16] = new Hitbox(851, 1162, 138, 320);
		grassHitbox[17] = new Hitbox(1820, 885, 10, 240);

		grassHitbox[18] = new Hitbox(1828, 873, 10, 10);
		grassHitbox[19] = new Hitbox(1834, 863, 10, 10);
		grassHitbox[20] = new Hitbox(1840, 853, 10, 10);
		grassHitbox[21] = new Hitbox(1846, 843, 10, 10);
		grassHitbox[22] = new Hitbox(1852, 833, 10, 10);
		grassHitbox[23] = new Hitbox(1858, 823, 10, 10);
		grassHitbox[24] = new Hitbox(1864, 813, 10, 10);

		grassHitbox[25] = new Hitbox(1724, 873, 10, 10);
		grassHitbox[26] = new Hitbox(1718, 863, 10, 10);
		grassHitbox[27] = new Hitbox(1712, 853, 10, 10);
		grassHitbox[28] = new Hitbox(1706, 843, 10, 10);
		grassHitbox[29] = new Hitbox(1700, 833, 10, 10);
		grassHitbox[30] = new Hitbox(1694, 823, 10, 10);
		grassHitbox[31] = new Hitbox(1688, 813, 10, 10);

		grassHitbox[32] = new Hitbox(390, 1955, 10, 100);

		try {

			/*
			 * lemonadeImage = ImageIO.read(new File("Lemonade.png")); chocolateImage =
			 * ImageIO.read(new File("Chocolate.png")); appleImage = ImageIO.read(new
			 * File("Apple.png")); sandwichImage = ImageIO.read(new File("Sandwich.png"));
			 * 
			 * peanutButterImage = ImageIO.read(new File("Peanut Butter.png")); bookImage =
			 * ImageIO.read(new File("Book.png")); cookiesImage = ImageIO.read(new
			 * File("Cookies.png")); keyImage = ImageIO.read(new File("Key.png"));
			 */
			lemonadeImage = ImageIO.read(getClass().getResourceAsStream("Lemonade.png"));
			chocolateImage = ImageIO.read(getClass().getResourceAsStream("Chocolate.png"));
			appleImage = ImageIO.read(getClass().getResourceAsStream("Apple.png"));
			sandwichImage = ImageIO.read(getClass().getResourceAsStream("Sandwich.png"));

			peanutButterImage = ImageIO.read(getClass().getResourceAsStream("Peanut Butter.png"));
			bookImage = ImageIO.read(getClass().getResourceAsStream("Book.png"));
			cookiesImage = ImageIO.read(getClass().getResourceAsStream("Cookies.png"));
			keyImage = ImageIO.read(getClass().getResourceAsStream("Key.png"));

			// House maps
			mapImage[0] = ImageIO.read(getClass().getResourceAsStream("House1Battle.png"));
			mapImage[1] = ImageIO.read(getClass().getResourceAsStream("House2Battle.png"));
			mapImage[2] = ImageIO.read(getClass().getResourceAsStream("House3Battle.png"));
			mapImage[3] = ImageIO.read(getClass().getResourceAsStream("House4Battle.png"));
			mapImage[4] = ImageIO.read(getClass().getResourceAsStream("House5Battle.png"));

			// Main map
			mapImage[5] = ImageIO.read(getClass().getResourceAsStream("World.png"));

			/*
			 * bulletSprite = ImageIO.read(getClass().getResourceAsStream("Bullet1.png"));
			 * bulletSprite2 = ImageIO.read(new File("Bullet2.png")); bulletSprite3 =
			 * ImageIO.read(new File("Bullet3.png")); bulletSprite4 = ImageIO.read(new
			 * File("Bullet4.png"));
			 */
			paperSprite = ImageIO.read(getClass().getResourceAsStream("paper.png"));
			bananaSprite = ImageIO.read(getClass().getResourceAsStream("TrashBanana.png"));
			trashCanSprite = ImageIO.read(getClass().getResourceAsStream("TrashTrashcan.png"));
			sodaCanSprite = ImageIO.read(getClass().getResourceAsStream("TrashSodaCan.png"));
			skunkoonSprite = ImageIO.read(getClass().getResourceAsStream("Raccoon.png"));

			bulletSprite = ImageIO.read(getClass().getResourceAsStream("Bullet1.png"));
			bulletSprite2 = ImageIO.read(getClass().getResourceAsStream("Bullet2.png"));
			bulletSprite3 = ImageIO.read(getClass().getResourceAsStream("Bullet3.png"));
			bulletSprite4 = ImageIO.read(getClass().getResourceAsStream("Bullet4.png"));

			pocketImage = ImageIO.read(getClass().getResourceAsStream("PocketWithDescription.png"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Pocket
		pocket = new Pocket();

		// teletubby names
		villager1 = new Villager(0, 0, null, 10, 10, "Tinky-Winky");
		villager2 = new Villager(0, 0, null, 10, 10, "Dipsy");
		villager3 = new Villager(0, 0, null, 10, 10, "Laa Laa");
		villager4 = new Villager(0, 0, null, 10, 10, "Po");

		lemonade = new Food(0, 0, lemonadeImage, "Lemonade", "Drink to gain +5 health.", 5);
		chocolate = new Food(0, 0, chocolateImage, "Chocolate", "Eat to gain +10 health.", 10);
		apple = new Food(0, 0, appleImage, "Apple", "Eat to gain +15 health.", 15);
		sandwich = new Food(0, 0, sandwichImage, "Sandwich", "Eat to gain +20 health.", 20);

		peanutButter = new Key(0, 0, peanutButterImage, "Peanut Butter",
				"Bring this to the purple house. to unlock the next level.", 1);
		book = new Key(0, 0, bookImage, "Book", "Bring this to the pink house to. unlock the next level.", 2);
		cookies = new Key(0, 0, cookiesImage, "Cookies", "Bring this to the orange house. to unlock the next level.",
				3);
		key = new Key(0, 0, keyImage, "Key", "Bring this to your house to. unlock the next level.", 4);

		villager1.setFood(lemonade);
		villager2.setFood(chocolate);
		villager3.setFood(apple);
		villager4.setFood(sandwich);

		villager1.setKey(peanutButter);
		villager2.setKey(book);
		villager3.setKey(cookies);
		villager4.setKey(key);

		// Pocket
		pocket = new Pocket();
		pocketOpen = false;
		pocketNum = 0;
		itemUsed = false;

		// Enemies
		paper1 = new Paper(300, 300, null, 30, 30, 30, 5);
		banana1 = new Banana(300, 400, null, 50, 50, 40, 10);
		banana2 = new Banana(300, 600, null, 50, 50, 40, 10);
		trashCan1 = new TrashCan(400, 300, null, 100, 100, 30, 5);
		sodaCan1 = new SodaCan(300, 200, null, 50, 50, 60, 10);
		sodaCan2 = new SodaCan(200, 450, null, 50, 50, 60, 10);
		skunkoon = new Skunkoon(500, 500, null, 200, 200, 100, 10);

		// Set up the game panel (where we put our graphics)
		gamePanel = new GameAreaPanel();
		gamePanel.setBackground(new Color(87, 65, 200, 150));

		this.add(new GameAreaPanel());

		MyKeyListener keyListener = new MyKeyListener();
		this.addKeyListener(keyListener);

		MyMouseListener mouseListener = new MyMouseListener();
		this.addMouseListener(mouseListener);

		this.requestFocusInWindow(); // make sure the frame has focus

		// this.setResizable(true);
		this.setVisible(true);

		// Start the game loop in a separate thread
		Thread t = new Thread(new Runnable() {
			public void run() {
				animate();
			}
		}); // start the gameLoop
		t.start();

	} // End of Constructor

	// the main gameloop - this is where the game state is updated
	public void animate() {

		while (true) {
			this.repaint();
		}
	}

	/** --------- INNER CLASSES ------------- **/

	// Inner class for the the game area - This is where all the drawing of the
	// screen occurs
	private class GameAreaPanel extends JPanel {

		public void paintComponent(Graphics g) {

			clock.update();
			character.updateSprites();
			if (character.getHealth() <= 0 && gameOver == false) {
				gameOver = true;
				thisFrame.dispose();
				new GameLost();

			} else if (gameOver == false && paper1 == null && banana1 == null && banana2 == null && trashCan1 == null
					&& sodaCan1 == null && sodaCan2 == null && skunkoon == null) {
				gameOver = true;
				thisFrame.dispose();
				new GameWon();
			}
			// Character not intersecting with grass hitbox
			// Move main map when main map is true
			if (mainMap) {
				// X and Y for grass hitboxes
				for (int i = 0; i < grassHitbox.length; i++) {
					Rectangle temp = new Rectangle((int) (gameMainMap.getPositionX() + grassHitbox[i].getPositionX()),
							(int) (gameMainMap.getPositionY() + grassHitbox[i].getPositionY()),
							grassHitbox[i].getWidth(), grassHitbox[i].getHeight());
				}

				// Character not intersecting with grass hitbox
				// Move up
				if (lastPressed == 0 && direction[0]) {
					gameMainMap.setPositionY(gameMainMap.getPositionY() + walkSpeed);
					character.move(0);
					// Move down
				} else if (lastPressed == 1 && direction[1]) {
					gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
					character.move(1);
					// Move Right
				} else if (lastPressed == 2 && direction[2]) {
					gameMainMap.setPositionX(gameMainMap.getPositionX() + -walkSpeed);
					character.move(2);
					// Move left
				} else if (lastPressed == 3 && direction[3]) {
					gameMainMap.setPositionX(gameMainMap.getPositionX() + walkSpeed);
					character.move(3);
				} else {
					character.move(5);
				}

				// Character intersects with grass hitbox
				for (int i = 0; i < grassHitbox.length; i++) {
					Rectangle temp = new Rectangle((int) (gameMainMap.getPositionX() + grassHitbox[i].getPositionX()),
							(int) (gameMainMap.getPositionY() + grassHitbox[i].getPositionY()),
							grassHitbox[i].getWidth(), grassHitbox[i].getHeight());
					if (character.getBox().intersects(temp)) {
						// Up
						if (lastPressed == 0 && direction[0]) {
							gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							character.move(0);
							// Down
						} else if (lastPressed == 1 && direction[1]) {
							gameMainMap.setPositionY(gameMainMap.getPositionY() + walkSpeed);
							character.move(1);
							// Right
						} else if (lastPressed == 2 && direction[2]) {
							gameMainMap.setPositionX(gameMainMap.getPositionX() + walkSpeed);
							character.move(2);
							// Left
						} else if (lastPressed == 3 && direction[3]) {
							gameMainMap.setPositionX(gameMainMap.getPositionX() + -walkSpeed);
							character.move(3);
						} else {
							character.move(5);
						}
					}

				}
				// Move character inside house when mainMap is false
			} else {
				if (lastPressed == 0 && direction[0]) {
					character.setPositionY(character.getPositionY() + -walkSpeedHouse);
					character.move(0);
				} else if (lastPressed == 1 && direction[1]) {
					character.setPositionY(character.getPositionY() + walkSpeedHouse);
					character.move(1);
				} else if (lastPressed == 2 && direction[2]) {
					character.setPositionX(character.getPositionX() + walkSpeedHouse);
					character.move(2);
				} else if (lastPressed == 3 && direction[3]) {
					character.setPositionX(character.getPositionX() + -walkSpeedHouse);
					character.move(3);
				} else {
					character.move(5);
				}

			}

			if (mainMap) {
				houseNameNum = 5;
				houseOneMap = false;
				houseTwoMap = false;
				houseFourMapLeft = false;
				houseFourMapRight = false;
				houseFiveMap = false;
			}
			if (houseOneMap) {
				houseNameNum = 0;
			}

			if (houseTwoMap) {
				houseNameNum = 1;
			}

			if (houseThreeMap) {
				houseNameNum = 2;
			}

			if (houseFourMapRight) {
				houseNameNum = 3;
			}

			if (houseFourMapLeft) {
				houseNameNum = 3;
			}

			if (houseFiveMap) {
				houseNameNum = 4;
			}
			map = mapImage[houseNameNum];
			// Resize images//

			// Main map resize
			if (mainMap && resized == false) {
				resized = true;
				newMap = mapImage[5].getScaledInstance(2500, 2500, Image.SCALE_DEFAULT); // Resize image for main map
			}

			// House resize
			if (houseOneMap || houseTwoMap || houseThreeMap || houseFourMapRight || houseFourMapLeft || houseFiveMap) {
				newMap = mapImage[houseNameNum].getScaledInstance(900, 900, Image.SCALE_DEFAULT);
			}
			if (num > 0) {
				for (int i = 0; i < num; i++) {
					if (bullet[i] != null) {
						if (bullet[i].getDirection() == 0) {
							bullet[i].setPositionY(bullet[i].getPositionY() + -1 * clock.getElapsedTime() * 100);
						} else if (bullet[i].getDirection() == 1) {
							bullet[i].setPositionY(bullet[i].getPositionY() + 1 * clock.getElapsedTime() * 100);
						} else if (bullet[i].getDirection() == 2) {
							bullet[i].setPositionX(bullet[i].getPositionX() + 1 * clock.getElapsedTime() * 100);
						} else if (bullet[i].getDirection() == 3) {
							bullet[i].setPositionX(bullet[i].getPositionX() + -1 * clock.getElapsedTime() * 100);
						}
					}
				}
			}

			setDoubleBuffered(true);
			if (shootSprite > 0 && shootSprite <= 13) {
				if (lastPressed == 0) {
					character.move(6);
				} else if (lastPressed == 1) {
					character.move(7);
				} else if (lastPressed == 2) {
					character.move(8);
				} else if (lastPressed == 3) {
					character.move(9);
				}
				shootSprite++;
			} else if (shootSprite == 14) {
				shootSprite = 0;
			}
			if (space) {
				bullet[num] = new Bullet((int) character.getPositionX() + 32, (int) character.getPositionY() + 25,
						bulletSprite, lastPressed);
				shootSprite = 1;
				num++;
				space = false;
			}

			// Update Hitboxes
			character.updateX(15);
			character.updateY(8);
			if (paper1 != null) {
				paper1.updateX(10);
				paper1.updateY(10);
			} else {
				if (houseOneMap) {
					enemies = false;
				}
			}
			if (banana1 != null) {
				banana1.updateX(0);
				banana1.updateY(0);
			}
			if (banana2 != null) {
				banana2.updateX(0);
				banana2.updateY(0);
			} else if ((banana1 == null) && (banana2 == null) && (houseTwoMap)) {
				enemies = false;
			}
			if (sodaCan1 == null && sodaCan2 == null && houseThreeMap) {
				enemies = false;
			}
			if (trashCan1 == null && (houseFourMapLeft || houseFourMapRight)) {
				enemies = false;
			}
			if (skunkoon != null) {
				skunkoon.updateX(0);
				skunkoon.updateY(0);
			} else {
				if (houseFiveMap) {
					enemies = false;
				}
			}

			if (mainMap) {
				g.drawImage(newMap, (int) gameMainMap.getPositionX(), (int) gameMainMap.getPositionY(), this);
				g.drawImage(character.getCurrentSprite(), 400, 420, this);
				character.setPositionX(400);
				character.setPositionY(420);

				g.setColor(Color.white);
				// Draw grass hitboxes and doors

				// House doors array hitboxes
				for (int i = 0; i < outsideDoor.length; i++) {
					Rectangle temp = new Rectangle((int) (gameMainMap.getPositionX() + outsideDoor[i].getPositionX()),
							(int) (gameMainMap.getPositionY() + outsideDoor[i].getPositionY()),
							outsideDoor[i].getWidth(), outsideDoor[i].getHeight());
					if (character.getBox().intersects(temp)) {

						// Going in house one
						if (i == 0) {
							if (houseOneComplete) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseOneMap = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
								// houseNameNum = 0;
							}

							// Going in house two
						} else if (i == 1) {
							if (houseTwoComplete) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseTwoMap = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
								// houseNameNum = 1;
							}

							// Going in house three
						} else if (i == 2) {
							if (houseThreeComplete) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseThreeMap = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
								// houseNameNum = 2;
							}

							// Going in house four left door
						} else if (i == 3) {
							if (houseFourCompleteLeft) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseFourMapLeft = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
							}

							// Going in house four right door
						} else if (i == 4) {
							if (houseFourCompleteRight) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseFourMapRight = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
							}

							// Going in house five
						} else if (i == 5) {
							if (houseFiveComplete) {
								gameMainMap.setPositionY(gameMainMap.getPositionY() + -walkSpeed);
							} else {
								houseFiveMap = true;
								mainMap = false;
								character.setPositionY(400);
								character.setPositionX(400);
								enemies = true;
							}
						}
					}
				}
			}

			// When in houses
			if (mainMap == false) {
				// For house one map hitboxes remove when done
				g.drawImage(newMap, 0, 0, this);
				g.drawImage(character.getCurrentSprite(), (int) character.getPositionX(),
						(int) character.getPositionY(), this);
				if (num > 0) {
					for (int i = 0; i < num; i++) {
						if (bullet[i] != null) {
							if (bullet[i].getDirection() == 0) {
								newBulletSprite = bulletSprite.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
							} else if (bullet[i].getDirection() == 1) {
								newBulletSprite = bulletSprite3.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
							} else if (bullet[i].getDirection() == 2) {
								newBulletSprite = bulletSprite2.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
							} else if (bullet[i].getDirection() == 3) {
								newBulletSprite = bulletSprite4.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
							}
							g.drawImage(newBulletSprite, (int) bullet[i].getPositionX(), (int) bullet[i].getPositionY(),
									this);
						}
					}
				}
				if (houseOneMap) {
					for (int j = 0; j < walls1.length; j++) {
						if (num > 0) {
							for (int i = 0; i < num; i++) {
								if (bullet[i] != null) {
									bullet[i].updateX(0);
									bullet[i].updateY(0);
									if (bullet[i].getBox().intersects(walls1[j])) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									} else if (paper1 != null && bullet[i].getBox().intersects(paper1.getBox())
											&& paper1.getElapsedTime() >= 1) {
										paper1.setHealth(paper1.getHealth() - 5);
										paper1.setElapsedTime(0);
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									}
								}
							}
						}

						if (character.getBox().intersects(walls1[j])) {
							if (lastPressed == 0) {
								character.setPositionY(character.getPositionY() + walkSpeedHouse);
							} else if (lastPressed == 1) {
								character.setPositionY(character.getPositionY() + -walkSpeedHouse);
							} else if (lastPressed == 2) {
								character.setPositionX(character.getPositionX() + -walkSpeedHouse);
							} else if (lastPressed == 3) {
								character.setPositionX(character.getPositionX() + walkSpeedHouse);
							}
						}
					}
					character.setElapsedTime(clock.getElapsedTime());
					if (paper1 != null) {
						paper1.setElapsedTime(clock.getElapsedTime());

						if (paper1.moveDirection(character.getPositionX(), character.getPositionY()) == 3) {
							paper1.setPositionX(paper1.getPositionX() + -0.5 * clock.getElapsedTime() * 100);
						} else if (paper1.moveDirection(character.getPositionX(), character.getPositionY()) == 2) {
							paper1.setPositionX(paper1.getPositionX() + 0.5 * clock.getElapsedTime() * 100);
						} else if (paper1.moveDirection(character.getPositionX(), character.getPositionY()) == 1) {
							paper1.setPositionY(paper1.getPositionY() + 0.5 * clock.getElapsedTime() * 100);
						} else if (paper1.moveDirection(character.getPositionX(), character.getPositionY()) == 0) {
							paper1.setPositionY(paper1.getPositionY() + -0.5 * clock.getElapsedTime() * 100);
						}
						g.drawImage(paperSprite, (int) paper1.getPositionX(), (int) paper1.getPositionY(), 50, 50,
								null);
						if ((character.getBox().intersects(paper1.getBox())) && (character.getElapsedTime() >= 2)) {
							character.setHealth(character.getHealth() - paper1.getDamage());
							character.setElapsedTime(0);
						}
						if (paper1.getHealth() <= 0) {
							paper1 = null;
						}
					}
				} else if (houseTwoMap) {

					for (int j = 0; j < walls2.length; j++) {

						if (num > 0) {
							for (int i = 0; i < num; i++) {
								if (bullet[i] != null) {
									bullet[i].updateX(0);
									bullet[i].updateY(0);
									if (bullet[i].getBox().intersects(walls2[j])) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									} else if (banana1 != null && bullet[i].getBox().intersects(banana1.getBox())) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
										banana1.setHealth(banana1.getHealth() - 5);
									} else if (banana2 != null && bullet[i].getBox().intersects(banana2.getBox())) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
										banana2.setHealth(banana2.getHealth() - 5);
									}
								}
							}
						}

						if (character.getBox().intersects(walls2[j])) {
							if (lastPressed == 0) {
								character.setPositionY(character.getPositionY() + walkSpeedHouse);
							} else if (lastPressed == 1) {
								character.setPositionY(character.getPositionY() + -walkSpeedHouse);
							} else if (lastPressed == 2) {
								character.setPositionX(character.getPositionX() + -walkSpeedHouse);
							} else if (lastPressed == 3) {
								character.setPositionX(character.getPositionX() + walkSpeedHouse);
							}
						}
						if (banana1 != null && banana1.getBox().intersects(walls2[j])) {
							banana1.setMove(false);
						}
						if (banana2 != null && banana2.getBox().intersects(walls2[j])) {
							banana2.setMove(false);
						}
					}
					if (banana1 != null) {
						banana1.setElapsedTime(clock.getElapsedTime());
						if (banana1.getElapsedTime() >= 3 && banana1.getMove() == false) {
							banana1.moveDirection(character.getPositionX(), character.getPositionY());
							banana1.setElapsedTime(0);
							banana1.setMove(true);
						} else if (banana1.getElapsedTime() >= 2 && banana1.getMove() == true) {
							banana1.setElapsedTime(0);
							banana1.setMove(false);
						}
						if (banana1.getMove() == true) {
							if (banana1.getDirection() == 3) {
								banana1.setPositionX(banana1.getPositionX() + -bananaSpeed);
							} else if (banana1.getDirection() == 2) {
								banana1.setPositionX(banana1.getPositionX() + bananaSpeed);
							} else if (banana1.getDirection() == 1) {
								banana1.setPositionY(banana1.getPositionY() + bananaSpeed);
							} else if (banana1.getDirection() == 0) {
								banana1.setPositionY(banana1.getPositionY() + -bananaSpeed);
							}
						}
						g.drawImage(bananaSprite, (int) banana1.getPositionX(), (int) banana1.getPositionY(),
								banana1.getWidth(), banana1.getHeight(), null);
						if ((character.getBox().intersects(banana1.getBox())) && (character.getElapsedTime() >= 2)) {
							character.setHealth(character.getHealth() - banana1.getDamage());
							character.setElapsedTime(0);
						}
					}
					if (banana2 != null) {
						banana2.setElapsedTime(clock.getElapsedTime());
						if (banana2.getElapsedTime() >= 3 && banana2.getMove() == false) {
							banana2.moveDirection(character.getPositionX(), character.getPositionY());
							banana2.setElapsedTime(0);
							banana2.setMove(true);
						} else if (banana2.getElapsedTime() >= 2 && banana2.getMove() == true) {
							banana2.setElapsedTime(0);
							banana2.setMove(false);
						}
						if (banana2.getMove() == true) {
							if (banana2.getDirection() == 3) {
								banana2.setPositionX(banana2.getPositionX() + -bananaSpeed);
							} else if (banana2.getDirection() == 2) {
								banana2.setPositionX(banana2.getPositionX() + bananaSpeed);
							} else if (banana2.getDirection() == 1) {
								banana2.setPositionY(banana2.getPositionY() + bananaSpeed);
							} else if (banana2.getDirection() == 0) {
								banana2.setPositionY(banana2.getPositionY() + -bananaSpeed);
							}
						}
						g.drawImage(bananaSprite, (int) banana2.getPositionX(), (int) banana2.getPositionY(),
								banana2.getWidth(), banana2.getHeight(), null);
						if ((character.getBox().intersects(banana2.getBox())) && (character.getElapsedTime() >= 2)) {
							character.setHealth(character.getHealth() - banana2.getDamage());
							character.setElapsedTime(0);
						}
					}

					character.setElapsedTime(clock.getElapsedTime());
					if (banana1 != null && banana1.getHealth() <= 0) {
						banana1 = null;
					}
					if (banana2 != null && banana2.getHealth() <= 0) {
						banana2 = null;
					}

				} else if (houseThreeMap) {

					for (int j = 0; j < walls3.length; j++) {
						if (num > 0) {
							for (int i = 0; i < num; i++) {
								if (bullet[i] != null) {
									bullet[i].updateX(0);
									bullet[i].updateY(0);
									if (bullet[i].getBox().intersects(walls3[j])) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									} else if (sodaCan1 != null && bullet[i].getBox().intersects(sodaCan1.getBox())) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
										sodaCan1.setHealth(sodaCan1.getHealth() - 5);
									} else if (sodaCan2 != null && bullet[i].getBox().intersects(sodaCan2.getBox())) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
										sodaCan2.setHealth(sodaCan2.getHealth() - 5);
									}
								}
							}
						}
						if (character.getBox().intersects(walls3[j])) {
							if (lastPressed == 0) {
								character.setPositionY(character.getPositionY() + walkSpeedHouse);
							} else if (lastPressed == 1) {
								character.setPositionY(character.getPositionY() + -walkSpeedHouse);
							} else if (lastPressed == 2) {
								character.setPositionX(character.getPositionX() + -walkSpeedHouse);
							} else if (lastPressed == 3) {
								character.setPositionX(character.getPositionX() + walkSpeedHouse);
							}
						}
					}
					character.setElapsedTime(clock.getElapsedTime());
					if (sodaCan1 != null) {
						sodaCan1.updateX(sodaCan1.getRadius() / 2);
						sodaCan1.updateY(sodaCan1.getRadius() / 2);
						sodaCan1.setElapsedTime(clock.getElapsedTime());
						sodaCan1.shoot(2 * sodaCan1.getElapsedTime() * 100);
						sodaCan1.setElapsedTime(0);
						g.fillArc((int) sodaCan1.getPositionX(), (int) sodaCan1.getPositionY(), sodaCan1.getRadius(),
								sodaCan1.getRadius(), (int) sodaCan1.getStartAngle(), sodaCan1.getArcAngle());

						double distance1 = Math.sqrt(Math
								.pow(character.getPositionX() - (sodaCan1.getPositionX() + sodaCan1.getRadius() / 2), 2)
								+ Math.pow(
										character.getPositionY() - (sodaCan1.getPositionY() + sodaCan1.getRadius() / 2),
										2));
						if (distance1 <= sodaCan1.getRadius() / 2) {
							double angle = Math.atan2(
									character.getPositionY() - (sodaCan1.getPositionY() + sodaCan1.getRadius() / 2),
									character.getPositionX() - (sodaCan1.getPositionX() + sodaCan1.getRadius() / 2));
							angle = Math.toDegrees(angle);
							if (angle < 0) {
								angle *= -1;
							} else {
								angle = 360 - angle;
							}
							if ((angle >= sodaCan1.getStartAngle())
									&& (angle <= sodaCan1.getStartAngle() + sodaCan1.getArcAngle())
									&& (character.getElapsedTime() >= 1.5)) {
								character.setHealth(character.getHealth() - sodaCan1.getDamage());
								character.setElapsedTime(0);
							}
						}
						g.drawImage(sodaCanSprite, (int) sodaCan1.getPositionX() + sodaCan1.getRadius() / 2 - 20,
								(int) sodaCan1.getPositionY() + sodaCan1.getRadius() / 2 - 20, sodaCan1.getWidth(),
								sodaCan1.getHeight(), null);
						if (sodaCan1.getHealth() <= 0) {
							sodaCan1 = null;
						}
					}
					if (sodaCan2 != null) {
						sodaCan2.updateX(sodaCan2.getRadius() / 2);
						sodaCan2.updateY(sodaCan2.getRadius() / 2);
						sodaCan2.setElapsedTime(clock.getElapsedTime());
						sodaCan2.shoot(2 * sodaCan2.getElapsedTime() * 100);
						sodaCan2.setElapsedTime(0);
						g.fillArc((int) sodaCan2.getPositionX(), (int) sodaCan2.getPositionY(), sodaCan2.getRadius(),
								sodaCan2.getRadius(), (int) sodaCan2.getStartAngle(), sodaCan2.getArcAngle());

						double distance2 = Math.sqrt(Math
								.pow(character.getPositionX() - (sodaCan2.getPositionX() + sodaCan2.getRadius() / 2), 2)
								+ Math.pow(
										character.getPositionY() - (sodaCan2.getPositionY() + sodaCan2.getRadius() / 2),
										2));
						if (distance2 <= sodaCan2.getRadius() / 2) {
							double angle = Math.atan2(
									character.getPositionY() - (sodaCan2.getPositionY() + sodaCan2.getRadius() / 2),
									character.getPositionX() - (sodaCan2.getPositionX() + sodaCan2.getRadius() / 2));
							angle = Math.toDegrees(angle);
							if (angle < 0) {
								angle *= -1;
							} else {
								angle = 360 - angle;
							}

							if ((angle >= sodaCan2.getStartAngle())
									&& (angle <= sodaCan2.getStartAngle() + sodaCan2.getArcAngle())
									&& (character.getElapsedTime() >= 1.5)) {
								character.setHealth(character.getHealth() - sodaCan2.getDamage());
								character.setElapsedTime(0);
							}
						}
						g.drawImage(sodaCanSprite, (int) sodaCan2.getPositionX() + sodaCan1.getRadius() / 2 - 20,
								(int) sodaCan2.getPositionY() + sodaCan1.getRadius() / 2 - 20, sodaCan2.getWidth(),
								sodaCan2.getHeight(), null);
						if (sodaCan2.getHealth() <= 0) {
							sodaCan2 = null;
						}
					}

				} else if (houseFourMapLeft || houseFourMapRight) {
					for (int j = 0; j < walls4.length; j++) {
						if (num > 0) {
							for (int i = 0; i < num; i++) {
								if (bullet[i] != null) {
									bullet[i].updateX(0);
									bullet[i].updateY(0);
									if (bullet[i].getBox().intersects(walls4[j])) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									} else if (trashCan1 != null && bullet[i].getBox().intersects(trashCan1.getBox())) {
										trashCan1.setHealth(trashCan1.getHealth() - 5);
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									}
								}
							}
						}
						if (character.getBox().intersects(walls4[j])) {
							if (lastPressed == 0) {
								character.setPositionY(character.getPositionY() + 1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 1) {
								character.setPositionY(character.getPositionY() + -1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 2) {
								character.setPositionX(character.getPositionX() + -1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 3) {
								character.setPositionX(character.getPositionX() + 1 * clock.getElapsedTime() * 100);
							}
						}
						for (int k = 0; k < 1000; k++) {
							if (enemyBullets[k] != null) {
								enemyBullets[k].updateX(0);
								enemyBullets[k].updateY(0);
								if (enemyBullets[k].getBox().intersects(walls4[j])) {
									enemyBullets[k] = null;
								} else if ((trashCan1 != null)
										&& (character.getBox().intersects(enemyBullets[k].getBox()))
										&& (character.getElapsedTime() >= 1)) {
									character.setHealth(character.getHealth() - trashCan1.getDamage());
									character.setElapsedTime(0);
									enemyBullets[k] = null;
								}
							}
						}
					}
					if (trashCan1 != null) {
						trashCan1.setElapsedTime(clock.getElapsedTime());
						g.drawImage(trashCanSprite, (int) trashCan1.getPositionX(), (int) trashCan1.getPositionY(),
								trashCan1.getWidth(), trashCan1.getHeight(), null);
					}
					character.setElapsedTime(clock.getElapsedTime());

					if (trashCan1 != null && trashCan1.getElapsedTime() >= 1) {
						trashCan1.setElapsedTime(0);
						for (int i = 0; i < 1000; i++) {
							if (enemyBullets[i] == null) {
								enemyBullets[i] = new EnemyBullet(trashCan1.getPositionX() + 20,
										trashCan1.getPositionY(), bulletSprite, character.getPositionX(),
										character.getPositionY());
								break;
							}
						}
					}
					for (int i = 0; i < 1000; i++) {
						if (enemyBullets[i] != null) {
							enemyBullets[i].setPositionX(
									enemyBullets[i].getPositionX() + Math.cos(enemyBullets[i].getAngle()) * 3);
							enemyBullets[i].setPositionY(
									enemyBullets[i].getPositionY() + Math.sin(enemyBullets[i].getAngle()) * 3);
							g.drawImage(bananaSprite, (int) enemyBullets[i].getPositionX(),
									(int) enemyBullets[i].getPositionY(), enemyBullets[i].getWidth(),
									enemyBullets[i].getHeight(), null);

						}
					}
					if (trashCan1 != null && trashCan1.getHealth() <= 0) {
						trashCan1 = null;
					}
				} else if (houseFiveMap) {
					for (int j = 0; j < walls5.length; j++) {
						if (num > 0) {
							for (int i = 0; i < num; i++) {
								if (bullet[i] != null) {
									bullet[i].updateX(0);
									bullet[i].updateY(0);
									if (bullet[i].getBox().intersects(walls5[j])) {
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									} else if (skunkoon != null && bullet[i].getBox().intersects(skunkoon.getBox())) {
										skunkoon.setHealth(skunkoon.getHealth() - 5);
										bullet[i] = null;
										for (int n = i; n < num; n++) {
											bullet[n] = bullet[n + 1];
											bullet[n + 1] = null;
											num--;
										}
									}
								}
							}
						}
						if (character.getBox().intersects(walls5[j])) {
							if (lastPressed == 0) {
								character.setPositionY(character.getPositionY() + 1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 1) {
								character.setPositionY(character.getPositionY() + -1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 2) {
								character.setPositionX(character.getPositionX() + -1 * clock.getElapsedTime() * 100);
							} else if (lastPressed == 3) {
								character.setPositionX(character.getPositionX() + 1 * clock.getElapsedTime() * 100);
							}
						}
						for (int k = 0; k < 1000; k++) {
							if (enemyBullets[k] != null) {
								enemyBullets[k].updateX(0);
								enemyBullets[k].updateY(0);
								if (enemyBullets[k].getBox().intersects(walls5[j])) {
									enemyBullets[k] = null;
								} else if ((character.getBox().intersects(enemyBullets[k].getBox()))
										&& (character.getElapsedTime() >= 1)) {
									character.setHealth(character.getHealth() - skunkoon.getDamage());
									character.setElapsedTime(0);
									enemyBullets[k] = null;
								}
							}
						}
					}
					// House 5 Stuff
					if (skunkoon != null) {
						skunkoon.setElapsedTime(clock.getElapsedTime());
					}
					character.setElapsedTime(clock.getElapsedTime());
					if (skunkoon != null && skunkoon.getElapsedTime() >= 2) {
						skunkoon.setElapsedTime(0);
						int bulletNum = 0;
						for (int i = 0; i < 1000; i++) {
							if (enemyBullets[i] == null && skunkoon != null) {
								if (bulletNum == 0) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() - 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 1) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 2) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() + 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 3) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() - 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 4) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() - 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() - 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 5) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() + 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() - 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 6) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() + 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else if (bulletNum == 7) {
									enemyBullets[i] = new EnemyBullet(skunkoon.getPositionX() + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + skunkoon.getHeight() / 2, bulletSprite,
											skunkoon.getPositionX() - 10 + skunkoon.getWidth() / 2,
											skunkoon.getPositionY() + 10 + skunkoon.getHeight() / 2);
									bulletNum++;
								} else {
									break;
								}
							}
						}
					}
					for (int i = 0; i < 1000; i++) {
						if (enemyBullets[i] != null) {
							enemyBullets[i].setPositionX(
									enemyBullets[i].getPositionX() + Math.cos(enemyBullets[i].getAngle()) * 3);
							enemyBullets[i].setPositionY(
									enemyBullets[i].getPositionY() + Math.sin(enemyBullets[i].getAngle()) * 3);
							enemyBullets[i].updateX(0);
							enemyBullets[i].updateY(0);
							g.drawImage(bananaSprite, (int) enemyBullets[i].getPositionX(),
									(int) enemyBullets[i].getPositionY(), enemyBullets[i].getWidth(),
									enemyBullets[i].getHeight(), null);
							if ((skunkoon != null) && (character.getBox().intersects(enemyBullets[i].getBox()))
									&& (character.getElapsedTime() >= 1)) {
								character.setHealth(character.getHealth() - skunkoon.getDamage());
								character.setElapsedTime(0);
								enemyBullets[i] = null;
							}

						}
					}
					if (skunkoon != null) {
						skunkoon.setPositionX(skunkoon.getPositionX()
								+ Math.cos(skunkoon.getAngle(character.getPositionX(), character.getPositionY()))
										* 0.5);
						skunkoon.setPositionY(skunkoon.getPositionY()
								+ Math.sin(skunkoon.getAngle(character.getPositionX(), character.getPositionY()))
										* 0.5);
						g.drawImage(skunkoonSprite, (int) skunkoon.getPositionX(), (int) skunkoon.getPositionY(),
								skunkoon.getWidth(), skunkoon.getHeight(), null);
						if (skunkoon != null && skunkoon.getHealth() <= 0) {
							skunkoon = null;
						}
					}
				}
				// Inside house one door collision
				///////////

				// Level complete back to main map collision//

				// Inside house one door collision
				if (!enemies) {
					if ((houseOneMap) && (character.getBox().intersects(insideDoor[0]))) {
						mainMap = true;
						houseOneMap = false;
						houseOneComplete = true;
						resized = false;
						pocket.addItem(lemonade);
						pocket.addItem(peanutButter);
					}

					// Inside house two door collision
					if ((houseTwoMap) && (character.getBox().intersects(insideDoor[1]))) {
						mainMap = true;
						houseTwoMap = false;
						houseTwoComplete = true;
						resized = false;
						pocket.addItem(chocolate);
						pocket.addItem(book);
					}

					// Inside house three door collision
					if ((houseThreeMap) && (character.getBox().intersects(insideDoor[2]))) {
						mainMap = true;
						houseThreeMap = false;
						houseThreeComplete = true;
						resized = false;
						pocket.addItem(apple);
						pocket.addItem(cookies);
					}

					// Inside house four door left collision (left door in left door out)
					if ((houseFourMapLeft) && (character.getBox().intersects(insideDoor[3]))) {
						mainMap = true;
						houseFourMapRight = false;
						houseFourMapLeft = false;
						houseFourCompleteLeft = true;
						houseFourCompleteRight = true;
						resized = false;
						pocket.addItem(apple);
						pocket.addItem(cookies);
					}

					// Inside house four door right collision (right door in right door out)
					if ((houseFourMapRight) && (character.getBox().intersects(insideDoor[4]))) {
						mainMap = true;
						houseFourMapRight = false;
						houseFourMapLeft = false;
						houseFourCompleteLeft = true;
						houseFourCompleteRight = true;
						resized = false;
						pocket.addItem(sandwich);
						pocket.addItem(key);
					}

					// Inside house four door left collision (left door in right door out)
					if ((houseFourMapLeft) && (character.getBox().intersects(insideDoor[4]))) {
						mainMap = true;
						gameMainMap.setPositionX(gameMainMap.getPositionX()
								- (outsideDoor[4].getPositionX() - outsideDoor[3].getPositionX()));
						houseFourMapRight = false;
						houseFourMapLeft = false;
						houseFourCompleteLeft = true;
						houseFourCompleteRight = true;
						resized = false;

					}

					// Inside house four door right collision (right door in left door out)
					if ((houseFourMapRight) && (character.getBox().intersects(insideDoor[3]))) {
						mainMap = true;
						gameMainMap.setPositionX(gameMainMap.getPositionX()
								+ (outsideDoor[4].getPositionX() - outsideDoor[3].getPositionX()));
						houseFourMapRight = false;
						houseFourMapLeft = false;
						houseFourCompleteLeft = true;
						houseFourCompleteRight = true;
						resized = false;

					}

					// Inside house five door collision
					if ((houseFiveMap) && (character.getBox().intersects(insideDoor[5]))) {
						mainMap = true;
						houseFiveMap = false;
						houseFiveComplete = true;
						resized = false;

					}
				} else {
					if ((houseOneMap) && (character.getBox().intersects(insideDoor[0]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
					if ((houseTwoMap) && (character.getBox().intersects(insideDoor[1]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
					if ((houseThreeMap) && (character.getBox().intersects(insideDoor[2]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
					if ((houseFourMapLeft) && (character.getBox().intersects(insideDoor[4]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
					if ((houseFourMapRight) && (character.getBox().intersects(insideDoor[3]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
					if ((houseFiveMap) && (character.getBox().intersects(insideDoor[5]))) {
						character.setPositionY(character.getPositionY() - walkSpeedHouse);
					}
				}
				// after inside door, exit to map from house
				/////////////////////////////////////////////////

				// Exits from house to main map

			}
			if (itemUsed) {
				if (item instanceof Food) {
					if (character.getHealth() + ((Food) item).getNutritionValue() <= 100) {
						character.setHealth(character.getHealth() + ((Food) item).getNutritionValue());
					} else {
						character.setHealth(100);
					}
					itemUsed = false;
				} else {
					keyNum = ((Key) item).getKeyNum();
				}
			}

			if (pocketOpen) {
				newPocketImage = pocketImage.getScaledInstance(770, 560, Image.SCALE_DEFAULT);
				g.drawImage(newPocketImage, 60, 125, this);
				g.setColor(Color.WHITE);
				for (int i = 0; i < 12; i++) {
					if (pocket.getItem(i) != null) {
						if (i == pocketNum) {
							newItem = pocket.getItem(i).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
							g.drawImage(newItem, 61 + ((i % 3) * 133), 130 + ((i / 3) * 133), this);
							newItem = pocket.getItem(i).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
							g.drawImage(newItem, 679, 157, this);
							g.setFont(new Font("TimesRoman", Font.BOLD, 19));
							g.drawString(pocket.getItem(i).getName(), 550, 235);
							if (pocket.getItem(i) instanceof Food) {
								g.drawString("Type: Food", 550, 340);
								g.drawString(pocket.getItem(i).getDescription(), 550, 380);
							} else {
								g.drawString("Type: Key", 550, 340);
								g.drawString(pocket.getItem(i).getDescription().substring(0,
										pocket.getItem(i).getDescription().indexOf(".")), 550, 380);
								g.drawString(pocket.getItem(i).getDescription()
										.substring(pocket.getItem(i).getDescription().indexOf(".") + 2), 550, 400);
							}
							g.drawString("Press space to use", 550, 530);
						} else {
							newItem = pocket.getItem(i).getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT);
							g.drawImage(newItem, 81 + ((i % 3) * 133), 150 + ((i / 3) * 133), this);
						}
					}
				}
				g.drawRect(81 + ((pocketNum % 3) * 133), 150 + ((pocketNum / 3) * 133), 110, 110);
			}
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 450, 45);
			g.setColor(Color.BLACK);
			g.fillRect(30, 10, 400, 25);
			g.setFont(new Font("TimesRoman", Font.BOLD, 15));
			g.drawString("HP", 5, 28);
			g.setColor(Color.RED);
			g.fillRect(30, 10, character.getHealth() * 4, 25);
		}
	}

// ----------- Inner class for the keyboard listener - this detects key presses
// and runs the corresponding code
	private class MyKeyListener implements KeyListener {
		// Box area (all num has to be mutiples of moveSpace)

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("P")) { // If 'P' is pressed
				if (pocketOpen) {
					pocketOpen = false;
				} else {
					pocketOpen = true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // If ESC is pressed
				System.exit(0);
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (pocketOpen) {
					if (pocketNum > 2) {
						pocketNum -= 3;
					}
				} else {
					direction[0] = true;
					lastPressed = 0;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (pocketOpen) {
					if (pocketNum < 9) {
						pocketNum += 3;
					}
				} else {
					direction[1] = true;
					lastPressed = 1;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (pocketOpen) {
					if (pocketNum < 11) {
						pocketNum++;
					}
				} else {
					direction[2] = true;
					lastPressed = 2;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (pocketOpen) {
					if (pocketNum > 0) {
						pocketNum--;
					}
				} else {
					direction[3] = true;
					lastPressed = 3;
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				direction[0] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				direction[1] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				direction[2] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				direction[3] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (pocketOpen) {
					item = pocket.useItem(pocketNum);
					itemUsed = true;
				} else {
					space = true;
				}
			}
		}
	} // end of keyboard listener

// ----------- Inner class for the keyboard listener - This detects mouse
// movement & clicks and runs the corresponding methods
	private class MyMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			System.out.println("Mouse Clicked");
			System.out.println("X:" + e.getX() + " y:" + e.getY());
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	} // end of mouselistener

}
//A class to track time

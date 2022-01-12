import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/*
 * Character
 * Version 5
 * Emily Huang
 * January 19, 2020
 * The character class is a class that contains all the movement sprites
 * and it is the main character that the player controls.
 */

class Character extends MovingEntity {
  
  private int health;
  private int direction;
  private double elapsedTime;
  BufferedImage[] movementSprites;
  //BufferedImage[] shootingSprites;
  int currentSprite;
  //int currentStep;
  
  Character (int x, int y, BufferedImage image, int width, int height, int health) {
    super(x, y, image, width, height);
    this.health = health;
    loadSprites();
    currentSprite = 0;
    elapsedTime = 0;
    this.direction = 5;
  }
  
  /*
   * getHealth
   * This method returns the health of the character
   * @return health of the character
   */
  public int getHealth() {
    return this.health;
  }
  
  /*
   * setHealth
   * This method takes in a value and sets the health of the character as the value
   * @param health, the new health of the character
   */
  public void setHealth(int health) {
    this.health = health;
  }
  
  /*
   * loadSprites
   * This method reads from a sprite sheet and loads in the needed sprites for proper movement.
   */
  public void loadSprites() { 
	  try {
		  //BufferedImage sheet = ImageIO.read(new File("spriteSheet.png"));
		  BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("Spritesheet.png"));

		  final int width = 64;
		  final int height = 64;
		  int rows = 4;
		  int colsMovement = 9;
		  int colsShoot = 13;
		  movementSprites = new BufferedImage[rows * colsMovement + rows * colsShoot];

		  for (int j = 0; j < rows; j++) {
			  for (int i = 0; i < colsMovement; i++) {
				  movementSprites[(j * colsMovement) + i] = sheet.getSubimage(i * width, j * height + 512, width, height);
			  }
		  }
			
		  for (int j = 0; j < rows; j++) { 
			  for (int i = 0; i < colsShoot; i++) { 
				  movementSprites[((j * colsShoot) + i) + (rows * colsMovement)] = sheet.getSubimage(i * width, j * height + 1024, width, height);
			  }
		  }
			 
	  } catch(Exception e) { System.out.println("error loading sheet");};
  }

  /*
   * getCurrentSprite
   * This method returns the current needed sprite.
   * @return BufferedImage containing current sprite.
   */
  public BufferedImage getCurrentSprite() {
	  //System.out.println(currentSprite);
	  return movementSprites[currentSprite];
  }
  
  /*
   * updateSprites
   * This method updates which sprite should be showing at the time,
   * depending on the direction. 0 is for up, 1 is for down, 2 is for right,
   * 3 is for left, 5 is for standing still, 6 is for up and shooting, 7 is for down and shooting,
   * 8 is for right and shooting, 9 is for left and shooting.
   */
  public void updateSprites() { 
	  //System.out.println("called " + direction);
	  if(direction == 0) { 
		  currentSprite++;
		  if (currentSprite>=9) {
			  currentSprite=0;
		  }
	  }

	  if(direction == 3) { 
		  currentSprite++;
		  if (currentSprite>=18) {
			  currentSprite=9;
		  }
	  }

	  if(direction == 1) { 
		  currentSprite++;
		  if (currentSprite>=27) {
			  currentSprite=18;
		  }
	  }

	  if(direction == 2) { 
		  currentSprite++;
		  if (currentSprite>=36) {
			  currentSprite=27;
		  } 
	  }
	  if(direction == 6) { 
		  currentSprite++;
		  if (currentSprite>=49) {
			  currentSprite=36;
		  } 
	  }
	  if(direction == 9) { 
		  currentSprite++;
		  if (currentSprite>=62) {
			  currentSprite=49;
		  } 
	  }
	  if(direction == 7) { 
		  currentSprite++;
		  if (currentSprite>=75) {
			  currentSprite=62;
		  } 
	  }
	  if(direction == 8) { 
		  currentSprite++;
		  if (currentSprite>=88) {
			  currentSprite=75;
		  } 
	  }
  }

  /*
   * move
   * This method accepts an integer containing a value that is the direction
   * that the character was last moving in and sets the current sprite to the
   * correct sprite moving in the direction.
   * @param movement, An integer that contains a value representing the direction the character was last moving in,
   * the directions are defined in the last method block comment
   */
  public void move(int movement) { 
	  direction = movement;
	  //System.out.println(direction);
	  if((movement == 0) && (currentSprite >= 9) && ((currentSprite <= 36) || (currentSprite >= 88))) {       
		  currentSprite = 0;
	  } else if((movement == 3) && ((currentSprite <= 8) || (currentSprite >= 18)) && ((currentSprite <= 36) || (currentSprite >= 88))) {
		  currentSprite = 9;
	  } else if((movement == 1) && ((currentSprite <= 17) || (currentSprite >= 27)) && ((currentSprite <= 36) || (currentSprite >= 88))) {
		  currentSprite = 18;
	  } else if((movement == 2) && ((currentSprite <= 26) || (currentSprite >= 36)) && ((currentSprite <= 36) || (currentSprite >= 88))) {
		  currentSprite = 27;
	  }else if((movement == 6) && ((currentSprite <= 35) || (currentSprite >= 49))) {
		  currentSprite = 36;
	  }else if((movement == 9) && ((currentSprite <= 48) || (currentSprite >= 62))) {
		  currentSprite = 49;
	  }else if((movement == 7) && ((currentSprite <= 61) || (currentSprite >= 75))) {
		  currentSprite = 62;
	  }else if((movement == 8) && ((currentSprite <= 74) || (currentSprite >= 88))) {
		  currentSprite = 75;
	  }else if((movement == 5) && ((currentSprite <= 36) || (currentSprite >= 88))) {
		  currentSprite = 18;
	  }
  }
  public double getElapsedTime(){
	  return elapsedTime;
  }
  public void setElapsedTime(double elapsedTime){
	  if(elapsedTime == 0) {
		  this.elapsedTime = 0;
	  }else{
		  this.elapsedTime += elapsedTime;
	  }
  }
}
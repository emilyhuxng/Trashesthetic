import java.awt.image.BufferedImage;
class Banana extends Enemy{
  //private int characterX, characterY;
  private double elapsedTime;
  private boolean move;
  private int direction;
  Banana(int x, int y, BufferedImage image, int width, int height, int health, int damage){
    super(x, y, image, width, height, health, damage);
    //this.characterX = characterX;
    //this.characterY = characterY;
    elapsedTime = 0;
    move = false;
  }
  //up: 0 
  //down: 1
  //right: 2
  //left: 3
  public void moveDirection(double characterX, double characterY){
    int maxDistance = Integer.MIN_VALUE;
    direction = 3;
    if(Math.abs((int)characterX - (int)this.getPositionX()) > maxDistance){
      maxDistance = Math.abs((int)characterX - (int)this.getPositionX());
      if(characterX < this.getPositionX()){
        direction = 3;
      }else{
        direction = 2;
      }
    }
    if(Math.abs((int)characterY - (int)this.getPositionY()) > maxDistance){
      maxDistance = Math.abs((int)characterY - (int)this.getPositionY());
      if(characterY < this.getPositionY()){
        direction = 0;
      }else{
        direction = 1;
      }
    }
    //If maxDistance is 0, that means the paper is on the person so it shouldn't move
    if(maxDistance == 0){
      direction = 5;
    }
  }
  public double getElapsedTime(){
    return elapsedTime;
  }
  public void setElapsedTime(double elapsedTime){
    if(elapsedTime == 0){
      this.elapsedTime = 0;
    }else{
      this.elapsedTime += elapsedTime;
    }
  }
  public boolean getMove(){
    return move;
  }
  public void setMove(boolean move){
    this.move = move;
  }
  public int getDirection() {
	return direction;
  }
}
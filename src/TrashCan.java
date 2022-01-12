import java.awt.image.BufferedImage;
class TrashCan extends Enemy{
  private double elapsedTime;
  private BufferedImage bullet;
  TrashCan(int x, int y, BufferedImage image, int width, int height, int health, int damage){
    super(x, y, image, width, height, health, damage);
    elapsedTime = 0;
  }
  /*public Bullet shoot(){
    return new Bullet(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.direction(), bullet);
  }*/
  public double getElapsedTime(){
    return elapsedTime;
  }
  public void setElapsedTime(double elapsedTime){
	  if(elapsedTime == 0) {
		  this.elapsedTime = 0;
	  }else {
		  this.elapsedTime += elapsedTime;
	  }
  }
}
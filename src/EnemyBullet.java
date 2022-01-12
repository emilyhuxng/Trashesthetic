import java.awt.image.BufferedImage;
class EnemyBullet extends MovingEntity{
	  private double characterX, characterY;
	  private double angle;
	  EnemyBullet(double x, double y, BufferedImage image, double characterX, double characterY){
	    super(x, y, image, 15, 15);
	    this.characterX = characterX;
	    this.characterY = characterY;
	    angle = Math.atan2(characterY-this.getPositionY(), characterX-this.getPositionX());
	  }
	  public double getAngle() {
		  return angle;
	  }

}

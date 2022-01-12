import java.awt.image.BufferedImage;
class SodaCan extends Enemy{
  private double elapsedTime;
  private int radius;
  private int arcAngle;
  private double startAngle;
  SodaCan(double x, double y, BufferedImage image, int width, int height, int health, int damage){
    super(x, y, image, width, height, health, damage);
    elapsedTime = 0;
    arcAngle = 40;
    radius = 250;
  }
  public void shoot(double move){
    startAngle += move;
    if(startAngle >= 360) {
    	startAngle = 0;
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
  public double getStartAngle() {
	  return startAngle;
  }
  public int getArcAngle() {
	  return arcAngle;
  }
  public int getRadius() {
	  return radius;
  }
}
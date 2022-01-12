import java.awt.image.BufferedImage;
public class Skunkoon extends Enemy{
	//private double characterX, characterY;
	private double angle;
	private double elapsedTime;
    Skunkoon(double x, double y, BufferedImage image, int width, int height, int health, int damage){
    	super(x, y, image, width, height, health, damage);
	    //this.characterX = characterX;
	    //this.characterY = characterY;
    }
    public double getAngle(double characterX, double characterY) {
    	angle = Math.atan2(characterY-this.getPositionY(), characterX-this.getPositionX());
    	return angle;
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

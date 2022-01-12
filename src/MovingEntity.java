import java.awt.image.BufferedImage;
import java.awt.Rectangle;

abstract class MovingEntity extends Entity { 
    
  private int height,width;
  private Rectangle boundingBox; //rectangle that is used for collision detection
  
  MovingEntity(double x, double y, BufferedImage image, int width, int height){
    super(x, y, image);
    this.width = width;
    this.height = height;
    this.boundingBox = new Rectangle((int)this.getPositionX(), (int)this.getPositionY(), width, height);
  } 
  
  public void updateX(double x){
    //update the content
    boundingBox.x = (int)(this.getPositionX() + x);
  }
  public void updateY(double y){
    //update the content
    boundingBox.y = (int)(this.getPositionY() + y);
  }
  
  public int getWidth(){
    return this.width;
  }
  
  public int getHeight(){
    return this.height;
  }
  
  public void setWidth(int width){
    this.width = width;
  }
  
  public void setHeight(int height){
    this.height = height;
  }
  
  public Rectangle getBox() {
    return this.boundingBox;
  }
  
}
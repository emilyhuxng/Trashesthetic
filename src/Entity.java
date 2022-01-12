import java.awt.image.BufferedImage;
abstract class Entity{
  private double x, y;
  private BufferedImage image;
  Entity(double x, double y, BufferedImage image){
    this.x = x; 
    this.y = y;
    this.image = image;
  }
  public double getPositionX(){
    return this.x;
  }
  public void setPositionX(double x){
    this.x = x;
  }
  public double getPositionY(){
    return this.y;
  }
  public void setPositionY(double y){
    this.y = y;
  }
  public BufferedImage getImage(){
    return this.image;
  }
  public void setImage(BufferedImage image){
    this.image = image;
  }
}
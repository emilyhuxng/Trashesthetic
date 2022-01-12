import java.awt.image.BufferedImage;

class Bullet extends MovingEntity{
  
  private int direction;
  
  Bullet(int x, int y, BufferedImage image, int direction){
    super(x, y, image, 15, 15);
    this.direction = direction;
  }
  
  public int getDirection() {
    return this.direction;
  }
  
}
import java.awt.image.BufferedImage;

class Key extends Item{
  
  private int keyNum;
  
  Key (double x, double y, BufferedImage image, String name, String description, int keyNum){
    super(x, y, image, name, description);
    this.keyNum = keyNum;
  }
  
  public int getKeyNum(){
    return this.keyNum;
  }
}
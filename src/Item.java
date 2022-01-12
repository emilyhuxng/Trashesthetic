import java.awt.image.BufferedImage;

abstract class Item extends Entity {

  private String name;
  private String description;
  
  Item (double x, double y, BufferedImage image, String name, String description){
    super(x, y, image);
    this.name = name;
    this.description = description;
  }
  
  public String getDescription(){
    return this.description;
  }
  
  public String getName(){
    return this.name;
  }
  
}
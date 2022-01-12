import java.awt.image.BufferedImage;

class Villager extends MovingEntity{
  
  private Food food;
  private Key key;
  private String name;
  
  Villager(int x, int y, BufferedImage image, int width, int height, String name){
    super(x, y, image, width, height);
    this.name = name;
  }
  
  public Food getFood(){
    return this.food;
  }
  
  public void setFood(Food food) {
    this.food = food;
  }
  
  public Key getKey(){
    return this.key;
  }
  
  public void setKey(Key key) {
    this.key = key;
  }
  
  public String getName(){
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
}
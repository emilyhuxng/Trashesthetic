import java.awt.image.BufferedImage;
abstract class Enemy extends MovingEntity{
  private int health;
  private boolean harmful;
  private int damage;
  Enemy(double x, double y, BufferedImage image, int width, int height, int health, int damage){
    super(x, y, image, width, height);
    this.health = health;
    this.harmful = true;
    this.damage = damage;
  }
  public int getHealth(){
    return this.health;
  }
  public void setHealth(int health){
    this.health = health;
  }
  public boolean getHarmful(){
    return this.harmful;
  }
  public void setHarmful(boolean harmful){
    this.harmful = harmful;
  }
  public int getDamage() {
	  return damage;
  }
  public void setDamage(int damage) {
	  this.damage = damage;
  }
}
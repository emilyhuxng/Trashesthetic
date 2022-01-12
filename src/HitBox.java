class Hitbox extends MovingEntity{
  
  Hitbox(int x, int y, int width, int height){
    super(x, y, null, width, height);
  }
  
  public void updateBox (double x, double y) {
    this.setPositionX((int)(x + this.getPositionX()));
    this.setPositionY((int)(y + this.getPositionY()));
  }
  
}
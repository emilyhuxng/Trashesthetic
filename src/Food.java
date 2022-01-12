import java.awt.image.BufferedImage;

class Food extends Item{
  
  private int nutritionValue;
  
  Food (double x, double y, BufferedImage image, String name, String description, int nutritionValue){
    super(x, y, image, name, description);
    this.nutritionValue = nutritionValue;
  }
  
  public int getNutritionValue(){
    return this.nutritionValue;
  }
}
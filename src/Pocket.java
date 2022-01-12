class Pocket {
  
  private Item[] pocket;
  
  Pocket() {
    this.pocket = new Item[12];
  }
  
  public void addItem(Item item) {
    for (int i = 0; i < 12; i ++) {
      if (pocket[i] == null) {
        pocket[i] = item;
        item = null;
      }
    }
  }
  
  public Item useItem(int itemNum) {
    Item temp = pocket[itemNum];
    for (int i = itemNum; i < 11; i ++) {
      pocket[i] = pocket[i + 1];
      pocket[i + 1] = null;
    }
    return temp;
  }
  
  public Item getItem (int itemNum) {
    return pocket[itemNum];
  }
  
}
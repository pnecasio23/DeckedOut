/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Food extends Item {

    public static ArrayList<Food> foodList = new ArrayList<Food>();
    public double restoreHunger;
    public double restoreSaturation;
    public int base_price;
    public int current_price;
    
    public Food(int i, String n, double h, double s, int p) {
        super(i, n);
        this.itemName = n;
        this.restoreHunger = h;
        this.restoreSaturation = s;
        this.base_price = p;
        this.current_price = p;
        foodList.add(this);
    }
    
    public void eat(Player player) {
        player.setFoodLevel(player.getFoodLevel() + this.restoreHunger);
        player.setFoodSaturationLevel(player.getFoodSaturationLevel() + this.restoreSaturation);
        
        if (player.getFoodLevel() > player.getFoodMaxLevel()) player.setFoodLevel(player.getFoodMaxLevel());
        
        player.getInv().removeItem(this);
    }
}

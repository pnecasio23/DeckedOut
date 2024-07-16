/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Potion extends Item {

    public ArrayList<Potion> potions;
    public static ArrayList<Potion> potionList = new ArrayList<Potion>();
    public String type;
    public int duration;
    public int potency;
    public int base_price;
    public int current_price;
    
    public Potion(int i, String n, String t, int d, int p, int pr) {
        super(i, n);
        this.type = t;
        this.duration = d;
        this.potency = p;
        this.base_price = pr;
        this.current_price = pr;
        potionList.add(this);
    }
    
    public Potion(int i, String n, String t, int d, int p) {
        super(i, n);
        this.itemName = n;
        this.type = t;
        this.duration = d;
        this.potency = p;
    }

    public void addStockItem(Potion item) {
        potions.add(item);
    }

    public int size() {
        return potions.size();
    }
    
    public void drink(Player player) {
        if (this.type.equalsIgnoreCase("healing")) {
            player.setHP(player.getHP() + (this.potency * 4));
        }
        if (this.type.equalsIgnoreCase("harming")) {
            player.setHP(player.getHP() - (this.potency * 6));
        }
        
        player.getInv().removeItem(this);
    }
}

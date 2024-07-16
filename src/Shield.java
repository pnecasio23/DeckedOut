/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Shield extends Item {

    public static ArrayList<Shield> shieldList = new ArrayList<Shield>();
    public double durability;
    public double maxDurability;
    public int base_price;
    public int current_price;
    
    public Shield(int i, String n, double du, int p) {
        super(i, n);
        this.itemName = n;
        this.durability = du;
        this.maxDurability = du;
        this.base_price = p;
        this.current_price = p;
        shieldList.add(this);
    }
    
    public Shield(int i, String n, double du) {
        super(i, n);
        this.itemName = n;
        this.durability = du;
        this.maxDurability = du;
    }
    
    public static void equip(Player player, Shield shield) {
        player.setShield(shield);
    }
}

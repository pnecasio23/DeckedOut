/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Armor extends Item {

    public static ArrayList<Armor> armorList = new ArrayList<Armor>();
    public double addDP;
    public double toughness;
    public double durability;
    public double maxDurability;
    public int base_price;
    public int current_price;
    
    public Armor(int i, String n, double d, double t, double du, int p) {
        super(i, n);
        this.addDP = d;
        this.toughness = t;
        this.durability = du;
        this.maxDurability = du;
        this.base_price = p;
        this.current_price = p;
        armorList.add(this);
    }
    
    public Armor(int i, String n, double d, double t, double du) {
        super(i, n);
        this.addDP = d;
        this.toughness = t;
        this.durability = du;
        this.maxDurability = du;
    }
    
    public static void equip(Player player, Armor armor) {
        player.setArmor(armor);
        player.setDP(armor.addDP);
    }
    
}

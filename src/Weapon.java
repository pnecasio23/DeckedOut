/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Weapon extends Item {

    public static ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
    public double addAP;
    public double durability;
    public double maxDurability;
    public int base_price;
    public int current_price;
    
    public Weapon(int i, String n, double a, double du, int p) {
        super(i, n);
        this.itemName = n;
        this.addAP = a;
        this.durability = du;
        this.maxDurability = du;
        this.base_price = p;
        this.current_price = p;
        weaponList.add(this);
    }
    
    public Weapon(int i, String n, double a, double du) {
        super(i, n);
        this.itemName = n;
        this.addAP = a;
        this.durability = du;
        this.maxDurability = du;
    }
    
    public static void equip(Player player, Weapon weapon) {
        player.setWeapon(weapon);
        player.setAP(weapon.addAP);
    }
}

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class PassiveEntity extends Entity {
    
    public static ArrayList<PassiveEntity> passiveEntityList = new ArrayList<PassiveEntity>();
    public String mobType;
    public String message;
    
    public PassiveEntity(String n, String t, String m) {
        super(n, "passive", 1, 1, 1, 1);
        this.mobType = t;
        this.message = m;
        passiveEntityList.add(this);
    }
    
    public static void initEntities() {
        new PassiveEntity("Birb", "parrot", "*breakdances*");
        new PassiveEntity("Jellie", "cat", "meow!");
        new PassiveEntity("Beeralis", "bee", "Look into my eyes, nothing but my eyes!");
        new PassiveEntity("Matilda", "llama", "*angrily spits*");
    }
    
    public void entitySpawn(Dungeon dungeon, Player player) {};
    
    public static void spawn() {
        int rand;
        rand = (int) Math.ceil(Math.random() * 100);
        
        if (rand > 90) {
            rand = (int) Math.ceil(Math.random() * (passiveEntityList.size() - 1));

            passiveEntityList.get(rand).isSpawned = true;

            System.out.println(passiveEntityList.get(rand).name + " came to say hi!");
            System.out.println("'" + passiveEntityList.get(rand).message + "'");
        }
    }
    
    public void entityAI(Dungeon dungeon, Player player) {
        if (this.isSpawned) {
            
        }
    }
    
    public void entityMove(Dungeon dungeon, Player player, int t, boolean isRandom) {
        if (this.isSpawned) {
            
        }
    }
    
    public boolean checkHealth() {
        if (this.HP <= 0) {
            return false;
        }
        else {return true;}
    }
   
}

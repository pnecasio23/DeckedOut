/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class LootTable {

    public int minRolls, maxRolls, totalWeight;
    public LootEntry[] pools;
    public ArrayList<LootEntry> tempLoot = new ArrayList<LootEntry>();
    
    public LootTable(int min, int max, LootEntry[] p) {
        this.minRolls = min;
        this.maxRolls = max;
        this.pools = p;
        
        for (int i = 0; i <= (pools.length - 1); i++) {
            for (int j = 0; j <= (pools[i].weight - 1); j++) {
                tempLoot.add(pools[i]);
                //System.out.println(pools[i].entry.itemName);
            }
            totalWeight += pools[i].weight;
        }
        //System.out.println("Total Weight: " + this.totalWeight);
    }
    
    public void generateLoot(Player p) {
        int rand, randNum, rolls;
        rolls = this.minRolls + (int) (Math.random() * ((this.maxRolls - this.minRolls) + 1));
        
        for (int i = 0; i < rolls; i++) {
            rand = (int) Math.ceil(Math.random() * (this.totalWeight));
            
            randNum = this.tempLoot.get(rand - 1).minCount + (int)(Math.random() * ((this.tempLoot.get(rand - 1).maxCount - this.tempLoot.get(rand - 1).minCount) + 1));
            
            for (int j = 0; j < randNum; j++) {
                if (this.tempLoot.get(rand - 1).entry.itemName.equalsIgnoreCase("Dungeon Coin")) p.getInv().addCoins(1);
                else if (this.tempLoot.get(rand - 1).entry.itemName.equalsIgnoreCase("Dungeon Key")) p.getInv().addKey();
                else if (this.tempLoot.get(rand - 1).entry.itemName.equalsIgnoreCase("Dungeon Lootbox")) p.getInv().addLootbox();
                else if (!p.getInv().isMiscInvFull(this.tempLoot.get(rand - 1).entry)) p.getInv().addItem(this.tempLoot.get(rand - 1).entry);
                else {
                    System.out.println("Misc inventory is full! Try again");
                    break;
                }
            }
            System.out.println("You received " + randNum + " " + this.tempLoot.get(rand - 1).entry.itemName);
        }
        System.out.println("------------------------");
    }
    
    public static void initLoots(Player p) throws InterruptedException {
        
        
    }
}

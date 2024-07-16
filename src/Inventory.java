/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Inventory {

    private FoodSlot[] foods;
    private ArtifactBoard artifacts;
    private CardSet cards;
    private PotionSlot[] potions;
    private MiscSlot[] misc;
    
    public ArrayList<MiscItem> keys;
    public ArrayList<MiscItem> coins;
    public ArrayList<MiscItem> lootboxes;
    
    MiscItem coin = new MiscItem(1, "Dungeon Coin");
    MiscItem key = new MiscItem(2, "Dungeon Key");
    MiscItem lootbox = new MiscItem(1, "Dungeon Lootbox");

    public Inventory() {
        foods = new FoodSlot[10];
        for (int i = 0; i < foods.length; i++) foods[i] = new FoodSlot(); 
        artifacts = new ArtifactBoard();
        cards = new CardSet();
        potions = new PotionSlot[10];
        for (int i = 0; i < potions.length; i++) potions[i] = new PotionSlot();
        misc = new MiscSlot[20];
        for (int i = 0; i < misc.length; i++) misc[i] = new MiscSlot(); 
        
        keys = new ArrayList<MiscItem>();
        coins = new ArrayList<MiscItem>();
        lootboxes = new ArrayList<MiscItem>();
    }
    
    public void checkInventory() {
        System.out.println("You have: ");
        if (noOfKeys() > 0) System.out.println(noOfKeys() + " keys");
        if (noOfCoins() > 0) System.out.println(noOfCoins() + " coins");
        if (noOfLootBox() > 0) System.out.println(noOfLootBox() + " lootboxes");
        System.out.println("------------------------");
        System.out.println("Your foods: ");
        for (int i = 0; i <= foods.length - 1; i++) {
            if (foods[i].item != null) System.out.println((i + 1) + ": " + foods[i].item.itemName + " [" + foods[i].amount + "]");
        }
        System.out.println("------------------------");
        System.out.println("Your potions: ");
        for (int i = 0; i <= potions.length - 1; i++) {
            if (potions[i].item != null) System.out.println((i + 1) + ": " + potions[i].item.itemName);
        }
        System.out.println("------------------------");
        System.out.println("Your misc items: ");
        for (int i = 0; i <= misc.length - 1; i++) {
            if (misc[i].item != null) System.out.println((i + 1) + ": " + misc[i].item.itemName + " [" + misc[i].amount + "]");
        }
    }
        
    public void addItem(Food f) {
        for (int i = 0; i <= (foods.length - 1); i++) {
            if (foods[i].item == null) {
                foods[i].item = f;
                foods[i].amount++;
                break;
            } 
            if (foods[i].item.id == f.id) {
                foods[i].amount++;
                break;
            }
        }
    }
    public void removeItem(Food f) {
        for (int i = 0; i <= (foods.length - 1); i++) {
            if (foods[i].item == null) {}
            else {
                if (foods[i].item.id == f.id) {
                    foods[i].amount--;

                    if (foods[i].amount <= 0) {
                        foods[i].item = null;
                    }
                    break;
                }
            }
        }
    }
    public boolean isFoodInvFull(Food f) {
        for (int i = 0; i <= (foods.length - 1); i++) {
            if (foods[i].item == null) {
                return false;
            }
            if (foods[i].item == f) {
                return false;
            }
        }
        return true;
    }
    public boolean isFoodInvEmpty() {
        for (int i = 0; i <= (foods.length - 1); i++) {
            if (foods[i].item != null) {
                return false;
            }
        }
        return true;
    }
    
    
    public void addItem(MiscItem m) {
        for (int i = 0; i <= (misc.length - 1); i++) {
            if (misc[i].item == null) {
                misc[i].item = m;
                misc[i].amount++;
                break;
            } 
            if (misc[i].item.id == m.id) {
                misc[i].amount++;
                break;
            }
        }
    }
    public void removeItem(MiscItem m) {
        for (int i = 0; i <= (misc.length - 1); i++) {
            if (misc[i].item == null) {}
            else {
                if (misc[i].item.id == m.id) {
                    misc[i].amount--;

                    if (misc[i].amount <= 0) {
                        misc[i].item = null;
                    }
                    break;
                }
            }
        }
    }
    public boolean isMiscInvFull(MiscItem m) {
        for (int i = 0; i <= (misc.length - 1); i++) {
            if (misc[i].item == null) {
                return false;
            }
            if (misc[i].item == m) {
                return false;
            }
        }
        return true;
    }
    public boolean isMiscInvEmpty() {
        for (int i = 0; i <= (misc.length - 1); i++) {
            if (misc[i].item != null) {
                return false;
            }
        }
        return true;
    }
    
    
    public void addItem(Potion p) {
        for (int i = 0; i <= (potions.length - 1); i++) {
            if (potions[i].item == null) {
                potions[i].item = p;
                potions[i].amount = 1;
                break;
            }
        }
    }
    public void removeItem(Potion p) {
        for (int i = 0; i <= (potions.length - 1); i++) {
            if (potions[i].item == null) {}
            else {
                if (potions[i].item.id == p.id) {
                    potions[i].item = null;
                    break;
                }
            }
        }
    }
    public boolean isPotionInvFull() {
        for (int i = 0; i <= (potions.length - 1); i++) {
            if (potions[i].item == null) {
                return false;
            }
        }
        return true;
    }
    public boolean isPotionInvEmpty() {
        for (int i = 0; i <= (potions.length - 1); i++) {
            if (potions[i].item != null) {
                return false;
            }
        }
        return true;
    }
    
    
    public void addCoins(int amount) {
        for (int i = 0; i <= amount - 1; i++) {
            coins.add(coin);
        }
    }
    
    public void removeCoins(int amount) {
        for (int i = 0; i <= amount - 1; i++) {
            coins.remove(coin);
        }
    }
    
    public void addKey() {
        keys.add(key);
    }
    public void removeKey() {
        keys.remove(key);
    }
    public void addLootbox() {
        lootboxes.add(lootbox);
    }
    public void removeLootbox() {
        lootboxes.remove(lootbox);
    }
    
    public ArtifactBoard getArtifacts() {
        return artifacts;
    }
    public CardSet getCards() {
        return cards;
    }
    public PotionSlot[] getPotions() {
        return potions;
    }
    public FoodSlot[] getFoods() {
        return foods;
    }
    public MiscSlot[] getMisc() {
        return misc;
    }
    public ArrayList<MiscItem> getKeys() {
        return keys;
    }
    public int noOfKeys() {
        return keys.size();
    }
    public ArrayList<MiscItem> getCoins() {
        return coins;
    }
    public int noOfCoins() {
        return coins.size();
    }
    public ArrayList<MiscItem> getLootboxes() {
        return lootboxes;
    }
    public int noOfLootBox() {
        return lootboxes.size();
    }
}

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/
import java.util.Scanner;

public class Shop {
    
    public String name = "Ye Old Shoppe";
    private int cost_multiplier = 1;
    
    public static void initShop() {
        new Food(6, "Potato", 1, 0.6, 2);
        new Food(7, "Cookie", 2, 0.4, 5);
        new Food(8, "Apple", 4, 2.4, 15);
        new Food(9, "Bread", 5, 6, 15);
        new Food(10, "Steak", 8, 12.8, 20);
        new Food(11, "Golden Carrot", 6, 14.4, 25);

        new Potion(21, "Potion of Healing I", "Healing", 1, 1, 60);
        new Potion(22, "Potion of Healing II", "Healing", 1, 2, 100);
        new Potion(23, "Potion of Harming I", "Harming", 1, 1, 60);
        new Potion(24, "Potion of Harming II", "Harming", 1, 2, 100);

        new Armor(31, "Leather Armor", 7, 0, 69, 30);
        new Armor(32, "Golden Armor", 11, 0, 97, 70);
        new Armor(33, "Chainmail Armor", 12, 0, 207, 90);
        new Armor(34, "Iron Armor", 15, 0, 207, 100);
        new Armor(35, "Diamond Armor", 20, 8, 454, 200);
        new Armor(36, "Netherite Armor", 20, 12, 509, 500);

        new Weapon(41, "Wooden Sword", 4, 60, 10);
        new Weapon(42, "Golden Sword", 5, 33, 10);
        new Weapon(43, "Stone Sword", 5, 131, 30);
        new Weapon(44, "Iron Sword", 6, 251, 50);
        new Weapon(45, "Diamond Sword", 7, 1561, 100);
        new Weapon(46, "Netherite Sword", 8, 2031, 300);

        new Shield(51, "Weak Shield", 168, 50);
        new Shield(52, "Normal Shield", 336, 100);
        new Shield(53, "Iron Shield", 750, 200);
        new Shield(54, "Diamond Shield", 1250, 500);
        new Shield(55, "Netherite Shield", 2000, 1000);
    }

    public static void chooseShop(Player player) throws InterruptedException {
        int amount, buyCounter;
        
        do {
            Scanner sc= new Scanner(System.in);
            System.out.println("------------------------");
            System.out.println("1: Deposit Loot Box ");
            System.out.println("2: Dungeon Key (50 coins) ");
            System.out.println("3: Dungeon Lootbox (150 coins) ");
            System.out.println("4: Foods ");
            System.out.println("5: Armors ");
            System.out.println("6: Weapons ");
            System.out.println("7: Shields");
            System.out.println("8: Potions");
            System.out.println("9: Pray in the AFK Throne");
            System.out.println("> CLOSE < ");
            System.out.print("What will you buy/do? ");
            GUITemplate.setAction(player);
            
            try {
                switch (player.action) {
                    case "1":
                        if (player.getInv().noOfLootBox() > 0) {
                            Dungeon.generateLootBox(player);
                            break;
                        }
                        else {
                            System.out.println("You don't have any lootbox!");
                        }
                        break;
                    case "2":
                        System.out.print("How many will you buy? ");
                        GUITemplate.setValue(player);
                        buyCounter = 0;
                        for (int i = player.number; i > 0; i--) {
                            if (player.getInv().noOfCoins() >= 50) {
                                player.getInv().removeCoins(50);
                                player.getInv().addKey();
                                buyCounter++;
                            }
                            else {
                                notEnoughCoins();
                                break;
                            }
                        }

                        System.out.println("You bought " + buyCounter + " Dungeon Keys.");
                        break;
                    case "3":
                        System.out.print("How many will you buy? ");
                        GUITemplate.setValue(player);
                        buyCounter = 0;
                        for (int i = player.number; i > 0; i--) {
                            if (player.getInv().noOfCoins() >= 150) {
                                player.getInv().removeCoins(150);
                                player.getInv().addLootbox();
                                buyCounter++;
                            }
                            else {
                                notEnoughCoins();
                                break;
                            }
                        }

                        System.out.println("You bought " + buyCounter + " Dungeon Lootbox.");
                        break;
                    case "4":
                        for (int i = 0; i <= Food.foodList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Food.foodList.get(i).itemName + " (" + Food.foodList.get(i).current_price + " coins) [heals " + Food.foodList.get(i).restoreHunger + " hunger points]");
                        }
                        System.out.println("> CLOSE <");
                        System.out.print("What will you buy? ");
                        GUITemplate.setAction(player);
                        if (!Player.action.equalsIgnoreCase("close")) {
                            if (!player.getInv().isFoodInvFull(Food.foodList.get(Integer.valueOf(Player.action)- 1))) buyItem(player, Food.foodList.get(Integer.valueOf(Player.action)- 1));
                            else System.out.println("Food inventory is full! Try again");
                        }
                        Player.action = "";
                        break;
                    case "5":
                        for (int i = 0; i <= Armor.armorList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Armor.armorList.get(i).itemName + " (" + Armor.armorList.get(i).current_price + " coins) [" + Armor.armorList.get(i).addDP + " defense points]");
                        }
                        System.out.println("> CLOSE <");
                        System.out.print("What will you buy? ");
                        GUITemplate.setAction(player);
                        if (!Player.action.equalsIgnoreCase("close")) {
                            buyArmor(player, Armor.armorList.get(Integer.valueOf(Player.action)- 1));
                        }
                        Player.action = "";
                        break;
                    case "6":
                        for (int i = 0; i <= Weapon.weaponList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Weapon.weaponList.get(i).itemName + " (" + Weapon.weaponList.get(i).current_price + " coins) [" + Weapon.weaponList.get(i).addAP + " attack points]");
                        }
                        System.out.println("> CLOSE <");
                        System.out.print("What will you buy? ");
                        GUITemplate.setAction(player);
                        if (!Player.action.equalsIgnoreCase("close")) {
                            buyWeapon(player, Weapon.weaponList.get(Integer.valueOf(Player.action)- 1));
                        }
                        Player.action = "";
                        break;
                    case "7":
                        for (int i = 0; i <= Shield.shieldList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Shield.shieldList.get(i).itemName + " (" + Shield.shieldList.get(i).current_price + " coins) [" + Shield.shieldList.get(i).durability + " durability]");
                        }
                        System.out.println("> CLOSE <");
                        System.out.print("What will you buy? ");
                        GUITemplate.setAction(player);
                        if (!Player.action.equalsIgnoreCase("close")) {
                            buyShield(player, Shield.shieldList.get(Integer.valueOf(Player.action)- 1));
                        }
                        Player.action = "";
                        break;
                    case "8":
                        for (int i = 0; i <= Potion.potionList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Potion.potionList.get(i).itemName + " (" + Potion.potionList.get(i).current_price + " coins) ");
                        }
                        System.out.println("> CLOSE <");
                        System.out.print("What will you buy? ");
                        GUITemplate.setAction(player);
                        if (!Player.action.equalsIgnoreCase("close")) {
                            if (!player.getInv().isPotionInvFull()) buyPotion(player, Potion.potionList.get(Integer.valueOf(Player.action)- 1));
                            else System.out.println("Potion inventory is full! Try again");
                        }
                        Player.action = "";
                        break;
                    case "9":
                        System.out.println("Hmmmm...");
                        Thread.sleep(3000);
                        System.out.println("*checks Book of Credibility*");
                        int rand = (int) Math.ceil(Math.random() * 100);
                        Thread.sleep(3000);
                        if (rand == 100) {
                            System.out.println("I can grant your wish...");
                            player.getInv().addKey();
                        }
                        else {
                            System.out.println("You don't seem credible...");
                        }
                        break;
                    case "close": case "CLOSE":
                        break;
                    default:
                }
            }
            catch (IndexOutOfBoundsException err) {
                System.out.println("There is no item in that index!");
            }
            
            Thread.sleep(3000);
            
        } while (!Player.action.equalsIgnoreCase("close"));
    }
    
    public static void blackMarket(Player player, Dungeon dungeon) {
        Scanner sc = new Scanner(System.in);
        player.setHP(5);
        do {
            System.out.print("Do you want to gamble? ");
            GUITemplate.setAction(player);
            if (player.getInv().noOfCoins() > 0) {
                int rand = 0;
                rand = (int) Math.ceil(Math.random() * 9);
                player.getInv().removeCoins(1);

                if (rand == 9) {
                    rand = (int) Math.ceil(Math.random() * 10);
                    player.getInv().addCoins(rand);
                    System.out.println("You received " + rand + " coins.");
                    System.out.println("------------------------");
                }
                else {
                    System.out.println("Oops.");
                    System.out.println("------------------------");
                    dungeon.setClank(dungeon.getClank() + 1);
                }
            }
            else {
                System.out.println("You don't have enough coins!");
                System.out.println("------------------------");
            }
        } while ("yes".equals(player.action));
    }
    
    public static void buyItem(Player player, Food item) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("How many will you buy? ");
        GUITemplate.setAction(player);
        int buyCounter = 0;
        for (int i = (Integer.valueOf(player.action)); i > 0; i--) {
            if (player.getInv().noOfCoins() >= item.current_price) {
                player.getInv().removeCoins(item.current_price);
                player.getInv().addItem(item);
                buyCounter++;
            }
            else {
                notEnoughCoins();
                break;
            }
        }

        System.out.println("You bought " + buyCounter + " " + item.itemName + "s.");
    }
    
    public static void buyArmor(Player player, Armor item) {
        if (player.getInv().noOfCoins() >= item.current_price) {
            player.getInv().removeCoins(item.current_price);
            Armor.equip(player, item);
        }
        else {
            notEnoughCoins();
        }

        System.out.println("You bought " + item.itemName + ".");
    }
    
    public static void buyWeapon(Player player, Weapon item) {
        if (player.getInv().noOfCoins() >= item.current_price) {
            player.getInv().removeCoins(item.current_price);
            Weapon.equip(player, item);
        }
        else {
            notEnoughCoins();
        }

        System.out.println("You bought " + item.itemName + ".");
    }
    
    public static void buyShield(Player player, Shield item) {
        if (player.getInv().noOfCoins() >= item.current_price) {
            player.getInv().removeCoins(item.current_price);
            Shield.equip(player, item);
        }
        else {
            notEnoughCoins();
        }

        System.out.println("You bought " + item.itemName + ".");
    }
    
    public static void buyPotion(Player player, Potion item) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("How many will you buy? ");
        GUITemplate.setAction(player);
        int buyCounter = 0;
        for (int i = (Integer.valueOf(player.action)); i > 0; i--) {
            if (player.getInv().noOfCoins() >= item.current_price) {
                player.getInv().removeCoins(item.current_price);
                player.getInv().addItem(item);
                buyCounter++;
            }
            else {
                notEnoughCoins();
                break;
            }
        }

        System.out.println("You bought " + buyCounter + " " + item.itemName + ".");
    }
    
    public static void notEnoughCoins() {
        System.out.println("You don't have enough coins!");
    }
}

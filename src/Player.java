/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.Scanner;

public class Player {

    private final String playerName;
    private double healthP;
    private double maxHP;
    private double attackP;
    private double defenseP;
    private int deaths = 0;
    
    private double foodLevel;
    private double foodMaxLevel;
    private double foodSaturationLevel;
    private double foodMaxSaturationLevel;
    private double foodExhaustionLevel;
    
    public static String action = "0";
    public static int number;
    
    public int posX;
    public int posY;
    public boolean hasLootbox;
    
    private int artifactsPoints = 0;
    
    private Inventory inv = new Inventory();
    private Armor armor = new Armor(30, "No Armor", 0, 0, 9999999);
    private Weapon weapon = new Weapon(40, "No Weapon", 0, 9999999);
    private Shield shield = new Shield(50, "No Shield", 9999999);
    
    public Player(String n) {
        playerName = n;
        healthP = 20;
        maxHP = 20;
        attackP = 1;
        defenseP = 0;
        posX = 0;
        posY = 0;
        foodLevel = 20;
        foodMaxLevel = 20;
        foodSaturationLevel = 5;
        foodMaxSaturationLevel = foodLevel;
        foodExhaustionLevel = 0;
    }
    
    public void printStats() { //prints player's stats
        System.out.println("Name: " + this.playerName);
        System.out.println("HP: " + this.healthP + " points");
        System.out.println("Attack: " + this.attackP + " HP");
        System.out.println("Defense: " + this.defenseP + " points");
        System.out.println("------------------------");
        System.out.println("Armor: " + this.armor.itemName + " (" + this.armor.durability + "/" + this.armor.maxDurability + ")" );
        System.out.println("Weapon: " + this.weapon.itemName + " (" + this.weapon.durability + "/" + this.weapon.maxDurability + ")" );
        System.out.println("Shield: " + this.shield.itemName + " (" + this.shield.durability + "/" + this.shield.maxDurability + ")" );
    }
    
    public void update(Dungeon dungeon) { //updates hunger points, health, armor durability, and tools durability
        this.checkHunger(dungeon);
        this.checkHealth();
        this.checkEquippables();
    }
    
    public void battle(Dungeon dungeon, HostileEntity entity) { //called when a player meets a hostile entity
        if (dungeon.roomEntity != null) {
            battleLoop:
            do {
                System.out.println("HP: " + this.getHP());
                System.out.println(entity.name + " HP: " + entity.HP);
                System.out.println("------------------------");
                System.out.println("1: attack");
                System.out.println("2: defend");
                System.out.println("3: run");
                System.out.println("------------------------");
                GUITemplate.setAction(this);
                switch (this.action) {
                    case "attack":
                        this.attack(this, entity);
                        break;
                    case "defend":
                        this.defend(this, entity);
                        break;
                    case "run":
                        entity.entityAttack(this);
                        this.checkHealth();
                        entity.checkHealth();
                        break battleLoop;
                }
            } while (this.action != "run" && this.getHP() > 0 && entity.HP > 0);
        }
    }
    
    public void attack(Player player, HostileEntity entity) { //attacks a selected hostile entity
        double damage;
        if ((entity.posX == this.posX) && (entity.posY == this.posY)) {
            damage = this.attackP * (1 - ((Math.min(20, Math.max((entity.DP/4), (entity.DP - ((4 * this.attackP)/(8)))))) / 25));
            entity.HP -= damage;
            this.weapon.durability--; //minecraft's combat mechanics
            
            System.out.println("You dealt " + damage + " of damage to " + entity.name);
            
            entity.entityAttack(player);
            
            this.checkHealth();
            entity.checkHealth();
        }
    }
    
    public void defend(Player player, HostileEntity entity) { //defends from a selected hostile entity
        double damage;
        if ((entity.posX == this.posX) && (entity.posY == this.posY)) {
            if (this.shield.itemName != "No Shield") {
                damage = entity.AP * (1 - ((Math.min(20, Math.max((this.defenseP/4), (this.defenseP - ((4 * entity.AP)/(this.armor.toughness + 8)))))) / 25));
                
                System.out.println("Your shield blocked " + damage + " of damage!");
                
                this.shield.durability -= Math.ceil(damage); //minecraft's combat mechanics
            }
            else {
                System.out.println("You have no shield!");
                entity.entityAttack(player);
            }
            
            this.checkHealth();
            entity.checkHealth();
        }
    }
    
    public void checkHunger(Dungeon dungeon) { //minecraft's hunger mechanics
        foodMaxSaturationLevel = foodLevel;
        foodExhaustionLevel += 1;
        
        if (foodSaturationLevel == 0 && foodExhaustionLevel >= 4) {
            foodLevel--;
        }
        
        if (foodExhaustionLevel >= 4) {
            foodExhaustionLevel = 0;
            foodSaturationLevel--;
        }
        
        if (foodLevel == 20 && foodSaturationLevel > 0) {
            healthP++;
        } else
        if (foodLevel >= 18) {
            healthP += 0.25;
        } else 
        if (foodLevel == 0) {
            if (dungeon.getDifficulty().equals("Easy")) {
                if (healthP > 10) {
                    healthP -= 0.25;
                }
            }
            if (dungeon.getDifficulty().equals("Normal")) {
                if (healthP > 1) {
                    healthP -= 0.25;
                }
            }
            if (dungeon.getDifficulty().equals("Hard")) {
                healthP -= 0.25;
            }
        }
        
        if (this.foodLevel > this.foodMaxLevel) this.foodLevel = this.foodMaxLevel;
        if (this.foodLevel < 0) this.foodLevel = 0;
        if (this.foodSaturationLevel > this.foodMaxSaturationLevel) this.foodSaturationLevel = this.foodMaxSaturationLevel;
        if (this.foodSaturationLevel < 0) this.foodSaturationLevel = 0;
    }
    
    public void checkHealth() {
        if (this.healthP > this.maxHP) this.healthP = this.maxHP;
        if (this.healthP < 0) this.healthP = 0;
        
        if (this.healthP <= 0) {
            this.deaths++;
            System.out.println("You died in the dungeon!");
        }
    }
    
    public void checkEquippables() {
        if (this.armor.durability <= 0) { //when an armor or tool is broken, a null set will replace the slot
            System.out.println("Your " + this.armor.itemName + " is broken!");
            this.armor = new Armor(30, "No Armor", 0, 0, 9999999);
        }
        if (this.weapon.durability <= 0) {
            System.out.println("Your " + this.weapon.itemName + " is broken!");
            this.weapon = new Weapon(40, "No Weapon", 0, 9999999);
        }
        if (this.shield.durability <= 0) {
            System.out.println("Your " + this.shield.itemName + " is broken!");
            this.shield = new Shield(50, "No Shield", 9999999);
        }
    }
    
    public void resetPlayer() { //called when the player exits a dungeon
        this.hasLootbox = false;
        this.setHP(this.getMaxHP()); 
        this.posX = 0;
        this.posY = 0;
        this.setFoodLevel(20);
        this.setFoodSaturationLevel(5);
        this.setFoodExhaustionLevel(0);
    }
    
    public String getName() {
        return playerName;
    }
    public double getHP() {
        return healthP;
    }
    public void setHP(double h) {
        this.healthP = h;
    }
    public double getMaxHP() {
        return maxHP;
    }
    public double getAP() {
        return attackP;
    }
    public void setAP(double a) {
        this.attackP = a;
    }
    public double getDP() {
        return defenseP;
    }
    public void setDP(double d) {
        this.defenseP = d;
    }
    public int getArtifactsPoints() {
        return artifactsPoints;
    }
    public void setArtifactsPoints(int p) {
        this.artifactsPoints = p;
    }
    
    public double getFoodLevel() {
        return foodLevel;
    }
    public void setFoodLevel(double l) {
        this.foodLevel = l;
    }
    public double getFoodMaxLevel() {
        return foodMaxLevel;
    }
    public void setFoodMaxLevel(double l) {
        this.foodMaxLevel = l;
    }
    public double getFoodSaturationLevel() {
        return foodSaturationLevel;
    }
    public void setFoodSaturationLevel(double l) {
        this.foodSaturationLevel = l;
    }
    public double getFoodExhaustionLevel() {
        return foodExhaustionLevel;
    }
    public void setFoodExhaustionLevel(double l) {
        this.foodExhaustionLevel = l;
    }
    
    public Inventory getInv() {
        return inv;
    }
    public Armor getArmor() {
        return armor;
    }
    public void setArmor(Armor a) {
        this.armor = a;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon w) {
        this.weapon = w;
    }
    public Shield getShield() {
        return shield;
    }
    public void setShield(Shield s) {
        this.shield = s;
    }
}

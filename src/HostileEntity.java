/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class HostileEntity extends Entity {
    
    public int alertLevel = 0;
    public static ArrayList<HostileEntity> hostileEntityList = new ArrayList<HostileEntity>();
    
    public HostileEntity(String n, double h, double a, double d) {
        super(n, "hostile", h, h, a, d);
        hostileEntityList.add(this);
    }
    
    public static void initEntities() {
        new HostileEntity("Ravager", 100, 6, 0);
        new HostileEntity("Zombie", 20, 3, 2);
        new HostileEntity("Skeleton", 20, 2.5, 0);
    }
    
    public void entityAI(Dungeon dungeon, Player player) {
        if (this.isSpawned) {
            if (this.checkPos(player) && this.checkHealth()) {
                dungeon.roomEntity = this;
                entityAttack(player);
            }
            else {
                dungeon.roomEntity = null;
            }
            
            if (checkForPlayer(player) || (alertLevel != 0)) {
                entityMove(dungeon, player, 20, false);
            }
            else {
                entityMove(dungeon, player, 50, true);
            }
            
            if (alertLevel > 0) alertLevel--;
        }
    }
    
    public void entitySpawn(Dungeon dungeon, Player player) {
        int rand;
        rand = (int) Math.floor(Math.random() * (dungeon.getMaxWidth() - 1));
        this.posX = rand;
        rand = (int) Math.floor(Math.random() * (dungeon.getMaxHeight() - 1));
        this.posY = rand;
        
        this.isSpawned = true;
    }
    
    public void entityMove(Dungeon dungeon, Player player, int t1, boolean isRandom) {
        int rand;
        
        if (isRandom) {
            rand = (int) Math.ceil(Math.random() * 100);
            if (rand > 50) {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > 50) {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isLeftSideClosed == false) {
                        this.posX--;
                    }
                }
                else {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isRightSideClosed == false) {
                        this.posX++;
                    }
                }
            }
            else {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > 50) {

                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isDownSideClosed == false) {
                        this.posY--;
                    }
                }
                else {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isUpSideClosed == false) {
                        this.posY++;
                    }
                }
            }
        }
        else {
            rand = (int) Math.ceil(Math.random() * 100);
            if ((rand > t1 && rand < ((t1 / 2) + t1)) || (this.posY == player.posY)) {
                if (player.posX < this.posX) {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isLeftSideClosed == false) {
                        this.posX--;
                    }
                }
                if (player.posX > this.posX) {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isRightSideClosed == false) {
                        this.posX++;
                    }
                }
            }
            else if ((rand > ((t1 / 2) + t1) && rand < 100) || (this.posX == player.posX)) {
                if (player.posY < this.posY) {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isDownSideClosed == false) {
                        this.posY--;
                    }
                }
                if (player.posY > this.posY) {
                    if (dungeon.getDungeonTiles()[this.posX][this.posY].isUpSideClosed == false) {
                        this.posY++;
                    }
                }
            }
        }
    }
    
    public void entityAttack(Player player) {
        double damage;
        if ((this.posX == player.posX) && (this.posY == player.posY)) {
            damage = this.AP * (1 - ((Math.min(20, Math.max((player.getDP()/4), (player.getDP() - ((4 * this.AP)/(player.getArmor().toughness + 8)))))) / 25));
            player.setHP(player.getHP() - damage);
            
            System.out.println(this.name + " dealt " + damage + " of damage!");
            
            if (player.getArmor().itemName != "No Armor") {
                player.getArmor().durability -= (damage - (player.getArmor().toughness/2));
            }
        }
    }
    
    public boolean checkPos(Player player) {
        if ((this.posX == player.posX) && (this.posY == player.posY)) {
            if (this.name.equalsIgnoreCase("Ravager")) System.out.println("A ravager growls...");
            if (alertLevel == 0) alertLevel = 5;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean checkForPlayer(Player player) {
        if ((this.posX == (player.posX - 1)) && (this.posY == player.posY)) {
            if (this.name.equalsIgnoreCase("Ravager")) System.out.println("A ravager sniffs...");
            return true;
        } else
        if ((this.posX == (player.posX + 1)) && (this.posY == player.posY)) {
            if (this.name.equalsIgnoreCase("Ravager")) System.out.println("A ravager sniffs...");
            return true;
        } else
        if ((this.posY == (player.posY - 1)) && (this.posX == player.posX)) {
            if (this.name.equalsIgnoreCase("Ravager")) System.out.println("A ravager sniffs...");
            return true;
        } else
        if ((this.posY == (player.posY + 1)) && (this.posX == player.posX)) {
            if (this.name.equalsIgnoreCase("Ravager")) System.out.println("A ravager sniffs...");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkHealth() {
        if (this.HP <= 0) {
            System.out.println(this.name + " died!");
            this.isSpawned = false;
            return false;
        }
        else {return true;}
    }
    
    public void resetEntity() {
        this.HP = this.maxHP;
        this.posX = 0;
        this.posY = 0;
        this.isSpawned = false;
    }
}

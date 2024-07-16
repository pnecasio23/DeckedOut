/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.awt.Color;
import java.util.ArrayList;

public class Tile {
    
    public static boolean debugMode = true; //if set to true, tile's info will be revealed
    
    public boolean isGenerated;
    public Biome biome;
    public String room;
    public boolean isLeftSideClosed;
    public boolean isRightSideClosed;
    public boolean isUpSideClosed;
    public boolean isDownSideClosed;
    public int roomX;
    public int roomY;
    public boolean isTreasure;
    public boolean hasSoulFlames;
    public boolean hasLoot;
    public String structure;
    
    public int doorsClosedChance = 25;
            
    public Tile() {
        
    }
    
    public void generateStartingTile(Dungeon dungeon) {
        dungeon.getDungeonTiles()[0][0].biome = Biome.biomeList.get(0);
        dungeon.getDungeonTiles()[0][0].room = "Entrance";
        dungeon.getDungeonTiles()[0][0].isGenerated = true;
        dungeon.getDungeonTiles()[0][0].isLeftSideClosed = true;
        dungeon.getDungeonTiles()[0][0].isDownSideClosed = true;
        dungeon.getDungeonTiles()[0][0].isRightSideClosed = false;
        dungeon.getDungeonTiles()[0][0].isUpSideClosed = false;
    }
    
    public void generateTreasureTile(Dungeon dungeon) {
        dungeon.setTreasureX((int) Math.ceil(Math.random() * (dungeon.getMaxWidth() - 1)));
        dungeon.setTreasureY((int) Math.ceil(Math.random() * (dungeon.getMaxHeight() - 1)));
        dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isTreasure = true;
        
        dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isLeftSideClosed = false;
        dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isRightSideClosed = false;
        dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isUpSideClosed = false;
        dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isDownSideClosed = false;
    }
    
    public void generateTile(Dungeon dungeon, int x, int y) {
        generateRoomBiome(dungeon, x, y);
        generateRoom(dungeon, x, y);
        generateWays(dungeon, x, y);
        dungeon.getDungeonTiles()[x][y].isGenerated = true;
        if (x == 0) {
            dungeon.getDungeonTiles()[x][y].isLeftSideClosed = true;
        }
        if (x == (dungeon.getMaxWidth() - 1)) {
            dungeon.getDungeonTiles()[x][y].isRightSideClosed = true;
        }
        if (y == 0) {
            dungeon.getDungeonTiles()[x][y].isDownSideClosed = true;
        }
        if (y == (dungeon.getMaxHeight() - 1)) {
            dungeon.getDungeonTiles()[x][y].isUpSideClosed = true;
        }
    }
    
    public void generateRoomBiome(Dungeon dungeon, int x, int y) {
        for (Biome b : Biome.biomeList) {
            if (b.id == dungeon.getBiomeMask()[x][y]) {
                dungeon.getDungeonTiles()[x][y].biome = b;
            }
        }
    }
    
    public static char[][] printBiomeMask(Dungeon dungeon, ArrayList<Biome> biomeInfo) {
        //algorithm from: https://stackoverflow.com/questions/22713688/2d-tile-map-generation-with-biomes;
        
        char[][] field = new char[dungeon.getMaxWidth()][dungeon.getMaxHeight()];
        
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                char nearest = '.'; // value here doesn't matter
                int dist = Integer.MAX_VALUE; // select a big number

                // walk over each biomeInfo
                for (int z = 0; z < biomeInfo.size(); z++) {

                    // calculate the difference in x and y direction
                    int xdiff = biomeInfo.get(z).x - i;
                    int ydiff = biomeInfo.get(z).y - j;

                    // calculate euclidean distance, sqrt is not needed
                    // because we only compare and do not need the real value
                    int cdist = xdiff*xdiff + ydiff*ydiff;

                    // is the current distance smaller than the old distance?
                    // if yes, take this biome
                    if (cdist < dist) {
                        nearest = biomeInfo.get(z).id;
                        dist = cdist;
                    }
                }

                // set the field to the nearest biome       
                field[i][j] = nearest;
            }
        }
        
        /*
        for (int j = field.length - 1; j >= 0; j--) {
            for (int i = 0; i < field[0].length; i++) {
                System.out.printf("%c ", field[i][j]);
            }
            System.out.printf("%n"); // %n means newline
        }
        System.out.printf("%n");
        */
        
        return field;
    }
    
    public static void checkTiles(Dungeon dungeon) {
        for (int y = 0; y <= dungeon.getMaxHeight() - 1; y++) {
            for (int x = 0; x <= dungeon.getMaxWidth() - 1; x++) {
                if (dungeon.getDungeonTiles()[x][y].isUpSideClosed && dungeon.getDungeonTiles()[x][y].isLeftSideClosed && dungeon.getDungeonTiles()[x][y].isDownSideClosed && dungeon.getDungeonTiles()[x][y].isRightSideClosed) {
                    dungeon.getDungeonTiles()[x][y].biome = new Biome("Void", '0', Color.black);
                    dungeon.getDungeonTiles()[x][y].room = "Void";
                }
            }
        }
    }
    
    public static void revealTile(Dungeon dungeon, Player player) {
        if (debugMode) {
            System.out.println("X: " + player.posX + " Y: " + player.posY);
            System.out.println("HP: " + player.getHP());
            //System.out.println("Hunger: " + player.foodLevel);
            //System.out.println("Saturation: " + player.foodSaturationLevel);
            //System.out.println("Saturation Exhaustion: " + player.foodExhaustionLevel);
            //if (Tile.debugMode) System.out.println("Treasure X: " + treasureX + " Treasure Y: " + treasureY);
            //System.out.println("Stealth Points: " + player.getInv().getCards().stealthPoints);
            //System.out.println("Loot Finder Points: " + player.getInv().getCards().lootFinderPoints);
            //System.out.println("Soul Seeker Points: " + player.getInv().getCards().soulSeekerPoints);
            //System.out.println("Beast Sense Points: " + player.getInv().getCards().beastSensePoints);
            for (HostileEntity h : HostileEntity.hostileEntityList) {
                if (h.isSpawned) {
                    //System.out.println(h.name + " X: " + h.posX + " Y: " + h.posY);
                    //System.out.println(h.name + " Alert Level: " + h.alertLevel);
                    //System.out.println(h.name + " HP: " + h.HP);
                }
            }
            //System.out.println("Max HP: " + player.maxHP);
            //System.out.println("Soul Flames Punched: " + dungeon.soulFlamePunched);
            //System.out.println("isLeftLocked: " + dungeon.getDungeonTiles()[player.posX][player.posY].isLeftSideClosed);
            //System.out.println("isRightLocked: " + dungeon.getDungeonTiles()[player.posX][player.posY].isRightSideClosed);
            //System.out.println("isUpLocked: " + dungeon.getDungeonTiles()[player.posX][player.posY].isUpSideClosed);
            //System.out.println("isDownLocked: " + dungeon.getDungeonTiles()[player.posX][player.posY].isDownSideClosed);
            //System.out.println("Clank: " + dungeon.clank);
            //System.out.println("Beast Sense Timer: " + dungeon.beastSenseTimer);
        }
        
        //System.out.println("Biome: " + dungeon.getDungeonTiles()[player.posX][player.posY].biome.biomeName);
        //System.out.println("Room: " + dungeon.getDungeonTiles()[player.posX][player.posY].room);
        
        if(dungeon.getDungeonTiles()[player.posX][player.posY].isTreasure == true) {
            System.out.println("You found the lootbox!");
            player.hasLootbox = true;
            player.getInv().addLootbox();
            
            dungeon.getDungeonTiles()[dungeon.getTreasureX()][dungeon.getTreasureY()].isTreasure = false;
        }
        
        PassiveEntity.spawn();
    }
    
    public void generateRoom(Dungeon dungeon, int x, int y) {
        int rand;
        rand = (int) Math.floor(Math.random() * (dungeon.getDungeonTiles()[x][y].biome.roomList.length));
        dungeon.getDungeonTiles()[x][y].room = dungeon.getDungeonTiles()[x][y].biome.roomList[rand];
    }
    
    public static void generateLoot(Dungeon dungeon, Player player) {
        for (int i = 0; i < (dungeon.getMaxWidth() + (Math.floor(player.getInv().getCards().lootFinderPoints / 3))); i++) {
            int randX = (int) Math.ceil(Math.random() * (dungeon.getMaxWidth() - 1));
            int randY = (int) Math.ceil(Math.random() * (dungeon.getMaxHeight() - 1));
            
            dungeon.getDungeonTiles()[randX][randY].hasLoot = true;
        }
    }
    
    public static void generateSoulFlames(Dungeon dungeon, Player player) {
        for (int i = 0; i < (dungeon.getMaxWidth() + (Math.floor(player.getInv().getCards().soulSeekerPoints / 3))); i++) {
            int randX = (int) Math.ceil(Math.random() * (dungeon.getMaxWidth() - 1));
            int randY = (int) Math.ceil(Math.random() * (dungeon.getMaxHeight() - 1));
            
            dungeon.getDungeonTiles()[randX][randY].hasSoulFlames = true;
        }
    }
    
    public void generateWays(Dungeon dungeon, int x, int y) {
        int rand;
        if (x != 0) {
            if(dungeon.getDungeonTiles()[x-1][y].isGenerated == false && dungeon.getDungeonTiles()[x-1][y].isRightSideClosed == false) {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > (100 - doorsClosedChance)) {
                    dungeon.getDungeonTiles()[x][y].isLeftSideClosed = true;
                    dungeon.getDungeonTiles()[x-1][y].isRightSideClosed = true;
                }
            }
        }
        else {
            dungeon.getDungeonTiles()[x][y].isLeftSideClosed = true;
        }
        
        if (x != (dungeon.getMaxWidth() - 1)) {
            if(dungeon.getDungeonTiles()[x+1][y].isGenerated == false && dungeon.getDungeonTiles()[x+1][y].isLeftSideClosed == false) {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > (100 - doorsClosedChance)) {
                    dungeon.getDungeonTiles()[x][y].isRightSideClosed = true;
                    dungeon.getDungeonTiles()[x+1][y].isLeftSideClosed = true;
                }
            }
        }
        else {
            dungeon.getDungeonTiles()[x][y].isRightSideClosed = true;
        }
        
        if (y != 0) {
            if(dungeon.getDungeonTiles()[x][y-1].isGenerated == false && dungeon.getDungeonTiles()[x][y-1].isUpSideClosed == false) {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > (100 - doorsClosedChance)) {
                    dungeon.getDungeonTiles()[x][y].isDownSideClosed = true;
                    dungeon.getDungeonTiles()[x][y-1].isUpSideClosed = true;
                }
            }
        }
        else {
            dungeon.getDungeonTiles()[x][y].isDownSideClosed = true;
        }
        
        if (y != (dungeon.getMaxHeight() - 1)) {
            if(dungeon.getDungeonTiles()[x][y+1].isGenerated == false && dungeon.getDungeonTiles()[x][y+1].isDownSideClosed == false) {
                rand = (int) Math.ceil(Math.random() * 100);
                if (rand > (100 - doorsClosedChance)) {
                    dungeon.getDungeonTiles()[x][y].isUpSideClosed = true;
                    dungeon.getDungeonTiles()[x][y+1].isDownSideClosed = true;
                }
            }
        }
        else {
            dungeon.getDungeonTiles()[x][y].isUpSideClosed = true;
        }
    }
    
    public static void generateClank(Dungeon dungeon, Player player) {
        int rand;
        rand = (int) Math.ceil(Math.random() * 100);
        if (rand > (60 + (2 * player.getInv().getCards().stealthPoints))) {
            rand = (int) Math.ceil(Math.random() * 5);
            dungeon.setClank(dungeon.getClank() + rand);
        }
    }
}

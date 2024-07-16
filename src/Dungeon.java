/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Dungeon {

    private final int maxWidth;
    private final int maxHeight;
    private final String difficulty;
    private int treasureX;
    private int treasureY;
    private boolean isActive;
    
    private Tile[][] dungeonTiles;
    private int[][] tileID;
    private char[][] biomeMask;
    public static ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
    
    private int soulFlamePunched = 0;
    private int beastSenseTimer = 0;
    private int clank = 100;
    
    public HostileEntity roomEntity;
    
    //loot entries
    static LootEntry[] genericLootEntries = {
        new LootEntry(new MiscItem(1, "Dungeon Coin"), 50, 1, 3),
        new LootEntry(new MiscItem(2, "Dungeon Key"), 1, 1, 1),
        new LootEntry(new MiscItem(3, "Dungeon Lootbox"), 1, 1, 1)
    };
    static LootEntry[] normalLootEntries = {
        new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 3, 7),
    };
    static LootEntry[] megaLootEntries = {
        new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 5, 10),
        new LootEntry(new MiscItem(2, "Dungeon Key"), 1, 1, 1)
    };
    static LootEntry[] omegaLootEntries = {
        new LootEntry(new MiscItem(1, "Dungeon Coin"), 8, 7, 12),
        new LootEntry(new MiscItem(2, "Dungeon Key"), 2, 1, 1)
    };
    
    //loot tables
    static LootTable genericLoot = new LootTable(2, 4, genericLootEntries);
    static LootTable normalLoot = new LootTable(3, 5, normalLootEntries);
    static LootTable megaLoot = new LootTable(3, 5, megaLootEntries);
    static LootTable omegaLoot = new LootTable(4, 5, omegaLootEntries);
    
    public Dungeon(int w, int h, String d) {
        this.maxWidth = w;
        this.maxHeight = h;
        this.difficulty = d;
        this.dungeonTiles = new Tile[w][h];
        this.tileID = new int[w][h];
        for (int y = 0; y <= this.maxHeight - 1; y++) {
            for (int x = 0; x <= this.maxWidth - 1; x++) {
                this.dungeonTiles[x][y] = new Tile();
            }
        }
        dungeonList.add(this);
    }
    
    public void generateDungeon(Player player) {
        player.getInv().getCards().processCards(player);
        
        //generate a biome mask to be used for biome generation
        for (int z = 1; z < Biome.biomeList.size(); z++) {
            Biome.biomeList.get(z).x = (int) Math.floor(Math.random() * (this.maxWidth));
            Biome.biomeList.get(z).y = (int) Math.floor(Math.random() * (this.maxHeight));
        }
        this.biomeMask = Tile.printBiomeMask(this, Biome.biomeList);
        
        this.dungeonTiles[0][0].generateTreasureTile(this);
        
        //generate tiles
        for (int y = 0; y <= this.maxHeight - 1; y++) {
            for (int x = 0; x <= this.maxWidth - 1; x++) {
                if (x == 0 && y == 0) {
                    this.dungeonTiles[0][0].generateStartingTile(this);
                }
                else {
                    this.dungeonTiles[x][y].generateTile(this, x, y);
                    this.dungeonTiles[x][y].biome.loot.generateLoot(player);
                }
            }
        }
        
        Tile.checkTiles(this);
        Tile.generateSoulFlames(this, player);
        Tile.generateLoot(this, player);
        
        //spawn hostile entities
        if (this.difficulty.equalsIgnoreCase("easy")) {
            HostileEntity.hostileEntityList.get(2).entitySpawn(this, player);
        }
        if (this.difficulty.equalsIgnoreCase("normal")) {
            HostileEntity.hostileEntityList.get(1).entitySpawn(this, player);
            HostileEntity.hostileEntityList.get(2).entitySpawn(this, player);
        }
        if (this.difficulty.equalsIgnoreCase("hard")) {
            HostileEntity.hostileEntityList.get(0).entitySpawn(this, player);
            HostileEntity.hostileEntityList.get(1).entitySpawn(this, player);
        }
        
    }
    
    public void playDungeon(GUITemplate frame, Player player) {
        frame.updateDungeonStats(player, this);
        checkCompass(player);
        this.isActive = true;
        DebugGUITemplate debugGUI = new DebugGUITemplate(this);
        
        playDungeonLoop:
        while (player.getHP() > 0 && !(player.hasLootbox && player.posX == 0 && player.posY == 0)) {
            player.action = "";
            for (HostileEntity h : HostileEntity.hostileEntityList) {
                h.entityAI(this, player);
                player.battle(this, roomEntity);
            }
            if (player.getHP() <= 0) break;
            player.update(this);
            frame.updateDungeonStats(player, this);
            
            Tile.revealTile(this, player);
            Tile.generateClank(this, player);
            move(player);
            checkSoulFlames(player);
            checkLoot(player);
            
            //debugGUI.updateDebug(this, player);
            
            if (!player.hasLootbox) this.checkCompass(player);
            if (player.getInv().getCards().beastSensePoints > 0 && HostileEntity.hostileEntityList.get(0).isSpawned) this.checkCompass(player, HostileEntity.hostileEntityList.get(0));
            if (player.posX == 0 && player.posY == 0) {
                System.out.println("Do you want to exit the dungeon? (Y/N)");
                GUITemplate.setAction(player);
                if (player.action.equalsIgnoreCase("Y")) break;
            }
        }
        
        if (player.getHP() > 0 && player.hasLootbox) {
            System.out.println("Dungeon complete!");
            pickACard(player);
        }
        
        player.resetPlayer();
        this.cleanDungeon();
        this.isActive = false;
    }
    
    public void pickACard(Player player) { //the player will pick a card from 3 random cards
        Card card1 = Card.randomCard(player, soulFlamePunched);
        Card card2 = Card.randomCard(player, soulFlamePunched);
        Card card3 = Card.randomCard(player, soulFlamePunched);
        
        System.out.println("------------------------");
        System.out.println("1: " + card1.itemName);
        System.out.println("2: " + card2.itemName);
        System.out.println("3: " + card3.itemName);
        System.out.print("What will you choose? ");
        GUITemplate.setAction(player);

        switch (player.action) {
            case "1":
                Card.addCards(player, card1);
                break;
            case "2":
                Card.addCards(player, card2);
                break;
            case "3":
                Card.addCards(player, card3);
                break;
            default:
                System.out.print("Invalid action");
        }
    }
    
    public void move(Player player) {
        System.out.print("Where will you go? ");
        GUITemplate.setAction(player);
        System.out.println("------------------------");
        //this.getDungeonTiles()[player.posX][player.posY].biome.loot.generateLoot(player);
        
        switch(player.action) {
            case "up":
                if (player.posY != (maxHeight - 1)) {
                    if (this.dungeonTiles[player.posX][player.posY].isUpSideClosed == false) {
                        player.posY++;
                    } else {System.out.println("Up wall locked!");}
                } else  {System.out.println("Reached map limit!");}
                break;
            case "down":
                if (player.posY != 0) {
                    if (this.dungeonTiles[player.posX][player.posY].isDownSideClosed == false) {
                        player.posY--;
                    } else {System.out.println("Down wall locked!");}
                } else  {System.out.println("Reached map limit!");}
                break;
            case "left":
                if (player.posX != 0) {
                    if (this.dungeonTiles[player.posX][player.posY].isLeftSideClosed == false) {
                        player.posX--;
                    } else {System.out.println("Left wall locked!");}
                } else  {System.out.println("Reached map limit!");}
                break;
            case "right":
                if (player.posX != (maxWidth - 1)) {
                    if (this.dungeonTiles[player.posX][player.posY].isRightSideClosed == false) {
                        player.posX++;
                    } else {System.out.println("Right wall locked!");}
                } else  {System.out.println("Reached map limit!");}
                break;
            case "black market": //a shop only open in cavern of souls
                if (this.dungeonTiles[player.posX][player.posY].room.equals("Cavern of Souls")) {
                    Shop.blackMarket(player, this);
                    break;
                }
                else {
                    System.out.println("Invalid move!");
                    break;
                }
            case "eat":
                if (!player.getInv().isFoodInvEmpty()) {
                    System.out.println("Your foods: ");
                    for (int i = 0; i <= player.getInv().getFoods().length - 1; i++) {
                        if (player.getInv().getFoods()[i].item != null) System.out.println((i + 1) + ": " + player.getInv().getFoods()[i].item.itemName + " [" + player.getInv().getFoods()[i].amount +"]");
                    }
                    System.out.print("What will you eat? ");
                    GUITemplate.setAction(player);
                    System.out.println("------------------------");

                    player.getInv().getFoods()[Integer.valueOf(player.action) - 1].item.eat(player);
                }
                else {
                    System.out.println("You have no food!");
                    System.out.println("------------------------");
                }
                
                break;
            case "drink":
                if (!player.getInv().isPotionInvEmpty()) {
                    System.out.println("Your potions: ");
                    for (int i = 0; i <= player.getInv().getPotions().length - 1; i++) {
                        System.out.println((i + 1) + ": " + player.getInv().getPotions()[i].item.itemName);
                    }
                    System.out.print("What will you drink? ");
                    GUITemplate.setAction(player);
                    System.out.println("------------------------");

                    player.getInv().getPotions()[(Integer.valueOf(player.action)) - 1].item.drink(player);
                }
                else {
                    System.out.println("You have no potions!");
                    System.out.println("------------------------");
                }
                
                break;
            default:
                System.out.println("Invalid move!");
                break;
        }
        player.action = "";
    }
    
    public static void generateLootBox(Player player) {
        int rand = (int) Math.ceil(Math.random() * 100);
        if (rand > 90) {
            System.out.println("The omega box contains: ");
            omegaLoot.generateLoot(player);
        } else
        if (rand > 60) {
            System.out.println("The mega box contains: ");
            megaLoot.generateLoot(player);
        } else
        if (rand > 0) {
            System.out.println("The normal box contains: ");
            normalLoot.generateLoot(player);
        }
        
        Artifact.randomArtifacts(player);
        Card.randomTierIICard(player);
        player.getInv().removeLootbox();
    }
    
    public void cleanDungeon() {
        for (int y = 0; y < this.maxHeight - 1; y++) {
            for (int x = 0; x < this.maxWidth - 1; x++) {
                this.dungeonTiles[x][y].isGenerated = false;
                this.dungeonTiles[x][y].isLeftSideClosed = false;
                this.dungeonTiles[x][y].isDownSideClosed = false;
                this.dungeonTiles[x][y].isRightSideClosed = false;
                this.dungeonTiles[x][y].isUpSideClosed = false;
            }
        }
        for (HostileEntity h : HostileEntity.hostileEntityList) {
            h.resetEntity();
        }
        this.soulFlamePunched = 0;
        this.clank = 100;
    }
    
    public void checkSoulFlames(Player player) {
        if (this.dungeonTiles[player.posX][player.posY].hasSoulFlames) {
            System.out.println("You punched a soul flame!");
            this.soulFlamePunched += player.getInv().getCards().soulSeekerPoints;
            
            this.dungeonTiles[player.posX][player.posY].hasSoulFlames = false;
        }
    }
    
    public void checkLoot(Player player) {
        if (this.dungeonTiles[player.posX][player.posY].hasLoot) {
            System.out.println("You found a loot barrel!");
            try {
                this.dungeonTiles[player.posX][player.posY].biome.loot.generateLoot(player);
            }
            catch (NullPointerException err) {
                genericLoot.generateLoot(player);
            }
            
            this.dungeonTiles[player.posX][player.posY].hasLoot = false;
        }
    }
    
    public void checkCompass(Player player) {
        String compassX = " ", compassY = " ";
        
        if (player.posX < this.treasureX) compassX = "right";
        if (player.posX > this.treasureX) compassX = "left";
        if (player.posY < this.treasureY) compassY = "up";
        if (player.posY > this.treasureY) compassY = "down";
        if (player.posX == this.treasureX) compassX = " ";
        if (player.posY == this.treasureY) compassY = " ";
        
        if ("right".equals(compassX) && " ".equals(compassY)) System.out.println("Compass: E");
        if ("left".equals(compassX) && " ".equals(compassY)) System.out.println("Compass: W");
        if (" ".equals(compassX) && "up".equals(compassY)) System.out.println("Compass: N");
        if (" ".equals(compassX) && "down".equals(compassY)) System.out.println("Compass: S");
        
        if ("right".equals(compassX) && "up".equals(compassY)) System.out.println("Compass: NE");
        if ("left".equals(compassX) && "up".equals(compassY)) System.out.println("Compass: NW");
        if ("right".equals(compassX) && "down".equals(compassY)) System.out.println("Compass: SE");
        if ("left".equals(compassX) && "down".equals(compassY)) System.out.println("Compass: SW");
    }
    
    public void checkCompass(Player player, HostileEntity entity) {
        String compassX = " ", compassY = " ";
        this.beastSenseTimer++;
        
        if (this.beastSenseTimer == (16 - player.getInv().getCards().beastSensePoints)) {
            if (entity.isSpawned) {
                if (player.posX < entity.posX) compassX = "right";
                if (player.posX > entity.posX) compassX = "left";
                if (player.posY < entity.posY) compassY = "up";
                if (player.posY > entity.posY) compassY = "down";
                if (player.posX == entity.posX) compassX = " ";
                if (player.posY == entity.posY) compassY = " ";

                if ("right".equals(compassX) && " ".equals(compassY)) System.out.println("A ravager is towards: E");
                if ("left".equals(compassX) && " ".equals(compassY)) System.out.println("A ravager is towards: W");
                if (" ".equals(compassX) && "up".equals(compassY)) System.out.println("A ravager is towards: N");
                if (" ".equals(compassX) && "down".equals(compassY)) System.out.println("A ravager is towards: S");

                if ("right".equals(compassX) && "up".equals(compassY)) System.out.println("A ravager is towards: NE");
                if ("left".equals(compassX) && "up".equals(compassY)) System.out.println("A ravager is towards: NW");
                if ("right".equals(compassX) && "down".equals(compassY)) System.out.println("A ravager is towards: SE");
                if ("left".equals(compassX) && "down".equals(compassY)) System.out.println("A ravager is towards: SW");
            }
            
            this.beastSenseTimer = 0;
        }
    }
    
    public int getMaxWidth() {
        return maxWidth;
    }
    public int getMaxHeight() {
        return maxHeight;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public int getTreasureX() {
        return treasureX;
    }
    public void setTreasureX(int x) {
        this.treasureX = x;
    }
    public int getTreasureY() {
        return treasureY;
    }
    public void setTreasureY(int y) {
        this.treasureY = y;
    }
    
    public Tile[][] getDungeonTiles() {
        return dungeonTiles;
    }
    public void setDungeonTiles(Tile[][] t) {
        this.dungeonTiles = t;
    }
    public int[][] getTileID() {
        return tileID;
    }
    public void setTileID(int[][] i) {
        this.tileID = i;
    }
    public char[][] getBiomeMask() {
        return biomeMask;
    }
    public void setDungeonTiles(char[][] m) {
        this.biomeMask = m;
    }
    
    public int getSoulFlamePunched() {
        return soulFlamePunched;
    }
    public void setSoulFlamePunched(int s) {
        this.soulFlamePunched = s;
    }
    public int getBeastSenseTimer() {
        return beastSenseTimer;
    }
    public void setBeastSenseTimer(int t) {
        this.beastSenseTimer= t;
    }
    public int getClank() {
        return clank;
    }
    public void setClank(int c) {
        this.clank = c;
    }
}

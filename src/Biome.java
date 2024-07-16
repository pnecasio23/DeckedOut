/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.awt.Color;
import java.util.ArrayList;

public class Biome {

    public String biomeName;
    public char id;
    public int x;
    public int y;
    public Color color;
    public LootTable loot;
    
    public String[] roomList;
    public static ArrayList<Biome> biomeList = new ArrayList<Biome>();
    
    public Biome(String n, char i, int x, int y, String[] r, Color c, LootTable l) {
        biomeName = n;
        id = i;
        this.x = x;
        this.y = y;
        roomList = r;
        color = c;
        loot = l;
        biomeList.add(this);
    }
    
    public Biome(String n, char i, String[] r, Color c, LootTable l) {
        biomeName = n;
        id = i;
        roomList = r;
        color = c;
        loot = l;
        biomeList.add(this);
    }
    
    public Biome(String n, char i, Color c) {
        biomeName = n;
        id = i;
        color = c;
    }
    
    public static void initBiomes() {
        Color darkGreen = new Color(11, 102, 35);
        Color lime = new Color(57, 255, 0);
        Color brown = new Color(195, 155, 119);
        
        LootEntry[] ancientLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 2, 4),
            new LootEntry(new MiscItem(62, "Book"), 3, 1, 2),
            new LootEntry(new MiscItem(63, "Cobblestone"), 5, 1, 3),
            new LootEntry(new MiscItem(64, "Arrow"), 5, 1, 4)
        };
        
        LootEntry[] forestLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 2, 5),
            new LootEntry(new MiscItem(65, "Oak Sapling"), 3, 1, 3),
            new LootEntry(new MiscItem(66, "Oak Leaves"), 1, 1, 2),
            new LootEntry(new MiscItem(67, "Oak Log"), 5, 2, 4)
        };
        
        LootEntry[] castleLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 3, 7),
            new LootEntry(new MiscItem(68, "Chain"), 3, 1, 3),
            new LootEntry(new MiscItem(69, "Iron Bar"), 1, 1, 2),
            new LootEntry(new MiscItem(70, "Bone"), 5, 2, 5)
        };
        
        LootEntry[] bastionLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 15, 4, 8),
            new LootEntry(new MiscItem(68, "Magma Cream"), 3, 1, 3),
            new LootEntry(new MiscItem(69, "Obsidian"), 1, 1, 2),
            new LootEntry(new MiscItem(70, "Gilded Blackstone"), 3, 2, 5),
            new LootEntry(new MiscItem(71, "Spectral Arrow"), 5, 2, 7),
                new LootEntry(new MiscItem(72, "Lodestone"), 1, 1, 1)
        };
        
        LootEntry[] netherLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 2, 4),
            new LootEntry(new MiscItem(73, "Quartz"), 3, 1, 2),
            new LootEntry(new MiscItem(74, "Gold Nugget"), 5, 1, 3),
            new LootEntry(new MiscItem(75, "Saddle"), 10, 1, 4),
            new LootEntry(new MiscItem(76, "Crying Obsidian"), 2, 1, 2),
            new LootEntry(new MiscItem(69, "Obsidian"), 1, 1, 1)
        };
        
        LootEntry[] pirateLootEntries = {
            new LootEntry(new MiscItem(1, "Dungeon Coin"), 10, 2, 4),
            new LootEntry(new MiscItem(81, "Nautilus Shell"), 2, 1, 3),
            new LootEntry(new MiscItem(82, "Raw Cod"), 10, 2, 5),
            new LootEntry(new MiscItem(83, "Treasure Map"), 4, 1, 2)
        };
        
        LootTable ancientLoot = new LootTable(1, 3, ancientLootEntries);
        LootTable forestLoot = new LootTable(1, 3, forestLootEntries);
        LootTable castleLoot = new LootTable(1, 3, castleLootEntries);
        LootTable bastionLoot = new LootTable(1, 3, bastionLootEntries);
        LootTable netherLoot = new LootTable(1, 3, netherLootEntries);
        LootTable pirateLoot = new LootTable(1, 3, pirateLootEntries);
        
        //String[] roomsVoid = {"Void"};
        String[] roomsAncientTemple = {"Ancient Library", "Sacred Altar", "Lush Waterfalls"}; // "Ancient Dungeon"};
        String[] roomsForest = {"Graveyard", "Camp Site", "Small Beach", "The Lakes", "Moo Moo Meadows"};
        String[] roomsCastleKeep = {"Throne Room", "Smithing Room", "Temple", "Winery", "The Library"}; // "The Vaults", "Prison Cells"};
        String[] roomsBastionKeep = {"Torture Chamber", "Dragon Temple", "Cavern of Souls", "Wither Rose Graden", "Web of Lies"}; //, "Hoglin Stable"};
        String[] roomsNetherFortress = {"Gold Room", "Warped Forest", "Crimson Forest", "Basalt Mines"}; //"Blaze Chamber", "Nether Wart Farms"};
        String[] roomsPiratePub = {"Shipwreck Storage", "Beer Brewer", "Fishery"};
        //String[] roomsMesaCanyon = {"Gold Mineshaft", "Desert Temple", "Riches Room", "Desert Well"};
        //String[] roomsIcyMountain = {"Glacier Falls", "Taiga Cabin", "Icy Peak"};
        //String[] roomsMushroomSwamp = {"Witch Hut", "Mushroom Fields", "Mangrove Forest", "Mossy Lake"};
        
        
        //new Biome("Void", '0', roomsAncientTemple, darkGreen);
        new Biome("Ancient Temple", 'a', 0, 0, roomsAncientTemple, darkGreen, ancientLoot);
        new Biome("Forest", 'f', roomsForest, lime, forestLoot);
        new Biome("Castle Keep", 'c', roomsCastleKeep, Color.gray, castleLoot);
        new Biome("Bastion Keep", 'b', roomsBastionKeep, Color.darkGray, bastionLoot);
        new Biome("Nether Fortress", 'n', roomsNetherFortress, Color.red, netherLoot);
        new Biome("Pirate Pub", 'p', roomsPiratePub, brown, pirateLoot);
        //new Biome("Mesa Canyon", 'm', roomsMesaCanyon, Color.orange);
        //new Biome("Icy Mountain", 'i', roomsIcyMountain, Color.white);
        //new Biome("Mushroom Swamp", 's', roomsMushroomSwamp, Color.magenta);
    }
}

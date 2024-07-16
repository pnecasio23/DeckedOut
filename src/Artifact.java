/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class Artifact extends Item {
    
    public String item;
    public String rarity;
    public String set;
    public static ArrayList<Artifact> artifactsSet = new ArrayList<Artifact>();
    public static ArrayList<Artifact> commonArtifacts = new ArrayList<Artifact>();
    public static ArrayList<Artifact> uncommonArtifacts = new ArrayList<Artifact>();
    public static ArrayList<Artifact> rareArtifacts = new ArrayList<Artifact>();
    public static ArrayList<Artifact> uniqueArtifacts = new ArrayList<Artifact>();
    
    public Artifact(String i, String r, String s) {
        super(0, i);
        this.item = i;
        this.rarity = r;
        this.set = s;
        this.itemName = i + " - " + rarity + " (" + set + " Set)";
        
        artifactsSet.add(this);
        
        if (this.rarity.equalsIgnoreCase("COMMON")) commonArtifacts.add(this);
        if (this.rarity.equalsIgnoreCase("UNCOMMON")) uncommonArtifacts.add(this);
        if (this.rarity.equalsIgnoreCase("RARE")) rareArtifacts.add(this);
        if (this.rarity.equalsIgnoreCase("UNIQUE")) uniqueArtifacts.add(this);
    }
    
    public Artifact(Artifact a) {
        super(0, a.item);
        this.item = a.item;
        this.rarity = a.rarity;
        this.set = a.set;
        this.itemName = a.item + " - " + a.rarity + " (" + a.set + " Set)";
    }
    
    public static void initArtifacts() {
        //new Artifact("", "", "");
        new Artifact("Obsidian", "COMMON", "Dragon");
        new Artifact("End Crystal", "UNCOMMON", "Dragon");
        new Artifact("Dragon's Breath", "RARE", "Dragon");
        new Artifact("Dragon's Head", "UNIQUE", "Dragon");
        
        new Artifact("Soul Sand", "COMMON", "Wither");
        new Artifact("Wither Rose", "UNCOMMON", "Wither");
        new Artifact("Wither Head", "RARE", "Wither");
        new Artifact("Nether Star", "UNIQUE", "Wither");
        
        new Artifact("Glowstone", "COMMON", "Nether");
        new Artifact("Shroomlight", "UNCOMMON", "Nether");
        new Artifact("Ancient Debris", "RARE", "Nether");
        new Artifact("Respawn Anchor", "UNIQUE", "Nether");
    }

    public static void checkArtifacts(Player player, int bn1, int bn2, int bn3, int bn4) {
        boolean setCorrect = true;
        boolean rarityCorrect = false;
        boolean isCommon = false, isUncommon = false, isRare = false, isUnique = false;
        
        int[] chosenID = {bn1, bn2, bn3, bn4};
        String set = player.getInv().getArtifacts().artifactsBoard[chosenID[0]].set;
        
        for (int i = 0; i <= 3; i++) {
            if(!player.getInv().getArtifacts().artifactsBoard[chosenID[i]].set.equalsIgnoreCase(set)) {
                setCorrect = false;
            }
            if(player.getInv().getArtifacts().artifactsBoard[chosenID[i]].rarity.equalsIgnoreCase("COMMON")) {
                isCommon = true;
            }
            if(player.getInv().getArtifacts().artifactsBoard[chosenID[i]].rarity.equalsIgnoreCase("UNCOMMON")) {
                isUncommon = true;
            }
            if(player.getInv().getArtifacts().artifactsBoard[chosenID[i]].rarity.equalsIgnoreCase("RARE")) {
                isRare = true;
            }
            if(player.getInv().getArtifacts().artifactsBoard[chosenID[i]].rarity.equalsIgnoreCase("UNIQUE")) {
                isUnique = true;
            }
        }
        
        if(isCommon && isUncommon && isRare && isUnique) {
            rarityCorrect = true;
        }
        
        if(rarityCorrect && setCorrect) {
            player.setArtifactsPoints(player.getArtifactsPoints() + 1);
            System.out.println("You gained a point!");
            //System.out.println(set);
            //System.out.println(setCorrect);
            //System.out.println(rarityCorrect);
            
            for (int i = 0; i <= 3; i++) {
                player.getInv().getArtifacts().artifactsBoard[chosenID[i]] = null;
                player.getInv().getArtifacts().noOfArtifacts--;
            }
        }
        else {
            System.out.println("Set not complete!");
            System.out.println(set);
            System.out.println(setCorrect);
            System.out.println(rarityCorrect);
        }
    }
    
    public static void checkArtifacts(Player player, int bn1, int bn2, int bn3) {
        int[] chosenID = {bn1, bn2, bn3};
        int rand = (int) Math.ceil(Math.random() * 20);
        System.out.println("You received " + rand + " coins");
        
        rand = (int) Math.ceil(Math.random() * 100);
        if(rand > 90) {
            System.out.println("You received a key!");
            player.getInv().addKey();
        }
        
        for (int i = 0; i <= 2; i++) {
            player.getInv().getArtifacts().artifactsBoard[chosenID[i]] = null;
            player.getInv().getArtifacts().noOfArtifacts--;
        }
    }
    
    public static void randomArtifacts(Player player) {
        int rand;
        for (int i = 0; i <= 12; i++) {
            rand = (int) Math.ceil(Math.random() * 100);
            if(rand > 80) {
                rand = (int) Math.floor(Math.random() * uniqueArtifacts.size());
                addArtifacts(player, new Artifact(uniqueArtifacts.get(rand)));
            } else
            if(rand > 50) {
                rand = (int) Math.floor(Math.random() * rareArtifacts.size());
                addArtifacts(player, new Artifact(rareArtifacts.get(rand)));
            } else
            if(rand > 30) {
                rand = (int) Math.floor(Math.random() * uncommonArtifacts.size());
                addArtifacts(player, new Artifact(uncommonArtifacts.get(rand)));
            } else
            if(rand > 0) {
                rand = (int) Math.floor(Math.random() * commonArtifacts.size());
                addArtifacts(player, new Artifact(commonArtifacts.get(rand)));
            }
            
            player.getInv().getArtifacts().noOfArtifacts++;
        }
    }
    
    public static void addArtifacts(Player player, Artifact artifact) {
        for (int i = 0; i <= player.getInv().getArtifacts().artifactsBoard.length-1; i++) {
            if(player.getInv().getArtifacts().noOfArtifacts == 15) {
                System.out.println("Board is full!");
                break;
            }
            if(player.getInv().getArtifacts().artifactsBoard[i] == null) {
                player.getInv().getArtifacts().artifactsBoard[i] = artifact;
                System.out.println(artifact.itemName);
                break;
            }
        }
    }
}

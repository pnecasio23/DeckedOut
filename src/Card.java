/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/
import java.util.ArrayList;

public class Card extends Item {

    public String effect;
    public int tier;
    public boolean selected;
    
    public static ArrayList<Card> cardsSet = new ArrayList<Card>();
    public static ArrayList<Card> tierICards = new ArrayList<Card>();
    public static ArrayList<Card> tierIICards = new ArrayList<Card>();
    public static ArrayList<Card> tierIIICards = new ArrayList<Card>();
    
    public static void initCards() {
        for (int i = 1; i <= 3; i++) {
            new Card("Stealth", i);
            new Card("Soul Seeker", i);
            new Card("Loot Finder", i);
            new Card("Beast Sense", i);
        }
    }
    
    public Card (String e, int t) {
        super(1, e);
        this.effect = e;
        this.tier = t;
        
        if (this.tier == 1) this.itemName = e + " I";
        if (this.tier == 2) this.itemName = e + " II";
        if (this.tier == 3) this.itemName = e + " III";
        
        if (this.tier == 1) tierICards.add(this); 
        if (this.tier == 2) tierIICards.add(this); 
        if (this.tier == 3) tierIIICards.add(this); 
        
        cardsSet.add(this);
    }
    
    public Card (Card c) {
        super(1, c.itemName);
        this.effect = c.effect;
        this.tier = c.tier;
    }

    public static void checkTierICards(Player player, int bn1, int bn2, int bn3) {
        boolean levelCorrect = true;
        
        int[] chosenID = {bn1, bn2, bn3};
        
        for (int i = 0; i <= 2; i++) {
            if (player.getInv().getCards().cardDeck.get(chosenID[i]).selected) {
                System.out.println("A selected card was chosen!");
                levelCorrect = false;
                break;
            }
            
            if(player.getInv().getCards().cardDeck.get(chosenID[i]).tier == 1) {
                levelCorrect = true;
            }
            else {
                levelCorrect = false;
                break;
            }
        }
        
        if(levelCorrect) {
            randomTierIICard(player);
            System.out.println("Trading successful!");
            
            for (int i = 0; i <= 2; i++) {
                player.getInv().getCards().cardDeck.remove(player.getInv().getCards().cardDeck.get(chosenID[i]));
                player.getInv().getCards().noOfCards--;
            }
        }
        else {
            System.out.println("Trading unsuccessful!");
        }
    }
    
    public static void checkTierIICards(Player player, int bn1, int bn2, int bn3) {
        boolean levelCorrect = true;
        
        int[] chosenID = {bn1, bn2, bn3};
        
        for (int i = 0; i <= 2; i++) {
            if (player.getInv().getCards().cardDeck.get(chosenID[i]).selected) {
                System.out.println("A selected card was chosen!");
                levelCorrect = false;
                break;
            }
            
            if (player.getInv().getCards().cardDeck.get(chosenID[i]).tier == 2) {
                levelCorrect = true;
            }
            else {
                levelCorrect = false;
                break;
            }
        }
        
        if(levelCorrect) {
            randomTierIIICard(player);
            System.out.println("Trading successful!");
            
            for (int i = 0; i <= 2; i++) {
                player.getInv().getCards().cardDeck.remove(player.getInv().getCards().cardDeck.get(chosenID[i]));
                player.getInv().getCards().noOfCards--;
            }
        }
        else {
            System.out.println("Trading unsuccessful!");
        }
    }
    
    public static Card randomCard(Player player, int soulflames) {
        int rand;
        rand = (int) Math.ceil(Math.random() * 100);
        player.getInv().getCards().noOfCards++;
        if(rand > (99 - soulflames)) {
            rand = (int) Math.floor(Math.random() * tierICards.size());
            return new Card(tierICards.get(rand));
        } else
        if(rand > (80 - soulflames)) {
            rand = (int) Math.floor(Math.random() * tierIICards.size());
            return new Card(tierIICards.get(rand));
        } else {
            rand = (int) Math.floor(Math.random() * tierIIICards.size());
            return new Card(tierIIICards.get(rand));
        }
    }
    
    public static void randomCard(Player player) {
        int rand;
        for (int i = 0; i <= 13; i++) {
            rand = (int) Math.floor(Math.random() * cardsSet.size());
            addCards(player, new Card(cardsSet.get(rand)));
        }
    }
    
    public static void randomTierIICard(Player player) {
        int rand;
        rand = (int) Math.floor(Math.random() * tierIICards.size());
        addCards(player, tierIICards.get(rand));
        player.getInv().getCards().noOfCards++;
    }
    
    public static void randomTierIIICard(Player player) {
        int rand;
        rand = (int) Math.floor(Math.random() * tierIIICards.size());
        addCards(player, tierIIICards.get(rand));
        player.getInv().getCards().noOfCards++;
    }
    
    public static void addCards(Player player, Card card) {
        player.getInv().getCards().cardDeck.add(card);
        System.out.println(card.itemName);
    }
}

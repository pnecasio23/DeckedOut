/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.ArrayList;

public class CardSet {

    public int noOfCards;
    public Card[] myCardSet;
    public Card[] tempSet;
    public ArrayList<Card> cardDeck;
    
    public int stealthPoints;
    public int soulSeekerPoints;
    public int lootFinderPoints;
    public int beastSensePoints;
    
    public CardSet() {
        noOfCards = 0;
        cardDeck = new ArrayList<Card>();
        myCardSet = new Card[5];
        tempSet = new Card[5];
        stealthPoints = 0;
        soulSeekerPoints = 0;
        lootFinderPoints = 0;
        beastSensePoints = 0;
    }
    
    public void checkMyCardSet() {
        System.out.println("------------------------");
        System.out.println("Your card set: ");
        for (int i = 0; i <= 4; i++) {
            if (myCardSet[i] != null) System.out.println((i+1) + ": " + myCardSet[i].itemName);
        }
    }
    
    public void checkCardDeck() {
        for (int i = 0; i <= (cardDeck.size()-1); i++) {
            System.out.println((i+1) + ": " + cardDeck.get(i).itemName);
        }
    }
    
    public void changeCards(Player player) {
        int card1, card2, card3, card4, card5;
        
        System.out.print("Choose first card (ID): ");
        GUITemplate.setValue(player);
        card1 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        
        do {
        System.out.print("Choose second card (ID): ");
        GUITemplate.setValue(player);
        card2 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        
        if (card1 == card2) {System.out.println("Duplicate IDs!");}
        } while (card1 == card2); 
        
        do {
        System.out.print("Choose third card (ID): ");
        GUITemplate.setValue(player);
        card3 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        
        if (card1 == card3 || card2 == card3) {System.out.println("Duplicate IDs!");}
        } while (card1 == card3 || card2 == card3); 
        
        do {
        System.out.print("Choose fourth card (ID): ");
        GUITemplate.setValue(player);
        card4 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        
        if (card1 == card4 || card2 == card4 || card3 == card4) {System.out.println("Duplicate IDs!");}
        } while (card1 == card4 || card2 == card4 || card3 == card4); 
        
        do {
        System.out.print("Choose fifth card (ID): ");
        GUITemplate.setValue(player);
        card5 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        
        if (card1 == card5 || card2 == card5 || card3 == card5 || card4 == card5) {System.out.println("Duplicate IDs!");}
        } while (card1 == card5 || card2 == card5 || card3 == card5 || card4 == card5); 
        
        myCardSet[0] = cardDeck.get(card1);
        myCardSet[1] = cardDeck.get(card2);
        myCardSet[2] = cardDeck.get(card3);
        myCardSet[3] = cardDeck.get(card4);
        myCardSet[4] = cardDeck.get(card5);
        
        for (Card c : cardDeck) {
            c.selected = false;
        }
        
        cardDeck.get(card1).selected = true;
        cardDeck.get(card2).selected = true;
        cardDeck.get(card3).selected = true;
        cardDeck.get(card4).selected = true;
        cardDeck.get(card5).selected = true;
    }
    
    public void processCards(Player player) {
        stealthPoints = 0;
        soulSeekerPoints = 0;
        lootFinderPoints = 0;
        beastSensePoints = 0;
        
        for (int i = 0; i <= 4 && myCardSet[i] != null; i++) {
            if (myCardSet[i].effect.equalsIgnoreCase("Stealth")) {
                stealthPoints += myCardSet[i].tier;
            }
            
            if (myCardSet[i].effect.equalsIgnoreCase("Soul Seeker")) {
                soulSeekerPoints += myCardSet[i].tier;
            }
            
            if (myCardSet[i].effect.equalsIgnoreCase("Loot Finder")) {
                lootFinderPoints += myCardSet[i].tier;
            }
            
            if (myCardSet[i].effect.equalsIgnoreCase("Beast Sense")) {
                beastSensePoints += myCardSet[i].tier;
            }
        }
    }
}

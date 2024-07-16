/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.util.Scanner;

public class Trade {
    
    public static void chooseTrade(Player player) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1: Artifacts --> Points ");
        System.out.println("2: Artifacts --> Coins ");
        System.out.println("3: 3 Tier I --> 1 Tier II Card ");
        System.out.println("4: 3 Tier II --> 1 Tier III Card ");
        System.out.println("5: All Misc Items --> Coins ");
        System.out.println("> CLOSE <");
        System.out.print("What will you do? ");
        GUITemplate.setAction(player);

        switch (player.action) {
            case "1":
                artifactsToPoints(player);
                break;
            case "2":
                artifactsToCoins(player);
                break;
            case "3":
                tierICardsToTierIICards(player);
                break;
            case "4":
                tierIICardsToTierIIICards(player);
                break;
            case "5":
                tradeMiscItems(player);
                break;
        }
    }
    
    public static void artifactsToPoints(Player player) {
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------");
        System.out.print("Choose first artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact1 = player.number - 1;
        System.out.println("------------------------");
        System.out.print("Choose second artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact2 = player.number - 1;
        System.out.println("------------------------");
        System.out.print("Choose third artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact3 = player.number - 1;
        System.out.println("------------------------");
        System.out.print("Choose fourth artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact4 = player.number - 1;

        System.out.println("------------------------");
        System.out.println("You chose: ");
        System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact1].itemName);
        System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact2].itemName);
        System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact3].itemName);
        System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact4].itemName);
        System.out.println("------------------------");

        Artifact.checkArtifacts(player, artifact1, artifact2, artifact3, artifact4);
    }
    
    public static void artifactsToCoins(Player player) {
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------");
        System.out.print("Choose first artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact1 = player.number - 1;
        System.out.println("------------------------");
        System.out.print("Choose second artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact2 = player.number - 1;
        System.out.println("------------------------");
        System.out.print("Choose third artifact (ID): ");
        GUITemplate.setValue(player);
        int artifact3 = player.number - 1;
        
        try {
            System.out.println("------------------------");
            System.out.println("You chose: ");
            System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact1].itemName);
            System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact2].itemName);
            System.out.println(player.getInv().getArtifacts().artifactsBoard[artifact3].itemName);
            System.out.println("------------------------");

            Artifact.checkArtifacts(player, artifact1, artifact2, artifact3);
        }
        catch (NullPointerException err) {
            System.out.println("------------------------");
            System.out.println("Missing artifact/s!");
        }
        catch (IndexOutOfBoundsException err) {
            System.out.println("------------------------");
            System.out.println("There are no artifacts in that index!");
        }
    }
    
    public static void tierICardsToTierIICards(Player player) {
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------");
        System.out.print("Choose first card (ID): ");
        GUITemplate.setValue(player);
        int card1 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        System.out.print("Choose second card (ID): ");
        GUITemplate.setValue(player);
        int card2 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        System.out.print("Choose third card (ID): ");
        GUITemplate.setValue(player);
        int card3 = Integer.valueOf(player.number) - 1;
        
        try {
            if ((player.getInv().getCards().cardDeck.get(card1) != null) && (player.getInv().getCards().cardDeck.get(card2) != null) && (player.getInv().getCards().cardDeck.get(card3) != null)) {
                System.out.println("------------------------");
                System.out.println("You chose: ");
                System.out.println(player.getInv().getCards().cardDeck.get(card1).itemName);
                System.out.println(player.getInv().getCards().cardDeck.get(card2).itemName);
                System.out.println(player.getInv().getCards().cardDeck.get(card3).itemName);
                System.out.println("------------------------");

                Card.checkTierICards(player, card1, card2, card3);
            }
            else {
                System.out.println("Missing card/s!");
            }
        }
        catch (IndexOutOfBoundsException err) {
            System.out.println("------------------------");
            System.out.println("There are no cards in that index!");
        }
    }
    
    public static void tierIICardsToTierIIICards(Player player) {
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------");
        System.out.print("Choose first card (ID): ");
        GUITemplate.setValue(player);
        int card1 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        System.out.print("Choose second card (ID): ");
        GUITemplate.setValue(player);
        int card2 = Integer.valueOf(player.number) - 1;
        System.out.println("------------------------");
        System.out.print("Choose third card (ID): ");
        GUITemplate.setValue(player);
        int card3 = Integer.valueOf(player.number) - 1;
        
        try {
            if ((player.getInv().getCards().cardDeck.get(card1) != null) && (player.getInv().getCards().cardDeck.get(card2) != null) && (player.getInv().getCards().cardDeck.get(card3) != null)) {
                System.out.println("------------------------");
                System.out.println("You chose: ");
                System.out.println(player.getInv().getCards().cardDeck.get(card1).itemName);
                System.out.println(player.getInv().getCards().cardDeck.get(card2).itemName);
                System.out.println(player.getInv().getCards().cardDeck.get(card3).itemName);
                System.out.println("------------------------");

                Card.checkTierIICards(player, card1, card2, card3);
            }
            else {
                System.out.println("Missing card/s!");
            }
        }
        catch (IndexOutOfBoundsException err) {
            System.out.println("------------------------");
            System.out.println("There are no cards in that index!");
        }
    }
    
    public static void tradeMiscItems(Player player) {
         
    }
}

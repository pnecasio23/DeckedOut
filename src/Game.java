import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Game {
    
    public static String title = "Decked Out";
    public static boolean isDebugMode = false; //when false, input and output are set to the JTextArea
    
    private static Dungeon dungeonEasy = new Dungeon(5, 5, "Easy"); //new easy dungeon object
    private static Dungeon dungeonNormal = new Dungeon(10, 10, "Normal"); //new normal dungeon object
    private static Dungeon dungeonHard = new Dungeon(15, 15, "Hard"); //new hard dungeon object

    public static void main(String[] args) throws InterruptedException, IOException, FontFormatException {
        
        String pName = JOptionPane.showInputDialog(null,
                    "What is your name? ", "Enter your name",
                    JOptionPane.QUESTION_MESSAGE); //asks for name
          
        Player player = new Player(pName);
        GUITemplate frame = new GUITemplate(player);
        
        System.out.println("Welcome to Decked Out!");
        System.out.println("Hope you have fun playing!");
        init(player); //initiate variables and objects
        
        do {
            frame.resetDungeonStats(player);
            System.out.println("------------------------");
            System.out.println("1: Enter Dungeon ");
            System.out.println("2: Visit Shop ");
            System.out.println("3: Visit Trading Area ");
            System.out.println("4: Check Stats ");
            System.out.println("5: Check Inventory ");
            System.out.println("6: Change Card Set ");
            System.out.println("7: Check Card Deck ");
            System.out.println("8: Check Board ");
            System.out.println("> QUIT < ");
            System.out.print("What will you do? ");
            frame.setAction(player);
            System.out.println("------------------------");

            switch(player.action) {
                case "1":
                    if (player.getInv().noOfKeys() > 0) {
                        for (int i = 0; i <= Dungeon.dungeonList.size()-1; i++) {
                            System.out.println((i + 1) + ": " + Dungeon.dungeonList.get(i).getDifficulty() + " (" + Dungeon.dungeonList.get(i).getMaxWidth() + "x" + Dungeon.dungeonList.get(i).getMaxHeight() + ") ");
                        }
                        System.out.print("What difficulty do you want to play? ");
                        frame.setAction(player);
                        System.out.println("------------------------");
                        
                        switch(player.action) {
                            case "1": //play an easy dungeon
                                dungeonEasy.generateDungeon(player);
                                dungeonEasy.playDungeon(frame, player);
                                break;
                            case "2": //play a normal dungeon
                                dungeonNormal.generateDungeon(player);
                                dungeonNormal.playDungeon(frame, player);
                                break;
                            case "3": //play a hard dungeon
                                dungeonHard.generateDungeon(player);
                                dungeonHard.playDungeon(frame, player);
                                break;
                        }
                        player.getInv().removeKey();
                    } 
                    else {
                        System.out.println("You have no more dungeon keys!");
                    }
                    break;
                case "2":
                    Shop.chooseShop(player);
                    break;
                case "3":
                    Trade.chooseTrade(player);
                    break;
                case "4":
                    player.printStats();
                    break;
                case "5":
                    player.getInv().checkInventory();
                    player.getInv().getCards().checkMyCardSet();
                    break;
                case "6":
                    if (player.getInv().getCards().cardDeck.size() >= 5) {player.getInv().getCards().changeCards(player);}
                    else System.out.println("You don't have enough cards yet!");
                    break;
                case "7":
                    player.getInv().getCards().checkCardDeck();
                    break;
                case "8":
                    System.out.println("Points: " + player.getArtifactsPoints());
                    player.getInv().getArtifacts().checkArtifactsBoard(player);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
            
            Thread.sleep(3000);
            
        } while (!"quit".equalsIgnoreCase(player.action));
        
        System.exit(0);
    }
    
    public static void init(Player p) throws InterruptedException {
        Biome.initBiomes();
        Shop.initShop();
        LootTable.initLoots(p);
        PassiveEntity.initEntities();
        HostileEntity.initEntities();
        Card.initCards();
        Artifact.initArtifacts();
        
        p.getInv().addCoins(10000);
        p.getInv().addKey();
        p.getInv().addLootbox();
        Card.randomCard(p);
        Artifact.randomArtifacts(p);
    }
}

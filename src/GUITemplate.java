/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.GroupLayout.Alignment.*;

public class GUITemplate extends JFrame implements ActionListener{

    public Font mcreg, mcbold;
    public JPanel northPanel, southPanel, main, inventory;
    public JPanel header, info, playerStats, roomStats, pics, slots, board;
    public JTextArea output;
    public static boolean pressed;
    
    public JLabel title, screen, name, hp, hunger, def, atk, slot, coords, biome, room, coin, point;
    public JLabel nameIcon, hpIcon, hungerIcon, defIcon, atkIcon, coordsIcon, biomeIcon, roomIcon, coinIcon, pointIcon;
    public static JTextField input;  
    public JButton enter, foods, potions, misc, cardSet, cardDeck, artifactsBoard;
    
    static Scanner sc = new Scanner(System.in);
    
    Color darkGray = new Color(22, 22, 22);
    Color lightGray = new Color(198, 198, 198);
    Color gray = new Color(139, 139, 139);
    
    public GUITemplate(Player p) throws IOException, FontFormatException {
        //set window
        super("Decked Out");
        this.setTitle("Decked Out");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(854, 480);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        
        URL url = ClassLoader.getSystemResource("textures/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        this.setIconImage(img);
        
        mcreg = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcreg = mcreg.deriveFont(Font.PLAIN, 15);
        mcbold = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft_bold.otf"));
        mcbold = mcbold.deriveFont(Font.PLAIN, 25);
       
        //initialize
        
        northPanel = new JPanel();
        southPanel = new JPanel();
        main = new JPanel();
        
        inventory = new JPanel();
        slots = new JPanel();
        board = new JPanel();
        header = new JPanel();
        info = new JPanel();
        playerStats = new JPanel();
        roomStats = new JPanel();
        pics = new JPanel();
        
        output = new JTextArea();
        
        title = new JLabel("Decked Out");
        name = new JLabel("Name: ");
        screen = new JLabel();
        hp = new JLabel("HP: ");
        hunger = new JLabel("Hunger: ");
        atk = new JLabel("ATK: ");
        def = new JLabel("DEF: ");
        coords = new JLabel("X: Y: ");
        biome = new JLabel("Biome: ");
        room = new JLabel("Room: ");
        coin = new JLabel("Coins: ");
        point = new JLabel("Points: ");
        
        nameIcon = new JLabel();
        hpIcon = new JLabel();
        hungerIcon = new JLabel();
        atkIcon = new JLabel();
        defIcon = new JLabel();
        coordsIcon = new JLabel();
        biomeIcon = new JLabel();
        roomIcon = new JLabel();
        coinIcon = new JLabel();
        pointIcon = new JLabel();
        
        foods = new JButton("Food Inventory");
        potions = new JButton("Potions Inventory");
        misc = new JButton("Misc Inventory");
        cardSet = new JButton("Card Set");
        cardDeck = new JButton("Card Deck");
        artifactsBoard = new JButton("Artifacts Board");
        
        this.setBackground(darkGray);
        //northPanel
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(darkGray);
        title.setForeground(Color.white);
        header.setBackground(darkGray);
        header.add(title);
        northPanel.add(header);
        
        //main panel
        GridLayout mainGrid = new GridLayout(2, 2);
        main.setLayout(mainGrid);
        //screen panel
        screen.setLayout(new FlowLayout(FlowLayout.CENTER));
        ImageIcon background = new ImageIcon(Game.class.getResource("textures/background.png"));
        screen.setIcon(background);
        pics.add(screen);
        pics.setBackground(darkGray);
        pics.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //info panel
        GridLayout infoGrid = new GridLayout(1, 2);
        info.setLayout(infoGrid);
        
        //playerStats
        GroupLayout playerlayout = new GroupLayout(playerStats);
        playerStats.setLayout(playerlayout);
        playerStats.setBackground(lightGray);
        
        nameIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/name.png")));
        hpIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/hp.png")));
        hungerIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/hunger.png")));
        atkIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/atk.png")));
        defIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/def.png")));
        
        playerlayout.setAutoCreateGaps(true);
        playerlayout.setAutoCreateContainerGaps(true);
        playerlayout.setHorizontalGroup(playerlayout.createSequentialGroup()
            .addGroup(playerlayout.createParallelGroup(LEADING)
                .addComponent(nameIcon)
                .addComponent(hpIcon)
                .addComponent(hungerIcon)
                .addComponent(atkIcon)
                .addComponent(defIcon))
            .addGroup(playerlayout.createParallelGroup(LEADING)
                .addComponent(name)
                .addComponent(hp)
                .addComponent(hunger)
                .addComponent(atk)
                .addComponent(def))
        );

        playerlayout.setVerticalGroup(playerlayout.createSequentialGroup()
            .addGroup(playerlayout.createParallelGroup(BASELINE)
                .addComponent(nameIcon)
                .addComponent(name))
            .addGroup(playerlayout.createParallelGroup(LEADING)
                .addComponent(hpIcon)
                .addComponent(hp))
            .addGroup(playerlayout.createParallelGroup(BASELINE)
                .addComponent(hungerIcon)
                .addComponent(hunger))
            .addGroup(playerlayout.createParallelGroup(BASELINE)
                .addComponent(atkIcon)
                .addComponent(atk))
            .addGroup(playerlayout.createParallelGroup(BASELINE)
                .addComponent(defIcon)
                .addComponent(def)));
        
        //roomStats
        GroupLayout roomlayout = new GroupLayout(roomStats);
        roomStats.setLayout(roomlayout);
        roomStats.setBackground(lightGray);
        
        coordsIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/coords.png")));
        biomeIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/biome.png")));
        roomIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/room.png")));
        coinIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/coin.png")));
        pointIcon.setIcon(new ImageIcon(Game.class.getResource("textures/icon/point.png")));
        
        roomlayout.setAutoCreateGaps(true);
        roomlayout.setAutoCreateContainerGaps(true);
        roomlayout.setHorizontalGroup(roomlayout.createSequentialGroup()
            .addGroup(roomlayout.createParallelGroup(LEADING)
                .addComponent(coordsIcon)
                .addComponent(biomeIcon)
                .addComponent(roomIcon)
                .addComponent(coinIcon)
                .addComponent(pointIcon))
            .addGroup(roomlayout.createParallelGroup(LEADING)
                .addComponent(coords)
                .addComponent(biome)
                .addComponent(room)
                .addComponent(coin)
                .addComponent(point))
        );

        roomlayout.setVerticalGroup(roomlayout.createSequentialGroup()
            .addGroup(roomlayout.createParallelGroup(BASELINE)
                .addComponent(coordsIcon)
                .addComponent(coords))
            .addGroup(roomlayout.createParallelGroup(BASELINE)
                .addComponent(biomeIcon)
                .addComponent(biome))
            .addGroup(roomlayout.createParallelGroup(BASELINE)
                .addComponent(roomIcon)
                .addComponent(room))
            .addGroup(roomlayout.createParallelGroup(BASELINE)
                .addComponent(coinIcon)
                .addComponent(coin))
            .addGroup(roomlayout.createParallelGroup(BASELINE)
                .addComponent(pointIcon)
                .addComponent(point)));
        
        info.add(playerStats);
        info.add(roomStats);
        
        //output panel
        output.setLayout(new FlowLayout(FlowLayout.CENTER));
        output.setEditable(false);
        output.setText("");
        JScrollPane scrollableTextArea = new JScrollPane(output);
        
        PrintStream printStream = new PrintStream(new CustomOutputStream(output));
        if (!Game.isDebugMode) System.setOut(printStream);
        if (!Game.isDebugMode) System.setErr(printStream);
        
        //inventory panel
        GridLayout invGrid = new GridLayout(1, 2);
        inventory.setLayout(invGrid);
        
        GridLayout slotsGrid = new GridLayout(3, 1);
        slots.setLayout(slotsGrid);
        slots.setBackground(lightGray);
        
        foods.setForeground(Color.white);
        foods.setBackground(darkGray);
        foods.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI foodInv = new InventoryGUI(p.getInv().getFoods());
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        misc.setForeground(Color.white);
        misc.setBackground(darkGray);
        misc.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI miscInv = new InventoryGUI(p.getInv().getMisc());
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        potions.setForeground(Color.white);
        potions.setBackground(darkGray);
        potions.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI potionInv = new InventoryGUI(p.getInv().getPotions());
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        slots.add(foods);
        slots.add(potions);
        slots.add(misc);
        
        //board panel
        GridLayout boardGrid = new GridLayout(3, 1);
        board.setLayout(boardGrid);
        board.setBackground(lightGray);
        
        cardSet.setForeground(Color.white);
        cardSet.setBackground(darkGray);
        cardSet.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI cardSetInv = new InventoryGUI(p.getInv().getCards().myCardSet);
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        cardDeck.setForeground(Color.white);
        cardDeck.setBackground(darkGray);
        cardDeck.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI cardDeckInv = new InventoryGUI(p.getInv().getCards().cardDeck);
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        artifactsBoard.setForeground(Color.white);
        artifactsBoard.setBackground(darkGray);
        artifactsBoard.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    InventoryGUI artifactsBoardInv = new InventoryGUI(p.getInv().getArtifacts().artifactsBoard);
                } catch (FontFormatException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUITemplate.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        board.add(cardSet);
        board.add(cardDeck);
        board.add(artifactsBoard);
        
        inventory.add(slots);
        inventory.add(board);
        
        //add panels
        main.add(pics);
        main.add(info);
        main.add(scrollableTextArea);
        main.add(inventory);

        //southPanel
        input = new JTextField("", 20);
        enter = new JButton("Enter");
        enter.setForeground(Color.white);
        enter.setBackground(darkGray);
        enter.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                pressed = true;
                System.out.println(input.getText());
                pressed = false;
            } 
        });
        southPanel.setBackground(darkGray);
        southPanel.add(input);
        southPanel.add(enter);
        
        //setFont
        title.setFont(mcbold);
        name.setFont(mcreg);
        hp.setFont(mcreg);
        hunger.setFont(mcreg);
        def.setFont(mcreg);
        atk.setFont(mcreg);
        coords.setFont(mcreg);
        biome.setFont(mcreg);
        room.setFont(mcreg);
        coin.setFont(mcreg);
        point.setFont(mcreg);
        input.setFont(mcreg);
        output.setFont(mcreg);
        enter.setFont(mcreg);
        foods.setFont(mcreg);
        misc.setFont(mcreg);
        potions.setFont(mcreg);
        cardSet.setFont(mcreg);
        cardDeck.setFont(mcreg);
        artifactsBoard.setFont(mcreg);
        
        //setBorder
        northPanel.setBorder(BorderFactory.createLineBorder(darkGray));
        main.setBorder(BorderFactory.createLineBorder(darkGray));
        southPanel.setBorder(BorderFactory.createLineBorder(darkGray));
        pics.setBorder(BorderFactory.createLineBorder(darkGray));
        info.setBorder(BorderFactory.createLineBorder(darkGray));
        inventory.setBorder(BorderFactory.createLineBorder(darkGray));
        slots.setBorder(BorderFactory.createLineBorder(darkGray));
        board.setBorder(BorderFactory.createLineBorder(darkGray));
        playerStats.setBorder(BorderFactory.createLineBorder(darkGray));
        roomStats.setBorder(BorderFactory.createLineBorder(darkGray));
        output.setBorder(BorderFactory.createLineBorder(darkGray));
        
        //add superpanels
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(main, BorderLayout.CENTER);
        
        this.setVisible(!Game.isDebugMode);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void updatePlayerStats(Player player) {
        this.name.setText("Name: " + player.getName());
        this.hp.setText("HP: " + player.getHP() + "/" + player.getMaxHP());
        this.hunger.setText("Hunger: " + player.getFoodLevel() + "/" + player.getFoodMaxLevel());
        this.atk.setText("ATK: " + player.getAP());
        this.def.setText("DEF: " + player.getDP());
    }
    
    public void updateDungeonStats(Player player, Dungeon dungeon) {
        updatePlayerStats(player);
        this.coords.setText("X: " + player.posX + " Y: " + player.posY);
        this.biome.setText("Biome: " + dungeon.getDungeonTiles()[player.posX][player.posY].biome.biomeName);
        this.room.setText("Room: " + dungeon.getDungeonTiles()[player.posX][player.posY].room);
        try {
            ImageIcon picture = new ImageIcon(Game.class.getResource("textures/biome/" + dungeon.getDungeonTiles()[player.posX][player.posY].biome.id + ".png"));
            this.screen.setIcon(picture);
        }
        catch (NullPointerException err) {
            ImageIcon background = new ImageIcon(Game.class.getResource("textures/background.png"));
            screen.setIcon(background);
            System.out.println("Picture not found");
        }
        this.coin.setText("Coins: " + player.getInv().noOfCoins());
        this.point.setText("Points: " + player.getArtifactsPoints());
    }
    
    public void resetDungeonStats(Player player) {
        updatePlayerStats(player);
        this.coords.setText("X: 0 Y: 0");
        this.biome.setText("Biome: Nether Hall");
        this.room.setText("Room: Entrance");
        this.coin.setText("Coins: " + player.getInv().noOfCoins());
        this.point.setText("Points: " + player.getArtifactsPoints());
        ImageIcon background = new ImageIcon(Game.class.getResource("textures/background.png"));
        screen.setIcon(background);
    }
    
    public static void setAction(Player player) {
        if (!Game.isDebugMode) {
            readLoop:
            while (!pressed) {
                try {player.action = input.getText();}
                catch (NullPointerException err) {}
            }
            player.action = input.getText();
            input.setText("");
        }
        else {
            player.action = sc.nextLine();
        }
    }
    
    public static void setValue(Player player) {
        if (!Game.isDebugMode) {
            readLoop:
            while (!pressed) {
                try { player.number = Integer.valueOf(input.getText()); }
                catch (NumberFormatException err) {}
            }
            player.number = Integer.valueOf(input.getText());
            input.setText("");
        }
        else {
            player.number = Integer.valueOf(input.getText());
        }
    }
    
    //from: https://stackoverflow.com/questions/5107629/how-to-redirect-console-content-to-a-textarea-in-java
    public class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}

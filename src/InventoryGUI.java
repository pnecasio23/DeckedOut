/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class InventoryGUI extends JFrame implements MouseListener{

    JPanel inventory, slot, details, prevClicked;
    JLabel itemIcon, info1, info2, info3;
    
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image icon;
    Color gray = new Color(155, 155, 155);
    Color darkGray = new Color(55, 55, 55);
    Color cyan = new Color(55, 255, 255);
    public Font mcreg, mcalt, mcbold;
    
    public InventoryGUI(FoodSlot[] f) throws FontFormatException, IOException {
        super("Food Inventory");
        this.setTitle("Food Inventory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/food.png")));
        initFonts();
        
        inventory = new JPanel();
        
        GridLayout invGrid = new GridLayout(2, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < f.length; i++) {
            slot = new FoodInvSlot(f[i]);
            prevClicked = slot;
            slot.addMouseListener(this);
            inventory.add(slot);
        }
        
        inventory.setBorder(BorderFactory.createLineBorder(Color.black));
        
        this.add(inventory);
        this.setVisible(true);
    }
    
    public InventoryGUI(PotionSlot[] p) throws FontFormatException, IOException {
        super("Potions Inventory");
        this.setTitle("Potions Inventory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/potion.png")));
        initFonts();
        
        inventory = new JPanel();
        
        GridLayout invGrid = new GridLayout(2, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < p.length; i++) {
            slot = new JPanel();
            itemIcon = new JLabel();
            info1 = new JLabel();
            info2 = new JLabel();
            prevClicked = slot;
            
            if (p[i].item != null) {
                try {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/potion/" + p[i].item.fileName + ".gif"));
                    itemIcon.setIcon(icon);
                }
                catch (NullPointerException err) {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                    itemIcon.setIcon(icon);
                }
                
                info1 = new JLabel(p[i].item.type);
                if (p[i].item.potency == 1) info2 = new JLabel("I");
                if (p[i].item.potency == 2) info2 = new JLabel("II");
                
                itemIcon.setAlignmentX(0.5f);
                info1.setAlignmentX(0.5f);
                info2.setAlignmentX(0.5f);
                info1.setFont(mcreg);
                info2.setFont(mcalt);

                slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));
                slot.add(itemIcon);
                slot.add(info1);
                slot.add(info2);
            }
            
            slot.addMouseListener(this);
            slot.setBackground(gray);
            info1.setForeground(Color.black);
            info2.setForeground(Color.black);
            slot.setBorder(BorderFactory.createLineBorder(darkGray));
            inventory.add(slot);
        }
        
        this.add(inventory);
        this.setVisible(true);
    }
    
    public InventoryGUI(MiscSlot[] m) throws FontFormatException, IOException {
        super("Misc Inventory");
        this.setTitle("Misc Inventory");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/misc.png")));
        initFonts();
        
        inventory = new JPanel();
        
        GridLayout invGrid = new GridLayout(4, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < m.length; i++) {
            slot = new JPanel();
            itemIcon = new JLabel();
            info1 = new JLabel();
            prevClicked = slot;
                
            if (m[i].item != null) {
                try {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/misc/" + m[i].item.fileName + ".png"));
                    itemIcon.setIcon(icon);
                }
                catch (NullPointerException err) {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                    itemIcon.setIcon(icon);
                }
                
                info1.setText(String.valueOf(m[i].amount));
                
                itemIcon.setAlignmentX(0.5f);
                info1.setAlignmentX(0.5f);
                info1.setFont(mcreg);

                slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));

                slot.add(itemIcon);
                slot.add(info1);
                inventory.add(slot);
            }
            
            slot.addMouseListener(this);
            slot.setBackground(gray);
            info1.setForeground(Color.black);
            slot.setBorder(BorderFactory.createLineBorder(darkGray));
            inventory.add(slot);
        }
        
        this.add(inventory);
        this.setVisible(true);
    }
    
    public InventoryGUI(Card[] c) throws FontFormatException, IOException {
        super("Card Set");
        this.setTitle("Card Set");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 130);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/card.png")));
        initFonts();
        
        inventory = new JPanel();
        
        GridLayout invGrid = new GridLayout(1, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < c.length; i++) {
            slot = new JPanel();
            itemIcon = new JLabel();
            info1 = new JLabel();
            info2 = new JLabel();
            prevClicked = slot;
                
            if (c[i] != null) {
                try {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/card/card.gif"));
                    itemIcon.setIcon(icon);
                }
                catch (NullPointerException err) {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                    itemIcon.setIcon(icon);
                }
                
                info1 = new JLabel(String.valueOf(c[i].effect));
                if (c[i].tier == 1) info2 = new JLabel("I");
                if (c[i].tier == 2) info2 = new JLabel("II");
                if (c[i].tier == 3) info2 = new JLabel("III");
                
                itemIcon.setAlignmentX(0.5f);
                info1.setAlignmentX(0.5f);
                info2.setAlignmentX(0.5f);
                info1.setFont(mcreg);
                info2.setFont(mcalt);

                slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));
                slot.add(itemIcon);
                slot.add(info1);
                slot.add(info2);
            }
            
            slot.addMouseListener(this);
            slot.setBackground(gray);
            info1.setForeground(Color.black);
            info2.setForeground(Color.black);
            slot.setBorder(BorderFactory.createLineBorder(darkGray));
            inventory.add(slot);
        }
        
        this.add(inventory);
        this.setVisible(true);
    }
    
    public InventoryGUI(ArrayList<Card> c) throws FontFormatException, IOException {
        super("Card Deck");
        this.setTitle("Card Deck");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/card.png")));
        initFonts();
        
        inventory = new JPanel();
        int rows = 4, maxSlots = 20;
        
        if (c.size() > 20) {
            if (c.size() % 5 == 0) {
                rows += (c.size() / 5) - 4;
                maxSlots = c.size();
            } 
            else {
                rows += ((c.size() / 5) + 1) - 4;
                maxSlots = rows * 5;
            }
        }
        
        GridLayout invGrid = new GridLayout(rows, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < maxSlots; i++) {
            slot = new JPanel();
            itemIcon = new JLabel();
            info1 = new JLabel();
            info2 = new JLabel();
            info3 = new JLabel();
            prevClicked = slot;
            
            if (c.size() >= (i + 1) && c.get(i) != null) {
                try {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/card/card.gif"));
                    itemIcon.setIcon(icon);
                }
                catch (NullPointerException err) {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                    itemIcon.setIcon(icon);
                }
                
                info1.setText(String.valueOf(c.get(i).effect));
                
                if (c.get(i).tier == 1) info2.setText("I");
                if (c.get(i).tier == 2) info2.setText("II");
                if (c.get(i).tier == 3) info2.setText("III");
                if (c.get(i).selected) info3 = new JLabel("SELECTED");
                
                itemIcon.setAlignmentX(0.5f);
                info1.setAlignmentX(0.5f);
                info2.setAlignmentX(0.5f);
                info3.setAlignmentX(0.5f);
                info1.setFont(mcreg);
                info2.setFont(mcalt);
                info3.setFont(mcalt);
            }
            
            slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));
            slot.add(itemIcon);
            slot.add(info1);
            slot.add(info2);
            slot.add(info3);
            slot.setPreferredSize(new Dimension(100,100));
            inventory.add(slot);
            
            slot.addMouseListener(this);
            slot.setBackground(gray);
            info1.setForeground(Color.black);
            info2.setForeground(Color.black);
            info3.setForeground(Color.blue);
            slot.setBorder(BorderFactory.createLineBorder(darkGray));
        }
        
        JScrollPane scrollableInv = new JScrollPane(inventory);
        scrollableInv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(scrollableInv);
        this.setVisible(true);
    }
    
    public InventoryGUI(Artifact[] a) throws FontFormatException, IOException {
        super("Artifacts Board");
        this.setTitle("Artifacts Board");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/artifact.png")));
        initFonts();
        
        inventory = new JPanel();
        GridLayout invGrid = new GridLayout(3, 5);
        inventory.setLayout(invGrid);
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                slot = new JPanel();
                itemIcon = new JLabel();
                info1 = new JLabel();
                info2 = new JLabel();
                info3 = new JLabel();
                prevClicked = slot;

                try {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/artifact/" + a[i].fileName + ".png"));
                    itemIcon.setIcon(icon);
                }
                catch (NullPointerException err) {
                    ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                    itemIcon.setIcon(icon);
                }
                
                info1 = new JLabel(String.valueOf(a[i].item));
                info2 = new JLabel(String.valueOf(a[i].set));
                info3 = new JLabel(String.valueOf(a[i].rarity));
                
                itemIcon.setAlignmentX(0.5f);
                info1.setAlignmentX(0.5f);
                info2.setAlignmentX(0.5f);
                info3.setAlignmentX(0.5f);
                info1.setFont(mcreg);
                info2.setFont(mcalt);
                info3.setFont(mcalt);

                slot.setLayout(new BoxLayout(slot, BoxLayout.Y_AXIS));
                slot.add(itemIcon);
                slot.add(info1);
                slot.add(info2);
                slot.add(info3);
                slot.setPreferredSize(new Dimension(100,100));
            }
            else {
                slot = new JPanel();
                slot = new JPanel();
                itemIcon = new JLabel();
                info1 = new JLabel();
                info2 = new JLabel();
                info3 = new JLabel();
            }
            
            slot.addMouseListener(this);
            slot.setBackground(gray);
            info1.setForeground(Color.black);
            info2.setForeground(Color.black);
            if (a[i] != null) {
                if (a[i].rarity.equalsIgnoreCase("common")) info3.setForeground(Color.black);
                if (a[i].rarity.equalsIgnoreCase("uncommon")) info3.setForeground(Color.yellow);
                if (a[i].rarity.equalsIgnoreCase("rare")) info3.setForeground(Color.blue);
                if (a[i].rarity.equalsIgnoreCase("unique")) info3.setForeground(Color.magenta);
            }
            slot.setBorder(BorderFactory.createLineBorder(darkGray));
            inventory.add(slot);
        }
        
        this.add(inventory);
        this.setVisible(true);
    }
    
    public void initFonts() throws FontFormatException, IOException{
        mcreg = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcreg = mcreg.deriveFont(Font.BOLD, 13);
        mcalt = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcalt = mcalt.deriveFont(Font.BOLD, 10);
        mcbold = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft_bold.otf"));
        mcbold = mcbold.deriveFont(Font.PLAIN, 25);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel clicked = (JPanel) e.getSource();
        clicked.setBackground(Color.white);
        //clicked.getComponent(1).setForeground(Color.black);
        clicked.setBorder(BorderFactory.createLineBorder(Color.black));
        
        prevClicked.setBackground(gray);
        //prevClicked.getComponent(1).setForeground(Color.white);
        prevClicked.setBorder(BorderFactory.createLineBorder(darkGray));
        prevClicked = clicked;
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}




/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class InfoGUI extends JFrame {

    JPanel main, iconPanel, detailsPanel, infoPanel, actionPanel;
    JLabel itemIcon, title, info1, info2, info3, desc;
    JButton action;
    
    public Font mcreg, mcalt, mcbold;
    
    public InfoGUI(FoodSlot f) throws FontFormatException, IOException {
        super("Information");
        this.setTitle("Information");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (0, 0));
        //this.setIconImage(kit.createImage(ClassLoader.getSystemResource("textures/icon/potion.png")));
        initFonts();
        
        main = new JPanel();
        iconPanel = new JPanel(); 
        detailsPanel = new JPanel(); 
        infoPanel = new JPanel();
        actionPanel = new JPanel();
        
        itemIcon = new JLabel();
        title = new JLabel();
        info1 = new JLabel();
        info2 = new JLabel();
        info3 = new JLabel();
        desc = new JLabel();
        
        action = new JButton();
        
        infoPanel.setLayout(new GridLayout(1, 2));
        
        //iconPanel
        itemIcon.setIcon(new ImageIcon(Game.class.getResource("textures/food/" + f.item.fileName + ".png")));
        title.setText(f.item.itemName);
        
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.add(itemIcon);
        iconPanel.add(title);
        
        //detailsPanel
        info1.setText("Hunger: " + String.valueOf(f.item.restoreHunger));
        info2.setText("Saturation: " + String.valueOf(f.item.restoreSaturation));
        info3.setText("Amount: " + String.valueOf(f.amount));
        
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.add(info1);
        detailsPanel.add(info2);
        detailsPanel.add(info3);
        
        //actionPanel
        action.setText("Eat");
        actionPanel.add(action);
        
        itemIcon.setAlignmentX(0.5f);
        title.setAlignmentX(0.5f);
        info1.setAlignmentX(0.5f);
        info2.setAlignmentX(0.5f);
        info3.setAlignmentX(0.5f);
        
        title.setFont(mcreg);
        info1.setFont(mcreg);
        info2.setFont(mcreg);
        info3.setFont(mcreg);
        action.setFont(mcreg);
        
        main.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        iconPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        infoPanel.add(iconPanel);
        infoPanel.add(detailsPanel);
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(actionPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    public void initFonts() throws FontFormatException, IOException{
        mcreg = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcreg = mcreg.deriveFont(Font.PLAIN, 15);
        mcalt = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcalt = mcalt.deriveFont(Font.BOLD, 10);
        mcbold = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft_bold.otf"));
        mcbold = mcbold.deriveFont(Font.PLAIN, 20);
    }
    
}

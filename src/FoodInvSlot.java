/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class FoodInvSlot extends JPanel implements MouseListener {

    public FoodSlot slot;
    public JLabel itemIcon, title, info1;
    
    Color gray = new Color(155, 155, 155);
    Color darkGray = new Color(55, 55, 55);
    
    public Font mcreg, mcalt, mcbold;
    
    public FoodInvSlot(FoodSlot f) throws FontFormatException, IOException {
        slot = f;
        itemIcon = new JLabel();
        info1 = new JLabel();
        initFonts();
        
        if (f.item != null) {
            try {
                ImageIcon icon = new ImageIcon(Game.class.getResource("textures/food/" + f.item.fileName + ".png"));
                itemIcon.setIcon(icon);
            }
            catch (NullPointerException err) {
                ImageIcon icon = new ImageIcon(Game.class.getResource("textures/missing.png"));
                itemIcon.setIcon(icon);
            }

            info1 = new JLabel(String.valueOf(f.amount));

            itemIcon.setAlignmentX(0.5f);
            info1.setAlignmentX(0.5f);
            info1.setFont(mcreg);
            info1.setForeground(Color.black);
        }
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(gray);
        this.setBorder(BorderFactory.createLineBorder(darkGray));

        this.addMouseListener(this);
        this.add(itemIcon);
        this.add(info1);
    }
    
    public void initFonts() throws FontFormatException, IOException{
        mcreg = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcreg = mcreg.deriveFont(Font.PLAIN, 15);
        mcalt = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft.otf"));
        mcalt = mcalt.deriveFont(Font.BOLD, 10);
        mcbold = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/minecraft_bold.otf"));
        mcbold = mcbold.deriveFont(Font.PLAIN, 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            if (slot.item != null) {
                try {
                    InfoGUI infoGUI = new InfoGUI(slot);
                } catch (FontFormatException ex) {
                    
                } catch (IOException ex) {
                    
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/
import javax.swing.*;
import java.awt.*;
import javax.swing.border.MatteBorder;

public class DebugGUITemplate extends JFrame{

    public Font font;
    public JPanel main;
    public JPanel grid, tile;
    public JPanel[][] tiles;
    
    public JLabel playerIcon, treasureIcon, entityIcon, soulFlamesIcon, lootIcon, previousIcon;
    
    public DebugGUITemplate(Dungeon dungeon) {
        //set window
        super("Decked Out - Debug");
        this.setTitle("Decked Out - Debug");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout (10, 10));
        
        //initialize
        main = new JPanel();
        grid = new JPanel();
        
        playerIcon = new JLabel("You");
        treasureIcon = new JLabel("T");
        entityIcon = new JLabel("R");
        
        tiles = new JPanel[dungeon.getMaxWidth()][dungeon.getMaxHeight()];
        
        GridLayout dungeonGrid = new GridLayout(dungeon.getMaxWidth(), dungeon.getMaxHeight());
        grid.setLayout(dungeonGrid);
        playerIcon.setForeground(Color.black);
        
        int up = 0, left = 0, down = 0, right = 0;
        
        for (int y = dungeon.getMaxHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < dungeon.getMaxWidth(); x++) {
                tile = new JPanel();
                tiles[x][y] = tile;
                
                tile.setBackground(dungeon.getDungeonTiles()[x][y].biome.color);
                treasureIcon.setForeground(Color.black);
                
                if (dungeon.getDungeonTiles()[x][y].isUpSideClosed) up = 2;
                if (dungeon.getDungeonTiles()[x][y].isLeftSideClosed) left = 2;
                if (dungeon.getDungeonTiles()[x][y].isDownSideClosed) down = 2;
                if (dungeon.getDungeonTiles()[x][y].isRightSideClosed) right = 2;
                
                if (dungeon.getDungeonTiles()[x][y].isTreasure) tile.add(treasureIcon);
                if (dungeon.getDungeonTiles()[x][y].hasSoulFlames) {
                    soulFlamesIcon = new JLabel("S");
                    soulFlamesIcon.setForeground(Color.black);
                    tile.add(soulFlamesIcon);
                }
                if (dungeon.getDungeonTiles()[x][y].hasLoot) {
                    lootIcon = new JLabel("L");
                    lootIcon.setForeground(Color.black);
                    tile.add(lootIcon);
                }
                
                tile.setBorder(new MatteBorder(up, left, down, right, Color.black));
                grid.add(tile);
                
                up = 0;
                left = 0;
                down = 0;
                right = 0;
            }
        }
        
        this.add(grid);
        
        this.setVisible(true);
    }
    
    public void updateDebug(Dungeon dungeon, Player player) {
        tiles[player.posX][player.posY].add(playerIcon);
        if (dungeon.getDungeonTiles()[player.posX][player.posY].hasSoulFlames) {}
    }
}
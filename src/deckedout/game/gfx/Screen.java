package deckedout.game.gfx;

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public class Screen {

    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
    
    public int[] pixels;
    public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
    //public int[] colors = new int[MAP_WIDTH * MAP_WIDTH * 4];
    
    public int xOffset = 0;
    public int yOffset = 0;
    
    public int width;
    public int height;
    
    public SpriteSheet sheet;
    
    public Screen(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.sheet = sheet;
        
        pixels = new int[width * height];
    }
    
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void render(int xPos, int yPos, int tile, int color) {
        render(xPos, yPos, tile, color, false, false);
    }
    
    public void render(int xPos, int yPos, int tile, int color, boolean mirrorX, boolean mirrorY) {
        xPos -= xOffset;
        yPos -= yOffset;
        
        int xTile = tile % 16;
        int yTile = tile / 16; 
        
        int tileOffset = (xTile << 4) + (yTile << 4) * sheet.width;
        
        for (int y = 0; y < 16; y++) {
            
            int ySheet = y;
            if (mirrorY) ySheet = 15 - y;
            if (y + yPos < 0 || y + yPos >= height) continue;
            
            for (int x = 0; x < 16; x++) {
                
                int xSheet = x;
                if (mirrorX) xSheet = 15 - x;
                if (x + xPos < 0 || x + xPos >= width) continue;
                int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 16)) & 255;
                
                if (col < 255) pixels[(x + xPos) + (y + yPos) * width] = col;
            }
        }
    }
    
    
}

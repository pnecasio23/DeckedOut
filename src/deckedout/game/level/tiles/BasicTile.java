package deckedout.game.level.tiles;

import deckedout.game.gfx.Screen;
import deckedout.game.level.Level;

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public class BasicTile extends Tile {

    protected int tileId;
    protected int tileColor;
    
    public BasicTile(int id, int x, int y, int color) {
        super(id, false, false);
        this.tileId = x + y;
        this.tileColor = tileColor;
    }
    
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, tileId, tileColor);
    }
}

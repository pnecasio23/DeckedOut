package deckedout.game.gfx;

/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020



public class Font {

    private static String chars = "" + "AB                " + "                ";
    
    public static void render(String msg, Screen screen, int x, int y, int color) {
        for (int i = 0; i < msg.length(); i++){
            int charIndex = chars.indexOf(msg.charAt(i));
            if (charIndex >= 0) screen.render(x + (i*16), y, charIndex + 14 * 16, color);
        }
    }
    
}
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckedout.game.main;

import deckedout.game.gfx.Colors;
import deckedout.game.gfx.Screen;
import deckedout.game.gfx.SpriteSheet;
import deckedout.game.level.Level;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Phejie
 */
public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 180;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Decked Out";
    
    private JFrame frame;
    
    public boolean running = false;
    public int tickCount = 0;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int[] colors = new int[6*6*6];
    
    private Screen screen;
    public InputHandler input;
    public Level level;
    
    
    public Main() {
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        
        frame = new JFrame(NAME);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void init() {
        int index = 0;
        for(int r = 0; r < 6; r++) {
            for(int g = 0; g < 6; g++) {
                for(int b = 0; b < 6; b++) {
                    int rr = (r * 255 / 5);
                    int gg = (g * 255 / 5);
                    int bb = (b * 255 / 5);
                    
                    colors[index++] = rr << 16 | gg << 8 | bb;
                }
            }
        }
        
        screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
        input = new InputHandler(this);
        level = new Level(64, 64);
    }
    private synchronized void start() {
        running = true;
        new Thread(this).start();
    }
    
    private synchronized void stop() {
        running = false;
    }
    
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        
        int ticks = 0;
        int frames = 0;
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        init();
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            
            if (shouldRender) {
                frames++;
                render();
            }
            
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " tps, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
            
            
        }
    }
    
    private int x = 0, y = 0;
    
    public void tick() {
        tickCount++;
        
        if (input.up.isPressed()) {y--;}
        if (input.down.isPressed()) {y++;}
        if (input.left.isPressed()) {x--;}
        if (input.right.isPressed()) {x++;}
        
        level.tick();
    }
    
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        int xOffset = x - (screen.width / 2);
        int yOffset = y - (screen.height / 2);
        
        level.renderTiles(screen, xOffset, yOffset);
 
        for (int x = 0; x < level.width; x++) {
            int color = Colors.get(-1, -1, -1, 000);
            if (x % 10 == 0 && x != 0) {
                    color = Colors.get(-1, -1, -1, 500);
            }
        }
        
        for(int y = 0; y < screen.height; y++) {
            for(int x = 0; x < screen.width; x++) {
                int colorCode = screen.pixels[x+y*screen.width];
                
                if(colorCode < 255) pixels[x + y * WIDTH] = colors[colorCode];
            }
        }
        Graphics g = bs.getDrawGraphics();
        
        g.drawRect(0, 0, getWidth(), getHeight());
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
        new Main().start();
    }
    
}

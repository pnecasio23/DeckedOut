/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public abstract class Entity {

    public String name;
    public String behavior;
    public double HP;
    public double maxHP;
    public double AP;
    public double DP;
    public int posX;
    public int posY;
    public boolean isSpawned = false;
    
    protected Entity(String n, String b, double h, double m, double a, double d) {
        this.name = n;
        this.behavior = b;
        this.HP = h;
        this.maxHP = m;
        this.AP = a;
        this.DP = d;
    }
    
    public abstract void entitySpawn(Dungeon dungeon, Player player);
    public abstract void entityAI(Dungeon dungeon, Player player);
    public abstract void entityMove(Dungeon dungeon, Player player, int t, boolean isRandom);
    
}

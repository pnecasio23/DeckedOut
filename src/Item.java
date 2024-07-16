/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public abstract class Item {

    public int id;
    public String itemName;
    public String fileName;
    
    public Item(int i, String n) {
        this.id = i;
        this.itemName = n;
        this.fileName = n.replaceAll(" ", "_").toLowerCase();
    }
}

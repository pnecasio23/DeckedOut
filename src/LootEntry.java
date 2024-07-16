/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public class LootEntry {

    public MiscItem entry;
    public int weight, minCount, maxCount;
    
    public LootEntry(MiscItem i, int w, int min, int max) {
        this.entry = i;
        this.weight = w;
        this.minCount = min;
        this.maxCount = max;
    }
}

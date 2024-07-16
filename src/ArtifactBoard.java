/* Name: Necasio, Paul Timothy R.

Section: Electron

Date Started: //2020

Date Finished: //2020

*/

public class ArtifactBoard {

    public int noOfArtifacts = 0;
    public Artifact[] artifactsBoard;
    
    public ArtifactBoard() {
        artifactsBoard = new Artifact[15];
    }
    
    public void checkArtifactsBoard(Player player) {
        for (int i = 0; i <= artifactsBoard.length - 1; i++) {
            if (artifactsBoard[i] != null) System.out.println((i+1) + ": " + artifactsBoard[i].itemName);
        }
    }
}

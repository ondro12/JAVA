package projekt.board;
import projekt.treasure.Treasure;

/**
 * Trieda sluziaca na pracu s polickom hracej dosky.
 * Vytvori policko a pracuje s nim
 * @author Marek Mrkva a Matus Ondris
 */

public class MazeField {
    public int row;
    public int col;
    public MazeCard card;
    public Treasure tre;

    public MazeField(int r, int c) {
        this.row = r;
        this.col = c;
        this.card = null;
        this.tre = null;
    }
    
    public int row(){
        return this.row;
    }
    public int col(){   
        return this.col;
    }
    
     /**
     * Metoda getCard .
     * Tato metoda sluzi na vratenie karty nachadzajucej sa na aktuialnom policku
     * 
     */
    
    public MazeCard getCard(){
        
        if(this.card != null)
            return this.card;
        else
            return null;
    }
    
     /**
     * Metoda getCard .
     * Tato metoda sluzi na vlozenie karty(kamena) na dane policko
     ** @param crd reprezentuje kamen ktory s ma vlozit
     */
    
    public void putCard(MazeCard crd){
        
        this.card = crd;        
    } 
    
     /**
     * Metoda getTreasure .
     * Tato metoda sluzi na vratenie pokladu nachadzajucej sa na aktuialnom policku
     */
    
    public Treasure getTreasure(){
        
        if (tre != null)
            return tre;
        return null;
    }
    
     /**
     * Metoda putTreasure .
     * Tato metoda sluzi na vlozenie pokladu na dane policko
     ** @param tres reprezentuje poklad ktory s ma vlozit
     */
    
    public void putTreasure(Treasure tres){
        tre = tres;
    }
}
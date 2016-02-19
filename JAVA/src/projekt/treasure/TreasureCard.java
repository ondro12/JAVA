package projekt.treasure;

/**
 * Trieda reprezentujuca kartu(poklad). 
 * Trieda sluzi pracu s pokladom
 * @author Marek Mrkva a Matus Ondris
 */

public class TreasureCard  {
    
    public Treasure tr;
    
    public TreasureCard(Treasure tr){
        this.tr = tr;          
    }
    
    @Override   
    public boolean equals(Object obj){
        
        boolean isEqual = false;
        if (obj == null || !(obj instanceof TreasureCard)){
            isEqual = false;
        } 
        else if (obj == this) {
            isEqual = true;
        } 
        else {
            TreasureCard other = (TreasureCard) obj;
            isEqual = (other.tr.equals(tr));
        }
        return isEqual;
    }
}
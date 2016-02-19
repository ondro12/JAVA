package projekt.board;
import java.util.ArrayList;
import projekt.treasure.CardPack;
import projekt.treasure.Treasure;
import projekt.treasure.TreasureCard;

/**
 * Trieda sluziaca na pracu s hracou doskou.
 * Vytvori hraciu plochu a naplni ju
 * @author Marek Mrkva a Matus Ondris
 */

public class MazeBoard {
    public int n;
    public  int size;
    public  ArrayList<MazeField> board;
    public MazeField freecard;
    public CardPack crdpack;
    
    public MazeBoard (){
        size = 0;
        n = 0;
        board = new ArrayList<MazeField>();
        freecard = new MazeField(0,0);
        crdpack = null;
    }
    
     /**
     * Metoda createMazeBoard .
     * Tato metoda vytvory hrciu dosku a kamene avsak nepriraduje poklady.
     * 
     * @param n urcuje pocet riadkov a stlpcov.
     */
    
    public static MazeBoard createMazeBoard(int n){
        MazeBoard boardd;
        boardd = new MazeBoard();
        boardd.n = n;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                MazeField nova;
                nova = new MazeField(i,j);
                boardd.board.add(boardd.size,nova);
                boardd.size++;
            }
        }

        return boardd;
    }
    
     /**
     * Metoda newGame .
     * Tato metoda naplni vytvorenu dosku pokladmy.
     * 
     * @param tresr reprezentuje poklad.
     */
    
public void newGame(int tresr){
    
        Treasure.createSet(tresr);
        crdpack = new CardPack(tresr,tresr);
        crdpack.shuffle();
        TreasureCard tc;
        for(int j = 1; j <= tresr;j++){
            tc = crdpack.popCard();
            int rndRow = (int)(Math.random()*n);
            int rndCol = (int)(Math.random()*n);
            boolean notfound = true;
            while(notfound){
                MazeField hlp;
                hlp = board.get(((rndRow)*n)+rndCol);
                if(hlp.tre == null){
                    notfound = false;
                    hlp.tre = tc.tr;
                    board.set(((rndRow)*n)+rndCol,hlp);
                }
                rndRow = (int)(Math.random()*n);
                rndCol = (int)(Math.random()*n);
            }
            
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                
                MazeField newfld;
                MazeCard newc;
                int randomNum = 1 + (int)(Math.random()*5);
                int num;
                if((i == 0  && j == 0)||( i+1 == n && j+1 == n ) ||( i+1 == n && j == 0)||( j+1 == n && i == 0)){
                    newc = MazeCard.create("C");
                        if(i == 0 && j == 0){
                            newc.turnRight();
                            newc.turnRight();
                        }
                        if(i == 0 && j == n-1){
                            newc.turnRight();
                            newc.turnRight();
                            newc.turnRight();
                        }
                        if(i == n-1 && j == 0){
                            newc.turnRight();
                        }
                }
		else if(i%2 == 0 && j%2 == 0){
			newc = MazeCard.create("F");
                        if(i == 0){
                            newc.turnRight();
                            newc.turnRight();
                        }
                        else if (j==0){
                            newc.turnRight();
                        }
                        else if (j == n-1){
                            newc.turnRight();
                            newc.turnRight();
                            newc.turnRight();
                        }
                        else if(n-1 != i)
                            for(int x = 1; x < randomNum;x++)
                                newc.turnRight();
                }
		else	
			
			if ((randomNum == 1) || (randomNum == 2)){
				newc = MazeCard.create("L");
                                num = 0 + (int)(Math.random()*3);
                                for(int x = 0;x < num;x++){
                                    newc.turnRight();
                                }
                        }
			else if((randomNum == 3) || (randomNum == 4)){
				newc = MazeCard.create("C");
                                num = 0 + (int)(Math.random()*3);
                                for(int x = 0;x < num;x++){
                                    newc.turnRight();
                                }
                        }
                        else{
				newc = MazeCard.create("F");
                                num = 0 + (int)(Math.random()*3);
                                for(int x = 0;x < num;x++){
                                    newc.turnRight();
                                }
                        }                          
                newfld = board.get(i*n+j);
                newfld.card = newc;
                board.set((i*n+j),newfld);                               
            }
        }
    
        int num = 1 + (int)(Math.random()*5);
        MazeCard newc;
        if(num == 1 || num == 2)
                    newc = MazeCard.create("C");
                else if(num == 3 || num == 4)
                    newc = MazeCard.create("L");
                else
                    newc = MazeCard.create("F");
        freecard.putCard(newc);
}

     /**
     * Metoda get .
     * Tato metoda vrati gladane policko, ak sa hlada na hracej doske ak nie vrati null.
     * 
     * @param r reprezentuje riadok.
     * @param c reprezentuje stlpec.
     */
    
    public MazeField get(int r, int c){
        
        if((r<1 || c<1)||(r>n || c>n)){
            return null;
        }
        else 
            return board.get(((r-1)*n+c)-1);
    }
    
     /**
     * Metoda getFreeCard .
     * Tato metoda vrati policko freecard
     */
    
    
    public MazeCard getFreeCard(){
        return freecard.card;
    }
    
     /**
     * Metoda shift .
     * Tato metoda sluzi na posunutie kamenov v rmci stlpva alebo riadku podla pozicie
     * vkladaneho kamena kamen aj s pokladom , ktory vypadne sa stava volnym kamenom
     * 
     * @param mf reprezentuje kamen , ktory ma byt vlozeny.
     */
    
    public void shift(MazeField mf){
        MazeField hlp;
        MazeCard acard;
        Treasure atres;
        Treasure ntres;
        MazeCard ncard;
        if((mf.row == 1) && (mf.col % 2 == 0)) {
                
                hlp = board.get(((mf.row-1)*n)+mf.col-1);
                ncard = hlp.card;
                ntres = hlp.tre;
                hlp.card = freecard.card;
                hlp.tre = freecard.tre;
                
                board.set((((mf.row-1)*n)+mf.col-1),hlp);
                hlp = board.get(((n-1)*n)+mf.col-1);
                freecard.card = hlp.card;
                freecard.tre = hlp.tre;
                
                for(int i = 2; i <= n; i++){
                    hlp = board.get(((i-1)*n)+mf.col-1);
                    acard = ncard;
                    atres = ntres;
                    ncard = hlp.card;
                    ntres = hlp.tre;
                    hlp.card = acard;
                    hlp.tre = atres;
                    board.set(((i-1)*n)+mf.col-1,hlp);                   
                }   
        }
        else if((mf.row == n) && (mf.col % 2==0)) {
            
                hlp = board.get(((mf.row-1)*n)+mf.col-1);
                ncard = hlp.card;
                ntres = hlp.tre;
                hlp.card = freecard.card;
                hlp.tre = freecard.tre;
                board.set(((mf.row-1)*n)+mf.col-1,hlp);
                hlp = board.get(0+mf.col-1);
                freecard.card = hlp.card;
                freecard.tre = hlp.tre;
                

                for(int i = n-1; i >= 1; i--){
                    hlp = board.get(((i-1)*n)+mf.col-1);
                    acard = ncard;
                    atres = ntres;
                    ncard = hlp.card;
                    ntres = hlp.tre;
                    hlp.card = acard;
                    hlp.tre = atres;
                    board.set(((i-1)*n)+mf.col-1,hlp);                    
                } 
        }
        else if((mf.col == 1) && (mf.row % 2 == 0)) {
            
                hlp = board.get(((mf.row-1)*n)+mf.col-1);
                ncard = hlp.card;
                ntres = hlp.tre;
                hlp.card = freecard.card;
                hlp.tre = freecard.tre;
                board.set(((mf.row-1)*n)+mf.col-1,hlp);
                hlp = board.get(((mf.row-1)*n)+n-1);
                freecard.card = hlp.card;
                freecard.tre = hlp.tre;
                        
                for(int i = 2; i <= n; i++){
                    hlp = board.get(((mf.row-1)*n)+i-1);
                    acard = ncard;
                    atres = ntres;
                    ncard = hlp.card;
                    ntres = hlp.tre;                   
                    hlp.card = acard;
                    hlp.tre = atres;
                    board.set(((mf.row-1)*n)+i-1,hlp);                   
                }
        }
        else if((mf.col == n) && (mf.row % 2 == 0)) {
            
                hlp = board.get(((mf.row-1)*n)+n-1);
                ncard = hlp.card;
                ntres = hlp.tre;
                hlp.card = freecard.card;
                hlp.tre = freecard.tre;
                board.set(((mf.row-1)*n)+n-1,hlp);
                hlp = board.get((mf.row-1)*n);
                freecard.card = hlp.card;
                freecard.tre = hlp.tre;
                
                for(int i = n-1; i >= 1; i--){
                    hlp = board.get(((mf.row-1)*n)+i-1);
                    acard = ncard;
                    atres = ntres;
                    ncard = hlp.card;
                    ntres = hlp.tre;
                    hlp.card = acard;
                    hlp.tre = atres;
                    board.set(((mf.row-1)*n)+i-1,hlp);                 
                }
        }
    }
}

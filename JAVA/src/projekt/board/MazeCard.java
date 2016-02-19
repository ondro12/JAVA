package projekt.board;


/**
 * Trieda sluziaca na pracu s kartou policka.
 * Vytvori kartu a pracuje s nou
 * @author Marek Mrkva a Matus Ondris
 */

public class MazeCard {
    public int typ;
    public int turned;
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    
    public MazeCard (){
        typ = 0;
        turned = 0;
        left = false;
        right = false;
        up = false;
        down = false;  
    }
    
     /**
     * Metoda CANGO .
     * Tato metoda sluzi na zistenie ktorym smerom je mozny pohyb po doske
     */
    
    public static enum CANGO{
        LEFT, RIGHT, UP, DOWN     
    }
    
     /**
     * Metoda create .
     * Tato metoda sluzi na vytvorenie karty daneho typu
     * 
     * @param type reprezentuje typ kamena C L F
     */
    
    public static MazeCard create(String type){
        MazeCard novy = new MazeCard();
        
        if ("C".equals(type)){
            
            novy.left = true;
            novy.right = false;
            novy.up = true;        
            novy.down = false;  
            novy.turned = 0;
            novy.typ = 1;
            return novy;
        }
        else if ("L".equals(type)){
            
                novy.typ = 2;
                novy.left = true;
                novy.right = true;
                novy.up = false;        
                novy.down = false; 
                novy.turned = 0;
                return novy;
        }
        else if ("F".equals(type)){
            
                novy.typ = 3;
                novy.left = true;
                novy.right = true;
                novy.up = true;        
                novy.down = false; 
                novy.turned = 0;
                return novy;
        }
        else throw new IllegalArgumentException("Can not create stone");
    }
    
     /**
     * Metoda canGo .
     * Tato metoda sluzi zistenie , ktorym smerom je mozny pohyb po danej karte
     * 
     * @param dir reprezentuje otazku nasmer,kadial je mozny pohyb
     */
    
    public boolean canGo(MazeCard.CANGO dir){
        
        if (((this.turned == 0 ) || (this.turned == 2 )) && (this.typ == 2) && (dir == CANGO.LEFT || dir == CANGO.RIGHT )){
          return true;  
        }
        else if (((this.turned == 1 ) || (this.turned == 3 ))&& (this.typ == 2) && (dir == CANGO.UP || dir == CANGO.DOWN )){
          return true;   
        }
        else if ((this.turned == 0)&& (this.typ == 1) && (dir == CANGO.LEFT || dir == CANGO.UP )){
          return true;  
        }
        else  if ((this.turned == 1)&& (this.typ == 1) && (dir == CANGO.UP || dir == CANGO.RIGHT )){
            return true;
        }
        else if ((this.turned == 2)&& (this.typ == 1) && (dir == CANGO.DOWN || dir == CANGO.RIGHT )){
          return true;  
        }
        else if ((this.turned == 3)&& (this.typ == 1) && (dir == CANGO.LEFT || dir == CANGO.DOWN )){
          return true;  
        }        
        else if ((this.turned == 0)&& (this.typ == 3) && (dir == CANGO.LEFT || dir == CANGO.RIGHT || dir == CANGO.UP)){
            return true;
        }
        else if ((this.turned == 1)&& (this.typ == 3) && (dir == CANGO.UP || dir == CANGO.RIGHT || dir == CANGO.DOWN)){
            return true;
        }
        else if ((this.turned == 2)&& (this.typ == 3) && (dir == CANGO.LEFT || dir == CANGO.RIGHT || dir == CANGO.DOWN)){
            return true;
        }
        else if ((this.turned == 3)&& (this.typ == 3) && (dir == CANGO.UP || dir == CANGO.LEFT || dir == CANGO.DOWN)){
            return true;
        }               
        else {
            return false;            
        } 
    }
    
     /**
     * Metoda turnRight .
     * Tato metoda sluzi na otocenie karty do prava
     * 
     */
    
    public void turnRight(){
        if(0 != typ)switch (typ){
            case 2:
                if((this.turned == 0)||(this.turned == 2)){
                    this.turned = 1;
                    this.left = false;
                    this.right = false;
                    this.up = true;        
                    this.down = true;
                break;
                }
                else if((this.turned == 1)||(this.turned == 3)){
                    this.turned = 0;
                    this.left = up;
                    this.right = up;
                    this.up = false;        
                    this.down = false;
                break;
                }
            case 1:
                if(this.turned == 0){
                    this.turned = 1;
                    this.left = false;
                    this.right = true;
                    this.up = true;        
                    this.down = false; 
                    break;
                }
                else if(this.turned == 1){
                    this.turned = 2;
                    this.left = false;
                    this.right = true;
                    this.up = false;        
                    this.down = true; 
                    break;
                }
                else if(this.turned == 2){
                    this.turned = 3;
                    this.left = true;
                    this.right = false;
                    this.up = false;        
                    this.down = true; 
                    break;
                }
                else if(this.turned == 3){
                    this.turned = 0;
                    this.left = true;
                    this.right = false;
                    this.up = true;        
                    this.down = false; 
                    break;
                }
            case 3:
                if(this.turned == 0){
                    this.turned = 1;
                    this.left = false;
                    this.right = true;
                    this.up = true;        
                    this.down = true; 
                    break; 
                }
                else if(this.turned == 1){
                    this.turned = 2;
                    this.left = true;
                    this.right = true;
                    this.up = false;        
                    this.down = true; 
                break; 
                }
                else if(this.turned == 2){
                    this.turned = 3;
                    this.left = true;
                    this.right = false;
                    this.up = true;        
                    this.down = true; 
                break; 
                }
                else if(this.turned == 3){
                    this.turned = 0;
                    this.left = true;
                    this.right = true;
                    this.up = true;        
                    this.down = false; 
                break; 
                }
        }
    }        
}
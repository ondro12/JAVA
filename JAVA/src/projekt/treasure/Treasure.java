package projekt.treasure;

/**
 * Trieda reprezentujuca poklad. 
 * Trieda sluzi pracu s pokladmi
 * @author Marek Mrkva a Matus Ondris
 */

public class Treasure {
    
    static int size;
    public int id;
    public static Treasure[] objSet;
   
    public Treasure(int code){             
        this.id = code;    
    }

    public static void createSet(int size){
        objSet = new Treasure[size];
        
        for(int i = 0; i < size; i++){
            objSet[i] = new Treasure(i);              
        }
    }    


    public static Treasure getTreasure(int code){

        for(int i = 0; i <= objSet.length; i++){
           if(code == objSet[i].id)
               return objSet[i];   
        }
        return null;        
    }    
}
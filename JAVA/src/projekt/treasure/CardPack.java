package projekt.treasure;

//autor Matus Ondris xondri04

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentujuca balicek kariet. 
 * Trieda sluzi pracu s balickom kariet(pokladov) ktore maju hraci hladat
 * @author Marek Mrkva a Matus Ondris
 */

public class CardPack {

    protected int maxSize;
    protected int initSize;
    protected int Size;
    public List<TreasureCard> Pack = new ArrayList<>(maxSize);
    
    public CardPack(int maxSize,int initSize){
        this.maxSize = maxSize;
        this.initSize = initSize;
        this.Size = initSize;
        if(initSize > maxSize)
            return;
        for(int i = 0; i < initSize ;i++){
            TreasureCard tc = new TreasureCard(Treasure.getTreasure(initSize -i -1));
            Pack.add(tc);
        }
    }
    
    public CardPack(int Size,List<TreasureCard> Pack){
        this.Size = Size;
        this.Pack = Pack;
    }
    
    public void deleteCard(Treasure tr){
        TreasureCard t = new TreasureCard(Treasure.getTreasure(tr.id));
        int index = 0;
        for(int i = 0;i < Pack.size();i++){
            TreasureCard tc = Pack.get(i);
            if(tc.tr.id == t.tr.id){
                index = i;
                break;
            }
        }
        Pack.remove(index);
        Size--;
    }
    
    public TreasureCard popCard(){
        if(Size == 0)
            return null;
        Size--;
        return Pack.get(Size);
    }
    public int size(){
       return Size; 
    }
    
    public void shuffle(){
        List<TreasureCard> Shuffle = new ArrayList<>(Size);
        while(!Pack.isEmpty()) {
            int Index=(int)(Math.random()*Pack.size());
            TreasureCard tc = Pack.get(Index);
            Shuffle.add(tc);
            Pack.remove(Index);   
        }
        Pack=Shuffle;
    }  
}


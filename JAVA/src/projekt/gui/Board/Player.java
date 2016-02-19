package projekt.gui.Board;

import projekt.board.MazeBoard;
import projekt.treasure.*;
import javax.swing.*;
import java.awt.*;

/**
 * Trieda sluziaca na prcu s hrcmi. 
 * Trieda sluzi na vytvorenie hracov nacitanie ich obrazkov a 
 * vlozenie ich figuriek an spravne pozicie(na rohy hracej dosky)
 * @author Marek Mrkva a Matus Ondris
 */

public class Player {
    public int posR;
    public int posC;
    private final Image P;
    public int ident;
    public static Player[] plyr;
    protected static int rate;
    public int score;
    public static CardPack crdP;
    public Treasure find;
    public static Player ac;
    private final String way = "./lib/images/";
    
    public Player(int identif, int n){
        
        ImageIcon image;
        this.ident = identif;
        
        if (ident == 1){
            image = new ImageIcon(way+"P.gif");
            posR = n-1;
            posC = n-1;
            
        }
        else if (ident == 2){
            image = new ImageIcon(way+"P2.png");
            posR = 0;
            posC = 0;
        }
        else if (ident == 3){
            image = new ImageIcon(way+"4.png");
            posR = n-1;
            posC = 0;
        }
        else{
            image = new ImageIcon(way+"3.png");
            posR = 0;
            posC = n-1;
        }
        P = image.getImage();
    }
    public static void createPlayer(int value, int x,int tr,MazeBoard mb){
        Player.rate = value;
        plyr = new Player[rate];
        crdP = new CardPack(tr,tr);
        crdP.shuffle();
        
        for (int i = 1;i <=rate;++i){
            plyr[i-1] = new Player(i,x);
            plyr[i-1].find = crdP.popCard().tr;
            plyr[i-1].score = 0;            
        }
        ac = plyr[0];        
    }
    public static void LoadPlayer(int val, int x,int tr,java.util.List<TreasureCard> p,MazeBoard mb,int[] score,int[] f,int[] fx,int[] fy,int a){
        
        Player.rate = val;
        plyr = new Player[val];
        crdP = new CardPack(tr,p);        
        crdP.shuffle();
        for (int i = 1;i <=val;++i){
            Treasure t = Treasure.getTreasure(f[i-1]);
            plyr[i-1] = new Player(i,x);
            plyr[i-1].find = t;
            crdP.deleteCard(t);
            plyr[i-1].find = t;
            plyr[i-1].posR = fx[i-1];
            plyr[i-1].posC = fy[i-1];
            plyr[i-1].score = score[i-1];
            
        }
        ac = plyr[a-1];
    }
    public static Image getPlayer(int id){
        return plyr[id-1].P;
    }
    public static Player getP(int id){
        return plyr[id-1];
    }
    public int getR(){
        return posR;
    }
    public int getC(){
        return posC;
    }
    public void move(int dx, int dy){
        
        posR += dx;
        posC += dy;
    }
    public void AddTr(){
        score++;
    }
    public void NextT(int id){
        
        if(crdP.size() == 0)
            System.err.println("Wrong state");
        
        plyr[id-1].find = crdP.popCard().tr;
    }
    
    public static void setAC(Player a){
        ac = a;
    }
    
    public static int getAC(){
        return ac.ident;
    }
}

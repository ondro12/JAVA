package projekt.gui.Board;

import projekt.board.MazeBoard;
import javax.swing.*;
import java.awt.*;

/**
 * Trieda sluziaca na spracovanie obrazkov policok. 
 * Trieda sluzi na spracovanie policka,jeho rotacie a vykreslenie
 * @author Marek Mrkva a Matus Ondris
 */

public class Map {
    
    private final Image I1,I2,C1,C2,C3,C4,F1,F2,F3,F4;
    private final String way = "./lib/images/";
    
     /**
     * Metoda zaloguj vypis vysledku predoslej operacie.
     * Sluzi na pracu s obrazkami hracich policok roznych typov a ich 
     * roznych otoceni , sluzi na zjednoduhsenie prace s obrazkami
     * nacitanie a zjednoduhsenie vracanie vsetkych kombinacii policok
     * @param n udava velkost hrcej plochy.
     * * @param mb reprezentuje hraciu plochu , z ktorou sa pracuje.
     */
    
    public Map(int n,MazeBoard mb){

        ImageIcon image = new ImageIcon(way+"C.jpg");
        C1 = image.getImage();
        image = new ImageIcon(way+"C2.jpg");
        C2 = image.getImage();
        image = new ImageIcon(way+"C3.jpg");
        C3 = image.getImage();
        image = new ImageIcon(way+"C4.jpg");
        C4 = image.getImage();
        image = new ImageIcon(way+"F.jpg");
        F1 = image.getImage();
        image = new ImageIcon(way+"F2.jpg");
        F2 = image.getImage();
        image = new ImageIcon(way+"F3.jpg");
        F3 = image.getImage();
        image = new ImageIcon(way+"F4.jpg");
        F4 = image.getImage();
        image = new ImageIcon(way+"I2.jpg");
        I1 = image.getImage();
        image = new ImageIcon(way+"I.jpg");
        I2 = image.getImage();        
    }    
    public Image getC(){
            return C1;
    }
    public Image getC2(){
            return C2;
    }
    public Image getC3(){
            return C3;
    }
    public Image getC4(){
            return C4;
    }
    public Image getF(){
            return F1;
    }
    public Image getF2(){
            return F2;
    }
    public Image getF3(){
            return F3;
    }
    public Image getF4(){
            return F4;
    }
    public Image getI(){
            return I1;
    }
    public Image getI2(){
            return I2;
    }
}

package projekt.gui.Board;

import java.awt.*;
import javax.swing.*;
import projekt.treasure.Treasure;

/**
 * Trieda sluziaca na vykreslenie obrazkov pokladov. 
 * Trieda sluzi na spracovanie pokladu , nacitanie obrazku a vykreslenie
 * @author Marek Mrkva a Matus Ondris
 */

public class ImgTreasure {
    
    static Image I;
    private static final String way = "./lib/images//";
    
     /**
     * Metoda getT .
     * Tato metoda zisti o aky poklad sa jedna a vrati obrazok ktory danemu pokladu prislusi.
     * 
     * @param treas poklad,ktoreho obrazok je potrebne nacitat a vratit.
     */
    
    public static Image getT(Treasure treas){
        ImageIcon image;
        if (treas.id == 1)
            image = new ImageIcon(way+"T1.png");
        else if (treas.id == 2)
            image = new ImageIcon(way+"2.png");
        else if (treas.id == 3)
            image = new ImageIcon(way+"T3.png");
        else if (treas.id == 4)
            image = new ImageIcon(way+"T4.png");
        else if (treas.id == 5)
            image = new ImageIcon(way+"T5.png");
        else if (treas.id == 6)
            image = new ImageIcon(way+"T.png");
        else if (treas.id == 7)
            image = new ImageIcon(way+"T7.png");
        else if (treas.id == 8)
            image = new ImageIcon(way+"T8.png");
        else if (treas.id == 9)
            image = new ImageIcon(way+"T9.png");
        else if (treas.id == 10)
            image = new ImageIcon(way+"T10.png");
        else if (treas.id == 11)
            image = new ImageIcon(way+"T11.png");
        else if (treas.id == 12)
            image = new ImageIcon(way+"T12.png");
        else if (treas.id == 13)
            image = new ImageIcon(way+"T13.png");
        else if (treas.id == 14)
            image = new ImageIcon(way+"T14.png");
        else if (treas.id == 15)
            image = new ImageIcon(way+"T15.png");
        else if (treas.id == 16)
            image = new ImageIcon(way+"T16.png");
        else if (treas.id == 17)
            image = new ImageIcon(way+"T17.png");
        else if (treas.id == 18)
            image = new ImageIcon(way+"T18.png");
        else if (treas.id == 19)
            image = new ImageIcon(way+"T19.png");
        else if (treas.id == 20)
            image = new ImageIcon(way+"T20.png");
        else if (treas.id == 21)
            image = new ImageIcon(way+"T21.png");
        else if (treas.id == 22)
            image = new ImageIcon(way+"T22.png");
        else if (treas.id == 23)
            image = new ImageIcon(way+"T23.png");
        else
            image = new ImageIcon(way+"T24.png");
             
        I = image.getImage();
        return I;
    }
}

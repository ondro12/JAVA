/**
 * Hlavna trieda spustnie Menu.
 * Spustenie gui menu  celej aplikcie.
 * @author Marek Mrkva a Matus Ondris
 */

package projekt;

import projekt.gui.menu.Menu;

public class Projekt {

    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                Menu menu = new Menu();
                }
        });
    }
    
}
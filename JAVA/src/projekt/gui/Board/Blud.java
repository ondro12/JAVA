package projekt.gui.Board;

import projekt.gui.menu.SaveDialog;
import java.awt.event.ActionEvent;
import projekt.board.MazeBoard;
import javax.swing.*;
import java.awt.event.ActionListener;
import projekt.gui.menu.Menu;

/**
 * Trieda sluziaca na spracovanie parametrov na liste. 
 * Implementuje volanie akcii prislusnych danym pokynom
 * @author Marek Mrkva a Matus Ondris
 */

public class Blud extends JFrame{
    
    private final int n;
    private final int treas;
    private final int numPlay;
    private final MazeBoard mb;
    private final JMenu men;
    private final JMenu hlp;
    private final JMenuBar bar;

     /**
     * Metoda Blud implementuje nasledujuce prikazy.
     * Prikaz help vypise napovedu
     * Prikaz new game orvori gui menu a ponukne zahajenie novej hry
     * Prikaz save prepne na gui sluziace na ulozenie hry
     * Prikaz exit aplikaciu spravne ukonci.
     */
    
    public Blud(final int n, int treasure,int players,final MazeBoard mb,final JFrame fram){
        
        this.n = n;
        this.treas = treasure;
        this.numPlay = players;
        this.mb = mb;
  
        fram.setTitle("Labyrinth PC-board Game");
        
        Board brd = new Board(n,mb,numPlay,treasure);
        brd.setLayout(null);
        
        bar = new JMenuBar();
        men = new JMenu("Game");
        hlp = new JMenu("Help");
        JMenuItem hlpCall = new JMenuItem("Help");
        hlpCall.addActionListener(new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        System.out.println("TODO HELP");

                    }
                });
        
        hlp.add(hlpCall);
        JMenuItem ng = new JMenuItem("New Game");
        ng.addActionListener(new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        fram.dispose();
                        Menu men = new Menu();

                    }
                });
        
        JMenuItem ext = new JMenuItem("Exit");
        ext.addActionListener(new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        fram.dispose();
                        System.exit(0);

                    }
                });
        JMenuItem sv = new JMenuItem("Save");
        sv.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        JFrame save = new JFrame();
                        SaveDialog sa = new SaveDialog(save,mb,n,treas);
                        save.add(sa);
                        save.setVisible(true);
                        save.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        save.setLocationRelativeTo(null);
                        save.pack();
                    }
                });
        
        men.add(ng);
        men.add(sv);
        men.add(ext);
        bar.add(men);
        bar.add(hlp);
        fram.setJMenuBar(bar);
        fram.add(brd);
        fram.setSize(n*50+400,n*50+400);
        fram.setLocationRelativeTo(null);
        fram.setVisible(true);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
package projekt.gui.menu;

import projekt.gui.Board.Player;
import projekt.board.MazeBoard;
import javax.swing.*;
import projekt.gui.Board.Blud;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * Trieda sluziaca na vytvorenie menu. 
 * Trieda sluzi na vytvorenie menu , tlacitok spusta volane akcie
 * vyvolane danymi tlacitkami
 * @author Marek Mrkva a Matus Ondris
 */

public class Menu{
	public Menu () {
		final JFrame mfr = new JFrame("MENU");              
                final JPanel grid = new JPanel();
                grid.setBackground(Color.GRAY);
                JPanel fld1 = new JPanel();
                fld1.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));  
                JLabel msg = new JLabel("Players ",JLabel.CENTER);
                fld1.add(msg);
                final JSpinner pls = new JSpinner(new SpinnerNumberModel(2, 2, 4, 1));
                fld1.add(pls);               
                JPanel fld2 = new JPanel();
                fld2.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));                
                JLabel msg2 = new JLabel("Size",JLabel.CENTER);
                fld2.add(msg2);
                final JSpinner Size = new JSpinner(new SpinnerNumberModel(7, 5, 11, 2));
                fld2.add(Size);               
                JPanel fld3 = new JPanel();
                fld3.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));                
                JLabel msg3 = new JLabel("Treasures",JLabel.CENTER);
                fld3.add(msg3);
                final JSpinner ccnt = new JSpinner(new SpinnerNumberModel(12, 12, 24, 12));
                fld3.add(ccnt);                
                JPanel fld4 = new JPanel();
                fld4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));                
                final JButton quit = new JButton("Exit");
                final JButton begin = new JButton("Start");
                begin.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int size = (Integer) Size.getValue();
                        int crds = (Integer) ccnt.getValue();
                        int plrs = (Integer) pls.getValue();
                        MazeBoard brd;
                        brd = MazeBoard.createMazeBoard(size);                       
                        brd.newGame(crds);
                        Player.createPlayer(plrs, size,crds,brd);
                        mfr.remove(grid);
                        Blud blud = new Blud(size,crds,plrs,brd,mfr);
                    }
                });
                fld4.add(begin);
                JButton load = new JButton("Load");
                load.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        JFrame LoFr = new JFrame();
                        Load lo = new Load(LoFr,mfr,grid);
                        LoFr.add(lo);
                        LoFr.setVisible(true);
                        LoFr.setLocationRelativeTo(null);
                        LoFr.pack();
                        LoFr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    }
                });
                fld4.add(load);

                quit.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                });
                
                fld4.add(quit);
                grid.setLayout(new GridLayout(4,1));
                grid.add(fld1);
                grid.add(fld2);
                grid.add(fld3);
                grid.add(fld4);
                mfr.add(grid);
                mfr.setSize(300, 250);
		mfr.setLocationRelativeTo(null);
		mfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mfr.setVisible(true);
	}
}
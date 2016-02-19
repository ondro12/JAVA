package projekt.gui.Board.buttons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import projekt.board.MazeBoard;
import projekt.board.MazeCard;
import projekt.board.MazeField;
import projekt.gui.Board.Map;
import projekt.gui.Board.Player;

/**
 * Trieda sluziaca na spracovanie tlacitok. 
 * Trieda sluzi na spracovanie tlacitok a vykonanie prislusnych naleziacich akcii
 * ktore sa maju vyvolat pri stlceni danych tlacitok , ich vykreslenie a vlozenie na 
 * zadane pozicie
 * @author Marek Mrkva a Matus Ondris
 */

public class Butt {
    public MazeBoard mb;
    private final int k = 50;
    private final JPanel p;
    public boolean played = false;
    public int lastx;
    public int lasty;
    private final int n;
    private Player[] pla;
    ImageIcon img;
    Image F;
    private final String path = "./lib/images/";
    
    /**
     * Metoda Butt.
     * Metoda vytvory tlacidla a akcie prislusne ku nim.
     */
    
    public Butt(Graphics g,JPanel p,int n,final MazeBoard mb){
        this.mb = mb;
        this.p = p;
        this.n = n;
        Map m = new Map(n,mb);
        
        JButton h11 = new JButton();
        setB(h11,250,150);
        
        JButton h12 = new JButton();
        setB(h12,350,150);
        
        if (n >= 7){
            JButton h13 = new JButton();
            setB(h13,450,150);
            
            JButton h23 = new JButton();
            setB(h23,450,200+n*k);
            
            JButton v13 = new JButton();
            setB(v13,150,450);
            
            JButton v23 = new JButton();
            setB(v23,200+n*k,450);
            
            setP(h13,1,6);
            setP(h23,n,6);
            
            setP(v13,6,1);
            setP(v23,6,n);
        }
        
        if(n >= 9){
            JButton h14 = new JButton();
            setB(h14,550,150);
            
            JButton h24 = new JButton();
            setB(h24,550,200+n*k);
            
            JButton v14 = new JButton();
            setB(v14,150,550);
            
            JButton v24 = new JButton();
            setB(v24,200+n*k,550);
            
            setP(h14,1,8);
            setP(h24,n,8);
            
            setP(v14,8,1);
            setP(v24,8,n);
        }
        if(n >= 11){
            JButton h15 = new JButton();
            setB(h15,650,150);
            
            JButton h25 = new JButton();
            setB(h25,650,200+n*k);
            
            JButton v15 = new JButton();
            setB(v15,150,650);
            
            JButton v25 = new JButton();
            setB(v25,200+n*k,650);
            
            setP(h15,1,10);
            setP(h25,n,10);
            
            setP(v15,10,1);
            setP(v25,10,n);
        }
        JButton h21 = new JButton();
        setB(h21,250,200+n*k);
        
        JButton h22 = new JButton();
        setB(h22,350,200+n*k);
        
        JButton v11 = new JButton();
        setB(v11,150,250);
        
        JButton v12 = new JButton();
        setB(v12,150,350);
        
        JButton v21 = new JButton();
        setB(v21,200+n*k,250);
        
        JButton v22 = new JButton();
        setB(v22,200+n*k,350);
        
        
        
        
        setP(h11,1,2);
        setP(h12,1,4);
        
        
        setP(h21,n,2);
        setP(h22,n,4);
        
        
        setP(v11,2,1);
        setP(v12,4,1);
        
        
        setP(v21,2,n);
        setP(v22,4,n);
        
        ImageIcon leftr = new ImageIcon(path+"left.png");
        JButton RotateL = new JButton(leftr);       
        RotateL.setBounds(n+10,n/2*k+210,30,30);
        RotateL.setMargin(new Insets(1,1,1,1));
        RotateL.setFocusable(false);
        p.add(RotateL);
        
        ImageIcon rightr = new ImageIcon(path+"right.png");
        JButton RotateR = new JButton(rightr);
        RotateR.setBounds(n+90,n/2*k+210,30,30);
        RotateR.setMargin(new Insets(1,1,1,1));
        RotateR.setFocusable(false);
        p.add(RotateR);
        
        RotateR.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        MazeCard mc = mb.getFreeCard();
                        mc.turnRight();
                    }
                });
        RotateL.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        MazeCard mc = mb.getFreeCard();
                        mc.turnRight();
                        mc.turnRight();
                        mc.turnRight();
                    }
                });
        
        
    }
    private void setP(final JButton b,final int r,final int col){
            b.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        pla = Player.plyr;
                        if(!played&&!((lastx == n && r == 1)&&(col == lasty))&&!((lastx == 1 && r == n)&&(col == lasty))&&!((lasty == 1 && col == n)&&(r == lastx))&&!((lasty == n && col == 1)&&(r == lastx))){
                            MazeField mf;
                            mf = new MazeField(r,col);
                            mb.shift(mf);
                            played = true;
                            lastx = r;
                            lasty = col;
                            for (Player pla1 : pla) {
                                int x = pla1.getR();
                                int y = pla1.getC();
                                if (((y + 1) == r) && (y%2 == 1) && (col == n)) {
                                    if (x == 0) {
                                        pla1.move(n-1, 0);
                                    } else {
                                        pla1.move(-1, 0);
                                    }
                                }
                                if (((y + 1) == r) && (y%2 == 1) && (col == 1)) {
                                    if (x == (n-1)) {
                                        pla1.move(-(n-1), 0);
                                    } else {
                                        pla1.move(1, 0);
                                    }
                                }
                                if (((x+1)== col)&&(x%2 == 1) && (r == 1)) {
                                    if (y == (n-1)) {
                                        pla1.move(0, -(n-1));
                                    } else {
                                        pla1.move(0, 1);
                                    }
                                }
                                if (((x+1)== col)&&(x%2 == 1) && (r == n)) {
                                    if (y == 0) {
                                        pla1.move(0, n-1);
                                    } else {
                                        pla1.move(0, -1);
                                    }
                                }
                            }
                        }
                    }
                });
            b.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt)
            {   
                MazeCard mc;
                mc = mb.getFreeCard();

                if(mc.left && mc.up && !mc.down && !mc.right)
                    img = new ImageIcon(path+"C.jpg");
                else if(mc.up && mc.right && !mc.down && !mc.left)
                    img = new ImageIcon(path+"C2.jpg");
                else if(!mc.up && mc.right && mc.down && !mc.left)
                    img = new ImageIcon(path+"C3.jpg");
                else if(!mc.up && !mc.right && mc.down && mc.left)
                    img = new ImageIcon(path+"C4.jpg");
                else if(mc.up && !mc.right && mc.down && !mc.left)
                    img = new ImageIcon(path+"I.jpg");
                else if(!mc.up && mc.right && !mc.down && mc.left)
                    img = new ImageIcon(path+"I2.jpg");
                else if(mc.up && mc.right && !mc.down && mc.left)
                    img = new ImageIcon(path+"F.jpg");
                else if(mc.up && mc.right && mc.down && !mc.left)
                    img = new ImageIcon(path+"F2.jpg");
                else if(!mc.up && mc.right && mc.down && mc.left)
                    img = new ImageIcon(path+"F3.jpg");
                else
                    img = new ImageIcon(path+"F4.jpg");
                b.setIcon(img);
            }
                @Override
                public void mouseExited(MouseEvent evt)
                {
                    b.setIcon(null);
                }
            }
            );
    }
    private void setB(JButton b,int x, int y){
        b.setBounds(x,y,k,k);
        b.setFocusable(false);
        p.add(b);
    }
    
}

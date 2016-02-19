package projekt.gui.menu;

import projekt.treasure.TreasureCard;
import projekt.treasure.Treasure;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.io.BufferedReader;
import javax.swing.JComboBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import projekt.gui.Board.Blud;
import projekt.gui.Board.Player;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projekt.board.MazeBoard;
import projekt.board.MazeCard;
import projekt.board.MazeField;
import java.nio.charset.Charset;

/**
 * Trieda sluziaca na nacitanie ulozenej hry. 
 * Trieda sluzi na nacitanie vopred ulozenej hry
 * @author Marek Mrkva a Matus Ondris
 */

public class Load extends JPanel{
    
    public Load(final JFrame fra,final JFrame hlavni,final JPanel g){
        
        JPanel windowP = new JPanel(new GridLayout(2,1,5,5));
	JPanel fldP = new JPanel(new GridLayout(2,1,5,5));
	windowP.add(new JLabel("Load"));
        JButton cancel = new JButton("Back");
        cancel.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        fra.dispose();
                    }
                });
        windowP.add(cancel);
	
        File fld;
        File[] Flist;
        final JComboBox flist;
	String way = "../ija/examples/";
        fld = new File(way);
        Flist = fld.listFiles();
        flist = new JComboBox<>(Flist);
        fldP.add(flist);

        JButton ld = new JButton("Load");
        ld.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        String vl = flist.getSelectedItem().toString();
                        String tx = "";
                        int cntr = 0;
                        try {
                            BufferedReader rdr = new BufferedReader(
                                    new InputStreamReader(
                                            new FileInputStream(vl),
                                            Charset.forName("UTF-8")));
                            int r;
                            
                            while((r = rdr.read()) != -1) {
                                
                              char chr = (char) r;
                              tx+=chr;
                            }
                        } 
                        catch (FileNotFoundException exp) {
                        } 
                        catch (IOException exp) {
                        }
                        int f = 0;
                        String search = "";
                        for(;tx.charAt(f)!=';';f++){
                            search+=tx.charAt(f);
                        }
                        f++;
                        int n ;
                        n = Integer.parseInt(search);
                        
                        search = "";
                        for(;tx.charAt(f)!=';';f++){
                            search+=tx.charAt(f);
                        }
                        int pl = Integer.parseInt(search);
                        f++;
                        search = "";
                        for(;tx.charAt(f)!='\n';f++){
                            search+=tx.charAt(f);
                        }
                        f++;
                        int treasure = Integer.parseInt(search);
                        MazeCard crd;
                        MazeBoard brd = MazeBoard.createMazeBoard(n);
                        for(int i = 1; i <= n;i++){
                            for(int j = 1; j <= n;j++){
                                
                                if(tx.charAt(f)=='q')
                                    crd = MazeCard.create("C");
                                else if(tx.charAt(f)=='e'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='r'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='w'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='a'){
                                    crd = MazeCard.create("L");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='s'){
                                    crd = MazeCard.create("L");
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='y'){
                                    crd = MazeCard.create("F");
                                }
                                else if(tx.charAt(f)=='x'){
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='c'){
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else {
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                MazeField pom;
                                pom = brd.board.get(((i-1)*n+j)-1);
                                pom.putCard(crd);
                                brd.board.set((((i-1)*n+j)-1),pom);
                                f++;
                            }
                            f++;
                        }
                        int val;
                        Treasure tres;
                        for(int i = 1; i <= n;i++){
                            for(int j = 1; j <= n;j++){
                                search = "";
                                if(tx.charAt(f) == '-'){
                                    f++;
                                    f++;   
                                } 
                                else{
                                    search += tx.charAt(f);
                                    if(tx.charAt(f+1)!=';'){
                                        
                                        search += tx.charAt(f+1);
                                        f++;
                                    }
                                    val = Integer.parseInt(search);
                                    tres = new Treasure(val);
                                    MazeField hlp;
                                    hlp = brd.board.get(((i-1)*n+j)-1);
                                    hlp.tre = tres;
                                    brd.board.set(((i-1)*n+j)-1,hlp);
                                    f++;
                                    f++;
                                    cntr++;
                                }
                            }
                            f++;
                        }
                        Treasure.createSet(24);
                        if(tx.charAt(f)=='q')
                            crd = MazeCard.create("C");
                                else if(tx.charAt(f)=='e'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='r'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='w'){
                                    crd = MazeCard.create("C");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='a'){
                                    crd = MazeCard.create("L");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='s'){
                                    crd = MazeCard.create("L");
                                }
                                else if(tx.charAt(f)=='y'){
                                    crd = MazeCard.create("F");
                                }
                                else if(tx.charAt(f)=='x'){
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                }
                                else if(tx.charAt(f)=='c'){
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                else {
                                    crd = MazeCard.create("F");
                                    crd.turnRight();
                                    crd.turnRight();
                                    crd.turnRight();
                                }
                                brd.freecard.card = new MazeCard();
                                brd.freecard.putCard(crd);
                                f++;
                        search = "";
                        if(tx.charAt(f)=='-'){
                            f+=3;
                        }else{
                            search += tx.charAt(f);
                                    if(tx.charAt(f+1)!=';'){
                                        
                                        search += tx.charAt(f+1);
                                        f++;
                                    }
                                    val = Integer.parseInt(search);
                                    tres = new Treasure(val);
                                    brd.freecard.tre = tres;
                                    f+=3;
                                    cntr++;
                        }
                        List<TreasureCard> cards = new ArrayList<>(cntr);
                        for(int i = 1; i <= n;i++){
                            for(int j =1; j <= n;j++){
                                
                                MazeField pom;
                                pom = brd.board.get(((i-1)*n)+j-1);
                                if(pom.tre!=null){
                                    TreasureCard tc = new TreasureCard(pom.tre);
                                    cards.add(tc);
                                }
                            }
                        }
                        int[] score = new int[pl];
                        int[] x = new int[pl];
                        int[] y = new int[pl];
                        int[] h = new int[pl];
                        for(int a = 0;a < pl;a++){
                            search = "";
                            search += tx.charAt(f);
                            if(tx.charAt(f+1)!= ' '){
                                search += tx.charAt(f+1);
                                f+=3;
                            }
                            else
                                f+=2;
                            
                            score[a] = Integer.parseInt(search);
                            search = "";
                            search += tx.charAt(f);
                            if(tx.charAt(f+1)!= ' '){
                                search += tx.charAt(f+1);
                                f+=3;
                            }
                            else
                                f+=2;
                            h[a] = Integer.parseInt(search);
                            search = "";
                            search += tx.charAt(f);
                            if(tx.charAt(f+1)!= ' '){
                                search += tx.charAt(f+1);
                                f+=3;
                            }
                            else
                                f+=2;
                            x[a] = Integer.parseInt(search);
                            search = "";
                            search += tx.charAt(f);
                            if(tx.charAt(f+1)!= '\n'){
                                search += tx.charAt(f+1);
                                f+=3;
                            }
                            else
                                f+=2;
                            y[a] = Integer.parseInt(search);
                        }
                        int actual;
                        search = "";
                        search += tx.charAt(f);
                        actual = Integer.parseInt(search);
                        Player.LoadPlayer(pl, n, cntr,cards, brd,score,h,x,y,actual);
                        Blud blud = new Blud(n,treasure,pl,brd,hlavni);
                        hlavni.remove(g);
                        fra.dispose();
                        
                    }
                });
        
	JPanel fldpp = new JPanel(new GridLayout(1,2,5,5));
        fldpp.add(ld);
        fldpp.add(new JLabel(""));
        fldP.add(fldpp);
	add(windowP);
	add(fldP);
    }
}

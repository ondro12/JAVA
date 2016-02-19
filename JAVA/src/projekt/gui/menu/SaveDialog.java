package projekt.gui.menu;

import projekt.gui.Board.Player;
import projekt.treasure.Treasure;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import projekt.board.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda sluziaca na ulozenie hry. 
 * Trieda sluzi na ulozenie rozohratej hry v stave v akom sa momentalne nachadza
 * @author Marek Mrkva a Matus Ondris
 */

public class SaveDialog extends JPanel{
    
    private String tx;
    String tn;    
    public SaveDialog(final JFrame frm,final MazeBoard brd, final int size,final int t){
        JLabel sv = new JLabel("New:");
        JPanel fld = new JPanel();
        fld.setLayout(new GridLayout(1,1));
        final JTextField msg = new JTextField();
        JPanel butt = new JPanel();
        butt.setLayout(new FlowLayout(10));
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        tx = msg.getText();
                        tn = "";
                        tn += size+";"+Player.plyr.length+";"+t+"\n"; 
                        for(int i = 1; i <= size;i++){
                                for(int j = 1; j <= size;j++){
                                    
                                    MazeField pom = brd.board.get(((i-1)*size)+j-1);
                                    MazeCard crd = pom.getCard();
                                    if(crd.left && crd.up && !crd.down && !crd.right)
                                        tn+="q";
                                    else if(crd.up && crd.right && !crd.down && !crd.left)
                                        tn+="w";
                                    else if(!crd.up && crd.right && crd.down && !crd.left)
                                        tn+="e";
                                    else if(!crd.up && !crd.right && crd.down && crd.left)
                                        tn+="r";
                                    else if(crd.up && !crd.right && crd.down && !crd.left)
                                        tn+="a";
                                    else if(!crd.up && crd.right && !crd.down && crd.left)
                                        tn+="s";
                                    else if(crd.up && crd.right && !crd.down && crd.left)
                                        tn+="y";
                                    else if(crd.up && crd.right && crd.down && !crd.left)
                                        tn+="x";
                                    else if(!crd.up && crd.right && crd.down && crd.left)
                                        tn+="c";
                                    else if(crd.up && !crd.right && crd.down && crd.left)
                                        tn+="v";
                                }
                                tn+="\n";
                        }
                        for(int i = 1; i <= size;i++){
                                for(int j = 1; j <= size;j++){
                                    MazeField pom = brd.board.get(((i-1)*size)+j-1);
                                    Treasure tres = pom.getTreasure();
                                    if(tres != null)
                                        tn+=tres.id+";";
                                    else
                                        tn+="-;";
                                }
                                tn+="\n";
                        }
                        MazeCard crd = brd.freecard.getCard();
                        if(crd.left && crd.up && !crd.down && !crd.right)
                                        tn+="q";
                                    else if(crd.up && crd.right && !crd.down && !crd.left)
                                        tn+="w";
                                    else if(!crd.up && crd.right && crd.down && !crd.left)
                                        tn+="e";
                                    else if(!crd.up && !crd.right && crd.down && crd.left)
                                        tn+="r";
                                    else if(crd.up && !crd.right && crd.down && !crd.left)
                                        tn+="a";
                                    else if(!crd.up && crd.right && !crd.down && crd.left)
                                        tn+="s";
                                    else if(crd.up && crd.right && !crd.down && crd.left)
                                        tn+="y";
                                    else if(crd.up && crd.right && crd.down && !crd.left)
                                        tn+="x";
                                    else if(!crd.up && crd.right && crd.down && crd.left)
                                        tn+="c";
                                    else if(crd.up && !crd.right && crd.down && crd.left)
                                        tn+="v";
                        Treasure tr = brd.freecard.getTreasure();
                                    if(tr != null)
                                        tn+=tr.id+";\n";
                                    else
                                        tn+="-;\n";
                        for(int k = 1;k<=Player.plyr.length;k++){
                            tn += Player.getP(k).score+" "+Player.getP(k).find.id+" "+Player.getP(k).getR()+" "+Player.getP(k).getC()+"\n";
                        }
                        tn += Player.ac.ident;
                        System.out.println(tn);
                        String way = "../ija/examples/"+tx+".txt";    
                        try {
                            try (BufferedWriter writer = new BufferedWriter ( new FileWriter(way) )) {
                                writer.write(tn);
                            }
                        } catch (IOException e) {
                        }
                        frm.dispose();
                    }
                });
        
        JButton quit = new JButton("Cancel");
        quit.addActionListener(new ActionListener(){
            
                @Override
                    public void actionPerformed(ActionEvent c){
                        frm.dispose();
                    }
                });
        
        butt.add(ok);
        butt.add(quit);
        fld.add(sv);
        fld.add(msg);
        fld.add(butt);
        add(fld);
    }
}

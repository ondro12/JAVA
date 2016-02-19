package projekt.gui.Board;

import projekt.board.MazeField;
import projekt.gui.Board.buttons.Butt;
import projekt.treasure.TreasureCard;
import java.awt.*;
import java.util.ArrayList;
import projekt.board.MazeBoard;
import projekt.board.MazeCard;
import java.util.Stack;
import javax.swing.*;
import projekt.treasure.Treasure;
import java.awt.event.*;
import projekt.treasure.CardPack;
import static java.awt.event.KeyEvent.*;

/**
 * Trieda sluziaca na pracu s hracou doskou. 
 * Trieda sluzi pracu s hracou doskou veskere akcie a pohyby co sa deju
 * @author Marek Mrkva a Matus Ondris
 */


public class Board extends JPanel implements ActionListener{
    private final Timer clk;
    private final Map world;
    public Player Pident[];
    private final MazeBoard brd;
    private final int size;
    private final int Pvl;
    private Player Pinf;
    private int Pval;
    private MazeCard crd;
    private Graphics gr;
    private final int v = 50;
    private Butt fld;
    private final Image F;
    private final Image IF;
    protected int trs;
    
    public boolean win = false;
    private final Stack<String> stack = new Stack<>();
    
    private final Font font = new Font("Serif",Font.BOLD, 30);
    
    private String Message = "";
    private String p1 = "";
    private String p2 = "Player2 not playng";
    private String p3 = "Player3 not playng";
    private String p4 = "Player4 not playng";
    private final String tah = "ON TURN";
    private final String path = "./lib/images/";
    
     /**
     * Metoda Board
     * Sluzi na spracovavanie akcii a eventov v hre pohyb hracov , skore , vytzstvo 
     * m
     */
        
    public Board(final int n,final MazeBoard mb,final int pl,final int tr){
        this.Pident = new Player[n];
        this.brd = mb;
        this.size = n;
        this.Pvl = pl;
        this.trs = tr;
        Pval = Player.getAC();
        ImageIcon img = new ImageIcon(path+"PFrame.png");
        F = img.getImage();
        img = new ImageIcon(path+"imgframe.png");
        IF = img.getImage();
        world = new Map(n,this.brd);
        for( int i = 1; i <= pl;++i){
            Pident[i] = Player.getP(i);
        }
        this.setBackground(Color.YELLOW);
        fld = new Butt(gr,this,n,mb);
        JButton nextP = new JButton("End of Turn");
        nextP.setBounds(455,v+30,100,50);
        nextP.setFocusable(false);
        nextP.setMargin(new Insets(1,1,1,1));
        this.add(nextP);
        ImageIcon sipka = new ImageIcon(path+"sipkaL.png");
        JButton Left = new JButton(sipka);
        Left.setBounds(size*50+260, size/2*v+200,50,50);
        Left.setFocusable(false);
        Left.setMargin(new Insets(1,1,1,1));
        this.add(Left);
        Left.addActionListener(new ActionListener(){
            @Override
                    public void actionPerformed(ActionEvent c){
                        Message = "Winner: Player "+(Pinf.ident);
                        if(!win){
                            left();
            }
                    }
        });
        sipka = new ImageIcon(path+"sipkaR.png");
        JButton Right = new JButton(sipka);
        Right.setBounds(size*50+330, size/2*v+200,50,50);
        Right.setFocusable(false);
        Right.setMargin(new Insets(1,1,1,1));
        this.add(Right);
        Right.addActionListener(new ActionListener(){
            @Override
                    public void actionPerformed(ActionEvent c){
                        Message = "Winner: Player "+(Pinf.ident);
                        if(!win){
                            right();
            }
                    }
        });
        sipka = new ImageIcon(path+"sipkaU.png");
        JButton Up = new JButton(sipka);
        Up.setBounds(size*50+295, size/2*v+150,50,50);
        Up.setFocusable(false);
        Up.setMargin(new Insets(1,1,1,1));
        this.add(Up);
        Up.addActionListener(new ActionListener(){
            @Override
                    public void actionPerformed(ActionEvent c){
                        Message = "Winner: Player "+(Pinf.ident);
                        if(!win){
                            up();}}
        });
        sipka = new ImageIcon(path+"sipkaD.png");
        JButton Down = new JButton(sipka);
        Down.setBounds(size*50+295, size/2*v+250,50,50);
        Down.setFocusable(false);
        Down.setMargin(new Insets(1,1,1,1));
        this.add(Down);
        Down.addActionListener(new ActionListener(){
            @Override
                    public void actionPerformed(ActionEvent c){
                        Message = "Winner: Player "+(Pinf.ident);
                        if(!win){
                            down();
                        }
                    }
        });
        JButton Undo = new JButton("Undo");
        Undo.setBounds(n+30,v+30,120,50);
        Undo.setFocusable(false);
        this.add(Undo);
        Undo.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        String set;
                        if(!stack.empty() && !win){
                            set = stack.pop();
                            Load(set);
                            if(stack.empty())
                                Save();
                        }    
                    }
                });
        ImageIcon keybrd = new ImageIcon(path+"arrows.png");
        JButton Ovladani = new JButton(keybrd);
        Ovladani.setBounds(n*50+265,n/2*v+90,100,53);
        Ovladani.setFocusable(false);
        Ovladani.setMargin(new Insets(1,1,1,1));
        this.add(Ovladani);
        Ovladani.setFocusable(true);
        Ovladani.addKeyListener( new KeyListener() {
            @Override
            public void keyTyped( KeyEvent evt ) {
            }

            @Override
            public void keyPressed( KeyEvent evt ) {
                int keycode = evt.getKeyCode();
                Message = "Winner: Player "+(Pinf.ident);
                if((keycode == VK_UP) && (!win)){
                    up();
                }
                if((keycode == VK_DOWN) && (!win)){
                    down();
                }
                if((keycode == VK_LEFT) && (!win)){
                    left();
                }
                if((keycode == VK_RIGHT) && (!win)){
                    right();
                }
            }
            @Override
            public void keyReleased( KeyEvent evt ) {
            }
        } );
        nextP.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent c){
                        if(!win){
                            Pval++;
                            if(Pval > pl)
                                Pval = 1;
                            Pinf = Pident[Pval];
                            Player.setAC(Pinf);
                            if(fld.played == false){
                                fld.lastx = 0;
                                fld.lasty = 0;
                            }
                            fld.played = false;
                            Save();                      
                        }
                    }
                });
        Pinf = Player.getP(Pval);
        Player.setAC(Pinf);
        
        p1 = "Player1 Score: "+Pident[1].score;
        if(pl > 1)
            p2 = "Player2 Score: "+Pident[2].score;
        if (pl > 2)
            p3 = "Player3 Score: "+Pident[3].score;
        if (pl > 3)
            p4 = "Player4 Score: "+Pident[4].score;
        Save();
        clk = new Timer(10,this);
        clk.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    
    /**
     * Metoda sluziaca na vykreslovanie obrazkov hracov
     * metoda sluzi na vykreslenie sprav obrzkov ku informaciam o hracoch
     */
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
            drawmap(g);
            g.drawImage(F, 0, 0, null);
            g.drawImage(F, 200, 0, null);
            g.drawImage(F, 400, 0, null);
            g.drawImage(F, 200, 70, null);
            g.drawImage(IF, 630, 0, null);
            g.drawString(p1,60,40);
            g.drawString(p2,260,40);
            g.drawString(p3,460,40);
            g.drawString(p4, 260, 110);
            g.drawString("FIND THOSE TRES", size*50+280, size/2*v+5);
            aktivita(g);
            g.drawImage(ImgTreasure.getT(Pinf.find),665,55,null);
            g.drawImage(Player.getPlayer(1),20,15,null);
            if(Pvl > 1)
                g.drawImage(Player.getPlayer(2),220,15,null);
            if(Pvl > 2)
                g.drawImage(Player.getPlayer(3),420,15,null);
            if(Pvl > 3)
                g.drawImage(Player.getPlayer(4),220,85,null);
            
            g.drawImage(Player.getPlayer(1),Pident[1].getR() * v+200,Pident[1].getC() * v+200,null);
         if (Pvl > 1)
            g.drawImage(Player.getPlayer(2),Pident[2].getR() * v+200,Pident[2].getC() * v+200,null);
         if (Pvl > 2)
             g.drawImage(Player.getPlayer(3),Pident[3].getR() * v+200,Pident[3].getC() * v+200,null);
         if (Pvl > 3)
             g.drawImage(Player.getPlayer(4),Pident[4].getR() * v+200,Pident[4].getC() * v+200,null);
        if(win){
            g.setColor(Color.blue);
            g.setFont(font);
            g.drawString(Message,150+size/2*50,150+size/2*50);
            
        }
    
    }    
    
    /**
     * Metoda drawmap.
     * Sluzi na vykreslenie hracej plochy
     */
    
    private void drawmap(Graphics g){
        for( int y = 0;y < size;y++){
                for( int x = 0;x < size;x++){
                    MazeField pom;
                    pom = brd.board.get(y*size+x);
                    crd = pom.getCard();
                    if(crd.left && crd.up && !crd.down && !crd.right)
                        g.drawImage(world.getC(),x*50+200,y*50+200,null);
                    if(crd.up && crd.right && !crd.down && !crd.left)
                        g.drawImage(world.getC2(),x*50+200,y*50+200,null);
                    if(!crd.up && crd.right && crd.down && !crd.left)
                        g.drawImage(world.getC3(),x*50+200,y*50+200,null);
                    if(!crd.up && !crd.right && crd.down && crd.left)
                        g.drawImage(world.getC4(),x*50+200,y*50+200,null);
                    if(crd.up && !crd.right && crd.down && !crd.left)
                        g.drawImage(world.getI2(),x*50+200,y*50+200,null);
                    if(!crd.up && crd.right && !crd.down && crd.left)
                        g.drawImage(world.getI(),x*50+200,y*50+200,null);
                    if(crd.up && crd.right && !crd.down && crd.left)
                        g.drawImage(world.getF(),x*50+200,y*50+200,null);
                    if(crd.up && crd.right && crd.down && !crd.left)
                        g.drawImage(world.getF2(),x*50+200,y*50+200,null);
                    if(!crd.up && crd.right && crd.down && crd.left)
                        g.drawImage(world.getF3(),x*50+200,y*50+200,null);
                    if(crd.up && !crd.right && crd.down && crd.left)
                        g.drawImage(world.getF4(),x*50+200,y*50+200,null);
                    if(pom.tre != null){
                        g.drawImage(ImgTreasure.getT(pom.tre),x*50+210,y*50+210,null);
                    }
                }
            }
        crd = brd.getFreeCard();
        if(crd.left && crd.up && !crd.down && !crd.right)
            g.drawImage(world.getC(),size+40,size/2*106+33,null);
        if(crd.up && crd.right && !crd.down && !crd.left)
            g.drawImage(world.getC2(),size+40,size/2*106+33,null);
        if(!crd.up && crd.right && crd.down && !crd.left)
            g.drawImage(world.getC3(),size+40,size/2*106+33,null);
        if(!crd.up && !crd.right && crd.down && crd.left)
            g.drawImage(world.getC4(),size+40,size/2*106+33,null);
        if(crd.up && !crd.right && crd.down && !crd.left)
            g.drawImage(world.getI2(),size+40,size/2*106+33,null);
        if(!crd.up && crd.right && !crd.down && crd.left)
            g.drawImage(world.getI(),size+40,size/2*106+33,null);
        if(crd.up && crd.right && !crd.down && crd.left)
            g.drawImage(world.getF(),size+40,size/2*106+33,null);
        if(crd.up && crd.right && crd.down && !crd.left)
            g.drawImage(world.getF2(),size+40,size/2*106+33,null);
        if(!crd.up && crd.right && crd.down && crd.left)
            g.drawImage(world.getF3(),size+40,size/2*106+33,null);
        if(crd.up && !crd.right && crd.down && crd.left)
            g.drawImage(world.getF4(),size+40,size/2*106+33,null);
        if(brd.freecard.tre != null)
            g.drawImage(ImgTreasure.getT(brd.freecard.tre),size+47,size/2*50+210,null);

    }
    
    /**
     * Metoda setM.
     * Sluzi na vypisanie informacii o skore hracov
     */
    
    private void setM(Player a){
        if (a.ident == 1)
            p1 = "Player1 Score: "+Pident[1].score;
        if (a.ident == 2 && Pvl > 1)
            p2 = "Player2 Score: "+Pident[2].score;
        if (a.ident == 3 && Pvl > 2)
            p3 = "Player3 Score: "+Pident[3].score;
        if (a.ident == 4 && Pvl > 3)
            p4 = "Player4 Score: "+Pident[4].score;
    }
    
     /**
     * Metoda aktivita.
     * Sluzi na vypisanie informacii tom ktory hrac je na tahu
     */
    
    private void aktivita(Graphics g){
        g.setColor(Color.red);
        if (Pval == 1)
            g.drawString(tah, 80, 25);
        if (Pval == 2)
            g.drawString(tah, 280, 25);
        if (Pval == 3)
            g.drawString(tah, 480, 25);
        if (Pval == 4)
            g.drawString(tah, 280, 95);
    }
    public Player AktivPlay(){
        return Pinf;
    }
    
     /**
     * Metody down,up,left,right.
     * Sluzia na pohyb hracov po hracej doske
     */
    
    public void down(){
        int x = Pinf.getR();
        int y = Pinf.getC();
        MazeField pom;
        pom = brd.board.get(y*size+x);
        MazeCard m = pom.getCard();
        if (y + 1 != size){
            MazeField pomdals;
            pomdals = brd.board.get((y+1)*size+x);
            MazeCard m2 = pomdals .getCard();
            if((m.down) && (m2.up)){
                Pinf.move(0,1);
                Treasure tres;
                        
                tres = pomdals.tre;
                if ((tres != null) && (tres.id == Pinf.find.id)){                    
                    Pinf.score++;
                    if(Pinf.score == trs/Pvl)
                        win = true;
                    else{
                        setM(Pinf);
                        Pinf.NextT(Pval);
                        pomdals.tre = null;
                        brd.board.set((y+1)*size+x,pomdals);
                        
                        Pval++;
                        if(Pval > Pvl)
                            Pval = 1;
                        Pinf = Pident[Pval];
                        Player.setAC(Pinf);
                        if(fld.played == false){
                                fld.lastx = 0;
                                fld.lasty = 0;
                            }
                        fld.played = false;
                    }
                    Save();
                }
            }
        }
    }
    public void up(){
        int x = Pinf.getR();
        int y = Pinf.getC();
        MazeField pom;
        pom = brd.board.get(y*size+x);
        MazeCard m = pom.getCard();
        if (y - 1 != -1){
            MazeField pomdals;
            pomdals = brd.board.get((y-1)*size+x);
            MazeCard m2 = pomdals.getCard();
            if((m.up) && m2.down){
                Pinf.move(0,-1);
                Treasure tres;     
                tres = pomdals.tre;
                if ((tres != null) && (tres.id == Pinf.find.id)){
                    
                    Pinf.score++;
                    if(Pinf.score== trs/Pvl)
                        win = true;
                    else{
                        setM(Pinf);
                        Pinf.NextT(Pval);
                        pomdals.tre = null;
                        brd.board.set(((y-1)*size)+x,pomdals);
                        
                        Pval++;
                        if(Pval > Pvl)
                            Pval = 1;
                        Pinf = Pident[Pval];
                        if(fld.played == false){
                                fld.lastx = 0;
                                fld.lasty = 0;
                            }
                        fld.played = false;
                    }
                    Save();
                }            
            }
        }
    }
    public void left(){
        int x = Pinf.getR();
        int y = Pinf.getC();
        MazeField pom;
        pom = brd.board.get(y*size+x);
        MazeCard m = pom.getCard();
        if (x - 1 != -1){
            MazeField pomdals;
            pomdals = brd.board.get((y)*size+x-1);
            MazeCard m2 = pomdals.getCard();
            if((m.left) && (m2.right)){
                Pinf.move(-1,0);
                Treasure tres;
                        
                tres = pomdals.tre;
                if ((tres != null) && (tres.id == Pinf.find.id)){
                    Pinf.score++;
                    if(Pinf.score == trs/Pvl)
                        win = true;
                    else{
                        setM(Pinf);
                        Pinf.NextT(Pval);
                        pomdals.tre = null;
                        brd.board.set((y)*size+x-1,pomdals);
                        
                        Pval++;
                        if(Pval > Pvl)
                            Pval = 1;
                        Pinf = Pident[Pval];
                        Player.setAC(Pinf);
                        if(fld.played == false){
                                fld.lastx = 0;
                                fld.lasty = 0;
                        }
                        fld.played = false;
                    }
                    Save();
                }
            }
                            
        }
    }
    public void right(){
        int x = Pinf.getR();
        int y = Pinf.getC();
        MazeField pom;
        pom = brd.board.get(y*size+x);
        MazeCard m = pom.getCard();
        if (x + 1 != size){
            MazeField pomdals;
            pomdals = brd.board.get(y*size+x+1);
            MazeCard m2 = pomdals.getCard();
            if((m.right) && (m2.left)){
                Pinf.move(1,0);
                Treasure tres;
                        
                tres = pomdals.tre;
                if ((tres != null) && (tres.id == Pinf.find.id)){
                    Pinf.score++;
                    if(Pinf.score == trs/Pvl)
                        win = true;
                    else{
                        setM(Pinf);
                        Pinf.NextT(Pval);
                        pomdals.tre = null;
                        brd.board.set(y*size+x+1,pomdals);
                        
                        Pval++;
                        if(Pval > Pvl)
                            Pval = 1;
                        Pinf = Pident[Pval];
                        Player.setAC(Pinf);
                        if(fld.played == false){
                                fld.lastx = 0;
                                fld.lasty = 0;
                        }
                        fld.played = false;
                    }
                    Save();
                }
            }
        }
    }
    
    private void Save(){
        String content = "";
                        content += size+";"+Player.plyr.length+";"+trs+"\n";
                        
                        for(int i = 1; i <= size;i++){
                                for(int j = 1; j <= size;j++){
                                    MazeField pom;
                                    pom = brd.board.get((i-1)*size+j-1);
                                    MazeCard mcrd = pom.getCard();
                                    if(mcrd.left && mcrd.up && !mcrd.down && !mcrd.right)
                                        content+="q";
                                    else if(mcrd.up && mcrd.right && !mcrd.down && !mcrd.left)
                                        content+="w";
                                    else if(!mcrd.up && mcrd.right && mcrd.down && !mcrd.left)
                                        content+="e";
                                    else if(!mcrd.up && !mcrd.right && mcrd.down && mcrd.left)
                                        content+="r";
                                    else if(mcrd.up && !mcrd.right && mcrd.down && !mcrd.left)
                                        content+="a";
                                    else if(!mcrd.up && mcrd.right && !mcrd.down && mcrd.left)
                                        content+="s";
                                    else if(mcrd.up && mcrd.right && !mcrd.down && mcrd.left)
                                        content+="y";
                                    else if(mcrd.up && mcrd.right && mcrd.down && !mcrd.left)
                                        content+="x";
                                    else if(!mcrd.up && mcrd.right && mcrd.down && mcrd.left)
                                        content+="c";
                                    else if(mcrd.up && !mcrd.right && mcrd.down && mcrd.left)
                                        content+="v";
                                }
                                content+="\n";
                        }
                        for(int i = 1; i <= size;i++){
                                for(int j = 1; j <= size;j++){
                                    MazeField pom;
                                    pom = brd.board.get((i-1)*size+j-1);
                                    Treasure tresu = pom.getTreasure();
                                    if(tresu != null)
                                        content+=tresu.id+";";
                                    else
                                        content+="-;";
                                }
                                content+="\n";
                        }
                        MazeCard mcrd = brd.freecard.getCard();
                        if(mcrd.left && mcrd.up && !mcrd.down && !mcrd.right)
                                        content+="q";
                                    else if(mcrd.up && mcrd.right && !mcrd.down && !mcrd.left)
                                        content+="w";
                                    else if(!mcrd.up && mcrd.right && mcrd.down && !mcrd.left)
                                        content+="e";
                                    else if(!mcrd.up && !mcrd.right && mcrd.down && mcrd.left)
                                        content+="r";
                                    else if(mcrd.up && !mcrd.right && mcrd.down && !mcrd.left)
                                        content+="a";
                                    else if(!mcrd.up && mcrd.right && !mcrd.down && mcrd.left)
                                        content+="s";
                                    else if(mcrd.up && mcrd.right && !mcrd.down && mcrd.left)
                                        content+="y";
                                    else if(mcrd.up && mcrd.right && mcrd.down && !mcrd.left)
                                        content+="x";
                                    else if(!mcrd.up && mcrd.right && mcrd.down && mcrd.left)
                                        content+="c";
                                    else if(mcrd.up && !mcrd.right && mcrd.down && mcrd.left)
                                        content+="v";
                        Treasure tres = brd.freecard.getTreasure();
                                    if(tres != null)
                                        content+=tres.id+";\n";
                                    else
                                        content+="-;\n";
                        for(int a = 1;a<=Player.plyr.length;a++){
                            content += Player.getP(a).score+" "+Player.getP(a).find.id+" "+Pident[a].getR()+" "+Pident[a].getC()+"\n";
                        }
                        content += Player.ac.ident;
                        stack.push(content);
    }
    
    /**
     * Metody Load a Save.
     * Sluzia na ulozenie a nacitanie aktualneho stavu hry,
     * pouzivame pomovne znaky ako identifikatory kamenov , hracov .
     */
    
    private void Load(String ent){
        for(int i = 1; i <= size;i++){
            for(int j = 1;j <= size;j++){
                MazeField pom;
                pom = brd.board.get((i-1)*size+j-1);
                pom.tre = null;
                brd.board.set((i-1)*size+j-1,pom);
            }
        }
        brd.freecard.tre = null;
        int k = 0;
        int count = 0;
                        String find = "";
                        for(;ent.charAt(k)!=';';k++){
                            find+=ent.charAt(k);
                        }
                        k++;
                        int n = Integer.parseInt(find);
                        find = "";
                        for(;ent.charAt(k)!=';';k++){
                            find+=ent.charAt(k);
                        }
                        int pl = Integer.parseInt(find);
                        k++;
                        find = "";
                        for(;ent.charAt(k)!='\n';k++){
                            find+=ent.charAt(k);
                        }
                        k++;
                        int treasure = Integer.parseInt(find);
                        MazeCard mc;
                        for(int i = 1; i <= n;i++){
                            for(int j = 1; j <= n;j++){
                                
                                if(ent.charAt(k)=='q')
                                    mc = MazeCard.create("C");
                                else if(ent.charAt(k)=='e'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='r'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='w'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='a'){
                                    mc = MazeCard.create("L");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='s'){
                                    mc = MazeCard.create("L");
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='y'){
                                    mc = MazeCard.create("F");
                                }
                                else if(ent.charAt(k)=='x'){
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='c'){
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else {
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                MazeField pom;
                                pom = brd.board.get(((i-1)*n)+j-1);
                                pom.putCard(mc);
                                brd.board.set(((i-1)*n)+j-1,pom);
                                k++;
                            }
                            k++;
                        }
                        int val;
                        Treasure tr;
                        for(int i = 1; i <= n;i++){
                            for(int j = 1; j <= n;j++){
                                find = "";
                                if(ent.charAt(k) == '-'){
                                    k++;
                                    k++;
                                    
                                } 
                                else{
                                    find += ent.charAt(k);
                                    if(ent.charAt(k+1)!=';'){
                                        
                                        find += ent.charAt(k+1);
                                        k++;
                                    }
                                    val = Integer.parseInt(find);
                                    tr = new Treasure(val);
                                    MazeField pom;
                                    pom = brd.board.get((i-1)*n+j-1);
                                    pom.tre = tr;
                                    brd.board.set((i-1)*n+j-1,pom);
                                    k++;
                                    k++;
                                    count++;
                                }
                            }
                            k++;
                        }
                        Treasure.createSet(24);
                        if(ent.charAt(k)=='q')
                            mc = MazeCard.create("C");
                                else if(ent.charAt(k)=='e'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='r'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='w'){
                                    mc = MazeCard.create("C");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='a'){
                                    mc = MazeCard.create("L");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='s'){
                                    mc = MazeCard.create("L");
                                }
                                else if(ent.charAt(k)=='y'){
                                    mc = MazeCard.create("F");
                                }
                                else if(ent.charAt(k)=='x'){
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                }
                                else if(ent.charAt(k)=='c'){
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                else {
                                    mc = MazeCard.create("F");
                                    mc.turnRight();
                                    mc.turnRight();
                                    mc.turnRight();
                                }
                                brd.freecard.putCard(mc);
                                k++;
                        find = "";
                        if(ent.charAt(k)=='-'){
                            k+=3;
                        }else{
                            find += ent.charAt(k);
                                    if(ent.charAt(k+1)!=';'){
                                        
                                        find += ent.charAt(k+1);
                                        k++;
                                    }
                                    val = Integer.parseInt(find);
                                    tr = new Treasure(val);
                                    brd.freecard.tre = tr;
                                    k+=3;
                                    count++;
                        }
                        java.util.List<TreasureCard> Pack = new ArrayList<>(count);
                        for(int i = 1; i <= n;i++){
                            for(int j = 1; j <= n;j++){
                                MazeField pom;
                                pom = brd.board.get((i-1)*n+j-1);
                                if(pom.tre != null){
                                    int t = pom.tre.id;
                                    TreasureCard tc = new TreasureCard(pom.tre);
                                    Pack.add(tc);
                                }
                            }
                        }
                        CardPack cp = new CardPack(count,Pack);
                        int[] score = new int[pl];
                        int[] fx = new int[pl];
                        int[] fy = new int[pl];
                        int[] f = new int[pl];
                        for(int a = 0;a < pl;a++){
                            find = "";
                            find += ent.charAt(k);
                            if(ent.charAt(k+1)!= ' '){
                                find += ent.charAt(k+1);
                                k+=3;
                            }
                            else
                                k+=2;
                            
                            score[a] = Integer.parseInt(find);
                            find = "";
                            find += ent.charAt(k);
                            if(ent.charAt(k+1)!= ' '){
                                find += ent.charAt(k+1);
                                k+=3;
                            }
                            else
                                k+=2;
                            f[a] = Integer.parseInt(find);
                            find = "";
                            find += ent.charAt(k);
                            if(ent.charAt(k+1)!= ' '){
                                find += ent.charAt(k+1);
                                k+=3;
                            }
                            else
                                k+=2;
                            fx[a] = Integer.parseInt(find);
                            find = "";
                            find += ent.charAt(k);
                            if(ent.charAt(k+1)!= '\n'){
                                find += ent.charAt(k+1);
                                k+=3;
                            }
                            else
                                k+=2;
                            fy[a] = Integer.parseInt(find);
                        }
                        int actual;
                        find = "";
                        find += ent.charAt(k);
                        actual = Integer.parseInt(find);
                        
                        
                        Player.LoadPlayer(pl, n, count,Pack, brd,score,f,fx,fy,actual);
                        for( int i = 1; i <= pl;++i){
                            Pident[i] = Player.getP(i);
                            Pident[i].find = Player.getP(i).find;
                            Pident[i].posR = fx[i-1];
                            Pident[i].posC = fy[i-1];
                            
                        }
                        Pval = Player.getAC();
                        Pinf = Pident[Pval];
                        p1 = "Player1 Score: "+Pident[1].score;
                                if(pl > 1)
                                    p2 = "Player2 Score: "+Pident[2].score;
                                if (pl > 2)
                                    p3 = "Player3 Score: "+Pident[3].score;
                                if (pl > 3)
                                    p4 = "Player4 Score: "+Pident[4].score;
                        fld.played = false;                   
    }
}

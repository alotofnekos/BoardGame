
package GameUI_test5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Game {
    public static int pvx=0;
    public static int pvy=0;
    public static int pnx=0;
    public static int pny=0;
    public static int pfx=0;
    public static int pfy=0;
    public static int turncounter=0;
    public static int cntAlpha=0;
    public static int cntBeta=0;
    public static int pass;
    public static boolean alpha = true;
    public static LinkedList<Piece> ps=new LinkedList<>();
    public static Piece selectedPiece=null;
    public static void main(String[] args) throws IOException {
        
        BufferedImage all=ImageIO.read(new File("D:\\GameUI_test2\\star3.png"));
        Image imgs[]=new Image[10];
       int ind=0;
       for(int y=0;y<400;y+=200){
        for(int x=0;x<1000;x+=200){
            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
       ind++;
        }    
        }
        Piece beta1=new Piece(0, 0, false, "star1", ps);
        Piece beta2=new Piece(2, 0, false, "star2", ps);
        Piece beta3=new Piece(4, 0, false, "star3", ps);
        Piece beta4=new Piece(1, 1, false, "star4", ps);
        Piece beta5=new Piece(3, 1, false, "star5", ps);

        
        Piece alpha1=new Piece(0, 6, true, "star1", ps);
        Piece alpha2=new Piece(2, 6, true, "star2", ps);
        Piece alpha3=new Piece(4, 6, true, "star3", ps);
        Piece alpha4=new Piece(1, 5, true, "star4", ps);
        Piece alpha5=new Piece(3, 5, true, "star5", ps);

                
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 340, 490);
        frame.setUndecorated(false);
        JPanel pn=new JPanel(){
            @Override
            public void paint(Graphics g) {
                Color black = Color.BLACK;
                Color wht = Color.WHITE;
                Color red = Color.RED;
                Color blue = Color.BLUE;
                for (int i = 0; i < 7; i++){
                    for (int j = 0; j < 5; j++){
                        if(i<2 && i%2 == j%2){
                            g.setColor(blue);
                            g.fillRect(j*64, i*64, 64, 64);
                        }
                        else if(i>4 && i%2 == j%2){
                            g.setColor(red);
                            g.fillRect(j*64, i*64, 64, 64);
                        }
                        else if(i>1 && i<5 && i%2 == j%2){
                            g.setColor(black);
                            g.fillRect(j*64, i*64, 64, 64);
                        }
                        else{
                            g.setColor(wht);
                            g.fillRect(j*64, i*64, 64, 64);
                        }
                    }
                }
                for(Piece p: ps){
                    int ind=0;
                    if(p.name.equalsIgnoreCase("star1")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("star2")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("star3")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("star4")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("star5")){
                        ind=4;
                    }
                    if(!p.isAlpha){
                        ind+=5;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
            
        };
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
           //  System.out.println((getPiece(e.getX(), e.getY()).isAlpha?"white ":"black ")+getPiece(e.getX(), e.getY()).name);
             selectedPiece=getPiece(e.getX(), e.getY());
                if(getPiece(e.getX(), e.getY()).isAlpha){
                   alpha=true;
                }
                else{
                    alpha=false;
                }
            selectedPiece.x=e.getX()/64;
            selectedPiece.y=e.getY()/64;
            pvx=selectedPiece.x;
            pvy=selectedPiece.y;

        } 

            @Override
            public void mouseReleased(MouseEvent e) {
             //   boolean is_whites_turn = true;
             //   if (turnCounter%2 == 1)
             //   {
             //       is_whites_turn = false;
            //    }   
                selectedPiece.x=e.getX()/64;
                selectedPiece.y=e.getY()/64;
                pnx=selectedPiece.x;
                pny=selectedPiece.y;
                //System.out.println("xp pressed " + pvx);
               // System.out.println("xy pressed " + pvy);
                //System.out.println("alpha pressed " + alpha);
               // System.out.println("turn pressed " + turncounter);
               // System.out.println("xp " + pnx);
               // System.out.println("xy " + pny);
               // System.out.println("alpha " + alpha);
               // System.out.println("turn " + turncounter);

               pass=selectedPiece.move(e.getX()/64, e.getY()/64,turncounter);
              // System.out.println("pass " + pass);
               selectedPiece.move(pvx,pvy,turncounter);
               frame.repaint();  
             //   frame.repaint();  

                if (((pvx!=pnx)||(pvy!=pny))&&(alpha==true)&&(turncounter==0)&&(pass>0)) {
                    turncounter=1;
                   // System.out.println("xp alpha " );
                    selectedPiece.move(e.getX()/64, e.getY()/64,turncounter);
                    frame.repaint();  
                    if (pass==2){
                        cntAlpha=cntAlpha+1;}
                    else{
                        cntAlpha=cntAlpha;
                    }
                } 
                else if(((pvx!=pnx)||(pvy!=pny))&&(alpha==false)&&(turncounter==1)&&(pass>0)) {
                    turncounter=0;
                   // System.out.println("xp beta " );
                    selectedPiece.move(e.getX()/64, e.getY()/64,turncounter);
                    frame.repaint();  
                    if (pass==3){
                        cntBeta=cntBeta+1;}
                        else{
                            cntBeta=cntBeta;
                        }
                }
                else{
                    selectedPiece.move(pvx,pvy,turncounter);
                    frame.repaint();  
                }

                if (cntAlpha==5){
                    JOptionPane.showMessageDialog(frame, "ALPHA WINS");
                }
                else if (cntBeta==5){
                    JOptionPane.showMessageDialog(frame, "BETA WINS");
                }
               //     turnCounter++;
                }   
            

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
       
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static Piece getPiece(int x,int y){
        int xp=x/64;
        int yp=y/64;
        for(Piece p: ps){
            if(p.xp==xp&&p.yp==yp){
                return p;
            }
        }
        return null;
    }
}

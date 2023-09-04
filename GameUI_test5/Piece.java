
package GameUI_test5;

import java.util.LinkedList;

import javax.lang.model.util.ElementScanner14;


public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    boolean isAlpha;
    boolean is_s;
    LinkedList<Piece> ps;
    String name;
    public Piece(int xp, int yp, boolean isAlpha,String n, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x=xp*64;
        y=yp*64;
        this.isAlpha = isAlpha;
        this.ps=ps;
        name=n;
        ps.add(this);
    }
    
    public int move(int xp,int yp,int cnt){
        if(!isAlpha&&cnt==1){
            if((xp>this.xp)||(yp>this.yp)){
              if (((xp==this.xp)&&(yp>this.yp+1))||((xp!=this.xp)&&(yp==this.yp))||(((xp<this.xp-1)||(xp>this.xp+1))&&(yp>=this.yp+1))||(xp>4)||(yp>6)){   
                   x=this.xp*64;
                   y=this.yp*64;
                  // System.out.println("fail1 ");
                  return 0;}
            else{
                if(((xp<this.xp)&&(yp<this.yp))||((xp>this.xp)&&(yp<this.yp))){
                    x=this.xp*64;
                    y=this.yp*64;
                   // System.out.println("fail2 ");
                    return 0;
                }
            }
            }
            else{
              if((xp<this.xp)||(yp<this.yp)){
                if (((xp==this.xp)&&(yp<this.yp))||((xp!=this.xp)&&(yp==this.yp))){   
                     x=this.xp*64;
                     y=this.yp*64;
                    // System.out.println("fail3 ");
                    return 0;}
                else{
                if(((xp<this.xp)&&(yp<this.yp))||((xp>this.xp)&&(yp<this.yp))){
                      x=this.xp*64;
                      y=this.yp*64;
                      //System.out.println("fail4 ");
                      return 0;
                  }
                }
              }
            }
        }
        else if(cnt==0){
            if((xp<this.xp)||(yp<this.yp)){
                if (((xp==this.xp)&&(yp<this.yp-1))||((xp!=this.xp)&&(yp==this.yp))||((xp<this.xp-1)||(xp>this.xp+1)&&(yp<=this.yp-1))){   
                     x=this.xp*64;
                     y=this.yp*64;
                     //System.out.println("fail5 ");
                    return 0;}
              else{
                  if(((xp>this.xp)&&(yp>this.yp))||((xp<this.xp)&&(yp>this.yp))||(xp>4)||(yp>6)){
                      x=this.xp*64;
                      y=this.yp*64;
                      //System.out.println("fail6 ");
                      return 0;
                  }
              }
              }
            else{
                if((xp>this.xp)||(yp>this.yp)){
                  if (((xp==this.xp)&&(yp>this.yp))||((xp!=this.xp)&&(yp==this.yp))||(xp>4)||(yp>6)){   
                       x=this.xp*64;
                       y=this.yp*64;
                       //System.out.println("fail7 ");
                      return 0;}
                  else{
                  if(((xp<this.xp)&&(yp>this.yp)||(yp>6))||((xp>this.xp)&&(yp>this.yp)||(xp>4)||(yp>6))){
                        x=this.xp*64;
                        y=this.yp*64;
                        //System.out.println("fail8 ");
                        return 0;
                    }
                  }
                }
              }
        }

        if(Game.getPiece(xp*64, yp*64)!=null){
            checker(xp,yp);
            if((Game.getPiece(xp*64, yp*64).isAlpha!=isAlpha)&&(is_s)){
               Game.getPiece(xp*64, yp*64).kill();
               if(isAlpha){
                this.xp=xp;
                this.yp=yp;
                x=xp*64;
                y=yp*64;
                return 2;
                }
             else{
                this.xp=xp;
                this.yp=yp;
                x=xp*64;
                y=yp*64;                 
                return 3;
            }
            }else{
                  x=this.xp*64;
                  y=this.yp*64;
            return 0;
            }
        }

     //   System.out.println("xp " + xp);
     //   System.out.println("this xp " + this.xp);
     //   System.out.println("yp " + yp);
    //    System.out.println("this yp " + this.yp);
  
    if((!isAlpha&&cnt==1)||(isAlpha&&cnt==0)){
        this.xp=xp;
        this.yp=yp;
        x=xp*64;
        y=yp*64;
        return 1;}
    else{
        return 0;
    }
    }
    public void kill(){
        ps.remove(this);
    }
    public void checker(int xp,int yp){
        if((xp==0||xp==2||xp==4||xp==6)){
            if((yp==0||yp==2||yp==4||yp==6)&&(this.yp!=yp)){
                is_s=true;
            }
            else{
                is_s=false;
            }
            
        }
        else{
            if(yp==1||yp==3||yp==5){
                is_s=true;
            }
            else{
                is_s=false;
            }
        }
    }

}

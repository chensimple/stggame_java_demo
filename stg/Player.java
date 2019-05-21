package stg;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
class Player 
{  
    static int x;  
    static int y; 
    static int r;
    static boolean islive=true;
	boolean U=false,D=false,L=false,R=false;
	boolean firing=false;
	int initial_speed=2;
	int speed=2;
	private long firingTimer=System.nanoTime(); ;  
    private long firingDelay=150;
	Player(){
		x=450;
		y=600;
		r=5;
		islive=true;
	}
	Player(int initial_x,int initial_y){
		x=initial_x;
		y=initial_y;
		r=5;
	}
	//可添加变化r构造
    public void UpdatePlane() {
    	if(U && R) {
    		y-=initial_speed*speed;
    		if(y<=0)
    			y=0;
    		x+=initial_speed*speed;	
    		if(x>=900)
    			x=0;
    	}
    	else if(U && L)	{
    		y-=initial_speed*speed;
    		if(y<=0)
    			y=0;
    		x-=initial_speed*speed;
    		if(x<=0)
    			x=900;
    	}
    	else if(D && L) {
    		y+=initial_speed*speed;
    		if(y>=865)
    			y=865;
    		x-=initial_speed*speed;
    		if(x<=0)
    			x=900;
    	}
    	else if(D && R) {
    		y+=initial_speed*speed;
    		if(y>=865)
    			y=865;
    		x+=initial_speed*speed;
    		if(x>=900)
    			x=0;
    	}
    	else if(U) {
    		y-=initial_speed*speed;
    		if(y<=0)
    			y=0;
    	}
    	else if(D) {
    		y+=initial_speed*speed;
    		if(y>=865)
    			y=865;
    	}
    	else if(L) {
    		x-=initial_speed*speed;
    		if(x<=0)
    			x=900;
    	}
    	else if(R) {
    		x+=initial_speed*speed;
    		if(x>=900)
    			x=0;
    	}
    	if (firing) {  
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;  
            if (elapsed > firingDelay) {  
                Mypanel.bullets.add(new Bullet(x,y,1));  
                firingTimer = System.nanoTime();  
            } 
    	}
	}
    public void DrawPlane(Graphics g) {
    	Color c1=new Color(255,255,255);
		g.setColor(c1);
		g.fillOval(x-r-2, y-r-2, 2*(r+2), 2*(r+2));
		Color c2=new Color(0,255,0);
		g.setColor(c2);
		g.fillOval(x-r, y-r, 2*r, 2*r);//判定点
	}
    public void keyPressed(int keyCode) {    
        switch(keyCode){  
             case KeyEvent.VK_LEFT:  
            	 L=true;  
                 break;  
             case KeyEvent.VK_RIGHT:  
                 R=true;  
                 break;  
             case KeyEvent.VK_UP:  
                 U=true;  
                 break;  
             case KeyEvent.VK_DOWN:  
                 D=true;  
                 break;  
             case KeyEvent.VK_SHIFT:
            	 speed=1;
            	 break;
             case KeyEvent.VK_Z:
            	 firing=true;
            	 break;
             } 
    }
	public void keyReleased(int keyCode) {
		 switch (keyCode){ 
		 case KeyEvent.VK_LEFT:
		 	 L=false;  
         	 break;  
		 case KeyEvent.VK_RIGHT:  
			 R=false;  
			 break;  
		 case KeyEvent.VK_UP:  
			 U=false;  
			 break;  
		 case KeyEvent.VK_DOWN:  
			 D=false;  
			 break; 
		 case KeyEvent.VK_SHIFT:
        	 speed=2;
        	 break;
		 case KeyEvent.VK_Z:
        	 firing=false;
        	 break;
		 } 
	}  

}
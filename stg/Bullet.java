package stg;

import java.awt.Color;
import java.awt.Graphics;
public class Bullet{
	int x,y;
	int model=1;//子弹样式
	boolean collision=false;
	//boolean isDraw=true;
	Bullet(){
		x=400;
		y=250;
		model=1;
	}
	Bullet(int initial_x,int initial_y,int shape_ID){
		x=initial_x;
		y=initial_y;
		model=shape_ID;
	}
	public void collision() {
		double distance=Math.sqrt((Boss.x-x)*(Boss.x-x)+(Boss.y-y)*(Boss.y-y));
		if(distance<=Boss.r) {
			collision=true;
			Boss.life-=20;//调整子弹伤害
		}
	}
	public boolean UpdateBullet() {
		y-=20;
		if(Boss.life>=0)
		collision();
		if(y>=0 && y<=900 && !collision) {
			return true;
		}
		else
			return false;
	}
	public void DrawBullet(Graphics g) {
		if(model==1) {
			Color c1=new Color(255,255,255);
			g.setColor(c1);
			g.fillOval(x-7, y-12, 14, 24);
			g.setColor(Color.BLUE);
			g.fillOval(x-5, y-10, 10,20);
		}
	}
}
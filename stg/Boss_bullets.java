package stg;
import java.awt.Color;
import java.awt.Graphics;
public class Boss_bullets {
	int x=-50;
	int y=-50;
	int r=7;
	Boss_bullets(){
		x=-50;
		y=-50;
		r=7;
	}
	Color c=new Color(0,0,0);
	public void setX(int a) {
		x=a;
	}
	public void setY(int b) {
		y=b;
	}
	public void setColor(int x,int y,int z) {
		c=new Color(x,y,z);
	}
	public void drawBullet(Graphics g) {
		Color c1=new Color(255,255,255);
		g.setColor(c1);
		g.fillOval(x-r-2, y-r-2, 2*(r+2), 2*(r+2));
		g.setColor(c);
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}
}


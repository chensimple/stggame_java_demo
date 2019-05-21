package stg;
import java.awt.*;
public class Boss {
/**
 * boss(坐标，生命值)
 */
	static int x=450;
	static int y=450;
	static int r=40;
	private boolean collision=false;
	public Boss_bullets[] bullets=new Boss_bullets[300];
	boolean if_alive=true;
	static int life=4999;
	static double time=0;
	
	Boss(){
		x=450;
		y=450;
		r=20;
		time=0;
		life=4999;
		collision=false;
		bullets=new Boss_bullets[300];
		creatbullets();
	}
	private void creatbullets() {
		for(int i=0;i<300;i++) {
			bullets[i]=new Boss_bullets();
		}
	}
	public void time() {
		time+=0.01;
	}
	public void update() {
		time();
		if(time>0&&time<5) {
			x=450;
			y=(int)(time*30);
		}
		if(time>32&&time<=37) {
			x=(int)(450-(time-32)*60);
		}
		if(time>37&&time<=47) {
			x=(int)(150+(time-37)*60);
		}
		if(time>47&&time<=57) {
			x=(int)(750-(time-47)*60);
		}
		if(time>57&&time<=62) {
			x=(int)(150+(time-57)*60);
		}
		if(time>62&&time<64) {
			x+=(450-x)/20;
			y+=(300-y)/20;
		}
		if(time>=64&&time<126) {
			x=450;
			y=300;
		}
		if(time>=126&&time<128) {
			x=450;
			y=(int)(300-50*(time-126));
		}
		if(time>=128&&time<158) {
			y=300+(int)(-100*(2*Math.cos(time-128)-Math.cos(2*(time-128))));
			x=450+(int)(-100*(2*Math.sin(time-128)-Math.sin(2*(time-128))));
		}

	}

	public void drawboss(Graphics g) {
		Color c=new Color(0,0,0);
		if(life>4000)				c=new Color(102,0,153);//紫色
		else if(life<=4000&&life>3000)c=new Color(0,0,204);//深蓝
		else if(life<=3000&&life>2000)c=new Color(34,139,34);//深绿
		else if(life<=2000&&life>1000)c=new Color(255,255,0);//黄
		else if(life<=1000&&life>0)	c=new Color(163,0,0);  //红
		g.setColor(c);
		g.fillArc(x-60,y-60,120, 120, 90, (int)((double)life%1000/1000*360));
		c=new Color(255,255,255);
		g.setColor(c);
		g.fillArc(x-50,y-50,100, 100, 90, (int)((double)life%1000/1000*360));
		
		c=new Color(0,0,255);          
		g.setColor(c);  
		//g.fillOval(x-r,y-r,2*r,2*r); 
	}
	public void drawbullet(Graphics g) {
		for(int i=0;i<bullets.length;i++) {
			bullets[i].drawBullet(g);
		}
		
	}
	public void collision(int x,int y,int r) {
		double distance=Math.sqrt((Player.x-x)*(Player.x-x)+(Player.y-y)*(Player.y-y));
		if(distance<=r) {
			collision=true;
		}
	}
public void updatebullet() {
		if(time>=0&&time<30) {                              //第一条血
			double degree=Math.PI*(time*50%360)/180;
			if(life>4000) {
				for(int j=0;j<8;j++)
					for(int i=j*20;i<j*20+20;i++) {
						if(200*time-100*j>0) {
							bullets[i].x=(int)(x-Math.pow((-1),j )*((200*time-100*j)%800)*Math.sin(degree+Math.PI/10*i));
							bullets[i].y=(int)(y-((200*time-100*j)%800)*Math.cos(degree+Math.PI/10*i));
							bullets[i].setColor(25*(7-j),0,25*j);
							collision(bullets[i].x,bullets[i].y,bullets[i].r);
						  	if(collision==true)
									Player.islive=false;
					}
				}
			}
			else {
				life=4000;
				time=30;
			}
		}
		if(time>=30&&time<32) {          //转阶段弹幕清屏
			life=4000;
			while(time<=30.5) {
				int j=(int)((time-30)*2000-50);
				for(int i=0;i<300;i++) {
					if(bullets[i].y-j<1) {
						bullets[i].x=-50;
						bullets[i].y=-50;
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
				}
				break;
			}
		}
		if(time>=32&&time<62) {                   //第二条血
			if(life>3000) {
				for(int j=0;j<8;j++)
					for(int i=0;i<6;i++) {
						for(int k=0;k<4;k++) {
							if(300*(time-32)-100*j>0) {
								bullets[j*24+i*4+k].x=(int)(x-Math.pow((-1),j )*((300*(time-32)-100*j)%800)*Math.sin(Math.PI/3*i+Math.PI/20*k));
								bullets[j*24+i*4+k].y=(int)(y-((300*(time-32)-100*j)%800)*Math.cos(Math.PI/3*i+Math.PI/20*k));
								collision(bullets[j*24+i*4+k].x,bullets[j*24+i*4+k].y,bullets[j*24+i*4+k].r);
							  	if(collision==true)
										Player.islive=false;
								if(j%2==0)bullets[j*24+i*4+k].setColor(255,215,0);
								else bullets[j*24+i*4+k].setColor(138,43,226);
						}
					}
				}
			}
			else {
				life=3000;
				time=62;
			}
		}
		if(time>=62&&time<64) {          //转阶段弹幕清屏
			life=3000;
			while(time<=62.5) {
				int j=(int)((time-62)*2000-50);
				for(int i=0;i<300;i++) {
					if(bullets[i].y-j<1) {
						bullets[i].x=-50;
						bullets[i].y=-50;
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
				}
				break;
			}	
		}
		if(time>=64&&time<94) { //第三条血
			double t=(time-64)%6;
			if(life>2000) {
				if(t>=0&&t<1) {                   
					for(int i=0;i<5;i++) {
						for(int j=0;j<20;j++) {
							bullets[20*i+j].setColor(255, 0, 0);
							bullets[20*i+j].x=x+(int)(((180+180*i)-450)*t);
							if(bullets[20*i+j].y>=300&&bullets[20*i+j].y<=900) {
								bullets[20*i+j].y=y+(int)(600*t);
							}
							else bullets[20*i+j].y=y;
							collision(bullets[20*i+j].x,bullets[20*i+j].y,bullets[20*i+j].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
				if(t>=1&&t<3) {
					for(int i=0;i<5;i++) {
						for(int j=0;j<20;j++) {
							if(bullets[20*i+j].y>=-50) {
								bullets[20*i+j].y=(int)(900-650*(t-1)+20*j);
							}	
							else bullets[20*i+j].y=-50;
							collision(bullets[20*i+j].x,bullets[20*i+j].y,bullets[20*i+j].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
				if(t>=3&&t<4) {
					for(int i=0;i<5;i++) {
						for(int j=0;j<20;j++) {
							bullets[20*i+j].setColor(255, 0, 0);
							bullets[20*i+j].x=x+(int)(((180*i)-450)*(t-3));
							if(bullets[20*i+j].y>=300&&bullets[20*i+j].y<=900) {
								bullets[20*i+j].y=y+(int)(600*(t-3));
							}
							else bullets[20*i+j].y=y;
							collision(bullets[20*i+j].x,bullets[20*i+j].y,bullets[20*i+j].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
				if(t>=4&&t<6) {    
					for(int i=0;i<5;i++) {
						for(int j=0;j<20;j++) {
							if(bullets[20*i+j].y>=-50) {
								bullets[20*i+j].y=(int)(900-650*(t-4)+20*j);
							}
							else bullets[20*i+j].y=-50;
							collision(bullets[20*i+j].x,bullets[20*i+j].y,bullets[20*i+j].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
				double degree=Math.PI*((time-64)*50%360)/180;
				for(int j=0;j<8;j++) {
					for(int i=j*20;i<j*20+20;i++) {
						if(200*(time-64)-100*j>0) {
							bullets[i+100].x=(int)(x-Math.pow((-1),j )*((200*(time-64)-100*j)%800)*Math.sin(degree+Math.PI/20*i));
							bullets[i+100].y=(int)(y-((200*(time-64)-100*j)%800)*Math.cos(degree+Math.PI/20*i));
							bullets[i+100].setColor(102,0,153);
							collision(bullets[i+100].x,bullets[i+100].y,bullets[i+100].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
			}
			else {
				life=2000;
				time=94;
			}
		}
		if(time>=94&&time<96) {          //转阶段弹幕清屏
			life=2000;
			while(time<=94.5) {
				int j=(int)((time-94)*2000-50);
				for(int i=0;i<300;i++) {
					if(bullets[i].y-j<1) {
						bullets[i].x=-50;
						bullets[i].y=-50;
					}
				}
				break;
			}
		}
		if(time>=96&&time<126) {           //第四条血
			if(life>1000) {
				double t=(time-96);
				for(int j=0;j<3;j++) {
					 for(int i=0;i<50;i++){
						if(3*t-i-j>0) {	
							bullets[50*j+i].x=x+(int)((3*t-i-j)*20*Math.sin(3*t-i-j));
							bullets[50*j+i].y=y+(int)((3*t-i-j)*20*Math.cos(3*t-i-j));
							bullets[50*j+i].setColor(255, 0, 0);
							collision(bullets[50*j+i].x,bullets[50*j+i].y,bullets[50*j+i].r);
						  	if(collision==true)
									Player.islive=false;
						}
					}
				}
				for(int j=0;j<8;j++)
					for(int i=0;i<6;i++) {
						for(int k=0;k<3;k++) {
							if(300*(time-96)-100*j-66.6*k>0) {
								bullets[150+j*18+i*3+k].x=(int)(x-((300*(time-96)-100*j-66.6*k)%800)*Math.sin(Math.PI/3*i+Math.PI/9*k));
								bullets[150+j*18+i*3+k].y=(int)(y-((300*(time-96)-100*j-66.6*k)%800)*Math.cos(Math.PI/3*i+Math.PI/9*k));
								if(j%2==0)bullets[150+j*18+i*3+k].setColor(153,51,250);
								else bullets[150+j*18+i*3+k].setColor(34,139,34);
								collision(bullets[150+j*18+i*3+k].x,bullets[150+j*18+i*3+k].y,bullets[150+j*18+i*3+k].r);
							  	if(collision==true)
										Player.islive=false;
						}
					}
				}
			}
			else {
				life=1000;
				time=126;
			}
		}
		if(time>=126&&time<127) {          //转阶段弹幕清屏
			life=1000;
			while(time<=126.5) {
				int j=(int)((time-126)*2000-50);
				for(int i=0;i<300;i++) {
					if(bullets[i].y-j<1) {
						bullets[i].x=-50;
						bullets[i].y=-50;
					}
				}
				break;
			}
		}
		if(time>=127&&time<158) {           //第五条血
			if(life>0) {
				double degree=Math.PI*((time-127)*100%360)/180;
				if(time>=127&&time<128) {
					for(int i=0;i<12;i++) {
						bullets[i].x=(int)(x+(360-30*i)*(time-127)*Math.sin(Math.PI/3*i+degree*2));
						bullets[i].y=(int)(y+(360-30*i)*(time-127)*Math.cos(Math.PI/3*i+degree*2));
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
					for(int i=12;i<48;i++) {
						bullets[i].x=(int)(450+((600-10*i)*3*(128.33-time)*Math.sin(Math.PI/12*i+degree)));
						bullets[i].y=(int)(450+((600-10*i)*3*(128.33-time)*Math.cos(Math.PI/12*i+degree)));
						bullets[i].setColor(0,96,0);
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
				}
				if(time>=128&&time<158) {
					for(int i=0;i<12;i++) {
						bullets[i].x=(int)(x+(360-30*i)*Math.sin(Math.PI/3*i+degree*2));
						bullets[i].y=(int)(y+(360-30*i)*Math.cos(Math.PI/3*i+degree*2));
						bullets[i].setColor(59,0,111);
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
					for(int i=12;i<48;i++) {
						bullets[i].x=(int)(450+(600-10*i)*Math.sin(Math.PI/12*i+degree));
						bullets[i].y=(int)(450+(600-10*i)*Math.cos(Math.PI/12*i+degree));
						bullets[i].setColor(0,96,0);
						collision(bullets[i].x,bullets[i].y,bullets[i].r);
					  	if(collision==true)
								Player.islive=false;
					}
				}
			}
		}
	}
}

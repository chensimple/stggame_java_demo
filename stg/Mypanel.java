package stg;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
@SuppressWarnings("serial")
class Mypanel extends JPanel implements Runnable,KeyListener{
	private boolean VAC=true;//无敌模式标志
	JButton start=new JButton("开始游戏");
	JButton Invincible=new JButton("无敌难度");
	JButton exit=new JButton("退出");
	private Thread thread; 
	public  Player player;  
	private BufferedImage buffer_image;
	private Graphics g;
    public static ArrayList<Bullet> bullets;  
    private Boss boss;
    //控制背景移动
    private int totle_time;
    //计时器
    public Timer timer;
    private Date time1;
    private long game_time;
    static private Image backgroung=Toolkit.getDefaultToolkit().getImage(("src/image/world1.png"));
    static private Image pls=Toolkit.getDefaultToolkit().getImage(("src/image/pls1.png"));
    static private Image bs1=Toolkit.getDefaultToolkit().getImage(("src/image/bs1.png"));
	static private Image bs2=Toolkit.getDefaultToolkit().getImage(("src/image/bs2.png"));
	public Mypanel() {
		setSize(900,900);
		setFocusable(true);
		requestFocus();
		setLayout(null);
		start.setBounds(400, 300, 150, 50);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VAC=false;
				thread.start();
				removeAll();
			}
		});
		add(start);
		Invincible.setBounds(400, 400, 150, 50);
		Invincible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VAC=true;
				thread.start();
				removeAll();
			}
		});
		add(Invincible);
		exit.setBounds(400, 500, 150, 50);
		exit.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		         System.exit(0);
		        }
		});
		add(exit);
	}
	public void addNotify() {  
        super.addNotify();  
        if (thread==null) {  
            thread=new Thread(this); 
        } 
        addKeyListener(this);  
    } 
	private void init() {
		player=new Player();
		buffer_image = new BufferedImage(900,900, BufferedImage.TYPE_INT_RGB);   //创建一个后备缓冲  
        g=buffer_image.getGraphics();
		bullets = new ArrayList<Bullet>();
		boss=new Boss();
		totle_time=0; 
		time1=new Date(0);
		timer=new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Date time2=new Date(time1.getTime()+1000);
                time1=time2;
                game_time=time2.getTime()/1000;
            }
		});
		timer.start();
	}
	public void run() {
		init();
		while(true) {
			update();
			render();
		    draw();
		    try {  
	               Thread.sleep(10);   
	           }
		    catch (InterruptedException e) {  
	               e.printStackTrace(); 
	               break;
	               } 
		   }  
		JOptionPane.showMessageDialog(null, "游戏结束，点击确定重新开始");  
		run();
	}	
	private void update() {
		if(Player.islive==true)
			player.UpdatePlane();
		else
		{
			if(VAC==true)
				player.UpdatePlane();
			else
				timer.stop();
				thread.interrupt();
		}
		for (int i = 0; i < bullets.size(); i++) {  
            boolean isDraw= bullets.get(i).UpdateBullet();  
            if (!isDraw) {  
                bullets.remove(i);  
                i--; 
            } 
        }
		if(Boss.life>0) {
		boss.update();
		boss.updatebullet();
		}
		else
		{
			boss=null;
			JOptionPane.showMessageDialog(null, "恭喜你过关了，点击确定退出游戏");  
			System.exit(0);
		}
	}
	private void render() {
		//background
		if(backgroung==null)//使用默认背景绘制
			g.drawRect(-8, 0, 908, 900);
		else
			drawbackground(g);
		g.drawImage(pls, Player.x-20, Player.y-30, 40, 60, this);
		//boss
		if(Boss.time<94) {
		boss.drawboss(g);
		g.drawImage(bs1, Boss.x-40, Boss.y-50, 90, 90, this);
		boss.drawbullet(g);
		}
		else if(Boss.time>=94)
		{
			boss.drawboss(g);
			g.drawImage(bs2, Boss.x-40, Boss.y-50, 90, 90, this);
			boss.drawbullet(g);
			}
		//player
		player.DrawPlane(g);
		for (int i = 0; i < bullets.size(); i++)  {
            bullets.get(i).DrawBullet(g); }
		
	}
	private void drawbackground(Graphics g) {
		  totle_time++;
		  if(totle_time>900)
			  totle_time-=900;
		  g.drawImage(backgroung, -8, -900+totle_time, 908,900,this);
		  g.drawImage(backgroung, -8, 0+totle_time, 908,900,this);
		  g.setColor(new Color(255,255,255));//调整字体颜色
		  g.setFont(new Font("Tahoma", Font.BOLD, 30));//字体
		  g.drawString(""+game_time+"s", 800, 800);//计时器
	}
	private void draw() {  
        // 主缓冲   
        Graphics g2=getGraphics();  
        if (g2==null) {  
            System.out.println("G2 is NULL");  
            System.exit(0);  
        }  
        // 把后备缓冲绘制到主缓冲 实现平滑的动态效果  
        g2.drawImage(buffer_image, 0, 0, null);  
        g2.dispose();  
    }
	@Override
	public void keyTyped(KeyEvent e) {
		//no operation
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();  
        player.keyPressed(keyCode);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();  
        player.keyReleased(keyCode);
	}
}
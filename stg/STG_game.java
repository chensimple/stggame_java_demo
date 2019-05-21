package stg;
import javax.swing.*;
@SuppressWarnings("serial")
public class STG_game extends JFrame{
	public STG_game(){
		this.setTitle("东方STG");
		this.setResizable(false);  //设置界面大小可由用户调整
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭
		this.setSize(900,900);  //初始大小
		this.setLocationRelativeTo(null);  //窗口将置于屏幕的中央
		this.setVisible(true);
		this.add(new Mypanel());
		}	
	
	public static void main(String[] args){ 
		new STG_game();
	}
}












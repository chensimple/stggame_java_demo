package stg;
import javax.swing.*;
@SuppressWarnings("serial")
public class STG_game extends JFrame{
	public STG_game(){
		this.setTitle("����STG");
		this.setResizable(false);  //���ý����С�����û�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�ر�
		this.setSize(900,900);  //��ʼ��С
		this.setLocationRelativeTo(null);  //���ڽ�������Ļ������
		this.setVisible(true);
		this.add(new Mypanel());
		}	
	
	public static void main(String[] args){ 
		new STG_game();
	}
}












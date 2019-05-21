package stg;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
@SuppressWarnings("serial")
public class Time_jishi extends JFrame{
    private JLabel lab= new JLabel();
    public Timer timer;
    Date time1=new Date(0);
    public Time_jishi() {
        timer=new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date time2 = new Date(time1.getTime() + 1000);
                time1=time2;
                long num=time2.getTime()/1000;
                lab.setText(num+"");
            }
        });
        timer.start();
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.add(lab);
        this.setSize(300, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(lab);
    }
    public static void main(String[] args) {
        new Time_jishi();
    }
}
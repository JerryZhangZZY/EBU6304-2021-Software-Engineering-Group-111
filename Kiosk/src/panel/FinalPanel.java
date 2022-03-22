package panel;

import javax.swing.*;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * This class can use the final panel.
 *
 * @author Wang Chenyu
 * @date 2022/3/21
 * @version 1.0
 */
public class FinalPanel extends JPanel {
    JLabel headline =  new JLabel();
    JButton exit_begin = new JButton();
    JButton exit_system = new JButton();
    ImageIcon icon1_bcak = new ImageIcon("KIosk/icons/initial.png");
    Image img_bcak = icon1_bcak.getImage();
    Image newimg_bcak= img_bcak.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_back = new ImageIcon(newimg_bcak);
    ImageIcon icon1_exit = new ImageIcon("Kiosk/icons/final_exit.png");
    Image img_exit = icon1_exit.getImage();
    Image newimg_exit= img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_exit = new ImageIcon(newimg_exit);
    public FinalPanel(){
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1920, 980);
        //headline
        headline.setText("Printing your boarding pass, baggage tags and ticket ...");
        headline.setHorizontalAlignment(JLabel.CENTER);
        headline.setFont(new Font("Arial Black", Font.PLAIN, 43));
        headline.setBounds(323, 150, 1284, 203);
        add(headline);
        exit_begin.setText("continue chek-in");
        exit_begin.setFont(new Font("Arial", Font.PLAIN, 26));
        exit_begin.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_begin.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_begin.setBounds(507, 793, 263, 150);
        exit_begin.setForeground(Color.GREEN);
        exit_begin.setBackground(Color.WHITE);
        exit_begin.setContentAreaFilled(false);
        exit_begin.setBorderPainted(false);
        add(exit_begin);
        exit_begin.setIcon(icon_back);
        exit_system.setText("Exit");
        exit_system.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_system.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_system.setFont(new Font("Arial", Font.PLAIN, 26));
        exit_system.setBounds(1165, 803, 263, 120);
        exit_system.setForeground(Color.RED);
        exit_system.setBackground(Color.WHITE);
        exit_system.setContentAreaFilled(false);
        exit_system.setBorderPainted(false);
        add(exit_system);
        exit_system.setIcon(icon_exit);
        exit_begin.setVisible(false);
        exit_system.setVisible(false);
        timer.schedule(new MyTask1(),3000);
        timer1.schedule(new MyTask2(),4000);
        timer2.schedule(new MyTask3(),10000);
    }
    class MyTask1 extends TimerTask {
        public void run() {
            headline.setText("Have a good trip! ");
        }

    }
    class MyTask2 extends TimerTask {
        public void run() {
            exit_begin.setVisible(true);
            exit_system.setVisible(true);
        }

    }
    class MyTask3 extends TimerTask {
        public void run() {
            //auto exit
        }

    }
}

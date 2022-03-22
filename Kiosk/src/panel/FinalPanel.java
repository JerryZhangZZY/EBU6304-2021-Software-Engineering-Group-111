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
    JLabel lblNewLabel =  new JLabel();
    JButton exit_begin = new JButton();
    JButton exit_system = new JButton();
    ImageIcon icon1_bcak = new ImageIcon("Kiosk/icons/back.png");
    Image img_bcak = icon1_bcak.getImage();
    Image newimg_bcak= img_bcak.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_bcak = new ImageIcon(newimg_bcak);
    ImageIcon icon1_exit = new ImageIcon("Kiosk/icons/exit.png");
    Image img_exit = icon1_exit.getImage();
    Image newimg_exit= img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_exit = new ImageIcon(newimg_exit);
    public FinalPanel(){
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1920, 980);

        lblNewLabel.setText("Printing your boarding pass, baggage tags and ticket ...");
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 43));
        lblNewLabel.setBounds(323, 150, 1284, 203);
        add(lblNewLabel);
        exit_begin.setBounds(507, 793, 263, 95);
        add(exit_begin);
        exit_begin.setIcon(icon_bcak);
        exit_system.setBounds(1165, 793, 263, 95);
        add(exit_system);
        exit_system.setIcon(icon_exit);
        exit_begin.setVisible(false);
        exit_system.setVisible(false);
        timer.schedule(new MyTask1(),3000);
        timer1.schedule(new MyTask2(),4000);
    }
    class MyTask1 extends TimerTask {
        public void run() {
            lblNewLabel.setText("Have a good trip! ");
        }

    }
    class MyTask2 extends TimerTask {
        public void run() {
            exit_system.setVisible(true);
            exit_begin.setVisible(true);
        }

    }
}

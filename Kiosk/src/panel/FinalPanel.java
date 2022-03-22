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
    JButton exit_exit = new JButton();
    public FinalPanel(){
        Timer timer = new Timer();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1920, 980);

        lblNewLabel.setText("Printing your boarding pass, baggage tags and ticket ...");
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 43));
        lblNewLabel.setBounds(323, 150, 1284, 203);
        add(lblNewLabel);
        timer.schedule(new MyTask1(),3000);
        timer.schedule(new MyTask2(),1000);
    }
    class MyTask1 extends TimerTask {
        public void run() {
            lblNewLabel.setText("Have a good trip! ");
        }

    }
    class MyTask2 extends TimerTask {
        public void run() {
            btnNewButton.setBounds(297, 693, 263, 95);
            add(btnNewButton);
            btnNewButton_1.setBounds(925, 693, 263, 95);
            add(btnNewButton_1);
        }

    }
}

package panel;

import dbWriter.StatusWriter;
import main.State;
import printer.BoardingPassPrinter;
import printer.TagPrinter;
import printer.TicketPrinter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class can use the final panel.
 *
 * @author Wang Chenyu
 * @author Zhang Zeyu
 * @author Wang Zaitian
 * @author Ni Ruijie
 * @author Liang Zhehao
 *
 * @date 2022/4/8
 * @version 2.0
 * Big bug fixed.
 *
 * @date 2022/3/27
 * @version 1.4
 * Exiting functions added: set the status of the idFlightPassenger to be true.
 *
 * @date  2022/3/25
 * @version 1.3
 * Exiting functions added
 *
 * @date 2022/3/24
 * @version 1.2
 * Appearance improvement.
 *
 * @date 2022/3/24
 * @version 1.1
 * Appearance improvement and bugs fixing.
 *
 * @date 2022/3/21
 * @version 1.0
 */
public class FinalPanel extends JPanel {
    JLabel headline =  new JLabel();
    JButton exit_begin = new JButton();
    JButton exit_system = new JButton();
    ImageIcon icon1_back = new ImageIcon("Kiosk/icons/initial.png");
    Image img_back = icon1_back.getImage();
    Image newing_back = img_back.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_back = new ImageIcon(newing_back);
    ImageIcon icon1_exit = new ImageIcon("Kiosk/icons/final_exit.png");
    Image img_exit = icon1_exit.getImage();
    Image newImg_exit = img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_exit = new ImageIcon(newImg_exit);
    public FinalPanel(){
        StatusWriter.setTrue(State.getPassengerFlight_index());
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1920, 980);
        //headline
        headline.setText("Printing your boarding pass, baggage tags and ticket...");
        headline.setHorizontalAlignment(JLabel.CENTER);
        headline.setFont(new Font("Helvetica", Font.BOLD, 50));
        headline.setForeground(Color.DARK_GRAY);
        headline.setBounds(250, 200, 1400, 203);
        add(headline);
        exit_begin.setText("Continue chek-in");
        exit_begin.setFont(new Font("Arial", Font.PLAIN, 35));
        exit_begin.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_begin.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_begin.setBounds(500, 700, 350, 120);
        exit_begin.setForeground(new Color(0, 100, 0));
        exit_begin.setBackground(Color.WHITE);
        exit_begin.setContentAreaFilled(false);
        exit_begin.setBorderPainted(false);
        exit_begin.addActionListener(e -> {
            timer2.cancel();
            State.setPc(3);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        add(exit_begin);
        exit_begin.setIcon(icon_back);
        exit_system.setText("          Exit          ");
        exit_system.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_system.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_system.setFont(new Font("Arial", Font.PLAIN, 35));
        exit_system.setBounds(1100, 700, 350, 120);
        exit_system.setForeground(Color.RED);
        exit_system.setBackground(Color.WHITE);
        exit_system.setContentAreaFilled(false);
        exit_system.setBorderPainted(false);
        exit_system.addActionListener(e -> {
            timer2.cancel();
            State.setPc(0);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        add(exit_system);
        exit_system.setIcon(icon_exit);
        exit_begin.setVisible(false);
        exit_system.setVisible(false);
        timer.schedule(new MyTask1(),3000);
        timer1.schedule(new MyTask2(),3700);
        timer2.schedule(new MyTask3(),15000);
        try {
            char columnInLetter = (char)(State.getSeatColumn()+(int)'A'-1);
            BoardingPassPrinter.creatBoardingPass(State.getPassengerFlight_index(), Integer.toString(State.getSeatRow())+columnInLetter);
            TagPrinter.creatTag(State.getPassengerFlight_index());
            TicketPrinter.creatTicket(State.getPassengerFlight_index());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    class MyTask1 extends TimerTask {
        public void run() {
            headline.setText("Have a good trip!");
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
            State.setPc(0);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        }

    }

    public JButton getExit_begin(){return exit_begin;}

    public JButton getExit_system(){return exit_system;}
}

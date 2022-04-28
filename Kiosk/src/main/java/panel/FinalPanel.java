package panel;

import BackendSystemDB.DBwrite;
import dbWriter.SeatWriter;
import dbWriter.StatusWriter;
import main.Clock;
import main.State;
import main.Theme;
import printer.BarCode_QRCodeGenerator;
import printer.BoardingPassPrinter;
import printer.TagPrinter;
import printer.TicketPrinter;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
 * @version 2.4
 * Create directory ./printerOutput if not exists
 * @date 2022/4/14
 *
 * @version 2.3
 * Appearance improvement.
 * @date 2022/4/14
 *
 * @version 2.2
 * Add a qr code links to the airline website which the flight belongs to.
 * @date 2022/4/13
 *
 * @version 2.1
 * Add confirm function
 * @date 2022/4/9
 *
 * @version 2.0
 * Big bug fixed.
 * @date 2022/4/8
 *
 * @version 1.4
 * Exiting functions added: set the status of the idFlightPassenger to be true.
 * @date 2022/3/27
 *
 * @version 1.3
 * Exiting functions added
 * @date  2022/3/25
 *
 * @version 1.2
 * Appearance improvement.
 * @date 2022/3/24
 *
 * @version 1.1
 * Appearance improvement and bugs fixing.
 * @date 2022/3/24
 *
 * @version 1.0
 * @date 2022/3/21
 */
public class FinalPanel extends JPanel {
    private JLabel qrLabel;;
    private JLabel headline = new JLabel();
    private JButton exit_begin = new JButton();
    private JButton exit_system = new JButton();
    private ImageIcon icon1_back = new ImageIcon("Kiosk/icons/initial.png");
    private Image img_back = icon1_back.getImage();
    private Image newing_back = img_back.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_back = new ImageIcon(newing_back);
    private ImageIcon icon1_exit = new ImageIcon("Kiosk/icons/final_exit.png");
    private Image img_exit = icon1_exit.getImage();
    private Image newImg_exit = img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_exit = new ImageIcon(newImg_exit);
    public FinalPanel(){
        try {
            char columnInLetter = (char)(State.getSeatColumn() + (int)'A' - 1);
            if (!Files.exists(Path.of("printerOutput"))){
                Files.createDirectory(Path.of("printerOutput"));
            }
            BarCode_QRCodeGenerator.generateQRcode(State.getPassengerFlight_index());
            BoardingPassPrinter.creatBoardingPass(State.getPassengerFlight_index(), Integer.toString(State.getSeatRow())+columnInLetter);
            TagPrinter.creatTag(State.getPassengerFlight_index());
            TicketPrinter.creatTicket(State.getPassengerFlight_index());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        confirm();
        StatusWriter.setTrue(State.getPassengerFlight_index());
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1920, 980);
        //headline
        headline.setText("Printing your boarding pass, baggage tags and ticket...");
        headline.setHorizontalAlignment(JLabel.CENTER);
        headline.setFont(new Font("Helvetica", Font.BOLD, 50));
        headline.setForeground(Theme.getMainFontColor());
        headline.setBounds(250, 150, 1400, 203);
        add(headline);

        exit_begin.setText("Continue check-in");
        exit_begin.setForeground(Theme.getMainFontColor());
        exit_begin.setFont(new Font("Arial", Font.PLAIN, 35));
        exit_begin.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_begin.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_begin.setBounds(500, 700, 350, 120);
        exit_begin.setContentAreaFilled(false);
        exit_begin.setBorderPainted(false);
        exit_begin.addActionListener(e -> {
            Clock.stopBackstageTimer();
            State.setPc(3);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        exit_begin.setIcon(icon_back);
        exit_begin.setVisible(false);
        add(exit_begin);

        exit_system.setText("          Exit          ");
        exit_system.setForeground(Theme.getMainFontColor());
        exit_system.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit_system.setHorizontalTextPosition(SwingConstants.CENTER);
        exit_system.setFont(new Font("Arial", Font.PLAIN, 35));
        exit_system.setBounds(1100, 700, 350, 120);
        exit_system.setContentAreaFilled(false);
        exit_system.setBorderPainted(false);
        exit_system.addActionListener(e -> {
            Clock.stopBackstageTimer();
            State.setPc(0);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        exit_system.setIcon(icon_exit);
        exit_system.setVisible(false);
        add(exit_system);

        qrLabel = new JLabel("Get more information on your airline website");
        qrLabel.setForeground(Theme.getMainFontColor());
        qrLabel.setBounds(700, 350, 500, 300);
        qrLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        qrLabel.setIcon(new ImageIcon("printerOutput/qrcode.jpg"));
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrLabel.setVerticalAlignment(SwingConstants.CENTER);
        qrLabel.setHorizontalTextPosition(0);
        qrLabel.setVerticalTextPosition(1);
        add(qrLabel);
        qrLabel.setVisible(false);
        Clock.setBackstageTimer(15000);
        timer.schedule(new MyTask1(),3000);
        timer1.schedule(new MyTask2(),4500);
    }
    class MyTask1 extends TimerTask {
        public void run() {
            qrLabel.setVisible(true);
            headline.setText("Have a good trip!");
        }

    }
    class MyTask2 extends TimerTask {
        public void run() {
            exit_begin.setVisible(true);
            exit_system.setVisible(true);
        }

    }

    public void confirm() {
        SeatWriter.setSeat(State.getIdFlight(), State.getSeatRow(), State.getSeatColumn());
        String[] food = {"Normal", "Vegetarian", "Halal"};
        String[] prefFood = new String[3];
        for (int i = 0; i < 3; i++) {
            if (!State.getSelectedPrefFood()[i])
                prefFood[i] = "NULL";
            else
                prefFood[i] = State.getPrefFoodName()[i];
        }
        DBwrite.changeline(State.getBookingNum(), State.getIdFlight(), "" + State.getSeatRow() + State.getColumnNum(), food[(int)State.getMeal() - 97], prefFood[0], prefFood[1], prefFood[2]);
    }

    public JButton getExit_begin(){return exit_begin;}

    public JButton getExit_system(){return exit_system;}
}
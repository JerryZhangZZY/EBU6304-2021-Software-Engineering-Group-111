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
 * @version 5.0
 * Get rid of 'cyy style'.
 * @date 2022/5/20
 *
 * @version 2.4
 * Create directory ./printer-output if not exists
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
    private JButton btnContinue = new JButton();
    private JButton btnExit = new JButton();

    public FinalPanel(){
        try {
            char columnInLetter = (char)(State.getSeatColumn() + (int)'A' - 1);
            if (!Files.exists(Path.of("printer-output"))){
                Files.createDirectory(Path.of("printer-output"));
            }
            BarCode_QRCodeGenerator.generateQRcode(State.getPassengerFlight_index());
            BoardingPassPrinter.creatBoardingPass(State.getPassengerFlight_index());
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

        btnContinue.setText("Continue check-in");
        btnContinue.setForeground(Theme.getMainFontColor());
        btnContinue.setFont(new Font("Arial", Font.PLAIN, 35));
        btnContinue.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnContinue.setHorizontalTextPosition(SwingConstants.CENTER);
        btnContinue.setBounds(500, 700, 350, 120);
        btnContinue.setContentAreaFilled(false);
        btnContinue.setBorderPainted(false);
        btnContinue.setFocusPainted(false);
        btnContinue.addActionListener(e -> {
            Clock.stopIdleTimer();
            State.setPc(3);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        btnContinue.setIcon(new ImageIcon("kiosk/icons/finalContinue.png"));
        btnContinue.setVisible(false);
        add(btnContinue);

        btnExit.setText("          Exit          ");
        btnExit.setForeground(Theme.getMainFontColor());
        btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExit.setFont(new Font("Arial", Font.PLAIN, 35));
        btnExit.setBounds(1100, 700, 350, 120);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(e -> {
            Clock.stopIdleTimer();
            State.setPc(0);
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
        });
        btnExit.setIcon(new ImageIcon("kiosk/icons/finalExit.png"));
        btnExit.setVisible(false);
        add(btnExit);

        qrLabel = new JLabel("Get more information on your airline website");
        qrLabel.setForeground(Theme.getMainFontColor());
        qrLabel.setBounds(700, 350, 500, 300);
        qrLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        qrLabel.setIcon(new ImageIcon("printer-output/qrcode.jpg"));
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrLabel.setVerticalAlignment(SwingConstants.CENTER);
        qrLabel.setHorizontalTextPosition(0);
        qrLabel.setVerticalTextPosition(1);
        add(qrLabel);
        qrLabel.setVisible(false);
        Clock.setIdleTimer(15000);
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
            btnContinue.setVisible(true);
            btnExit.setVisible(true);
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

    public JButton getBtnContinue(){return btnContinue;}

    public JButton getBtnExit(){return btnExit;}
}

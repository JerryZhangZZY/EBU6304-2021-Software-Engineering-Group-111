package panel;

import card.FlightInfoCard;
import dbReader.PassengerFlightReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * This is the panel that a passenger can select a flight to check in.
 *
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 1.0
 * @date 2022/3/24
 *
 * @version 1.1
 * @date 2022/3/27
 * Adjusted actions in mouseReleased(MouseEvent e).
 *
 * @version 1.2
 * @date 2022/3/27
 * Added judgement condition and automaticallyExit method.
 *
 * @version 1.3
 * @date 2022/3/27
 * Rename components, improve appearance and format.
 *
 * @version 2.0
 * @date 2022/4/8
 * Migrate event handlers into FlightInfoCard.
 */

public class FlightSelectionPanel extends JPanel {

    public FlightSelectionPanel() {
        String bookingNum = State.getBookingNum();
        List<String> idFlightList = PassengerFlightReader.getIdFlightByBookingNum(bookingNum);

        setBounds(new Rectangle(0, 0, 1600, 980));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        if (idFlightList.size() == 0) {
            JLabel lblNoFlight = new JLabel("There is no flight available for check-in");
            lblNoFlight.setFont(new Font("Calibre", Font.BOLD, 60));
            lblNoFlight.setForeground(Color.DARK_GRAY);
            lblNoFlight.setHorizontalAlignment(SwingConstants.CENTER);
            lblNoFlight.setBounds(200, 200, 1200, 380);
            add(lblNoFlight);
            JLabel lblAutoExit = new JLabel("Automatically exit in 3 secs...");
            lblAutoExit.setHorizontalAlignment(SwingConstants.CENTER);
            lblAutoExit.setFont(new Font("Calibre", Font.PLAIN, 30));
            lblAutoExit.setForeground(Color.GRAY);
            lblAutoExit.setBounds(400, 400, 800, 200);
            add(lblAutoExit);
            State.setPc(0);
        }

        for(int cardNum = 0; cardNum < idFlightList.size(); cardNum++) {
            FlightInfoCard flightInfoCard = new FlightInfoCard(idFlightList.get(cardNum));
            flightInfoCard.setBounds(500, 150 + 250 * cardNum, 530, 150);
            flightInfoCard.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    flightInfoCard.doPress();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    flightInfoCard.doRelease();
                }

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
            add(flightInfoCard);
        }
    }

    public static void automaticallyExit(){
        if(State.getPc() == 0) {
            try {
                sleep(3500);
            } catch (InterruptedException ignored) {}
        }
    }
}

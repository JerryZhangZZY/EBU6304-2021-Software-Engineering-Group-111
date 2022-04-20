package panel;

import card.FlightInfoCard;
import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import main.Config;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the panel that a passenger can select a flight to check in.
 *
 * @author Zhang Zeyu
 * @author Ni Ruijie
 * @author Liang Zhehao
 *
 * @version 3.0
 * Those flight info cards that are before the set time
 * will be set gray.
 * @date 2022/4/20
 *
 * @version 2.2
 * Display multiple booking numbers' flights.
 * Also set the color of checked-in flights to gray.
 * @date 2022/4/10
 *
 * @version 2.1
 * Replace automaticallyExit() with a simpler Thread.
 * @date 2022/4/8
 *
 * @version 2.0
 * Migrate event handlers into FlightInfoCard.
 * @date 2022/4/8
 *
 * @version 1.3
 * Rename components, improve appearance and format.
 * @date 2022/3/27
 *
 * @version 1.2
 * Added judgement condition and automaticallyExit method.
 * @date 2022/3/27
 *
 * @version 1.1
 * Adjusted actions in mouseReleased(MouseEvent e).
 * @date 2022/3/27
 *
 * @version 1.0
 * @date 2022/3/24
 */

public class FlightSelectionPanel extends JPanel {

    public Thread getThread() {
        return thread;
    }

    Thread thread;

    public FlightSelectionPanel() {
        List<String> bookingNumList = State.getBookingNumList();
        List<String>[] idFlightList = new ArrayList[2];
        List<Boolean>[] unavailableList = new ArrayList[2];
        List<Boolean>[] statusList = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            if (i < bookingNumList.size()) {
                idFlightList[i] = PassengerFlightReader.getIdFlightByBookingNum(bookingNumList.get(i));
                statusList[i] = PassengerFlightReader.getStatusByBookingNum(bookingNumList.get(i));
                if (Boolean.parseBoolean(Config.readConfig("enableCheckInLeadingTime")))
                    unavailableList[i] = getUnavailableList(idFlightList[i]);
                else {
                    unavailableList[i] = new ArrayList<>();
                    for (int j = 0; j < idFlightList[i].size(); j++)
                        unavailableList[i].add(false);
                }
            }
            else {
                idFlightList[i] = new ArrayList<>();
                statusList[i] = new ArrayList<>();
                unavailableList[i] = new ArrayList<>();
            }
        }

        setBounds(0, 0, 1600, 980);
        setBackground(new Color(244, 244, 244));
        setLayout(null);

        if (hasNoMore(statusList)) {
            JLabel lblNoFlight = new JLabel("All flights you booked are checked-in!");
            lblNoFlight.setFont(new Font("Calibri", Font.BOLD, 60));
            lblNoFlight.setForeground(Color.DARK_GRAY);
            lblNoFlight.setHorizontalAlignment(SwingConstants.CENTER);
            lblNoFlight.setBounds(200, 200, 1200, 380);
            add(lblNoFlight);
            JLabel lblAutoExit = new JLabel("Automatically exit in 3 secs...");
            lblAutoExit.setHorizontalAlignment(SwingConstants.CENTER);
            lblAutoExit.setFont(new Font("Calibri", Font.PLAIN, 30));
            lblAutoExit.setForeground(Color.GRAY);
            lblAutoExit.setBounds(400, 400, 800, 200);
            add(lblAutoExit);

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException ignored) {}
                    State.setPc(0);
                }
            });
            thread.start();
        }

        else {
            int x = 500;
            if (bookingNumList.size() == 2) {
                x = 200;
                JLabel vertical = new JLabel();
                vertical.setBounds(800, 40, 5, 800);
                vertical.setOpaque(true);
                vertical.setBackground(new Color(11, 89, 167));
                add(vertical);
            }
            for (int column = 0; column < bookingNumList.size(); column++) {
                JLabel lblBookingNum = new JLabel(bookingNumList.get(column));
                lblBookingNum.setBounds(x + 670 * column, 150, 530, 60);
                lblBookingNum.setHorizontalAlignment(SwingConstants.CENTER);
                lblBookingNum.setForeground(Color.DARK_GRAY);
                lblBookingNum.setFont(new Font("Arial", Font.BOLD, 50));
                add(lblBookingNum);
                for (int cardNum = 0; cardNum < idFlightList[column].size(); cardNum++) {
                    FlightInfoCard flightInfoCard = new FlightInfoCard(idFlightList[column].get(cardNum), column);
                    flightInfoCard.setBounds(x + 670 * column, 250 + 250 * cardNum, 530, 150);
                    if (unavailableList[column].get(cardNum))
                        flightInfoCard.setGray(false);
                    else if (statusList[column].get(cardNum))
                        flightInfoCard.setGray(true);
                    else {
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
                    }
                    add(flightInfoCard);
                }
            }
        }
    }



    public boolean hasNoMore(List<Boolean>[] statusList) {
        for (int i = 0; i < 2; i++) {
            for (Boolean status : statusList[i]) {
                if (!status)
                    return false;
            }
        }
        return true;
    }

    public boolean isUnavailable(String idFlight) {
        long departureTimeInMs = FlightReader.getDepartureDateTime(FlightReader.indexOf(idFlight)).getTime();
        long currentTimeInMs = new Date().getTime();
        long startCheckInTime = departureTimeInMs - (Long.parseLong(Config.readConfig("startCheckInLeadingTime")) * 3600000L);
        long stopCheckInTime = departureTimeInMs - (Long.parseLong(Config.readConfig("stopCheckInLeadingTime")) * 60000L);
        return currentTimeInMs < startCheckInTime || currentTimeInMs >= stopCheckInTime;
    }

    public List<Boolean> getUnavailableList(List<String> idFlightList) {
        List<Boolean> unavailableList = new ArrayList<>();
        for (String idFlight : idFlightList) {
            unavailableList.add(isUnavailable(idFlight));
        }
        return unavailableList;
    }
}

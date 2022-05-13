package panel;

import card.FlightInfoCard;
import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import main.Clock;
import main.Config;
import main.State;
import main.Theme;

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
 * @version 3.3
 * Use Theme generated color.
 * @date 2022/4/26
 *
 * @version 3.2
 * Added function: Set the timer when there are flights haven't been checked in.
 *                 And reset the timer when recall the panel.
 * @date 2022/4/26
 *
 * @version 3.1
 * Integrate doPress() and doRelease() as doClick()
 * and improved appearance.
 * @date 2022/4/25
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
        setBackground(Theme.getBackgroundColor());
        setLayout(null);

        if (hasNoMore(statusList)) {
            JLabel lblNoFlight = new JLabel("All flights you booked are checked-in!");
            lblNoFlight.setFont(new Font("Calibri", Font.BOLD, 60));
            lblNoFlight.setForeground(Theme.getMainFontColor());
            lblNoFlight.setHorizontalAlignment(SwingConstants.CENTER);
            lblNoFlight.setBounds(200, 200, 1200, 380);
            add(lblNoFlight);
            JLabel lblAutoExit = new JLabel("Automatically exit in 3 secs...");
            lblAutoExit.setHorizontalAlignment(SwingConstants.CENTER);
            lblAutoExit.setFont(new Font("Calibri", Font.PLAIN, 30));
            lblAutoExit.setForeground(Theme.getSecondaryFontColor());
            lblAutoExit.setBounds(400, 400, 800, 200);
            add(lblAutoExit);

            Clock.setIdleTimer(3500);
        }

        else {
            Clock.setCheckinTimer();
            int x = 500;
            if (bookingNumList.size() == 2) {
                x = 200;
                JLabel vertical = new JLabel();
                vertical.setBounds(800, 40, 5, 800);
                vertical.setOpaque(true);
                vertical.setBackground(Theme.getThemeColor());
                add(vertical);
            }
            for (int column = 0; column < bookingNumList.size(); column++) {
                JLabel lblBookingNum = new JLabel(bookingNumList.get(column));
                lblBookingNum.setBounds(x + 670 * column, 150, 530, 60);
                lblBookingNum.setHorizontalAlignment(SwingConstants.CENTER);
                lblBookingNum.setForeground(Theme.getMainFontColor());
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
                            public void mouseClicked(MouseEvent e) {
                                flightInfoCard.doClick();
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {
                                flightInfoCard.setBackground(Theme.getCardUnavailableColor());
                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                flightInfoCard.setBackground(Theme.getCardColor());
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

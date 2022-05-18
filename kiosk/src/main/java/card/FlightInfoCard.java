package card;

import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * This class can return a panel of a flight info card.
 *
 * @author Zhang Zeyu
 * @author zaitian
 *
 * @version 5.0
 * add time span
 * @date 5/18
 *
 * @version 4.0
 * Optimize text makeup.
 *
 * @version 3.2
 * Get card unavailable color from Theme
 * and improve GUI appearance.
 *
 * @version 3.1
 * Integrate doPress() and doRelease() as doClick().
 * @date 2022/4/25
 *
 * @version 3.0
 * Add unavailable label.
 * @date 2022/4/20
 *
 * @version 2.1
 * Add setGrey() to make this card looks unavailable.
 * @date 2022/4/10
 *
 * @version 2.0
 * Write own doPress() and doRelease()
 * and add instance variable idFlight.
 * @date 2022/4/8
 *
 * @version 1.1
 * Now accepts only idFlight one param.
 * @date 2022/3/24
 *
 * @version 1.0
 * @date 2022/3/19
 */

public class FlightInfoCard extends JPanel {

    String idFlight;
    int column;
    JLabel lblIdFlight = new JLabel();
    JLabel lblDate = new JLabel();
    JLabel lblDepartureTime = new JLabel();
    JLabel lblArrivalTime = new JLabel();
    JLabel lblDeparture = new JLabel();
    JLabel lblArrival = new JLabel();
    JLabel lblArrow = new JLabel("--------->");
    JLabel lblGray = new JLabel();
    JLabel lblTimeSpan = new JLabel();

    public FlightInfoCard(String idFlight, int column) {
        this.idFlight = idFlight;
        this.column = column;
        String date = FlightReader.getDepartureDate(FlightReader.indexOf(idFlight));
        String departureTime = FlightReader.getDepartureTime(FlightReader.indexOf(idFlight));
        String arrivalTime = FlightReader.getArrivalTime(FlightReader.indexOf(idFlight));
        String departure = FlightReader.getDeparture(FlightReader.indexOf(idFlight));
        String arrival = FlightReader.getArrival(FlightReader.indexOf(idFlight));

        String timeSpan, arrivalDate;
        arrivalDate = FlightReader.getArrivalDate(FlightReader.indexOf(idFlight));
        Period period = Period.between(LocalDate.parse(arrivalDate), LocalDate.parse(date));
        timeSpan = "+ " + Math.abs(period.getDays()) + " day(s)";
        lblTimeSpan.setText(timeSpan);

        setBackground(Theme.getCardColor());
        setLayout(null);
        setSize(530,150);

        lblIdFlight.setFont(new Font("Arial", Font.PLAIN, 25));
        lblIdFlight.setForeground(Theme.getMainFontColor());
        lblIdFlight.setHorizontalAlignment(SwingConstants.LEFT);
        lblIdFlight.setBounds(20, 15, 200, 35);
        lblIdFlight.setText(idFlight);
        add(lblIdFlight);

        lblDate.setFont(new Font("Arial", Font.PLAIN, 25));
        lblDate.setForeground(Theme.getTertiaryFontColor());
        lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDate.setBounds(310, 15, 200, 35);
        lblDate.setText(date);
        add(lblDate);

        lblDepartureTime.setForeground(Theme.getMainFontColor());
        lblDepartureTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));
        lblDepartureTime.setBounds(20, 57, 135, 35);
        lblDepartureTime.setText(departureTime);
        add(lblDepartureTime);

        lblArrivalTime.setForeground(Theme.getMainFontColor());
        lblArrivalTime.setHorizontalAlignment(SwingConstants.TRAILING);
        lblArrivalTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));
        lblArrivalTime.setBounds(375, 57, 135, 35);
        lblArrivalTime.setText(arrivalTime);
        add(lblArrivalTime);

        lblDeparture.setForeground(Theme.getMainFontColor());
        lblDeparture.setFont(new Font("Arial", Font.PLAIN, 25));
        lblDeparture.setBounds(20, 102, 200, 35);
        lblDeparture.setText(departure);
        add(lblDeparture);

        lblArrival.setHorizontalAlignment(SwingConstants.TRAILING);
        lblArrival.setForeground(Theme.getMainFontColor());
        lblArrival.setFont(new Font("Arial", Font.PLAIN, 25));
        lblArrival.setBounds(310, 102, 200, 35);
        lblArrival.setText(arrival);
        add(lblArrival);

        lblArrow.setForeground(Theme.getTertiaryFontColor());
        lblArrow.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblArrow.setBounds(194, 57, 171, 35);
        add(lblArrow);
    }

    /**
     * Invoke this method to make this card looks unavailable.
     *
     * @param type true: checked-in; false: unavailable
     */
    public void setGray(boolean type) {
        setBackground(Theme.getCardUnavailableColor());
        lblIdFlight.setForeground(Theme.getTertiaryFontColor());
        lblDepartureTime.setForeground(Theme.getTertiaryFontColor());
        lblArrivalTime.setForeground(Theme.getTertiaryFontColor());
        lblDeparture.setForeground(Theme.getTertiaryFontColor());
        lblArrival.setForeground(Theme.getTertiaryFontColor());
        if (type) {
            lblGray.setText("checked-in");
            lblGray.setForeground(new Color(60,179,113));
        }
        else {
            lblGray.setText("unavailable");
            lblGray.setForeground(new Color(205,92,92));
        }
        lblGray.setBounds(190, 120, 150, 30);
        lblGray.setHorizontalAlignment(SwingConstants.CENTER);
        lblGray.setFont(new Font("Calibri", Font.BOLD, 30));
        add(lblGray);
    }

    public void doClick() {
        State.setIdFlight(idFlight);
        State.setBookingNum(State.getBookingNumList().get(column));
        State.setPassengerFlight_index(PassengerFlightReader.indexOf(State.getBookingNum(), idFlight));
        State.setPc(4);
    }
}
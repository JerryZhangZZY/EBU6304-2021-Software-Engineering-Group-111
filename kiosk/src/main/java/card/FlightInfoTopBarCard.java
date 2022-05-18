package card;

import dbReader.FlightReader;
import main.Theme;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * This class can return a panel of flight info top bar card.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 5.0
 * Modify Layout
 * Appearance improvement
 * @date 2022/5/18
 *
 * @version 1.2
 * Labels relocation.
 * @date 2022/3/29
 *
 * @version 1.1
 * Panel size changed.
 * @date 2022/3/28
 *
 * @version 1.0
 * @date 2022/3/24
 */

public class FlightInfoTopBarCard extends JPanel {

    public FlightInfoTopBarCard(String idFlight) {
        String date = FlightReader.getDepartureDate(FlightReader.indexOf(idFlight));
        String departureTime = FlightReader.getDepartureTime(FlightReader.indexOf(idFlight));
        String arrivalTime = FlightReader.getArrivalTime(FlightReader.indexOf(idFlight));
        String departure = FlightReader.getDeparture(FlightReader.indexOf(idFlight));
        String arrival = FlightReader.getArrival(FlightReader.indexOf(idFlight));
        setBackground(Theme.getCardColor());
        setLayout(null);
        setSize(1560,180);

        JLabel lblTopLine = new JLabel();
        lblTopLine.setBounds(0, 0, 1560, 10);
        lblTopLine.setOpaque(true);
        lblTopLine.setBackground(Theme.getThemeColor());
        add(lblTopLine);

        JLabel lblIdFlight = new JLabel(idFlight);
        lblIdFlight.setForeground(Theme.getSecondaryFontColor());
        lblIdFlight.setFont(new Font("Arial", Font.PLAIN, 55));
        lblIdFlight.setBounds(1204, 60, 237, 70);
        add(lblIdFlight);

        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Arial", Font.PLAIN, 45));
        lblDate.setForeground(Theme.getTertiaryFontColor());
        lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDate.setBounds(65, 60, 274, 70);
        lblDate.setText(date);
        add(lblDate);

        JLabel lblDepartureTime = new JLabel();
        lblDepartureTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartureTime.setForeground(Theme.getMainFontColor());
        lblDepartureTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        lblDepartureTime.setBounds(428, 45, 200, 46);
        lblDepartureTime.setText(departureTime);
        add(lblDepartureTime);

        JLabel lblArrivalTime = new JLabel();
        lblArrivalTime.setForeground(Theme.getMainFontColor());
        lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrivalTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        lblArrivalTime.setBounds(885, 45, 184, 46);
        lblArrivalTime.setText(arrivalTime);
        add(lblArrivalTime);

        JLabel lblDeparture = new JLabel();
        lblDeparture.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeparture.setForeground(Theme.getMainFontColor());
        lblDeparture.setFont(new Font("Arial", Font.PLAIN, 30));
        lblDeparture.setBounds(428, 106, 200, 46);
        lblDeparture.setText(departure);
        add(lblDeparture);

        JLabel lblArrival = new JLabel();
        lblArrival.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrival.setForeground(Theme.getMainFontColor());
        lblArrival.setFont(new Font("Arial", Font.PLAIN, 30));
        lblArrival.setBounds(877, 106, 200, 46);
        lblArrival.setText(arrival);
        add(lblArrival);

        String timeSpan, arrivalDate;
        arrivalDate = FlightReader.getArrivalDate(FlightReader.indexOf(idFlight));
        Period period = Period.between(LocalDate.parse(arrivalDate), LocalDate.parse(date));
        int spanDays = Math.abs(period.getDays());
        timeSpan = spanDays == 0 ? null : ("+" + spanDays + "day" + (spanDays > 1 ? "s" : ""));

        JLabel lblTimeSpan = new JLabel();
        lblTimeSpan.setText(timeSpan);
        lblTimeSpan.setBounds(710, 65, 90, 25);
        lblTimeSpan.setFont(new Font("Arial", Font.PLAIN, 25));
        lblTimeSpan.setForeground(Theme.getSecondaryFontColor());
        lblTimeSpan.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTimeSpan);

        JLabel lblArrow = new JLabel(">—————>");
        lblArrow.setForeground(Theme.getTertiaryFontColor());
        lblArrow.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrow.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblArrow.setBounds(649, 73, 219, 34);
        add(lblArrow);
    }
}

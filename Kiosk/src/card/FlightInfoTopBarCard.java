package card;

import dbReader.FlightReader;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of flight info top bar card.
 *
 * @author Zhang Zeyu
 *
 * @version 1.0
 * @date 2022/3/24
 *
 * @version 1.1
 * Panel size changed.
 * @date 2022/3/28
 *
 * @version 1.2
 * Labels relocation.
 * @date 2022/3/29
 */

public class FlightInfoTopBarCard extends JPanel {

    public FlightInfoTopBarCard(String idFlight) {
        String date = FlightReader.getDate(FlightReader.indexOf(idFlight));
        String departureTime = FlightReader.getDepartureTime(FlightReader.indexOf(idFlight));
        String arrivalTime = FlightReader.getArrivalTime(FlightReader.indexOf(idFlight));
        String departure = FlightReader.getDeparture(FlightReader.indexOf(idFlight));
        String arrival = FlightReader.getArrival(FlightReader.indexOf(idFlight));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1580,180);

        JLabel lblIdFlight = new JLabel(idFlight);
        lblIdFlight.setForeground(Color.DARK_GRAY);
        lblIdFlight.setFont(new Font("Arial", Font.PLAIN, 55));
        lblIdFlight.setBounds(100, 55, 237, 70);
        add(lblIdFlight);

        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Arial", Font.PLAIN, 45));
        lblDate.setForeground(Color.DARK_GRAY);
        lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDate.setBounds(1194, 55, 274, 70);
        lblDate.setText(date);
        add(lblDate);

        JLabel lblDepartureTime = new JLabel();
        lblDepartureTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartureTime.setForeground(Color.DARK_GRAY);
        lblDepartureTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        lblDepartureTime.setBounds(428, 40, 200, 46);
        lblDepartureTime.setText(departureTime);
        add(lblDepartureTime);

        JLabel lblArrivalTime = new JLabel();
        lblArrivalTime.setForeground(Color.DARK_GRAY);
        lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrivalTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        lblArrivalTime.setBounds(885, 40, 184, 46);
        lblArrivalTime.setText(arrivalTime);
        add(lblArrivalTime);

        JLabel lblDeparture = new JLabel();
        lblDeparture.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeparture.setForeground(Color.DARK_GRAY);
        lblDeparture.setFont(new Font("Arial", Font.PLAIN, 30));
        lblDeparture.setBounds(428, 101, 200, 46);
        lblDeparture.setText(departure);
        add(lblDeparture);

        JLabel lblArrival = new JLabel();
        lblArrival.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrival.setForeground(Color.DARK_GRAY);
        lblArrival.setFont(new Font("Arial", Font.PLAIN, 30));
        lblArrival.setBounds(877, 101, 200, 46);
        lblArrival.setText(arrival);
        add(lblArrival);

        JLabel lblArrow = new JLabel("--------->");
        lblArrow.setForeground(Color.LIGHT_GRAY);
        lblArrow.setFont(new Font("Tahoma", Font.BOLD, 45));
        lblArrow.setBounds(649, 68, 219, 34);
        add(lblArrow);
    }
}

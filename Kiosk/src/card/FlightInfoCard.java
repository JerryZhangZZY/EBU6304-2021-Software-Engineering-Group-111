package card;

import dbReader.FlightReader;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of a flight info card.
 *
 * @author Zhang Zeyu
 * @date 2022/3/19
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.1
 * Now accepts only idFlight one param.
 */

public class FlightInfoCard extends JPanel {

    public FlightInfoCard(String idFlight) {
        String date = FlightReader.getDate(FlightReader.indexOf(idFlight));
        String departureTime = FlightReader.getDepartureTime(FlightReader.indexOf(idFlight));
        String arrivalTime = FlightReader.getArrivalTime(FlightReader.indexOf(idFlight));
        String departure = FlightReader.getDeparture(FlightReader.indexOf(idFlight));
        String arrival = FlightReader.getArrival(FlightReader.indexOf(idFlight));

        //setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(530,150);

        JLabel lblIdFlight = new JLabel();
        lblIdFlight.setFont(new Font("Arial", Font.PLAIN, 25));
        lblIdFlight.setForeground(Color.DARK_GRAY);
        lblIdFlight.setHorizontalAlignment(SwingConstants.LEFT);
        lblIdFlight.setBounds(20, 10, 200, 34);
        lblIdFlight.setText(idFlight);
        add(lblIdFlight);

        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Arial", Font.PLAIN, 25));
        lblDate.setForeground(Color.LIGHT_GRAY);
        lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDate.setBounds(310, 10, 200, 34);
        lblDate.setText(date);
        add(lblDate);

        JLabel lblDepartureTime = new JLabel();
        lblDepartureTime.setForeground(Color.DARK_GRAY);
        lblDepartureTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));
        lblDepartureTime.setBounds(20, 54, 135, 35);
        lblDepartureTime.setText(departureTime);
        add(lblDepartureTime);

        JLabel lblArrivalTime = new JLabel();
        lblArrivalTime.setForeground(Color.DARK_GRAY);
        lblArrivalTime.setHorizontalAlignment(SwingConstants.TRAILING);
        lblArrivalTime.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));
        lblArrivalTime.setBounds(375, 54, 135, 35);
        lblArrivalTime.setText(arrivalTime);
        add(lblArrivalTime);

        JLabel lblDeparture = new JLabel();
        lblDeparture.setForeground(Color.DARK_GRAY);
        lblDeparture.setFont(new Font("Arial", Font.PLAIN, 25));
        lblDeparture.setBounds(20, 95, 200, 30);
        lblDeparture.setText(departure);
        add(lblDeparture);

        JLabel lblArrival = new JLabel();
        lblArrival.setHorizontalAlignment(SwingConstants.TRAILING);
        lblArrival.setForeground(Color.DARK_GRAY);
        lblArrival.setFont(new Font("Arial", Font.PLAIN, 25));
        lblArrival.setBounds(310, 95, 200, 30);
        lblArrival.setText(arrival);
        add(lblArrival);

        JLabel lblArrow = new JLabel("--------->");
        lblArrow.setForeground(Color.LIGHT_GRAY);
        lblArrow.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblArrow.setBounds(194, 54, 171, 34);
        add(lblArrow);
    }
}

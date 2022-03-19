package card;

/**
 * This class can return a panel of a flight info card.
 *
 * @author Zhang Zeyu
 * @date 2022/3/19
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class flightInfoCard extends JPanel {

    private String idFlight;
    private String date;
    private String departureTime;
    private String arrivalTime;
    private String departure;
    private String arrival;

    public flightInfoCard(String idFlight,
                          String date,
                          String departureTime,
                          String arrivalTime,
                          String departure,
                          String arrival) {
        this.idFlight = idFlight;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departure = departure;
        this.arrival = arrival;

        setBorder(new LineBorder(new Color(0, 0, 0)));
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

        JLabel lblNewLabel = new JLabel("--------->");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblNewLabel.setBounds(194, 54, 171, 34);
        add(lblNewLabel);
    }
}

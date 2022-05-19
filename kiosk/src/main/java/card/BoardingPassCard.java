package card;

import dbReader.FlightReader;
import dbReader.PlaneReader;
import main.State;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of boarding pass card.
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

public class BoardingPassCard extends JPanel {

    public BoardingPassCard(String idFlight) {
        int indexFlight = FlightReader.indexOf(idFlight);
        int indexPlane = PlaneReader.indexOf(FlightReader.getIdPlane(indexFlight));
        String date = FlightReader.getDepartureDate(indexFlight);
        String departureTime = FlightReader.getDepartureTime(indexFlight);
        String arrivalTime = FlightReader.getArrivalTime(indexFlight);
        String departure = FlightReader.getDeparture(indexFlight);
        String[] CnT1 = departure.split(" ");
        String departureCity = "", departureTerminal = "";
        for (int i = 0; i < CnT1.length - 1; i++)
            departureCity = departureCity + CnT1[i] + " ";
        departureTerminal = CnT1[CnT1.length - 1];
        String arrival = FlightReader.getArrival(indexFlight);
        String[] CnT2 = arrival.split(" ");
        String arrivalCity = "",  arrivalTerminal = "";
        for (int i = 0; i < CnT2.length - 1; i++)
            arrivalCity = arrivalCity + CnT2[i] + " ";
        arrivalTerminal = CnT1[CnT2.length - 1];
        String airline = PlaneReader.getAirline(indexPlane);
        String gate = FlightReader.getGate(indexFlight);
        String boardingTime = FlightReader.getBoardingTime(indexFlight);
        String seat = State.getPrefSeatName()[State.getSeatPre()];
        setBackground(null);
        setLayout(null);
        setSize(1120,300);

        JLabel lblAirline1 = new JLabel(airline);
        lblAirline1.setBounds(580, 0, 200, 55);
        lblAirline1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblAirline1.setForeground(new Color(255, 255, 255));
        lblAirline1.setVerticalAlignment(SwingConstants.CENTER);
        lblAirline1.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblAirline1);

        JLabel lblAirline2 = new JLabel(airline);
        lblAirline2.setBounds(810, 0, 300, 55);
        lblAirline2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblAirline2.setForeground(new Color(255, 255, 255));
        lblAirline2.setVerticalAlignment(SwingConstants.CENTER);
        lblAirline2.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAirline2);

        JLabel lblName1 = new JLabel(State.getPassengerName());
        lblName1.setBounds(54, 142, 100, 25);
        lblName1.setFont(new Font("Arial", Font.BOLD, 14));
        lblName1.setForeground(new Color(28, 28, 28));
        add(lblName1);

        JLabel lblName2 = new JLabel(State.getPassengerName());
        lblName2.setBounds(873, 60, 100, 25);
        lblName2.setFont(new Font("Arial", Font.BOLD, 14));
        lblName2.setForeground(new Color(28, 28, 28));
        add(lblName2);

        JLabel lblDeparture1 = new JLabel(departureCity);
        lblDeparture1.setForeground(new Color(28, 28, 28));
        lblDeparture1.setFont(new Font("Arial", Font.BOLD, 20));
        lblDeparture1.setBounds(54, 75, 200, 25);
        add(lblDeparture1);

        JLabel lblDeparture2 = new JLabel(departureCity);
        lblDeparture2.setForeground(new Color(28, 28, 28));
        lblDeparture2.setFont(new Font("Arial", Font.BOLD, 14));
        lblDeparture2.setBounds(918, 146, 200, 25);
        add(lblDeparture2);

        JLabel lblDepartureTime = new JLabel(departureTime);
        lblDepartureTime.setForeground(new Color(28, 28, 28));
        lblDepartureTime.setFont(new Font("Arial", Font.BOLD, 14));
        lblDepartureTime.setBounds(918, 169, 200, 25);
        add(lblDepartureTime);

        JLabel lblArrival1 = new JLabel(arrivalCity);
        lblArrival1.setForeground(new Color(28, 28, 28));
        lblArrival1.setFont(new Font("Arial", Font.BOLD, 20));
        lblArrival1.setBounds(294, 75, 200, 25);
        add(lblArrival1);

        JLabel lblArrival2 = new JLabel(arrivalCity);
        lblArrival2.setForeground(new Color(28, 28, 28));
        lblArrival2.setFont(new Font("Arial", Font.BOLD, 14));
        lblArrival2.setBounds(918, 192, 200, 25);
        add(lblArrival2);

        JLabel lblArrivalTime = new JLabel(arrivalTime);
        lblArrivalTime.setForeground(new Color(28, 28, 28));
        lblArrivalTime.setFont(new Font("Arial", Font.BOLD, 14));
        lblArrivalTime.setBounds(918, 215, 200, 25);
        add(lblArrivalTime);

        JLabel lblDate1 = new JLabel(date);
        lblDate1.setFont(new Font("Arial", Font.BOLD, 14));
        lblDate1.setForeground(new Color(28, 28, 28));
        lblDate1.setBounds(54, 185, 274, 25);
        add(lblDate1);

        JLabel lblDate2 = new JLabel(date);
        lblDate2.setFont(new Font("Arial", Font.BOLD, 14));
        lblDate2.setForeground(new Color(28, 28, 28));
        lblDate2.setBounds(873, 103, 274, 25);
        add(lblDate2);

        JLabel lblSeat = new JLabel(seat);
        lblSeat.setFont(new Font("Arial", Font.BOLD, 14));
        lblSeat.setForeground(new Color(28, 28, 28));
        lblSeat.setBounds(54, 243, 274, 25);
        add(lblSeat);

        JLabel lblIdFlight = new JLabel(idFlight);
        lblIdFlight.setForeground(new Color(28, 28, 28));
        lblIdFlight.setFont(new Font("Arial", Font.BOLD, 20));
        lblIdFlight.setBounds(510, 75, 237, 25);
        add(lblIdFlight);

        JLabel lblTerminal = new JLabel(departureTerminal);
        lblTerminal.setForeground(new Color(28, 28, 28));
        lblTerminal.setFont(new Font("Arial", Font.BOLD, 20));
        lblTerminal.setBounds(671, 75, 237, 25);
        add(lblTerminal);

        JLabel lblGate = new JLabel(gate);
        lblGate.setBounds(673, 155, 300, 25);
        lblGate.setFont(new Font("Arial", Font.BOLD, 20));
        lblGate.setForeground(new Color(28, 28, 28));
        add(lblGate);

        JLabel lblBoardingTime = new JLabel(boardingTime);
        lblBoardingTime.setBounds(509, 155, 300, 25);
        lblBoardingTime.setFont(new Font("Arial", Font.BOLD, 20));
        lblBoardingTime.setForeground(new Color(28, 28, 28));
        add(lblBoardingTime);

        JLabel lblSeatNum1 = new JLabel("" + State.getSeatRow() + PlaneReader.getColumnNum(indexPlane).split(",")[State.getSeatColumn() - 1]);
        lblSeatNum1.setBounds(509, 239, 300, 25);
        lblSeatNum1.setFont(new Font("Arial", Font.BOLD, 20));
        lblSeatNum1.setForeground(new Color(28, 28, 28));
        add(lblSeatNum1);

        JLabel lblSeatNum2 = new JLabel("" + State.getSeatRow() + PlaneReader.getColumnNum(indexPlane).split(",")[State.getSeatColumn() - 1]);
        lblSeatNum2.setBounds(921, 244, 300, 25);
        lblSeatNum2.setFont(new Font("Arial", Font.BOLD, 20));
        lblSeatNum2.setForeground(new Color(28, 28, 28));
        add(lblSeatNum2);

        JLabel background = new JLabel(new ImageIcon("kiosk/icons/boardingPass.png"));
        background.setBounds(0, 0, 1120, 300);
        add(background);
    }
}

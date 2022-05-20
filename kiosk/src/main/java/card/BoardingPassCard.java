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
 * @version 5.1
 * Amend layout.
 * @date 2022/5/20
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
        departureTerminal = CnT1[CnT1.length - 1].replace("T", "");
        String arrival = FlightReader.getArrival(indexFlight);
        String[] CnT2 = arrival.split(" ");
        String arrivalCity = "";
        for (int i = 0; i < CnT2.length - 1; i++)
            arrivalCity = arrivalCity + CnT2[i] + " ";
        String airline = PlaneReader.getAirline(indexPlane);
        String gate = FlightReader.getGate(indexFlight);
        String boardingTime = FlightReader.getBoardingTime(indexFlight);
        String seat = State.getPrefSeatName()[State.getSeatPre()];
        setBackground(null);
        setLayout(null);
        setSize(1120,300);

        JLabel lblAirline1 = new JLabel(airline);
        lblAirline1.setBounds(570, 0, 200, 48);
        lblAirline1.setFont(new Font("Arial", Font.PLAIN, 22));
        lblAirline1.setForeground(new Color(255, 255, 255));
        lblAirline1.setVerticalAlignment(SwingConstants.CENTER);
        lblAirline1.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblAirline1);

        JLabel lblAirline2 = new JLabel(airline);
        lblAirline2.setBounds(875, 0, 200, 48);
        lblAirline2.setFont(new Font("Arial", Font.PLAIN, 22));
        lblAirline2.setForeground(new Color(255, 255, 255));
        lblAirline2.setVerticalAlignment(SwingConstants.CENTER);
        lblAirline2.setHorizontalAlignment(SwingConstants.LEADING);
        add(lblAirline2);

        JLabel lblPassengerName1 = new JLabel(State.getPassengerName());
        lblPassengerName1.setBounds(49, 141, 100, 25);
        lblPassengerName1.setFont(new Font("Arial", Font.BOLD, 19));
        lblPassengerName1.setForeground(new Color(28, 28, 28));
        add(lblPassengerName1);

        JLabel lblPassengerName2 = new JLabel(State.getPassengerName());
        lblPassengerName2.setBounds(876, 59, 100, 25);
        lblPassengerName2.setFont(new Font("Arial", Font.BOLD, 19));
        lblPassengerName2.setForeground(new Color(28, 28, 28));
        add(lblPassengerName2);

        JLabel lblDeparture1 = new JLabel(departureCity);
        lblDeparture1.setForeground(new Color(28, 28, 28));
        lblDeparture1.setFont(new Font("Arial", Font.BOLD, 28));
        lblDeparture1.setBounds(49, 67, 200, 35);
        add(lblDeparture1);

        JLabel lblDeparture2 = new JLabel(departureCity);
        lblDeparture2.setForeground(new Color(28, 28, 28));
        lblDeparture2.setFont(new Font("Arial", Font.BOLD, 19));
        lblDeparture2.setBounds(924, 143, 200, 25);
        add(lblDeparture2);

        JLabel lblDepartureTime = new JLabel(departureTime);
        lblDepartureTime.setForeground(new Color(28, 28, 28));
        lblDepartureTime.setFont(new Font("Arial", Font.BOLD, 19));
        lblDepartureTime.setBounds(924, 164, 200, 25);
        add(lblDepartureTime);

        JLabel lblArrival1 = new JLabel(arrivalCity);
        lblArrival1.setForeground(new Color(28, 28, 28));
        lblArrival1.setFont(new Font("Arial", Font.BOLD, 28));
        lblArrival1.setBounds(291, 67, 200, 35);
        add(lblArrival1);

        JLabel lblArrival2 = new JLabel(arrivalCity);
        lblArrival2.setForeground(new Color(28, 28, 28));
        lblArrival2.setFont(new Font("Arial", Font.BOLD, 19));
        lblArrival2.setBounds(924, 189, 200, 25);
        add(lblArrival2);

        JLabel lblArrivalTime = new JLabel(arrivalTime);
        lblArrivalTime.setForeground(new Color(28, 28, 28));
        lblArrivalTime.setFont(new Font("Arial", Font.BOLD, 19));
        lblArrivalTime.setBounds(924, 210, 200, 25);
        add(lblArrivalTime);

        JLabel lblDate1 = new JLabel(date);
        lblDate1.setForeground(new Color(28, 28, 28));
        lblDate1.setFont(new Font("Arial", Font.BOLD, 19));
        lblDate1.setBounds(49, 184, 274, 25);
        add(lblDate1);

        JLabel lblDate2 = new JLabel(date);
        lblDate2.setForeground(new Color(28, 28, 28));
        lblDate2.setFont(new Font("Arial", Font.BOLD, 19));
        lblDate2.setBounds(876, 101, 274, 25);
        add(lblDate2);

        JLabel lblClass = new JLabel(seat);
        lblClass.setFont(new Font("Arial", Font.BOLD, 19));
        lblClass.setForeground(new Color(28, 28, 28));
        lblClass.setBounds(49, 242, 274, 25);
        add(lblClass);

        JLabel lblIdFlight = new JLabel(idFlight);
        lblIdFlight.setForeground(new Color(28, 28, 28));
        lblIdFlight.setFont(new Font("Arial", Font.BOLD, 28));
        lblIdFlight.setBounds(508, 67, 237, 35);
        add(lblIdFlight);

        JLabel lblTerminal = new JLabel(departureTerminal);
        lblTerminal.setForeground(new Color(28, 28, 28));
        lblTerminal.setFont(new Font("Arial", Font.BOLD, 28));
        lblTerminal.setBounds(673, 67, 237, 35);
        add(lblTerminal);

        JLabel lblGate = new JLabel(gate);
        lblGate.setForeground(new Color(28, 28, 28));
        lblGate.setFont(new Font("Arial", Font.BOLD, 28));
        lblGate.setBounds(673, 147, 300, 35);
        add(lblGate);

        JLabel lblBoardingTime = new JLabel(boardingTime);
        lblBoardingTime.setForeground(new Color(28, 28, 28));
        lblBoardingTime.setFont(new Font("Arial", Font.BOLD, 28));
        lblBoardingTime.setBounds(508, 147, 300, 35);
        add(lblBoardingTime);

        JLabel lblSeatNum1 = new JLabel("" + State.getSeatRow() + PlaneReader.getColumnNum(indexPlane).split(",")[State.getSeatColumn() - 1]);
        lblSeatNum1.setForeground(new Color(28, 28, 28));
        lblSeatNum1.setFont(new Font("Arial", Font.BOLD, 28));
        lblSeatNum1.setBounds(508, 233, 300, 35);
        add(lblSeatNum1);

        JLabel lblSeatNum2 = new JLabel("" + State.getSeatRow() + PlaneReader.getColumnNum(indexPlane).split(",")[State.getSeatColumn() - 1]);
        lblSeatNum2.setForeground(new Color(28, 28, 28));
        lblSeatNum2.setFont(new Font("Arial", Font.BOLD, 28));
        lblSeatNum2.setBounds(924, 238, 300, 35);
        add(lblSeatNum2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("kiosk/icons/boardingPass.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), this);
    }
}

package panelTest;

/**
 * This test reads flight info from json database and generate a flight info card on a frame.
 *
 * @author Zhang Zeyu
 * @date 2022/3/19
 * @version 1.0
 */

import panel.flightInfoCard;

import javax.swing.*;
import java.awt.*;

public class flightInfoCardTest extends JFrame{

    private String idFlight;
    private String date;
    private String departureTime;
    private String arrivalTime;
    private String departure;
    private String arrival;

    public static void main(String[] args) {

        //晚上写读json

        flightInfoCardTest frame = new flightInfoCardTest();
        frame.setSize(1900,1600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(244,244,244));
        frame.setContentPane(contentPane);
        contentPane.add(new flightInfoCard("CA0001",
                                     "2022-03-19",
                                     "08:30",
                                     "12:00",
                                     "Beijing",
                                     "Shanghai"));
    }
}

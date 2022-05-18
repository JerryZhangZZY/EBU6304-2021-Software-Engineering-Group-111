package cardTest;

import card.FlightInfoCard;
import frame.MainFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * This test reads flight info from json database and generate a flight info card on a frame.
 *
 * @author Zhang Zeyu
 *
 * @version 5.0
 * Adjust location displayed.
 * Extend switching time delay.
 * @date 2022/5/18
 *
 * @version 3.0
 * Add unavailable label.
 * @date 2022/4/20
 *
 * @version 2.0
 * Change params and test setGray().
 * @date 2022/4/10
 *
 * @version 1.2
 * Use main frame to test.
 * @date 2022/3/24
 *
 * @version 1.1
 * Switch to new dbReader tools.
 * @date 2022/3/20
 *
 * @version 1.0
 * @date 2022/3/19
 */

public class FlightInfoCardTest extends JFrame{

    public static void main(String[] args) throws IOException {

        MainFrame frame = new MainFrame();
        FlightInfoCard flightInfoCard = new FlightInfoCard("LH1607", 1);
        flightInfoCard.setLocation(100, 100);
        frame.loadPanel(flightInfoCard);
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flightInfoCard.setGray(true);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flightInfoCard.setGray(false);
    }
}

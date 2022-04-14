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
        FlightInfoCard flightInfoCard = new FlightInfoCard("CA0001", 1);
        frame.loadPanel(flightInfoCard);
        frame.displayComponents(true, true, true);
        frame.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flightInfoCard.setGray();
    }
}

package cardTest;

import card.FlightInfoCard;
import frame.MainFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * This test reads flight info from json database and generate a flight info card on a frame.
 *
 * @author Zhang Zeyu
 * @date 2022/3/19
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.1
 * Switch to new dbReader tools.
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.2
 * Use main frame to test.
 */

public class FlightInfoCardTest extends JFrame{

    public static void main(String[] args) throws IOException {

        MainFrame frame = new MainFrame(1);
        frame.loadPanel(new FlightInfoCard("CA0001"));
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
    }
}

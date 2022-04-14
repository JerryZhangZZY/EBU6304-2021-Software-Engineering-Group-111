package cardTest;

import card.FlightInfoTopBarCard;
import frame.MainFrame;

/**
 * This is the test class of FlightInfoTopBarCard.
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.0
 */

public class FlightInfoTopBarCardTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new FlightInfoTopBarCard("CA0001"));
        frame.setVisible(true);
    }
}

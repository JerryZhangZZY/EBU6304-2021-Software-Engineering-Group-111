package frameTest;

import card.FlightInfoCard;
import frame.MainFrame;

import javax.swing.*;
/**
 * test for initial version main frame, with a panel embedded
 *
 * @author zaitian
 * @author Zhang Zeyu
 *
 * @version 2.0
 * Change params for FlightInfoCard().
 * @date 2022/4/10
 *
 * @version 1.0
 * @date 2022/3/18
 */

public class MainFrameTest {
    public static void main(String[] args) {
        JPanel flightInfoCard = new FlightInfoCard("BA0002", 1);
        MainFrame testMainFrame = new MainFrame();
        testMainFrame.loadPanel(flightInfoCard);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}
package frameTest;

import card.FlightInfoCard;
import frame.MainFrame;

import javax.swing.*;
/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * test for initial version main frame, with a panel embedded
 */

public class MainFrameTest {
    public static void main(String[] args) {
        JPanel flightInfoCard = new FlightInfoCard("CA0001");
        MainFrame testMainFrame = new MainFrame();
        testMainFrame.loadPanel(flightInfoCard);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}
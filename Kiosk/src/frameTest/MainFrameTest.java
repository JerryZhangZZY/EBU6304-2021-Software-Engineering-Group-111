package frameTest;

import javax.swing.*;

import card.flightInfoCard;
import frame.*;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * test for initial version main frame, with a panel embedded
 */

public class MainFrameTest {
    public static void main(String[] args) {
        JPanel flightInfoCard = new flightInfoCard("CA0001",
                "2022-03-19",
                "08:30",
                "12:00",
                "Beijing",
                "Shanghai");
        MainFrame testMainFrame = new MainFrame();
        testMainFrame.loadPanel(flightInfoCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}
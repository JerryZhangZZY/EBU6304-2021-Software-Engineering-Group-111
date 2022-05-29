package panelTest;

import frame.MainFrame;
import main.State;
import panel.FinalPanel;

/**
 * This class can test the final panel.
 *
 * @author Wang Chenyu
 * @date 2022/3/21
 * @version 1.0
 */

public class FinalPanelTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        State.setIdFlight("BA0002");
        State.setBookingNum("bn0001");
        State.setSeatRow(1);
        State.setSeatColumn(4);
        State.setSeatPre(1);
        State.setMeal('a');
        MainFrame testMainFrame = new MainFrame();
        FinalPanel testEnterIDPanel = new FinalPanel();
        testMainFrame.loadPanel(testEnterIDPanel);
        testMainFrame.displayComponents(true, false, false);
        testMainFrame.setVisible(true);
    }
}

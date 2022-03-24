package panelTest;

import frame.MainFrame;
import main.State;
import panel.FlightSelectionPanel;

/**
 * This is the test of FlightSelectionPanel.
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.0
 */

public class FlightSelectionPanelTest {
    public static void main(String[] args) {
        State.setBookingNum("bn0001");
        MainFrame frame = new MainFrame();
        frame.loadPanel(new FlightSelectionPanel());
        frame.setVisible(true);
    }
}

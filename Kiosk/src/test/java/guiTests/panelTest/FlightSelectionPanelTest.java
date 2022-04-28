package panelTest;

import frame.MainFrame;
import main.State;
import panel.FlightSelectionPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the test of FlightSelectionPanel.
 *
 * @author Zhang Zeyu
 *
 * @version 3.0
 * Upgrade.
 * @date 2022/3/24
 *
 * @version 1.0
 * @date 2022/3/24
 */

public class FlightSelectionPanelTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("bn0001");
        list.add("bn0002");
        State.setBookingNumList(list);
        MainFrame frame = new MainFrame();
        frame.loadPanel(new FlightSelectionPanel());
        frame.setVisible(true);
    }
}

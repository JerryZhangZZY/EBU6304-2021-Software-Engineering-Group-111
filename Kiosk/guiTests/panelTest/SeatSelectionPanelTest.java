package panelTest;

import frame.MainFrame;
import main.State;
import panel.ProgressPanel;
import panel.SeatSelectionPanel;

/**
 *
 * @author Liang Zhehao
 * @date 2022/3/24
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/28
 * @version 1.1
 * Upgrade the test ui.
 */
public class SeatSelectionPanelTest {
    public static void main(String[] args) {
        State.setPassengerName("Jack");
        State.setBookingNum("bn0001");
        State.setIdFlight("CA0001");
        MainFrame frame = new MainFrame();
        ProgressPanel progressPanel = new ProgressPanel(2);
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();
        seatSelectionPanel.add(State.smallBillCard);
        progressPanel.loadCardsPanel(seatSelectionPanel);
        frame.loadPanel(progressPanel);
        frame.setVisible(true);
    }
}

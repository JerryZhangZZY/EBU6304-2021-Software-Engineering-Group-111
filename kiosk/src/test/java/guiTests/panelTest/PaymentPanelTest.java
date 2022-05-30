package panelTest;

import frame.MainFrame;
import main.State;
import panel.PaymentPanel;
import panel.ProgressPanel;

/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/26
 * Test program for PaymentPanel
 *
 * @version 1.1
 * @author Zhang Zeyu
 * @date 2022/3/29
 * Make test runnable again.
 */
public class PaymentPanelTest {
    public static void main(String[] args) {
        State.setPassengerFlight_index(0);
        State.setIdFlight("BA0002");
        State.setPassengerName("Jack");
        State.setBookingNum("bn0001");
        ProgressPanel progressPanel = new ProgressPanel(4);
        progressPanel.loadCardsPanel(new PaymentPanel(165));
        MainFrame frame = new MainFrame();
        frame.loadPanel(progressPanel);
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
    }
}

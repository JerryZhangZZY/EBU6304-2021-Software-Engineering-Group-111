package panelTest;

import frame.MainFrame;
import panel.PaymentPanel;
/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/26
 * Test program for PaymentPanel
 */
public class PaymentPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new PaymentPanel());
        frame.setVisible(true);
    }
}

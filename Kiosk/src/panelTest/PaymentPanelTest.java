package panelTest;

import frame.MainFrame;
import panel.PaymentPanel;

public class PaymentPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new PaymentPanel());
        frame.setVisible(true);
    }
}

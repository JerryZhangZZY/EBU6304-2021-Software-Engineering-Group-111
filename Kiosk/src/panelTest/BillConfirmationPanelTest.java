package panelTest;

import frame.MainFrame;
import panel.BillConfirmationPanel;

public class BillConfirmationPanelTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();

        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
        testMainFrame.loadPanel(billConfirmationPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}

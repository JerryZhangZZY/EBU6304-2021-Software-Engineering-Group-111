package panelTest;

import frame.MainFrame;
import panel.BillConfirmPanel;

public class ConfirmPanelTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();

        BillConfirmPanel billConfirmPanel = new BillConfirmPanel();
        testMainFrame.loadPanel(billConfirmPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}

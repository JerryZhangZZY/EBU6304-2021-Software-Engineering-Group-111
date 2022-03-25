package panelTest;

import frame.MainFrame;
import panel.ConfirmPanel;

public class ConfirmPanelTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();

        ConfirmPanel confirmPanel = new ConfirmPanel();
        testMainFrame.loadPanel(confirmPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}

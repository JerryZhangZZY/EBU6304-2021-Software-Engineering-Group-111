package panelTest;

import frame.MainFrame;
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
        MainFrame testMainFrame = new MainFrame();
        FinalPanel testEnterIDPanel = new FinalPanel();
        testMainFrame.loadPanel(testEnterIDPanel);
        testMainFrame.displayComponents(true, false, false);
        testMainFrame.setVisible(true);
    }
}

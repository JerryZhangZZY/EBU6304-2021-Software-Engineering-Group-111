package panelTest;

import frame.MainFrame;
import panel.FinalPanel;

import javax.swing.*;

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
        MainFrame testMainFrame = new MainFrame(1);
        FinalPanel testEnterIDPanel = new FinalPanel();
        testMainFrame.loadPanel(testEnterIDPanel);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

package panelTest;

import frame.MainFrame;
import panel.*;

import javax.swing.*;

/**
 * @version 1.0
 * @date 3/19
 * @author zaitian
 * test for panel where users enter id
 */
public class EnterIDPanelTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MainFrame testMainFrame = new MainFrame(1);
        JPanel testEnterIDPanel = new EnterIDPanel();
        testMainFrame.loadPanel(testEnterIDPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }

}

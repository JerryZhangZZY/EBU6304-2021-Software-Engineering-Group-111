package panelTest;

import frame.MainFrame;
import panel.IdLoginPanel;

/**
 * This is the test class of IdLoginPanel
 *
 * @author Zhang Zeyu
 * @date 2022/3/27
 * @version 1.0
 */

public class IdLoginPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        IdLoginPanel panel = new IdLoginPanel();
        frame.loadPanel(panel);
        frame.displayComponents(true,true,true);
        frame.setVisible(true);
    }
}

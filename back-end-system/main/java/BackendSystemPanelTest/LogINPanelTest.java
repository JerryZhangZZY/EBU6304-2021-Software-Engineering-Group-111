package BackendSystemPanelTest;

/**
 * the test for LINPanel
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */
import BackendSystemFrame.BackendMainFrame;
import BackendSystemPanel.LogINPanel;

public class LogINPanelTest {
    public static void main(String[] args) {
        BackendMainFrame main = new BackendMainFrame();
        LogINPanel test = new LogINPanel();
        main.loadPanel(test);
        main.setVisible(true);
    }

}

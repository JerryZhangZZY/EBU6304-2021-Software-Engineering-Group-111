package BackendSystemPanelTest;
/**
 * the panel for table panel
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */
import BackendSystemFrame.BackendMainFrame;
import BackendSystemPanel.TablePanel;

import java.io.IOException;


public class TablePanelTest {
    public static void main(String[] args) throws IOException {
        BackendMainFrame main = new BackendMainFrame();
        TablePanel test = new TablePanel();
        main.loadPanel(test);
        main.setVisible(true);
    }
}

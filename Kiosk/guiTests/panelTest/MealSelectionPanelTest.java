package panelTest;

import frame.MainFrame;
import main.State;
import panel.MealSelectionPanel;

/**
 * This class test MealSelectionPanel
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 2022/4/23
 *
 * @version 1.0
 * @date 2022/3/24
 */
public class MealSelectionPanelTest {
    public static void main(String[] args) {
        State.setIdFlight("AC0001");
        MainFrame frame = new MainFrame();
        frame.loadPanel(new MealSelectionPanel());
        frame.setVisible(true);
    }
}

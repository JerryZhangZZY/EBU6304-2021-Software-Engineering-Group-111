package panelTest;

import frame.MainFrame;
import panel.MealSelectionPanel;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/24
 * @version 1.0
 */
public class MealSelectionPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new MealSelectionPanel());
        frame.setVisible(true);
    }
}

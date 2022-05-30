package panelTest;

import frame.MainFrame;
import main.State;
import panel.MealSelectionPanel;
import panel.ProgressPanel;

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
        State.setIdFlight("BA0001");
        MainFrame frame = new MainFrame();
        ProgressPanel progressPanel = new ProgressPanel(3);
        MealSelectionPanel mealSelectionPanel = new MealSelectionPanel();
        mealSelectionPanel.add(State.getSmallBillCard());
        progressPanel.loadCardsPanel(mealSelectionPanel);
        frame.loadPanel(progressPanel);
        frame.setVisible(true);
    }
}

package cardTest;

import card.MealSelectionCard;
import frame.MainFrame;
import panel.ProgressPanel;

/**
 * @version 1.0
 * @author Liang Zhehao
 * @date 3/18
 * test for prefCard, with a panel embedded
 *
 * @author Zhang Zeyu
 * @date 2022/3/25
 * @version 1.1
 */
public class MealSelectionCardTest {
    public static void main(String[] args) {
//        MainFrame testMainFrame = new MainFrame();
//        MealSelectionCard mealSelectionCard = new MealSelectionCard();
//        testMainFrame.loadPanel(mealSelectionCard);
//        testMainFrame.displayComponents(true, true, false);
//        testMainFrame.setVisible(true);

        ProgressPanel panel = new ProgressPanel(3);
        panel.loadCardsPanel(new MealSelectionCard());
        MainFrame frame = new MainFrame();
        frame.displayComponents(true, true, true);
        frame.loadPanel(panel);
        frame.setVisible(true);
    }
}

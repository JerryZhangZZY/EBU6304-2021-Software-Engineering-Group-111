package cardTest;

import card.MealInfoCard;
import frame.MainFrame;
import panel.ProgressPanel;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/21
 * test for FoodCard, with a panel embedded
 *
 * @author Zhang Zeyu
 * @date 2022/3/25
 * @version 1.1
 */
public class MealInfoCardTest {
    public static void main(String[] args) {
//        MainFrame testMainFrame = new MainFrame();
//        MealInfoCard f = new MealInfoCard();
//        testMainFrame.loadPanel(f);
//        testMainFrame.displayComponents(true,true,true);
//        testMainFrame.setVisible(true);

        ProgressPanel panel = new ProgressPanel(3);
        panel.loadCardsPanel(new MealInfoCard());
        MainFrame frame = new MainFrame();
        frame.displayComponents(true, true, true);
        frame.loadPanel(panel);
        frame.setVisible(true);
    }
}

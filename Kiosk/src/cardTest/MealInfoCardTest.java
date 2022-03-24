package cardTest;

import card.MealInfoCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/21
 * test for FoodCard, with a panel embedded
 */
public class MealInfoCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        MealInfoCard f = new MealInfoCard();
        testMainFrame.loadPanel(f);
        testMainFrame.displayComponents(true,true,true);
        testMainFrame.setVisible(true);

    }
}

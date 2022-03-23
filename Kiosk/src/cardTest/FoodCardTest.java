package cardTest;

import card.FoodInfoCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/21
 * test for FoodCard, with a panel embedded
 */
public class FoodCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame(1);
        FoodInfoCard f = new FoodInfoCard();
        testMainFrame.loadPanel(f);
        testMainFrame.displayComponents(true,true,true);
        testMainFrame.setVisible(true);

    }
}

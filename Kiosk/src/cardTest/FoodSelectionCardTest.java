package cardTest;

import card.FoodSelectionCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Liang Zhehao
 * @date 3/18
 * test for prefCard, with a panel embedded
 */
public class FoodSelectionCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        FoodSelectionCard foodSelectionCard = new FoodSelectionCard("Extra", "Kweichow Moutai", "Ice-cream", 5, 100, 10);
        testMainFrame.loadPanel(foodSelectionCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

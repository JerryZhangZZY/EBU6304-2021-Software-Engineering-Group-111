package cardTest;

import card.MealSelectionCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Liang Zhehao
 * @date 3/18
 * test for prefCard, with a panel embedded
 */
public class PrefCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame(1);
        MealSelectionCard mealSelectionCard = new MealSelectionCard("Extra", "Kweichow Moutai", "Ice-cream", 5, 100, 10);
        testMainFrame.loadPanel(mealSelectionCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

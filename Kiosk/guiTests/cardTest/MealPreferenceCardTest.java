package cardTest;

import card.MealPreferenceCard;
import frame.MainFrame;
import main.State;

/**
 * This class test MealPreferenceCard
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 2022/4/26
 */
public class MealPreferenceCardTest {

    public static void main(String[] args) {

        State.setIdFlight("AC0001");
        MainFrame testMainFrame = new MainFrame();
        MealPreferenceCard mealPreferenceCard = new MealPreferenceCard();
        mealPreferenceCard.setLocation(0, 0);
        testMainFrame.loadPanel(mealPreferenceCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

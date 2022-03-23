package cardTest;

import card.PrefMealCard;
import card.PrefSeatCard;
import frame.MainFrame;

import javax.swing.*;

/**
 * @version 1.0
 * @author Liang Zhehao
 * @date 3/18
 * test for prefCard, with a panel embedded
 */
public class PrefCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame(1);
        PrefMealCard prefMealCard = new PrefMealCard("Extra", "Kweichow Moutai", "Ice-cream", 5, 100, 10);
        testMainFrame.loadPanel(prefMealCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

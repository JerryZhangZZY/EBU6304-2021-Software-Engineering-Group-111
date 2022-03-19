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
        //JPanel flightInfoCard = new PrefSeatCard("Normal", "Legroom Pro", "Legroom Max", "Legroom Ultra", 0, 10, 20, 50);
        JPanel flightInfoCard = new PrefMealCard("Extra", "Maotai", "Ice-cream", 5, 100, 10);
        MainFrame testMainFrame = new MainFrame();
        testMainFrame.loadPanel(flightInfoCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

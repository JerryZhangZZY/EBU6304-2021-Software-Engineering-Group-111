package cardTest;

import frame.MainFrame;
import card.SeatSelectionCard;

/**
 * @author wcy
 * @version 1.0
 * @date 3/19
 * test for initial version main frame, with a panel embedded
 */

public class SeatSelectionCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        SeatSelectionCard h = new SeatSelectionCard();
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}
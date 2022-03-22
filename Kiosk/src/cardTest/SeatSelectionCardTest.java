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
        int[] a = {1, 0, 1, 0, 1, 1};
        int b = 4;
        MainFrame testMainFrame = new MainFrame(1);
        SeatSelectionCard h = new SeatSelectionCard( "AC0001","Normal", "Legroom Pro", "Legroom Max", "Legroom Ultra", 0, 10, 20, 50);
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}
package cardTest;

import card.SeatBillCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/23
 * a card of seat bill
 */
public class SeatBillCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        SeatBillCard h = new SeatBillCard(10,'A',"Large room",30);
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

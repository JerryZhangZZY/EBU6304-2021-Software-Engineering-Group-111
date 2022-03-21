package cardTest;

import frame.MainFrame;
import card.SeatInfoCard;

/**
 * @version 1.0
 * @author wcy
 * @date 3/19
 * test for initial version main frame, with a panel embedded
 */

public class SeatInfoCardTest {
    public static void main(String[] args) {
        int[] a= {1,0,1,0,1,1};
        int b= 12;
        MainFrame testMainFrame = new MainFrame();
        SeatInfoCard h = new SeatInfoCard(a,b);
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}
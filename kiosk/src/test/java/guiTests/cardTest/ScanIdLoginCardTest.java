package cardTest;

import card.ScanIdLoginCard;
import frame.MainFrame;

/**
 * This is the test class of ScanIdLoginCard
 *
 * @author Zhang Zeyu
 * @date 2022/3/27
 * @version 1.0
 */

public class ScanIdLoginCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        ScanIdLoginCard card = new ScanIdLoginCard();
        testMainFrame.loadPanel(card);
        testMainFrame.displayComponents(true,true,true);
        testMainFrame.setVisible(true);
    }
}

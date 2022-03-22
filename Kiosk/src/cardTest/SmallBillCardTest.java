package cardTest;
import frame.MainFrame;
import card.SmallBillCard;

/**
 * @author wcy
 * @version 1.0
 * @date 3/19
 * test for initial version main frame, with a panel embedded
 */
public class SmallBillCardTest {
    public static void main(String[] args) {
        int b = 4;
        MainFrame testMainFrame = new MainFrame(1);
        SmallBillCard h = new SmallBillCard(b);
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

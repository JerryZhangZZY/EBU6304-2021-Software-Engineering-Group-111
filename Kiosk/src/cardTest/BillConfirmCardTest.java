package cardTest;

import card.BillConfirmCard;
import frame.MainFrame;

/**
 * This class can test confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */
public class BillConfirmCardTest {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new BillConfirmCard());
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
    }
}

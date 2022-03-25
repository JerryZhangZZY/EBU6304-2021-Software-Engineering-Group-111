package cardTest;

import card.ConfirmCard;
import frame.MainFrame;

/**
 * This class can test confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */
public class ConfirmCardTest {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new ConfirmCard());
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
    }
}

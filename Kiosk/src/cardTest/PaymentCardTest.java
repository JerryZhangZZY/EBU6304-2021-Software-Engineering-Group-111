package cardTest;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/24
 * a card of paymentCard test
 */
import card.PaymentCard;
import frame.MainFrame;

public class PaymentCardTest {
    public static void main(String[] args) {
        PaymentCard payment = new PaymentCard();
        MainFrame testMainFrame = new MainFrame();
        testMainFrame.loadPanel(payment);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }


}

package panel;

import card.BillConfirmCard;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of confirm panel.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */
public class BillConfirmationPanel extends JPanel {

    private BillConfirmCard billConfirmCard;

    public BillConfirmationPanel() {
        setBounds(0, 0, 1600, 880);
        setBackground(Color.PINK);
        setLayout(null);

        billConfirmCard = new BillConfirmCard();
        add(billConfirmCard);
    }
}

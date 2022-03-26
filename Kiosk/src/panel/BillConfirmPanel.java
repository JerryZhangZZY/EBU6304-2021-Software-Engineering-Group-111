package panel;

import card.ConfirmCard;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of confirm panel.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */
public class BillConfirmPanel extends JPanel {

    private ConfirmCard confirmCard;

    public BillConfirmPanel() {
        setBounds(0, 0, 1600, 880);
        setBackground(Color.PINK);
        setLayout(null);

        confirmCard = new ConfirmCard();
        add(confirmCard);
    }
}

package panel;

import card.ScanIdLoginCard;
import card.TypeIdLoginCard;

import javax.swing.*;

/**
 * This is the panel that passengers can use their ids to login.
 * @author Zhang Zeyu
 *
 * @version 1.0
 * @date 2022/3/27
 */

public class IdLoginPanel extends JPanel {

    TypeIdLoginCard typeIdLoginCard;
    ScanIdLoginCard scanIdLoginCard;

    public IdLoginPanel() {
        setSize(1920, 980);
        setLayout(null);

        typeIdLoginCard = new TypeIdLoginCard();
        add(typeIdLoginCard);

        scanIdLoginCard = new ScanIdLoginCard();
        scanIdLoginCard.setLocation(1150, 0);
        add(scanIdLoginCard);
    }

    public void reset() {
        typeIdLoginCard.reset();
        scanIdLoginCard.reset();
    }
}

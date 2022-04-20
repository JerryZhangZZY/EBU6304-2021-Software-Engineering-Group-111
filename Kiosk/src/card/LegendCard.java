package card;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a legend card
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 2022/4/21
 */
public class LegendCard extends JPanel {

    /**
     *
     * @param empty
     * @param selected
     * @param occupied
     */
    public LegendCard(ImageIcon[] empty, ImageIcon selected, ImageIcon occupied) {
        setBackground(Color.WHITE);
        setSize(330,780);
        setLayout(null);

        JLabel[] iconlbl = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            iconlbl[i] = new JLabel();
            iconlbl[i].setSize(45, 45);
            if (i == 4)
                iconlbl[i].setIcon(selected);
            else if (i == 5)
                iconlbl[i].setIcon(occupied);
            else
                iconlbl[i].setIcon(empty[i]);
        }
        for (int i = 0; i < 7; i++) {
            JLabel line = new JLabel();
            line.setBounds(15, i * 120 + 25, 300, 2);
            line.setOpaque(true);
            line.setBackground(new Color(211, 211, 211));
            add(line);

            if (i == 6)
                break;

            iconlbl[i].setLocation(15, i * 120 + 35);
            add(iconlbl[i]);
        }
    }
}

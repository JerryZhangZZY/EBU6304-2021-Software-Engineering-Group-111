package card;

import main.State;
import main.Theme;

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

public class SeatLegendCard extends JPanel {

    /**
     *
     * @param empty empty icon
     * @param selected selected icon
     * @param occupied occupied icon
     */
    public SeatLegendCard(ImageIcon[] empty, ImageIcon selected, ImageIcon occupied) {
        setBackground(Theme.getCardColor());
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
            line.setBounds(15, i * 120 + 29, 300, 2);
            line.setOpaque(true);
            line.setBackground(Theme.getTertiaryFontColor());
            add(line);

            if (i == 6)
                break;

            iconlbl[i].setLocation(15, i * 120 + 45);
            add(iconlbl[i]);

            JLabel seatName = new JLabel();
            seatName.setFont(new Font("Arial", Font.PLAIN, 25));
            seatName.setForeground(Theme.getMainFontColor());
            seatName.setBounds(70, i * 120 + 39, 250, 35);
            add(seatName);

            JLabel detail = new JLabel();
            detail.setFont(new Font("Arial", Font.PLAIN, 18));
            detail.setForeground(Theme.getSecondaryFontColor());
            detail.setVerticalAlignment(SwingConstants.TOP);
            detail.setBounds(70, i * 120 + 74, 250, 80);
            add(detail);

            JLabel price = new JLabel();
            price.setFont(new Font("Arial", Font.PLAIN, 25));
            price.setForeground(Theme.getMainFontColor());
            price.setHorizontalAlignment(SwingConstants.RIGHT);
            price.setBounds(200, i * 120 + 112, 115, 35);
            add(price);

            if (i == 0) {
                seatName.setText(State.getPrefSeatName()[i]);
                detail.setText("Sit at the back of the aircraft");
                price.setText("$" + State.getPrefSeatPrice()[i]);
            } else if (i == 1) {
                seatName.setText(State.getPrefSeatName()[i]);
                detail.setText("Enjoy better service");
                price.setText("$" + State.getPrefSeatPrice()[i]);
            } else if (i == 2) {
                seatName.setText(State.getPrefSeatName()[i]);
                detail.setText("Stretch out in an exit row seat");
                price.setText("$" + State.getPrefSeatPrice()[i]);
            } else if (i == 3) {
                seatName.setText(State.getPrefSeatName()[i]);
                detail.setText("<html><body>Enjoy more comfortable <br>seating and service<body></html>");
                price.setText("$" + State.getPrefSeatPrice()[i]);
            } else if (i == 4) {
                seatName.setText("Your Selection");
                detail.setText("Your current seat selection");
            } else {
                seatName.setText("Not Available");
                detail.setText("<html><body>These seats are no longer <br>available<body></html>");
            }
        }
    }
}

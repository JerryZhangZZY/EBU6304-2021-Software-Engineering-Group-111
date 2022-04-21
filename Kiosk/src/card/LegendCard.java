package card;

import main.State;

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

            JLabel seatName = new JLabel();
            seatName.setFont(new Font("Arial", Font.PLAIN, 25));
            seatName.setForeground(Color.BLACK);
            seatName.setBounds(70, i * 120 + 35, 250, 35);
            add(seatName);

            JLabel detail = new JLabel();
            detail.setFont(new Font("Arial", Font.PLAIN, 18));
            detail.setForeground(Color.GRAY);
            detail.setVerticalAlignment(SwingConstants.TOP);
            detail.setBounds(70, i * 120 + 70, 250, 80);
            add(detail);

            JLabel price = new JLabel();
            price.setFont(new Font("Arial", Font.PLAIN, 25));
            price.setForeground(Color.BLACK);
            price.setHorizontalAlignment(SwingConstants.RIGHT);
            price.setBounds(200, i * 120 + 110, 115, 35);
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

package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author Wang Chenyu
 * @version 1.0
 * @date 3/23
 * a card of seat bill
 *
 * @author Liang Zhehao
 * @version 1.1
 * @date 2022/3/26
 */
public class SeatBillCard extends JPanel {
    private JLabel headline = new JLabel("Seat:");
    private JLabel preference = new JLabel();
    private JLabel bill = new JLabel();

    public SeatBillCard(int row, char column, String prefer, int pay) {

        setBorder(new LineBorder(Color.darkGray));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1111, 190);

        preference.setText("·" + prefer);
        preference.setBounds(127, 89, 831, 69);
        preference.setFont(new Font("Arial", Font.BOLD, 38));
        preference.setForeground(Color.GRAY);
        add(preference);

        JLabel line = new JLabel("·······························");
        line.setLocation(495, 89);
        line.setSize(413, 69);
        line.setForeground(Color.GRAY);
        line.setFont(new Font("Arial", Font.BOLD, 38));
        add(line);

        headline.setLocation(20, 10);
        headline.setSize(500, 69);
        headline.setText("Seat:  " + row + column);
        headline.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        add(headline);

        bill.setLocation(970, 89);
        bill.setSize(104, 69);
        bill.setForeground(Color.ORANGE);
        bill.setFont(new Font("Arial", Font.BOLD, 38));
        bill.setText("$" + pay);
        add(bill);
    }
}


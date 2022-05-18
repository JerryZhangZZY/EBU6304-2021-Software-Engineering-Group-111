package card;

import main.Theme;

import javax.swing.*;
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
    private JLabel headText;
    private JLabel preference = new JLabel();
    private JLabel bill = new JLabel();

    public SeatBillCard(int row, char column, String prefer, int pay) {

        setBackground(Theme.getCardColor());
        setLayout(null);
        setSize(1120, 205);

        JLabel lblTopLine = new JLabel();
        lblTopLine.setBounds(0, 0, 1120, 10);
        lblTopLine.setOpaque(true);
        lblTopLine.setBackground(Theme.getThemeColor());
        add(lblTopLine);

        headline.setLocation(40, 20);
        headline.setSize(140, 69);
        headline.setFont(new Font("Arial", Font.PLAIN, 50));
        headline.setForeground(Theme.getMainFontColor());
        headline.setBackground(Theme.getCardColor());
        headline.setOpaque(true);
        add(headline);

        headText = new JLabel("Seat:  " + row + column);
        headText.setBounds(40, 20, 500, 69);
        headText.setFont(new Font("Arial", Font.BOLD, 50));
        headText.setForeground(Theme.getMainFontColor());
        add(headText);

        int gain = 85, base = 100, x = 30, wid = 1060;

        JLabel line1 = new JLabel();
        line1.setBounds(x, base, wid, 2);
        line1.setOpaque(true);
        line1.setBackground(Theme.getTertiaryFontColor());
        add(line1);

        JLabel line2 = new JLabel();
        line2.setBounds(x, base + gain, wid, 2);
        line2.setOpaque(true);
        line2.setBackground(Theme.getTertiaryFontColor());
        add(line2);

        preference.setText("· " + prefer);
        preference.setBounds(120, base + 1, 831, gain);
        preference.setFont(new Font("Arial", Font.PLAIN, 38));
        preference.setForeground(Theme.getSecondaryFontColor());
        preference.setVerticalAlignment(SwingConstants.CENTER);
        add(preference);

        JLabel line = new JLabel("  ·····························");
        line.setLocation(495, base + 1);
        line.setSize(413, gain);
        line.setForeground(Theme.getTertiaryFontColor());
        line.setVerticalAlignment(SwingConstants.CENTER);
        line.setFont(new Font("Arial", Font.PLAIN, 38));
        add(line);

        bill.setLocation(955, base + 1);
        bill.setSize(100, gain);
        bill.setForeground(new Color(255,69,0));
        bill.setFont(new Font("Arial", Font.PLAIN, 38));
        bill.setText("$" + pay);
        bill.setHorizontalAlignment(SwingConstants.RIGHT);
        bill.setVerticalAlignment(SwingConstants.CENTER);
        add(bill);
    }

    public JLabel getBill() {
        return bill;
    }

    public JLabel getPreference() {
        return preference;
    }

    public JLabel getHeadText() {
        return headText;
    }
}


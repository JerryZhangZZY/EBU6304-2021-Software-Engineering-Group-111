package card;

import main.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * a card of meal bill
 * @author Liang Zhehao
 * @author Wang Chenyu
 *
 * @version 1.1
 * @date 2022/3/26
 *
 * @version 1.0
 * @date 3/23
 *
 */
public class MealBillCard extends JPanel {

    private JLabel headline;
    private JLabel headText;
    private JLabel[] extr = new JLabel[3];
    private JLabel[] bill = new JLabel[3];
    private JLabel[] line = new JLabel[3];

    private String[] pref = new String[3];
    private int[] pay = new int[3];
    
    public MealBillCard(char basis, String extr1, String extr2, String extr3, int bill1, int bill2, int bill3) {

        pref[0] = extr1;
        pref[1] = extr2;
        pref[2] = extr3;
        pay[0] = bill1;
        pay[1] = bill2;
        pay[2] = bill3;

        setSize(1120, 375);
        setBackground(Theme.getCardColor());
        setLayout(null);

        for (int i = 0; i < 2; i++) {
            for (int j = 2; j > i; j--) {
                if (pay[j] < pay[j - 1]) {
                    int temp1 = pay[j];
                    pay[j] = pay[j - 1];
                    pay[j - 1] = temp1;
                    String temp2 = pref[j];
                    pref[j] = pref[j - 1];
                    pref[j - 1] = temp2;
                }
            }
        }
        String food = "";
        if (basis == 'a')
            food = "Normal";
        else if (basis == 'b')
            food = "Vegetarian";
        else if (basis == 'c')
            food = "Halal";
        headline = new JLabel("Meal:");
        headline.setBounds(40, 20, 140, 69);
        headline.setFont(new Font("Arial", Font.PLAIN, 50));
        headline.setForeground(Theme.getMainFontColor());
        headline.setBackground(Theme.getCardColor());
        headline.setOpaque(true);
        add(headline);

        headText = new JLabel("Meal:  " + food);
        headText.setBounds(40, 20, 600, 69);
        headText.setFont(new Font("Arial", Font.BOLD, 50));
        headText.setForeground(Theme.getMainFontColor());
        add(headText);

        JLabel lblTopLine = new JLabel();
        lblTopLine.setBounds(0, 0, 1120, 10);
        lblTopLine.setOpaque(true);
        lblTopLine.setBackground(Theme.getThemeColor());
        add(lblTopLine);

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

        JLabel line3 = new JLabel();
        line3.setBounds(x, base + 2 * gain, wid, 2);
        line3.setOpaque(true);
        line3.setBackground(Theme.getTertiaryFontColor());
        add(line3);

        JLabel line4 = new JLabel();
        line4.setBounds(x, base + 3 * gain, wid, 2);
        line4.setOpaque(true);
        line4.setBackground(Theme.getTertiaryFontColor());
        add(line4);

        int n = 0;
        for (int i = 0; i < 3; i++) {
            if (pay[i] == -1) {
                continue;
            }
            extr[n] = new JLabel();
            extr[n].setText("· " + pref[i]);
            extr[n].setLocation(120, base + 1 + gain * n);
            extr[n].setFont(new Font("Arial", Font.PLAIN, 38));
            extr[n].setForeground(Theme.getSecondaryFontColor());
            extr[n].setVerticalAlignment(SwingConstants.CENTER);
            extr[n].setSize(350, gain);
            add(extr[n]);

            bill[n] = new JLabel();
            bill[n].setLocation(955, base + 1 + gain * n);
            bill[n].setSize(100, gain);
            bill[n].setText("$" + pay[i]);
            bill[n].setHorizontalAlignment(SwingConstants.RIGHT);
            bill[n].setForeground(new Color(255,69,0));
            bill[n].setVerticalAlignment(SwingConstants.CENTER);
            bill[n].setFont(new Font("Arial", Font.PLAIN, 38));
            add(bill[n]);

            line[n] = new JLabel("  ·····························");
            line[n].setLocation(495, base + 1 + gain * n);
            line[n].setSize(413, gain);
            line[n].setForeground(Theme.getTertiaryFontColor());
            line[n].setVerticalAlignment(SwingConstants.CENTER);
            line[n].setFont(new Font("Arial", Font.PLAIN, 38));
            add(line[n]);

            n++;
        }

        if (pay[0] == -1 && pay[1] == -1 && pay[2] == -1) {
            extr[0] = new JLabel();
            extr[0].setText("· No preference selected");
            extr[0].setLocation(120, base + 1);
            extr[0].setFont(new Font("Arial", Font.PLAIN, 38));
            extr[0].setForeground(Theme.getSecondaryFontColor());
            extr[0].setVerticalAlignment(SwingConstants.CENTER);
            extr[0].setSize(700, gain);
            add(extr[0]);
        }

    }

    public JLabel getHeadline() {
        return headline;
    }

    public JLabel[] getExtr() {
        return extr;
    }

    public JLabel[] getBill() {
        return bill;
    }

    public JLabel[] getLine() {
        return line;
    }

}

package card;

import javax.swing.*;
import java.awt.*;

/**
 * @author Wang Chenyu
 * @version 1.0
 * @date 3/23
 * a card of meal bill
 *
 * @author Liang Zhehao
 * @version 1.1
 * @date 2022/3/26
 */
public class MealBillcard extends JPanel {

    private JLabel basis_meal;
    
    private JLabel[] extr = new JLabel[3];
    private JLabel[] bill = new JLabel[3];
    private JLabel[] line = new JLabel[3];

    private String[] pref = new String[3];
    private int[] pay = new int[3];
    
    public MealBillcard(char basis, String extr1, String extr2, String extr3, int bill1, int bill2, int bill3) {

        pref[0] = extr1;
        pref[1] = extr2;
        pref[2] = extr3;
        pay[0] = bill1;
        pay[1] = bill2;
        pay[2] = bill3;

//        setBorder(new LineBorder(Color.darkGray));
        setBackground(Color.WHITE);
        setLayout(null);

        String food = "";
        if (basis == 'a')
            food = "Standard";
        else if (basis == 'b')
            food = "Vegetarian";
        else if (basis == 'c')
            food = "Halal";
        JLabel headline = new JLabel("Meal:  " + food);
        headline.setBounds(40, 20, 600, 69);
        headline.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        headline.setForeground(Color.DARK_GRAY);
        add(headline);

        int n = 0;
        for (int i = 0; i < 3; i++) {
            if (pay[i] == -1) {
                continue;
            }
            extr[n] = new JLabel();
            extr[n].setText("· " + pref[i]);
            extr[n].setLocation(120, 105 + 91 * n);
            extr[n].setFont(new Font("Arial", Font.BOLD, 38));
            extr[n].setForeground(Color.DARK_GRAY);
            extr[n].setSize(350, 69);
            add(extr[n]);

            bill[n] = new JLabel();
            bill[n].setLocation(970, 105 + 91 * n);
            bill[n].setSize(127, 69);
            bill[n].setText("$" + pay[i]);
            bill[n].setForeground(new Color(255,69,0));
            bill[n].setFont(new Font("Arial", Font.BOLD, 38));
            add(bill[n]);

            line[n] = new JLabel("·······························");
            line[n].setLocation(495, 105 + 91 * n);
            line[n].setSize(413, 69);
            line[n].setForeground(Color.LIGHT_GRAY);
            line[n].setFont(new Font("Arial", Font.BOLD, 38));
            add(line[n]);

            n++;
        }

        setSize(1111, 121 + n * 91);

    }
}

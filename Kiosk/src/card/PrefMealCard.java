package card;

/**
 * This class can return a panel of a meal preference info card.
 *
 * @author Liang Zhehao
 * @date 2022/3/19
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PrefMealCard extends JPanel {

    private JRadioButton rdbtnMeal1 = new JRadioButton();
    private JRadioButton rdbtnMeal2 = new JRadioButton();
    private JRadioButton rdbtnMeal3 = new JRadioButton();

    public PrefMealCard(String meal1, String meal2, String meal3,
                        float price1, float price2, float price3) {

        setForeground(Color.WHITE);
        setLayout(null);
        setSize(351,212);

        JLabel lblNewLabel = new JLabel("Preference");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel.setBounds(34, 22, 229, 40);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(":  $" + price1);
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(213, 79, 90, 24);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(":  $" + price2);
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(213, 119, 90, 24);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel(":  $" + price3);
        lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(213, 159, 90, 24);
        add(lblNewLabel_1_2);

        rdbtnMeal1.setText(meal1);
        rdbtnMeal1.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal1.setForeground(Color.DARK_GRAY);
        rdbtnMeal1.setBounds(16, 80, 144, 23);
        add(rdbtnMeal1);

        rdbtnMeal2.setText(meal2);
        rdbtnMeal2.setForeground(Color.DARK_GRAY);
        rdbtnMeal2.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal2.setBounds(16, 120, 144, 23);
        add(rdbtnMeal2);

        rdbtnMeal3.setText(meal3);
        rdbtnMeal3.setForeground(Color.DARK_GRAY);
        rdbtnMeal3.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal3.setBounds(16, 160, 159, 23);
        add(rdbtnMeal3);

    }

}

package card;

import main.State;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class can return a panel of a meal preference info card.
 *
 * @author Liang Zhehao
 * @version 1.0
 * @date 2022/3/19
 *
 * @author Liang Zhehao
 * @version 1.1
 * @date 2022/3/23
 */

public class MealSelectionCard extends JPanel {

    private JRadioButton[] rdbtnMeal = new JRadioButton[3];

    private FoodInfoCard foodInfoCard;
    private SmallBillCard smallBillCard;
    private int bill;

    private int[] price = new int[3];

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createLineBorder(Color.red)
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 20));

    public MealSelectionCard(String meal1, String meal2, String meal3,
                             int price1, int price2, int price3) {

        bill = 0;
        this.price[0] = price1;
        this.price[1] = price2;
        this.price[2] = price3;

        rdbtnMeal[0] = new JRadioButton();
        rdbtnMeal[1] = new JRadioButton();
        rdbtnMeal[2] = new JRadioButton();

        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        setLayout(null);
        setSize(1540, 880);

        foodInfoCard = new FoodInfoCard();
        foodInfoCard.setBounds(40, 20, 1450, 400);
        //foodInfoCard.setBorder(new LineBorder(Color.WHITE));
        add(foodInfoCard);

        JLabel lblNewLabel = new JLabel("Preference");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel.setBounds(114, 584, 229, 40);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(":  $" + price1);
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(340, 641, 90, 24);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(":  $" + price2);
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(340, 681, 90, 24);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel(":  $" + price3);
        lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(340, 721, 90, 24);
        add(lblNewLabel_1_2);
        
        rdbtnMeal[0].setText(meal1);
        rdbtnMeal[0].setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal[0].setForeground(Color.DARK_GRAY);
        rdbtnMeal[0].setBounds(96, 642, 220, 23);
        add(rdbtnMeal[0]);
        
        rdbtnMeal[1].setText(meal2);
        rdbtnMeal[1].setForeground(Color.DARK_GRAY);
        rdbtnMeal[1].setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal[1].setBounds(96, 682, 220, 23);
        add(rdbtnMeal[1]);
        
        rdbtnMeal[2].setText(meal3);
        rdbtnMeal[2].setForeground(Color.DARK_GRAY);
        rdbtnMeal[2].setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal[2].setBounds(96, 722, 220, 23);
        add(rdbtnMeal[2]);

        PrefListener prefListener = new PrefListener();
        for (int i=0 ; i<3 ; i++)
            rdbtnMeal[i].addItemListener(prefListener);

        JPanel panel = new JPanel();
        panel.setBounds(80, 562, 388, 220);
        add(panel);

        OKListener okListener = new OKListener();
        JButton btnOK = new JButton("OK");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 40));
        btnOK.setBounds(1185, 562, 154, 72);
        btnOK.addActionListener(okListener);
        add(btnOK);

        smallBillCard = new SmallBillCard(bill);
        smallBillCard.setBounds(1126, 667, 265, 115);
        add(smallBillCard);
    }

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (foodInfoCard.getChosen() != 'd') {
                State.setPc(State.getPc() + 1);
                //上传已选数据
            } else {
                foodInfoCard.setBorder(tipBorder);
            }
        }
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            for (int i=0 ; i<3 ; i++) {
                if (e.getSource() == rdbtnMeal[i] && rdbtnMeal[i].isSelected())
                    smallBillCard.addPrice(price[i]);
                else if (e.getSource() == rdbtnMeal[i] && !rdbtnMeal[i].isSelected())
                    smallBillCard.subPrice(price[i]);
            }
        }
    }
}

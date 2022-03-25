package panel;

import card.MealInfoCard;
import card.SmallBillCard;
import main.State;

import javax.swing.*;
import javax.swing.border.Border;
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
 *
 * @author Liang Zhehao
 * @version 1.2
 * @date 2022/3/25
 * Add setters and remove parameters
 *
 * @author Zhang Zeyu
 * @date 2022/3/25
 * @version 1.3
 * GUI appearance redesigned.
 */

public class MealSelectionPanel extends JPanel {

    private JRadioButton[] rdbtnMeal = new JRadioButton[3];

    private MealInfoCard mealInfoCard;
    private SmallBillCard smallBillCard = State.smallBillCard;

    private int[] price = new int[3];
    private String[] foodName = new String[3];
    private boolean[] select = State.getSelectedPrefFood();

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.RED)
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , Color.RED);

    public MealSelectionPanel() {

        for(int i = 0; i < 3; i++) {
            this.price[i] = State.getPrefFoodPrice()[i];
            this.foodName[i] = State.getPrefFoodName()[i];
        }

        rdbtnMeal[0] = new JRadioButton();
        rdbtnMeal[1] = new JRadioButton();
        rdbtnMeal[2] = new JRadioButton();

        setBackground(new Color(244, 244, 244));
        setForeground(Color.WHITE);
        setLayout(null);
        setSize(1600, 880);

        mealInfoCard = new MealInfoCard();
        mealInfoCard.setBounds(0, 0, 1160, 880);
        add(mealInfoCard);

        JPanel panelPref = new JPanel();
        panelPref.setBounds(1155, 50, 388, 220);
        panelPref.setBackground(Color.WHITE);
        add(panelPref);

        JLabel lblTitle = new JLabel("Preference");
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lblTitle.setBounds(34, 22, 250, 40);
        panelPref.add(lblTitle);

        JLabel lblPrice1 = new JLabel(":  $" + price[0]);
        lblPrice1.setForeground(Color.DARK_GRAY);
        lblPrice1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice1.setBounds(260, 90, 90, 30);
        panelPref.setLayout(null);
        panelPref.add(lblPrice1);

        JLabel lblPrice2 = new JLabel(":  $" + price[1]);
        lblPrice2.setForeground(Color.DARK_GRAY);
        lblPrice2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice2.setBounds(260, 130, 90, 30);
        panelPref.add(lblPrice2);

        JLabel lblPrice3 = new JLabel(":  $" + price[2]);
        lblPrice3.setForeground(Color.DARK_GRAY);
        lblPrice3.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice3.setBounds(260, 170, 90, 30);
        panelPref.add(lblPrice3);

        rdbtnMeal[0].setText(foodName[0]);
        rdbtnMeal[0].setForeground(Color.DARK_GRAY);
        rdbtnMeal[0].setBackground(Color.WHITE);
        rdbtnMeal[0].setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnMeal[0].setBounds(16, 90, 250, 30);
        panelPref.add(rdbtnMeal[0]);

        rdbtnMeal[1].setText(foodName[1]);
        rdbtnMeal[1].setForeground(Color.DARK_GRAY);
        rdbtnMeal[1].setBackground(Color.WHITE);
        rdbtnMeal[1].setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnMeal[1].setBounds(16, 130, 250, 30);
        panelPref.add(rdbtnMeal[1]);

        rdbtnMeal[2].setText(foodName[2]);
        rdbtnMeal[2].setForeground(Color.DARK_GRAY);
        rdbtnMeal[2].setBackground(Color.WHITE);
        rdbtnMeal[2].setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnMeal[2].setBounds(16, 170, 250, 30);
        panelPref.add(rdbtnMeal[2]);

        PrefListener prefListener = new PrefListener();
        for (int i=0 ; i<3 ; i++)
            rdbtnMeal[i].addItemListener(prefListener);

        OKListener okListener = new OKListener();
        JButton btnOK = new JButton("OK");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 40));
        btnOK.setForeground(Color.DARK_GRAY);
        btnOK.setBackground(Color.WHITE);
        btnOK.setBounds(1255, 562, 154, 72);
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public void setFoodName(String[] foodName) {
        this.foodName = foodName;
    }

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (mealInfoCard.getChosen() != 'd') {
                State.setPc(State.getPc() + 1);
                //上传已选数据
                State.setMeal(mealInfoCard.getChosen());
                State.setSelectedPrefFood(select);
                State.setBill(smallBillCard.getPrice());
            } else {
                mealInfoCard.setBorder(tipBorder);
            }
        }
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            for (int i=0 ; i<3 ; i++) {
                if (e.getSource() == rdbtnMeal[i] && rdbtnMeal[i].isSelected()) {
                    smallBillCard.addPrice(price[i]);
                    select[i] = true;
                }
                else if (e.getSource() == rdbtnMeal[i] && !rdbtnMeal[i].isSelected()) {
                    smallBillCard.subPrice(price[i]);
                    select[i] = false;
                }
            }
        }
    }
}

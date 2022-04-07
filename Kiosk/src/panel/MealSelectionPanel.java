package panel;

import card.MealInfoCard;
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
 * This class can return a meal selection panel.
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
 *
 * @author Zhang Zeyu
 * @date 2022/3/28
 * @version 1.4
 * Appearance improved.
 */

public class MealSelectionPanel extends JPanel {

    private JRadioButton[] rdbtnMeal = new JRadioButton[3];

    private MealInfoCard mealInfoCard;

    private int[] price = new int[3];
    private String[] foodName = new String[3];
    private boolean[] select = {false, false, false};
    JButton btnOK = new JButton("OK");

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.RED)
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , Color.RED);

    public MealSelectionPanel(boolean cheat) {}

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
        rdbtnMeal[0].setBounds(16, 90, 240, 30);
        panelPref.add(rdbtnMeal[0]);

        rdbtnMeal[1].setText(foodName[1]);
        rdbtnMeal[1].setForeground(Color.DARK_GRAY);
        rdbtnMeal[1].setBackground(Color.WHITE);
        rdbtnMeal[1].setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnMeal[1].setBounds(16, 130, 240, 30);
        panelPref.add(rdbtnMeal[1]);

        rdbtnMeal[2].setText(foodName[2]);
        rdbtnMeal[2].setForeground(Color.DARK_GRAY);
        rdbtnMeal[2].setBackground(Color.WHITE);
        rdbtnMeal[2].setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnMeal[2].setBounds(16, 170, 240, 30);
        panelPref.add(rdbtnMeal[2]);

        PrefListener prefListener = new PrefListener();
        for (int i=0 ; i<3 ; i++)
            rdbtnMeal[i].addItemListener(prefListener);

        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1200, 760, 330, 70);
        btnOK.setForeground(Color.WHITE);
        btnOK.setBackground(new Color(11, 89, 167));
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public void setFoodName(String[] foodName) {
        this.foodName = foodName;
    }

    public JButton getNormal_food(){return mealInfoCard.getNormal_food();}

    public JButton getVegetarian_food(){return mealInfoCard.getVegetarian_food();}

    public JButton getHalal_food(){return mealInfoCard.getHalal_food();}

    public  JButton getConfirm(){return btnOK;}

    public  JRadioButton[] getrdbtnMeal(){return rdbtnMeal;}

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (mealInfoCard.getChosen() != 'd') {
                State.setPc(State.getPc() + 1);
                State.setMeal(mealInfoCard.getChosen());
                State.setSelectedPrefFood(select);
                State.setBill(State.smallBillCard.getPrice());
            } else {
                mealInfoCard.setBorder(tipBorder);
                State.setMeal('d');
            }
        }
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            for (int i=0 ; i<3 ; i++) {
                if (e.getSource() == rdbtnMeal[i] && rdbtnMeal[i].isSelected()) {
                    State.smallBillCard.addPrice(price[i]);
                    select[i] = true;
                }
                else if (e.getSource() == rdbtnMeal[i] && !rdbtnMeal[i].isSelected()) {
                    State.smallBillCard.subPrice(price[i]);
                    select[i] = false;
                }
            }
        }
    }
}

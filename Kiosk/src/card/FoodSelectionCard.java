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

public class FoodSelectionCard extends JPanel {

    private JRadioButton[] rdbtnMeal = new JRadioButton[3];

    private FoodInfoCard foodInfoCard;
    private SmallBillCard smallBillCard;

    private int[] price = new int[3];
    private String[] foodName = new String[3];
    private boolean[] select = State.getSelectedPrefFood();

    private Border tipBorder1 = BorderFactory
            .createTitledBorder(BorderFactory.createLineBorder(Color.WHITE)
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , Color.RED);
    private Border tipBorder2 = BorderFactory
            .createMatteBorder(5,5,5,5,Color.RED);
    private JLabel tip = new JLabel("Please select your meal");
    private Border tipBorder = BorderFactory
            .createCompoundBorder(tipBorder1,tipBorder2);

    public FoodSelectionCard() {

        for(int i = 0; i < 3; i++) {
            this.price[i] = State.getPrefFoodPrice()[i];
            this.foodName[i] = State.getPrefFoodName()[i];
        }

        rdbtnMeal[0] = new JRadioButton();
        rdbtnMeal[1] = new JRadioButton();
        rdbtnMeal[2] = new JRadioButton();

        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        setLayout(null);
        setSize(1540, 880);

        foodInfoCard = new FoodInfoCard();
        foodInfoCard.setBounds(40, 20, 1450, 420);
        //foodInfoCard.setBorder(new LineBorder(Color.WHITE));
        add(foodInfoCard);

        JLabel lblNewLabel = new JLabel("Preference");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel.setBounds(114, 584, 229, 40);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(":  $" + price[0]);
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(340, 641, 90, 24);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(":  $" + price[1]);
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(340, 681, 90, 24);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel(":  $" + price[2]);
        lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(340, 721, 90, 24);
        add(lblNewLabel_1_2);
        
        rdbtnMeal[0].setText(foodName[0]);
        rdbtnMeal[0].setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal[0].setForeground(Color.DARK_GRAY);
        rdbtnMeal[0].setBounds(96, 642, 220, 23);
        add(rdbtnMeal[0]);
        
        rdbtnMeal[1].setText(foodName[1]);
        rdbtnMeal[1].setForeground(Color.DARK_GRAY);
        rdbtnMeal[1].setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnMeal[1].setBounds(96, 682, 220, 23);
        add(rdbtnMeal[1]);
        
        rdbtnMeal[2].setText(foodName[2]);
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

        smallBillCard = new SmallBillCard(0);
        smallBillCard.setBounds(1126, 667, 265, 115);
        add(smallBillCard);
    }

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (foodInfoCard.getChosen() != 'd') {
                State.setPc(State.getPc() + 1);
                //上传已选数据
                State.setMeal(foodInfoCard.getChosen());
                State.setSelectedPrefFood(select);
                State.setBill(smallBillCard.getPrice());
            } else {
                foodInfoCard.setBorder(tipBorder);
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

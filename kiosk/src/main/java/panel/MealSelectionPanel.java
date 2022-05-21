package panel;

import card.MealInfoCard;
import card.MealPreferenceCard;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class can return a meal selection panel.
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 3.1
 * UI upgrade, remove redundancy
 * @date 2022/4/26
 *
 * @version 3.0
 * Bug fixed.
 * @date 2022/4/22
 *
 * @version 1.4
 * Appearance improved.
 * @date 2022/3/28
 *
 * @version 1.3
 * GUI appearance redesigned.
 * @date 2022/3/25
 *
 * @version 1.2
 * Add setters and remove parameters
 * @date 2022/3/25
 *
 * @version 1.1
 * @date 2022/3/23
 *
 * @version 1.0
 * @date 2022/3/19
 */

public class MealSelectionPanel extends JPanel {

    private MealInfoCard mealInfoCard;
    MealPreferenceCard mealPreferenceCard;

    private JButton btnOK = new JButton("OK");

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(205,92,92))
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , new Color(205,92,92));

    public MealSelectionPanel(boolean cheat) {}

    public MealSelectionPanel() {


        setBackground(Theme.getBackgroundColor());
        setForeground(Theme.getMinorFontColor());
        setLayout(null);
        setSize(1600, 880);

        mealInfoCard = new MealInfoCard();
        mealInfoCard.setLocation(10, 0);
        add(mealInfoCard);

        mealPreferenceCard = new MealPreferenceCard();
        mealPreferenceCard.setLocation(1220, 50);
        add(mealPreferenceCard);

        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1220, 760, 330, 70);
        btnOK.setForeground(Theme.getMinorFontColor());
        btnOK.setBackground(Theme.getThemeColor());
        btnOK.addActionListener(okListener);
        btnOK.setBorderPainted(false);
        btnOK.setFocusPainted(false);
        add(btnOK);
    }

    public JButton getNormal_food(){return mealInfoCard.getNormal_food();}

    public JButton getVegetarian_food(){return mealInfoCard.getVegetarian_food();}

    public JButton getHalal_food(){return mealInfoCard.getHalal_food();}

    public  JButton getConfirm(){return btnOK;}

    public  JRadioButton[] getrdbtnMeal(){return mealPreferenceCard.getPref();}

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (mealInfoCard.getChosen() != 'd') {
                State.setPc(6);
                State.setMeal(mealInfoCard.getChosen());
                State.setSelectedPrefFood(mealPreferenceCard.getSelection());
                State.setBill(State.getSmallBillCard().getPrice());
            } else {
                mealInfoCard.setBorder(tipBorder);
                State.setMeal('d');
            }
        }
    }
}

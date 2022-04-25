package card;

import dbReader.FlightReader;
import dbReader.MealPreferenceReader;
import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This card can return a meal preference card
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 4/26
 */
public class MealPreferenceCard extends JPanel {

    private JRadioButton[] pref = new JRadioButton[3];

    private int[] price = new int[3];
    private String[] foodName = new String[3];
    private boolean[] select = {false, false, false};
    private String[] detail = new String[3];

    public MealPreferenceCard() {
        setSize(330, 550);
        setBackground(Theme.getCardColor());
        setLayout(null);

        int[] p = FlightReader.getMealPreference(FlightReader.indexOf(State.getIdFlight()));
        for (int i = 0; i < 3; i++) {
            price[i] = MealPreferenceReader.getPrice(p[i]);
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j > i; j--) {
                if (price[j] < price[j - 1]) {
                    int temp = price[j];
                    price[j] = price[j - 1];
                    price[j - 1] = temp;
                    temp = p[j];
                    p[j] = p[j - 1];
                    p[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            foodName[i] = MealPreferenceReader.getName(p[i]);
            detail[i] = MealPreferenceReader.getIntroduction(p[i]);
        }
        State.setPrefFoodName(foodName);
        State.setPrefFoodPrice(price);

        PrefListener prefListener = new PrefListener();
        for(int i = 0; i < 3; i++) {
            pref[i] = new JRadioButton();
        }

        JLabel lblTitle = new JLabel("Preference");
        lblTitle.setForeground(Theme.getMainFontColor());
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lblTitle.setBounds(0, 24, 330, 40);
        add(lblTitle);
        
        for (int i = 0; i < 4; i++) {
            JLabel line = new JLabel();
            line.setBounds(15, i * 150 + 80, 300, 2);
            line.setOpaque(true);
            line.setBackground(Theme.getTertiaryFontColor());
            add(line);

            if (i == 3)
                break;

        pref[i].setText(foodName[i]);
        pref[i].setForeground(Theme.getMainFontColor());
        pref[i].setBackground(Theme.getCardColor());
        pref[i].setFont(new Font("Arial", Font.PLAIN, 25));
        pref[i].setBounds(25, i * 150 + 90, 300, 30);
//        pref[i].setIcon(new ImageIcon(new ImageIcon("Kiosk/icons/selectBoxEmpty.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
//        pref[i].setSelectedIcon(new ImageIcon(new ImageIcon("Kiosk/icons/selectBoxSelected.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        add(pref[i]);
        pref[i].addItemListener(prefListener);

        JLabel lblPrice = new JLabel("$" + price[i]);
        lblPrice.setForeground(Theme.getMainFontColor());
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrice.setBounds(200, i * 150 + 193, 115, 35);
        add(lblPrice);

        JTextArea lblDetail = new JTextArea(detail[i]);
        lblDetail.setLineWrap(true);
        lblDetail.setWrapStyleWord(true);
        lblDetail.setEditable(false);
        lblDetail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDetail.setForeground(Theme.getSecondaryFontColor());
        lblDetail.setBounds(48, i * 150 +125, 250, 100);
        add(lblDetail);
        }
    }

    public boolean[] getSelection() {
        return select;
    }

    public JRadioButton[] getPref() {
        return pref;
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            for (int i=0 ; i<3 ; i++) {
                if (e.getSource() == pref[i] && pref[i].isSelected()) {
                    State.smallBillCard.addPrice(price[i]);
                    select[i] = true;
                }
                else if (e.getSource() == pref[i] && !pref[i].isSelected()) {
                    State.smallBillCard.subPrice(price[i]);
                    select[i] = false;
                }
            }
        }
    }
}

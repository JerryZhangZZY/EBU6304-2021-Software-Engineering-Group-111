package card;

import main.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * a card of food, you can choose some kinds of food in flight
 *
 * @author Zhang Zeyu
 * @author Wang Chenyu
 *
 *
 * @version 1.1
 * @date 2022/3/25
 * GUI appearance redesigned and code formatted.
 *
 * @version 1.0
 * @date 3/23
 *
 */

public class MealInfoCard extends JPanel {
    private char chosen='d';
    private JButton normal_food = new JButton();
    private JButton vegetarian_food = new JButton();
    private JButton halal_food = new JButton();
    private ImageIcon icon1_normal = new ImageIcon("Kiosk/icons/normal.png");
    private Image img_normal = icon1_normal.getImage();
    private Image newimg_normal= img_normal.getScaledInstance(350, 780, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_normal = new ImageIcon(newimg_normal);
    private ImageIcon icon1_vegetarian = new ImageIcon("Kiosk/icons/vegetarian.png");
    private Image img_vegetarian = icon1_vegetarian.getImage();
    private Image newimg_vegetarian= img_vegetarian.getScaledInstance(350, 780, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_vegetarian = new ImageIcon(newimg_vegetarian);
    private ImageIcon icon1_halal = new ImageIcon("Kiosk/icons/halal.png");
    private Image img_halal = icon1_halal.getImage();
    private Image newimg_halal= img_halal.getScaledInstance(350, 780, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_halal = new ImageIcon(newimg_halal);
    private Border button_chosen = BorderFactory.createLineBorder(new Color(60,179,113),10, true);
    public MealInfoCard(){
        setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1130, 880);
        ChosenListener chosenListener = new ChosenListener();
        normal_food.setLocation(30, 50);
        normal_food.setSize(350, 780);
        normal_food.setBorder(null);
        normal_food.setContentAreaFilled(false);
        normal_food.setIcon(icon_normal);
        normal_food.addActionListener(chosenListener);

        vegetarian_food.setLocation(405, 50);
        vegetarian_food.setSize(350, 780);
        vegetarian_food.setBorder(null);
        vegetarian_food.setContentAreaFilled(false);
        vegetarian_food.setIcon(icon_vegetarian);
        vegetarian_food.addActionListener(chosenListener);

        halal_food.setLocation(780, 50);
        halal_food.setContentAreaFilled(false);
        halal_food.setSize(350, 780);
        halal_food.setBorder(null);
        halal_food.setIcon(icon_halal);
        halal_food.addActionListener(chosenListener);

        add(normal_food);
        add(vegetarian_food);
        add(halal_food);
    }
    private class ChosenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setBorder(new LineBorder(Theme.getBackgroundColor()));
            if (e.getSource() == normal_food) {
                if (chosen == 'a') {
                    chosen = 'd';
                    normal_food.setBounds(30, 50, 350, 780);
                    normal_food.setBorder(null);
                } else {
                    if (chosen == 'b') {
                        vegetarian_food.setBounds(405, 50, 350, 780);
                        vegetarian_food.setBorder(null);
                    } else if (chosen == 'c') {
                        halal_food.setBounds(780, 50, 350, 780);
                        halal_food.setBorder(null);
                    }
                    chosen = 'a';
                    normal_food.setBounds(26, 41, 358, 798);
                    normal_food.setBorder(button_chosen);
                }
            } else if (e.getSource() == vegetarian_food) {
                if (chosen == 'b') {
                    chosen = 'd';
                    vegetarian_food.setBounds(405, 50, 350, 780);
                    vegetarian_food.setBorder(null);
                } else {
                    if (chosen == 'a') {
                        normal_food.setBounds(30, 50, 350, 780);
                        normal_food.setBorder(null);
                    } else if (chosen == 'c') {
                        halal_food.setBounds(780, 50, 350, 780);
                        halal_food.setBorder(null);
                    }
                    chosen = 'b';
                    vegetarian_food.setBounds(401, 41, 358, 798);
                    vegetarian_food.setBorder(button_chosen);
                }
            } else if (e.getSource() == halal_food) {
                if (chosen == 'c') {
                    chosen = 'd';
                    halal_food.setBounds(780, 50, 350, 780);
                    halal_food.setBorder(null);
                } else {
                    if (chosen == 'a') {
                        normal_food.setBounds(30, 50, 350, 780);
                        normal_food.setBorder(null);
                    } else if (chosen == 'b') {
                        vegetarian_food.setBounds(405, 50, 350, 780);
                        vegetarian_food.setBorder(null);
                    }
                    chosen = 'c';
                    halal_food.setBounds(776, 41, 358, 798);
                    halal_food.setBorder(button_chosen);
                }
            }
        }

    }

    public char getChosen(){
        return this.chosen;
    }

    public JButton getNormal_food(){return normal_food;}

    public JButton getVegetarian_food(){return vegetarian_food;}

    public JButton getHalal_food(){return halal_food;}

}





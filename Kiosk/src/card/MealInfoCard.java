package card;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/23
 * a card of food, you can choose some kinds of food in flight
 */

public class MealInfoCard extends JPanel {
    private char chosen='d';
    private JButton normal_food = new JButton();
    private JButton vegetarian_food = new JButton();
    private JButton halal_food = new JButton();
    private ImageIcon icon1_normal = new ImageIcon("Kiosk/icons/normal.png");
    private Image img_normal = icon1_normal.getImage();
    private Image newimg_normal= img_normal.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_normal = new ImageIcon(newimg_normal);
    private ImageIcon icon1_vegetarian = new ImageIcon("Kiosk/icons/vegetarian.png");
    private Image img_vegetarian = icon1_vegetarian.getImage();
    private Image newimg_vegetarian= img_vegetarian.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_vegetarian = new ImageIcon(newimg_vegetarian);
    private ImageIcon icon1_halal = new ImageIcon("Kiosk/icons/halal.png");
    private Image img_halal = icon1_halal.getImage();
    private Image newimg_halal= img_halal.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_halal = new ImageIcon(newimg_halal);
    private Border button_use = BorderFactory.createLineBorder(Color.DARK_GRAY,8,true);
    private Border button_chosen = BorderFactory.createLineBorder(new Color(60,179,113),8,true);
    public MealInfoCard(){
        //setBorder(new LineBorder(Color.WHITE));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1600, 880);
        ChosenListener chosenListener = new ChosenListener();
        normal_food.setBackground(Color.WHITE);
        normal_food.setLocation(40, 40);
        normal_food.setSize(390, 315);
        normal_food.setContentAreaFilled(false);
        normal_food.setBorder(button_use);
        normal_food.setIcon(icon_normal);
        normal_food.addActionListener(chosenListener);


        vegetarian_food.setBackground(Color.WHITE);
        vegetarian_food.setLocation(530, 40);
        vegetarian_food.setSize(390, 315);
        vegetarian_food.setContentAreaFilled(false);
        vegetarian_food.setBorder(button_use);
        vegetarian_food.setIcon(icon_vegetarian);
        vegetarian_food.addActionListener(chosenListener);

        halal_food.setBackground(Color.WHITE);
        halal_food.setLocation(1020, 40);
        halal_food.setContentAreaFilled(false);
        halal_food.setSize(390, 315);
        halal_food.setBorder(button_use);
        halal_food.setIcon(icon_halal);
        halal_food.addActionListener(chosenListener);

        add(normal_food);
        add(vegetarian_food);
        add(halal_food);
    }
    private class ChosenListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setBorder(new LineBorder(Color.WHITE));
            if(e.getSource()==normal_food){
                if(chosen=='a'){
                    chosen='d';
                    normal_food.setBorder(button_use);
                }
                else {
                    if(chosen=='b'){
                        vegetarian_food.setBorder(button_use);
                    }
                    else if(chosen=='c') {
                        halal_food.setBorder(button_use);
                    }
                    chosen = 'a';
                    normal_food.setBorder(button_chosen);
                }
            }
            else if(e.getSource()==vegetarian_food){
                if(chosen=='b'){
                    chosen='d';
                    vegetarian_food.setBorder(button_use);
                }
                else {
                    if(chosen=='a'){
                        normal_food.setBorder(button_use);
                    }
                    else if(chosen=='c'){
                        halal_food.setBorder(button_use);
                    }
                    chosen='b';
                    vegetarian_food.setBorder(button_chosen);
                }
            }
            else if(e.getSource()==halal_food){
                if(chosen=='c'){
                    chosen='d';
                    halal_food.setBorder(button_use);
                }
                else {
                    if(chosen=='a'){
                        normal_food.setBorder(button_use);
                    }
                    else if(chosen=='b'){
                        vegetarian_food.setBorder(button_use);
                    }
                    chosen='c';
                    halal_food.setBorder(button_chosen);
                }
            }
        }

    }

    public char getChosen(){
        return this.chosen;
    }

}





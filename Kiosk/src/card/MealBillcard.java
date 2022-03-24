package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/23
 * a card of meal bill
 */
public class MealBillcard extends JPanel {
    public MealBillcard(char basis,String extr1,String extr2,String extr3,int bill1,int bill2,int bill3){
        setBorder(new LineBorder(Color.darkGray));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1111, 450);
        JLabel headline = new JLabel("Meal:");
        headline.setBounds(24, 20, 157, 69);
        headline.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        add(headline);

        JLabel basis_meal = new JLabel();
        basis_meal.setBounds(174, 25, 300, 58);
        String food;
        if(basis=='a')
            food = "Standard";
        else if(basis=='b')
            food ="Vegetarian";
        else
            food = "Halal";
        basis_meal.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        basis_meal.setText(food);
        add(basis_meal);

        JLabel extr_one = new JLabel();
        extr_one.setText("·"+extr1);
        extr_one.setLocation(174, 132);
        extr_one.setFont(new Font("Arial", Font.BOLD, 38));
        extr_one.setForeground(Color.GRAY);
        extr_one.setSize(279, 69);
        add(extr_one);

        JLabel extr_two = new JLabel();
        extr_two.setText("·"+extr2);
        extr_two.setLocation(174, 242);
        extr_two.setSize(279, 69);
        extr_two.setFont(new Font("Arial", Font.BOLD, 38));
        extr_two.setForeground(Color.GRAY);
        add(extr_two);

        JLabel extr_three = new JLabel();
        extr_three.setText("·"+extr3);
        extr_three.setLocation(174, 343);
        extr_three.setSize(279, 69);
        extr_three.setFont(new Font("Arial", Font.BOLD, 38));
        extr_three.setForeground(Color.GRAY);
        add(extr_three);

        JLabel bill_one = new JLabel();
        bill_one.setLocation(970, 132);
        bill_one.setSize(127, 69);
        bill_one.setText("$"+String.valueOf(bill1));
        bill_one.setForeground(Color.ORANGE);
        bill_one.setFont(new Font("Arial", Font.BOLD, 38));
        add(bill_one);

        JLabel bill_two = new JLabel();
        bill_two.setForeground(Color.ORANGE);
        bill_two.setLocation(970, 242);
        bill_two.setText("$"+String.valueOf(bill2));
        bill_two.setSize(127, 69);
        bill_two.setFont(new Font("Arial", Font.BOLD, 38));
        add(bill_two);

        JLabel bill_three = new JLabel();
        bill_three.setLocation(970, 343);
        bill_three.setSize(127, 69);
        bill_three.setForeground(Color.ORANGE);
        bill_three.setText("$"+String.valueOf(bill3));
        bill_three.setFont(new Font("Arial", Font.BOLD, 38));
        add(bill_three);

        JLabel line1 = new JLabel("·······························");
        line1.setLocation(463, 150);
        line1.setSize(413, 32);
        line1.setForeground(Color.GRAY);
        line1.setFont(new Font("Arial", Font.BOLD, 38));
        add(line1);

        JLabel line2 = new JLabel("·······························");
        line2.setLocation(463, 260);
        line2.setSize(413, 32);
        line2.setFont(new Font("Arial", Font.BOLD, 38));
        line2.setForeground(Color.GRAY);
        add(line2);

        JLabel line3 = new JLabel("·······························");
        line3.setLocation(463, 361);
        line3.setSize(413, 32);
        line3.setFont(new Font("Arial", Font.BOLD, 38));
        line3.setForeground(Color.GRAY);
        add(line3);
    }
}

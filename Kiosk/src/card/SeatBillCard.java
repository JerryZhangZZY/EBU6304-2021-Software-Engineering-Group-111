package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/23
 * a card of seat bill
 */
public class SeatBillCard extends JPanel{
    JLabel headline = new JLabel("Seat:");
    JLabel preference = new JLabel();
    JLabel row_column = new JLabel();
    JLabel bill = new JLabel();
    public SeatBillCard(int row,char column,String prefer,int pay){

        setBorder(new LineBorder(Color.darkGray));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1111, 170);

        preference.setText("·"+prefer);
        preference.setBounds(127, 99, 538, 50);
        preference.setFont(new Font("Arial", Font.BOLD, 38));
        preference.setForeground(Color.GRAY);
        add(preference);

        headline.setLocation(20, 10);
        headline.setSize(131, 61);
        headline.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        add(headline);

        row_column.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        row_column.setText(String.valueOf(row)+column);
        row_column.setLocation(161, 10);
        row_column.setSize(131, 61);
        add(row_column);

        bill.setLocation(888, 96);
        bill.setSize(104, 40);
        bill.setForeground(Color.ORANGE);
        bill.setFont(new Font("Arial", Font.BOLD, 38));
        bill.setText("$"+String.valueOf(pay));
        add(bill);
    }
}


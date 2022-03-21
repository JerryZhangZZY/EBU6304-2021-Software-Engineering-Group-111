package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class can return a panel of a seat preference info card.
 *
 * @author Liang Zhehao
 * @date 2022/3/19
 * @version 1.0
 *
 * @author Liang Zhehao
 * @date 2022/3/21
 * @version 1.1
 */

public class PrefSeatCard extends JPanel implements ItemListener {

    private JRadioButton rdbtnSeat1 = new JRadioButton();
    private JRadioButton rdbtnSeat2 = new JRadioButton();
    private JRadioButton rdbtnSeat3 = new JRadioButton();
    private JRadioButton rdbtnSeat4 = new JRadioButton();

    public PrefSeatCard(String seat1, String seat2, String seat3, String seat4,
                        float price1, float price2, float price3, float price4) {

        setForeground(Color.WHITE);
        setLayout(null);
        setSize(351,255);

        JLabel lblNewLabel = new JLabel("Preference");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel.setBounds(34, 22, 229, 40);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(":  $" + price1);
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(213, 79, 90, 24);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(":  $" + price2);
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(213, 119, 90, 24);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel(":  $" + price3);
        lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(213, 159, 90, 24);
        add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel(":  $" + price4);
        lblNewLabel_1_3.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(213, 199, 90, 24);
        add(lblNewLabel_1_3);

        rdbtnSeat1.setText(seat1);
        rdbtnSeat1.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat1.setForeground(Color.DARK_GRAY);
        rdbtnSeat1.setBounds(16, 80, 144, 23);
        add(rdbtnSeat1);
        rdbtnSeat1.setSelected(true);

        rdbtnSeat2.setText(seat2);
        rdbtnSeat2.setForeground(Color.DARK_GRAY);
        rdbtnSeat2.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat2.setBounds(16, 120, 144, 23);
        add(rdbtnSeat2);

        rdbtnSeat3.setText(seat3);
        rdbtnSeat3.setForeground(Color.DARK_GRAY);
        rdbtnSeat3.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat3.setBounds(16, 160, 159, 23);
        add(rdbtnSeat3);

        rdbtnSeat4.setText(seat4);
        rdbtnSeat4.setForeground(Color.DARK_GRAY);
        rdbtnSeat4.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat4.setBounds(16, 200, 159, 23);
        add(rdbtnSeat4);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnSeat4);
        group.add(rdbtnSeat3);
        group.add(rdbtnSeat1);
        group.add(rdbtnSeat2);

        rdbtnSeat1.addItemListener(this);
        rdbtnSeat2.addItemListener(this);
        rdbtnSeat3.addItemListener(this);
        rdbtnSeat4.addItemListener(this);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        int n = -1;
        if(e.getSource() == rdbtnSeat1 && rdbtnSeat1.isSelected())
            n = 0;
        else if(e.getSource() == rdbtnSeat2 && rdbtnSeat2.isSelected())
            n = 1;
        else if(e.getSource() == rdbtnSeat3 && rdbtnSeat3.isSelected())
            n = 2;
        else if(e.getSource() == rdbtnSeat4 && rdbtnSeat4.isSelected())
            n = 3;

        if(n != -1) {
            System.out.println(n);
            //call function to show preferable seat
            SeatInfoCard.resetScrollBar(n);
        }
    }

}

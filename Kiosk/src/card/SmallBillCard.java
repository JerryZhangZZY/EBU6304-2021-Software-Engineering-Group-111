package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * This class can return the price of you select.
 *
 * @author Wang Chenyu
 * @date 2022/3/21
 * @version 1.0
 */
public class SmallBillCard extends JPanel{
    private int price;
    JLabel screen = new JLabel();
    public SmallBillCard(int price) {
        this.price = price;

        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(265, 115);
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setText("$"+String.valueOf(price));
        screen.setBounds(101, 53, 154, 59);
        screen.setFont(new Font("Eras Bold ITC", Font.BOLD, 58));
        screen.setForeground(new Color(255,69,0));
        add(screen);

        JLabel lblNewLabel = new JLabel("Bill");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 42));
        lblNewLabel.setBounds(10, 5, 126, 61);
        add(lblNewLabel);
    }

    public void setPrice(int price) {
        this.price = price;
        screen.setText(String.valueOf(price));
    }
}
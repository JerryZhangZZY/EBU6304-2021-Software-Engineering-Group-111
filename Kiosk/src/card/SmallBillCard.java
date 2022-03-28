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
 *
 * @author Zhang Zeyu
 * @date 2022/3/21
 * @version 1.1
 * Appearance improved and bug fixed.
 *
 * @author Liang Zhehao
 * @date 2022/3/23
 * @version 1.2
 * Add addPrice and subPrice function
 */
public class SmallBillCard extends JPanel{
    private int price;
    private JLabel screen = new JLabel();
    public SmallBillCard(int price) {
        this.price = 0;
        //loadBill();

        setBorder(new LineBorder(Color.DARK_GRAY, 5));
        setBackground(Color.WHITE);
        setLayout(null);
        //setSize(265, 115);
        setBounds(1200, 600, 265, 115);
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setText("$"+ price);
        screen.setBounds(45, 53, 200, 59);
        screen.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        screen.setForeground(new Color(255,69,0));
        add(screen);

        JLabel lblNewLabel = new JLabel("Bill");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 38));
        lblNewLabel.setBounds(20, 3, 126, 61);
        add(lblNewLabel);
    }

    public void setPrice(int price) {
        this.price = price;
        screen.setText("$" + price);
    }

    public int getPrice() {
        return price;
    }

    public void addPrice(int price) {
        this.price += price;
        screen.setText("$" + this.price);
    }

    public void subPrice(int price) {
        this.price -= price;
        screen.setText("$" + this.price);
    }
}
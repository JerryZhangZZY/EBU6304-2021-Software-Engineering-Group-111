package card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class can return the price of you select.
 *
 * @author Wang Chenyu
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 1.2
 * Add addPrice and subPrice function
 * @date 2022/3/23
 *
 * @version 1.1
 * Appearance improved and bug fixed.
 * @date 2022/3/21
 *
 * @version 1.0
 * @date 2022/3/21
 */
public class SmallBillCard extends JPanel{
    private int price;
    private final JLabel screen;
    JLabel lblTitle;
    public SmallBillCard(int price) {
        this.price = 0;
        //loadBill();

        setBorder(new LineBorder(Color.DARK_GRAY, 5));
        setBackground(Color.WHITE);
        setLayout(null);
        //setSize(265, 115);
        setBounds(1200, 600, 330, 115);
        screen = new JLabel();
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setText("$"+ price);
        screen.setBounds(105, 50, 200, 59);
        screen.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        screen.setForeground(new Color(255,69,0));
        add(screen);

        lblTitle = new JLabel("Bill");
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 38));
        lblTitle.setBounds(20, 3, 126, 61);
        add(lblTitle);
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

    public void changeTitle(String title) {
        lblTitle.setText(title);
    }
}
package card;

import main.Theme;

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
 * @version 3.0
 * Location and appearance adjustment.
 * @date 2022/4/22
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
    private final JLabel lblPrice;
    JLabel lblTitle;
    public SmallBillCard(int price) {
        this.price = 0;

        setBorder(new LineBorder(Theme.getThemeColor(), 7));
        setBackground(Theme.getCardColor());
        setLayout(null);
        setBounds(1220, 652, 330, 115);
        lblPrice = new JLabel();
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrice.setText("$"+ price);
        lblPrice.setBounds(105, 40, 200, 59);
        lblPrice.setFont(new Font("Eras Bold ITC", Font.BOLD, 55));
        lblPrice.setForeground(new Color(255,69,0));
        add(lblPrice);

        lblTitle = new JLabel("Bill");
        lblTitle.setForeground(Theme.getMainFontColor());
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 50));
        lblTitle.setBounds(20, 18, 126, 61);
        add(lblTitle);
    }

    /**
     * set the price displayed in the SmallBillCard
     * @param price price
     */
    public void setPrice(int price) {
        this.price = price;
        lblPrice.setText("$" + price);
    }

    public int getPrice() {
        return price;
    }

    /**
     * add price
     * @param price price
     */
    public void addPrice(int price) {
        this.price += price;
        lblPrice.setText("$" + this.price);
    }

    /**
     * subtract price
     * @param price price
     */
    public void subPrice(int price) {
        this.price -= price;
        lblPrice.setText("$" + this.price);
    }

    public void changeTitle(String title) {
        lblTitle.setText(title);
    }
}
package panel;

import main.Config;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/26
 * Generate class which can enter the credit card ID of users
 *
 * @version 1.1
 * @author Ni Ruijie
 * @date 3/27
 * Bound 1920*800 ---> 1600*800
 *
 * @version 1.2
 * @author Zhang Zeyu
 * @date 2022/3/29
 * Redesigned GUI.
 */
public class PaymentPanel extends JPanel {
    private JTextField tfCreditId;
    private JLabel lblPrice;
    private int price;

	public PaymentPanel(int price) {
		setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1600, 880);
        setVisible(true);

        JPanel panelUnionPay = new JPanel();
        panelUnionPay.setBackground(Theme.getCardColor());
        panelUnionPay.setBorder(new LineBorder(new Color(165, 42, 42), 10, true));
        panelUnionPay.setBounds(new Rectangle(400, 140, 800, 600));
        add(panelUnionPay);
        panelUnionPay.setLayout(null);

        JPanel panelTopBar = new JPanel();
        panelTopBar.setBackground(new Color(165, 42, 42));
        panelTopBar.setBounds(10, 10, 780, 90);
        panelUnionPay.add(panelTopBar);
        panelTopBar.setLayout(null);

        JLabel lblTitle = new JLabel(" China UnionPay");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblTitle.setIcon(new ImageIcon("Kiosk/icons/unionpay.png"));
        lblTitle.setBounds(10, 10, 450, 63);
        panelTopBar.add(lblTitle);

        JPanel panelInput = new JPanel();
        panelInput.setBackground(Theme.getCardColor());
        panelInput.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
        panelInput.setBounds(225, 325, 350, 60);
        panelUnionPay.add(panelInput);
        panelInput.setLayout(null);

        tfCreditId = new JTextField();
        tfCreditId.setText("Credit card ID");
        tfCreditId.setForeground(new Color(128, 0, 0));
        tfCreditId.setBackground(new Color(165, 42, 42));
        tfCreditId.setFont(new Font("Arial", Font.BOLD, 35));
        tfCreditId.setBorder(null);
        tfCreditId.setBounds(68, 10, 270, 40);
        tfCreditId.setSelectionColor(new Color(128, 0, 0));
        tfCreditId.setSelectedTextColor(Color.BLACK);
        tfCreditId.setEditable(false);
        panelInput.add(tfCreditId);
        tfCreditId.setColumns(10);

        JLabel lblUnionCardIcon = new JLabel("");
        lblUnionCardIcon.setIcon(new ImageIcon("Kiosk/icons/unioncard.png"));
        lblUnionCardIcon.setBounds(12, 10, 44, 40);
        panelInput.add(lblUnionCardIcon);

        JPanel panelPay = new JPanel();
        panelPay.setLayout(null);
        panelPay.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
        panelPay.setBackground(Theme.getCardColor());
        panelPay.setBounds(225, 425, 350, 60);
        panelUnionPay.add(panelPay);

        JLabel lblPay = new JLabel("Pay");
        lblPay.setForeground(Color.WHITE);
        lblPay.setHorizontalAlignment(SwingConstants.CENTER);
        lblPay.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblPay.setBounds(135, 10, 80, 40);
        panelPay.add(lblPay);

        lblPrice = new JLabel("$" + price);
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setForeground(Theme.getMainFontColor());
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 60));
        lblPrice.setBounds(300, 170, 200, 60);
        panelUnionPay.add(lblPrice);

        JLabel lblPayTo = new JLabel("To: " + Config.readConfig("airportName"));
        lblPayTo.setForeground(Theme.getSecondaryFontColor());
        lblPayTo.setFont(new Font("Calibri", Font.BOLD, 20));
        lblPayTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPayTo.setBounds(250, 250, 300, 28);
        panelUnionPay.add(lblPayTo);

        panelPay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                panelPay.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
                lblPay.setForeground(Color.WHITE);
                State.setPc(State.getPc() + 1);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                panelPay.setBorder(new LineBorder(new Color(128, 0, 0), 30, true));
                lblPay.setForeground(Color.LIGHT_GRAY);
            }
        });

        tfCreditId.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                tfCreditId.setEditable(true);
                if (tfCreditId.getText().equals("Credit card ID")) {
                    tfCreditId.setText(null);
                    tfCreditId.setForeground(Color.WHITE);
                }
            }
        });

        panelUnionPay.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                tfCreditId.setEditable(false);
                if (tfCreditId.getText().equals("")) {
                    tfCreditId.setText("Credit card ID");
                    tfCreditId.setForeground(new Color(128, 0, 0));
                }
            }
        });
    }
}

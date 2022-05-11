package panel;

import main.Config;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * integrated test, calling all tests
 * @author Chenyu
 * @author Zhang Zeyu
 * @author Ni Ruijie
 * @author Li Chunlin
 *
 * @version 4.0
 * Check payment number
 * @date 5/11
 *
 * @version 3.0
 * GUI function improve
 * @date 4/25
 *
 * @version 1.1
 * Redesigned GUI.
 * @date 3/29
 *
 * @version 1.0
 * Generate class which can enter the credit card ID of users
 * @date 3/26
 */
public class PaymentPanel extends JPanel {
    private JTextField tfCreditId;
    private JLabel lblPrice;
    private JButton btnPay;

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
        tfCreditId.setColumns(10);
        panelInput.add(tfCreditId);

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

        btnPay = new JButton();
        btnPay.setText("Pay");
        btnPay.setFont(new Font("Tahoma", Font.BOLD, 35));
        btnPay.setForeground(Color.WHITE);
        btnPay.setHorizontalAlignment(SwingConstants.CENTER);
        btnPay.setVerticalAlignment(SwingConstants.CENTER);
        btnPay.setContentAreaFilled(false);
        btnPay.setBorder(null);
        btnPay.setFocusPainted(false);
        btnPay.setBounds(0,0, 350, 60);
        panelPay.add(btnPay);

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

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPay.setBorder(new LineBorder(new Color(128, 0, 0), 30, true));
                if ( tfCreditId.getText().length() != 8 ){
                    setWarning();
                }else{
                    State.setPc(State.getPc() + 1);
                }
            }
        });

        tfCreditId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                tfCreditId.setEditable(true);
                if (tfCreditId.getText().equals("Credit card ID")) {
                    btnPay.setText("Pay");
                    btnPay.setFont(new Font("Tahoma", Font.BOLD, 35));
                    btnPay.setForeground(Color.WHITE);
                    btnPay.setHorizontalAlignment(SwingConstants.CENTER);
                    btnPay.setVerticalAlignment(SwingConstants.CENTER);
                    btnPay.setContentAreaFilled(false);
                    btnPay.setBorder(null);
                    btnPay.setFocusPainted(false);
                    btnPay.setBounds(0,0, 350, 60);
                    tfCreditId.setText(null);
                    tfCreditId.setForeground(Color.WHITE);
                }
            }
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                tfCreditId.setEditable(true);
                if (tfCreditId.getText().equals("Credit card ID")) {
                    btnPay.setText("Pay");
                    btnPay.setFont(new Font("Tahoma", Font.BOLD, 35));
                    btnPay.setForeground(Color.WHITE);
                    btnPay.setHorizontalAlignment(SwingConstants.CENTER);
                    btnPay.setVerticalAlignment(SwingConstants.CENTER);
                    btnPay.setContentAreaFilled(false);
                    btnPay.setBorder(null);
                    btnPay.setFocusPainted(false);
                    btnPay.setBounds(0,0, 350, 60);
                    tfCreditId.setText(null);
                    tfCreditId.setForeground(Color.WHITE);
                }
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                tfCreditId.setEditable(false);
                if (tfCreditId.getText().equals("")){
                    tfCreditId.setText("Credit card ID");
                    tfCreditId.setForeground(new Color(128, 0, 0));
                }
            }
        });
    }

    public void setWarning() {
        btnPay.setFont(new Font("Arial", Font.ITALIC, 25));
        btnPay.setText("Invalid booking number!");
        btnPay.setForeground(new Color(255,255,255));
        btnPay.setContentAreaFilled(false);
        btnPay.setBorder(null);
        btnPay.setFocusPainted(false);
        tfCreditId.setText("");
    }

    public JButton getButtonPay(){
        return btnPay;
    }
}
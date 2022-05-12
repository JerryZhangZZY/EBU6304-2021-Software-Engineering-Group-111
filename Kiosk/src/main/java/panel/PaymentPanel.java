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
 * Check payment format
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
    private JLabel errorWarning =  new JLabel("Incorrect password format");;
    private JPanel panelUnionPay = new JPanel();

    public PaymentPanel(int price) {
        setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1600, 880);
        setVisible(true);


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

        errorWarning.setFont(new Font("Tahoma", Font.BOLD, 20));
        errorWarning.setForeground(Color.RED);
        errorWarning.setBounds(265, 392, 300, 27);
        errorWarning.setVisible(false);
        panelUnionPay.add(errorWarning);

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
                String str = tfCreditId.getText();
//                try {
//                    payFlash();
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
                boolean right =false;
                right = check(str);
                if(right)
                    State.setPc(State.getPc() + 1);
                else
                    {
                        tfCreditId.setText("Credit card ID");
                        tfCreditId.setForeground(new Color(128, 0, 0));
                        panelPay.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
                        errorWarning.setVisible(true);
                    }
            }
        });

        tfCreditId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                tfCreditId.setEditable(true);
                if (!tfCreditId.getText().equals("")){
                    errorWarning.setVisible(false);
                }
                if (tfCreditId.getText().equals("Credit card ID")) {
                    tfCreditId.setText(null);
                    tfCreditId.setForeground(Color.WHITE);
                }
            }
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                tfCreditId.setEditable(true);
                if (!tfCreditId.getText().equals("")){
                    errorWarning.setVisible(false);
                }
                if (tfCreditId.getText().equals("Credit card ID")) {
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
    public JButton getButtonPay(){
        return btnPay;
    }
    public boolean check(String str){
        if (str.length() != 8)
            return false;
        boolean right=false;
        String regex ="^[0-9]{1,8}$";
        right = str.matches(regex);
        return right;
    }
    public JTextField getTfCreditId(){
        return tfCreditId;
    }
    public JLabel getErrorWarning(){
        return errorWarning;
    }
//    public void payFlash() throws InterruptedException {
//        JLabel waiticon = new JLabel();
//        waiticon.setBounds(586, 421, 58, 67);
//        panelUnionPay.add(waiticon);
//        ArrayList<ImageIcon> icon = new ArrayList();
//        ImageIcon wait = new ImageIcon("Kiosk/icons/wait/wait0.png");
//        Image img_wait = wait.getImage();
//        Image newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        ImageIcon icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait1.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait2.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait3.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait4.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait5.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait6.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        wait = new ImageIcon("Kiosk/icons/wait/wait7.png");
//        img_wait = wait.getImage();
//        newimg_wait= img_wait.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon_wait = new ImageIcon(newimg_wait);
//        icon.add(icon_wait);
//        int a= 0,c = 0;
//        for(int i = 0; i<24 ; i++){
//            waiticon.setIcon(icon.get(a));
//            a++;
//            if(a==8){
//                a=0;
//            }
//
//        }
//    }
}
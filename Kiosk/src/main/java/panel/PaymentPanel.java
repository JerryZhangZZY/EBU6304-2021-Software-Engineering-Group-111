package panel;

import main.Config;
import main.State;
import main.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * integrated test, calling all tests
 * @author Chenyu
 * @author Zhang Zeyu
 * @author Ni Ruijie
 * @author Li Chunlin
 *
 * @version 4.1
 * Add animations.
 * @date 2022/5/12
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
    private JLabel errorWarning =  new JLabel();
    private JPanel panelUnionPay = new JPanel();
    private JPanel panelPay;
    private LoadingCard loadingCard;
    private MouseListener mouseListener;

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

        panelPay = new JPanel();
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

        errorWarning.setText("Incorrect format");
        errorWarning.setFont(new Font("Arial", Font.BOLD, 25));
        errorWarning.setForeground(Color.RED);
        errorWarning.setBounds(225, 300, 350, 25);
        errorWarning.setHorizontalAlignment(SwingConstants.CENTER);
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

        loadingCard = new LoadingCard();
        loadingCard.setBounds(12, 10, 40, 40);
        loadingCard.setVisible(false);
        loadingCard.setEnabled(false);
        panelPay.add(loadingCard);

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPay.setBorder(new LineBorder(new Color(128, 0, 0), 30, true));
                String str = tfCreditId.getText();
                boolean right =false;
                right = check(str);
                if (right)
                    loading();
                else {
                    tfCreditId.setText("Credit card ID");
                    tfCreditId.setForeground(new Color(128, 0, 0));
                    panelPay.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
                    errorWarning.setVisible(true);
                }
            }
        });
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                panelPay.setBorder(new LineBorder(new Color(128, 0, 0), 30, true));
                btnPay.setForeground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panelPay.setBorder(new LineBorder(new Color(165, 42, 42), 30, true));
                btnPay.setForeground(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        btnPay.addMouseListener(mouseListener);

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

    public static class LoadingCard extends JPanel {

        @Override
        public void setBackground(Color bg) {
            super.setBackground(new Color(165, 42, 42));
        }

        int angle = 0;
        BufferedImage img;
        {
            try {
                img = ImageIO.read(new File("Kiosk/icons/loading.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            rotate(g);
        }

        public void rotate(Graphics g) {
            angle = (angle + 3) % 360;
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.rotate(Math.toRadians(angle),img.getWidth() >> 1,img.getHeight() >> 1);
            g2.drawImage(img,0,0,null);
        }
    }

    public void loading() {
        btnPay.setEnabled(false);
        btnPay.removeMouseListener(mouseListener);
        tfCreditId.setEnabled(false);
        loadingCard.setEnabled(true);
        loadingCard.setVisible(true);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (150 + new Random().nextInt(50)); i++) {
                    loadingCard.repaint();
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                panelPay.remove(loadingCard);
                JLabel lblTick = new JLabel("✔");
                lblTick.setBounds(18, 10, 40, 40);
                lblTick.setForeground(new Color(60,179,113));
                lblTick.setFont(new Font("Default", Font.BOLD, 40));
                panelPay.add(lblTick);
                panelPay.repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                State.setPc(State.getPc() + 1);
            }
        });
        thread.start();
    }

    public JButton getButtonPay(){
        return btnPay;
    }

    public boolean check(String str){
        if (str.length() != 8)
            return false;
        boolean right;
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
}
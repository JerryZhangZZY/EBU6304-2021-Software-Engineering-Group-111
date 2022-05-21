package panel;

import dbReader.PassengerFlightReader;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * a panel for users to input booking number
 * to be embedded into center panel of main frame
 *
 * @author zaitian
 * @author Zhang Zeyu
 *
 * @version 5.0
 * Highlight focus on text field.
 * Appearance improvement.
 * @date 2022/5/20
 *
 * @version 4.0
 * change error hint cancelling logic
 * @date 5/20
 *
 * @version 3.0
 * Separate altButton pressed color.
 * @date 2022/4/26
 *
 * @version 2.0
 * setBookingNum() -> setBookingNumList().
 * @date 2022/4/10
 *
 * @version 1.4
 * Add ENTER listener.
 * @date 2022/3/29
 *
 * @version 1.3
 * Appearance improved.
 * @date 2022/3/28
 *
 * @version 1.2
 * Appearance improved.
 * @date 2022/3/27
 *
 * @version 1.1
 * Set bookingNum and passengerName to State
 * and add bookingNumber validation check
 * and appearance improvement.
 * @date 2022/3/25
 *
 * @version 1.0
 * @date 3/19
 */

public class BookingLoginPanel extends JPanel {
    private JPanel textPanel;
    private JLabel lblInstruction;
    private JPanel InputPanel;
    private JTextField tfBookingNumber;
    private JPanel buttonPanel;
    private JButton btnOk;
    private JButton btnAlt;
    private JSeparator separator1;
    private JSeparator separator2;
    private JLabel lblOR;

    /**
     * Create the panel.
     */
    public BookingLoginPanel() {
        setBounds(new Rectangle(0, 0, 1920, 880));
        setLayout(null);
        setSize(1920, 880);
        /*
         * text panel
         */
        textPanel = new JPanel();
        textPanel.setBounds(0, 0, 1920, 280);
        textPanel.setBackground(Theme.getBackgroundColor());
        textPanel.setLayout(null);
        add(textPanel);

        lblInstruction = new JLabel("Please enter your booking number");
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruction.setFont(new Font("Arial", Font.PLAIN, 50));
        lblInstruction.setForeground(Theme.getMainFontColor());
        lblInstruction.setBounds(560, 200, 800, 80);
        textPanel.add(lblInstruction);
        /*
         * input panel
         */
        InputPanel = new JPanel();
        InputPanel.setBounds(0, 280, 1920, 200);
        InputPanel.setBackground(Theme.getBackgroundColor());
        InputPanel.setLayout(null);
        add(InputPanel);

        tfBookingNumber = new JTextField();
        tfBookingNumber.setFont(new Font("Arial", Font.PLAIN, 35));
        tfBookingNumber.setBounds(760, 60, 400, 70);
        tfBookingNumber.setForeground(Theme.getMainFontColor());
        tfBookingNumber.setBackground(Theme.getCardColor());
        tfBookingNumber.setColumns(10);
        tfBookingNumber.setHorizontalAlignment(SwingConstants.CENTER);
        tfBookingNumber.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        tfBookingNumber.setCaretColor(Theme.getTertiaryFontColor());
        InputPanel.add(tfBookingNumber);

        /*
         * button panel
         */
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 480, 1920, 400);
        buttonPanel.setBackground(Theme.getBackgroundColor());
        buttonPanel.setLayout(null);
        add(buttonPanel);

        btnOk = new JButton("OK");
        btnOk.setFont(new Font("Arial", Font.BOLD, 35));
        btnOk.setForeground(Theme.getMinorFontColor());
        btnOk.setBackground(Theme.getThemeColor());
        btnOk.setBounds(760, 10, 400, 70);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        buttonPanel.add(btnOk);

        btnAlt = new JButton("Use my ID");
        btnAlt.setFont(new Font("Arial", Font.PLAIN, 30));
        btnAlt.setForeground(Theme.getMainFontColor());
        btnAlt.setBackground(Theme.getBackgroundColor());
        btnAlt.setBounds(760, 161, 400, 70);
        btnAlt.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        btnAlt.setFocusPainted(false);
        buttonPanel.add(btnAlt);

        separator1 = new JSeparator();
        separator1.setBounds(560, 120, 360, 2);
        separator1.setForeground(Theme.getSecondaryFontColor());
        separator1.setBackground(Theme.getSecondaryFontColor());
        buttonPanel.add(separator1);

        separator2 = new JSeparator();
        separator2.setBounds(1000, 120, 360, 2);
        separator2.setForeground(Theme.getSecondaryFontColor());
        separator2.setBackground(Theme.getSecondaryFontColor());
        buttonPanel.add(separator2);

        lblOR = new JLabel("OR");
        lblOR.setForeground(Theme.getSecondaryFontColor());
        lblOR.setFont(new Font("Arial", Font.ITALIC, 20));
        lblOR.setHorizontalAlignment(SwingConstants.CENTER);
        lblOR.setBounds(931, 100, 60, 40);
        buttonPanel.add(lblOR);


        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBookingNumber.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
                if(tfBookingNumber.getText().isBlank() || !PassengerFlightReader.bookingValid(tfBookingNumber.getText())){
                    setWaring();
                }
                else {
                    List<String> bookingNumList = new ArrayList<>();
                    bookingNumList.add(tfBookingNumber.getText());
                    State.setBookingNumList(bookingNumList);
                    State.setPassengerName(PassengerFlightReader.getPassengerNameByBookingNum(tfBookingNumber.getText()));
                    reset();
                    State.setPc(3);
                }
            }
        });

        btnAlt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBookingNumber.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
                reset();
                State.setPc(2);
                UIManager.put("Button.select", Theme.getButtonPressedColor());
            }
        });

        btnAlt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                UIManager.put("Button.select", Theme.getAltButtonPressedColor());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                UIManager.put("Button.select", Theme.getButtonPressedColor());
            }
        });

        tfBookingNumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(tfBookingNumber.getForeground().equals(new Color(205,92,92))) {
                    reset();
                }
            }
        });

        tfBookingNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    btnOk.doClick();
                else if (tfBookingNumber.getForeground().equals(new Color(205,92,92))){
                    reset();
                }
            }
        });

        tfBookingNumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                tfBookingNumber.setBorder(new LineBorder(Theme.getThemeColor(), 4));
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                tfBookingNumber.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
                if (tfBookingNumber.getForeground().equals(new Color(205,92,92)))
                    reset();
                requestFocus();
            }
        });
    }

    public void reset() {
        tfBookingNumber.setText("");
        tfBookingNumber.setFont(new Font("Arial", Font.PLAIN, 35));
        tfBookingNumber.setForeground(Color.BLACK);
    }

    public void setWaring() {
        tfBookingNumber.setFont(new Font("Arial", Font.ITALIC, 25));
        tfBookingNumber.setText("Invalid booking number!");
        tfBookingNumber.setCaretPosition(0);
        tfBookingNumber.setForeground(new Color(205,92,92));
    }

    public JTextField getTfBookingNumber() {
        return tfBookingNumber;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JButton getBtnAlt() {
        return btnAlt;
    }
}

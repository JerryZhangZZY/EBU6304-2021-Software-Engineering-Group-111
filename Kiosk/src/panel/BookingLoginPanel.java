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
    private JLabel instructLabel;
    private JPanel InputPanel;
    private JTextField bookingNumberTextField;
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton altButton;
    private JSeparator separator1;
    private JSeparator separator2;
    private JLabel orLabel;

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

        instructLabel = new JLabel("Please enter your booking number");
        instructLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        instructLabel.setForeground(Theme.getMainFontColor());
        instructLabel.setBounds(560, 200, 800, 80);
        textPanel.add(instructLabel);
        /*
         * input panel
         */
        InputPanel = new JPanel();
        InputPanel.setBounds(0, 280, 1920, 200);
        InputPanel.setBackground(Theme.getBackgroundColor());
        InputPanel.setLayout(null);
        add(InputPanel);

        bookingNumberTextField = new JTextField();
        bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 35));
        bookingNumberTextField.setBounds(760, 60, 400, 70);
        bookingNumberTextField.setForeground(Theme.getMainFontColor());
        bookingNumberTextField.setBackground(Theme.getCardColor());
        bookingNumberTextField.setColumns(10);
        bookingNumberTextField.setHorizontalAlignment(SwingConstants.CENTER);
        bookingNumberTextField.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        InputPanel.add(bookingNumberTextField);

        /*
         * button panel
         */
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 480, 1920, 400);
        buttonPanel.setBackground(Theme.getBackgroundColor());
        buttonPanel.setLayout(null);
        add(buttonPanel);

        okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 35));
        okButton.setForeground(Theme.getMinorFontColor());
        okButton.setBackground(Theme.getThemeColor());
        okButton.setBounds(760, 10, 400, 70);
        okButton.setBorderPainted(false);
        buttonPanel.add(okButton);

        altButton = new JButton("Use my ID");
        altButton.setFont(new Font("Arial", Font.PLAIN, 30));
        altButton.setForeground(Theme.getMainFontColor());
        altButton.setBackground(Theme.getBackgroundColor());
        altButton.setBounds(760, 161, 400, 70);
        altButton.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        buttonPanel.add(altButton);

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

        orLabel = new JLabel("OR");
        orLabel.setForeground(Theme.getSecondaryFontColor());
        orLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orLabel.setBounds(931, 100, 60, 40);
        buttonPanel.add(orLabel);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bookingNumberTextField.getText().isBlank() || !PassengerFlightReader.bookingValid(bookingNumberTextField.getText())){
                    setWaring();
                }
                else {
                    List<String> bookingNumList = new ArrayList<>();
                    bookingNumList.add(bookingNumberTextField.getText());
                    State.setBookingNumList(bookingNumList);
                    State.setPassengerName(PassengerFlightReader.getPassengerNameByBookingNum(bookingNumberTextField.getText()));
                    reset();
                    State.setPc(3);
                }
            }
        });

        altButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                State.setPc(2);
                UIManager.put("Button.select", Theme.getButtonPressedColor());
            }
        });
        altButton.addMouseListener(new MouseAdapter() {
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

        bookingNumberTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(bookingNumberTextField.getForeground().equals(new Color(205,92,92))) {
                    bookingNumberTextField.setText("");
                    bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 35));
                    bookingNumberTextField.setForeground(Color.BLACK);
                }
            }
        });
        bookingNumberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    okButton.doClick();
                else if (bookingNumberTextField.getForeground().equals(new Color(205,92,92))){
                    reset();
                }
            }
        });
    }

    public void reset(){
        bookingNumberTextField.setText("");
        bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 35));
        bookingNumberTextField.setForeground(Color.BLACK);
    }

    public void setWaring(){
        bookingNumberTextField.setFont(new Font("Arial", Font.ITALIC, 25));
        bookingNumberTextField.setText("Invalid booking number!");
        bookingNumberTextField.setForeground(new Color(205,92,92));
    }
    public JTextField getBookingNumberTextField() {
        return bookingNumberTextField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getAltButton() {
        return altButton;
    }


}

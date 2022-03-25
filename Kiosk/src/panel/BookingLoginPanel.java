package panel;

import dbReader.PassengerFlightReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @version 1.0
 * @date 3/19
 * @author zaitian
 * a panel for users to input booking number
 * to be embedded into center panel of main frame
 *
 * @author Zhang Zeyu
 * @date 2022/3/25
 * @version 1.1
 * Set bookingNum and passengerName to State
 * and add bookingNumber validation check
 * and appearance improvement.
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
        setBackground(Color.PINK);
        setLayout(null);
        setSize(1920, 880);
        /*
        text panel
         */
        textPanel = new JPanel();
        textPanel.setBounds(0, 0, 1920, 280);
        add(textPanel);
        textPanel.setLayout(null);

        instructLabel = new JLabel("Please enter your booking number");
        instructLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        instructLabel.setForeground(Color.DARK_GRAY);
        instructLabel.setBounds(660, 150, 600, 80);
        textPanel.add(instructLabel);
        /*
        input panel
         */
        InputPanel = new JPanel();
        InputPanel.setBounds(0, 280, 1920, 200);
        add(InputPanel);
        InputPanel.setLayout(null);

        bookingNumberTextField = new JTextField();
        bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 35));
        bookingNumberTextField.setBounds(710, 20, 500, 80);
        InputPanel.add(bookingNumberTextField);
        bookingNumberTextField.setColumns(10);
        bookingNumberTextField.setHorizontalAlignment(SwingConstants.CENTER);

        /*
        button panel
         */
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 480, 1920, 400);
        add(buttonPanel);
        buttonPanel.setLayout(null);

        okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 28));
        okButton.setForeground(Color.DARK_GRAY);
        okButton.setBackground(Color.WHITE);
        okButton.setBounds(860, 20, 200, 60);
        buttonPanel.add(okButton);

        altButton = new JButton("Use my ID");
        altButton.setFont(new Font("Arial", Font.PLAIN, 24));
        altButton.setForeground(Color.DARK_GRAY);
        altButton.setBackground(Color.WHITE);
        altButton.setBounds(860, 158, 200, 60);
        buttonPanel.add(altButton);

        separator1 = new JSeparator();
        separator1.setBounds(560, 120, 360, 2);
        buttonPanel.add(separator1);

        separator2 = new JSeparator();
        separator2.setBounds(1000, 120, 360, 2);
        buttonPanel.add(separator2);

        orLabel = new JLabel("OR");
        orLabel.setForeground(Color.GRAY);
        orLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orLabel.setBounds(931, 100, 60, 40);
        buttonPanel.add(orLabel);

        bookingNumberTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                bookingNumberTextField.setText("");
                bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 35));
                bookingNumberTextField.setForeground(Color.BLACK);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bookingNumberTextField.getText().isBlank() || !PassengerFlightReader.bookingValid(bookingNumberTextField.getText())){
                    bookingNumberTextField.setFont(new Font("Arial", Font.ITALIC, 25));
                    bookingNumberTextField.setText("Invalid booking number!");
                    bookingNumberTextField.setForeground(Color.RED);
                }
                else {
                    bookingNumberTextField.setBorder(null);
                    State.setBookingNum(bookingNumberTextField.getText());
                    State.setPassengerName(PassengerFlightReader.getPassengerNameByBookingNum(bookingNumberTextField.getText()));
                    bookingNumberTextField.setText("");
                    State.setPc(3);
                }
            }
        });
        altButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingNumberTextField.setText("");
                State.setPc(2);
            }
        });
    }
}

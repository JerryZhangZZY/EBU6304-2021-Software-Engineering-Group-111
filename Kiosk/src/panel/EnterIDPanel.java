package panel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * @version 1.0
 * @date 3/19
 * @author zaitian
 * a panel for users to input booking number
 * to be embedded into center panel of main frame
 */
public class EnterIDPanel extends JPanel {
    private JTextField bookingNumberTextField;

    /**
     * Create the panel.
     */
    public EnterIDPanel() {
        setBounds(new Rectangle(0, 0, 1920, 880));
        setBackground(Color.PINK);
        setLayout(null);
        setSize(1920, 880);
        /*
        text panel
         */
        JPanel textPanel = new JPanel();
        textPanel.setBounds(0, 0, 1920, 280);
        add(textPanel);
        textPanel.setLayout(null);

        JLabel instructLabel = new JLabel("Please enter your booking number");
        instructLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        instructLabel.setBounds(660, 150, 600, 80);
        textPanel.add(instructLabel);
        /*
        input panel
         */
        JPanel InputPanel = new JPanel();
        InputPanel.setBounds(0, 280, 1920, 200);
        add(InputPanel);
        InputPanel.setLayout(null);

        bookingNumberTextField = new JTextField();
        bookingNumberTextField.setFont(new Font("Arial", Font.PLAIN, 24));
        bookingNumberTextField.setBounds(710, 20, 500, 80);
        InputPanel.add(bookingNumberTextField);
        bookingNumberTextField.setColumns(10);
        /*
        button panel
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 480, 1920, 400);
        add(buttonPanel);
        buttonPanel.setLayout(null);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 28));
        okButton.setBounds(860, 20, 200, 60);
        buttonPanel.add(okButton);

        JButton altButton = new JButton("Use my ID");
        altButton.setFont(new Font("Arial", Font.PLAIN, 24));
        altButton.setBounds(860, 158, 200, 60);
        buttonPanel.add(altButton);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(560, 120, 360, 2);
        buttonPanel.add(separator1);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(1000, 120, 360, 2);
        buttonPanel.add(separator2);

        JLabel orLabel = new JLabel("OR");
        orLabel.setForeground(Color.GRAY);
        orLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orLabel.setBounds(931, 100, 60, 40);
        buttonPanel.add(orLabel);
    }
}

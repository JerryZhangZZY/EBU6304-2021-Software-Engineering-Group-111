package panel;

import dbReader.PassengerFlightReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/26
 * Generate class which can enter the credit card ID of users
 * @version 1.1
 * @author Ni Ruijie
 * @date 3/27
 * Bound 1920*800 ---> 1600*800
 */
public class PaymentPanel extends JPanel {
	private JPanel paymentPanel;
    private JLabel progressLabel;
    private JTextField txtTry;
	/**
	 * Create the panel.
	 */
	public PaymentPanel() {
		//setBounds(new Rectangle(0, 0, 1920, 880));
		setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1600, 880);
        setVisible(true);
        
        txtTry = new JTextField();
        txtTry.setFont(new Font("Arial", Font.PLAIN, 35));
        txtTry.setBounds(500, 431, 600, 70);
        add(txtTry);
        txtTry.setColumns(10);
        
        JLabel creditNotice = new JLabel("Credit Card ID:");
        creditNotice.setHorizontalAlignment(SwingConstants.CENTER);
        creditNotice.setFont(new Font("Arial", Font.PLAIN, 55));
        creditNotice.setBounds(500, 277, 600, 87);
        add(creditNotice);
        
        JButton payButton = new JButton("Pay");
        payButton.setFont(new Font("Arial", Font.PLAIN, 50));
        payButton.setBounds(735, 615, 150, 80);
        add(payButton);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.setPc(State.getPc()+1);
            }
        });
	}
}

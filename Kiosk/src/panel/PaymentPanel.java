package panel;

import javax.swing.*;
import java.awt.*;
/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/26
 * Generate class which can enter the credit card ID of users
 */
public class PaymentPanel extends JPanel {
	private JPanel paymentPanel;
    private JLabel progressLabel;
    private JTextField txtTry;
	/**
	 * Create the panel.
	 */
	public PaymentPanel() {
		setBounds(new Rectangle(0, 0, 1920, 880));
		setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1920, 880);

        paymentPanel = new JPanel();
        paymentPanel.setBounds(320, 0, 1600, 880);
        paymentPanel.setBackground(new Color(244, 244, 244));
        add(paymentPanel);
        paymentPanel.setLayout(null);
        
        txtTry = new JTextField();
        txtTry.setFont(new Font("Arial", Font.PLAIN, 35));
        txtTry.setBounds(500, 431, 600, 70);
        paymentPanel.add(txtTry);
        txtTry.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Credit Card ID:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 55));
        lblNewLabel.setBounds(500, 277, 600, 87);
        paymentPanel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Pay");
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 50));
        btnNewButton.setBounds(735, 615, 150, 80);
        paymentPanel.add(btnNewButton);

	}
}

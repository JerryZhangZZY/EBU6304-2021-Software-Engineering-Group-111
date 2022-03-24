package card;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/24
 * a card of payment
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentCard extends JPanel{
    private String id;
    JButton confirm = new JButton("Pay");
    JTextField cardID = new JTextField();
    JLabel title = new JLabel("Credit Card ID");
    Border cardLine = BorderFactory.createLineBorder(Color.DARK_GRAY,2,true);
    Border buttonLine = BorderFactory.createLineBorder(Color.DARK_GRAY,2,true);
    public  PaymentCard(){

        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1600, 880);
        title.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
        title.setVerticalTextPosition(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(533, 155, 474, 101);
        add(title);

        cardID.setBounds(493, 271, 553, 51);
        cardID.setFont(new Font("Eras Bold ITC", Font.BOLD, 26));
        cardID.setBackground(Color.WHITE);
        cardID.setBorder(cardLine);
        add(cardID);

        confirm.setFont(new Font("Eras Bold ITC", Font.BOLD, 26));
        confirm.setBorder(buttonLine);
        confirm.setBackground(Color.WHITE);
        confirm.setBounds(714, 375, 101, 42);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id=cardID.getText();
            }
        });
        add(confirm);
    }
    public String getId(){
        return id;
    }
}

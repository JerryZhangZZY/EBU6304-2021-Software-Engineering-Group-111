package card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class returns a panel of typing login card.
 *
 * @author Li Chunlin
 * @date 2022/3/24
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/27
 * @version 1.1
 * Rewrite...
 */

public class TypeIdLoginCard extends JPanel {
    private JLabel lblSurname;
    private JLabel lblId;
    private JButton buttonOk;
    private JTextField tfSurname;
    private JTextField tfId;

    public TypeIdLoginCard(){
        setSize(1150,980);
        setBackground(new Color(244, 244, 244));
        setLayout(null);

        lblSurname = new JLabel("Surname");
        lblSurname.setBounds(375,200,200,40);
        lblSurname.setFont(new Font("Arial",Font.PLAIN,35));
        lblSurname.setForeground(Color.DARK_GRAY);
        add(lblSurname);

        lblId = new JLabel("ID");
        lblId.setBounds(375,350,200,40);
        lblId.setFont(new Font("Arial",Font.PLAIN,35));
        lblId.setForeground(Color.DARK_GRAY);
        add(lblId);

        tfSurname = new JTextField();
        tfSurname.setFont(new Font("Arial", Font.PLAIN,35));
        tfSurname.setBounds(375,240,400,70);
        tfSurname.setHorizontalAlignment(SwingConstants.CENTER);
        tfSurname.setColumns(20);
        add(tfSurname);

        tfId = new JTextField();
        tfId.setFont(new Font("Arial", Font.PLAIN,35));
        tfId.setBounds(375,390,400,70);
        tfId.setHorizontalAlignment(SwingConstants.CENTER);
        tfId.setColumns(10);
        add(tfId);

        buttonOk = new JButton("OK");
        buttonOk.setFont(new Font("Arial", Font.PLAIN,35));
        buttonOk.setBackground(new Color(11, 89, 167));
        buttonOk.setForeground(Color.WHITE);
        buttonOk.setBounds(375,540,400,70);
        add(buttonOk);

        JLabel vertical = new JLabel();
        vertical.setBounds(1145, 40, 5, 800);
        vertical.setOpaque(true);
        vertical.setBackground(new Color(11, 89, 167));
        add(vertical);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
    }

    /**
     *
     * @return "-1": Authentication failed; "0": No booking number; else: Booking number.
     */
    public String getBookingNumByTyping() {
        String bookingNum = "";
        return bookingNum;
    }

    public void reset() {
        tfSurname.setText(null);
        tfId.setText(null);
    }
}

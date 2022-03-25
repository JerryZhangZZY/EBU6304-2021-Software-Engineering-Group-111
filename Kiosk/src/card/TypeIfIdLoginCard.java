package card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class can log in the Name and ID of passengers
 *
 * @author Li Chunlin
 * @date 2022/3/24
 * @version 1.0
 *
 */

public class TypeIfIdLoginCard extends JPanel {
    private JLabel label1;
    private JLabel label12;
    private JLabel label2;
    private JLabel label22;
    private JPanel panel1;
    private JPanel panel2;
    private JButton button;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel ButtonPanel;

    public TypeIfIdLoginCard(){
        setBounds(new Rectangle(0,0,1152,880));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1152,880);

        //"Surname"
        label1 = new JLabel("Surname");
        label1.setBounds(150,50,1152,200);
        label1.setFont(new Font("Arial",Font.PLAIN,36));
        add(label1);

        //"ID"
        label2 = new JLabel("ID");
        label2.setBounds(200,280,1152,200);
        label2.setFont(new Font("Arial",Font.PLAIN,36));
        add(label2);

        //input your Surname
        panel1 = new JPanel();
        panel1.setBounds(0,100,1152,300);
        //panel1.setBackground(Color.RGBtoHSB(37,89,161,));
        add(panel1);
        panel1.setLayout(null);

        textField1 = new JTextField();
        textField1.setFont(new Font("Arial", Font.PLAIN,24));
        textField1.setBounds(326,100,500,60);
        panel1.add(textField1);
        textField1.setColumns(10);

        //input your ID
        panel2 = new JPanel();
        panel2.setBounds(0,300,1152,300);
        panel2.setBackground(Color.WHITE);
        add(panel2);
        panel2.setLayout(null);
        textField2 = new JTextField();
        textField2.setFont(new Font("Arial", Font.PLAIN,24));
        textField2.setBounds(326,100,500,60);
        panel2.add(textField2);
        textField2.setColumns(10);

        //Label: Your input is wrong
        label12 = new JLabel("Your input is wrong.");
        label12.setBounds(326,210,500,30);
        label12.setForeground(Color.RED);
        label12.setFont(new Font("Arial",Font.PLAIN,24));
        label12.setHorizontalAlignment(SwingConstants.CENTER);
        label12.setVisible(true);
        panel1.add(label12);
        label22 = new JLabel("Your input is wrong.");
        label22.setBounds(326,210,500,30);
        label22.setForeground(Color.RED);
        label22.setFont(new Font("Arial",Font.PLAIN,24));
        label22.setHorizontalAlignment(SwingConstants.CENTER);
        label22.setVisible(true);
        panel2.add(label22);

        //vertical string
        /*
        JLabel Vertical = new JLabel();
        Vertical.setSize(1,400);
        Vertical.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        add(Vertical);
        Vertical.setLocation(1151,240);
         */
        JLabel Vertical = new JLabel();
        Vertical.setSize(80, 300);
        Vertical.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        label1.add(Vertical);
        Vertical.setLocation(1120, 30);


        /*
        ImageIcon icon1 = new ImageIcon(String.valueOf(mright_label));
        icon1.setImage(icon1.getImage().getScaledInstance(1, 199, Image.SCALE_DEFAULT));
        mright_label.setIcon(icon1);
         */







        //button OK
        ButtonPanel = new JPanel();
        ButtonPanel.setBounds(0,550,1152,200);
        ButtonPanel.setBackground(Color.WHITE);
        add(ButtonPanel);
        ButtonPanel.setLayout(null);

        button = new JButton("OK");
        button.setFont(new Font("Arial", Font.PLAIN,24));
        button.setBounds(476,50,200,60);
        ButtonPanel.add(button);

        /*
        OK action
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        /*
        OK action
         */


    }
}

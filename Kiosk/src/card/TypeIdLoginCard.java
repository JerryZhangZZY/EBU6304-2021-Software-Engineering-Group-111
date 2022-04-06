package card;

import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class returns a panel of typing login card.
 *
 * @author Li Chunlin
 * @author Zhang Zeyu
 * @author zaitian
 *
 * @version 2.0
 * fix wrongly clear input bug
 * @date 4/6
 *
 * @version 1.1
 * Rewrite...
 * @date 2022/3/27
 *
 * @version 1.0
 * @date 2022/3/24
 */

public class TypeIdLoginCard extends JPanel {
    private JLabel lblSurname;
    private JLabel lblId;
    private JButton buttonOk;
    private JTextField tfSurname;
    private JTextField tfId;
    private JLabel vertical;
    private JLabel lblError;
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
        buttonOk.setFont(new Font("Arial", Font.BOLD,35));
        buttonOk.setBackground(new Color(11, 89, 167));
        buttonOk.setForeground(Color.WHITE);
        buttonOk.setBounds(375,540,400,70);
        add(buttonOk);

        vertical = new JLabel();
        vertical.setBounds(1145, 40, 5, 800);
        vertical.setOpaque(true);
        vertical.setBackground(new Color(11, 89, 167));
        add(vertical);

        lblError = new JLabel("Authentication failed!");
        lblError.setBounds(375, 495, 400, 40);
        lblError.setFont(new Font("Arial", Font.PLAIN,35));
        lblError.setForeground(Color.RED);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblError);
        lblError.setVisible(false);

        tfSurname.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (lblError.isVisible()){
                    tfSurname.setText(null);
                    lblError.setVisible(false);
                }
            }
        });

        tfId.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (lblError.isVisible()) {
                    tfId.setText(null);
                    lblError.setVisible(false);
                }
            }
        });

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> bookingNumList = getBookingNumByTyping(tfSurname.getText(), tfId.getText());
                if (bookingNumList.size() == 3)
                    lblError.setVisible(true);
                else {
                    State.setPassengerName(tfSurname.getText());
                    if (bookingNumList.size() == 0)
                        State.setBookingNum(null);
                    else
                        State.setBookingNum(bookingNumList.get(0));
                    State.setPc(3);
                }
            }
        });
    }

    /**
     * Authenticate id and surname and give back booking number(s).
     * @return [F,F,F]: Authentication failed; []: No booking number; [bn1]/[bn1,bn2]: Booking number(s).
     */
    public List<String> getBookingNumByTyping(String surName, String passengerId) {
        List<String> list = new ArrayList<>();
        try {
            if (!tfSurname.getText().equals(PassengerReader.getSurname(PassengerReader.indexOf(tfId.getText())))) {
                for(int i = 0; i < 3; i++)
                    list.add("F");
            }
            else {
                list.addAll(PassengerFlightReader.getBookingNumByPassengerId(passengerId));
            }
            return list;
        } catch (Exception e) {
            for(int i = 0; i < 3; i++)
                list.add("F");
            return list;
        }


    }

    public void reset() {
        tfSurname.setText(null);
        tfId.setText(null);
    }

    public JButton getButtonOk() {
        return buttonOk;
    }

    public JTextField getTfSurname() {
        return tfSurname;
    }

    public JTextField getTfId() {
        return tfId;
    }

    public JLabel getLblError() {
        return lblError;
    }
}

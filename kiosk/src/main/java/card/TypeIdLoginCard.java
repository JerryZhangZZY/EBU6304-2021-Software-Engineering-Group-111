package card;

import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
 * @version 5.0
 * Highlight focus on text field.
 * @date 2022/5/20
 *
 * @version 2.1
 * setBookingNum() -> setBookingNumList.
 * @date 2022/4/10
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
    private final JLabel lblSurname;
    private final JLabel lblId;
    private final JButton btnOk;
    private final JTextField tfSurname;
    private final JTextField tfId;
    private final JLabel vertical;
    private final JLabel lblError;
    public TypeIdLoginCard(){
        setSize(1150,980);
        setBackground(Theme.getBackgroundColor());
        setLayout(null);

        lblSurname = new JLabel("Surname");
        lblSurname.setBounds(375,200,200,40);
        lblSurname.setFont(new Font("Arial",Font.PLAIN,35));
        lblSurname.setForeground(Theme.getMainFontColor());
        add(lblSurname);

        lblId = new JLabel("ID");
        lblId.setBounds(375,350,200,40);
        lblId.setFont(new Font("Arial",Font.PLAIN,35));
        lblId.setForeground(Theme.getMainFontColor());
        add(lblId);

        /*
        Just use to force focus
         */
        JTextField tf = new JTextField();
        tf.setBounds(-1, -1, 0, 0);
        add(tf);

        tfSurname = new JTextField();
        tfSurname.setFont(new Font("Arial", Font.PLAIN,35));
        tfSurname.setBackground(Theme.getCardColor());
        tfSurname.setForeground(Theme.getMainFontColor());
        tfSurname.setBounds(375,240,400,70);
        tfSurname.setHorizontalAlignment(SwingConstants.CENTER);
        tfSurname.setColumns(20);
        tfSurname.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        tfSurname.setCaretColor(Theme.getTertiaryFontColor());
        add(tfSurname);

        tfId = new JTextField();
        tfId.setFont(new Font("Arial", Font.PLAIN,35));
        tfId.setBackground(Theme.getCardColor());
        tfId.setForeground(Theme.getMainFontColor());
        tfId.setBounds(375,390,400,70);
        tfId.setHorizontalAlignment(SwingConstants.CENTER);
        tfId.setColumns(10);
        tfId.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        tfId.setCaretColor(Theme.getTertiaryFontColor());
        add(tfId);

        btnOk = new JButton("OK");
        btnOk.setFont(new Font("Arial", Font.BOLD,35));
        btnOk.setBackground(Theme.getThemeColor());
        btnOk.setForeground(Theme.getMinorFontColor());
        btnOk.setBounds(375,540,400,70);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        add(btnOk);

        vertical = new JLabel();
        vertical.setBounds(1145, 40, 5, 800);
        vertical.setOpaque(true);
        vertical.setBackground(Theme.getThemeColor());
        add(vertical);

        lblError = new JLabel("Authentication failed!");
        lblError.setBounds(375, 495, 400, 40);
        lblError.setFont(new Font("Arial", Font.PLAIN,35));
        lblError.setForeground(new Color(205,92,92));
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

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBorder();
                List<String> bookingNumList = getBookingNumByTyping(tfSurname.getText(), tfId.getText());
                if (bookingNumList.size() == 3)
                    lblError.setVisible(true);
                else {
                    State.setPassengerName(tfSurname.getText());
                    if (bookingNumList.size() == 0)
                        State.setBookingNumList(new ArrayList<>());
                    else
                        State.setBookingNumList(bookingNumList);
                    State.setPc(3);
                }
            }
        });

        tfSurname.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                tfSurname.setBorder(new LineBorder(Theme.getThemeColor(), 4));
                tfId.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
            }
        });

        tfId.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                tfId.setBorder(new LineBorder(Theme.getThemeColor(), 4));
                tfSurname.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resetBorder();
                lblError.setVisible(false);
                requestFocus();
            }
        });
    }

    /**
     * Reset border of tfSurname and tfId to original.
     */
    public void resetBorder() {
        tfSurname.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
        tfId.setBorder(new LineBorder(Theme.getTertiaryFontColor(), 2));
    }

    /**
     * Authenticate id and surname and give back booking number(s).
     * @return [F,F,F]: Authentication failed; []: No booking number; [bn1]/[bn1,bn2]: Booking number(s).
     */
    public List<String> getBookingNumByTyping(String surName, String passengerId) {
        List<String> list = new ArrayList<>();
        try {
            if (!surName.equals(PassengerReader.getSurname(PassengerReader.indexOf(tfId.getText())))) {
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
        lblError.setVisible(false);
    }

    public JButton getBtnOk() {
        return btnOk;
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

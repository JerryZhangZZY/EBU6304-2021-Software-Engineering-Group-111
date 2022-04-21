package card;

import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import idCardReader.IdCardReader;
import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class returns a panel of scanning login card.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * setBookingNum() -> setBookingNumList().
 * @date 2022/4/10
 *
 * @version 1.2
 * Fixed a bug.
 * @date 2022/3/29
 *
 * @version 1.1
 * Performance improvement.
 * @date 2022/3/28
 *
 * @version 1.0
 * @date 2022/3/22
 *
 */

public class ScanIdLoginCard extends JPanel {

    private JLabel lblScanningImg;

    private JButton buttonScan;

    private JLabel lblError;

    public ScanIdLoginCard() {
        setSize(770, 980);
        setBackground(Theme.getBackgroundColor());
        setLayout(null);

        lblScanningImg = new JLabel();
        lblScanningImg.setBounds(210, 150, 400, 400);
        lblScanningImg.setIcon(new ImageIcon("Kiosk/icons/scan.png"));
        add(lblScanningImg);

        buttonScan = new JButton("Scan");
        buttonScan.setBounds(235, 540, 300, 70);
        buttonScan.setFont(new Font("Arial", Font.BOLD,35));
        buttonScan.setBackground(Theme.getThemeColor());
        buttonScan.setForeground(Theme.getMinorFontColor());
        add(buttonScan);

        lblError = new JLabel("Scanning failed!");
        lblError.setBounds(235, 495, 300, 40);
        lblError.setFont(new Font("Arial", Font.PLAIN,35));
        lblError.setForeground(Color.RED);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblError);
        lblError.setVisible(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                lblError.setVisible(false);
            }
        });

        buttonScan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> bookingNumList = getBookingNumByScanning();
                if (bookingNumList.size() == 3)
                    lblError.setVisible(true);
                else {
                    lblError.setVisible(false);
                    try {
                        State.setPassengerName(PassengerReader.getSurname(PassengerReader.indexOf(IdCardReader.readId())));
                    } catch (Exception ex) {
                        State.setPassengerName("Mr.Nobody");
                    }
                    if (bookingNumList.size() == 0)
                        State.setBookingNumList(new ArrayList<>());
                    else
                        State.setBookingNumList(bookingNumList);
                    State.setPc(3);
                }
            }
        });
    }

    /**
     * Scan the id card and give back booking number(s).
     * @return [F,F,F]: Scan failed; []: No booking number; [bn1]/[bn1,bn2]: Booking number(s).
     */
    public List<String> getBookingNumByScanning() {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(PassengerFlightReader.getBookingNumByPassengerId(IdCardReader.readId()));
            return list;
        } catch (IOException e) {
            for(int i = 0; i < 3; i++)
                list.add("F");
            return list;
        }
    }

    public void reset() {
        lblError.setVisible(false);
    }

    public JButton getButtonScan() {
        return buttonScan;
    }

    public JLabel getLblError() {
        return lblError;
    }
}

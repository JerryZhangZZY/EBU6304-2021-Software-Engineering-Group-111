package panel;


import card.SmallBillCard;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import main.State;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class can return a seat selection panel.
 *
 * @author Wang Chenyu
 * @date 2022/3/19
 * @version 1.0
 *
 * @author Liang Zhehao
 * @date 2022/3/21
 * @version 1.1
 *
 * @author Liang Zhehao
 * @date 2022/3/21
 * @version 1.2
 *
 * @author Liang Zhehao
 * @date 2022/3/23
 * @version 1.3
 * Add bill and OK button
 */
public class SeatSelectionPanel extends JPanel {
    private int[] avail_seat = new int[6];
    private int row;
    private int totalrow;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    private int bill = 0;
    private int[] price = new int[4];
    private String[] seatName = new String[4];
    //button init
    private JButton[] button = new JButton[6];
    private JLabel row_num = new JLabel();
    private JPanel warn;
    private JScrollBar scrollBar = new JScrollBar();
    //icon loading
    private ImageIcon icon1_empty = new ImageIcon("Kiosk/icons/avail.png");
    private Image img_empty = icon1_empty.getImage();
    private Image newimg_empty = img_empty.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_empty = new ImageIcon(newimg_empty);
    private ImageIcon icon1_occu = new ImageIcon("Kiosk/icons/occu.png");
    private Image img_occu = icon1_occu.getImage();
    private Image newimg_occu = img_occu.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_occu = new ImageIcon(newimg_occu);
    private ImageIcon icon1_chonse = new ImageIcon("Kiosk/icons/chosen.png");
    private Image img_chonse = icon1_chonse.getImage();
    private Image newimg_chonse = img_chonse.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon icon_chonse = new ImageIcon(newimg_chonse);

    private SmallBillCard smallBillCard = State.smallBillCard;

    private JRadioButton rdbtnSeat1 = new JRadioButton();
    private JRadioButton rdbtnSeat2 = new JRadioButton();
    private JRadioButton rdbtnSeat3 = new JRadioButton();
    private JRadioButton rdbtnSeat4 = new JRadioButton();

    public SeatSelectionPanel() {

        for (int i = 0; i < 4; i++) {
            this.price[i] = State.getPrefSeatPrice()[i];
            this.seatName[i] = State.getPrefSeatName()[i];
        }
        row = 4;
        this.idFlight = State.getIdFlight();
        totalrow = PlaneReader.getCapacity(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight)))) / 6;

        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1600, 880);
        //add bound icon
        JLabel right_label = new JLabel();
        right_label.setSize(75, 262);
        JLabel left_label = new JLabel();
        left_label.setSize(147, 206);
        right_label.setIcon(new ImageIcon("Kiosk/icons/rightbound.png"));
        left_label.setIcon(new ImageIcon("Kiosk/icons/leftbound.png"));
        add(right_label);
        add(left_label);
        right_label.setLocation(1460, 1);
        left_label.setLocation(145, 30);
        //add mid_line
        JLabel mright_label = new JLabel();
        mright_label.setSize(118, 199);
        JLabel mleft_label = new JLabel();
        mleft_label.setSize(118, 168);
        mright_label.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        mleft_label.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        add(mright_label);
        add(mleft_label);
        mright_label.setLocation(851, 30);
        mleft_label.setLocation(734, 45);

        SimpleListener ourListener = new SimpleListener();
        for (int i = 0; i < 6; i++) {
            button[i] = new JButton("");
            button[i].setForeground(Color.WHITE);
            button[i].setBackground(Color.WHITE);
            button[i].setContentAreaFilled(false);
            button[i].setBorderPainted(false);
            add(button[i]);
            button[i].addActionListener(ourListener);
        }
        button[0].setBounds(165, 1, 221, 220);
        button[1].setBounds(332, 1, 221, 220);
        button[2].setBounds(495, 1, 221, 220);
        button[3].setBounds(931, 1, 221, 220);
        button[4].setBounds(1103, 1, 221, 220);
        button[5].setBounds(1268, 1, 221, 220);
        //row number
        row_num.setFont(new Font("Arial", Font.PLAIN, 26));
        row_num.setText(String.valueOf(row));
        row_num.setBounds(88, 103, 41, 50);
        add(row_num);
        //seat label
        JLabel lblNewLabel = new JLabel("A");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        lblNewLabel.setBounds(260, 210, 19, 26);
        add(lblNewLabel);


        JLabel lblB = new JLabel("B");
        lblB.setFont(new Font("Arial", Font.PLAIN, 26));
        lblB.setBounds(436, 210, 19, 26);
        add(lblB);

        JLabel lblC = new JLabel("C");
        lblC.setFont(new Font("Arial", Font.PLAIN, 26));
        lblC.setBounds(595, 210, 19, 26);
        add(lblC);

        JLabel lblD = new JLabel("D");
        lblD.setFont(new Font("Arial", Font.PLAIN, 26));
        lblD.setBounds(1034, 210, 19, 26);
        add(lblD);

        JLabel lblE = new JLabel("E");
        lblE.setFont(new Font("Arial", Font.PLAIN, 26));
        lblE.setBounds(1208, 210, 19, 26);
        add(lblE);

        JLabel lblF = new JLabel("F");
        lblF.setFont(new Font("Arial", Font.PLAIN, 26));
        lblF.setBounds(1372, 210, 19, 26);
        add(lblF);

        Border tipBorder = BorderFactory
                .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.RED)
                        , "Please select your seat", TitledBorder.CENTER
                        , TitledBorder.BOTTOM
                        , new Font("Arial", Font.PLAIN, 25)
                        , Color.RED);
        warn = new JPanel();
        warn.setBounds(117, 15, 1413, 255);
        warn.setBackground(new Color(244, 244, 244));
        warn.setBorder(tipBorder);
        warn.setVisible(false);
        add(warn);

        scrollBar.setBounds(34, 59, 34, 157);
        scrollBar.setBlockIncrement(1);
        resetScrollBar(0);
        add(scrollBar);

        SeatReader seatReader = new SeatReader(idFlight);
        boolean[] seat = seatReader.getSeat(4);
        int[] s = new int[6];
        for (int i = 0; i < seat.length; i++) {
            if (seat[i])
                s[i] = 0;
            else
                s[i] = 1;
        }
        setAvail_seat(s);
        addSeatIcon(s);

        addSeatIcon(avail_seat);
        ScrollListener scrollListener = new ScrollListener();
        scrollBar.addAdjustmentListener(scrollListener);

        JLabel lblNewLabel1 = new JLabel("Preference");
        lblNewLabel1.setForeground(Color.DARK_GRAY);
        lblNewLabel1.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel1.setBounds(117, 438, 229, 40);
        add(lblNewLabel1);

        JLabel lblNewLabel_1 = new JLabel(":  $" + price[0]);
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(317, 510, 90, 28);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(":  $" + price[1]);
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(317, 560, 90, 28);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel(":  $" + price[2]);
        lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(317, 610, 90, 28);
        add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel(":  $" + price[3]);
        lblNewLabel_1_3.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(317, 660, 90, 28);
        add(lblNewLabel_1_3);

        rdbtnSeat1.setText(seatName[0]);
        rdbtnSeat1.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat1.setForeground(Color.DARK_GRAY);
        rdbtnSeat1.setBounds(101, 510, 144, 28);
        add(rdbtnSeat1);
        rdbtnSeat1.setSelected(true);

        rdbtnSeat2.setText(seatName[1]);
        rdbtnSeat2.setForeground(Color.DARK_GRAY);
        rdbtnSeat2.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat2.setBounds(101, 560, 144, 28);
        add(rdbtnSeat2);

        rdbtnSeat3.setText(seatName[2]);
        rdbtnSeat3.setForeground(Color.DARK_GRAY);
        rdbtnSeat3.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat3.setBounds(101, 610, 159, 28);
        add(rdbtnSeat3);

        rdbtnSeat4.setText(seatName[3]);
        rdbtnSeat4.setForeground(Color.DARK_GRAY);
        rdbtnSeat4.setFont(new Font("Arial", Font.PLAIN, 20));
        rdbtnSeat4.setBounds(101, 660, 159, 28);
        add(rdbtnSeat4);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnSeat4);
        group.add(rdbtnSeat3);
        group.add(rdbtnSeat1);
        group.add(rdbtnSeat2);

        JPanel panel = new JPanel();
        panel.setBounds(49, 336, 544, 459);
        add(panel);

        PrefListener prefListener = new PrefListener();
        rdbtnSeat1.addItemListener(prefListener);
        rdbtnSeat2.addItemListener(prefListener);
        rdbtnSeat3.addItemListener(prefListener);
        rdbtnSeat4.addItemListener(prefListener);

        OKListener okListener = new OKListener();
        JButton btnOK = new JButton("OK");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 28));
        btnOK.setBounds(1255, 562, 154, 72);
        btnOK.setForeground(Color.DARK_GRAY);
        btnOK.setBackground(Color.WHITE);
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    //seat   button[0].setIcon(icon);
    private void addSeatIcon(int[] avail) {
        if (getTemp_row() == row) {
            avail[getTemp_column()] = 2;
            avail_seat[getTemp_column()] = 2;
        }
        for (int i = 0; i < 6; i++) {
            if (avail[i] == 1)
                button[i].setIcon(icon_occu);
            else if (avail[i] == 2)
                button[i].setIcon(icon_chonse);
            else
                button[i].setIcon(icon_empty);
        }
    }

    private void setTemp_row(int temp_row) {
        this.temp_row = temp_row;
    }

    private void setTemp_column(int temp_column) {
        this.temp_column = temp_column;
    }

    private void setAvail_seat(int[] avail_seat) {
        System.arraycopy(avail_seat, 0, this.avail_seat, 0, 6);
    }

    private int getTemp_row() {
        return temp_row;
    }

    private int getTemp_column() {
        return temp_column;
    }

    public void setSeatName(String[] seatName) {
        this.seatName = seatName;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            int n = -1;
            if (e.getSource() == rdbtnSeat1 && rdbtnSeat1.isSelected())
                n = 0;
            else if (e.getSource() == rdbtnSeat2 && rdbtnSeat2.isSelected())
                n = 1;
            else if (e.getSource() == rdbtnSeat3 && rdbtnSeat3.isSelected())
                n = 2;
            else if (e.getSource() == rdbtnSeat4 && rdbtnSeat4.isSelected())
                n = 3;

            if (n != -1) {
                resetScrollBar(n);
            }
        }
    }

    private class SimpleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int p = row;
            if (p >= 4)
                p = 0;
            int click = -1;
            for (int i = 0; i < 6; i++) {
                if (e.getSource() == button[i])
                    click = i;
            }

            if (avail_seat[click] == 0) {
                if (getTemp_row() == row) {
                    button[getTemp_column()].setIcon(icon_empty);
                    avail_seat[getTemp_column()] = 0;
                    smallBillCard.subPrice(price[p]);
                } else if (getTemp_row() <= 3 && getTemp_row() >= 1) {
                    smallBillCard.subPrice(price[getTemp_row()]);
                }
                setTemp_row(row);
                setTemp_column(click);
                avail_seat[click] = 2;
                button[click].setIcon(icon_chonse);
                warn.setVisible(false);
                smallBillCard.addPrice(price[p]);
            } else if (avail_seat[click] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[click] = 0;
                button[click].setIcon(icon_empty);
                smallBillCard.subPrice(price[p]);
            }
        }
    }


    private void resetScrollBar(int pref) {
        if (pref == 0) {
            scrollBar.setMinimum(4);
            scrollBar.setMaximum(24);
            int k = 24 - totalrow;
            scrollBar.setVisibleAmount(k);
            if (getTemp_row() >= 4) {
                scrollBar.setValue(getTemp_row());
                row = getTemp_row();
            } else {
                scrollBar.setValue(4);
                row = 4;
            }

        } else {
            scrollBar.setMinimum(pref);
            scrollBar.setMaximum(pref);
            scrollBar.setValue(pref);
            row = pref;
        }
    }

    private class ScrollListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
            row_num.setText(String.valueOf(adjustmentEvent.getValue()));
            row = adjustmentEvent.getValue();

            SeatReader seatReader = new SeatReader(idFlight);
            boolean[] seat = seatReader.getSeat(adjustmentEvent.getValue());
            int[] s = new int[6];
            for (int i = 0; i < seat.length; i++) {
                if (seat[i])
                    s[i] = 0;
                else
                    s[i] = 1;
            }
            setAvail_seat(s);
            addSeatIcon(s);
        }
    }

    public class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (getTemp_row() != -1) {
                State.setPc(State.getPc() + 1);
                State.setSeatRow(temp_row);
                State.setSeatColumn(temp_column);
                State.setBill(smallBillCard.getPrice());
                if (temp_row >= 4)
                    State.setSeatPre(0);
                else
                    State.setSeatPre(temp_row);
            } else {
                warn.setVisible(true);
            }
        }
    }
}
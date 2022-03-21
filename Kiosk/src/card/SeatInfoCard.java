package card;
/**
 * This class can return a panel of a flight info card.
 *
 * @author Wang Chenyu
 * @author Liang Zhehao
 * @date 2022/3/21
 * @version 2.0
 */

import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.AbstractAction;


public class SeatInfoCard extends JPanel {
    private int[] avail_seat = new int[6];
    private static int row;
    private static int totalrow;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    //button init
    JButton button[] = new JButton[6];
    JLabel row_num = new JLabel();
    private static JScrollBar scrollBar = new JScrollBar();
    //icon loading
    ImageIcon icon1_empty = new ImageIcon("Kiosk/src/icons/avail.png");
    Image img_empty = icon1_empty.getImage();
    Image newimg_empty = img_empty.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_empty = new ImageIcon(newimg_empty);
    ImageIcon icon1_occu = new ImageIcon("Kiosk/src/icons/occu.png");
    Image img_occu = icon1_occu.getImage();
    Image newimg_occu = img_occu.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_occu = new ImageIcon(newimg_occu);
    ImageIcon icon1_chonse = new ImageIcon("Kiosk/src/icons/chosen.png");
    Image img_chonse = icon1_chonse.getImage();
    Image newimg_chonse = img_chonse.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_chonse = new ImageIcon(newimg_chonse);

    public SeatInfoCard(String idFlight) {

//        for (int i = 0; i < 6; i++) {
//            this.avail_seat[i] = avail_seat[i];
//        }
//        this.row = row;
        row = 4;
        this.idFlight = idFlight;
        totalrow = PlaneReader.getCapacity(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight)))) / 6;

        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1529, 235);
        //add bound icon
        JLabel right_label = new JLabel();
        right_label.setSize(75, 262);
        JLabel left_label = new JLabel();
        left_label.setSize(147, 206);
        right_label.setIcon(new ImageIcon("Kiosk/src/icons/rightbound.png"));
        left_label.setIcon(new ImageIcon("Kiosk/src/icons/leftbound.png"));
        add(right_label);
        add(left_label);
        right_label.setLocation(1460, -19);
        left_label.setLocation(145, 10);
        //add mid_line
        JLabel mright_label = new JLabel();
        mright_label.setSize(118, 199);
        JLabel mleft_label = new JLabel();
        mleft_label.setSize(118, 168);
        mright_label.setIcon(new ImageIcon("Kiosk/src/icons/mid.png"));
        mleft_label.setIcon(new ImageIcon("Kiosk/src/icons/mid.png"));
        add(mright_label);
        add(mleft_label);
        mright_label.setLocation(851, 10);
        mleft_label.setLocation(734, 25);

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
        button[0].setBounds(165, -19, 221, 220);
        button[1].setBounds(332, -19, 221, 220);
        button[2].setBounds(495, -19, 221, 220);
        button[3].setBounds(931, -19, 221, 220);
        button[4].setBounds(1103, -19, 221, 220);
        button[5].setBounds(1268, -19, 221, 220);
        //row number
        row_num.setFont(new Font("Arial", Font.PLAIN, 26));
        row_num.setText(String.valueOf(row));
        row_num.setBounds(88, 83, 41, 50);
        add(row_num);
        //seat label
        JLabel lblNewLabel = new JLabel("A");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        lblNewLabel.setBounds(260, 190, 19, 26);
        add(lblNewLabel);


        JLabel lblB = new JLabel("B");
        lblB.setFont(new Font("Arial", Font.PLAIN, 26));
        lblB.setBounds(436, 190, 19, 26);
        add(lblB);

        JLabel lblC = new JLabel("C");
        lblC.setFont(new Font("Arial", Font.PLAIN, 26));
        lblC.setBounds(595, 190, 19, 26);
        add(lblC);

        JLabel lblD = new JLabel("D");
        lblD.setFont(new Font("Arial", Font.PLAIN, 26));
        lblD.setBounds(1034, 190, 19, 26);
        add(lblD);

        JLabel lblE = new JLabel("E");
        lblE.setFont(new Font("Arial", Font.PLAIN, 26));
        lblE.setBounds(1208, 190, 19, 26);
        add(lblE);

        JLabel lblF = new JLabel("F");
        lblF.setFont(new Font("Arial", Font.PLAIN, 26));
        lblF.setBounds(1372, 190, 19, 26);
        add(lblF);

//        int k=20-totalrow;
        scrollBar.setBounds(34, 59, 34, 101);
        scrollBar.setBlockIncrement(1);
//        scrollBar.setMinimum(4);
//        scrollBar.setMaximum(20);
//        scrollBar.setVisibleAmount(k);
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
    }

    //seat   button[0].setIcon(icon);
    public void addSeatIcon(int[] avail) {
        if (getTemp_row() == row)
            avail[getTemp_column()] = 2;
        for (int i = 0; i < 6; i++) {
            if (avail[i] == 1)
                button[i].setIcon(icon_occu);
            else if (avail[i] == 2)
                button[i].setIcon(icon_chonse);
            else
                button[i].setIcon(icon_empty);
        }
    }

    public void setTemp_row(int temp_row) {
        this.temp_row = temp_row;
    }

    public void setTemp_column(int temp_column) {
        this.temp_column = temp_column;
    }

    public void setAvail_seat(int[] avail_seat) {
        for (int i = 0; i < 6; i++) {
            this.avail_seat[i] = avail_seat[i];
        }
    }

//    public static void setRow(int row) {
//        this.row = row;
//    }

    public int getTemp_row() {
        return temp_row;
    }

    public int getTemp_column() {
        return temp_column;
    }

    private class SimpleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int click = -1;
            for (int i = 0; i < 6; i++) {
                if (e.getSource() == button[i])
                    click = i;
            }

            if (avail_seat[click] == 0) {
                if (getTemp_row() == row){
                    button[getTemp_column()].setIcon(icon_empty);
                    avail_seat[getTemp_column()] = 0;
                }
                setTemp_row(row);
                setTemp_column(click);
                avail_seat[click] = 2;
                button[click].setIcon(icon_chonse);
            } else if (avail_seat[click] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[click] = 0;
                button[click].setIcon(icon_empty);
            }

        }
    }


    public static void resetScrollBar(int pref) {
        if (pref == 0) {
            scrollBar.setMinimum(4);
            scrollBar.setMaximum(20);
            int k=20-totalrow;
            scrollBar.setVisibleAmount(k);
            row = 4;
            scrollBar.setValue(4);
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
}
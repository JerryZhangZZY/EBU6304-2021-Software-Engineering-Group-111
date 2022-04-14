package panel;


import card.PlaneInfoCard;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import main.State;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;

/**
 * This class can return a seat selection panel.
 *
 * @author Wang Chenyu
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 2.1
 * Improve UI appearance
 * @date 2022/4/13
 *
 * @version 2.0
 * Add PlaneInfoCard
 * @date 2022/4/11
 *
 * @version 1.4
 * Appearance improved.
 * @date 2022/3/28
 *
 * @version 1.3
 * Add bill and OK button
 * @date 2022/3/23
 *
 * @version 1.2
 * @date 2022/3/21
 *
 * @version 1.1
 * @date 2022/3/21
 *
 * @version 1.0
 * @date 2022/3/19
 */
public class SeatSelectionPanel extends JPanel {
    private int[] avail_seat = new int[6];
    private int row;
    private int totalrow;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    private int[] price = new int[4];
    private String[] seatName = new String[4];
    //button init
    private JButton[] seatButton = new JButton[6];
    private JLabel rowNum = new JLabel();
    private JPanel warn = new JPanel();
    private JScrollBar scrollBar;
    //icon loading
    private ImageIcon icon_empty = new ImageIcon(new ImageIcon("Kiosk/icons/avail.png").getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon_occupied = new ImageIcon(new ImageIcon("Kiosk/icons/occu.png").getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon_chosen = new ImageIcon(new ImageIcon("Kiosk/icons/chosen.png").getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private JButton btnOK = new JButton("OK");

    private JRadioButton rdbtnSeat1 = new JRadioButton();
    private JRadioButton rdbtnSeat2 = new JRadioButton();
    private JRadioButton rdbtnSeat3 = new JRadioButton();
    private JRadioButton rdbtnSeat4 = new JRadioButton();

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.RED)
                    , "Please select your seat", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , Color.RED);

    public SeatSelectionPanel(boolean cheat) {}

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

        //add windows
        JLabel leftWindow = new JLabel();
        JLabel rightWindow = new JLabel();
        leftWindow.setSize(46, 200);
        rightWindow.setSize(46, 200);
        leftWindow.setLocation(50, 30);
        rightWindow.setLocation(1360, 30);
        leftWindow.setIcon(new ImageIcon("Kiosk/icons/leftWindow.png"));
        rightWindow.setIcon(new ImageIcon("Kiosk/icons/rightWindow.png"));
        warn.add(leftWindow);
        warn.add(rightWindow);

        //add aisle
        JLabel leftAisle = new JLabel();
        JLabel rightAisle = new JLabel();
        leftAisle.setSize(7, 200);
        rightAisle.setSize(7, 200);
        leftAisle.setLocation(805, 30);
        rightAisle.setLocation(643, 30);
        leftAisle.setOpaque(true);
        rightAisle.setOpaque(true);
        leftAisle.setBackground(new Color(18, 150, 219));
        rightAisle.setBackground(new Color(18, 150, 219));
        warn.add(leftAisle);
        warn.add(rightAisle);

        SimpleListener ourListener = new SimpleListener();

        int seatShortInterval = 165;
        int seatLongIntervalDif = 270;
        int seatX = 70;
        for (int i = 0; i < 6; i++) {
            seatButton[i] = new JButton();
            seatButton[i].setForeground(Color.WHITE);
            seatButton[i].setBackground(Color.WHITE);
            seatButton[i].setContentAreaFilled(false);
            seatButton[i].setBorderPainted(false);
            seatButton[i].addActionListener(ourListener);
            if (i == 3)
                seatX += seatLongIntervalDif;

            seatButton[i].setBounds(seatX, 0, 220, 220);
            warn.add(seatButton[i]);

            JLabel lblColumn = new JLabel(String.valueOf((char)(65 + i)));
            lblColumn.setFont(new Font("Arial", Font.BOLD, 35));
            lblColumn.setBounds(seatX + 96, 210, 26, 26);
            warn.add(lblColumn);

            seatX += seatShortInterval;
        }

        //row number
        rowNum.setFont(new Font("Arial", Font.BOLD, 35));
        rowNum.setText(String.valueOf(row));
        rowNum.setBounds(1420, 105, 41, 50);
        warn.add(rowNum);

        warn.setLayout(null);
        warn.setBounds(0, 10, 1580, 270);
        warn.setBackground(new Color(244, 244, 244));
        warn.setVisible(true);
        add(warn);

        int idPlane = FlightReader.getIdPlane(FlightReader.indexOf(State.getIdFlight()));
        PlaneInfoCard planeInfoCard = new PlaneInfoCard(PlaneReader.getModel(PlaneReader.indexOf(idPlane)),
                PlaneReader.getCapacity(PlaneReader.indexOf(idPlane)),
                PlaneReader.getAirline(PlaneReader.indexOf(idPlane)));
        planeInfoCard.setLocation(50, 280);
        add(planeInfoCard);

        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(11, 89, 167)));
        UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(Color.WHITE));
        UIManager.put("ScrollBar.track", new ColorUIResource(new Color(244, 244, 244)));
        scrollBar = new JScrollBar();
        scrollBar.setUI(new BasicScrollBarUI());
        scrollBar.setBounds(1470, 30, 60, 200);
        scrollBar.setBlockIncrement(1);
        resetScrollBar(0);
        warn.add(scrollBar);

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

        addSeatIcon(avail_seat);
        ScrollListener scrollListener = new ScrollListener();
        scrollBar.addAdjustmentListener(scrollListener);

        JPanel prefPanel = new JPanel();
        prefPanel.setBackground(Color.WHITE);
        prefPanel.setLayout(null);
        prefPanel.setBounds(50, 550, 388, 280);
        add(prefPanel);

        JLabel lblPref = new JLabel("Preference");
        lblPref.setForeground(Color.DARK_GRAY);
        lblPref.setFont(new Font("Arial", Font.BOLD, 45));
        lblPref.setBounds(34, 22, 250, 40);
        prefPanel.add(lblPref);

        JLabel lblPrice1 = new JLabel(":  $" + price[0]);
        lblPrice1.setForeground(Color.DARK_GRAY);
        lblPrice1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice1.setBounds(260, 90, 90, 30);
        prefPanel.add(lblPrice1);

        JLabel lblPrice2 = new JLabel(":  $" + price[1]);
        lblPrice2.setForeground(Color.DARK_GRAY);
        lblPrice2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice2.setBounds(260, 130, 90, 30);
        prefPanel.add(lblPrice2);

        JLabel lblPrice3 = new JLabel(":  $" + price[2]);
        lblPrice3.setForeground(Color.DARK_GRAY);
        lblPrice3.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice3.setBounds(260, 170, 90, 30);
        prefPanel.add(lblPrice3);

        JLabel lblPrice4 = new JLabel(":  $" + price[3]);
        lblPrice4.setForeground(Color.DARK_GRAY);
        lblPrice4.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice4.setBounds(260, 210, 90, 30);
        prefPanel.add(lblPrice4);

        rdbtnSeat1.setText(seatName[0]);
        rdbtnSeat1.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat1.setForeground(Color.DARK_GRAY);
        rdbtnSeat1.setBackground(Color.WHITE);
        rdbtnSeat1.setBounds(16, 90, 240, 30);
        prefPanel.add(rdbtnSeat1);
        rdbtnSeat1.setSelected(true);

        rdbtnSeat2.setText(seatName[1]);
        rdbtnSeat2.setForeground(Color.DARK_GRAY);
        rdbtnSeat2.setBackground(Color.WHITE);
        rdbtnSeat2.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat2.setBounds(16, 130, 240, 30);
        prefPanel.add(rdbtnSeat2);

        rdbtnSeat3.setText(seatName[2]);
        rdbtnSeat3.setForeground(Color.DARK_GRAY);
        rdbtnSeat3.setBackground(Color.WHITE);
        rdbtnSeat3.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat3.setBounds(16, 170, 240, 30);
        prefPanel.add(rdbtnSeat3);

        rdbtnSeat4.setText(seatName[3]);
        rdbtnSeat4.setForeground(Color.DARK_GRAY);
        rdbtnSeat4.setBackground(Color.WHITE);
        rdbtnSeat4.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat4.setBounds(16, 210, 240, 30);
        prefPanel.add(rdbtnSeat4);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnSeat4);
        group.add(rdbtnSeat3);
        group.add(rdbtnSeat1);
        group.add(rdbtnSeat2);

        PrefListener prefListener = new PrefListener();
        rdbtnSeat1.addItemListener(prefListener);
        rdbtnSeat2.addItemListener(prefListener);
        rdbtnSeat3.addItemListener(prefListener);
        rdbtnSeat4.addItemListener(prefListener);

        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1200, 760, 330, 70);
        btnOK.setForeground(Color.WHITE);
        btnOK.setBackground(new Color(11, 89, 167));
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    private void addSeatIcon(int[] avail) {
        if (getTemp_row() == row) {
            avail[getTemp_column()] = 2;
            avail_seat[getTemp_column()] = 2;
        }
        for (int i = 0; i < 6; i++) {
            if (avail[i] == 1)
                seatButton[i].setIcon(icon_occupied);
            else if (avail[i] == 2)
                seatButton[i].setIcon(icon_chosen);
            else
                seatButton[i].setIcon(icon_empty);
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

    public JRadioButton getRdbtnSeat1() {
        return rdbtnSeat1;
    }

    public JRadioButton getRdbtnSeat2() {
        return rdbtnSeat2;
    }

    public JRadioButton getRdbtnSeat3() {
        return rdbtnSeat3;
    }

    public JRadioButton getRdbtnSeat4() {
        return rdbtnSeat4;
    }

    public JButton[] getSeatButton() {
        return seatButton;
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    public JButton getBtnOK() {
        return btnOK;
    }

    public int getTotalrow() {
        return totalrow;
    }

    public int[] getAvail_seat() {
        return avail_seat;
    }

    public JPanel getWarn() {
        return warn;
    }

    public Border getTipBorder() {
        return tipBorder;
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
                if (e.getSource() == seatButton[i])
                    click = i;
            }

            if (avail_seat[click] == 0) {
                if (getTemp_row() == row) {
                    seatButton[getTemp_column()].setIcon(icon_empty);
                    avail_seat[getTemp_column()] = 0;
                    State.smallBillCard.subPrice(price[p]);
                } else if (getTemp_row() <= 3 && getTemp_row() >= 1) {
                    State.smallBillCard.subPrice(price[getTemp_row()]);
                }
                setTemp_row(row);
                setTemp_column(click);
                avail_seat[click] = 2;
                seatButton[click].setIcon(icon_chosen);
                warn.setBorder(null);
                State.smallBillCard.addPrice(price[p]);
            } else if (avail_seat[click] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[click] = 0;
                seatButton[click].setIcon(icon_empty);
                State.smallBillCard.subPrice(price[p]);
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
            rowNum.setText(String.valueOf(adjustmentEvent.getValue()));
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
                State.setSeatColumn(temp_column + 1);
                State.setBill(State.smallBillCard.getPrice());
                if (temp_row >= 4)
                    State.setSeatPre(0);
                else
                    State.setSeatPre(temp_row);
            } else {
                warn.setBorder(tipBorder);
            }
        }
    }
}
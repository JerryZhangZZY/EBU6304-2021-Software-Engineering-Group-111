package panel;

import card.PlaneInfoCard;
import card.ScrollCard;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import main.State;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SeatSelectionPanel extends JPanel {

    private int[][] avail_seat = new int[3][];      //0: empty 1: occupied 2: selected
    private int row = 4;
    private int totalRow;
    private int totalColumn = 0;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    private int[] price = new int[4];
    private String[] seatName = new String[4];
    //button init
    private ArrayList<JButton>[] seatButton = new ArrayList[3];
    private JLabel[] rowNum = new JLabel[3];
    private JPanel warn = new JPanel();
    private ScrollCard scrollCard = new ScrollCard();
    private JScrollBar scrollBar = scrollCard.getScrollBar();
    //icon loading
    private ImageIcon icon_empty = new ImageIcon(new ImageIcon("Kiosk/icons/avail.png").getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon_occupied = new ImageIcon(new ImageIcon("Kiosk/icons/occu.png").getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon_chosen = new ImageIcon(new ImageIcon("Kiosk/icons/chosen.png").getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
    private JButton btnOK = new JButton("OK");

    private JRadioButton rdbtnNormalSeat = new JRadioButton();
    private JRadioButton rdbtnPrefSeat = new JRadioButton();
    private SeatReader seatReader;

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED)
                    , "Please select your seat", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , Color.RED);

    public SeatSelectionPanel(boolean cheat) {
    }

    public SeatSelectionPanel() {

        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1600, 880);

        seatReader =  new SeatReader(State.getIdFlight());

        for (int i = 0; i < 4; i++) {
            this.price[i] = State.getPrefSeatPrice()[i];
            this.seatName[i] = State.getPrefSeatName()[i];
        }
        this.idFlight = State.getIdFlight();

        int seatModel = PlaneReader.getSeatModel(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight))));
        int temp1 = seatModel;
        int corridor = -1;
        while (temp1 > 0) {
            totalColumn = totalColumn + temp1 % 10;
            temp1 /= 10;
            corridor++;
        }
        int[] seatM = new int[corridor + 1];
        for (int i = corridor; i >= 0; i--) {
            seatM[i] = seatModel % 10;
            seatModel /= 10;
        }

        totalRow = PlaneReader.getCapacity(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight)))) / totalColumn;

        char[] columnIndex = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q'};

        //SeatSelection
        warn.setLayout(null);
        warn.setBounds(0, 10, 1180, 540);
        warn.setBackground(new Color(244, 244, 244));
        warn.setVisible(true);
        warn.setBorder(null);
        add(warn);

        ImageIcon iconLW = new ImageIcon("Kiosk/icons/leftWindow.png");
        iconLW.setImage(iconLW.getImage().getScaledInstance(16, 100, Image.SCALE_SMOOTH));
        ImageIcon iconRW = new ImageIcon("Kiosk/icons/rightWindow.png");
        iconRW.setImage(iconRW.getImage().getScaledInstance(16, 100, Image.SCALE_SMOOTH));

        int[][] s = new int[3][];
        for (int i = 0; i < 3; i++) {
            boolean[] seat = seatReader.getSeat(4 + i);
            s[i] = new int[seat.length];
            for (int j = 0; j < seat.length; j++) {
                if (seat[j])
                    s[i][j] = 0;
                else
                    s[i][j] = 1;
            }
        }
        setAvail_seat(s);

        SeatListener seatListener = new SeatListener();

        int seatShortInterval = 940 / (totalColumn + corridor -1);
        int seatX;
        int t = 0, n = 0;

        seatX = 100;
        for (int i = 0; i < totalColumn; i++) {
            t++;
            JLabel cNum = new JLabel(String.valueOf(columnIndex[i]));
            cNum.setFont(new Font("Arial", Font.BOLD, 35));
            cNum.setBounds(seatX + 27, 95, 80, 80);
            warn.add(cNum);
            if (t == seatM[n] && n !=corridor) {
                seatX += seatShortInterval;
                n++;
                t = 0;
            }
            seatX += seatShortInterval;
        }
        for (int i = 0; i < 3; i++) {
            seatX = 100;
            seatButton[i] = new ArrayList<>();
            rowNum[i] = new JLabel(String.valueOf(row + i), SwingConstants.RIGHT);
            rowNum[i].setFont(new Font("Arial", Font.BOLD, 35));
            rowNum[i].setBounds(0, 200 + i * 100, 40, 50);
            warn.add(rowNum[i]);

            JLabel leftWindow = new JLabel();
            leftWindow.setSize(20, 100);
            leftWindow.setLocation(50, 175 + i * 100);
            leftWindow.setIcon(iconLW);
            warn.add(leftWindow);

            JLabel rightWindow = new JLabel();
            rightWindow.setSize(20, 100);
            rightWindow.setLocation(1150, 175 + i * 100);
            rightWindow.setIcon(iconRW);
            warn.add(rightWindow);

            t = 0;
            n = 0;
            for (int j = 0; j < totalColumn; j++) {
                t++;
                seatButton[i].add(j, new JButton());
                seatButton[i].get(j).setForeground(Color.WHITE);
                seatButton[i].get(j).setBackground(Color.WHITE);
                seatButton[i].get(j).setContentAreaFilled(false);
                seatButton[i].get(j).setBorderPainted(false);
                seatButton[i].get(j).setIcon(icon_empty);
                seatButton[i].get(j).addActionListener(seatListener);
                seatButton[i].get(j).setBounds(seatX, 185 + i * 100, 80, 80);
                if (t == seatM[n] && n !=corridor) {
                    seatX += seatShortInterval;
                    n++;
                    t = 0;

                    JLabel leftAisle = new JLabel();
                    leftAisle.setSize(4, 300);
                    leftAisle.setLocation(seatX + 38 - seatShortInterval / 4, 175);
                    leftAisle.setOpaque(true);
                    leftAisle.setBackground(new Color(18, 150, 219));
                    warn.add(leftAisle);

                    JLabel rightAisle = new JLabel();
                    rightAisle.setSize(4, 300);
                    rightAisle.setLocation(seatX + 38 + seatShortInterval / 4, 175);
                    rightAisle.setOpaque(true);
                    rightAisle.setBackground(new Color(18, 150, 219));
                    warn.add(rightAisle);
                }

                seatButton[i].add(j, seatButton[i].get(j));
                warn.add(seatButton[i].get(j));

                seatX += seatShortInterval;
            }
        }
        addSeatIcon(avail_seat);

        JLabel cutline2 = new JLabel("Selected");
        cutline2.setForeground(Color.DARK_GRAY);
        cutline2.setFont(new Font("Arial", Font.BOLD, 20));
        cutline2.setBounds(580, 30, 160, 50);
        warn.add(cutline2);

        ImageIcon iconSelected = new ImageIcon("Kiosk/icons/chosen.png");
        iconSelected.setImage(iconSelected.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel selected = new JLabel();
        selected.setIcon(iconSelected);
        selected.setBounds(525, 10, 75, 75);
        warn.add(selected);

        JLabel cutline1 = new JLabel("Available");
        cutline1.setForeground(Color.DARK_GRAY);
        cutline1.setFont(new Font("Arial", Font.BOLD, 20));
        cutline1.setBounds(210, 30, 160, 50);
        warn.add(cutline1);

        ImageIcon iconAvail = new ImageIcon("Kiosk/icons/avail.png");
        iconAvail.setImage(iconAvail.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel avail = new JLabel();
        avail.setIcon(iconAvail);
        avail.setBounds(155, 10, 75, 75);
        warn.add(avail);

        JLabel cutline3 = new JLabel("Occupied");
        cutline3.setForeground(Color.DARK_GRAY);
        cutline3.setFont(new Font("Arial", Font.BOLD, 20));
        cutline3.setBounds(950, 30, 160, 50);
        warn.add(cutline3);

        ImageIcon iconOccu = new ImageIcon("Kiosk/icons/occu.png");
        iconOccu.setImage(iconOccu.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel occu = new JLabel();
        occu.setIcon(iconOccu);
        occu.setBounds(905, 10, 75, 75);
        warn.add(occu);

        //ScrollCard
        scrollCard.setLocation(1200, 20);
        add(scrollCard);
        resetScrollBar(0, scrollBar);

        ScrollListener scrollListener = new ScrollListener();
        scrollBar.addAdjustmentListener(scrollListener);

        //PlaneInfoCard
        int idPlane = FlightReader.getIdPlane(FlightReader.indexOf(State.getIdFlight()));
        PlaneInfoCard planeInfoCard = new PlaneInfoCard(PlaneReader.getModel(PlaneReader.indexOf(idPlane)),
                PlaneReader.getCapacity(PlaneReader.indexOf(idPlane)),
                PlaneReader.getAirline(PlaneReader.indexOf(idPlane)));
        planeInfoCard.setLocation(500, 550);
        add(planeInfoCard);

        //PrefPanel
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

        rdbtnNormalSeat.setText(seatName[0]);
        rdbtnNormalSeat.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnNormalSeat.setForeground(Color.DARK_GRAY);
        rdbtnNormalSeat.setBackground(Color.WHITE);
        rdbtnNormalSeat.setBounds(16, 90, 240, 30);
        prefPanel.add(rdbtnNormalSeat);
        rdbtnNormalSeat.setSelected(true);

        rdbtnPrefSeat.setText(seatName[1]);
        rdbtnPrefSeat.setForeground(Color.DARK_GRAY);
        rdbtnPrefSeat.setBackground(Color.WHITE);
        rdbtnPrefSeat.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnPrefSeat.setBounds(16, 130, 240, 30);
        prefPanel.add(rdbtnPrefSeat);

        PrefListener prefListener = new PrefListener();
        rdbtnNormalSeat.addItemListener(prefListener);
        rdbtnPrefSeat.addItemListener(prefListener);

        JLabel lblPrefName1 = new JLabel(seatName[2]);
        lblPrefName1.setForeground(Color.DARK_GRAY);
        lblPrefName1.setBackground(Color.WHITE);
        lblPrefName1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrefName1.setBounds(38, 170, 240, 30);
        prefPanel.add(lblPrefName1);

        JLabel lblPrefName2 = new JLabel(seatName[3]);
        lblPrefName2.setForeground(Color.DARK_GRAY);
        lblPrefName2.setBackground(Color.WHITE);
        lblPrefName2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrefName2.setBounds(38, 210, 240, 30);
        prefPanel.add(lblPrefName2);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnNormalSeat);
        group.add(rdbtnPrefSeat);

        //OKbutton
        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1200, 760, 330, 70);
        btnOK.setForeground(Color.WHITE);
        btnOK.setBackground(new Color(11, 89, 167));
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    private void addSeatIcon(int[][] avail) {
        if (getTemp_row() >= row && getTemp_row() <= row + 2) {
            avail[getTemp_row() - row][getTemp_column()] = 2;
            avail_seat[getTemp_row() - row][getTemp_column()] = 2;
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < totalColumn; j++) {
                if (avail[i][j] == 1)
                    seatButton[i].get(j).setIcon(icon_occupied);
                else if (avail[i][j] == 2)
                    seatButton[i].get(j).setIcon(icon_chosen);
                else
                    seatButton[i].get(j).setIcon(icon_empty);
            }
    }

    private void resetScrollBar(int pref, JScrollBar scrollBar) {
        if (pref == 0) {
            scrollBar.setMinimum(4);
            scrollBar.setMaximum(totalRow + 1);
            scrollBar.setVisibleAmount(3);
            if (getTemp_row() >= 4) {
                scrollBar.setValue(getTemp_row());
                row = getTemp_row();
            } else {
                scrollBar.setValue(4);
                row = 4;
            }
        } else {
            scrollBar.setMinimum(1);
            scrollBar.setMaximum(4);
            scrollBar.setValue(1);
            scrollBar.setVisibleAmount(3);
            row = 1;
        }
    }

    private void setTemp_row(int temp_row) {
        this.temp_row = temp_row;
    }

    private void setTemp_column(int temp_column) {
        this.temp_column = temp_column;
    }

    private void setAvail_seat(int[][] avail_seat) {
        this.avail_seat = avail_seat;
    }

    private int getTemp_row() {
        return temp_row;
    }

    private int getTemp_column() {
        return temp_column;
    }

    public JRadioButton getRdbtnNormalSeat() {
        return rdbtnNormalSeat;
    }

    public JRadioButton getRdbtnPrefSeat() {
        return rdbtnPrefSeat;
    }

    public ArrayList<JButton>[] getSeatButton() {
        return seatButton;
    }

    public ScrollCard getScrollCard() {
        return scrollCard;
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    public JButton getBtnOK() {
        return btnOK;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public int getTotalColumn() {
        return totalColumn;
    }

    public int[][] getAvail_seat() {
        return avail_seat;
    }

    public JPanel getWarn() {
        return warn;
    }

    public Border getTipBorder() {
        return tipBorder;
    }

    private class SeatListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int clickRow = -1, clickColumn = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < totalColumn; j++)
                    if (e.getSource() == seatButton[i].get(j)) {
                        clickRow = i;
                        clickColumn = j;
                    }
            }
            int p1 = clickRow + row;
            if (p1 >= 4)
                p1 = 0;
            int p2 = getTemp_row();
            if (p2 >= 4)
                p2 = 0;

            if (avail_seat[clickRow][clickColumn] == 0) {
                if (getTemp_row() >= row && getTemp_row() <= row + 2) {
                    seatButton[getTemp_row() - row].get(getTemp_column()).setIcon(icon_empty);
                    avail_seat[getTemp_row() - row][getTemp_column()] = 0;
                    State.smallBillCard.subPrice(price[p2]);
                } else if (getTemp_row() <= 3 && getTemp_row() >= 1) {
                    State.smallBillCard.subPrice(price[getTemp_row()]);
                }
                setTemp_row(row + clickRow);
                setTemp_column(clickColumn);
                avail_seat[clickRow][clickColumn] = 2;
                seatButton[clickRow].get(clickColumn).setIcon(icon_chosen);
                warn.setBorder(null);
                State.smallBillCard.addPrice(price[p1]);
            } else if (avail_seat[clickRow][clickColumn] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[clickRow][clickColumn] = 0;
                seatButton[clickRow].get(clickColumn).setIcon(icon_empty);
                State.smallBillCard.subPrice(price[p1]);
            }
        }
    }

    private class ScrollListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
            row = adjustmentEvent.getValue();
            int[][] s = new int[3][];
            for (int i = 0; i < 3; i++) {
                rowNum[i].setText(String.valueOf(adjustmentEvent.getValue() + i));
                boolean[] seat = seatReader.getSeat(adjustmentEvent.getValue() + i);
                s[i] = new int[seat.length];
                for (int j = 0; j < seat.length; j++) {
                    if (seat[j])
                        s[i][j] = 0;
                    else
                        s[i][j] = 1;
                }
            }
            setAvail_seat(s);
            addSeatIcon(s);
        }
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            int n = -1;
            if (e.getSource() == rdbtnNormalSeat && rdbtnNormalSeat.isSelected())
                n = 0;
            else if (e.getSource() == rdbtnPrefSeat && rdbtnPrefSeat.isSelected())
                n = 1;

            if (n != -1) {
                resetScrollBar(n, scrollBar);
            }
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

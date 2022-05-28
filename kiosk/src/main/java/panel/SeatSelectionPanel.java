package panel;

import card.PlaneInfoCard;
import card.SeatLegendCard;
import card.ScrollCard;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import main.Config;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * This class can return a seat selection panel.
 *
 * @author Wang Chenyu
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 4.1
 * Add animations for folding/unfolding aircraft info.
 * Also improved GUI.
 *
 * @version 4.0
 * add PlaneInfoCard
 * @date 2022/5/7
 *
 * @version 3.1
 * redesign the UI, change the icon, enhance the function of displaying multiple rows
 * @date 2022/4/21
 *
 * @version 3.0
 * add function of displaying multiple rows
 * @date 2022/4/20
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

    private int[][] avail_seat;      //0: empty 1: occupied 2: selected
    private int row = 1;
    private int totalRow;
    private int totalColumn = 0;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    private int[] price = new int[4];
    //button init
    private ArrayList<JButton>[] seatButton;
    private ArrayList<JLabel>[] rowNum;
    private JPanel warn = new JPanel();
    private ScrollCard scrollCard = new ScrollCard();
    private JScrollBar scrollBar = scrollCard.getScrollBar();
    private PlaneInfoCard planeInfoCard;
    private JButton btnFold = new JButton();;
    //icon loading
    private ImageIcon[] iconEmpty = new ImageIcon[4];
    private ImageIcon[] iconSelected = new ImageIcon[5];
    private ImageIcon iconNotAvailable = new ImageIcon(new ImageIcon("kiosk/icons/seatNotAvailable.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon iconFold, iconUnfold;
    private JButton btnOK = new JButton("OK");

    private SeatReader seatReader;

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(205,92,92))
                    , "Please select your seat", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , new Color(205,92,92));

    private int[] seatPattern;
    private String[] columnNum;
    private int range = 12;

    private int freshTime = 8;

    public SeatSelectionPanel(boolean cheat) { }

    public SeatSelectionPanel() {

        try {
            int animationSpeed = Integer.parseInt(Config.readConfig("animationSpeed"));
            switch (animationSpeed) {
                case -1 -> freshTime = 0;
                case 1 -> freshTime = 17;
                case 2 -> freshTime = 12;
                case 3 -> freshTime = 8;
                case 4 -> freshTime = 5;
                case 5 -> freshTime = 2;
            }
        } catch (Exception ignored) {}

        setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1600, 880);

        iconEmpty[0] = new ImageIcon(new ImageIcon("kiosk/icons/seatStandard.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[1] = new ImageIcon(new ImageIcon("kiosk/icons/seatStandardPlus.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[2] = new ImageIcon(new ImageIcon("kiosk/icons/seatExtraLegroom.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[3] = new ImageIcon(new ImageIcon("kiosk/icons/seatPremium.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconSelected[0] = new ImageIcon(new ImageIcon("kiosk/icons/seatStandardSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconSelected[1] = new ImageIcon(new ImageIcon("kiosk/icons/seatStandardPlusSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconSelected[2] = new ImageIcon(new ImageIcon("kiosk/icons/seatExtraLegroomSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconSelected[3] = new ImageIcon(new ImageIcon("kiosk/icons/seatPremiumSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconSelected[4] = new ImageIcon(new ImageIcon("kiosk/icons/seatSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));

        seatReader =  new SeatReader(State.getIdFlight());

        System.arraycopy(State.getPrefSeatPrice(), 0, this.price, 0, 4);
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
        if (totalRow < range)
            range = totalRow;

        avail_seat = new int[range][];
        seatButton = new ArrayList[range];
        rowNum = new ArrayList[range];
        seatPattern = new int[totalRow];

        for (int i = 0; i < totalRow; i++)
            seatPattern[i] = 0;
        for (int i = 0; i < totalRow / 12; i++)
            seatPattern[i] = 3;
        for (int i = 0; i < totalRow / 6; i++)
            seatPattern[i + totalRow / 12] = 1;
        if (totalRow < 30)
            seatPattern[totalRow / 2] = 2;
        else {
            seatPattern[totalRow / 3] = 2;
            seatPattern[2 * totalRow / 3] = 2;
        }

        columnNum = PlaneReader.getColumnNum(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight)))).split(",");

        //SeatSelection
        warn.setLayout(null);
        warn.setBounds(800 - (45 * (totalColumn + corridor) + 15 * (totalColumn + corridor - 1) + 40) / 2, 0, (45 * (totalColumn + corridor) + 15 * (totalColumn + corridor - 1) + 40), 880);
        warn.setBackground(Theme.getBackgroundColor());
        warn.setVisible(true);
        warn.setBorder(null);
        add(warn);

        int[][] s = new int[range][];
        for (int i = 0; i < range; i++) {
            boolean[] seat = seatReader.getSeat(1 + i);
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

        int seatShortInterval = 60;
        int seatX;
        int t = 0, n = 0;

        seatX = 20;
        for (int i = 0; i < totalColumn; i++) {
            t++;
            JLabel cNum = new JLabel(columnNum[i], SwingConstants.CENTER);
            cNum.setFont(new Font("Arial", Font.BOLD, 25));
            cNum.setForeground(Theme.getTertiaryFontColor());
            cNum.setBounds(seatX, 30, 45, 80);
            warn.add(cNum);
            if (t == seatM[n] && n !=corridor) {
                seatX += seatShortInterval;
                n++;
                t = 0;
            }
            seatX += seatShortInterval;
        }
        for (int i = 0; i < range; i++) {
            seatX = 20;
            rowNum[i] = new ArrayList<>();
            seatButton[i] = new ArrayList<>();

            t = 0;
            n = 0;
            for (int j = 0; j < totalColumn; j++) {
                t++;
                seatButton[i].add(j, new JButton());
                seatButton[i].get(j).setContentAreaFilled(false);
                seatButton[i].get(j).setBorderPainted(false);
                seatButton[i].get(j).addActionListener(seatListener);
                seatButton[i].get(j).setBounds(seatX, 100 + i * 60, 45, 45);
                if (t == seatM[n] && n !=corridor) {
                    seatX += seatShortInterval;

                    rowNum[i].add(n, new JLabel(String.valueOf(row + i), SwingConstants.CENTER));
                    rowNum[i].get(n).setFont(new Font("Arial", Font.BOLD, 25));
                    rowNum[i].get(n).setForeground(Theme.getTertiaryFontColor());
                    rowNum[i].get(n).setBounds(seatX, 100 + i * 60, 45, 45);
                    warn.add(rowNum[i].get(n));

                    t = 0;
                    n++;
                }

                seatButton[i].add(j, seatButton[i].get(j));
                warn.add(seatButton[i].get(j));

                seatX += seatShortInterval;
            }
        }
        addSeatIcon(avail_seat);

        //ScrollCard and PlaneInfoCard
        JPanel sp = new JPanel();
        sp.setLayout(null);
        sp.setBackground(null);
        sp.setBounds(1220, 50, 330, 570);
        add(sp);

        btnFold.setBounds(0, 555, 330, 15);
        btnFold.setText("▲ more aircraft information    ");
        btnFold.setFont(new Font("Arial", Font.PLAIN, 20));
        btnFold.setForeground(Theme.getTertiaryFontColor());
        btnFold.setContentAreaFilled(false);
        btnFold.setHorizontalAlignment(SwingConstants.CENTER);
        btnFold.setBorderPainted(false);
        btnFold.setFocusPainted(false);
        btnFold.addActionListener(new FolderListener());
        sp.add(btnFold);

        planeInfoCard = new PlaneInfoCard();
        planeInfoCard.setLocation(0, 570);
        sp.add(planeInfoCard);

        scrollCard.setLocation(0, 0);
        sp.add(scrollCard);
        scrollBar.setMinimum(1);
        scrollBar.setMaximum(totalRow + 1);
        scrollBar.setVisibleAmount(range);

        ScrollListener scrollListener = new ScrollListener();
        scrollBar.addAdjustmentListener(scrollListener);

        //LegendCard
        SeatLegendCard seatLegendCard = new SeatLegendCard(iconEmpty, iconSelected[4], iconNotAvailable);
        seatLegendCard.setLocation(50, 50);
        add(seatLegendCard);

        //OKButton
        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1220, 760, 330, 70);
        btnOK.setForeground(Theme.getMinorFontColor());
        btnOK.setBackground(Theme.getThemeColor());
        btnOK.addActionListener(okListener);
        btnOK.setBorderPainted(false);
        btnOK.setFocusPainted(false);
        add(btnOK);
    }

    /**
     * Add seat icon
     * @param avail seat information
     */
    private void addSeatIcon(int[][] avail) {
        if (getTemp_row() >= row && getTemp_row() <= row + range - 1) {
            avail[getTemp_row() - row][getTemp_column()] = 2;
            avail_seat[getTemp_row() - row][getTemp_column()] = 2;
        }
        for (int i = 0; i < range; i++)
            for (int j = 0; j < totalColumn; j++) {
                if (avail[i][j] == 1)
                    seatButton[i].get(j).setIcon(iconNotAvailable);
                else if (avail[i][j] == 2)
                    seatButton[i].get(j).setIcon(iconSelected[seatPattern[i + row - 1]]);
                else
                    seatButton[i].get(j).setIcon(iconEmpty[seatPattern[i + row - 1]]);
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

    public ArrayList<JButton>[] getSeatButton() {
        return seatButton;
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

    /**
     * Listener for seat button
     */
    private class SeatListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int clickRow = -1, clickColumn = -1;
            for (int i = 0; i < range; i++) {
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
                if (getTemp_row() >= row && getTemp_row() <= row + range - 1) {
                    seatButton[getTemp_row() - row].get(getTemp_column()).setIcon(iconEmpty[seatPattern[getTemp_row() - 1]]);
                    avail_seat[getTemp_row() - row][getTemp_column()] = 0;
                    State.getSmallBillCard().subPrice(price[seatPattern[getTemp_row() - 1]]);
                } else if (getTemp_row() != -1) {
                    State.getSmallBillCard().subPrice(price[seatPattern[getTemp_row() - 1]]);
                }
                setTemp_row(row + clickRow);
                setTemp_column(clickColumn);
                avail_seat[clickRow][clickColumn] = 2;
                seatButton[clickRow].get(clickColumn).setIcon(iconSelected[seatPattern[getTemp_row() - 1]]);
                warn.setBorder(null);
                State.getSmallBillCard().addPrice(price[seatPattern[getTemp_row() - 1]]);
            } else if (avail_seat[clickRow][clickColumn] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[clickRow][clickColumn] = 0;
                seatButton[clickRow].get(clickColumn).setIcon(iconEmpty[seatPattern[row + clickRow - 1]]);
                State.getSmallBillCard().subPrice(price[seatPattern[row + clickRow - 1]]);
            }
        }
    }

    /**
     * Listener for scroll bar
     */
    private class ScrollListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
            row = adjustmentEvent.getValue();
            int[][] s = new int[range][];
            for (int i = 0; i < range; i++) {
                for (int j = 0; j < rowNum[i].size(); j++)
                    rowNum[i].get(j).setText(String.valueOf(adjustmentEvent.getValue() + i));
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

    /**
     * Listener for ok button
     */
    public class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (getTemp_row() != -1) {
                State.setPc(5);
                State.setSeatRow(temp_row);
                State.setSeatColumn(temp_column + 1);
                State.setColumnNum(columnNum[temp_column].charAt(0));
                State.setBill(State.getSmallBillCard().getPrice());
                State.setSeatPre(seatPattern[getTemp_row() - 1]);
            } else {
                warn.setBorder(tipBorder);
            }
        }
    }

    /**
     * Listener to display plane info
     */
    public class FolderListener implements ActionListener {

        private boolean onoff = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            /*
            unfold plane info
             */
            if (!onoff)
                unfoldAnimation();
            /*
            fold plane info
             */
            else
                foldAnimation();
            onoff = !onoff;
        }
    }

    /**
     * Move component vertically.
     * @param component thing you want to move
     * @param distance how many pixels you want it to move
     */
    public void moveVertical(Component component, int distance) {
        component.setLocation(component.getX(), component.getY() + distance);
    }

    /**
     * Animation that move scrollCard, planeInfoCard and btn up by 450px.
     */
    public void unfoldAnimation() {
        btnFold.setEnabled(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(scrollCard, (int)(0.0227 * frame * (frame - 50)));
                    moveVertical(planeInfoCard, (int)(0.0227 * frame * (frame - 50)));
                    moveVertical(btnFold, (int)(0.0227 * frame * (frame - 50)));
                    try {
                        sleep(freshTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                scrollCard.setLocation(0, -450);
                planeInfoCard.setLocation(0, 120);
                btnFold.setLocation(0, 105);
                btnFold.setText("▼  hide aircraft information    ");
                btnFold.setEnabled(true);
            }
        });
        thread.start();
    }

    /**
     * Animation that move scrollCard, planeInfoCard and btn down by 450px.
     */
    public void foldAnimation() {
        btnFold.setEnabled(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(scrollCard, (int)(-0.0227 * frame * (frame - 50)));
                    moveVertical(planeInfoCard, (int)(-0.0227 * frame * (frame - 50)));
                    moveVertical(btnFold, (int)(-0.0227 * frame * (frame - 50)));
                    try {
                        sleep(freshTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                scrollCard.setLocation(0, 0);
                planeInfoCard.setLocation(0, 570);
                btnFold.setLocation(0, 555);
                btnFold.setText("▲ more aircraft information    ");
                btnFold.setEnabled(true);
            }
        });
        thread.start();
    }
}

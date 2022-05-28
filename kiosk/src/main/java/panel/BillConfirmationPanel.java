package panel;

import card.BoardingPassCard;
import card.MealBillCard;
import card.SeatBillCard;
import card.SmallBillCard;
import dbReader.PassengerFlightReader;
import idCardReader.IdCardReader;
import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 5.0
 * Appearance improvement
 * @date 2022/5.18
 *
 * @version 1.2
 * Add function: scanning card to confirm
 * and appearance improved.
 * @date 2022/3/29
 *
 * @version 1.1
 * Reuse SmallBillCard
 * and appearance improved.
 * @date 2022/3/28
 *
 * @version 1.0
 * @date 2022/3/25
 */

public class BillConfirmationPanel extends JPanel {

    private BoardingPassCard boardingPassCard;
    private SeatBillCard seatBillCard;
    private MealBillCard mealBillCard;
    private JLabel lblScanningImg;
    private JLabel lblInstruction;
    private JButton btnConfirm;

    public BillConfirmationPanel() {

        setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1600, 880);
        /*
        Initialize boardingPassCard
         */
        boardingPassCard = new BoardingPassCard(State.getIdFlight());
        boardingPassCard.setLocation(50, 20);
        add(boardingPassCard);

        /*
        Initialize seatBillCard
         */
        String seatPre = State.getPrefSeatName()[State.getSeatPre()];
        int seatPay = State.getPrefSeatPrice()[State.getSeatPre()];

        seatBillCard = new SeatBillCard(State.getSeatRow(), State.getColumnNum(), seatPre, seatPay);
        seatBillCard.setLocation(50, 345);
        add(seatBillCard);

        /*
        Initialize mealBillCard
         */
        boolean[] select = State.getSelectedPrefFood();
        int[] foodPay = new int[3];
        for (int i = 0; i < 3; i++) {
            if (select[i])
                foodPay[i] = State.getPrefFoodPrice()[i];
            else
                foodPay[i] = -1;
        }
        mealBillCard = new MealBillCard(State.getMeal(),
                State.getPrefFoodName()[0], State.getPrefFoodName()[1], State.getPrefFoodName()[2],
                foodPay[0], foodPay[1], foodPay[2]);
        mealBillCard.setLocation(50, 535);
        add(mealBillCard);

        SmallBillCard totalBill = new SmallBillCard(State.getBill());
        totalBill.changeTitle("Total:");
        add(totalBill);

        lblScanningImg = new JLabel();
        lblScanningImg.setBounds(1210, 90, 400, 400);
        lblScanningImg.setIcon(new ImageIcon("kiosk/icons/scan.png"));
        add(lblScanningImg);

        btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 35));
        btnConfirm.setBounds(1220, 760, 330, 70);
        btnConfirm.setForeground(Theme.getMinorFontColor());
        btnConfirm.setBackground(Theme.getThemeColor());
        btnConfirm.addActionListener(new ConfirmListener());
        btnConfirm.setBorderPainted(false);
        btnConfirm.setFocusPainted(false);
        add(btnConfirm);

        lblInstruction = new JLabel();
        lblInstruction.setText("Scan ID to continue");
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruction.setBounds(1220, 450, 330, 40);
        lblInstruction.setFont(new Font("Arial", Font.PLAIN, 35));
        lblInstruction.setForeground(Theme.getMainFontColor());
        add(lblInstruction);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                lblInstruction.setText("Scan ID to continue");
                lblInstruction.setForeground(Theme.getMainFontColor());
            }
        });
    }

    /**
     * Listener for confirm button
     */
    public class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (IdCardReader.readId().equals(PassengerFlightReader.getIdPassenger(State.getPassengerFlight_index()))) {
                    lblInstruction.setText("Scan ID to continue");
                    lblInstruction.setForeground(Theme.getMainFontColor());
                    if (State.getBill() != 0) {
                        State.setPc(7);
                    } else {
                        State.setPc(8);
                    }
                }
                else
                    throw new Exception();
            } catch (Exception e) {
                lblInstruction.setText("Authentication failed!");
                lblInstruction.setForeground(new Color(205,92,92));
            }
        }
    }

    public SeatBillCard getSeatBillCard() {
        return seatBillCard;
    }

    public MealBillCard getMealBillCard() {
        return mealBillCard;
    }

    public JButton getBtnConfirm() {
        return btnConfirm;
    }

}

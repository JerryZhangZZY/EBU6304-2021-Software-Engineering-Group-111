package card;

import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */

public class BillConfirmCard extends JPanel {

    private FlightInfoTopBarCard flightInfoTopBarCard;
    private SeatBillCard seatBillCard;
    private MealBillcard mealBillcard;

    private JTextField textBill;

    public BillConfirmCard() {

        setBackground(new Color(244, 244, 244));
        setForeground(Color.WHITE);
        setLayout(null);
        setSize(1600, 880);
        /*
        Initialize flightInfoTopBarCard
         */
        flightInfoTopBarCard = new FlightInfoTopBarCard(State.getIdFlight());
        flightInfoTopBarCard.setLocation(0, 0);
        add(flightInfoTopBarCard);

        /*
        Initialize seatBillCard
         */
        String seatPre = State.getPrefSeatName()[State.getSeatPre()];
        int seatPay = State.getPrefSeatPrice()[State.getSeatPre()];
        char column = 'o';
        switch (State.getSeatColumn()) {
            case 1:
                column = 'A';
                break;
            case 2:
                column = 'B';
                break;
            case 3:
                column = 'C';
                break;
            case 4:
                column = 'D';
                break;
            case 5:
                column = 'E';
                break;
            case 6:
                column = 'F';
        }
        seatBillCard = new SeatBillCard(State.getSeatRow(), column, seatPre, seatPay);
        seatBillCard.setLocation(50, 206);
        add(seatBillCard);

        /*
        Initialize mealBillcard
         */
        boolean[] select = State.getSelectedPrefFood();
        int[] foodPay = new int[3];
        for (int i = 0; i < 3; i++) {
            if (select[i])
                foodPay[i] = State.getPrefFoodPrice()[i];
            else
                foodPay[i] = -1;
        }
        mealBillcard = new MealBillcard(State.getMeal(),
                State.getPrefFoodName()[0], State.getPrefFoodName()[1], State.getPrefFoodName()[2],
                foodPay[0], foodPay[1], foodPay[2]);
        mealBillcard.setLocation(50, 422);
        add(mealBillcard);

        JLabel lblTotal = new JLabel("Total : $ " + State.getBill(), JLabel.CENTER);
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 40));
        lblTotal.setBounds(1183, 602, 347, 86);
        add(lblTotal);

        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.PLAIN, 40));
        btnConfirm.setForeground(Color.DARK_GRAY);
        btnConfirm.setBackground(Color.WHITE);
        btnConfirm.setBounds(1249, 720, 215, 86);
        add(btnConfirm);
        btnConfirm.addActionListener(new ConfirmListener());
    }

    public class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (State.getBill() != 0) {
                State.setPc(State.getPc() + 1);
            } else {
                State.setPc(State.getPc() + 2);
                //upload database
            }
        }
    }
}

package card;

import main.State;

import javax.swing.*;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 */

public class ConfirmCard extends JPanel {

    private FlightInfoTopBarCard flightInfoTopBarCard;
    private SeatBillCard seatBillCard;
    private MealBillcard mealBillcard;

    public ConfirmCard() {
        /*
        Initialize flightInfoTopBarCard
         */
        flightInfoTopBarCard = new FlightInfoTopBarCard(State.getIdFlight());

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
    }
}

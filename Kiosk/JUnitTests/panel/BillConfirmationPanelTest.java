package panel;

import backupDbOperation.BackupDbOperator;
import card.MealBillcard;
import card.SeatBillCard;
import dbReader.PassengerFlightReader;
import idCardReader.IdCardReader;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test BillConfirmationPanel
 *
 * @author Liang Zhehao
 *
 * @version 2.0
 * @date 2022/4/8
 */
class BillConfirmationPanelTest {

    @BeforeEach
    void reset() {
        State.setPrefSeatName(new String[]{"Normal", "Pro", "Max", "Sierra blue"});
        State.setPrefSeatPrice(new int[]{0, 5, 20, 10000});
        State.setPrefFoodName(new String[]{"Extra", "Kweichow Moutai", "Ice-cream"});
        State.setPrefFoodPrice(new int[]{5, 1000, 12});
        State.setIdFlight("AC0001");
    }

    @BeforeEach
    void backupDb() {
        BackupDbOperator.pull();
    }

    @AfterEach
    void resetDB() {
        BackupDbOperator.push();
    }

    @RepeatedTest(10)
    void testSeatBill() {
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F'};

        Random random = new Random();
        State.setSeatPre(random.nextInt(4));
        State.setSeatRow(random.nextInt(100));
        State.setSeatColumn(random.nextInt(6) + 1);

        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
        SeatBillCard seatBillCard = billConfirmationPanel.getSeatBillCard();
        JLabel headline = seatBillCard.getHeadline();
        JLabel pref = seatBillCard.getPreference();
        JLabel bill = seatBillCard.getBill();

        assertEquals("Seat:  " + State.getSeatRow() + c[State.getSeatColumn() - 1], headline.getText());
        assertEquals("· " + State.getPrefSeatName()[State.getSeatPre()], pref.getText());
        assertEquals("$" + State.getPrefSeatPrice()[State.getSeatPre()], bill.getText());
    }

    @RepeatedTest(10)
    void testMealBill() {
        char[] c = {'a', 'b', 'c'};
        String[] food = {"Normal", "Vegetarian", "Halal"};

        Random random = new Random();
        State.setMeal(c[random.nextInt(3)]);
        State.setSelectedPrefFood(new boolean[]{random.nextBoolean(), random.nextBoolean(), random.nextBoolean()});
        int temp = 0;
        for (int i = 0; i < 3; i++) {
            if (State.getSelectedPrefFood()[i])
                temp ++;
        }

        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
        MealBillcard mealBillcard = billConfirmationPanel.getMealBillcard();
        JLabel headline = mealBillcard.getHeadline();
        JLabel[] extr = mealBillcard.getExtr();
        JLabel[] bill = mealBillcard.getBill();

        assertEquals("Meal:  " + food[(int)State.getMeal() - 97], headline.getText());
        assertEquals(120 + temp * 91, mealBillcard.getHeight());
        int n = 0;
        for (int i = 0; i < temp; i++) {
            while (!State.getSelectedPrefFood()[n])
                n ++;
            assertEquals("· " + State.getPrefFoodName()[n], extr[i].getText());
            assertEquals("$" + State.getPrefFoodPrice()[n], bill[i].getText());
            n ++;
        }
    }

    @Test
    void testConfirmButton() {
        int tempPc;
        for (int i = 0; i < 2; i++) {
            State.setPassengerFlight_index(i+2);
            BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
            JButton button = billConfirmationPanel.getBtnConfirm();
            try {
                String s1 = IdCardReader.readId();
                String s2 = PassengerFlightReader.getIdPassenger(State.getPassengerFlight_index());
                if (s1.equals(s2)) {
                    State.setBill(0);
                    tempPc = State.getPc();
                    button.doClick();
                    assertEquals(tempPc + 2, State.getPc());

                    State.setBill(1);
                    tempPc = State.getPc();
                    button.doClick();
                    assertEquals(tempPc + 1, State.getPc());
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                tempPc = State.getPc();
                button.doClick();
                assertEquals(tempPc, State.getPc());
            }
        }
    }
}

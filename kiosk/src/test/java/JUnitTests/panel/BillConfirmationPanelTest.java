package panel;

import backupDbOperation.BackupDbOperator;
import card.MealBillCard;
import card.SeatBillCard;
import common.MainFrameBarsTest;
import dbReader.PassengerFlightReader;
import frame.MainFrame;
import idCardReader.IdCardReader;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test BillConfirmationPanel
 *
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 5.0
 * Adapt to SeatBillCard v5.1
 * @date 2022/5/20
 *
 * @version 2.0
 * @date 2022/4/8
 */
public class BillConfirmationPanelTest implements MainFrameBarsTest {
    MainFrame mainFrame;
    int currentPC;

    @BeforeEach
    public void reset() {
        State.setPrefSeatName(new String[]{"Normal", "Pro", "Max", "Sierra blue"});
        State.setPrefSeatPrice(new int[]{0, 5, 20, 10000});
        State.setPrefFoodName(new String[]{"Extra", "Kweichow Moutai", "Ice-cream"});
        State.setPrefFoodPrice(new int[]{5, 1000, 12});
        State.setIdFlight("BA0001");
        mainFrame = new MainFrame();
        currentPC = 6;
        State.setPc(6);
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
    public void testSeatBill() {

        Random random = new Random();
        State.setSeatPre(random.nextInt(4));
        State.setSeatRow(random.nextInt(100));
        State.setSeatColumn(random.nextInt(6) + 1);
        State.setColumnNum('A');

        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
        SeatBillCard seatBillCard = billConfirmationPanel.getSeatBillCard();
        JLabel headline = seatBillCard.getHeadText();
        JLabel pref = seatBillCard.getPreference();
        JLabel bill = seatBillCard.getBill();

        assertEquals(Integer.toString(State.getSeatRow()) + State.getColumnNum(), headline.getText());
        assertEquals("· " + State.getPrefSeatName()[State.getSeatPre()], pref.getText());
        assertEquals("$" + State.getPrefSeatPrice()[State.getSeatPre()], bill.getText());
    }

    @RepeatedTest(10)
    public void testMealBill() {
        char[] c = {'a', 'b', 'c'};
        String[] food = {"Normal", "Vegetarian", "Halal"};

        Random random = new Random();
        State.setSeatColumn(1);
        State.setMeal(c[random.nextInt(3)]);
        State.setSelectedPrefFood(new boolean[]{random.nextBoolean(), random.nextBoolean(), random.nextBoolean()});
        int temp = 0;
        for (int i = 0; i < 3; i++) {
            if (State.getSelectedPrefFood()[i])
                temp ++;
        }

        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
        MealBillCard mealBillCard = billConfirmationPanel.getMealBillCard();
        JLabel headline = mealBillCard.getHeadText();
        JLabel[] extr = mealBillCard.getExtr();
        JLabel[] bill = mealBillCard.getBill();

        assertEquals(food[(int)State.getMeal() - 97], headline.getText());

        int[] expectPrice = State.getPrefFoodPrice();
        String[] expectName = State.getPrefFoodName();
        boolean[] expectSelect = State.getSelectedPrefFood();
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j > i; j--) {
                if (expectPrice[j] < expectPrice[j - 1]) {
                    int temp1 = expectPrice[j];
                    expectPrice[j] = expectPrice[j - 1];
                    expectPrice[j - 1] = temp1;
                    String temp2 = expectName[j];
                    expectName[j] = expectName[j - 1];
                    expectName[j - 1] = temp2;
                    boolean temp3 = expectSelect[j];
                    expectSelect[j] = expectSelect[j - 1];
                    expectSelect[j - 1] = temp3;
                }
            }
        }
        int n = 0;
        for (int i = 0; i < temp; i++) {
            while (!State.getSelectedPrefFood()[n])
                n ++;
            assertEquals("· " + expectName[n], extr[i].getText());
            assertEquals("$" + expectPrice[n], bill[i].getText());
            n ++;
        }
    }

    @Test
    public void testConfirmButton() {
        int tempPc = State.getPc();
        for (int i = 0; i < 2; i++) {
            State.setPassengerFlight_index(i+2);
            BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
            JButton button = billConfirmationPanel.getBtnConfirm();
            try {
                String s1 = IdCardReader.readId();
                String s2 = PassengerFlightReader.getIdPassenger(State.getPassengerFlight_index());
                if (s1.equals(s2)) {
                    State.setBill(0);
                    button.doClick();
                    assertEquals(tempPc + 2, State.getPc());
                    State.setPc(tempPc);
                    State.setBill(1);
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
    public void testExit(){
        doExit(mainFrame);
    }
    public void testBack(){
        doBack(mainFrame, currentPC);
    }
}

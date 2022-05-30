import backupDbOperation.BackupDbOperator;
import card.ScanIdLoginCardTest;
import card.TypeIdLoginCardTest;
import main.Clock;
import main.Config;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import panel.*;

import java.io.IOException;
import java.util.Random;

/**
 * integrated test, calling all tests
 * @author zaitian
 * @author Chenyu
 *
 * @version 2.0
 * @date 4/27
 *
 * @version 1.1
 * implement markov process
 * @date 4/23
 *
 * @version 1.0
 * @date 4/22
 */
public class SystemTest {
    @BeforeAll
    public static void disableTimer(){
        Clock.disableCheckinTimer();
        //Clock.disableBackstageTimer();
    }
    @BeforeEach
    void backup() {
        BackupDbOperator.pull();
    }
    @AfterEach
    void recover() {
        BackupDbOperator.push();
    }
//    @Test
    @RepeatedTest(10)
    void main() throws IOException {
        Config.loadConfig();
        int currentPC = -1;
        State.setPc(0);
        while (true) {
            System.out.println("switch to: " + State.getPc());
            switch (State.getPc()) {
                case 0 -> {
                    currentPC = State.getPc();
                    testWelcomePanel();
                }
                case 1 -> {
                    currentPC = State.getPc();
                    testBookNumLoginPanel();
                }
                case 2 -> {
                    currentPC = State.getPc();
                    testAltLoginPanel();
                }
                case 3 -> {
                    currentPC = State.getPc();
                    testFlightSelectionPanel();
                }
                case 4 -> {
                    currentPC = State.getPc();
                    testSeatSelectionPanel();
                }
                case 5 -> {
                    currentPC = State.getPc();
                    testMealSelectionPanel();
                }
                case 6 -> {
                    currentPC = State.getPc();
                    testBillConfirmationPanel();
                }
                case 7 -> {
                    currentPC = State.getPc();
                    testPaymentPanel();
                }
                case 8 -> {
                    currentPC = State.getPc();
                    testFinalPanel();
                }
            }
            if ((State.getPc() == 0) && (currentPC != 0))   //return from non-welcome
                break;
        }
    }
    void testWelcomePanel(){    //0
        WelcomePanelTest welcomePanelTest = new WelcomePanelTest();
        welcomePanelTest.test();
    }
    void testBookNumLoginPanel() throws IOException {       //1
        BookingLoginPanelTest bookingLoginPanelTest = new BookingLoginPanelTest();
        bookingLoginPanelTest.reset();
        Markov markov;
        markov= new Markov();
        int next = markov.nextStateOf(1);
//        next = 0;
        System.out.println("next of 1: " + next);
        if (next == 0) {
            bookingLoginPanelTest.testExit();
        }
        else if (next == 2) {
            bookingLoginPanelTest.testAlternativeCheckIn();
        }
        else if (next == 3){
            bookingLoginPanelTest.testBookingLoginPanel();
        }
    }
    void testAltLoginPanel() throws IOException {       //2
        Markov markov = new Markov();
        int next = markov.nextStateOf(2);
        System.out.println("next fo 2: " + next);
        if (new Random().nextDouble() <= 0.6) {
            System.out.println("alt use left");
            TypeIdLoginCardTest typeIdLoginCardTest = new TypeIdLoginCardTest();
            typeIdLoginCardTest.reset();
            if (next == 0) {
                typeIdLoginCardTest.testExit();
            }
            else if (next == 1) {
                typeIdLoginCardTest.testBack();
            }
            else if (next == 3) {
                typeIdLoginCardTest.testEnteringID();
            }
        }
        else {
            ScanIdLoginCardTest scanIdLoginCardTest = new ScanIdLoginCardTest();
            scanIdLoginCardTest.reset();
            if (next == 0) {
                scanIdLoginCardTest.testExit();
            }
            else if (next == 1) {
                scanIdLoginCardTest.testBack();
            }
            else if (next == 3) {
                scanIdLoginCardTest.compareTypeAndScan();
            }
        }
    }
    void testFlightSelectionPanel() throws IOException {        //3
        FlightSelectionPanelTest flightSelectionPanelTest = new FlightSelectionPanelTest();
        flightSelectionPanelTest.reset();
        Markov markov = new Markov();
        int next = markov.nextStateOf(3);
        System.out.println("next of 3: "+ next);
        if (next == 0) {
            flightSelectionPanelTest.testExit();
        }
        else if (next == 4) {
            flightSelectionPanelTest.reset();
            switch (State.getBookingNumList().size()) {
                case 0 -> {
                    flightSelectionPanelTest.testNoMore();
                }
                case 1 -> {
                    flightSelectionPanelTest.testOneBookingNum();
                }
                case 2 -> {
                    flightSelectionPanelTest.testTwoBookingNum();
                }
            }
        }
    }
    void testSeatSelectionPanel() throws IOException{       //4
        SeatSelectionPanelTest test = new SeatSelectionPanelTest();
        test.reset();
        Markov markov;
        markov= new Markov();
        int next = markov.nextStateOf(4);
        System.out.println("next of 4: " + next);
        if(next == 0){
            test.testExit();
        }
        if(next == 3){
            test.testBack();
        }
        if(next == 5){
            test.testSeat();
        }
    }
    void testMealSelectionPanel() throws IOException{       //5
        MealSelectionPanelTest test = new MealSelectionPanelTest();
        test.reset();
        Markov markov;
        markov= new Markov();
        int next = markov.nextStateOf(5);
        System.out.println("next of 5: " + next);
        if(next == 0){
            test.testExit();
        }
        if(next == 4){
            test.testBack();
        }
        if(next == 6){
            test.testNormalFood();
            test.testSpecialFood();
        }
    }
    void testBillConfirmationPanel() throws IOException {       //6
        BillConfirmationPanelTest billConfirmationPanelTest = new BillConfirmationPanelTest();
        billConfirmationPanelTest.reset();
        Markov markov = new Markov();
        int next = markov.nextStateOf(6);
        System.out.println("next of 6: " + next);
        if (next == 0){
            billConfirmationPanelTest.testExit();
        }
        else if (next == 5){
            billConfirmationPanelTest.testBack();
        }
        else if (next == 7){
            billConfirmationPanelTest.testSeatBill();
            billConfirmationPanelTest.testMealBill();
            billConfirmationPanelTest.testConfirmButton();
        }
    }
    void testPaymentPanel() throws IOException {       //7
        PaymentPanelTest paymentPanelTest = new PaymentPanelTest();
        paymentPanelTest.reset();
        Markov markov = new Markov();
        int next = markov.nextStateOf(7);
        System.out.println("next of 7: " + next);
        if (next == 0){
            paymentPanelTest.testExit();
        }
        else if (next == 6){
            paymentPanelTest.testBack();
        }
        else if (next == 8){
            paymentPanelTest.testPaymentPanel();
        }
    }
    void testFinalPanel() throws IOException {        //8
        FinalPanelTest finalPanelTest = new FinalPanelTest();
        Markov markov = new Markov();
        int next = markov.nextStateOf(8);
        System.out.println("next of 8: " + next);
        finalPanelTest.testDataConfirm();
        if (next == 0){
            finalPanelTest.systemExitTest();
        }
        else if (next == 3){
            finalPanelTest.exitTest();
        }
    }
}

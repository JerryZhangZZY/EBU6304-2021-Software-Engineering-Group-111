import card.ScanIdLoginCardTest;
import card.TypeIdLoginCardTest;
import main.Config;
import main.State;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import panel.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * integrated test, calling all tests
 * @author zaitian
 *
 * @version 1.1
 * implement markov process
 * @date 4/23
 *
 * @version 1.0
 * @date 4/22
 */
public class SystemTest {
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


            }
            if (State.getPc() > 5)
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
    void testFlightSelectionPanel() throws IOException {
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
    void testSeatSelectionPanel() throws IOException{
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
    void testMealSelectionPanel() throws IOException{
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
        if(next == 5){
            test.testNormalFood();
            test.testSpecialFood();
        }
    }

}

import card.ScanIdLoginCardTest;
import card.TypeIdLoginCardTest;
import main.Config;
import main.State;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import panel.WelcomePanelTest;
import panel.BookingLoginPanelTest;

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
    @RepeatedTest(100)
    void main() throws IOException {
        Config.loadConfig();
        int currentPC = -1;
        State.setPc(0);
        while (true) {
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

            }
            if (State.getPc() > 2)
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
        System.out.println(next);
        if (next == 0) {
            bookingLoginPanelTest.testExit();
        }
        else if (next == 2) {
            bookingLoginPanelTest.testBookingLoginPanel();
        }
        else if (next == 3){
            bookingLoginPanelTest.testAlternativeCheckIn();
        }
    }
    void testAltLoginPanel() throws IOException {
        if (new Random().nextDouble() <= 0.6) {
            TypeIdLoginCardTest typeIdLoginCardTest = new TypeIdLoginCardTest();
            typeIdLoginCardTest.reset();
            typeIdLoginCardTest.testEnteringID();
        }
        else {
            ScanIdLoginCardTest scanIdLoginCardTest = new ScanIdLoginCardTest();
            scanIdLoginCardTest.compareTypeAndScan();
        }
    }
}

import main.Config;
import main.State;
import org.junit.jupiter.api.Test;
import panel.WelcomePanelTest;
import panel.BookingLoginPanelTest;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    @Test
    void main() throws InterruptedException, IOException {
        Config.loadConfig();
        int currentPC = -1;
        State.setPc(0);
        while (true) {
            while (currentPC == State.getPc()) {
                sleep(10);
            }
            switch (State.getPc()) {
                case 0 -> testWelcomePanel();
                case 1 -> testBookNumLoginPanel();
            }
            if (State.getPc() == 1)
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
        if (next == 2) {
            bookingLoginPanelTest.testBookingLoginPanel();
        }
        else if (next == 3){
            bookingLoginPanelTest.testAlternativeCheckIn();
        }
    }
    void testAltLoginPanel(){

    }
}

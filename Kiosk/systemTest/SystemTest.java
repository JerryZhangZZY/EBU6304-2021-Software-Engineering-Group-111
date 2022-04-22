import main.Config;
import main.State;
import org.junit.jupiter.api.Test;
import panel.WelcomePanelTest;
import panel.BookingLoginPanelTest;

import static java.lang.Thread.sleep;

/**
 * integrated test, calling all tests
 * @author zaitian
 *
 * @version 1.0
 * @date 4/22
 */
public class SystemTest {
    @Test
    void main() throws InterruptedException {
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
                break;;
        }
    }
    void testWelcomePanel(){
        WelcomePanelTest welcomePanelTest = new WelcomePanelTest();
        welcomePanelTest.reset();
        welcomePanelTest.test();
    }
    void testBookNumLoginPanel(){
        BookingLoginPanelTest bookingLoginPanelTest = new BookingLoginPanelTest();
        bookingLoginPanelTest.reset();
        bookingLoginPanelTest.testBookingLoginPanel();
    }
}

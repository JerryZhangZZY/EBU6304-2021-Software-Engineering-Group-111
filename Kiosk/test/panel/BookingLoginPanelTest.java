package panel;

import dbReader.PassengerFlightReader;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.*;

import javax.swing.*;

import java.awt.*;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for booking number login panel
 * @author zaitian
 *
 * @version 2.0
 * @date 4/6
 *
 * @version 1.0
 * @date 3/28
 */

class BookingLoginPanelTest {
    String[] expectedName = {"Jack", "Jack", "Mike", "nay"};
    String[] expectedBookingNumber = {"bn0001", "bn0002", "bn0003", "nil"};
    MainFrame mainFrame = new MainFrame();
    BookingLoginPanel bookingLoginPanel = new BookingLoginPanel();

    @BeforeEach
    void reset(){
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(bookingLoginPanel);
        bookingLoginPanel.reset();
    }
    @DisplayName("try entering a booking number")
    @RepeatedTest(10)
    void testBookingLoginPanel(){
        int bn = new Random().nextInt(expectedBookingNumber.length);
        bookingLoginPanel.getBookingNumberTextField().setText(expectedBookingNumber[bn]);
        JButton okBtn = bookingLoginPanel.getOkButton();
        okBtn.doClick();

        System.out.println(bn);
        System.out.println(expectedBookingNumber[bn]);
        System.out.println(expectedName[bn]);

        if(expectedBookingNumber[bn].isBlank() ||
                !PassengerFlightReader.bookingValid(expectedBookingNumber[bn])){
                    assertAll("proper warning",
                            () -> assertEquals("Invalid booking number!", bookingLoginPanel.getBookingNumberTextField().getText()),
                            () -> assertEquals(Color.RED, bookingLoginPanel.getBookingNumberTextField().getForeground()),
                            () -> assertEquals(new Font("Arial", Font.ITALIC, 25), bookingLoginPanel.getBookingNumberTextField().getFont())
                    );
                    System.out.println("check-in failed\n----------------");
        }
        else {
            String actualName = State.getPassengerName();
            String actualBookingNumber = State.getBookingNum();
            assertEquals(expectedName[bn], actualName, "name recorded");
            assertEquals(expectedBookingNumber[bn], actualBookingNumber, "book num recorded");
            assertEquals(3, State.getPc());
            System.out.println("check-in passed\n----------------");
        }
    }
    @DisplayName("use other ways to check-in")
    @Test
    void testAlternativeCheckIn(){
        JButton altBtn = bookingLoginPanel.getAltButton();
        altBtn.doClick();
        assertEquals(2, State.getPc());
    }
}
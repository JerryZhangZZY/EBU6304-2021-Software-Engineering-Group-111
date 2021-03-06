package panel;

import common.MainFrameBarsTest;
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
 * @version 3.0
 * implement MainFrameBarsTest interface
 * @date 4/23
 *
 * @version 2.1
 * adapt to new version panel
 * @date 4/11
 *
 * @version 2.0
 * @date 4/6
 *
 * @version 1.0
 * @date 3/28
 */

public class BookingLoginPanelTest implements MainFrameBarsTest {
    String[] candidateName = {"Jack", "Jack", "Mike", "nay"};
    String[] candidateBookingNumber = {"bn0001", "bn0002", "bn0003", "nil"};
    MainFrame mainFrame = new MainFrame();
    BookingLoginPanel bookingLoginPanel = new BookingLoginPanel();
    @BeforeEach
    public void reset(){
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(bookingLoginPanel);
        bookingLoginPanel.reset();
    }
    @DisplayName("try entering a booking number")
    @RepeatedTest(10)
    public void testBookingLoginPanel(){
        int bn = new Random().nextInt(candidateBookingNumber.length);
        bookingLoginPanel.getTfBookingNumber().setText(candidateBookingNumber[bn]);
        JButton okBtn = bookingLoginPanel.getBtnOk();
        okBtn.doClick();

        System.out.println(bn);
        System.out.println(candidateBookingNumber[bn]);
        System.out.println(candidateName[bn]);

        if(candidateBookingNumber[bn].isBlank() ||
                !PassengerFlightReader.bookingValid(candidateBookingNumber[bn])){
                    assertAll("proper warning",
                            () -> assertEquals("Invalid booking number!", bookingLoginPanel.getTfBookingNumber().getText()),
                            () -> assertEquals(new Color(205,92,92), bookingLoginPanel.getTfBookingNumber().getForeground()),
                            () -> assertEquals(new Font("Arial", Font.ITALIC, 25), bookingLoginPanel.getTfBookingNumber().getFont())
                    );
                    System.out.println("check-in failed\n----------------");
        }
        else {
            String actualName = State.getPassengerName();
            String actualBookingNumber = State.getBookingNumList().get(0);
            assertEquals(candidateName[bn], actualName, "name retrieved");
            assertEquals(candidateBookingNumber[bn], actualBookingNumber, "book num recorded");
            assertEquals(3, State.getPc());
            System.out.println("check-in passed\n----------------");
        }
    }
    @DisplayName("use other ways to check-in")
    @Test
    public void testAlternativeCheckIn(){
        JButton altBtn = bookingLoginPanel.getBtnAlt();
        altBtn.doClick();
        assertEquals(2, State.getPc());
    }
    @Test
    public void testExit(){
        doExit(mainFrame);
    }
}
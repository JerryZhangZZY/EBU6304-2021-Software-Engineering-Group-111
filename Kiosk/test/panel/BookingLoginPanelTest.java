package panel;

import dbReader.SeatReader;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BookingLoginPanelTest {
    String expectedName = new String("Jack");
    String expectedBookingNumber = new String("bn0001");


    @Test
    void testBookingLoginPanel(){
        MainFrame mainFrame = new MainFrame();
        BookingLoginPanel bookingLoginPanel = new BookingLoginPanel();
        mainFrame.loadPanel(bookingLoginPanel);
        bookingLoginPanel.getBookingNumberTextField().setText(expectedBookingNumber);
        JButton okBtn = bookingLoginPanel.getOkButton();
        okBtn.doClick();
        String actualName = State.getPassengerName();
        String actualBookingNumber = State.getBookingNum();
        assertEquals(expectedName, actualName);
        assertEquals(expectedBookingNumber, actualBookingNumber);
    }
}
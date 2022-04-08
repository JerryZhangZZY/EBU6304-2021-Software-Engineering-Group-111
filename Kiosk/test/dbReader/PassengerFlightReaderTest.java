package dbReader;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of PassengerFlightReader.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class PassengerFlightReaderTest {

    @Test
    void indexOf() {
        assertEquals(0, PassengerFlightReader.indexOf(1));
        assertEquals(0, PassengerFlightReader.indexOf("bn0001", "CA0001"));
    }

    @Test
    void getIdPassenger() {
        assertEquals("123001", PassengerFlightReader.getIdPassenger(0));
    }

    @Test
    void getIdFlight() {
        assertEquals("CA0001", PassengerFlightReader.getIdFlight(0));
    }

    @Test
    void getBookingNum() {
        assertEquals("bn0001", PassengerFlightReader.getBookingNum(0));
    }

    @Test
    void getCarryon() {
        assertEquals(2, PassengerFlightReader.getCarryon(0));
    }

    @Test
    void getCheckin() {
        assertEquals(1, PassengerFlightReader.getCheckin(0));
    }

    @Test
    void getBagDropCounter() {
        assertEquals(15, PassengerFlightReader.getBagDropCounter(0));
    }

    @Test
    void getStatus() {
        assertFalse(PassengerFlightReader.getStatus(0));
    }

    @Test
    void getIdFlightByBookingNum() {
        List<String> list = new ArrayList<>();
        list.add("CA0001");
        list.add("AC0001");
        assertEquals(list, PassengerFlightReader.getIdFlightByBookingNum("bn0001"));
    }

    @Test
    void getBookingNumByPassengerId() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("bn0001");
        set.add("bn0002");
        assertEquals(set, PassengerFlightReader.getBookingNumByPassengerId("123001"));
    }

    @Test
    void bookingValid() {
        assertTrue(PassengerFlightReader.bookingValid("bn0001"));
        assertFalse(PassengerFlightReader.bookingValid("bn0000"));
    }

    @Test
    void getPassengerNameByBookingNum() {
        assertEquals("Jack", PassengerFlightReader.getPassengerNameByBookingNum("bn0001"));
    }
}
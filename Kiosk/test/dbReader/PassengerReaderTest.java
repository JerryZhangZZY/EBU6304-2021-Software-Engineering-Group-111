package dbReader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of PassengerReader.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class PassengerReaderTest {

    @Test
    void indexOf() {
        assertEquals(0, PassengerReader.indexOf("123001"));
    }

    @Test
    void getSurname() {
        assertEquals("Jack", PassengerReader.getSurname(0));
    }
}
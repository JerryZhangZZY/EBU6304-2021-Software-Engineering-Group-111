package dbReader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of PlaneReader.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class PlaneReaderTest {

    @Test
    void indexOf() {
        assertEquals(0, PlaneReader.indexOf(1));
    }

    @Test
    void getModel() {
        assertEquals("Dassault Falcon 900", PlaneReader.getModel(0));
    }

    @Test
    void getCapacity() {
        assertEquals(36, PlaneReader.getCapacity(0));
    }

    @Test
    void getAirline() {
        assertEquals("AIR CHINA", PlaneReader.getAirline(0));
    }
}
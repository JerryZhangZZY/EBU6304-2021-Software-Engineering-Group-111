package dbReader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test of FlightReader.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class FlightReaderTest {

    @Test
    void indexOf() {
        assertEquals(0, FlightReader.indexOf("CA0001"));
    }

    @Test
    void getIdFlight() {
        assertEquals("CA0001", FlightReader.getIdFlight(0));
    }

    @Test
    void getDeparture() {
        assertEquals("Beijing T3", FlightReader.getDeparture(0));
    }

    @Test
    void getArrival() {
        assertEquals("Shanghai T2", FlightReader.getArrival(0));
    }

    @Test
    void getGate() {
        assertEquals("3A", FlightReader.getGate(0));
    }

    @Test
    void getDepartureTime() {
        assertEquals("10:00", FlightReader.getDepartureTime(0));
    }

    @Test
    void getArrivalTime() {
        assertEquals("12:00", FlightReader.getArrivalTime(0));
    }

    @Test
    void getBoardingTime() {
        assertEquals("09:30", FlightReader.getBoardingTime(0));
    }

    @Test
    void getIdPlane() {
        assertEquals(1, FlightReader.getIdPlane(0));
    }

    @Test
    void getDate() {
        assertEquals("2022-03-18", FlightReader.getDate(0));
    }
}
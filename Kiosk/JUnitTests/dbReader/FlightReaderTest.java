package dbReader;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test of FlightReader.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 3.1
 * Add test for getFoodName() and getFoodPrice()
 * @date 2022/4/23
 *
 * @version 3.0
 * Add test for getDepartureDateTime().
 * @date 2022/4/20
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
        assertEquals("2022-04-20", FlightReader.getDate(0));
    }

    @Test
    void getDepartureDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long actual = FlightReader.getDepartureDateTime(0).getTime();
        long expected = 0;
        try {
            expected = simpleDateFormat.parse("2022-04-20 10:00").getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void getFoodPName() {
        assertArrayEquals(new String[]{"Extra", "Kweichow Moutai", "Ice-cream"}, FlightReader.getFoodName(0));
    }

    @Test
    void getFoodPrice() {
        assertArrayEquals(new int[]{5, 100, 12}, FlightReader.getFoodPrice(0));
    }

}
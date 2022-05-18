package dbReaderTest;

import dbReader.FlightReader;

/**
 * This class tests major methods in FlightReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public class FlightReaderTest {
    public static void main(String[] args) {
        System.out.println(FlightReader.indexOf("CA0001"));
        System.out.println(FlightReader.getDeparture(FlightReader.indexOf("CA0002")));
        System.out.println(FlightReader.getIdPlane(FlightReader.indexOf("CA0001")));
        System.out.println(FlightReader.getDepartureDate(FlightReader.indexOf("AC0001")));
    }
}

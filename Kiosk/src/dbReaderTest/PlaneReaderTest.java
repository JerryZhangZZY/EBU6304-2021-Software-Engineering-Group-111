package dbReaderTest;

import dbReader.PlaneReader;

/**
 * This class tests major methods in FlightReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public class PlaneReaderTest {
    public static void main(String[] args) {
        System.out.println(PlaneReader.indexOf(1));
        System.out.println(PlaneReader.getAirline(PlaneReader.indexOf(2)));
        System.out.println(PlaneReader.getModel(PlaneReader.indexOf(3)));
    }
}

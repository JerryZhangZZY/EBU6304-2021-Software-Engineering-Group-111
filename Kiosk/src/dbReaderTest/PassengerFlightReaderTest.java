package dbReaderTest;

import dbReader.PassengerFlightReader;

/**
 * This class tests all methods in PassengerFlightReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public class PassengerFlightReaderTest {
    public static void main(String[] args) {
        System.out.println(PassengerFlightReader.indexOf(1));
        System.out.println(PassengerFlightReader.getIdPassenger(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getIdFlight(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getBookingNum(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getCarryon(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getCheckin(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getBagDropCounter(PassengerFlightReader.indexOf(3)));
        System.out.println(PassengerFlightReader.getStatus(PassengerFlightReader.indexOf(3)));
        System.out.println("--------------------------------");
        System.out.println(PassengerFlightReader.getIdFlightByBookingNum("bn0001"));
        System.out.println(PassengerFlightReader.getBookingNumByPassengerId("123001"));
    }
}

package dbReaderTest;

import dbReader.PassengerReader;

/**
 * This class tests methods in PassengerReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public class PassengerReaderTest {
    public static void main(String[] args) {
        System.out.println(PassengerReader.indexOf("123001"));
        System.out.println(PassengerReader.getSurname(PassengerReader.indexOf("123002")));
    }
}

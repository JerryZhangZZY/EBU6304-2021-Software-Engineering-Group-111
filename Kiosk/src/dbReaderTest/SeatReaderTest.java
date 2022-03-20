package dbReaderTest;

import dbReader.SeatReader;

import java.util.Arrays;

/**
 * This is a test class of SeatReader that print out seat status map of a given flight.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 * @date 2022/2/21
 * @version 1.0
 */

public class SeatReaderTest {
    public static void main(String[] args) {
        SeatReader sr1 = new SeatReader("AC0001");
        for(int i = 1; i <= 12; i++) {
            System.out.println(Arrays.toString(sr1.getSeat(i)));
        }
    }
}

package dbWriterTest;

import dbReader.PassengerFlightReader;
import dbWriter.StatusWriter;

/**
 * This is a test class of StatusWriter.
 *
 * @author Zhang Zeyu
 * @date 2022/3/21
 * @version 1.0
 */

public class StatusWriterTest {
    public static void main(String[] args) {
        StatusWriter.setTrue(PassengerFlightReader.indexOf(1));
    }
}

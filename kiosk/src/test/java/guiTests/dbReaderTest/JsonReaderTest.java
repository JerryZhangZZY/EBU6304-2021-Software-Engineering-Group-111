package dbReaderTest;

import dbReader.JsonReader;

/**
 * This is a test class of JsonReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public class JsonReaderTest {
    public static void main(String[] args) {
        System.out.println(JsonReader.read("database/flight.json"));
    }
}

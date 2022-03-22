package idCardReaderTest;

import idCardReader.IdCardReader;

import java.io.IOException;

/**
 * This s a test class of idCardReader.
 *
 * @author Zhang Zeyu
 * @date 2022/3/23
 * @version 1.0
 */

public class IdCardReaderTest {
    public static void main(String[] args) {
        try {
            System.out.println(IdCardReader.readId());
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("-1");
        }
    }
}

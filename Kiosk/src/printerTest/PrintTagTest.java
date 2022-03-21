/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/22
 * Test for Tag Printer
 */
package printerTest;
import printer.PrintTag;

import java.io.IOException;
public class PrintTagTest {
    public static void main(String[] args) {
        try {
            PrintTag.creatTag(3);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

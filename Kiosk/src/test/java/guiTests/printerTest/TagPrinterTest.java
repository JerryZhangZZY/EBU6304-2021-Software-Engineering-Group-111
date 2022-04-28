package printerTest;
import printer.TagPrinter;

import java.io.IOException;
/**
 * Test program for Tag Printer
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 3/22
 */
public class TagPrinterTest {
    public static void main(String[] args) {
        try {
            TagPrinter.creatTag(3);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

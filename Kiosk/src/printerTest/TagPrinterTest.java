package printerTest;
import printer.TagPrinter;

import java.io.IOException;
/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/22
 * Test for Tag Printer
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

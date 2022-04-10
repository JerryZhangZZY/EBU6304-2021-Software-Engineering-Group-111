package printer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import printer.TagPrinter;

import java.util.Random;
import dbReader.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test program for Tag Printer
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 2022/4/7
 */
public class TagPrinterTest {
    int[] expectedIndex = {0,1,2,3};
    @DisplayName("If the file is created successfully")
    @RepeatedTest(4)
    void testTagPrinter() throws IOException {
        int index = new Random().nextInt(expectedIndex.length);
        TagPrinter.creatTag(index);
        String filePath = TagPrinter.getFilePath();
        File file = new File(filePath);
        assertTrue(file.exists());
        System.out.println("index: "+index);
        System.out.println(filePath);
        System.out.println("---------------");
    }
}

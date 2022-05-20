package printer;

import main.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
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
        TagPrinter.getPhoto(index);
        String filejpgPath = TagPrinter.getFilePhotoPath();
        File filejpg = new File(filejpgPath);
        assertTrue(filejpg.exists());
        System.out.println("index: " + index);
        System.out.println(filejpgPath);
        System.out.println("---------------");

        TagPrinter.getTxt(index);
        String filetxtPath = TagPrinter.getFileTxtPath();
        File filetxt = new File(filetxtPath);
        assertTrue(filetxt.exists());
        System.out.println("index: " + index);
        System.out.println(filetxtPath);
        System.out.println("---------------");
    }
}

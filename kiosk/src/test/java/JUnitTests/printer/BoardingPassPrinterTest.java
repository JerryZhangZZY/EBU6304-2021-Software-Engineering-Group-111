package printer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * JUnit test program for Boarding Pass Printer
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 2022/4/7
 */
public class BoardingPassPrinterTest {
    int[] expectedIndex = {0,1,2,3};
    @DisplayName("If the file is created successfully")
    @RepeatedTest(4)
    void testTicketPrinter() throws IOException {
        int index = new Random().nextInt(expectedIndex.length);
        int column = new Random().nextInt(6);
        int row = (new Random().nextInt(50))+1;
        char columnInLetter = (char)(column+(int)'A'-1);
        System.out.println("index: "+index);
        System.out.println("column: "+column);
        System.out.println("column in letter: "+columnInLetter);
        System.out.println("row: "+row);
        BoardingPassPrinter.creatBoardingPass(index);
        String filePath = BoardingPassPrinter.getFilePath();
        File file = new File(filePath);
        assertTrue(file.exists());
        System.out.println(filePath);
        System.out.println("---------------");
    }
}

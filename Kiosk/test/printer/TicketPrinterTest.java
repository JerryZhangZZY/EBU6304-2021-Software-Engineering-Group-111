package printer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import printer.TagPrinter;
import printer.TicketPrinter;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * JUnit test program for Ticket Printer
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 2022/4/7
 */
public class TicketPrinterTest {
    int[] expectedIndex = {0,1,2,3};
    @DisplayName("If the file is created successfully")
    @RepeatedTest(4)
    void testTicketPrinter() throws IOException {
        int index = new Random().nextInt(expectedIndex.length);
        TicketPrinter.creatTicket(index);
        String filePath = TicketPrinter.getFilePath();
        File file = new File(filePath);
        assertTrue(file.exists());
        System.out.println("index: "+index);
        System.out.println(filePath);
        System.out.println("---------------");
    }
}

package printer;

import main.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

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
        State.setPassengerFlight_index(index);
        TicketPrinter.creatTicketJPG(index);
        String filejpgPath = TicketPrinter.getFilePhotoPath();
        File filejpg = new File(filejpgPath);
        assertTrue(filejpg.exists());
        System.out.println("index: "+index);
        System.out.println(filejpgPath);
        System.out.println("---------------");

        TicketPrinter.creatTicketTXT(index);
        String filetxtPath = TicketPrinter.getFileTxtPath();
        File filetxt = new File(filetxtPath);
        assertTrue(filetxt.exists());
        System.out.println("index: "+index);
        System.out.println(filetxtPath);
        System.out.println("---------------");
    }
}

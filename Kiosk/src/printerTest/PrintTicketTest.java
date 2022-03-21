package printerTest;
/**
 * @version 1.0
 * @author wcy
 * @date 3/20
 * use for print ticket and generate a TXT
 */
import printer.PrintTicket;
import javax.imageio.IIOException;
import java.io.IOException;


public class PrintTicketTest {
    public static void main(String[] args) {
       try {
           PrintTicket.creatTicket(3,"23A");
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}



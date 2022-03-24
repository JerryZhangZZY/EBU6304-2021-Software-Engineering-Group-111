package printerTest;

import printer.TagPrinter;
import printer.TicketPrinter;

import java.io.IOException;
/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/24
 * Test for Ticket Printer
 */
public class TicketPrinterTest {
    public static void main(String[] args) {
        try {
            TicketPrinter.creatTicket(2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

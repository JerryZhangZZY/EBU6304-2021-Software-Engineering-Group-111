package printerTest;

import printer.TicketPrinter;

import java.io.IOException;
/**
 * Test program for Ticket Printer
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 3/24
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

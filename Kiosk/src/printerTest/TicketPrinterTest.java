package printerTest;

import printer.TagPrinter;
import printer.TicketPrinter;

import java.io.IOException;

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

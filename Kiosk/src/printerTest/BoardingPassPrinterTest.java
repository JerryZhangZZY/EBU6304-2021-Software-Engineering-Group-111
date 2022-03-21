package printerTest;
/**
 * @version 1.0
 * @author wcy
 * @date 3/20
 * use for print ticket and generate a TXT
 */
import printer.BoardingPassPrinter;

import java.io.IOException;


public class BoardingPassPrinterTest {
    public static void main(String[] args) {
       try {
           BoardingPassPrinter.creatTicket(1,"23A");
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}



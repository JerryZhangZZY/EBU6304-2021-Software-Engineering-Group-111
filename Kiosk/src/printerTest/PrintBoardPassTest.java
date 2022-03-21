package printerTest;
/**
 * @version 1.0
 * @author wcy
 * @date 3/20
 * use for print ticket and generate a TXT
 */
import printer.PrintBoardPass;

import java.io.IOException;


public class PrintBoardPassTest {
    public static void main(String[] args) {
       try {
           PrintBoardPass.creatTicket(1,"23A");
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}



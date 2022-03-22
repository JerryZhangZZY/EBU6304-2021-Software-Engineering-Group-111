package printerTest;

import printer.BoardingPassPrinter;

import java.io.IOException;

/**
 * @version 1.0
 * @author wcy
 * @date 3/20
 * use for print ticket and generate a TXT
 */
public class BoardingPassPrinterTest {
    public static void main(String[] args) {
       try {
           BoardingPassPrinter.creatBoardingPass(1,"23A");
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}



package printerTest;

import printer.BoardingPassPrinter;

import java.io.IOException;

/**
 * use for print ticket and generate a TXT
 *
 * @author wcy
 * @version 1.0
 * @date 3/20
 */
public class BoardingPassPrinterTest {
    public static void main(String[] args) {
       try {
           BoardingPassPrinter.creatBoardingPass(1);
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}



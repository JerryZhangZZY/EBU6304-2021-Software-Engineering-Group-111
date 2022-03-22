package printerTest;

import printer.BarCode_QRCodeGenerator;

import java.io.File;
/**
 * @version 1.0
 * @author QuellanAn
 * @reference https://blog.csdn.net/qq_27790011/article/details/78401450
 * @version 1.1
 * @author Ni Ruijie
 * @date 3/22
 * This is the phase 1 test program of the BarCode_QRCodeGenerator
 */
public class BarCode_QRCodeGeneratorTest {

    /**
     * Test the function. Simply generate a qr code image for the specified string.
     */
    public static void main(String[] args) throws Exception {
        String text = "1234567890";
        String result;
        String format = "jpg";
        //Generate QRCode
        File outputFile = new File("Kiosk/printerOutPut/rqcode.jpg");
        BarCode_QRCodeGenerator.writeToFile(BarCode_QRCodeGenerator.toQRCodeMatrix(text, null, null), format, outputFile);
        System.out.println("success1");
        //Generate BarCode
        outputFile = new File("Kiosk/printerOutPut/barcode.jpg");
        BarCode_QRCodeGenerator.writeToFile(BarCode_QRCodeGenerator.toBarCodeMatrix(text, null, null), format, outputFile);
        System.out.println("success2");
    }

}
//https://blog.csdn.net/qq_27790011/article/details/78401450

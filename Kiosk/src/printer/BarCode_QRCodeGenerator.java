package printer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import dbReader.AirlineWebsiteReader;
import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import dbReader.PlaneReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

/**
 * @version 1.0
 * @author QuellanAn
 * @reference https://blog.csdn.net/qq_27790011/article/details/78401450
 * @version 1.1
 * @author Ni Ruijie
 * @date 3/22
 * ZXing2.3 is used to generate the auxiliary class of bar code.
 */
public final class BarCode_QRCodeGenerator {

    private static final String CHARSET = "utf-8";
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private BarCode_QRCodeGenerator() {
    }

    /**
     * Generate matrix is a simple function, parameter fixed, more is used to demonstrate.
     * @param text
     * @return
     */
    public static BitMatrix toQRCodeMatrix(String text, Integer width, Integer height) {
        // Image format of qr code
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        // The encoding used for the content
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text,
                    BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitMatrix;
    }

    /**
     * Generates a qr code image for the specified string.
     * @param text
     * @param file
     * @param format
     * @return
     */
    public boolean toQrcodeFile(String text, File file, String format) {
        BitMatrix matrix = toQRCodeMatrix(text, null, null);
        if (matrix != null) {
            try {
                writeToFile(matrix, format, file);
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Generate black and white graph from point matrix.
     * @param matrix
     * @return iamge
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * Encode strings into a one-dimensional barcode matrix
     *
     * @param str
     * @param width
     * @param height
     * @return
     */
    public static BitMatrix toBarCodeMatrix(String str, Integer width,
                                            Integer height) {

        if (width == null || width < 200) {
            width = 200;
        }

        if (height == null || height < 50) {
            height = 50;
        }

        try {
            // Literal encoding
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(str,
                    BarcodeFormat.CODE_128, width, height, hints);

            return bitMatrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generate files according to matrix and picture format.
     */
    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    /**
     * Writes the matrix to the output stream.
     */
    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format "
                    + format);
        }
    }

    public static void generateQRcode(int idPassengerFlight_index) throws Exception{
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlight_index = FlightReader.indexOf(idFlight);
        int idPlane = FlightReader.getIdPlane(idFlight_index);
        int idPlane_index = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlane_index);

        String text = AirlineWebsiteReader.getWebsite(AirlineWebsiteReader.indexOf(airline));
        String format = "jpg";
        //Generate QRCode
        File outputFile = new File("Kiosk/printerOutPut/qrcode.jpg");
        BarCode_QRCodeGenerator.writeToFile(BarCode_QRCodeGenerator.toQRCodeMatrix(text, 200, 200), format, outputFile);
    }
}
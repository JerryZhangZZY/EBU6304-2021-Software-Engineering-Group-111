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
import main.Theme;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;


/**
 * This class can generate qr code or barcode automatically by a given String
 *
 * @author Ni Ruijie
 * @author Zhang Zeyu
 *
 * @version 3.0
 * Auto change barcode colors.
 * @date 2022/4/21
 *
 * @version 2.1
 * Adjust color.
 * @date 2022/4/14
 *
 * @version 2.0
 * The limit of the width and height is dismissed
 * New function added, which can generate the qr codes of websites by idPassengerFlight_index
 * @date 2022/4/13
 *
 * @version 1.0
 * reference: <a href="https://blog.csdn.net/qq_27790011/article/details/78401450">https://blog.csdn.net/qq_27790011/article/details/78401450</a>
 * @date 2022/3/22
 */
public final class BarCode_QRCodeGenerator {

    private static final String CHARSET = "utf-8";
    private static int foreground;
    private static int background;

    private BarCode_QRCodeGenerator() {
    }

    /**
     * Generate matrix is a simple function, parameter fixed, more is used to demonstrate.
     * @param text text
     * @return bitMatrix
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
     * @param text text
     * @param file file
     * @param format format
     * @return boolean
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
     * @param matrix matrix
     * @return image
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? foreground : background);
            }
        }
        return image;
    }

    /**
     * Encode strings into a one-dimensional barcode matrix
     *
     * @param str string
     * @param width width
     * @param height height
     * @return bitMatrix
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
    /**
     * Generate the qr codes of websites by idPassengerFlight_index
     * @param idPassengerFlight_index primary key
     */
    public static void generateQRcode(int idPassengerFlight_index) throws Exception{
        setColors();
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlight_index = FlightReader.indexOf(idFlight);
        int idPlane = FlightReader.getIdPlane(idFlight_index);
        int idPlane_index = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlane_index);

        String text = AirlineWebsiteReader.getWebsite(AirlineWebsiteReader.indexOf(airline));
        String format = "jpg";
        //Generate QRCode
        File outputFile = new File("printer-outputqrcode.jpg");
        BarCode_QRCodeGenerator.writeToFile(BarCode_QRCodeGenerator.toQRCodeMatrix(text, 200, 200), format, outputFile);
    }

    public static int colorToHex(Color color) {
        String r,g,b;
        StringBuilder su = new StringBuilder();
        r = Integer.toHexString(color.getRed());
        g = Integer.toHexString(color.getGreen());
        b = Integer.toHexString(color.getBlue());
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() ==1 ? "0" +g : g;
        b = b.length() == 1 ? "0" + b : b;
        r = r.toUpperCase();
        g = g.toUpperCase();
        b = b.toUpperCase();
        su.append(r);
        su.append(g);
        su.append(b);
        return Integer.parseInt(su.toString(), 16);
    }

    public static void setColors() {
        foreground = colorToHex(Theme.getMainFontColor());
        background = colorToHex(Theme.getBackgroundColor());
    }
}
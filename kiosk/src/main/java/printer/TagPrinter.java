package printer;

import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import main.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * A Tag Printer which can print out tags of the carry-on baggage in .txt files
 * @author Wcy
 * @author Ni Ruijie
 *
 * @version 2.0
 * add jpg tag and finish config
 * @data 5/19
 *
 * @version 1.0
 * @date 3/22
 */
public abstract class TagPrinter extends JPanel {
    static String tag_txt;
    static String tag_jpg;

    /**
     * Generate .txt for tags
     *
     * @param idPassengerFlight_index primary key
     */
    public static void creatTag(int idPassengerFlight_index) throws IOException {

        Boolean choosen = Boolean.parseBoolean(Config.readConfig("imagePrinter"));

        if(choosen) {
            TagPrinter.getPhoto(idPassengerFlight_index);
        }
        else {
            TagPrinter.getTxt(idPassengerFlight_index);
        }
    }
    public static void getPhoto(int idPassengerFlight_index) throws IOException{
        int carryon_num = PassengerFlightReader.getCarryon(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int carryon_left = carryon_num;

        Border tagBorder = BorderFactory.createLineBorder(Color.BLACK, 5, false);

        JPanel tagPanel = new JPanel();
        tagPanel.setBorder(tagBorder);
        tagPanel.setBounds(0, 0, 450, 227);
        tagPanel.setLayout(null);

        JLabel lblColor = new JLabel();
        lblColor.setOpaque(true);
        lblColor.setBackground(new Color(192, 48, 48));
        lblColor.setBounds(5, 5, 440, 15);
        tagPanel.add(lblColor);

        JLabel lblTitle = new JLabel("Mr / Ms");
        lblTitle.setVerticalTextPosition(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitle.setForeground(new Color(28, 28, 28));
        lblTitle.setBounds(164, 39, 98, 38);
        tagPanel.add(lblTitle);

        JLabel lblName = new JLabel(surname);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setFont(new Font("Arial", Font.BOLD, 25));
        lblName.setForeground(new Color(28, 28, 28));
        lblName.setBounds(166, 93, 98, 38);
        tagPanel.add(lblName);

        JLabel lblPackage = new JLabel();
        lblPackage.setHorizontalAlignment(JLabel.CENTER);
        lblPackage.setVerticalTextPosition(JLabel.CENTER);
        lblPackage.setFont(new Font("Arial", Font.BOLD, 25));
        lblPackage.setForeground(new Color(28, 28, 28));
        lblPackage.setBounds(44, 152, 361, 35);
        tagPanel.add(lblPackage);
        while (carryon_left >= 1) {
            lblPackage.setText("This is " + (carryon_num - carryon_left + 1) + " / " + carryon_num + " of your baggage");
            String tag_file = idPassenger + "-" + idPassengerFlight_index + "-" + (carryon_num - carryon_left + 1) + "of" + carryon_num;
            String tag_path = "printer-output/";
            tag_jpg = tag_path + tag_file + ".jpg";
            BufferedImage image = new BufferedImage(tagPanel.getWidth(), tagPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            tagPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(tag_jpg));
            carryon_left--;
        }
    }
    public static void getTxt(int idPassengerFlight_index) throws IOException{
        int carryon_num = PassengerFlightReader.getCarryon(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int carryon_left = carryon_num;

        while (carryon_left >= 1) {
            String tag_file = idPassenger + "-" + idPassengerFlight_index + "-" + (carryon_num - carryon_left + 1) + "of" + carryon_num;
            String tag_path = "printer-output/";
            tag_txt = tag_path + tag_file + ".txt";
            File tag = new File(tag_txt);
            tag.createNewFile();
            FileWriter bucket = new FileWriter(tag_txt);
            BufferedWriter buffer = new BufferedWriter(bucket);
            String side_bound = "|";
            buffer.write("_________________________________________");
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("                Mr / Ms                ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            for (int i = 0; i < (39 - surname.length()) / 2; i++) {
                buffer.write(" ");
            }
            buffer.write(surname);
            for (int i = 0; i < (39 - surname.length()) / 2 + (39 - surname.length()) % 2; i++) {
                buffer.write(" ");
            }
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("                                       ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("     This is " + (carryon_num - carryon_left + 1) + " / " + carryon_num + " of your baggage     ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write("_________________________________________");
            carryon_left--;
            buffer.close();
        }
    }
    public static String getFilePhotoPath(){
        return tag_jpg;
    }
    public static String getFileTxtPath(){
        return tag_txt;
    }
}


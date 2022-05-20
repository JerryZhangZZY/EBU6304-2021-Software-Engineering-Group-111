package printer;

import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import dbReader.PlaneReader;
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
 * A Ticket Printer which can print out tickets of the check-in baggage in .txt files
 * @auther Wcy
 * @author Ni Ruijie
 * @author Liang Zhehao
 *
 * @version 5.0
 * @date 2022/5/20
 *
 * @version 2.0
 * add jpg ticket and finish config
 * @date 5/19
 *
 * @version 1.0
 * @date 3/23
 */
public class TicketPrinter extends JPanel {
    static String ticketTxt;
    static String ticketImage;

    /**
     * Generate .txt for tickets
     *
     * @param idPassengerFlightIndex primary key
     */
    public static void creatTicket(int idPassengerFlightIndex) throws IOException{
        Boolean choosen = Boolean.parseBoolean(Config.readConfig("imagePrinter"));
        if (choosen) {
            TicketPrinter.creatTicketJPG(idPassengerFlightIndex);
        }
        else{
            TicketPrinter.creatTicketTXT(idPassengerFlightIndex);
        }
    }
    public static void creatTicketJPG(int idPassengerFlight_index) throws IOException{
        int checkinNum = PassengerFlightReader.getCheckin(idPassengerFlight_index);
        int bagDropCounter = PassengerFlightReader.getBagDropCounter(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlightIndex = FlightReader.indexOf(idFlight);
        String departure = FlightReader.getDeparture(idFlightIndex);
        String arrival = FlightReader.getArrival(idFlightIndex);
        int idPlane = FlightReader.getIdPlane(idFlightIndex);
        int idPlaneIndex = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlaneIndex);
        int idPassengerIndex = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassengerIndex);
        int checkinLeft = checkinNum;
        String ticketFile = idPassenger + "-" + idPassengerFlight_index + "-ticket";
        String ticketPath = "printer-output/";

        ticketImage = ticketPath + ticketFile + ".jpg";

        Border ticketBorder = BorderFactory.createLineBorder(Color.BLACK, 7, false);

        JPanel ticketPanel = new JPanel();
        ticketPanel.setBorder(ticketBorder);
        ticketPanel.setLayout(null);

        JLabel lblTitle = new JLabel("Mr / Ms");
        lblTitle.setVerticalTextPosition(JLabel.CENTER);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setForeground(new Color(28, 28, 28));
        lblTitle.setFont(new Font("plain Arial", Font.BOLD, 25));
        lblTitle.setBounds(176, 39, 98, 38);
        ticketPanel.add(lblTitle);

        JLabel lblName = new JLabel(surname);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setForeground(new Color(28, 28, 28));
        lblName.setFont(new Font("bold Arial", Font.BOLD, 25));
        lblName.setBounds(176, 69, 98, 38);
        ticketPanel.add(lblName);

        JLabel lblFlight = new JLabel("Flight ID");
        lblFlight.setHorizontalAlignment(JLabel.CENTER);
        lblFlight.setVerticalTextPosition(JLabel.CENTER);
        lblFlight.setForeground(new Color(28, 28, 28));
        lblFlight.setFont(new Font("Arial", Font.BOLD, 25));
        lblFlight.setBounds(172, 124, 106, 35);
        ticketPanel.add(lblFlight);

        JLabel lblTopLabel = new JLabel();
        lblTopLabel.setOpaque(true);
        lblTopLabel.setBounds(5, 5, 440, 24);
        lblTopLabel.setBackground(new Color(192, 48, 48));
        ticketPanel.add(lblTopLabel);

        JLabel lblFlightID = new JLabel(idFlight);
        lblFlightID.setVerticalTextPosition(SwingConstants.CENTER);
        lblFlightID.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlightID.setForeground(new Color(28, 28, 28));
        lblFlightID.setFont(new Font("Arial", Font.BOLD, 25));
        lblFlightID.setBounds(153, 157, 145, 35);
        ticketPanel.add(lblFlightID);

        JLabel lblAirline = new JLabel("Airline");
        lblAirline.setVerticalTextPosition(SwingConstants.CENTER);
        lblAirline.setHorizontalAlignment(SwingConstants.CENTER);
        lblAirline.setForeground(new Color(28, 28, 28));
        lblAirline.setFont(new Font("Arial", Font.BOLD, 25));
        lblAirline.setBounds(182, 213, 85, 35);
        ticketPanel.add(lblAirline);

        JLabel lblAirlineName = new JLabel(airline);
        lblAirlineName.setVerticalTextPosition(SwingConstants.CENTER);
        lblAirlineName.setHorizontalAlignment(SwingConstants.CENTER);
        lblAirlineName.setForeground(new Color(28, 28, 28));
        lblAirlineName.setFont(new Font("Arial", Font.BOLD, 25));
        lblAirlineName.setBounds(116, 245, 218, 35);
        ticketPanel.add(lblAirlineName);

        JLabel lblDeparture = new JLabel("Departure");
        lblDeparture.setVerticalTextPosition(SwingConstants.CENTER);
        lblDeparture.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeparture.setForeground(new Color(28, 28, 28));
        lblDeparture.setFont(new Font("Arial", Font.BOLD, 25));
        lblDeparture.setBounds(162, 304, 126, 35);
        ticketPanel.add(lblDeparture);

        JLabel lblDepartureName = new JLabel(departure);
        lblDepartureName.setVerticalTextPosition(SwingConstants.CENTER);
        lblDepartureName.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartureName.setForeground(new Color(28, 28, 28));
        lblDepartureName.setFont(new Font("Arial", Font.BOLD, 25));
        lblDepartureName.setBounds(35, 348, 381, 35);
        ticketPanel.add(lblDepartureName);

        JLabel lblArrival = new JLabel("Arrival");
        lblArrival.setVerticalTextPosition(SwingConstants.CENTER);
        lblArrival.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrival.setForeground(new Color(28, 28, 28));
        lblArrival.setFont(new Font("Arial", Font.BOLD, 25));
        lblArrival.setBounds(165, 403, 120, 35);
        ticketPanel.add(lblArrival);

        JLabel lblArrivalName = new JLabel(arrival);
        lblArrivalName.setVerticalTextPosition(SwingConstants.CENTER);
        lblArrivalName.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrivalName.setForeground(new Color(28, 28, 28));
        lblArrivalName.setFont(new Font("Arial", Font.BOLD, 25));
        lblArrivalName.setBounds(122, 441, 207, 35);
        ticketPanel.add(lblArrivalName);

        JLabel lblBag = new JLabel("Bag Drop Counter");
        lblBag.setVerticalTextPosition(SwingConstants.CENTER);
        lblBag.setHorizontalAlignment(SwingConstants.CENTER);
        lblBag.setForeground(new Color(28, 28, 28));
        lblBag.setFont(new Font("Arial", Font.BOLD, 25));
        lblBag.setBounds(100, 500, 250, 35);
        ticketPanel.add(lblBag);

        JLabel lblBagNum = new JLabel(Integer.toString(bagDropCounter));
        lblBagNum.setVerticalTextPosition(SwingConstants.CENTER);
        lblBagNum.setHorizontalAlignment(SwingConstants.CENTER);
        lblBagNum.setForeground(new Color(28, 28, 28));
        lblBagNum.setFont(new Font("Arial", Font.BOLD, 25));
        lblBagNum.setBounds(176, 545, 98, 38);
        ticketPanel.add(lblBagNum);

        JLabel lblSecond = new JLabel();
        lblSecond.setOpaque(true);
        lblSecond.setBackground(new Color(192, 48, 48));
        lblSecond.setBounds(5, 601, 440, 8);
        ticketPanel.add(lblSecond);

        JLabel lblCheckText1 = new JLabel("Check-in baggage");
        lblCheckText1.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheckText1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheckText1.setForeground(new Color(28, 28, 28));
        lblCheckText1.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheckText1.setBounds(100, 643, 250, 35);
        ticketPanel.add(lblCheckText1);

        JLabel lblCheck1 = new JLabel();
        lblCheck1.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck1.setForeground(new Color(28, 28, 28));
        lblCheck1.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck1.setBounds(100, 701, 250, 35);
        ticketPanel.add(lblCheck1);

        JLabel lblThird = new JLabel();
        lblThird.setOpaque(true);
        lblThird.setBackground(new Color(192, 48, 48));
        lblThird.setBounds(5, 765, 440, 8);
        ticketPanel.add(lblThird);

        JLabel lblCheckText2 = new JLabel("Check-in baggage");
        lblCheckText2.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheckText2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheckText2.setForeground(new Color(28, 28, 28));
        lblCheckText2.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheckText2.setBounds(100, 811, 250, 35);
        ticketPanel.add(lblCheckText2);

        JLabel lblCheck2 = new JLabel("2 of 3");
        lblCheck2.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck2.setForeground(new Color(28, 28, 28));
        lblCheck2.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck2.setBounds(100, 867, 250, 35);
        ticketPanel.add(lblCheck2);

        JLabel lblForth = new JLabel();
        lblForth.setOpaque(true);
        lblForth.setBackground(new Color(192, 48, 48));
        lblForth.setBounds(5, 930, 440, 8);
        ticketPanel.add(lblForth);

        JLabel lblCheckText3 = new JLabel("Check-in baggage");
        lblCheckText3.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheckText3.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheckText3.setForeground(new Color(28, 28, 28));
        lblCheckText3.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheckText3.setBounds(100, 960, 250, 35);
        ticketPanel.add(lblCheckText3);

        JLabel lblCheck3 = new JLabel("3 of 3");
        lblCheck3.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck3.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck3.setForeground(new Color(28, 28, 28));
        lblCheck3.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck3.setBounds(100, 1009, 250, 35);
        ticketPanel.add(lblCheck3);
        if (checkinLeft == 0) {
            ticketPanel.setBounds(0, 0, 450, 508);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticketImage));
        } else if (checkinLeft == 1) {
            lblCheck1.setText("1 of 1");
            ticketPanel.setBounds(0, 0, 450, 765);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticketImage));
        } else if (checkinLeft == 2) {
            lblCheck1.setText("1 of 2");
            lblCheck2.setText("2 of 2");
            ticketPanel.setBounds(0, 0, 450, 930);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticketImage));
        } else if (checkinLeft == 3) {
            lblCheck1.setText("1 of 3");
            lblCheck2.setText("2 of 3");
            lblCheck3.setText("3 of 3");
            ticketPanel.setBounds(0, 0, 450, 1095);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticketImage));
        }
    }
    public static void creatTicketTXT(int idPassengerFlight_index) throws IOException{
        int checkinNum = PassengerFlightReader.getCheckin(idPassengerFlight_index);
        int bagDropCounter = PassengerFlightReader.getBagDropCounter(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlightIndex = FlightReader.indexOf(idFlight);
        String departure = FlightReader.getDeparture(idFlightIndex);
        String arrival = FlightReader.getArrival(idFlightIndex);
        int idPlane = FlightReader.getIdPlane(idFlightIndex);
        int idPlaneIndex = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlaneIndex);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int checkinLeft = checkinNum;
        String ticketFile = idPassenger + "-" + idPassengerFlight_index + "-ticket";
        String ticketPath = "printer-output/";

        ticketTxt = ticketPath + ticketFile + ".txt";
        File ticket = new File(ticketTxt);
        ticket.createNewFile();
        FileWriter bucket = new FileWriter(ticketTxt);
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
        buffer.write("               Flight ID               ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        for (int i = 0; i < (39 - idFlight.length()) / 2; i++) {
            buffer.write(" ");
        }
        buffer.write(idFlight);
        for (int i = 0; i < (39 - idFlight.length()) / 2 + (39 - idFlight.length()) % 2; i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                                       ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                Airline                ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        for (int i = 0; i < (39 - airline.length()) / 2; i++) {
            buffer.write(" ");
        }
        buffer.write(airline);
        for (int i = 0; i < (39 - airline.length()) / 2 + (39 - airline.length()) % 2; i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                                       ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("               Departure               ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        for (int i = 0; i < (39 - departure.length()) / 2; i++) {
            buffer.write(" ");
        }
        buffer.write(departure);
        for (int i = 0; i < (39 - departure.length()) / 2 + (39 - departure.length()) % 2; i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                                       ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                Arrival                ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        for (int i = 0; i < (39 - arrival.length()) / 2; i++) {
            buffer.write(" ");
        }
        buffer.write(arrival);
        for (int i = 0; i < (39 - arrival.length()) / 2 + (39 - arrival.length()) % 2; i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        if (bagDropCounter != -1) {
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("                                       ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("            Bag Drop Counter           ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            for (int i = 0; i < (39 - Integer.toString(bagDropCounter).length()) / 2; i++) {
                buffer.write(" ");
            }
            buffer.write(Integer.toString(bagDropCounter));
            for (int i = 0; i < (39 - Integer.toString(bagDropCounter).length()) / 2 + (39 - Integer.toString(bagDropCounter).length()) % 2; i++) {
                buffer.write(" ");
            }
            buffer.write(side_bound);
        }
        while (checkinLeft >= 1) {
            buffer.newLine();
            buffer.write("_________________________________________");
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("            Check-in baggage           ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("                                       ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write(side_bound);
            buffer.write("                " + (checkinNum - checkinLeft + 1) + " of " + checkinNum + "                 ");
            buffer.write(side_bound);
            checkinLeft--;
        }
        buffer.newLine();
        buffer.write("_________________________________________");
        buffer.close();
    }
    public static String getFilePhotoPath(){
        return ticketImage;
    }
    public static String getFileTxtPath(){
        return ticketTxt;
    }
}


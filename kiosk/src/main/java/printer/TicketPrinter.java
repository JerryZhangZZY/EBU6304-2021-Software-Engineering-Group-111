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
 *
 * @version 2.0
 * add jpg ticket and finish config
 * @date 5/19
 *
 * @version 1.0
 * @date 3/23
 */
public class TicketPrinter extends JPanel {
    static String ticket_txt;
    static String ticket_jpg;

    /**
     * Generate .txt for tickets
     *
     * @param idPassengerFlight_index primary key
     */
    public static void creatTicket(int idPassengerFlight_index) throws IOException{
        Boolean choosen = Boolean.parseBoolean(Config.readConfig("imagePrinter"));
        if (choosen) {
            TicketPrinter.creatTicketJPG(idPassengerFlight_index);
        }
        else{
            TicketPrinter.creatTicketTXT(idPassengerFlight_index);
        }
    }
    public static void creatTicketJPG(int idPassengerFlight_index) throws IOException{
        int checkin_num = PassengerFlightReader.getCheckin(idPassengerFlight_index);
        int bagDropCounter = PassengerFlightReader.getBagDropCounter(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlight_index = FlightReader.indexOf(idFlight);
        String departure = FlightReader.getDeparture(idFlight_index);
        String arrival = FlightReader.getArrival(idFlight_index);
        int idPlane = FlightReader.getIdPlane(idFlight_index);
        int idPlane_index = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlane_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int checkin_left = checkin_num;
        String ticket_file = idPassenger + "-" + idPassengerFlight_index + "-ticket";
        String ticket_path = "printer-output/";

        ticket_jpg = ticket_path + ticket_file + ".jpg";

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

        JLabel lblCheck = new JLabel("Check-in baggage");
        lblCheck.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck.setForeground(new Color(28, 28, 28));
        lblCheck.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck.setBounds(100, 643, 250, 35);
        ticketPanel.add(lblCheck);

        JLabel lblCheck_1 = new JLabel("1 of 3");
        lblCheck_1.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck_1.setForeground(new Color(28, 28, 28));
        lblCheck_1.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck_1.setBounds(100, 701, 250, 35);
        ticketPanel.add(lblCheck_1);

        JLabel lblThird = new JLabel();
        lblThird.setOpaque(true);
        lblThird.setBackground(new Color(192, 48, 48));
        lblThird.setBounds(5, 765, 440, 8);
        ticketPanel.add(lblThird);

        JLabel lblCheck_2 = new JLabel("Check-in baggage");
        lblCheck_2.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck_2.setForeground(new Color(28, 28, 28));
        lblCheck_2.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck_2.setBounds(100, 811, 250, 35);
        ticketPanel.add(lblCheck_2);

        JLabel lblCheck_2_1 = new JLabel("2 of 3");
        lblCheck_2_1.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck_2_1.setForeground(new Color(28, 28, 28));
        lblCheck_2_1.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck_2_1.setBounds(100, 867, 250, 35);
        ticketPanel.add(lblCheck_2_1);

        JLabel lblForth = new JLabel();
        lblForth.setOpaque(true);
        lblForth.setBackground(new Color(192, 48, 48));
        lblForth.setBounds(5, 930, 440, 8);
        ticketPanel.add(lblForth);

        JLabel lblCheck_3 = new JLabel("Check-in baggage");
        lblCheck_3.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck_3.setForeground(new Color(28, 28, 28));
        lblCheck_3.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck_3.setBounds(100, 960, 250, 35);
        ticketPanel.add(lblCheck_3);

        JLabel lblCheck_3_1 = new JLabel("3 of 3");
        lblCheck_3_1.setVerticalTextPosition(SwingConstants.CENTER);
        lblCheck_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheck_3_1.setForeground(new Color(28, 28, 28));
        lblCheck_3_1.setFont(new Font("Arial", Font.BOLD, 25));
        lblCheck_3_1.setBounds(100, 1009, 250, 35);
        ticketPanel.add(lblCheck_3_1);
        if (checkin_left == 0) {
            ticketPanel.setBounds(0, 0, 450, 508);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticket_jpg));
        } else if (checkin_left == 1) {
            ticketPanel.setBounds(0, 0, 450, 765);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticket_jpg));
        } else if (checkin_left == 2) {
            ticketPanel.setBounds(0, 0, 450, 930);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticket_jpg));
        } else if (checkin_left == 3) {
            ticketPanel.setBounds(0, 0, 450, 1095);
            BufferedImage image = new BufferedImage(ticketPanel.getWidth(), ticketPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            ticketPanel.paint(g2); // instead of just paint(g2);
            ImageIO.write(image, "jpeg", new java.io.File(ticket_jpg));
        }
    }
    public static void creatTicketTXT(int idPassengerFlight_index) throws IOException{
        int checkin_num = PassengerFlightReader.getCheckin(idPassengerFlight_index);
        int bagDropCounter = PassengerFlightReader.getBagDropCounter(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlight_index = FlightReader.indexOf(idFlight);
        String departure = FlightReader.getDeparture(idFlight_index);
        String arrival = FlightReader.getArrival(idFlight_index);
        int idPlane = FlightReader.getIdPlane(idFlight_index);
        int idPlane_index = PlaneReader.indexOf(idPlane);
        String airline = PlaneReader.getAirline(idPlane_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int checkin_left = checkin_num;
        String ticket_file = idPassenger + "-" + idPassengerFlight_index + "-ticket";
        String ticket_path = "printer-output/";

        ticket_txt = ticket_path + ticket_file + ".txt";
        File ticket = new File(ticket_txt);
        ticket.createNewFile();
        FileWriter bucket = new FileWriter(ticket_txt);
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
        while (checkin_left >= 1) {
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
            buffer.write("                " + (checkin_num - checkin_left + 1) + " of " + checkin_num + "                 ");
            buffer.write(side_bound);
            checkin_left--;
        }
        buffer.newLine();
        buffer.write("_________________________________________");
        buffer.close();
    }
    public static String getFilePhotoPath(){
        return ticket_jpg;
    }
    public static String getFileTxtPath(){
        return ticket_txt;
    }
}


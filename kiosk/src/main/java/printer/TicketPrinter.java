package printer;

import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import dbReader.PlaneReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A Ticket Printer which can print out tickets of the check-in baggage in .txt files
 *
 * @author Ni Ruijie
 * @version 1.0
 * @date 3/23
 */
public class TicketPrinter {
    static String ticket_temp;
    /**
     * Generate .txt for tickets
     * @param idPassengerFlight_index primary key
     */
    public static void creatTicket(int idPassengerFlight_index)throws IOException {
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

        String ticket_file = idPassenger +"-"+idPassengerFlight_index+"-ticket";
        String ticket_path ="printer-output/";
        ticket_temp = ticket_path +ticket_file +".txt";
        File ticket = new File(ticket_temp);
        ticket.createNewFile();
        FileWriter bucket=new FileWriter(ticket_temp);
        BufferedWriter buffer = new BufferedWriter(bucket);
        String side_bound = "|";
        buffer.write("_________________________________________");
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                Mr / Ms                ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        for(int i= 0;i<(39-surname.length())/2;i++){
            buffer.write(" ");
        }
        buffer.write(surname);
        for(int i= 0;i<(39-surname.length())/2+(39-surname.length())%2;i++){
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
        for(int i= 0;i<(39-idFlight.length())/2;i++){
            buffer.write(" ");
        }
        buffer.write(idFlight);
        for(int i= 0;i<(39-idFlight.length())/2+(39-idFlight.length())%2;i++){
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
        for(int i= 0;i<(39-airline.length())/2;i++){
            buffer.write(" ");
        }
        buffer.write(airline);
        for(int i= 0;i<(39-airline.length())/2+(39-airline.length())%2;i++){
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
        for(int i= 0;i<(39-departure.length())/2;i++){
            buffer.write(" ");
        }
        buffer.write(departure);
        for(int i= 0;i<(39-departure.length())/2+(39-departure.length())%2;i++){
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
        for(int i= 0;i<(39-arrival.length())/2;i++){
            buffer.write(" ");
        }
        buffer.write(arrival);
        for(int i= 0;i<(39-arrival.length())/2+(39-arrival.length())%2;i++){
            buffer.write(" ");
        }
        buffer.write(side_bound);
        if(bagDropCounter!=-1){
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
            for(int i= 0;i<(39-Integer.toString(bagDropCounter).length())/2;i++){
                buffer.write(" ");
            }
            buffer.write(Integer.toString(bagDropCounter));
            for(int i= 0;i<(39-Integer.toString(bagDropCounter).length())/2+(39-Integer.toString(bagDropCounter).length())%2;i++){
                buffer.write(" ");
            }
            buffer.write(side_bound);
        }
        while(checkin_left>=1){
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
            buffer.write("                "+(checkin_num-checkin_left+1)+" of "+checkin_num+"                 ");
            buffer.write(side_bound);
            checkin_left--;
        }
        buffer.newLine();
        buffer.write("_________________________________________");
        buffer.close();
    }

    public static String getFilePath(){
        return ticket_temp;
    }
}


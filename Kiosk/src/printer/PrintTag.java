/**
 * @version 1.0
 * @author Ni Ruijie
 * @date 3/22
 * A Tag Printer which can print out tags of the carry-on baggage in .txt files
 */
package printer;

import dbReader.*;
import java.io.*;

public abstract class PrintTag {
    /**
     * Generate .txt form tags
     * @param idPassengerFlight primary key
     */
    public static void creatTag(int idPassengerFlight)throws IOException{
        int idPassengerFlight_index= PassengerFlightReader.indexOf(idPassengerFlight);
        int carryon_num = PassengerFlightReader.getCarryon(idPassengerFlight_index);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        int carryon_left = carryon_num;

        while(carryon_left>=1){
            String tag_file = idPassenger +"-"+idPassengerFlight_index+"-"+(carryon_num-carryon_left+1)+"of"+carryon_num;
            String tag_path ="Kiosk/printerOutPut/";
            String tag_temp = tag_path +tag_file +".txt";
            File tag = new File(tag_temp);
            tag.createNewFile();
            FileWriter bucket=new FileWriter(tag_temp);
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
            buffer.write("     This is "+(carryon_num-carryon_left+1)+" / "+carryon_num+" of your baggage     ");
            buffer.write(side_bound);
            buffer.newLine();
            buffer.write("_________________________________________");
            carryon_left --;
            buffer.close();
        }
    }
}

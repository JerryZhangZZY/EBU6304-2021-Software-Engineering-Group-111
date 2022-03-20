package cardTest;

import card.FlightInfoCard;
import dbReader.FlightReader;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * This test reads flight info from json database and generate a flight info card on a frame.
 *
 * @author Zhang Zeyu
 * @date 2022/3/19
 * @version 1.0
 */

public class FlightInfoCardTest extends JFrame{

    public static void main(String[] args) throws IOException {

        String idFlight = null;
        String date = null;
        String departureTime = null;
        String arrivalTime = null;
        String departure = null;
        String arrival = null;

//        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
//        JSONArray arr = obj.getJSONArray("flight");
//        for (int i = 0; i < arr.size(); i++) {
//            if(arr.getJSONObject(i).getString("idFlight").equals("CA0001")) {
//                idFlight = arr.getJSONObject(i).getString("idFlight");
//                date = arr.getJSONObject(i).getString("departureTime").substring(0, 10);
//                departureTime = arr.getJSONObject(i).getString("departureTime").substring(11);
//                arrivalTime = arr.getJSONObject(i).getString("arrivalTime").substring(11);
//                departure = arr.getJSONObject(i).getString("departure");
//                arrival = arr.getJSONObject(i).getString("arrival");
//                break;
//            }
//        }

        //switch to new dbReader tools
        idFlight = "AC0001";
        int tmpIndex = FlightReader.indexOf(idFlight);
        date = FlightReader.getDate(tmpIndex);
        departureTime = FlightReader.getDepartureTime(tmpIndex);
        arrivalTime = FlightReader.getArrivalTime(tmpIndex);
        departure = FlightReader.getDeparture(tmpIndex);
        arrival = FlightReader.getArrival(tmpIndex);

        FlightInfoCardTest frame = new FlightInfoCardTest();
        frame.setSize(1900,1600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(244,244,244));
        frame.setContentPane(contentPane);
        contentPane.add(new FlightInfoCard(idFlight,
                                     date,
                                     departureTime,
                                     arrivalTime,
                                     departure,
                                     arrival));
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

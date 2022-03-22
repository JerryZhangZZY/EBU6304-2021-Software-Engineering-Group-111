package card;

import dbReader.PassengerFlightReader;
import idCardReader.IdCardReader;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class returns a panel of scanning login card.
 *
 * @author Zhang Zeyu
 * @date 2022/3/22
 * @version 1.0
 */

public abstract class ScanIdLoginCard extends JPanel {

    /**
     * Scan the id card and give result back.
     * @return [F,F,F]: Scan failed; []: No booking number; [bn1]/[bn1,bn2]: Booking number(s).
     */
    public static List<String> getBookingNumByScanning() {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(PassengerFlightReader.getBookingNumByPassengerId(IdCardReader.readId()));
            return list;
        } catch (IOException e) {
//            e.printStackTrace();
            for(int i = 0; i < 3; i++)
                list.add("F");
            return list;
        }
    }
}

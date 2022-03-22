package main;
import card.FlightInfoCard;
import frame.*;
import panel.*;

import javax.swing.*;

import static java.lang.Thread.sleep;

public class Control {
    public static void main(String[] args) throws InterruptedException {
        JFrame program;
        JPanel panel = new JPanel();
        MainFrame program1 = new MainFrame(123001);
        program1.displayComponents(false, false, false);
        //sleep(1000);
        program1.loadPanel(panel);
        panel = new EnterIDPanel();
        //program1.setVisible(true);
        //sleep(1000);
        program1.loadPanel(new FlightInfoCard("idFlight",
                "date",
                "departureTime",
                "arrivalTime",
                "departure",
                "arrival"));
        program1.setVisible(true);


    }
}

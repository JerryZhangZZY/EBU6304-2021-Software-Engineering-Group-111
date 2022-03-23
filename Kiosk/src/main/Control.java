package main;
import card.FlightInfoCard;
import card.SeatSelectionCard;
import frame.*;
import panel.*;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * the core class that run with main, managing all frames and panels
 */
public class Control {
    public static void main(String[] args) throws InterruptedException {
        MainFrame kiosk;
        EnterIDPanel enterIDPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        /*
        main frame
         */
        kiosk = new MainFrame(1);
        kiosk.displayComponents(true, true, true);
        /*
        enter id panel
         */
        enterIDPanel = new EnterIDPanel();
        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);
        seatPanel.loadCards(new SeatSelectionCard( "AC0001",
                "Normal", "Legroom Pro",
                "Legroom Max", "Legroom Ultra",
                0, 10, 20, 50));

        int currentPC = 1;
        State.setPc(currentPC);
        currentPC = 0;
        while (true){
            kiosk.setVisible(true);
            while (currentPC == State.getPc()){
                sleep(1000);
            }
            switch (State.getPc()) {
                case 1:{    //enter ID
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(enterIDPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 3:{    //flights
                    break;
                }
                case 4:{    //seat
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(seatPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 5: {    //food
                    kiosk.unloadPanel(kiosk.getLoadedPanel());

                    currentPC  = State.getPc();
                    kiosk.repaint();
                    break;
                }


            }
        }
//            kiosk.loadPanel(new FlightInfoCard("idFlight",
//                    "date",
//                    "departureTime",
//                    "arrivalTime",
//                    "departure",
//                    "arrival"));

    }
}

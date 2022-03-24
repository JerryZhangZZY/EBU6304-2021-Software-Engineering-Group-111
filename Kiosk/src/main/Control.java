package main;
import card.FoodSelectionCard;
import card.SeatSelectionCard;
import frame.*;
import panel.*;

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
        WelcomePanel welcomePanel;
        BookingLoginPanel bookingLoginPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        /*
        main frame
         */
        kiosk = new MainFrame();
        kiosk.displayComponents(true, true, true);
        /*
        welcoming panel
         */
        welcomePanel = new WelcomePanel();
        /*
        booking number login panel
         */
        bookingLoginPanel = new BookingLoginPanel();
        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);
        seatPanel.loadCards(new SeatSelectionCard());
        /*
        meal choosing panel
         */
        mealPanel = new ProgressPanel(3);
        mealPanel.loadCards(new FoodSelectionCard());
        /*
        control flow
         */
        int currentPC = 0;
        State.setPc(currentPC);
        currentPC = -1;
        while (true){
            kiosk.setVisible(true);
            while (currentPC == State.getPc()){
                sleep(1);
            }
            switch (State.getPc()) {

                case 0: {    //welcome
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.hideBars(true);
                    kiosk.loadPanel(welcomePanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 1:{    //enter ID
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.hideBars(false);
                    kiosk.resetWelcomeText(1);
                    kiosk.loadPanel(bookingLoginPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 3:{    //flights
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.setWelcomeText();
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
                    kiosk.loadPanel(mealPanel);
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

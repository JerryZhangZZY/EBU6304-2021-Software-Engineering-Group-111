package main;

import frame.*;
import panel.*;

import static java.lang.Thread.sleep;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * the core class that run with main, managing all frames and panels
 *
 * @version 1.1
 * @author Ni Ruijie
 * @date 3/27
 * Add function: Flight selection page won't display the flight card which has been checked-in.
 * bugs fixed
 *
 * @version 1.2
 * @author Ni Ruijie
 * @date 3/27
 * Add function: Automatically back to welcome page when there is no flight available for check-in.
 */
public class Control {
    public static void main(String[] args) throws InterruptedException {
        MainFrame kiosk;
        WelcomePanel welcomePanel;
        BookingLoginPanel bookingLoginPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        FinalPanel finalPanel;
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel(true);
        MealSelectionPanel mealSelectionPanel = new MealSelectionPanel(false);
//        BillConfirmationPanel billConfirmationPanel = new BillConfirmationPanel();
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
        flight choosing panel
         */
        flightsPanel = new ProgressPanel(1);
        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);
//        seatPanel.loadCards(new SeatSelectionCard());
        /*
        meal choosing panel
         */
        mealPanel = new ProgressPanel(3);
//        mealPanel.loadCards(new MealSelectionCard());
        /*
        confirm and bill panel
         */
        billPanel = new ProgressPanel(4);
        /*
        final panel
         */
//        finalPanel = new FinalPanel();
        /*
        control flow
         */
        int currentPC = -1;
        State.setPc(0);
        State.setIsReady(new boolean[]{true, true, true,
                false, false, false, false, true, true});
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
                case 1:{    //enter booking number
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.hideBars(false);
                    kiosk.displayComponents(true, true, false);
                    kiosk.resetWelcomeText(1);
                    kiosk.loadPanel(bookingLoginPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
                case 2:{    //enter or scan ID
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.displayComponents(true, true, true);
                }
                case 3:{    //flights
                    currentPC = State.getPc();
                    if (!State.getIsReady()[3]) {
                        flightsPanel = new ProgressPanel(1);
                        flightsPanel.loadCardsPanel(new FlightSelectionPanel());
                    }
                    kiosk.displayComponents(true, true, false);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(flightsPanel);
                    kiosk.revalidate();
                    kiosk.setWelcomeText();
                    System.out.println(State.getPc());
                    FlightSelectionPanel.automaticallyExit();
                    break;
                }
                case 4:{    //seat
                    if (!State.getIsReady()[4]) {
                        State.resetSmallBillCard();
                        seatPanel = new ProgressPanel(2);
                        seatSelectionPanel = new SeatSelectionPanel();
                        seatPanel.loadCardsPanel(seatSelectionPanel);
                    }
                    seatSelectionPanel.add(State.smallBillCard);
                    kiosk.displayComponents(true, true, true);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(seatPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
                case 5:{    //food
                    if (!State.getIsReady()[5]) {
                        mealPanel = new ProgressPanel(3);
                        mealSelectionPanel = new MealSelectionPanel();
                        mealPanel.loadCardsPanel(mealSelectionPanel);
                    }
                    mealSelectionPanel.add(State.smallBillCard);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(mealPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
                case 6: {    //bill
                    if (!State.getIsReady()[6]) {
                        billPanel = new ProgressPanel(4);
                        billPanel.loadCardsPanel(new BillConfirmationPanel());
                    }
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(billPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
                case 7: {   //pay
                    payPanel = new ProgressPanel(4);
                    payPanel.loadCardsPanel(new PaymentPanel());
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(payPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
                case 8:{    //finish
                    finalPanel = new FinalPanel();
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(finalPanel);
                    kiosk.revalidate();
                    currentPC = State.getPc();
                    break;
                }
            }
        }
    }
}

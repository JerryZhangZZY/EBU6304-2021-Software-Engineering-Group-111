package main;

import frame.*;
import panel.*;

import static java.lang.Thread.sleep;

/**
 * the core class that runs main method, managing all frames and panels
 *
 * @author Zhang Zeyu
 * @author Ni Ruijie
 * @author zaitian
 * @author Liang Zhehao
 *
 * @version 2.2
 * load config at launch
 * @date 4/19
 *
 * @version 2.1
 * Upgrade to new animation methods.
 * @date 2022/4/11
 *
 * @version 2.0
 * Replace automaticallyExit() with a simpler Thread.
 * @date 2022/4/8
 *
 * @version 1.4
 * Performance improved.
 * @date 2022/3/29
 *
 * @version 1.3
 * Add alter login panel.
 * @date 2022/3/28
 *
 * @version 1.2
 * Add function: Automatically back to welcome page when there is no flight available for check-in.
 * @date 3/27
 *
 * @version 1.1
 * Add function: Flight selection page won't display the flight card which has been checked-in.
 * bugs fixed
 * @date 3/27
 *
 * @version 1.0
 * @date 3/22
 */
public class Control {
    /**
     * main method to control
     * @param args potential configurations
     * @throws InterruptedException if interrupted when sleeping, not designed to occur
     */
    public static void main(String[] args) throws InterruptedException {
        Config.loadConfig();
        Theme.loadColor();

        MainFrame kiosk;
        BookingLoginPanel bookingLoginPanel;
        IdLoginPanel idLoginPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        FinalPanel finalPanel;
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel(true);
        MealSelectionPanel mealSelectionPanel = new MealSelectionPanel(false);
        /*
        main frame
         */
        kiosk = new MainFrame();
        kiosk.displayComponents(true, true, true);

        /*
        booking number login panel
         */
        bookingLoginPanel = new BookingLoginPanel();

        /*
        id login panel
         */
        idLoginPanel = new IdLoginPanel();

        /*
        flight choosing panel
         */
        flightsPanel = new ProgressPanel(1);

        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);

        /*
        meal choosing panel
         */
        mealPanel = new ProgressPanel(3);

        /*
        confirm and bill panel
         */
        billPanel = new ProgressPanel(4);

        /*
        payment panel
         */
        payPanel = new ProgressPanel(4);

        /*
        final panel
         */
        //instantiate everytime.

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
                sleep(10);
            }
            switch (State.getPc()) {
                case 0 : {    //welcome
                    kiosk.showClock(false);
                    kiosk.displayComponents(true, true, false);
                    kiosk.hideBars(false);
                    kiosk.resetWelcomeText();
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.repaint();
                    kiosk.loadPanel(new BookingLoginPanel());
                    kiosk.lockScreen();
                    currentPC = State.getPc();
                    break;
                }
                case 1 : {    //enter booking number
                    if (currentPC < State.getPc())
                        kiosk.unlockScreen();
                    else {
                        kiosk.displayComponents(true, true, false);
                        kiosk.hideBars(false);
                        kiosk.resetWelcomeText();
                        bookingLoginPanel.reset();
                        kiosk.scrollUp(bookingLoginPanel);
                    }
                    currentPC = State.getPc();
                    break;
                }
                case 2 : {    //enter or scan ID
                    kiosk.displayComponents(true, true, true);
                    idLoginPanel.reset();
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(idLoginPanel);
                    else
                        kiosk.scrollUp(idLoginPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 3 : {    //flights
                    if (!State.getIsReady()[3]) {
                        flightsPanel = new ProgressPanel(1);
                        flightsPanel.loadCardsPanel(new FlightSelectionPanel());
                    }
                    kiosk.displayComponents(true, true, false);
                    kiosk.setWelcomeText();
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(flightsPanel);
                    else
                        kiosk.scrollUp(flightsPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 4 : {    //seat
                    if (!State.getIsReady()[4]) {
                        State.resetSmallBillCard();
                        seatPanel = new ProgressPanel(2);
                        seatSelectionPanel = new SeatSelectionPanel();
                        seatPanel.loadCardsPanel(seatSelectionPanel);
                    }
                    seatSelectionPanel.add(State.smallBillCard);
                    kiosk.displayComponents(true, true, true);
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(seatPanel);
                    else
                        kiosk.scrollUp(seatPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 5 : {    //food
                    if (!State.getIsReady()[5]) {
                        mealPanel = new ProgressPanel(3);
                        mealSelectionPanel = new MealSelectionPanel();
                        mealPanel.loadCardsPanel(mealSelectionPanel);
                    }
                    mealSelectionPanel.add(State.smallBillCard);
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(mealPanel);
                    else
                        kiosk.scrollUp(mealPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 6 : {    //bill
                    if (!State.getIsReady()[6]) {
                        billPanel = new ProgressPanel(4);
                        billPanel.loadCardsPanel(new BillConfirmationPanel());
                    }
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(billPanel);
                    else
                        kiosk.scrollUp(billPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 7 : {   //pay
                    payPanel.loadCardsPanel(new PaymentPanel(State.getBill()));
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(payPanel);
                    else
                        kiosk.scrollUp(payPanel);
                    currentPC = State.getPc();
                    break;
                }
                case 8 : {    //finish
                    finalPanel = new FinalPanel();
                    kiosk.displayComponents(true, false, false);
                    kiosk.scrollDown(finalPanel);
                    currentPC = State.getPc();
                    break;
                }
            }
        }
    }
}

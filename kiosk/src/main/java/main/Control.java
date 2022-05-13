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
 * @version 4.1
 * No-operation detection
 * @date 2022/5/11
 *
 * @version 4.0
 * Auto dark theme.
 * @date 2022/5/7
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
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        FinalPanel finalPanel;
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel(true);
        MealSelectionPanel mealSelectionPanel = new MealSelectionPanel(false);

        kiosk = new MainFrame();
        flightsPanel = new ProgressPanel(1);
        seatPanel = new ProgressPanel(2);
        mealPanel = new ProgressPanel(3);
        billPanel = new ProgressPanel(4);

        /*
        control flow
         */
        int currentPC = -1;
        State.setPc(0);
        State.setIsReady(new boolean[]{true, true, true,
                false, false, false, false, true, true});
        while (true){
            kiosk.setVisible(true);
            while (currentPC == State.getPc())
                sleep(10);
            switch (State.getPc()) {
                case 0 -> {    //welcome
                    kiosk.stopMouseListener();
                    if (Theme.autoDarkTheme()) {
                        kiosk.refreshColor();
                    }
                    kiosk.showClock(false);
                    kiosk.displayComponents(true, true, false);
                    kiosk.resetWelcomeText();
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(new BookingLoginPanel());
                    kiosk.mouseListener();
                    kiosk.repaint();
                    kiosk.lockScreen();
                    currentPC = State.getPc();
                }
                case 1 -> {    //enter booking number
                    if (currentPC < State.getPc())
                        kiosk.unlockScreen();
                    else {
                        kiosk.displayComponents(true, true, false);
                        kiosk.resetWelcomeText();
                        kiosk.scrollUp(new BookingLoginPanel());
                    }
                    kiosk.mouseListener();
                    currentPC = State.getPc();
                }
                case 2 -> {    //enter or scan ID
                    kiosk.displayComponents(true, true, true);
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(new IdLoginPanel());
                    else
                        kiosk.scrollUp(new IdLoginPanel());
                    kiosk.mouseListener();
                    currentPC = State.getPc();
                }
                case 3 -> {    //flights
                    kiosk.stopMouseListener();
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
                }
                case 4 -> {    //seat
                    if (!State.getIsReady()[4]) {
                        State.resetSmallBillCard();
                        seatPanel = new ProgressPanel(2);
                        seatSelectionPanel = new SeatSelectionPanel();
                        seatPanel.loadCardsPanel(seatSelectionPanel);
                    }
                    seatSelectionPanel.add(State.getSmallBillCard());
                    kiosk.displayComponents(true, true, true);
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(seatPanel);
                    else
                        kiosk.scrollUp(seatPanel);
                    currentPC = State.getPc();
                }
                case 5 -> {    //food
                    if (!State.getIsReady()[5]) {
                        mealPanel = new ProgressPanel(3);
                        mealSelectionPanel = new MealSelectionPanel();
                        mealPanel.loadCardsPanel(mealSelectionPanel);
                    }
                    mealSelectionPanel.add(State.getSmallBillCard());
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(mealPanel);
                    else
                        kiosk.scrollUp(mealPanel);
                    currentPC = State.getPc();
                }
                case 6 -> {    //bill
                    if (!State.getIsReady()[6]) {
                        billPanel = new ProgressPanel(4);
                        billPanel.loadCardsPanel(new BillConfirmationPanel());
                    }
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(billPanel);
                    else
                        kiosk.scrollUp(billPanel);
                    currentPC = State.getPc();
                }
                case 7 -> {   //pay
                    payPanel = new ProgressPanel(4);
                    payPanel.loadCardsPanel(new PaymentPanel(State.getBill()));
                    if (currentPC < State.getPc())
                        kiosk.scrollDown(payPanel);
                    else
                        kiosk.scrollUp(payPanel);
                    currentPC = State.getPc();
                }
                case 8 -> {    //finish
                    finalPanel = new FinalPanel();
                    kiosk.displayComponents(true, false, false);
                    Clock.stopCheckinTimer();
                    kiosk.scrollDown(finalPanel);
                    currentPC = State.getPc();
                }
            }
        }
    }
}

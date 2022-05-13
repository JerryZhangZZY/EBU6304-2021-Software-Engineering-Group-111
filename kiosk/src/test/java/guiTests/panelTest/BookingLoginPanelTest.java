package panelTest;

import frame.MainFrame;
import panel.*;
import panel.BookingLoginPanel;
import main.*;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * @version 1.0
 * @date 3/19
 * @author zaitian
 * test for panel where users enter id
 */
public class BookingLoginPanelTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        MainFrame testMainFrame = new MainFrame();

        BookingLoginPanel testBookingLoginPanel = new BookingLoginPanel();
        testMainFrame.loadPanel(testBookingLoginPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
//        String got = testEnterIDPanel.getPassedData();

        int pcLast = State.getPc();
        while (true){
            while (pcLast == State.getPc())
            {
                sleep(1000);
                System.out.println("in main B4 "+State.getPc());
            }
            System.out.println("After2 "+State.getPc());
            Component lastPanel = testMainFrame.getLoadedPanel();
//            testMainFrame.unloadPanel(testMainFrame.getLoadedPanel());
            testMainFrame.unloadPanel(lastPanel);
            testMainFrame.loadPanel(new ProgressPanel(0));
            testMainFrame.repaint();
            pcLast = State.getPc();
            sleep(2000);
            testMainFrame.unloadPanel(testMainFrame.getLoadedPanel());
            testMainFrame.loadPanel((JPanel) lastPanel);

        }
    }

}

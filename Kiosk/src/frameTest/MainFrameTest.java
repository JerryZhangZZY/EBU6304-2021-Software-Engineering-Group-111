package frameTest;

import javax.swing.*;

import card.FlightInfoCard;
import frame.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Thread.sleep;
/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * test for initial version main frame, with a panel embedded
 */

public class MainFrameTest {
    public static void main(String[] args) {
        JPanel flightInfoCard = new FlightInfoCard("CA0001",
                "2022-03-19",
                "08:30",
                "12:00",
                "Beijing",
                "Shanghai");
        MainFrame testMainFrame = new MainFrame(1);
        testMainFrame.loadPanel(flightInfoCard);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}
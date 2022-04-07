package panel;

import backupDbOperation.BackupDbOperator;
import dbWriter.StatusWriter;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test of flight selection.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/7
 */

class FlightSelectionPanelTest {

    @BeforeEach
    void setPc() {
        State.setPc(3);
    }

    @AfterEach
    void recoverDb() {
        BackupDbOperator.push();
    }

    @Test
    void testNoBookingNum() throws InterruptedException {
        State.setBookingNum("bn0002");
        StatusWriter.setTrue(2);

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        FlightSelectionPanel.automaticallyExit();

        assertEquals(0, State.getPc());
    }

    @Test
    void testOneBookingNum() throws AWTException, InterruptedException {
        State.setBookingNum("bn0001");

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        FlightSelectionPanel.automaticallyExit();

        assertEquals(3, State.getPc());

        MainFrame mainFrame = new MainFrame();
        ProgressPanel progressPanel = new ProgressPanel(1);
        progressPanel.loadCardsPanel(flightSelectionPanel);
        mainFrame.loadPanel(progressPanel);
        mainFrame.setVisible(true);
        Point point = flightSelectionPanel.getComponent(0).getLocationOnScreen();
        Robot robot = new Robot();
        robot.delay(1000);
        robot.mouseMove((int) (point.getX() + 1), (int) (point.getY() + 1));
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        mainFrame.setVisible(false);
        robot.delay(10);

        assertEquals(4, State.getPc());
        assertEquals("CA0001", State.getIdFlight());
    }
}

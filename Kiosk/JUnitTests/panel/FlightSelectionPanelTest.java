package panel;

import backupDbOperation.BackupDbOperator;
import card.FlightInfoCard;
import common.MainFrameBarsTest;
import dbWriter.StatusWriter;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test of flight selection.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 * @author zaitian
 *
 * @version 3.1
 * Integrate doPress() and doRelease() as doClick().
 * @date 2022/4/25
 *
 * @version 3.0
 * implement MainFrameBarsTest interface
 * @date 2022/4/23
 *
 * @version 2.3
 * Upgrade old tests and add a new test.
 * @date 2022/4/10
 *
 * @version 2.2
 * Replace automaticallyExit() with a simpler Thread.
 * @date 2022/4/8
 *
 * @version 2.1
 * Use new doPress() and doRelease() methods to simulate clicks
 * and add interrupt() to skip timer.
 * @date 2022/4/8
 *
 * @version 2.0
 * @date 2022/4/7
 */

public class FlightSelectionPanelTest implements MainFrameBarsTest {
    MainFrame mainFrame;
    int currentPc;

    @BeforeEach
    public void reset() {
        currentPc = 3;
        State.setPc(currentPc);
        mainFrame = new MainFrame();
    }

    @BeforeEach
    void backupDb() {
        BackupDbOperator.pull();
    }
    @AfterEach
    void recoverDb() {
        BackupDbOperator.push();
    }

    @Test
    public void testNoMore() {
        List<String> list = new ArrayList<>();
        list.add("bn0002");
        State.setBookingNumList(list);
        StatusWriter.setTrue(2);

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(flightSelectionPanel);
        try {
            Thread.sleep(4000);
        }catch (Exception e){}

        assertEquals(0, State.getPc());
    }

    @Test
    public void testOneBookingNum() {
        List<String> list = new ArrayList<>();
        list.add("bn0001");
        State.setBookingNumList(list);

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(flightSelectionPanel);

        State.setPc(3);

        FlightInfoCard flightInfoCard = (FlightInfoCard)flightSelectionPanel.getComponent(1);
        flightInfoCard.doClick();

        assertEquals(4, State.getPc());
        assertEquals("MU1314", State.getIdFlight());
    }

    @Test
    public void testTwoBookingNum() {
        List<String> list = new ArrayList<>();
        list.add("bn0001");
        list.add("bn0002");
        State.setBookingNumList(list);

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(flightSelectionPanel);

        State.setPc(3);

        FlightInfoCard flightInfoCard = (FlightInfoCard)flightSelectionPanel.getComponent(2);
        flightInfoCard.doClick();

        assertEquals(4, State.getPc());
        assertEquals("MU1314", State.getIdFlight());
    }
    @Test
    public void testExit(){
        doExit(mainFrame);
    }
}

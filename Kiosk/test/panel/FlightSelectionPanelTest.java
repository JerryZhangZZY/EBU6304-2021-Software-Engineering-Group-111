package panel;

import backupDbOperation.BackupDbOperator;
import card.FlightInfoCard;
import dbWriter.StatusWriter;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test of flight selection.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
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
    void testNoBookingNum() {
        State.setBookingNum("bn0002");
        StatusWriter.setTrue(2);

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();
        flightSelectionPanel.getThread().interrupt();
        try {
            flightSelectionPanel.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(0, State.getPc());
    }

    @Test
    void testOneBookingNum() {
        State.setBookingNum("bn0001");

        FlightSelectionPanel flightSelectionPanel = new FlightSelectionPanel();

        assertEquals(3, State.getPc());

        FlightInfoCard flightInfoCard = (FlightInfoCard)flightSelectionPanel.getComponent(0);
        flightInfoCard.doPress();
        flightInfoCard.doRelease();

        assertEquals(4, State.getPc());
        assertEquals("CA0001", State.getIdFlight());
    }
}

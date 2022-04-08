package panel;

import backupDbOperation.BackupDbOperator;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * This is a unit test of final panel
 *
 * @author Wang Chenyu
 *
 * @version 2.0
 * @date 2022/4/7
 */
public class FinalPanelTest {
    @BeforeEach
    void pull() {
        BackupDbOperator.pull();
    }

    @Test
    void exitTest() {
        FinalPanel finalPanel = new FinalPanel();
        JButton exit = finalPanel.getExit_begin();
        exit.doClick();
        assertEquals(3, State.getPc());
        System.out.println("right");
    }

    @Test
    void systemExitTest() {
        FinalPanel finalPanel = new FinalPanel();
        JButton exit = finalPanel.getExit_system();
        exit.doClick();
        assertEquals(0, State.getPc());
        System.out.println("right");
    }

    @AfterEach
    void reset() {
        BackupDbOperator.push();
    }
}

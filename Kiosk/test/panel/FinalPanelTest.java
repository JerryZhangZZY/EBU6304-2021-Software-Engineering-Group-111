package panel;
import backupDbOperation.BackupDbOperator;
import dbReader.PassengerFlightReader;
import dbWriter.StatusWriter;
import main.State;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FinalPanelTest {
    @BeforeEach
    void pull(){
        BackupDbOperator.pull();
    }
    @Test
    void exitTest(){
        FinalPanel fianlPanel= new FinalPanel();
        JButton exit = fianlPanel.getExit_begin();
        int tempc;
        exit.doClick();
        tempc=State.getPc();
        assertEquals(3,tempc);
        System.out.println("right");
    }
    @Test
    void systemExitTest(){
        FinalPanel fianlPanel= new FinalPanel();
        JButton exit = fianlPanel.getExit_system();
        int tempc;
        exit.doClick();
        tempc=State.getPc();
        assertEquals(0,tempc);
        System.out.println("right");
    }
    @AfterEach
    void reset(){
        BackupDbOperator.push();
    }
}

package panel;

import backupDbOperation.BackupDbOperator;
import common.MainFrameBarsTest;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test SeatSelectionPanel
 *
 * @author Liang Zhehao
 * @author zaitian
 *
 * @version 3.0
 * implement MainFrameBarsTest interface
 * @date 4/23
 *
 * @version 2.0
 * @date 2022/4/6
 */
public class SeatSelectionPanelTest implements MainFrameBarsTest {
    MainFrame mainFrame;
    int currentPc;
    @BeforeEach
    public void reset() {
//        State.setPrefSeatName(new String[]{"Normal", "Pro", "Max", "Sierra blue"});
        State.setPrefSeatPrice(new int[]{0, 5, 20, 10000});
        State.setIdFlight("LH3077");
        State.resetSmallBillCard();
        mainFrame = new MainFrame();
        currentPc = 4;
        State.setPc(currentPc);
    }

    @BeforeEach
    public void backupDb() {
        BackupDbOperator.pull();
    }

    @AfterEach
    private void recoverDb() {
        BackupDbOperator.push();
    }

    @RepeatedTest(5)
    public void testSeat() {
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(seatSelectionPanel);
        JButton okBtn = seatSelectionPanel.getBtnOK();
        ArrayList<JButton>[] seat = seatSelectionPanel.getSeatButton();
        JScrollBar scrollBar = seatSelectionPanel.getScrollBar();

        int expectedSeatRow = -1;
        int expectedSeatColumn = -1;
        boolean chosen = false;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int r1 = random.nextInt(seatSelectionPanel.getTotalRow() - 12) + 1;
            scrollBar.setValue(r1);
            for (int j = 0; j < random.nextInt(10); j++) {
                int r2 = random.nextInt(6);
                int r3 = random.nextInt(3);
                seat[r3].get(r2).doClick();
                if (seatSelectionPanel.getAvail_seat()[r3][r2] == 2) {
                    expectedSeatRow = r1 + r3;
                    expectedSeatColumn = r2 + 1;
                    chosen = true;
                } else if (seatSelectionPanel.getAvail_seat()[r3][r2] == 0) {
                    expectedSeatRow = -1;
                    expectedSeatColumn = -1;
                    chosen = false;
                }
            }
            State.setPc(4);
            okBtn.doClick();
            if (chosen) {
                assertEquals(expectedSeatRow, State.getSeatRow());
                assertEquals(expectedSeatColumn, State.getSeatColumn());
                assertEquals(State.getSmallBillCard().getPrice(), State.getBill());
                assertEquals(5, State.getPc());
                assertEquals(seatSelectionPanel.getWarn().getBorder(), null);
            } else {
                assertEquals(4, State.getPc());
                assertEquals(seatSelectionPanel.getWarn().getBorder(), seatSelectionPanel.getTipBorder());
            }
        }
    }
    @Test
    public void testExit(){
        doExit(mainFrame);
    }
    @Test
    public void testBack(){
        doBack(mainFrame,currentPc);
    }
}

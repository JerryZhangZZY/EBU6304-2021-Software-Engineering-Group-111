package panel;

import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test SeatSelectionPanel
 *
 * @author Liang Zhehao
 *
 * @version 2.0
 * @date 2022/4/6
 */
class SeatSelectionPanelTest {

    @BeforeEach
    void reset() {
        State.setPrefSeatName(new String[]{"Normal", "Pro", "Max", "Sierra blue"});
        State.setPrefSeatPrice(new int[]{0, 5, 20, 10000});
        State.setIdFlight("AC0001");
        State.resetSmallBillCard();
    }

    @RepeatedTest(5)
    void testNormalSeat() {

        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();

        JButton okBtn = seatSelectionPanel.getBtnOK();
        ArrayList<JButton>[] seat = seatSelectionPanel.getSeatButton();
        JScrollBar scrollBar = seatSelectionPanel.getScrollBar();

        int expectedSeatRow = -1;
        int expectedSeatColumn = -1;
        int tempPc;
        boolean chosen = false;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int r1 = random.nextInt(seatSelectionPanel.getTotalRow() - 6) + 4;
            scrollBar.setValue(r1);
            for (int j = 0; j < random.nextInt(10); j++) {
                int r2 = random.nextInt(6);
                int r3 = random.nextInt(3);
                tempPc = State.getPc();
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

                okBtn.doClick();
                if (chosen) {
                    assertEquals(expectedSeatRow, State.getSeatRow());
                    assertEquals(expectedSeatColumn, State.getSeatColumn());
                    assertEquals(State.getPrefSeatPrice()[0], State.smallBillCard.getPrice());
                    assertEquals(State.smallBillCard.getPrice(), State.getBill());
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals(seatSelectionPanel.getWarn().getBorder(), null);
                } else {
                    assertEquals(tempPc, State.getPc());
                    assertEquals(seatSelectionPanel.getWarn().getBorder(), seatSelectionPanel.getTipBorder());
                }

            }
        }

    }

    @RepeatedTest(5)
    void testPrefSeat() {

        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();

        JRadioButton[] prefSeat = new JRadioButton[2];
        prefSeat[0] = seatSelectionPanel.getRdbtnNormalSeat();
        prefSeat[1] = seatSelectionPanel.getRdbtnPrefSeat();
        JButton okBtn = seatSelectionPanel.getBtnOK();
        ArrayList<JButton>[] seat = seatSelectionPanel.getSeatButton();

        int expectedSeatPre = 0;
        boolean chosen = false;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int r1 = random.nextInt(1);
            prefSeat[r1].doClick();

            for (int j = 0; j < random.nextInt(10); j++) {
                int r2 = random.nextInt(6);
                int r3 = random.nextInt(3);
                seat[r3].get(r2).doClick();
                if (seatSelectionPanel.getAvail_seat()[r3][r2] == 2) {
                    chosen = true;
                    expectedSeatPre = r1;
                    if (r1 == 1)
                        expectedSeatPre += r3;
                    assertEquals(State.getPrefSeatPrice()[expectedSeatPre], State.smallBillCard.getPrice());
                } else if (seatSelectionPanel.getAvail_seat()[r3][r2] == 0) {
                    chosen = false;
                    assertEquals(0, State.smallBillCard.getPrice());
                } else {
                    if (chosen)
                        assertEquals(State.getPrefSeatPrice()[expectedSeatPre], State.smallBillCard.getPrice());
                    else
                        assertEquals(0, State.smallBillCard.getPrice());
                }

                okBtn.doClick();
                if (chosen) {
                    assertEquals(expectedSeatPre, State.getSeatPre());
                }
            }
        }
    }
}

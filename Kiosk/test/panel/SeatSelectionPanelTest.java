package panel;

import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class test SeatSelectionPanel
 *
 * @author Liang Zhehao
 *
 * @version 2.0
 * @date 2022/4/6
 */
class SeatSelectionPanelTest {

    @Test
    void testSeatSelectionPanel() {

        State.setPrefSeatName(new String[]{"Normal", "Pro", "Max", "Sierra blue"});
        State.setPrefSeatPrice(new int[]{0, 5, 20, 10000});
        State.setIdFlight("AC0001");
        State.resetSmallBillCard();

        MainFrame mainFrame = new MainFrame();
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();
        mainFrame.loadPanel(seatSelectionPanel);

        JRadioButton prefSeat1 = seatSelectionPanel.getRdbtnSeat1();
        JRadioButton prefSeat2 = seatSelectionPanel.getRdbtnSeat2();
        JRadioButton prefSeat3 = seatSelectionPanel.getRdbtnSeat3();
        JRadioButton prefSeat4 = seatSelectionPanel.getRdbtnSeat4();
        JButton okBtn = seatSelectionPanel.getBtnOK();
        JButton seat[] = seatSelectionPanel.getSeatButton();
        JScrollBar scrollBar = seatSelectionPanel.getScrollBar();

        int expectedSeatPre = 0;
        int expectedSeatRow;
        int expectedSeatColumn;

        for (int i = 4; i <= seatSelectionPanel.getTotalrow(); i++) {
            scrollBar.setValue(i);
            for (int j = 0; j < 6; j++) {
                seat[j].doClick();
                okBtn.doClick();
                expectedSeatRow = i;
                expectedSeatColumn = j + 1;
                if (seatSelectionPanel.getAvail_seat()[j] == 2) {
                    assertEquals(expectedSeatPre, State.getSeatPre());
                    assertEquals(expectedSeatRow, State.getSeatRow());
                    assertEquals(expectedSeatColumn, State.getSeatColumn());
                    assertEquals(State.getPrefSeatPrice()[0], State.smallBillCard.getPrice());
                }
            }
        }
        prefSeat2.doClick();
        expectedSeatPre = 1;
        for (int i = 0; i < 6; i++) {
            seat[i].doClick();
            okBtn.doClick();
            expectedSeatRow = 1;
            expectedSeatColumn = i + 1;
            if (seatSelectionPanel.getAvail_seat()[i] == 2) {
                assertEquals(expectedSeatPre, State.getSeatPre());
                assertEquals(expectedSeatRow, State.getSeatRow());
                assertEquals(expectedSeatColumn, State.getSeatColumn());
                assertEquals(State.getPrefSeatPrice()[1], State.smallBillCard.getPrice());
            }
        }
    }
}

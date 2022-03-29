package cardTest;

import card.BillConfirmCard;
import frame.MainFrame;
import main.State;
import panel.ProgressPanel;

/**
 * This class can test confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/29
 * @version 1.1
 * Make the test work correctly.
 */
public class BillConfirmCardTest {

    public static void main(String[] args) {
        State.setBookingNum("bn0001");
        State.setIdFlight("CA0001");
        State.setPassengerName("Jack");
        State.setPassengerFlight_index(0);
        State.setSeatRow(1);
        State.setSeatColumn(1);
        State.setMeal('a');
        State.setSelectedPrefFood(new boolean[]{true, true, true});
        MainFrame frame = new MainFrame();
        ProgressPanel progressPanel = new ProgressPanel(4);
        progressPanel.loadCardsPanel(new BillConfirmCard());
        frame.loadPanel(progressPanel);
        frame.displayComponents(true, true, true);
        frame.setVisible(true);
    }
}

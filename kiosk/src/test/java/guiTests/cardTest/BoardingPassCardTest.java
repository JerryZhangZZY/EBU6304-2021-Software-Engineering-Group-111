package cardTest;

import card.BoardingPassCard;
import frame.MainFrame;
import main.State;

/**
 * This is the test class of BoardingPassCard.
 *
 * @author Zhang Zeyu
 *
 * @version 5.0
 * Adapt to the latest software version.
 * @date 2022/5/20
 *
 * @version 1.0
 * @date 2022/3/24
 */

public class BoardingPassCardTest {
    public static void main(String[] args) {
        State.setSeatColumn(3);
        State.setSeatRow(8);
        State.setPassengerName("Jack");
        MainFrame frame = new MainFrame();
        BoardingPassCard boardingPassCard = new BoardingPassCard("BA0002");
        boardingPassCard.setLocation(100, 100);
        frame.loadPanel(boardingPassCard);
        frame.setVisible(true);
    }
}

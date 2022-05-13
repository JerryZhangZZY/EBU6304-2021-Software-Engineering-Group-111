package cardTest;

import card.PlaneInfoCard;
import frame.MainFrame;
import main.State;

/**
 * This class test PlaneInfoCard
 *
 * @author Liang Zhehao
 *
 * @version 4.0
 * @date 2022/5/7
 */

public class PlaneInfoCardTest {

    public static void main(String[] args) {

        State.setIdFlight("AC0001");
        MainFrame testMainFrame = new MainFrame();
        PlaneInfoCard planeInfoCard = new PlaneInfoCard();
        planeInfoCard.setLocation(0, 0);
        testMainFrame.loadPanel(planeInfoCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

package cardTest;

import card.PlaneInfoCard;
import frame.MainFrame;

/**
 * This class test PlaneInfoCard.
 *
 * @author Liang Zhehao
 *
 * @version 2.0
 * @date 2022/4/11
 */
public class PlaneInfoCardTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new PlaneInfoCard("Boeing 737-800", 144, "UNITED AIRLINES"));
        frame.setVisible(true);
    }
}

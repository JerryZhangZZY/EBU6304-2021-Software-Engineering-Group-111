package panelTest;

import frame.MainFrame;
import panel.SeatSelectionPanel;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/24
 * @version 1.0
 */
public class SeatSelectionPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new SeatSelectionPanel());
        frame.setVisible(true);
    }
}

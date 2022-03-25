package panelTest;

import frame.MainFrame;
import panel.SeatSelectionPanel;

public class SeatSelectionPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new SeatSelectionPanel());
        frame.setVisible(true);
    }
}

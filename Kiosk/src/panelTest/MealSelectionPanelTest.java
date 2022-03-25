package panelTest;

import frame.MainFrame;
import panel.MealSelectionPanel;

public class MealSelectionPanelTest {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.loadPanel(new MealSelectionPanel());
        frame.setVisible(true);
    }
}

package panelTest;

import frame.MainFrame;
import main.State;
import panel.BillConfirmationPanel;
import panel.ProgressPanel;

public class BillConfirmationPanelTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        State.setIdFlight("BA0001");
        State.setSelectedPrefFood(new boolean[]{true, true, true});
        State.setPassengerName("Mike");
        State.setSeatRow(6);
        State.setSeatColumn(3);
        State.setColumnNum('C');
        State.setMeal('a');

        ProgressPanel progressPanel = new ProgressPanel(4);
        progressPanel.loadCardsPanel(new BillConfirmationPanel());
        testMainFrame.loadPanel(progressPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
    }
}

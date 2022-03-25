package panelTest;

import frame.*;
import panel.*;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 */
public class ProgressPanelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame testMainFrame = new MainFrame();
        ProgressPanel testProgressPanel = new ProgressPanel(0);
        testProgressPanel.loadCardsPanel(new FlightSelectionPanel());
        testMainFrame.loadPanel(testProgressPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
	}

}

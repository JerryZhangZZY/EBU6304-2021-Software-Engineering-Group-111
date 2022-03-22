package panelTest;

import javax.swing.JPanel;

import frame.*;
import panel.*;

public class ProgressPanelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame testMainFrame = new MainFrame(1);
        JPanel testProgressPanel = new ProgressPanel();
        testMainFrame.loadPanel(testProgressPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
	}

}

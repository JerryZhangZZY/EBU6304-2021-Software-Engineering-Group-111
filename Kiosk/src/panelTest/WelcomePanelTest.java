package panelTest;

import frame.MainFrame;
import panel.WelcomePanel;

public class WelcomePanelTest {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(2);
        WelcomePanel welcomePanel = new WelcomePanel();
        mainFrame.hideBars(true);
        mainFrame.loadPanel(welcomePanel);
        mainFrame.setVisible(true);
    }
}

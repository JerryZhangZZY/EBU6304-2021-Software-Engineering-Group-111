package panel;

import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for welcome panel
 * @author zaitian
 * @author Liang Zhehao
 *
 * @version 2.1
 * simplified
 * @date 4/22
 *
 * @version 2.0
 * @date 4/14
 *
 * @version 1.0
 * @date 4/8
 */
public class WelcomePanelTest {
    MainFrame mainFrame = new MainFrame();
    WelcomePanel welcomePanel = new WelcomePanel();
    @RepeatedTest(5)
    public void test() {
            welcomePanel.getBtnMask().doClick();
            assertEquals(1, State.getPc());
    }


}
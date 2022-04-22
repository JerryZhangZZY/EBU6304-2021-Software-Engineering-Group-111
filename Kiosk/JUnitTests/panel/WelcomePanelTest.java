package panel;

import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for welcome panel
 * @author zaitian
 * @author Liang Zhehao
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
    @BeforeEach
    public void reset(){
        mainFrame.setVisible(true);
        mainFrame.hideBars(true);
        mainFrame.lockScreen();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @RepeatedTest(5)
    public void test() {
        for (int j = 0; j < 10; j++) {
            State.setPc(0);
            try {
                Robot robot = new Robot();
                while(!mainFrame.isVisible())
                    ;
                Point p = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove(new Random().nextInt(1920),
                        new Random().nextInt(1080));
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseMove(p.x, p.y);
                welcomePanel.getMask().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        mainFrame.setVisible(false);
                    }
                });
                for (int i = 0; i < 1000; i++) {
                    if (State.getPc() != 1)
                        robot.delay(1);
                }
                assertEquals(1, State.getPc());
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

    }


}
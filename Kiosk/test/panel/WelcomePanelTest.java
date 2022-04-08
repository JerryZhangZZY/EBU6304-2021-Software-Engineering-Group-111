package panel;

import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.w3c.dom.ranges.Range;
import panel.WelcomePanel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for welcome panel
 * @author zaitian
 * @version 1.0
 * @date 4/8
 */
class WelcomePanelTest {
    MainFrame mainFrame = new MainFrame();
    WelcomePanel welcomePanel = new WelcomePanel();
    @BeforeEach
    void reset(){
        State.setPc(0);
    }
    @RepeatedTest(10)
    void test(){
        mainFrame.hideBars(true);
        mainFrame.loadPanel(welcomePanel);
        mainFrame.setVisible(true);
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
package common;

import frame.MainFrame;
import main.State;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * enabling testing exit and back button in test classes
 * @author zaitian
 *
 * @version 1.0
 * @date 4/23
 */
public interface MainFrameBarsTest {
    /**
     * clicking exit button
     * @param mainFrame the main frame used in a certain test class
     */
    default void doExit(MainFrame mainFrame){
        JButton exitBtn = mainFrame.getExitButton();
        exitBtn.doClick();
        assertEquals(0, State.getPc());
    }
    /**
     * clicking back button
     * @param mainFrame the main frame used in a certain test class
     * @param currentPC pc at that time when method is called
     */
    default void doBack(MainFrame mainFrame, int currentPC){
        JButton backBtn = mainFrame.getBackButton();
        backBtn.doClick();
        assertEquals (currentPC-1, State.getPc());
    }
}

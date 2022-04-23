package common;

import frame.MainFrame;
import main.State;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface MainFrameBarsTest {
    default void doExit(MainFrame mainFrame){
        JButton exitBtn = mainFrame.getExitButton();
        exitBtn.doClick();
        assertEquals(0, State.getPc());
    }
    default void doBack(MainFrame mainFrame, int currentPC){
        JButton backBtn = mainFrame.getBackButton();
        backBtn.doClick();
        assertEquals (currentPC-1, State.getPc());
    }
}

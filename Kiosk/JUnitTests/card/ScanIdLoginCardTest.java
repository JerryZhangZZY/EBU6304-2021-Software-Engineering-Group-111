package card;

import frame.MainFrame;
import idCardReader.IdCardReader;
import main.State;
import org.junit.jupiter.api.Test;
import panel.IdLoginPanel;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for id card scanning panel
 * @author zaitian
 * @version 1.0
 */
public class ScanIdLoginCardTest {
    MainFrame mainFrame = new MainFrame();
    IdLoginPanel panel = new IdLoginPanel();
    TypeIdLoginCard ref = panel.getTypeIdLoginCard();
    ScanIdLoginCard card = panel.getScanIdLoginCard();
    JButton btn = card.getButtonScan();
    JLabel lbl = card.getLblError();
    String id = IdCardReader.readId();
    String name = IdCardReader.readName();

    public ScanIdLoginCardTest() throws IOException {
    }

    /**
     * for a pair of id and name, if type passes, scan should also pass
     * vice versa
     */
    @Test
    public void compareTypeAndScan() {
        mainFrame.loadPanel(panel);
        ref.getTfId().setText(id);
        ref.getTfSurname().setText(name);
        ref.getButtonOk().doClick();
        if (State.getPc() == 3){
            State.setPc(2);
            btn.doClick();
            assertEquals(3, State.getPc());
            assertFalse(lbl.isVisible());
        }
        else{
            assertTrue(lbl.isVisible());
            assertEquals(2, State.getPc());
        }
    }
}
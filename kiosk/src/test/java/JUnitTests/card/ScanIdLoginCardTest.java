package card;

import common.MainFrameBarsTest;
import frame.MainFrame;
import idCardReader.IdCardReader;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panel.IdLoginPanel;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for id card scanning panel
 * @author zaitian
 *
 * @version 2.0
 * implement MainFrameBarsTest interface
 * @date 4/23
 *
 * @version 1.0
 * @date 4.8
 */
public class ScanIdLoginCardTest implements MainFrameBarsTest {
    MainFrame mainFrame = new MainFrame();
    IdLoginPanel panel = new IdLoginPanel();
    TypeIdLoginCard ref = panel.getTypeIdLoginCard();
    ScanIdLoginCard card = panel.getScanIdLoginCard();
    JButton btn = card.getBtnScan();
    JLabel lbl = card.getLblError();
    String id = IdCardReader.readId();
    String name = IdCardReader.readName();
    int currentPc;

    public ScanIdLoginCardTest() throws IOException {
    }
    @BeforeEach
    public void reset(){
        currentPc = 2;
        State.setPc(currentPc);
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
        ref.getBtnOk().doClick();
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
    @Test
    public void testExit(){
        doExit(mainFrame);
    }
    @Test
    public void testBack(){
        doBack(mainFrame,currentPc);
    }
}
package card;

import common.MainFrameBarsTest;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for checking-in by typing ID and name
 * @author zaitian
 *
 * @version 2.0
 * implement MainFrameBarsTest interface
 * @date 4/23
 *
 * @version 1.1
 * adapt to new login card
 * @date 4/11
 *
 * @version 1.0
 * @date 4/7
 */
public class TypeIdLoginCardTest implements MainFrameBarsTest {
    String[] candidateName = {"Jack", "Mike", "Amy", "Karl", "Amy", "nay"};
    String[] candidateID = {"123001", "123002", "123003","123003", "123006", "nil"};
    String[] expectedBookingNum = {"bn0001", "bn0003", null, null, null, null};

    MainFrame mainFrame = new MainFrame();
    TypeIdLoginCard card = new TypeIdLoginCard();
    JTextField idTF = card.getTfId();
    JTextField nameTF = card.getTfSurname();
    JLabel errLbl = card.getLblError();
    JButton okBtn = card.getButtonOk();
    int currentPc;

    @BeforeEach
    public void reset(){
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(card);
        card.reset();
        currentPc = 2;
        State.setPc(currentPc);
    }

    @RepeatedTest(10)
    public void testEnteringID(){
        int candidate = new Random().nextInt(candidateID.length);
        nameTF.setText(candidateName[candidate]);
        idTF.setText(candidateID[candidate]);
        okBtn.doClick();
        List<String> bookingNumList = card.getBookingNumByTyping(nameTF.getText(), idTF.getText());
        if (bookingNumList.size() == 3) {
            assertTrue(errLbl.isVisible());
        }
        else {
            if (State.getBookingNumList().size() == 0)
                assertEquals(expectedBookingNum[candidate], null);
            else {
                assertEquals(candidateName[candidate], State.getPassengerName());
                assertEquals(expectedBookingNum[candidate], State.getBookingNumList().get(0));
                assertEquals(3, State.getPc());
            }
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
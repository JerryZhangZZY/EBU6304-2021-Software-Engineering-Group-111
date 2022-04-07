package card;

import frame.MainFrame;
import main.State;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import javax.swing.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for checking-in by typing ID and name
 * @author zaitian
 *
 * @version 1.0
 * @date 4/7
 */
class TypeIdLoginCardTest {
    String[] candidateName = {"Jack", "Mike", "Amy", "Karl", "Amy", "nay"};
    String[] candidateID = {"123001", "123002", "123003","123003", "123006", "nil"};
    String[] expectedBookingNum = {"bn0001", "bn0003", null, null, null, null};

    MainFrame mainFrame = new MainFrame();
    TypeIdLoginCard card = new TypeIdLoginCard();
    JTextField idTF = card.getTfId();
    JTextField nameTF = card.getTfSurname();
    JLabel errLbl = card.getLblError();
    JButton okBtn = card.getButtonOk();

    @BeforeEach
    void reset(){
        mainFrame.unloadPanel(mainFrame.getLoadedPanel());
        mainFrame.loadPanel(card);
        card.reset();
    }

    @RepeatedTest(10)
    void testEnteringID(){
        int candidate = new Random().nextInt(candidateID.length);
        nameTF.setText(candidateName[candidate]);
        idTF.setText(candidateID[candidate]);
        okBtn.doClick();

        List<String> bookingNumList = card.getBookingNumByTyping(nameTF.getText(), idTF.getText());
        if (bookingNumList.size() == 3) {
            assertTrue(errLbl.isVisible());
        }
        else {
            assertEquals(candidateName[candidate], State.getPassengerName());
            assertEquals(expectedBookingNum[candidate], State.getBookingNum());
            assertEquals(3, State.getPc());
        }
    }
}
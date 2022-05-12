package panel;
import common.MainFrameBarsTest;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * test class for id card scanning panel
 * @author Chenyu
 *
 * @version 3.0
 * implement MainFrameBarsTest interface
 * @date 4/25
 */
public class PaymentPanelTest implements MainFrameBarsTest {
    private int price;
    private MainFrame mainFrame= new MainFrame();
    int currentPc;
    @BeforeEach
    public void reset(){
        currentPc = 7;
        State.setPc(7);
    }
    @Test
    public void testPaymentPanel(){
        price = State.getBill();
        PaymentPanel test = new PaymentPanel(price);
        JButton pay = test.getButtonPay();
        JTextField id  =test.getTfCreditId();
        id.setText("12345678");
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc+1, State.getPc());
    }
    @Test
    public void testChecksum(){
        PaymentPanel test = new PaymentPanel(price);
        JButton pay = test.getButtonPay();
        JTextField id  =test.getTfCreditId();
        JLabel error  =test.getErrorWarning();
        id.setText("123456789");
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc,State.getPc());
        assertTrue(error.isVisible());
        id.setText("123456llplp9");
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc,State.getPc());
        assertTrue(error.isVisible());
        id.setText("");
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc,State.getPc());
        assertTrue(error.isVisible());
        id.setText("12345678");
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc+1,State.getPc());
        assertTrue(error.isVisible());
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

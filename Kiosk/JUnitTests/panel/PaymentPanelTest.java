package panel;
import common.MainFrameBarsTest;
import frame.MainFrame;
import main.Config;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import panel.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        currentPc  =  State.getPc();
        pay.doClick();
        assertEquals(currentPc+1, State.getPc());
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

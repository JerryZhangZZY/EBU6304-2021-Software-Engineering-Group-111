package panel;
import backupDbOperation.BackupDbOperator;
import common.MainFrameBarsTest;
import frame.MainFrame;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This is a unit test of Meal selection
 *
 * @author Wang Chenyu
 * @author zaitian
 * @author Liang Zhehao
 *
 * @version 3.1
 * remove pull(), fix bugs
 * @date  4/23
 *
 * @version 3.0
 * implement MainFrameBarsTest interface
 * @date 4/23
 *
 * @version 2.0
 * @date 2022/4/7
 */
public class MealSelectionPanelTest implements MainFrameBarsTest {
    MainFrame mainFrame = new MainFrame();
    int currentPc;

    @BeforeEach
    void reset(){
        State.setIdFlight("AC0001");
        State.setMeal('d');
        State.resetSmallBillCard();
        currentPc = 5;
        State.setPc(currentPc);
    }

    @AfterEach
    void recoverDb() {
        BackupDbOperator.push();
    }

    @Test
    void testNormalFood(){
        MealSelectionPanel selectionPanel = new  MealSelectionPanel();
        JButton[] food = new JButton[3];
        food[0]= selectionPanel.getNormal_food();
        food[1] = selectionPanel.getVegetarian_food();
        food[2] = selectionPanel.getHalal_food();
        JButton confirm = selectionPanel.getConfirm();
        int r1,exp=-1;
        Random random = new Random();
        char choose ='d';
        int tempPc;
        for(int i = 0; i < 20; i++){
            r1=random.nextInt(3);
            food[r1].doClick();
            tempPc = State.getPc();
            confirm.doClick();
            choose=State.getMeal();
            if(r1!=exp)
            {
                if (r1 == 0) {
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals('a', choose);
                } else if (r1 == 1) {
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals('b', choose);
                } else if (r1 == 2) {
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals('c', choose);
                }
            }
            else
            {
                assertEquals(tempPc, State.getPc());
                assertEquals('d',choose);
            }
            exp = (int)State.getMeal()-97;
        }
    }
    @Test
    void testSpecialFood(){
        MealSelectionPanel selectionPanel = new  MealSelectionPanel();
        JRadioButton[] meal = selectionPanel.getrdbtnMeal();
        boolean[] pre = new boolean[3];
        Random random = new Random();
        int[] bill = State.getPrefFoodPrice();
        JButton confirm = selectionPanel.getConfirm();
        int r;
        int price=0;
        int choose;
        for(int i = 0 ; i <3 ; i++){
            pre[i]=false;
        }
        for(int i = 0; i < 10 ; i++){
            r=random.nextInt(3);
            meal[r].doClick();
            State.setMeal('a');
            confirm.doClick();
            choose=State.smallBillCard.getPrice();
            if(pre[r]){
                price-=bill[r];
                pre[r]=false;
            }
            else{
                price+=bill[r];
                pre[r]=true;
            }
            assertEquals(price,choose);
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

package panel;
import main.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This is a unit test of Meal selection
 *
 * @author Wang Chenyu
 *
 * @version 2.0
 * @date 2022/4/7
 */
public class MealSelectionPanelTest {
    @BeforeEach
    void reset(){
        State.setPrefFoodName(new String[]{"Extra", "Kweichow Moutai", "Ice-cream"});
        State.setPrefFoodPrice(new int[]{11,22,36});
        State.setMeal('d');
        State.resetSmallBillCard();
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
                    System.out.println(1);
                } else if (r1 == 1) {
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals('b', choose);
                    System.out.println(2);
                } else if (r1 == 2) {
                    assertEquals(tempPc + 1, State.getPc());
                    assertEquals('c', choose);
                    System.out.println(3);
                }
            }
            else
            {
                assertEquals(tempPc, State.getPc());
                assertEquals('d',choose);
                System.out.println(4);
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
        int[] bill={11,22,36};
        JButton confirm = selectionPanel.getConfirm();
        int r;
        int price=0;
        int choose;
        for(int i = 0 ; i <3 ; i++){
            pre[i]=false;
        }
        for(int i = 0; i < 100 ; i++){
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
            System.out.println("right");
        }
    }
}

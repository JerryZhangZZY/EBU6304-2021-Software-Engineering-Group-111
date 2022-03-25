package cardTest;

import card.TypeIfIdLoginCard;
import frame.MainFrame;

/**
 * @author Li Chunlin
 * @version 1.0
 * @date 3/24
 * test for log in the surname and id
 */
public class TypeIfIdLoginCardTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        TypeIfIdLoginCard h = new TypeIfIdLoginCard();
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true,true,true);
        testMainFrame.setVisible(true);
    }
}
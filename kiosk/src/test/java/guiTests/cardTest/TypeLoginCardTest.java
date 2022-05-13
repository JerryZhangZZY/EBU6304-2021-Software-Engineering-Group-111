package cardTest;

import card.TypeIdLoginCard;
import frame.MainFrame;

/**
 * @author Li Chunlin
 * @version 1.0
 * @date 3/24
 * test for log in the surname and id
 */
public class TypeLoginCardTest {

    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        TypeIdLoginCard h = new TypeIdLoginCard();
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true,true,true);
        testMainFrame.setVisible(true);
    }
}
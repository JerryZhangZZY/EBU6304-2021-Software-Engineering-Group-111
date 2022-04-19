package cardTest;

import card.ScrollCard;

import javax.swing.*;

/**
 * This is the GUI test class for ScrollCard class.
 *
 * @author Zhang Zeyu
 *
 * @version 3.0
 * @date 2022/4/19
 */

public class ScrollCardTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(600, 800);

        ScrollCard scrollCard = new ScrollCard();
        scrollCard.setLocation(50, 50);
        jFrame.add(scrollCard);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        scrollCard.getScrollBar().setMaximum(10);
    }
}

package card;

import main.Theme;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * This card provides a scroll bar to select rows in a plane when selecting a seat.
 *
 * @author Zhang Zeyu
 *
 * @version 3.0
 * @date 2022/4/19
 */

public class ScrollCard extends JPanel {

    JScrollBar scrollBar;
    public ScrollCard() {
        setSize(330, 600);
        setLayout(null);
        setBackground(null);

        scrollBar = new JScrollBar();
        scrollBar.setBounds(141, 65, 48, 325);
        scrollBar.setUI(new PlaneScrollBarUI());
        scrollBar.setBlockIncrement(1);
        scrollBar.setVisibleAmount(3);
        add(scrollBar);
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Kiosk/icons/planeTopView.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), this);
    }

    public static class PlaneScrollBarUI extends BasicScrollBarUI {
        @Override
        public void paintTrack(final Graphics g, final JComponent c, final Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp1 = new GradientPaint(0, 0, new Color(208, 214, 216), 34, 0, new Color(252, 252, 252));
            g2.setPaint(gp1);
            g2.fillRect(0, 0, 34, trackBounds.height);
            GradientPaint gp2 = new GradientPaint(34, 0, new Color(252, 252, 252), 49, 0, new Color(230, 234, 234));
            g2.setPaint(gp2);
            g2.fillRect(34, 0, 15, trackBounds.height);
            if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT)
                this.paintDecreaseHighlight(g);
            if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT)
                this.paintIncreaseHighlight(g);
        }

        @Override
        protected void paintThumb(final Graphics g, final JComponent c, final Rectangle thumbBounds) {
            g.translate(thumbBounds.x, thumbBounds.y);
            g.drawRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 5, 5);
            Graphics2D g2 = (Graphics2D) g;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.addRenderingHints(rh);
            g2.setColor(new Color(209, 215, 216));
            g2.fillRect(0, 0, 3, thumbBounds.height);
            g2.setColor(Theme.getThemeColor());
            g2.setStroke(new BasicStroke(4.0f));
            g2.drawRoundRect(1, 1, thumbBounds.width - 3, thumbBounds.height - 3, 3, 3);
            scrollbar.repaint();
        }

        @Override
        protected JButton createIncreaseButton(final int orientation) {
            JButton button = new JButton();
            button.setBorder(null);
            return button;
        }

        @Override
        protected JButton createDecreaseButton(final int orientation) {
            JButton button = new JButton();
            button.setBorder(null);
            return button;
        }
    }
}

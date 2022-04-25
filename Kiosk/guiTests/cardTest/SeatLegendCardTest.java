package cardTest;

import card.SeatLegendCard;
import frame.MainFrame;

import javax.swing.*;

/**
 * This class test LegendCard
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 2022/4/22
 */
public class SeatLegendCardTest {
    public static void main(String[] args) {
        ImageIcon[] iconEmpty = new ImageIcon[4];
        iconEmpty[0] = new ImageIcon(new ImageIcon("Kiosk/icons/seatStandard.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[1] = new ImageIcon(new ImageIcon("Kiosk/icons/seatStandardPlus.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[2] = new ImageIcon(new ImageIcon("Kiosk/icons/seatExtraLegroom.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        iconEmpty[3] = new ImageIcon(new ImageIcon("Kiosk/icons/seatPremium.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconSelected = new ImageIcon(new ImageIcon("Kiosk/icons/seatSelected.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconNotAvailable = new ImageIcon(new ImageIcon("Kiosk/icons/seatNotAvailable.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));

        MainFrame testMainFrame = new MainFrame();
        SeatLegendCard seatLegendCard = new SeatLegendCard(iconEmpty, iconSelected, iconNotAvailable);
        seatLegendCard.setLocation(0, 0);
        testMainFrame.loadPanel(seatLegendCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}

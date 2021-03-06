package panel;

import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * panel that holds cards for customers to choose their meal, seat, etc.
 *
 * @author zaitian
 * @author Zhang Zeyu
 *
 * @version 1.2
 * Enhanced loading process
 * @date 3/26
 *
 * @version 1.1
 * Appearance improvement.
 * @date 2022/3/25
 *
 * @version 1.0
 * @date 3/22
 */

public class ProgressPanel extends JPanel {
    private final JPanel progressPanel;
    private final JLabel progressLabel;
    private final JPanel cardsPanel;
    private final int progress;
	/**
	 * Create the panel.
     * @param progress which step have customers reached
     *                 1 for flight, 2 for seat
     *                 3 for meal, 4 for confirm and pay
	 */
	public ProgressPanel(int progress) {
	    this.progress = progress;

		setBounds(new Rectangle(0, 0, 1920, 880));
		setBackground(Theme.getBackgroundColor());
        setLayout(null);
        setSize(1920, 880);
        /*
        left panel to hold progress indicator
         */
        progressPanel = new JPanel();
        progressPanel.setBounds(0, 0, 320, 880);
        progressPanel.setBackground(Theme.getCardColor());
        add(progressPanel);
        progressPanel.setLayout(null);
        /*
        progress indicator
         */
        //TODO switch case determining which progress indicator to use
        progressLabel = new JLabel();
        ImageIcon progressImage = new ImageIcon();
        switch(progress) {
            case 1: {
                progressImage = new ImageIcon("kiosk/Icons/progressIndicator1.png");
                break;
            }
            case 2: {
                progressImage = new ImageIcon("kiosk/Icons/progressIndicator2.png");
                break;
            }
            case 3: {
                progressImage = new ImageIcon("kiosk/Icons/progressIndicator3.png");
                break;
            }
            case 4: {
                progressImage = new ImageIcon("kiosk/Icons/progressIndicator4.png");
                break;
            }
            default: {
                break;
            }
        }
        progressLabel.setIcon(progressImage);
        progressLabel.setBounds(20, 20, 320, 840);
        progressPanel.add(progressLabel);
        /*
        right panel to hold option cards
         */
        cardsPanel = new JPanel();
        cardsPanel.setBounds(320, 0, 1600, 880);
        cardsPanel.setLayout(null);
        add(cardsPanel);

    }
    /**
     * loading the panel that contains one or more cards
     * which takes four fifth of the center panel on the right
     * @param cardsPanel panel with cards to be loaded
     */
	public void loadCardsPanel(JPanel cardsPanel){
	    this.cardsPanel.add(cardsPanel, 0);
        State.setIsReady(true,  progress+2);
        if (progress == 1){
            State.setIsReady(new boolean[]{true, true, true,
                    false, false, false, false, true, true});
            State.resetSmallBillCard();
        }
    }
}

package panel;

import main.State;

import javax.swing.*;
import java.awt.*;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * panel that holds cards for customers to choose their meal, seat, etc.
 *
 * @author Zhang Zeyu
 * @date 2022/3/25
 * @version 1.1
 * Appearance improvement.
 *
 * @author zaitian
 * @date 3/26
 * @version 1.2
 * Enhanced loading process
 */

public class ProgressPanel extends JPanel {
    private JPanel progressPanel;
    private JLabel progressLabel;
    private JPanel cardsPanel;
    private int progress;
	/**
	 * Create the panel.
     * @param progress which step have customers reached
     *                 1 for flight, 2 for seat
     *                 3 for meal, 4 for confirm and pay
	 */
	public ProgressPanel(int progress) {
	    this.progress = progress;

		setBounds(new Rectangle(0, 0, 1920, 880));
		setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1920, 880);
        /*
        left panel to hold progress indicator
         */
        progressPanel = new JPanel();
        progressPanel.setBounds(0, 0, 320, 880);
        progressPanel.setBackground(new Color(244, 244, 244));
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
                progressImage = new ImageIcon("Kiosk/Icons/progressIndicator1.png");
                break;
            }
            case 2: {
                progressImage = new ImageIcon("Kiosk/Icons/progressIndicator2.png");
                break;
            }
            case 3: {
                progressImage = new ImageIcon("Kiosk/Icons/progressIndicator3.png");
                break;
            }
            case 4: {
                progressImage = new ImageIcon("Kiosk/Icons/progressIndicator4.png");
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
        cardsPanel.setBackground(Color.PINK);
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
    /**
     * access the cards panel loaded
     * @return the cards panel loaded
     */
    public Component getLoadedCardsPanel(){
	    if (cardsPanel.getComponentCount()>0){
	        return cardsPanel.getComponent(0);
        }
	    else{
	        return null;
        }
    }
    /**
     * remove the cards panel loaded
     * @param cardsPanel the currently loaded cards panel
     */
    public void unloadCardsPanel(Component cardsPanel){
	    if (cardsPanel != null){
	        this.cardsPanel.remove(cardsPanel);
        }
    }
}

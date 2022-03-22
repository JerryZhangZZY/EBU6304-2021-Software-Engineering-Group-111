package panel;

import card.SeatSelectionCard;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * panel that holds cards for customers to choose their meal, seat, etc.
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
        setBackground(Color.PINK);
        setLayout(null);
        setSize(1920, 880);
        /*
        left panel to hold progress indicator
         */
        progressPanel = new JPanel();
        progressPanel.setBounds(0, 0, 320, 880);
        add(progressPanel);
        progressPanel.setLayout(null);
        /*
        progress indicator
         */
        //TODO switch case determining which progress indicator to use
        progressLabel = new JLabel("New label");
        ImageIcon progressImage = new ImageIcon("D:\\Desktop\\testProgress320800.png");
        progressLabel.setIcon(progressImage);
        progressLabel.setBounds(10, 40, 300, 800);
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
	public void loadCards(JPanel cards){
	    cardsPanel.add(cards, 0);
    }
}

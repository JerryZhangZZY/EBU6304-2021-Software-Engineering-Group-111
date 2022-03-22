package panel;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ProgressPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProgressPanel() {
		setBounds(new Rectangle(0, 0, 1920, 880));
        setBackground(Color.PINK);
        setLayout(null);
        setSize(1920, 880);
        
        JPanel progressPanel = new JPanel();
        progressPanel.setBounds(0, 0, 320, 880);
        add(progressPanel);
        progressPanel.setLayout(null);
        
        JLabel progressLabel = new JLabel("New label");
        ImageIcon progressImage = new ImageIcon("D:\\Desktop\\testProgress320800.png");
        progressLabel.setIcon(progressImage);
        progressLabel.setBounds(10, 40, 300, 800);
        progressPanel.add(progressLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(318, 0, 1600, 880);
        add(panel);
	}
}

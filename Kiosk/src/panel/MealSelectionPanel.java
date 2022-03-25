package panel;

import card.MealSelectionCard;

import javax.swing.*;
import java.awt.*;

public class MealSelectionPanel extends JPanel {

    private MealSelectionCard mealSelectionCard;

    public MealSelectionPanel() {
        setBounds(320, 0, 1600, 880);
        setBackground(new Color(244, 244, 244));
        setLayout(null);

        mealSelectionCard = new MealSelectionCard();
        add(mealSelectionCard);
    }

}

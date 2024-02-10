package display.mainPanels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Exercise;

import java.awt.FlowLayout;

public class ExerciseIconsPanel extends JPanel {
    
    private static JLabel powderIcon = new JLabel(new ImageIcon("Resources/exerciseIcons/powderIcon.java"));
    
    public ExerciseIconsPanel() {
        setLayout(new FlowLayout());
        setBounds(480, 640, 480, 60);
    }

    public void update() {
        if (Exercise.getProteinPowder()) {
            add(powderIcon);
        }
        //TODO - if: check for weights, resistance stuff, etc.
    }

}

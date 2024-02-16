package display.mainPanels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Exercise;

import java.awt.Color;
import java.awt.FlowLayout;

public class ExerciseIconsPanel extends JPanel {
    
    private static JLabel powderIcon = new JLabel(new ImageIcon("Resources/exerciseIcons/ProteinIcon.java"));
    
    public ExerciseIconsPanel() {
        setLayout(new FlowLayout());
        setBounds(480, 621, 480, 60);
        setBackground(Color.gray);
    }

    public void update() {
        if (Exercise.getProteinPowder()) {
            add(powderIcon);
        }
        //TODO - if: check for weights, resistance stuff, etc.
    }

}

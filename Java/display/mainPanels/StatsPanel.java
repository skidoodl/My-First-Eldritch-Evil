package display.mainPanels;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import main.Pet;
import main.Main;

public class StatsPanel extends JPanel{
    // Set up font details
    private static final int FONT_SIZE = 18;
    private static final Font DEFAULT_FONT = new Font("Arial",Font.PLAIN,FONT_SIZE);
    
    public StatsPanel() {
        Pet pet = Main.pet;
        // Have pet create array of stats to be printed
        String[] stats = pet.getStatsArray();

        setLayout(new GridLayout(stats.length,1));
        setBounds(480, 0, 480, 360); //Maybe change to take up the WHOLE right side?
        setBackground(Color.white);
        setBorder(new EmptyBorder(5,5,5,5));
        
        for (String stat : stats) {
            JLabel label = new JLabel(stat);
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.black);
            add(label);
        }
    }

    public void update() {
        // Have pet create array of stats to be printed
        String[] stats = Main.pet.getStatsArray();

        for (String stat : stats) {
            JLabel label = new JLabel(stat);
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.black);
            add(label);
        }
    }
    
}

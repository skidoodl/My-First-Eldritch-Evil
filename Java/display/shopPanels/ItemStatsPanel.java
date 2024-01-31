package display.shopPanels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Inventory;



public class ItemStatsPanel extends JPanel {
    String[] stats;
    private final String dfText = 
        "<html><p>No Item Selected - In future this panel may contain hints or smth" +
        "when no item is selected.</p><html>";
    JLabel defaultLabel = new JLabel(dfText);
    JLabel statsLabel = new JLabel();
    
    public ItemStatsPanel () {
        
        // Default Stats Panel
        setBounds(720, 0, 240, 240);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(4, 1));
        
        defaultLabel.setForeground(Color.black);
        

        add(defaultLabel, BorderLayout.CENTER);
    }

    public void showStats (String item) {        
        remove(defaultLabel);
        
        addStatLabels(item);
    }

    public void hideStats() {
        removeLabels();

        add(defaultLabel, BorderLayout.CENTER);
    }

    private void addStatLabels(String item) {
        removeLabels();

        String[] stats = Inventory.getItemStats(item);
        for (int i = 0; i < stats.length; i++) {
            JLabel label = new JLabel(stats[i]);
            label.setForeground(Color.black);
            add(label);
        }
    }

    private void removeLabels() {
        for (int i = getComponentCount() - 1; i >= 0; i--) {
            remove(i);
        }
    }

}

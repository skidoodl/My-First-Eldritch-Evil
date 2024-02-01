package display.inventoryPanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryActionPanel extends JPanel {
    
    public InventoryActionPanel() {
        setBounds(480, 500, 480, 220);
        setBackground(Color.gray);
        JLabel label = new JLabel("Inventory Action Panel");
        label.setForeground(Color.black);

        add(label, BorderLayout.CENTER);
    }
}

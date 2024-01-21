package display.shopPanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultActionPanel extends JPanel {

    
    public DefaultActionPanel () {
        setBounds(480, 500, 480, 220);
        setBackground(Color.gray);

        JLabel label = new JLabel("Placeholder Action Panel");
        label.setForeground(Color.black);

        add(label, BorderLayout.CENTER);

    }
    
}

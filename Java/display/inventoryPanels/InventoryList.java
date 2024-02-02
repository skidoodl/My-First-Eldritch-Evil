package display.inventoryPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Inventory;

public class InventoryList extends JPanel {
    // Set up font details
    private static final int FONT_SIZE = 22;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    
    String[] items = Inventory.getInventoryList();
    int[] quantity = Inventory.getInventoryAmount();

    public InventoryList() {
        
        setLayout(new GridLayout(items.length, 1));
        setBounds(0, 0, 480, 720);
        setBorder(new LineBorder(Color.magenta));
        setBackground(Color.white);
        setBorder(new EmptyBorder(5,10,25,10));

        addLabels();
    }

    private void addLabels() {
        Inventory.sortInventory();
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                break;
            }
            JLabel label = new JLabel(items[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.black);
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            FontMetrics fMetrics = label.getFontMetrics(label.getFont());
            int width = fMetrics.stringWidth(items[i]);
            int height = fMetrics.getHeight();
            // label.setPreferredSize(new Dimension(width, height));
            label.setMaximumSize(new Dimension(width, height));
            System.out.println("Label: " + label.getMaximumSize());

            add(label);
        }
    }

    private class OptionMouseListener extends MouseAdapter {
        private final JLabel label;

        public OptionMouseListener(JLabel label) {
            this.label = label;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setFont(BOLD_FONT);
            label.repaint();
            label.revalidate();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setFont(DEFAULT_FONT);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            String selectedItem = source.getText();
            System.out.println(selectedItem + " selected");
        }
    }
}

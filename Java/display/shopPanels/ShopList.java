package display.shopPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Inventory;
import main.Shop;

public class ShopList extends JPanel {
    // Set up font details
    private static final int FONT_SIZE = 22;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    
    String[] items = Inventory.getItemList();
    int[] price = Inventory.getPricesList();
    int[] stock = Inventory.getStockList();

    public ShopList() {

        setLayout(new GridLayout(items.length, 1));
        setBounds(0,30,480,690);
        setBorder(new LineBorder(Color.darkGray));
        setBackground(Color.white);
        setBorder(new EmptyBorder(5,10,25,10));

        addLabels();
    }

    private void addLabels() {
        for (int i = 0; i < items.length; i++) {
            if (stock[i] == 0) { // don't add if item is out of stock
                continue;
            }
            
            JLabel label = new JLabel(items[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(DEFAULT_FONT);
            
            // Change color depending on if player can afford item
            if (Inventory.affordability(items[i]) && Inventory.getItemStock(items[i]) > 0) { // can afford
                label.setForeground(Color.black);
            } else {
                label.setForeground(Color.gray);
            }

            add(label);
        }
    }

    private void removeAllLabels() {
        for (int i = getComponentCount() - 1; i >= 0; i--) {
            remove(i);
        }
    }

    public void refresh() {
        removeAllLabels();
        addLabels();
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
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setFont(DEFAULT_FONT);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            String selectedItem = source.getText();
            Shop.selectItem(selectedItem);
        }

    }

    
}

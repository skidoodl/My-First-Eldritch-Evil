package display.shopPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Inventory;

public class ItemList extends JPanel {
    // Set up font details
    private static final int FONT_SIZE = 22;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    
    String[] items = Inventory.getItemList();
    int[] price = Inventory.getPricesList();

    public ItemList() {

        setLayout(new GridLayout(items.length, 1));
        setBounds(0,30,480,690);
        setBackground(Color.white);
        setBorder(new EmptyBorder(5,10,25,10));

        addLabels();
    }

    private void addLabels() {
        for (int i = 0; i < items.length; i++) {
            JLabel label = new JLabel(items[i] + "  |  $" + price[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.black);
            add(label);
        }
    }

    private void removeAllLabels() {
        for (int i = getComponentCount() - 1; i >= 0; i--) {
            remove(i);
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
    
        }

    }

    private void buyMenu (String item) {
        //trigger dat slider.
    }
}

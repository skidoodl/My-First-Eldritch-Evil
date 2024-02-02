package display.mainPanels;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import main.Inventory;
import main.Menu;
import utils.Lazy;

public class MenuPanel extends JPanel {
    // Set up font details
    private static final int FONT_SIZE = 22;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Arial", Font.BOLD, FONT_SIZE+1);


    String[] options = Menu.menuOpts;
    boolean[] allowedWhileSleeping = Menu.allowedWhileSleeping;

    public MenuPanel() {

        setLayout(new GridLayout(options.length, 1));
        setBounds(0, 0, 480, 720);
        setBackground(Color.black);
        setBorder(new EmptyBorder(5, 10, 25, 20));

        addLabels();
    }

    public void update() {
        removeAllLabels();
        addLabels();
        repaint();
    }

    private void addLabels() {
        /* 
         * I need to figure out a better system for dealing with allowed/unallowed menu
         * choices. Perhaps an adjacent array with booleans, and a method to determine
         * true/false for each thing. That way, stuff like feeding the pet can be
         * grayed out if there is no food in the inventory, for example.
         */

        boolean isSleeping = main.Main.pet.isSleeping;
        for (int i = 0; i < options.length; i++) {
            JLabel label = new JLabel(options[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.white);
            if (isSleeping && !allowedWhileSleeping[i]) { // prevent during sleeping
                label.setForeground(Color.darkGray);
            }
            // empty inventory
            if (options[i].equals("Inventory") && Inventory.getInventoryTotal() == 0) {
                label.setForeground(Color.darkGray);
            }
            add(label);
            // no food
            if (options[i].equals("Feed "+main.Main.pet.name) && !Inventory.isInInventory("Food")) {
                label.setForeground(Color.darkGray);
            }
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
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setFont(DEFAULT_FONT);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            String selectedOption = source.getText();
            // Perform action based on the selected option
            handleOptionClick(selectedOption);

        }

    }

    private void handleOptionClick(String option) {
        // Add your logic here to handle the click for each option
        int sel = Lazy.findInArray(options, option);
        Menu.useMenu(sel);
    }
}

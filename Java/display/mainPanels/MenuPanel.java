package display.mainPanels;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import main.Menu;
import utils.Lazy;

public class MenuPanel extends JPanel{
    //Set up font details
    private static final int FONT_SIZE = 20;
    private static final Font DEFAULT_FONT = new Font("Papyrus", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Papyrus", Font.BOLD, FONT_SIZE);
    private static String[] opts;

    String[] options = Menu.menuOpts;
    boolean[] allowedWhileSleeping = Menu.allowedWhileSleeping;

    public MenuPanel() {
        

        opts = options;
        setLayout(new GridLayout(options.length, 1));
        setBounds(0,0,480,720);
        setBackground(Color.black);
        setBorder(new EmptyBorder(5, 10, 25, 20));

        addLabels();
    }

    public void update () {
        removeAllLabels();
        addLabels();
    }

    private void addLabels () {
        boolean isSleeping = main.Main.pet.isSleeping;
        for (int i = 0; i < options.length; i++) {
            JLabel label = new JLabel(options[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(DEFAULT_FONT);
            if (!isSleeping || allowedWhileSleeping[i]) {
                label.setForeground(Color.white);
            } else {
                label.setForeground(Color.darkGray);
            }
            add(label);
        }
    }

    private void removeAllLabels () {
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
        int sel = Lazy.findInArray(opts, option);
        Menu.useMenu(sel);
    }
}

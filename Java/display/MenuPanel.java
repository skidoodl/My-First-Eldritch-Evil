package display;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import main.Menu;
import utils.Lazy;

public class MenuPanel extends JPanel{
    //Set up font details
    private static final int FONT_SIZE = 20;
    private static final Font DEFAULT_FONT = new Font("Papyrus", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Papyrus", Font.BOLD, FONT_SIZE);
    private static String[] opts;

    public MenuPanel(String[] options) {
        opts = options;
        setLayout(new GridLayout(options.length, 1));
        setBounds(0,0,480,720);
        setBackground(Color.black);
        setBorder(new EmptyBorder(5, 10, 25, 20));

        for (String option : options) {
            JLabel label = new JLabel(option);
            label.addMouseListener(new OptionMouseListener(label));
            label.setFont(new Font("Papyrus", Font.PLAIN, 20));
            label.setForeground(Color.white);
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
            //TODO - Figure out the option selected
            
        }

    }

    private void handleOptionClick(String option) {
        // Add your logic here to handle the click for each option
        int sel = Lazy.findInArray(opts, option);
        JOptionPane.showMessageDialog(this, "Selected Option: " + option+"\nIndex: "+sel);
        Menu.useMenu(sel);
    }
}

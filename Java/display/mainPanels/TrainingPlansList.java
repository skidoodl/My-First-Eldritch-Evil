package display.mainPanels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Exercise;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrainingPlansList extends JPanel {
    // Set up font details
    private static final int FONT_SIZE = 25;
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font BOLD_FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    private static final Color selColor = new Color(4, 133, 0);

    public TrainingPlansList() {
        setLayout(new GridLayout(Exercise.getPlanCount()+2, 1));
        setBounds(480, 0, 480, 360);
        setPreferredSize(new Dimension(480, 360));
        setBackground(Color.lightGray);

    }

    public void update() {
        removeAllLabels();
        addLabels();
        revalidate();
        repaint();
    }

    private void addLabels() {
        String unlPlans[] = new String[Exercise.countUnlockedPlans()];
        System.out.print("Adding training plan labels...");

        // Step 1: Determine Unlocked Plans
        int x=0;
        for (int i=0; i<Exercise.getPlanCount(); i++) {
            if (Exercise.isUnlocked(i)) {
                unlPlans[x] = Exercise.getPlanString(i);
                x++;
            }
        }

        // Step 2: Add Labels
        System.out.print("creating and placing labels...");
        for (int i = 0; i < unlPlans.length; i++) {
            JLabel label = new JLabel(unlPlans[i]);
            label.addMouseListener(new OptionMouseListener(label));
            label.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 0));
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.black);
            try {
                if (Exercise.getSelectedPlanString().equals(unlPlans[i])) {
                    label.setForeground(selColor);
                }
            } catch (NullPointerException e) {

            }

            
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

    private void handleOptionClick(String plan) {
        if (plan.equals(Exercise.getSelectedPlanString())) {
            return;
        }
        Exercise.selectPlan(plan);
        update();
    }
}

package display.mainPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import display.GameWindow;
import main.Exercise;
import main.Inventory;
import main.Shop;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ExercisePanel extends JPanel {
    
    private static JButton bProteinPowder = new JButton();
    private static JButton bViewPlans = new JButton("View Plans");
    private static JButton bTrainPlan = new JButton("Train with Plan");
    private static JButton bTrainNoPlan = new JButton("Train without Plan");
    
    private static boolean proteinInUse = false;

    public ExercisePanel() {
        setBounds(480, 360, 460, 180);
        setBackground(Color.gray);
        
        setLayout(new GridLayout(2, 2, 10, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // View Fitness Plans
        bViewPlans = buttonSetUp(bViewPlans);
        bViewPlans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        // Protein Powder
        bProteinPowder = buttonSetUp(bProteinPowder);
        setProteinButton();
        bProteinPowder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proteinInUse) {
                    return;
                }
                if (Inventory.isInInventory("Protein Powder")) {
                    Exercise.useProteinPowder();
                    proteinInUse = true;
                } else {
                    Shop.openShop();
                    Shop.selectItem("Protein Powder");
                }
                GameWindow.updateAllPanels();
            }
            
        });

        // Train with plan
        bTrainPlan = buttonSetUp(bTrainPlan);
        bTrainPlan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        
        // Train w/out plan
        bTrainNoPlan = buttonSetUp(bTrainNoPlan);
        bTrainNoPlan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        add(bViewPlans);
        add(bProteinPowder);
        add(bTrainPlan);
        add(bTrainNoPlan);

    }

    private static void setProteinButton() {
        if(Inventory.isInInventory("Protein Powder")) {
            System.out.println("Use Protein Powder");
            bProteinPowder.setText("Use Protein Powder");
        } else {
            bProteinPowder.setText("Buy Protein Powder");
        }
        if (proteinInUse) {
            bProteinPowder.setForeground(Color.lightGray);
            bProteinPowder.setBackground(Color.darkGray);
        }
    }

    private JButton buttonSetUp(JButton button) {
        button.setFont(new Font("Calibri", Font.PLAIN, 18));
        button.setForeground(Color.black);
        button.setBackground(Color.white);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void update() {
        System.out.println("Update Exercise Panel");
        bViewPlans = buttonSetUp(bViewPlans);
        bProteinPowder = buttonSetUp(bProteinPowder);
        setProteinButton();
        bTrainPlan = buttonSetUp(bTrainPlan);
        bTrainNoPlan = buttonSetUp(bTrainNoPlan);

        add(bViewPlans);
        add(bProteinPowder);
        add(bTrainPlan);
        add(bTrainNoPlan);
    }
}
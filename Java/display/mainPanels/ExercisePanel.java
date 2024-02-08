package display.mainPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.Inventory;

import javax.swing.JButton;

public class ExercisePanel extends JPanel {
    
    private static JButton bProteinPowder = new JButton();
    private static JButton bViewPlans = new JButton("View Plans");
    private static JButton bTrainPlan = new JButton("Train with Plan");
    private static JButton bTrainNoPlan = new JButton("Train without Plan");


    public ExercisePanel() {
        setBounds(480, 360, 480, 360);
        setBackground(Color.gray);
        setLayout(new BorderLayout());

        // View Fitness Plans
        bViewPlans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        // Protein Powder
        nameProteinButton();
        bProteinPowder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        // Train with plan
        bTrainPlan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        
        // Train w/out plan
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

    private void nameProteinButton() {
        if(Inventory.isInInventory("Protein Powder")) {
            System.out.println("Use Protein Powder");
            bProteinPowder.setText("Use Protein Powder");
        } else {
            bProteinPowder.setText("Buy Protein Powder");
        }
    }
}

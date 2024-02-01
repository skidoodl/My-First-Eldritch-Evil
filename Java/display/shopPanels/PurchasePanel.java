package display.shopPanels;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import main.Inventory;
import main.Shop;

public class PurchasePanel extends JPanel implements ChangeListener {
    JButton button;
    JSlider slider;
    String item;

    public PurchasePanel (String item) {
        this.item = item;
        setBounds(480,500,480,220);
        setBackground(Color.gray);


        slider = new JSlider();

        slider.setMaximum(Inventory.getMostPurchasable(item));
        slider.setMinimum(1);
        slider.setPreferredSize(new Dimension(400, 130));
        //slider.setBounds(480, 500, 300, 150);
        slider.setPaintTicks(true); 
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5); // TODO - Should change based on max-purchasable
        slider.setPaintLabels(true);
        slider.setValue(0);
        slider.setSnapToTicks(true);
        slider.setBackground(Color.gray);
        slider.setForeground(Color.black);

        slider.addChangeListener(this);

        button = new JButton("Buy 1 " + item + " for " + Inventory.getItemPrice(item) + " mon.");
        button.setPreferredSize(new Dimension(260, 30));
        button.setBackground(Color.black);
        button.setForeground(new Color(109,77,172));
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shop.itemPurchase(item, slider.getValue());
            }
        });

        this.add(slider, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
    }

    @Override
    public void stateChanged (ChangeEvent e) {
        int amount = slider.getValue();
        int cost = slider.getValue()*Inventory.getItemPrice(this.item);
        button.setText("Buy " + amount + " " + this.item + " for " + cost + " mon.");
    }

}

package display.shopPanels;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import display.ShopWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import main.Inventory;

public class PurchasePanel extends JPanel implements ChangeListener {
    JButton button;
    JSlider slider;
    String item;

    public PurchasePanel (String item) {
        this.item = item;
        setBounds(480,500,480,220);
        setBackground(Color.gray);


        slider = new JSlider();

        int stock = Inventory.getItemStock(item);
        int mostPurchasable = Inventory.getWallet()/Inventory.getItemPrice(item);
        if (stock < mostPurchasable) {
            slider.setMaximum(stock);
        } else {
            slider.setMaximum(mostPurchasable);
        }
        slider.setMinimum(0);
        slider.setPreferredSize(new Dimension(300, 150));
        slider.setBounds(480, 500, 300, 150);
        slider.setPaintTicks(true); // ticks are dark gray so they don't really show :(
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5); // TODO - Should change based on max-purchasable
        slider.setPaintLabels(true);
        slider.setValue(0);
        slider.setSnapToTicks(true);
        
        slider.addChangeListener(this);

        button = new JButton("Buy 0 " + item + " for 0 mon.");
        button.setBounds(500, 680, 210, 140);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopWindow.itemPurchase(item, slider.getValue());
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

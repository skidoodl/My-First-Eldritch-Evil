package display.shopPanels;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import display.GameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
        setBackground(Color.lightGray);


        slider = new JSlider();

        slider.setMaximum(Inventory.getMostPurchasable(item));
        slider.setMinimum(0);
        slider.setPreferredSize(new Dimension(400, 130));
        slider.setBackground(Color.lightGray);
        slider.setPaintTicks(true); 
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        calculateMajorTick();
        slider.setPaintLabels(true);
        slider.setValue(1);
        slider.setSnapToTicks(true);
        slider.setForeground(Color.black);
        slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        slider.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    //System.out.println("Mouse wheel moved UP " + notches + " notch(es)");
                    slider.setValue(slider.getValue() - 1);
                } else if (notches > 0) {
                    //System.out.println("Mouse wheel moved DOWN " + -notches + " notch(es)");
                    slider.setValue(slider.getValue() + 1);
                }
            }
            
        });

        slider.addChangeListener(this);

        String buyString = "Buy 1 " + item + " for " + Inventory.getItemPrice(item) + " mon.";
        button = new JButton("Buy 1 " + item + " for " + Inventory.getItemPrice(item) + " mon.");
        int w = 230;
        if (buyString.length() > 35) {
            w = 300;
        }
        button.setPreferredSize(new Dimension(w, 40));
        button.setForeground(Color.black);
        button.setBackground(new Color(109,77,172));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shop.purchaseItem(item, slider.getValue());
                GameWindow.updateAllPanels();
            }
        });

        this.add(slider, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
    }

    private void calculateMajorTick() {
        int max = Inventory.getMostPurchasable(item);
        if (max <= 5) {
            slider.setMajorTickSpacing(max);
            return;
        }
        for (int i = 4; i < 10; i++) {
            if (max % i == 0) {
                slider.setMajorTickSpacing(i);
                return;
            }
        }
        slider.setMajorTickSpacing(5);
    }

    @Override
    public void stateChanged (ChangeEvent e) {
        int amount = slider.getValue();
        int cost = slider.getValue()*Inventory.getItemPrice(this.item);
        String buyString = "Buy " + amount + " " + this.item + " for " + cost + " mon.";
        button.setText(buyString);
        int w = 230;
        if (buyString.length() > 35) {
            w = 300;
        }
        button.setPreferredSize(new Dimension(w, 40));
    }

}

package display.shopPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Inventory;

public class SellPanel extends JPanel implements ChangeListener{
    JButton button;
    JButton cancel;
    JSlider slider = new JSlider();
    String item;

    public SellPanel(String item) {
        this.item = item;
        setBounds(480, 500, 480, 220);
        setPreferredSize(new Dimension(480, 220));
        setBackground(Color.gray);

        slider.setMaximum(Inventory.getInventoryAmount(item));
        slider.setMinimum(0);
        slider.setPreferredSize(new Dimension(400, 130));
        setBackground(Color.gray);
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
                    slider.setValue(slider.getValue() - 1);
                } else if (notches > 0) {
                    slider.setValue(slider.getValue() + 1);
                }
            }
        });

        slider.addChangeListener(this);

        button = new JButton("Sell 1 " + item + " for " + Inventory.getItemPrice(item)/2 + " mon.");
        button.setPreferredSize(new Dimension(180, 40));
        button.setForeground(Color.black);
        button.setBackground(new Color(109, 77, 172));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Inventory.sell(item, slider.getValue());
                Inventory.cancelSellPanel();
            }
            
        });

        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(180, 40));
        cancel.setForeground(Color.black);
        cancel.setBackground(new Color(109, 77, 172));
        cancel.setFocusPainted(false);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Inventory.cancelSellPanel();
            }
            
        });

        this.add(slider, BorderLayout.CENTER);
        this.add(cancel, BorderLayout.SOUTH);
        this.add(button, BorderLayout.SOUTH);
    }

    private void calculateMajorTick() {
        int max = Inventory.getInventoryAmount(item);
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
    public void stateChanged(ChangeEvent e) {
        int amount = slider.getValue();
        int sell = amount*(Inventory.getItemPrice(this.item)/2);
        button.setText("Sell " + amount + " " + this.item + " for " + sell + " mon.");
        
    }
}

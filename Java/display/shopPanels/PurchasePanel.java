package display.shopPanels;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;

import main.Inventory;

public class PurchasePanel extends JPanel implements ChangeListener{
    JLabel label;
    JSlider slider;

    public void ShopSlider (String item) {
        label = new JLabel();

        setBounds(480,500,480,220);
        setBackground(Color.magenta);

        slider = new JSlider();

        int stock = Inventory.getItemStock(item);
        int mostPurchasable = Inventory.getWallet()/Inventory.getItemPrice(item);
        if (stock > mostPurchasable) {
            slider.setMaximum(stock);
        } else {
            slider.setMaximum(mostPurchasable);
        }
        slider.setMinimum(0);
        slider.setPreferredSize(new Dimension(400, 200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);

        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5);

        slider.setPaintLabels(true);
        
        label.setText("Slider Output: " + slider.getValue());

        slider.addChangeListener(this);

        add(slider);
        add(label);
    }

    @Override
    public void stateChanged (ChangeEvent e) {
        label.setText("Slider Output: " + slider.getValue());
    }
}

package display.inventoryPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Inventory;

public class ItemActionPanel extends JPanel {
    private JButton useBtn;
    private JButton sellBtn;
    // TODO - private JButton viewInShopBtn;
    
    public ItemActionPanel (String item) {
        setBounds(480,500,480,220);
        setBackground(Color.gray);

        // -- USE ITEM -- //
        useBtn = new JButton();
        // Set Text
        if (Inventory.getItemType(item).equalsIgnoreCase("Gear")) {
            // TODO - Check if gear is/n't equipped, change btn txt accordingly
            useBtn.setText("Equip " + item);
        } else {
            useBtn.setText("Use Item");
        }
        // Other Setup
        useBtn.setPreferredSize(new Dimension(200, 40));
        useBtn.setForeground(Color.black);
        useBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Action Listener
        useBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inventory.useItem(item);
            }
        });

        // -- SELL BUTTON -- //
        sellBtn = new JButton("Sell Item");
        sellBtn.setPreferredSize(new Dimension(200, 40));
        sellBtn.setForeground(Color.black);
        sellBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Action Listener
        sellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inventory.sellItem(item);
            }
        });

        add(useBtn);
        add(sellBtn);
    }

}

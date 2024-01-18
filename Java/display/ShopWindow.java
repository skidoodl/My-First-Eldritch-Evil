package display;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.ShopFrame;
import display.shopPanels.ItemList;

public class ShopWindow {
    ShopFrame sFrame;
    public int itemSelected;

    public ShopWindow() {
        sFrame = new ShopFrame();

        // WALLET DISPLAY
        JPanel wallet = new JPanel();
        wallet.setBackground(Color.black);
        wallet.setBounds(0,0,480,30);

        // ITEM LIST
        ItemList items = new ItemList();

        // ITEM DISPLAY
        JPanel display = new JPanel();
        display.setBackground(Color.pink);
        display.setBounds(480,0,480,250);

        // ITEM DESCRIPTION
        JPanel desc = new JPanel();
        desc.setBackground(Color.cyan);
        desc.setBounds(480,250,480,250);

        //ACTION MENU
        JPanel actions = new JPanel();
        actions.setBackground(Color.orange);
        actions.setBounds(480,500,480,220);

        sFrame.add(wallet);
        sFrame.add(items);
        sFrame.add(display);
        sFrame.add(desc);
        sFrame.add(actions);
        sFrame.revalidate();
        sFrame.repaint();
        sFrame.setVisible(true);
        
    }
}

package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import display.shopPanels.*;

public class Shop implements WindowListener {
    //private static ShopFrame sFrame;
    private static JFrame sFrame = new JFrame();
    private static boolean shopFrameOpen;

    private static DefaultActionPanel actionPanel;
    private static PurchasePanel purchasePanel;
    private static ItemDisplay itemDisp = new ItemDisplay();
    private static ItemStatsPanel itemStats;

    private static boolean itemSelected = false;
    private static String currentSelected;

    private static void createShopFrame() {
        ImageIcon icon = new ImageIcon("Resources/Icons/Shop_Icon.png");
        
        updateHeader();
        sFrame.setSize(960, 720);
        sFrame.setLayout(null);
        sFrame.setResizable(false);
        sFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sFrame.setIconImage(icon.getImage());

        shopFrameOpen = true;
    }

    public static void openShop() {
        if (shopFrameOpen) {
            sFrame.requestFocus();
            return;
        }

        createShopFrame();

        // WALLET DISPLAY
        JPanel wallet = new JPanel();
        wallet.setBackground(Color.black);
        wallet.setBounds(0,0,480,30);

        // ITEM LIST
        ItemList items = new ItemList();

        // DISPLAY
        JPanel display = new JPanel();
        display.setBackground(Color.lightGray);
        display.setBounds(480,0,480,240);
        
        // ITEM STATS
        itemStats = new ItemStatsPanel();

        // ITEM DESCRIPTION
        JPanel desc = new JPanel();
        desc.setBackground(Color.cyan);
        desc.setBounds(480,240,480,260);

        //ACTION MENU
        actionPanel = new DefaultActionPanel();

        sFrame.add(wallet);
        sFrame.add(items);
        sFrame.add(display);
        sFrame.add(itemDisp);
        sFrame.add(desc);
        sFrame.add(actionPanel);
        sFrame.add(itemStats);

        if (itemSelected == true) {
            itemSelect(currentSelected);
        }

        sFrame.revalidate();
        sFrame.repaint();
        sFrame.setVisible(true);
        
    }

    public static void itemSelect(String item) {
        System.out.println("Item Select");

        if (item.equals(currentSelected)) {
            deselectItem();
            return;
        }

        // Add the purchase panel
        if (!itemSelected) {
            sFrame.remove(actionPanel);
            itemSelected = true;
        } else {
            sFrame.remove(purchasePanel);
        }
        currentSelected = item;
        purchasePanel = new PurchasePanel(item);
        itemDisp.showImage(item);
        itemStats.showStats(item);
        sFrame.add(purchasePanel);

        sFrame.revalidate();
    }

    public static void deselectItem() {
        if (itemSelected) {
            System.out.println("Deselect");
            itemSelected = false;
            currentSelected = null;
            sFrame.remove(purchasePanel);
            sFrame.add(actionPanel);
            itemDisp.removeImage();
            itemStats.hideStats();
            
        }
        sFrame.revalidate();
        sFrame.repaint();
    }

    public static void itemPurchase(String item, int quantity) {
        Inventory.purchaseItem(currentSelected, quantity);
        updateHeader();
        itemSelect(item);
    }

    private static void updateHeader() {
        sFrame.setTitle("Shop - My First Eldritch Evil - Wallet: " + Inventory.getWallet() + " mon");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shopFrameOpen = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowClosed'");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }
}

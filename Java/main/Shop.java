package main;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.ShopFrame;
import display.generalPanels.ItemStatsPanel;
import display.shopPanels.*;

public class Shop {
    private static ShopFrame sFrame = new ShopFrame();

    private static DefaultActionPanel actionPanel;
    private static PurchasePanel purchasePanel;
    private static ItemDisplay itemDisp = new ItemDisplay();
    private static ItemStatsPanel itemStats;
    private static ShopList items;

    private static boolean itemSelected = false;
    private static String currentSelected;


    public static void openShop() {
        
        // WALLET DISPLAY
        JPanel wallet = new JPanel();
        wallet.setBackground(Color.black);
        wallet.setBounds(0,0,480,30);

        // ITEM LIST
        items = new ShopList();

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
            selectItem(currentSelected);
        }

        sFrame.revalidate();
        sFrame.repaint();
        sFrame.open();
        
    }

    public static void selectItem(String item) {
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
        items.refresh();
        selectItem(item);
    }


    private static void updateHeader() {
        sFrame.setTitle("Shop - My First Eldritch Evil - Wallet: " + Inventory.getWallet() + " mon");
    }

    
}

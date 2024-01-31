package display;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.ShopFrame;
import display.shopPanels.DefaultActionPanel;
import display.shopPanels.ItemDisplay;
import display.shopPanels.ItemList;
import display.shopPanels.PurchasePanel;
import display.shopPanels.*;
import main.Inventory;

public class ShopWindow {
    private static ShopFrame sFrame;

    private static DefaultActionPanel actionPanel;
    private static PurchasePanel purchasePanel;
    private static ItemDisplay itemDisp = new ItemDisplay();
    private static ItemStatsPanel itemStats;

    private static boolean itemSelected = false;
    private static String currentSelected;

    public static void openShop() {
        sFrame = new ShopFrame();

        // WALLET DISPLAY
        JPanel wallet = new JPanel();
        wallet.setBackground(Color.black);
        wallet.setBounds(0,0,480,30);

        // ITEM LIST
        ItemList items = new ItemList();

        // DISPLAY
        JPanel display = new JPanel();
        display.setBackground(Color.pink);
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
            sFrame.remove(purchasePanel);
            itemSelected = false;
            currentSelected = null;
            sFrame.add(actionPanel);
            itemDisp.removeImage();
        }
        sFrame.revalidate();
        sFrame.repaint();
    }

    public static void itemPurchase(String item, int quantity) {
        Inventory.purchaseItem(currentSelected, quantity);
        sFrame.updateHeader();
        itemSelect(item);
    }
}

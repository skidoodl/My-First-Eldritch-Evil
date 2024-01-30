package display;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.ShopFrame;
import display.shopPanels.DefaultActionPanel;
import display.shopPanels.ItemDisplay;
import display.shopPanels.ItemList;
import display.shopPanels.PurchasePanel;
import main.Inventory;

public class ShopWindow {
    private static ShopFrame sFrame;

    private static DefaultActionPanel actionPanel;
    private static PurchasePanel purchasePanel;
    private static ItemDisplay itemDisp = new ItemDisplay();

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
        sFrame.revalidate();
        sFrame.repaint();
        sFrame.setVisible(true);
        
    }

    public static void itemSelect(String item) {
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
        sFrame.add(purchasePanel);

        sFrame.revalidate();
    }

    public static void deselectItem() {
        if (itemSelected) {
            sFrame.remove(purchasePanel);
            itemSelected = false;
            currentSelected = null;
            sFrame.add(actionPanel);
            itemDisp.removeImage();
        }
        sFrame.revalidate();
        sFrame.repaint();
    }

    public static void refreshItem(String item) {
        deselectItem();
        itemSelect(item);

    }

    public static void itemPurchase(String item, int quantity) {
        // TODO - add method to properly update relevant panels

        Inventory.purchaseItem(currentSelected, quantity);
        sFrame.updateHeader();
        refreshItem(item);        
    }
}

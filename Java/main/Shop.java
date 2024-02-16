package main;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import display.frames.ShopFrame;
import display.generalPanels.ItemDisplay;
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
        deselectItem();
        
        // WALLET DISPLAY
        JPanel wallet = new JPanel();
        wallet.setBackground(Color.black);
        wallet.setBounds(0,0,480,30);

        // ITEM LIST
        items = new ShopList();
        
        // ITEM STATS
        itemStats = new ItemStatsPanel();

        // ITEM DESCRIPTION
        JPanel desc = new JPanel();
        desc.setBackground(Color.gray);
        desc.setBounds(480,240,480,260);

        //ACTION MENU
        actionPanel = new DefaultActionPanel();

        sFrame.add(wallet);
        sFrame.add(items);
        sFrame.add(itemDisp);
        sFrame.add(desc);
        sFrame.add(actionPanel);
        sFrame.add(itemStats);

        sFrame.revalidate();
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
        if (Inventory.getMostPurchasable(item) > 0) {
            sFrame.add(purchasePanel);
        }

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
        try{ 
            items.refresh();
        } catch (NullPointerException e) {
            
        }
        sFrame.revalidate();
        sFrame.repaint();
    }

    public static void purchaseItem(String item, int quantity) {
        if (item.equals("Random Training Plan")) {
            for (int i=0; i<quantity; i++) {
                randomTrainingPlan();
                if(Inventory.getWallet() < Inventory.getItemPrice("Random Training Plan") && (i+1 != quantity)) {
                    JOptionPane.showMessageDialog(null, "You can no longer afford this item.", "Not Enough Mon!", JOptionPane.INFORMATION_MESSAGE);
                    updateHeader();
                    items.refresh();
                    deselectItem();
                    return;
                }
            }
        } else {
            Inventory.purchaseItem(item, quantity);
        }
        updateHeader();
        items.refresh();
        selectItem(item);
    }
    private static void randomTrainingPlan() {
        int ref = Inventory.getItemReference("Random Training Plan");
        Random ran = new Random();
        System.out.print("Getting random training plan...");
        int p = ran.nextInt(Exercise.getPlanCount());
        String planItem = "Training Plan: " + Exercise.getPlanString(p);
        System.out.println("Random Int: " + p + " // Plan Item Name: " + planItem);
        Inventory.give(planItem, 1);
        Exercise.unlockPlan(p);
        JOptionPane.showMessageDialog(null, "You recieved " + planItem + "!    ", "Training Plan Obtained", JOptionPane.INFORMATION_MESSAGE);
        int priceIncrease = (int) Math.round(Inventory.getItemPrice("Random Training Plan")*0.18);
        Inventory.changeItemStock(ref, -1);
        Inventory.changeMoney(-Inventory.getItemPrice(ref));
        Inventory.changeItemPrice(ref, priceIncrease);
    }


    private static void updateHeader() {
        sFrame.setTitle("Shop - My First Eldritch Evil - Wallet: " + Inventory.getWallet() + " mon");
    }
    
}

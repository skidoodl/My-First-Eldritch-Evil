package main;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.InventoryFrame;
import display.inventoryPanels.InventoryActionPanel;
import display.inventoryPanels.InventoryList;
import display.inventoryPanels.ItemActionPanel;
import utils.Lazy;

public class Inventory {
    //-----WALLET-----//
    private static int wallet;
    //-----NORMAL ITEMS-----//
    private static String[] inventory = new String[10];
    private static int[] itemInvAmount = new int[10];
    private static final String[] items = {"Food", "Medication", "Strong Medication","Vitamins","Energy Drink","Incense","Alarm Clock"};
    private static final String[] itemType = {"Food", "Healing", "Healing", "Healing", "Buffs", "Miscellaneous", "Gear"};
    private static int[] itemStock = {50,20,5,25,35,16,1,0,0,0};
    private static final int[] itemPrice = {/*food*/ 50,/*meds*/ 300,/*strong meds*/ 600,/*vitamins*/200,/*Energy Drink*/250,/*Incense*/400,/*Alarm Clock*/750};

    //-----OTHER STUFF-----//
    

    // ----- GET METHODS ----- //

    public static String[] getInventoryList() {
        return inventory;
    }
    public static int getInventoryTotal() {
        int total = 0;
        for(int i = 0; i < itemInvAmount.length && itemInvAmount != null; i++) {
            total += itemInvAmount[i];
        }
        return total;
    }

    public static String getInventoryItemString(int itemReference) {
        return inventory[itemReference];
    }
    public static String getInventoryItemReference(String itemName) {
        // TODO - Complete method
        return null;
    }

    public static int[] getInventoryAmount() {
        return itemInvAmount;
    }
    public static int getInventoryAmount(String itemName) {
        int i = Lazy.findInArray(inventory, itemName);
        return itemInvAmount[i];
    }
    public static int getInventoryAmount(int itemReference) {
        return itemInvAmount[itemReference];
    }

    public static String[] getItemList() {
        return items;
    }
    public static String getItemName (int itemReference) {
        return items[itemReference];
    }
    public static int getItemReference (String itemName) {
        return Lazy.findInArray(items, itemName);
    }

    public static int[] getPricesList() {
        return itemPrice;
    }
    public static int getItemPrice(String itemName) {
        return itemPrice[Lazy.findInArray(items, itemName)];
    }
    public static int getItemPrice(int itemReference) {
        return itemPrice[itemReference];
    }

    public static int[] getStockList () {
        return itemStock;
    }
    public static int getItemStock(String itemName) {
        return itemStock[Lazy.findInArray(items, itemName)];
    }
    public static int getItemStock(int itemReference) {
        return itemStock[itemReference];
    }

    public static String getItemType (String item) {
        return itemType[getItemReference(item)];
    }
    public static String getItemType (int itemReference) {
        return itemType[itemReference];
    }

    public static int getWallet() {
        return wallet;
    }
    public static void setWallet(int amount) {
        wallet = amount;
    }
    public static void addMoney(int amount) {
        wallet += amount;
    }

    //----USEFUL TOOLS----//

    public static void purchaseItem(String itemName, int quantity) {
        int i = Lazy.findInArray(items, itemName);
        itemStock[i] -= quantity;
        give(itemName, quantity);

        wallet -= (itemPrice[i]*quantity);
    }

    public static void give(String itemName, int quantity) {
        int i;
        sortInventory();
        // search inventory for the item
        for (i = 0; i < inventory.length; i++) { // TODO - make this search a method
            if (inventory[i] == null) {
                inventory[i] = itemName;
                break;
            }
            if (inventory[i].equals(itemName)) {
                break;
            }
        }
        itemInvAmount[i] += quantity;
    }
    public static void give(int itemReference, int quantity) {
        // to do
    }

    public static void remove(String itemName, int quantity) {
        int i = findInventoryLocation(itemName);
        itemInvAmount[i] -= quantity;
    }

    public static String[] getItemStats(String item) {
        int i = getItemReference(item);
        
        String owned;
        if (getInventoryAmount(i) == 0) {
            owned = "None";
        } else {
            owned = Integer.toString(getInventoryAmount(i));
        }

        String[] stats = {
            item,
            "Amount Owned: " + owned,
            "Item Stock: " + itemStock[i] + "x",
            "Buy Price: " + itemPrice[i] + " mon",
            "Sell Price: " + itemPrice[i]/2 + " mon",
            "Item Type: " + itemType[i]
        };

        return stats;
    }
    public static String[] getItemStats(int itemReference) {
        
        String owned;
        if (getInventoryAmount(itemReference) == 0) {
            owned = "None";
        } else {
            owned = Integer.toString(getInventoryAmount(itemReference));
        }

        String[] stats = {
            getItemName(itemReference),
            "Amount Owned: " + owned,
            "Item Stock: " + itemStock[itemReference] + "x",
            "Buy Price: " + itemPrice[itemReference] + " mon",
            "Sell Price: " + itemPrice[itemReference]/2 + " mon",
            "Item Type: " + itemType[itemReference]
        };

        return stats;
    }
    public static boolean isInInventory(String item) {
        System.out.println("Searching inventory for " + item + "...");
        sortInventory();
        for (int i = 0; i < itemInvAmount.length; i++) {
            if (inventory[i] == null) {
                break;
            }
            if (inventory[i].equalsIgnoreCase(item)) {
                System.out.println(item + " found.");
                return true;
            }
        }
        System.out.println(item + " could not be found.");
        return false;
    }

    public static Integer findInventoryLocation(String item) {
        System.out.println("Searching inventory for " + item + "...");
        sortInventory();
        for (int i = 0; i < itemInvAmount.length; i++) {
            if(inventory[i] == null) {
                break;
            }
            if(inventory[i].equalsIgnoreCase(item)) {
                System.out.println(item + " found at ref# " + i);
                return i;
            }
        }
        System.out.println(item + " could not be found.");
        return null;
    }

    public static boolean affordability(String item) {
        int i = getItemReference(item);
        if (wallet >= itemPrice[i]) {
            return true;
        } else {
            return false;
        }
    }

    public static int getMostPurchasable(String item) {
        int i = getItemReference(item);
        int mostP = wallet/itemPrice[i];

        if (itemStock[i] < mostP) {
            return itemStock[i];
        } else {
            return mostP;
        }
    }

    // ---- OPEN INVENTORY ---- //
    private static InventoryFrame iFrame;

    private static InventoryActionPanel actionPanel;
    private static ItemActionPanel itemActions;

    public static void openInventory() {

        iFrame = new InventoryFrame();

        // item list
        InventoryList invList = new InventoryList();
        
        // wallet info
        JPanel monInfo = new JPanel(); // Displays player's wallet info
        monInfo.setBackground(Color.red);
        monInfo.setBounds(0,640,480,40);

        // item info
        JPanel itemInfo = new JPanel(); // Displays info about items or, if not item is selected, tips or other game-related info
        itemInfo.setBackground(Color.green);
        itemInfo.setBounds(480,0,480,480);

        // action panel
        actionPanel = new InventoryActionPanel();

        // Add Panels
        iFrame.add(invList);
        iFrame.add(monInfo);
        iFrame.add(itemInfo);
        iFrame.add(actionPanel);
        iFrame.revalidate();
        iFrame.repaint();
        iFrame.setVisible(true);
    }

    // ----- Inventory Actions ----- //
    public static void useItem(String item) {
        // TODO - Build
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    public static void sellItem(String item) {
        // TODO - Build
        throw new UnsupportedOperationException("Unimplemented method 'sellItem");
    }

    // Set Up select-related variables
    private static boolean itemSelected = false;
    private static String currentSelected;
    
    public static void selectItem(String item) {
        System.out.println("Inventory " + item + " selected");
        
        if (item.equals(currentSelected)) {
            deselectItem();
            return;
        }

        //Add item action panel
        if (!itemSelected) {
            iFrame.remove(actionPanel);
            itemSelected = true;
        } else {
            iFrame.remove(itemActions);
        }
        currentSelected = item;
        itemActions = new ItemActionPanel(item);
        iFrame.add(itemActions);

        iFrame.revalidate();
    }

    public static void deselectItem() {
        // TODO - Finish
        throw new UnsupportedOperationException("Unimplemented method 'deselectItem");
    }

    public static void sortInventory() {
        System.out.println("Sorting Inventory");
        int i;
        for (i = 0; i < inventory.length; i++) {
            if (itemInvAmount[i] == 0) {
                inventory[i] = null;
                break;
            }
        }
        for (int j = i + 1; j < inventory.length; j++) {
            if (inventory[j] != null) {
                inventory[i] = inventory[j]; // move item name down
                itemInvAmount[i] = itemInvAmount[j]; // move amount down
                itemInvAmount[j] = 0; // delete j amount
                inventory[j] = null; // delete j item
                i++;
            }
        }
        Lazy.printArray(inventory, false);
    }
}

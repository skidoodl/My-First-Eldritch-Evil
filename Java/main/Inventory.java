package main;

import java.awt.Color;

import javax.swing.JPanel;

import display.frames.InventoryFrame;
import display.inventoryPanels.InventoryList;
import utils.Lazy;

public class Inventory {
    //-----WALLET-----//
    private static int wallet;
    //-----NORMAL ITEMS-----//
    private static String[] inventory = new String[10];
    private static int[] itemInvAmount = new int[10];
    private static final String[] items = {"Food", "Medication", "Strong Medication","Vitamins","Energy Drink","Incense"};
    private static int[] itemStock = {50,20,5,25,35,16,0,0,0,0};
    private static final int[] itemPrice = {/*food*/ 50,/*meds*/ 450,/*strong meds*/ 900,/*vitamins*/200,/*Energy Drink*/230,/*Incense*/410};

    public static String[] getInventoryList() {
        return inventory;
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

        String[] stats = {
            item,
            "\nBuy Price: " + itemPrice[i] + " mon",
            "\n Sell Price: " + "unavailable",
            "\nStock: x" + itemStock[i]
        };
        // more to come, I assume

        return stats;
    }
    public static String[] getItemStats(int itemReference) {

        String[] stats = {
            getItemName(itemReference),
            "\nBuy Price: " + itemPrice[itemReference] + " mon",
            "\n Sell Price: " + "unavailable",
            "\nStock: x" + itemStock[itemReference]
        };
        // more to come, I assume

        return stats;
    }
    public static boolean isInInventory(String item) {
        System.out.println("Searching inventory for" + item + "...");
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


    // ---- OPEN INVENTORY ---- //
    private static InventoryFrame iFrame;

    public static void openInventory() {

        iFrame = new InventoryFrame();

        InventoryList invList = new InventoryList();
        

        JPanel monInfo = new JPanel(); // Displays player's wallet info
        monInfo.setBackground(Color.red);
        monInfo.setBounds(0,640,480,40);

        JPanel itemInfo = new JPanel(); // Displays info about items or, if not item is selected, tips or other game-related info
        itemInfo.setBackground(Color.green);
        itemInfo.setBounds(480,0,480,720);

        // Add Panels
        iFrame.add(invList);
        iFrame.add(monInfo);
        iFrame.add(itemInfo);
        iFrame.revalidate();
        iFrame.repaint();
        iFrame.setVisible(true);
    
    }


    private static void sortInventory() {
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
        Lazy.printArray(inventory);
    }
}

package main;

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

    public static void purchaseItem(String itemName, int quantity) {
        int i = Lazy.findInArray(items, itemName);
        itemStock[i] -= quantity;
        give(itemName, quantity);

        wallet -= (itemPrice[i]*quantity);
    }

    public static void give(String itemName, int quantity) {
        int i;
        sortInventory();
        for (i = 0; i < inventory.length; i++) { // search inventory for the item
            if (inventory[i] == null) {
                inventory[i] = itemName;
                break;
            }
            if (inventory[i] == itemName) {
                break;
            }
        }
        itemInvAmount[i] += quantity;
    }
    public static void give(int itemReference, int quantity) {
        // to do
    }

    public static void remove(String itemName, int quantity) {
        // to do
    }


    private static void sortInventory() {
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

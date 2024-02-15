package main;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import display.frames.InventoryFrame;
import display.generalPanels.ItemDisplay;
import display.generalPanels.ItemStatsPanel;
import display.inventoryPanels.InventoryActionPanel;
import display.inventoryPanels.InventoryList;
import display.inventoryPanels.ItemActionPanel;
import display.shopPanels.SellPanel;
import utils.Lazy;

public class Inventory {
    //-----WALLET-----//
    private static int wallet;
    //-----NORMAL ITEMS-----//
    private static final String[] items = {
        "Food", 
        "Medication", 
        "Strong Medication",
        "Vitamins",
        "Protein Powder",
        "Energy Drink",
        "Incense",
        "Alarm Clock",
        "Random Training Plan",
        "Training Plan: Weight Lifting",
        "Training Plan: Bulking",
        "Training Plan: Weight Loss",
        "Training Plan: Stamina Training",
        "Training Plan: Sprinting",
        "Training Plan: Sparring",
    };
    private static final String[] itemType = {
        "Food", // food
        "Healing", // medication
        "Healing", // strong medication
        "Healing", // vitamins
        "Buffs", // protein powder
        "Buffs", // energy drink
        "Miscellaneous", // incense
        "Gear", // alarm clock
        "Training", // random training plan
        "Training", // weight lifting
        "Training", // bulking
        "Training", // weight loss
        "Training", // stamina
        "Training", // sprinting
        "Training", // sparring
    };
    private static int[] itemStock = {
        50, // food
        12, // meds
        5, // strong meds
        20, // vitamins
        15, // protein powder
        10, // energy drink
        10, // incense
        1, // alarm clock
        4, // random training plan
        0, // weight lifting
        0, // bulking
        0, // weight loss
        0, // stamina
        0, // sprint
        0, // spar
    };
    private static int[] itemPrice = {
        50, // food
        300, // meds
        600, // strong meds
        200, // vitamins
        125, // protein powder
        250, // energy drink
        400, // incense
        650, // alarm clock
        725, // random training plan
        1200, // weight lifting
        1200, // bulking
        1200, // weight loss
        1200, // stamina
        1200, // sprint
        1200, // spar
    };

    private static String[] inventory = new String[items.length];
    private static int[] itemInvAmount = new int[items.length];

    //-----OTHER STUFF-----//

    public static boolean checkItemDataError() {
        int i = items.length;
        int j = itemType.length;
        int k = itemStock.length;
        int l = itemPrice.length;

        if (i == j && i == k && i == l && j == k && j == l && k == l) {
            return false;
        }
        JOptionPane.showMessageDialog(null, "One or more of the inventory item information arrays may be missing information.", "Item Data Array Length Discrepancy", JOptionPane.ERROR_MESSAGE);
        return true;
    }
    

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
    public static int getInventoryItemReference(String itemName) {
        return Lazy.findInArray(inventory, itemName);
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
    public static void changeItemPrice(String item, int amount) {
        itemPrice[getItemReference(item)] += amount;
    }
    public static void changeItemPrice(int itemReference, int amount) {
        itemPrice[itemReference] += amount;
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
    public static void changeItemStock(String item, int amount) {
        int i = getItemReference(item);
        itemStock[i] += amount;
    }
    public static void changeItemStock(int itemReference, int amount) {
        itemStock[itemReference] += amount;
    }
    public static int countItemsStocked() {
        int x = 0;
        for (int i = 0; i < itemStock.length; i++) {
            if (itemStock[i] != 0) {
                x++;
            }
        }
        return x;
    }

    public static String getItemType (String item) {
        System.out.println("Getting item type");
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
    public static void changeMoney(int amount) {
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
        for (i = 0; i < inventory.length; i++) {
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

    public static void sell(String item, int quantity) {
        remove(item, quantity);
        changeMoney((getItemPrice(item)/2)*quantity);
    }

    public static String[] getItemStats(String item) {
        int i = getItemReference(item);
        
        String owned;
        try {
            owned = Integer.toString(getInventoryAmount(item));
        } catch (ArrayIndexOutOfBoundsException e) {
            owned = "None";
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
       /*  JPanel itemInfo = new JPanel(); // Displays info about items or, if not item is selected, tips or other game-related info
        itemInfo.setBackground(Color.green);
        itemInfo.setBounds(480,0,480,480); */

        // action panel
        actionPanel = new InventoryActionPanel();

        // Add Panels
        iFrame.add(invList);
        iFrame.add(monInfo);
        //iFrame.add(itemInfo);
        iFrame.add(actionPanel);
        iFrame.revalidate();
        iFrame.repaint();
        iFrame.setVisible(true);
    }

    // ----- Inventory Actions ----- //
    public static void useItem(String item) {
        switch(item) {
            case "Food":
                Main.pet.feed();
                break;
            case "Medication":
                break;
            case "Strong Medication":
                break;
            case "Vitamins":
                break;
            case "Energy Drink":
                break;
            case "Incense":
                break;
            case "Alarm Clock":
                break;
            default:
                System.out.println("Unlisted (use item)");
        }
    }

    

    // Set Up select-related variables
    private static boolean itemSelected = false;
    private static String currentSelected;
    private static ItemDisplay itemDisp = new ItemDisplay();
    private static ItemStatsPanel iStats = new ItemStatsPanel();
    
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
        if (selling) {
            iFrame.remove(sellPanel);
        }
        currentSelected = item;
        itemActions = new ItemActionPanel(item);
        itemDisp.showImage(item);
        iStats.showStats(item);
        iFrame.add(itemActions);
        iFrame.add(itemDisp);
        iFrame.add(iStats);

        iFrame.revalidate();
        iFrame.repaint();
    }

    public static void deselectItem() {
        if (itemSelected) {
            System.out.println("Inventory Deselect");
            itemSelected = false;
            currentSelected = null;
            iFrame.remove(itemActions);
            // add default action panel
            itemDisp.removeImage();
            iStats.hideStats();
            if (sellPanel != null) {
                iFrame.remove(sellPanel);
            }
            //iFrame.remove(sellPanel);
        }
        iFrame.revalidate();
        iFrame.repaint();
    }

    private static SellPanel sellPanel;
    private static boolean selling = false;
    public static void sellItem(String item) {
        selling = true;
        iFrame.remove(itemActions);
        sellPanel = new SellPanel(item);
        iFrame.add(sellPanel);
        iFrame.revalidate();
    }

    public static void cancelSellPanel() {
        selling = false;
        iFrame.remove(sellPanel);
        iFrame.add(itemActions);
        sellPanel = null;
        iFrame.revalidate();
        iFrame.repaint();
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

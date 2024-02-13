package main;
import display.GameWindow;

import javax.swing.JOptionPane;

public class Menu{
  private static boolean statsOn = false;

  //-----MENU-----//
  private static String name = Main.pet.name;
  public static String[] menuOpts = {"Inventory","Store",(name+"'s Stats"),("Feed "+name),("Sleep "+name),("Exercise "+name),("Cuddle with "+name),"Next Day","Test"};
  public static final boolean[] allowedWhileSleeping = {true,true,true,false,false,false,true,false,true};
  //-----NORMAL INVENTORY-----//
  public static String[] invItems = new String[10];
  public static final String[] shopItems = {"Food", "Medication", "Strong Medication","Vitamins","Energy Drink","Incense"};
  public static int[] itemStock = {100,20,5,25,35,16,0,0,0,0};
  public static int[] itemInvAmount = new int[10];
  public static final int[] itemPrice = {/*food*/ 50,/*meds*/ 450,/*strong meds*/ 900,/*vitamins*/200,/*Energy Drink*/230,/*Incense*/410}; // load item prices
  //-----CRISIS INVENTORY-----//
  public static String[] crisisInvItems = new String[10];
  public static String[] crisisShopItems = {"Armor (lv1)", "Armour (lv2)", "Armoure (lv3)","Wood Sword","Iron Sword","Shield","Food Bundle","Sedatives","Magic Juice","Magic Super Juice"};
  public static int[] crisisItemStock = {1,1,1,1,1,1,20,20,15,10};
  public static int[] crisisInvAmount = new int[10];
  public static int[] crisisPrice = {/*Armor*/ 600,/*Armour*/ 950,/*Armoure*/ 1800,/*Wood Sword*/475,/*iron sword*/ 1020,/*Shield*/380,/*food bundle*/ 220, /*sedatives*/375, /*Magic Juice*/450, /*Magic Super Juice*/775}; // load item prices
  

  public static void useMenu (int sel){ // TODO - Make this a method in GameWindow plz
    final Pet pet = Main.pet;
    GameWindow gw = Main.gw;

    switch(sel){ //TODO - Edit the cases to start from 0
        case 0: //inventory
          if (Inventory.getInventoryTotal() == 0) {
            JOptionPane.showMessageDialog(null, "You have no items in your inventory. Visit the shop to buy some items.", "Inventory Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
          } else {
            Inventory.openInventory();
          }
          break;
        case 1: //store
          Shop.openShop();
          break;
        case 2: //Check Stats
          if (statsOn) {
            statsOn = false;
            gw.statsVisible(false);
          } else {
            statsOn = true;
            gw.statsVisible(true);
          }
          break;
        case 3: //feed
        if (!Inventory.isInInventory("Food")) { // TODO - should move this to feed method
          JOptionPane.showMessageDialog(null, "You have no food :(", "No Food", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
          pet.feed();
          if (statsOn) { // move this into the feed method?
            gw.updateStats();
          }
          break;
        case 4: // sleep
          if (!pet.isSleeping){
            if (pet.energy >= 1.6) {
              JOptionPane.showMessageDialog(null,pet.name+" is too energized to sleep.","Sleep "+pet.name, JOptionPane.INFORMATION_MESSAGE);
            } else {
              gw.petSleep();
            }            
          }
          break;
        case 5: //Exercise
          GameWindow.toggleExercisePanel();
          break;
        case 6: //cuddle pet
          pet.cuddlePet();
          break;
        case 7:
          if (pet.isSleeping) {
            JOptionPane.showMessageDialog(null, "Cannot continue while pet is sleeping", "Pet is sleeping", JOptionPane.NO_OPTION);
          }
          Cycle.nextCycle(pet);
          Main.action = 3;
          break;
        case 8:
          Inventory.giveMoney(500);
          System.out.println("500 money added. Current balance: " + Inventory.getWallet());
          if(pet.mood == "hungry"){
            pet.mood = "normal";
          } else {
            pet.mood = "hungry";
          }
          gw.updatePetDisplay();
          break;
        default:
          System.out.println("Option "+sel+" is not available.");
          break;
      }
  }  

}
package main;
import java.util.Scanner;
import display.GameWindow;
import display.ShopWindow;
import utils.Lazy;

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
  

  public static void openInventory(){ //TODO - Make inventory class
    final Pet pet = Main.pet;
    final GameWindow gw = Main.gw;

    gw.inventory();
    
    System.out.println("\n\n---------{INVENTORY}---------\nPRESS 0 TO EXIT");
    if(!pet.isEvil){ //if pet isn't evil, display this inventory
      sortInventory();
      int i=0;
      for(i=0; invItems[i] != null && i<invItems.length; i++){
        if(invItems[i]==null){break;}
        System.out.println("  "+(i + 1) + ". " + invItems[i]+" x"+itemInvAmount[i]);
      }
      System.out.print("SELECT: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt(); 
      
      if(input == 0){return;} //return
      String sel = (invItems[(input-1)]);
      switch(sel){
        case "Food":
          pet.feed();
          break;
        case "Medication":
          pet.medicate(false);
          break;
        case "Strong Medication":
          pet.medicate(true);
          break;
        case "Vitamins":
          pet.vitamins();
          break;
        case "Energy Drink":
          pet.energyDrink();
          break;
        case "Incense":
          pet.incense();
        default:
          System.out.println("Unavailable");
          break;
      }
    }else{ //---CRISIS INVENTORY---//
      sortInventory();
      int i=0;
      for(i=0;i<crisisInvItems.length;i++){
        if(crisisInvItems[i]==null){break;}
        System.out.print("  "+(i + 1) + ". " + crisisInvItems[i]+" x"+crisisInvAmount[i]);
        if(Evil.activeWeapon.equals(crisisInvItems[i]) || Evil.activeShield.equals(crisisInvItems[i])){System.out.print(" - Equipped");}
        switch(crisisInvItems[i]){
          case "Armor (lv1)":
            if(Evil.armor == 1){System.out.print(" - Equipped");}
            break;
          case "Armour (lv2)":
            if(Evil.armor == 2){System.out.print(" - Equipped");}
            break;
          case "Armoure (lv3)":
            if(Evil.armor == 3){System.out.print(" - Equipped");}
          default:
            //def
        }
        System.out.println();
      }
      
      System.out.print("SELECT: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt();
      if(input == 0){
        return;
      }
      String sel = crisisInvItems[(input-1)];

      switch (sel){ 
        case "Armor (lv1)":
          if(Evil.armor == 1){ //if already equipped
            System.out.println("Unequipped Armor");
            Evil.armor = 0;
          }else{
            System.out.println("Equipped Armor");
            Evil.armor = 1;
          }
          Lazy.waitForEnter();
          break;
        case "Armour (lv2)":
          if(Evil.armor == 2){ //if already equipped
            System.out.println("Unequipped Armour");
            Evil.armor = 0;
          }else{
            System.out.println("Equipped Armour");
            Evil.armor = 2;
          }
          Lazy.waitForEnter();
          break;
        case "Armoure (lv3)":
          if(Evil.armor == 3){ //if already equipped
            System.out.println("Unequipped Armoure");
            Evil.armor = 0;
          }else{
            System.out.println("Equipped Armoure");
            Evil.armor = 3;
          }
          Lazy.waitForEnter();
          break;
        case "Wood Sword":
          if(Evil.activeWeapon.equals("Wood Sword")){ //if already equipped
            System.out.println("Unequipped Wood Sword");
            Evil.activeWeapon = "none";
          }else{
            System.out.println("Equipped Wood Sword");
            Evil.activeWeapon = "Wood Sword";
          }
          Lazy.waitForEnter();
          break;
        case "Iron Sword":
          if(Evil.activeWeapon.equals("Iron Sword")){ //if already equipped
            System.out.println("Unequipped Iron Sword");
            Evil.activeWeapon = "none";
          }else{
            System.out.println("Equipped Iron Sword");
            Evil.activeWeapon = "Iron Sword";
          }
          Lazy.waitForEnter();
          break;
        case "Shield":
          if(Evil.activeShield.equals("Shield")){
            System.out.println("Unequipped Shield.");
            Evil.activeShield = "none";
          }else{
            System.out.println("Equipped Shield");
            Evil.activeShield = "Shield";
          }
          Lazy.waitForEnter();
          break;
        case "Food Bundle":
          Evil.foodBribe(pet);
          break;
        case "Sedatives":
          Evil.sedate(pet);
          break;
        case "Magic Juice":
          if(Evil.bonusHP>0){
            System.out.println("You cannot recover any more health");
            Lazy.waitForEnter();
          }else{
            Evil.magicJuice(false);
          }
          break;
        case "Magic Super Juice":
          if(Evil.bonusHP>0){
            System.out.println("You cannot recover any more health");
            Lazy.waitForEnter();
          }else{
            Evil.magicJuice(true);
          }
          break;
        default:
          System.out.println("Unavailable"); //Yet to implement: magic juices
      }
    }
  }

  public static void useMenu (int sel){ // TODO - Make this a method in GameWindow plz
    final Pet pet = Main.pet;
    GameWindow gw = Main.gw;

    switch(sel){ //TODO - Edit the cases to start from 0
        case 0: //inventory
          Menu.openInventory();
          break;
        case 1: //store
          ShopWindow.openShop();
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
          pet.feed();
          if (statsOn) {
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
          pet.exercise();
          break;
        case 6: //cuddle pet
          pet.cuddlePet();
          break;
        case 7:
          if (pet.isSleeping) {
            JOptionPane.showMessageDialog(null, "Cannot continue while pet is sleeping", "Pet is sleeping", JOptionPane.NO_OPTION);
          }
          Main.action = 3;
          break;
        case 8:
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

  private static void sortInventory(){
    final Pet pet = Main.pet;
    
    int i;
    if(!pet.isEvil){
      for(i=0; i<invItems.length; i++){
        if(itemInvAmount[i]==0){
          invItems[i] = null;
          break;
        }
      }
      for(int j=i+1; j<invItems.length; j++){
        if(invItems[j] != null){
          invItems[i] = invItems[j]; //move item name down
          itemInvAmount[i] = itemInvAmount[j]; //move amount down
          itemInvAmount[j] = 0; //delete j amount
          invItems[j] = null; //delete j item
          i++;
        }
      }
    }else{
      for(i=0; i<crisisInvItems.length; i++){
        if(crisisInvAmount[i]==0){
          crisisInvItems[i] = null;
          break;
        }
      }
      for(int j=i+1; j<crisisInvItems.length; j++){
        if(crisisInvItems[j] != null){
          crisisInvItems[i] = crisisInvItems[j]; //move item name down
          crisisInvAmount[i] = crisisInvAmount[j]; //move amount down
          crisisInvAmount[j] = 0; //delete j amount
          crisisInvItems[j] = null; //delete j item
          i++;
        }
      }
    }
    
  }

  
}
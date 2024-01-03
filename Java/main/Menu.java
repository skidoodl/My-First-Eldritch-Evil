package main;
import java.util.Scanner;
import display.GameWindow;
import utils.Lazy;

public class Menu{
  public static boolean statsAlert = false;
  public static boolean statsOn = false;

  private static String name = Main.pet.name;
  private static String[] menuOpts = {"Inventory","Store",(name+"'s Stats"),("Feed "+name),("Sleep "+name),("Exercise "+name),("Cuddle with "+name),"Next Day"};
  //-----NORMAL INVENTORY-----//
  public static String[] invItems = new String[10];
  public static String[] shopItems = {"Food", "Medication", "Strong Medication","Vitamins","Energy Drink","Incense"};
  public static int[] itemStock = {100,20,5,25,35,16,0,0,0,0};
  public static int[] itemInvAmount = new int[10];
  public static int[] itemPrice = {/*food*/ 50,/*meds*/ 450,/*strong meds*/ 900,/*vitamins*/200,/*Energy Drink*/230,/*Incense*/410}; // load item prices
  //-----CRISIS INVENTORY-----//
  public static String[] crisisInvItems = new String[10];
  public static String[] crisisShopItems = {"Armor (lv1)", "Armour (lv2)", "Armoure (lv3)","Wood Sword","Iron Sword","Shield","Food Bundle","Sedatives","Magic Juice","Magic Super Juice"};
  public static int[] crisisItemStock = {1,1,1,1,1,1,20,20,15,10};
  public static int[] crisisInvAmount = new int[10];
  public static int[] crisisPrice = {/*Armor*/ 600,/*Armour*/ 950,/*Armoure*/ 1800,/*Wood Sword*/475,/*iron sword*/ 1020,/*Shield*/380,/*food bundle*/ 220, /*sedatives*/375, /*Magic Juice*/450, /*Magic Super Juice*/775}; // load item prices
  
  public static void displayMenu() { //Main Game Menu
    final Pet pet = Main.pet;
    final GameWindow gw = Main.gw;
    

    gw.menuScreen(menuOpts);
    
    if(!pet.isEvil){
      Scanner scan = new Scanner(System.in);

      //Lazy.clearConsole();
      System.out.print("\n\n--------{MY FIRST ELDRITCH EVIL}--------\n  1. Inventory");
      System.out.print("\n  2. Store\n  3. "+name+"'s Stats");
      if(statsAlert){
        System.out.print("  (!)");
      }
      System.out.print("\n  4. Feed " + name + " ");
      for(int i=0; i<itemInvAmount.length; i++){
        if(invItems[i] == null){continue;} //skip the nulls
        if(invItems[i].equalsIgnoreCase("Food")){ //once it finds food
          if(itemInvAmount[i] > 0)
          System.out.print(" ("+itemInvAmount[i]+"x Food)");
          break;
        }
      }
      if (pet.satiety <= 0.25){ //menu alert if pet satiety is low
        System.out.print(" (!)");
      }
      System.out.print("\n  5. Sleep "+name+" ");
      if (pet.energy <= 0.25){ // menu alert if pet energy is low
        System.out.print(" (!)");
      }
      System.out.print("\n  6. Exercise "+name+" ");
      if (pet.exercised<1){
        System.out.print(" (!)");
      }
      System.out.print("\n  7. Cuddle with "+name+"\n  8. Next Day\nSELECT: ");
      int input = scan.nextInt();
      useMenu(input-1);

    }else{
      Evil.crisisMenu(pet);
    }
  }


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

  public static void useMenu (int sel){
    final Pet pet = Main.pet;
    GameWindow gw = Main.gw;

    Scanner scan = new Scanner(System.in);
    switch(sel){ //TODO - Edit the cases to start from 0
        case 0: //inventory
        Menu.openInventory();
        break;
        case 1: //store
          Menu.openShop();
          break;
        case 2: //Check Stats
          if (statsOn) {
            statsOn = false;
            gw.statsVisible(false);
          } else {
            statsAlert = false;
            statsOn = true;
            gw.statsVisible(true);
          }
          break;
        case 3: //feed
          pet.feed();
          break;
        case 4: // sleep

          System.out.print("How many hours do you want "+pet.name+" to sleep? ");
          int hours = scan.nextInt();
          pet.sleep(hours);
          break;
        case 5: //Exercise
          pet.exercise();
          break;
        case 6: //cuddle pet
          pet.cuddlePet();
          break;
        case 7:
          Main.action = 3;
          break;
        default:
          System.out.println("Option "+sel+" is not available.");
          break;
      }
  }

  public static void openShop(){
    final Pet pet = Main.pet;
    
    Scanner scan = new Scanner(System.in);
    if(pet.name.trim().equals("ferg")){
      System.out.println("dev mode");
      int max = Integer.MAX_VALUE;
      pet.money = max;
    }
    System.out.println("\n\n-----------{SHOP}-----------\nWallet: "+pet.money+" mon\n----------------------------\nPRESS 0 TO EXIT");
    if(!pet.isEvil){ //if pet is not evil...
      for (int i = 0; i < shopItems.length; i++){ //load the item list
        if(itemStock[i] > 0){
          System.out.println("  "+(i+1)+". "+shopItems[i]+ " x" + itemStock[i]+"  -  Price: "+itemPrice[i]);
        }
      }
      System.out.print("SELECT: ");
      int sel = scan.nextInt(); //allow user to select item to purchase
      while (sel>11 || sel<0){ //ensure the user makes a selection within the correct range
       sel = scan.nextInt();
      }
      if (sel == 0){
        return;
      }else{
        System.out.print("How many? ");
        int quant = scan.nextInt();
        if(itemStock[(sel - 1)] < quant){
          System.out.println("Not enough stock.");
          Lazy.waitForEnter();
          Menu.openShop();
        }
        if (quant<1){ //if user inputs a quantity less than 1, return them back to the main shopping thing or smth idk
          Menu.openShop();
        }
        int cost = itemPrice[(sel-1)]*quant; //calculate purchase cost
        
        if (cost>pet.money){
          System.out.println("You don't have enough money.");
          Lazy.waitForEnter();
        }else{
          pet.money -= cost; //subtract cost from wallet
          int emptySlot = -1;
          itemStock[(sel-1)] = itemStock[(sel-1)] - quant;
          boolean duplicate = false;
          for(int i = 0; i < invItems.length;i++){ //search for duplicates with invItems and your selection
            if(invItems[i] == shopItems[(sel-1)]){
              duplicate = true;
              emptySlot = i;
              break;
            }
          }
          if(!duplicate){
            for (int i = 0; i < invItems.length; i++) {
              if (invItems[i] == null) {
                emptySlot = i;
                break;
              }
            }
          }
          if(emptySlot != -1){
            invItems[emptySlot] = shopItems[(sel-1)];
          }
          itemInvAmount[emptySlot] = itemInvAmount[emptySlot] + quant;
        }
        return;
      }
    }else{ //----CRISIS SHOP----/
      for (int i = 0; i < crisisShopItems.length; i++){ //load the item list
        if(crisisItemStock[i]>0){
          System.out.println("  "+(i+1)+". "+crisisShopItems[i]+ " x" + crisisItemStock[i]+"  -  Price: "+crisisPrice[i]);
        }
      }
      System.out.print("SELECT: ");
      int sel = scan.nextInt(); //allow user to select item to purchase
  
      while (sel>11 || sel<0){ //ensure the user makes a selection within the correct range
        System.out.print("This input is NO BUENO. Try again fella or gal: ");
        sel = scan.nextInt();
      }
      if (sel == 0){
        return;
      }else{
        int quant;
        if (crisisItemStock[sel-1]==1){
          quant = 1;
        }else{
          System.out.print("How many? ");
          quant = scan.nextInt();
        }
        if(crisisItemStock[(sel - 1)] < quant){
          System.out.println("Not enough stock.");
          Lazy.waitForEnter();
          Menu.openShop();
        }
        if (quant<1){ //if user inputs a quantity less than 1, return them back to the main shopping thing or smth idk
          Evil.crisisMenu(pet);
        }
        int cost = crisisPrice[(sel-1)]*quant; //calculate purchase cost
        
        if (cost>pet.money){
          System.out.println("You don't have enough money.");
          Lazy.waitForEnter();
          Evil.crisisMenu(pet);
        }else{
          pet.money -= cost; //subtract cost from wallet
          int emptySlot = -1;
          crisisItemStock[(sel-1)] -= quant;
          boolean duplicate = false;
          for(int i = 0; i < crisisInvItems.length;i++){ //search for duplicates with invItems and your selection
            if(crisisInvItems[i] == crisisShopItems[(sel-1)]){
              duplicate = true;
              emptySlot = i;
              break;
            }
          }
          if(!duplicate){
            for (int i = 0; i < crisisInvItems.length; i++) {
              if (crisisInvItems[i] == null) {
                  emptySlot = i;
                  break;
              }
            }
          }
          if(emptySlot != -1){
            crisisInvItems[emptySlot] = crisisShopItems[(sel-1)];
          }
          crisisInvAmount[emptySlot] = crisisInvAmount[emptySlot] + quant;
        }
        return;
      }
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
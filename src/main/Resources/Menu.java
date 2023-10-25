import java.util.Scanner;
public class Menu{
  //-----NORMAL INVENTORY-----//
  public static String[] invItems = new String[10];
  public static String[] shopItems = {"Food", "Medication", "Strong Medication","Vitamins"};
  public static int[] itemStock = {100,20,5,25,0,0,0,0,0,0};
  public static int[] itemInvAmount = new int[10];
  public static int[] itemPrice = {/*food*/ 50,/*meds*/ 500,/*strong meds*/ 1200,/*vitamins*/650}; // load item prices
  //-----CRISIS INVENTORY-----//
  public static String[] crisisInvItems = new String[10];
  public static String[] crisisShopItems = {"Armor (lv1)", "Armour (lv2)", "Armoure (lv3)","Wood Sword","Iron Sword","Shield","Food Bundle","Sedatives","Magic Juice","Magic Super Juice"};
  public static int[] crisisItemStock = {1,1,1,1,1,1,20,20,15,10};
  public static int[] crisisInvAmount = new int[10];
  public static int[] crisisPrice = {/*Armor*/ 600,/*Armour*/ 950,/*Armoure*/ 1800,/*Wood Sword*/350,/*iron sword*/ 999,/*Shield*/420,/*food bundle*/ 100, /*sedatives*/350, /*Magic Juice*/450, /*Magic Super Juice*/775}; // load item prices

  public static void displayMenu(Pet pet) { //Main Game Menu
    if(!pet.isEvil){
      Scanner scan = new Scanner(System.in);
      String name = pet.name; 
      //Lazy.clearConsole();
      System.out.print("\n--------[MY FIRST ELDRITCH EVIL]--------\n  1. Inventory\n  2. Go to the store\n  3. Check "+name+"'s Stats\n  4. Feed " + name + "\n  5. Sleep "+name+"\n  6. Exercise "+name+"\n  7. Cuddle with "+name+"\n  8. Next Day\nSELECT: ");
      int input = scan.nextInt();
      switch(input){
        case 1: //inventory
        Menu.openInventory(pet);
        break;
        case 2: //store
          Menu.openShop(pet);
          break;
        case 3: //Check Stats
          pet.checkStats();
          break;
        case 4: //feed
          pet.feed();
          break;
        case 5: // sleep
          System.out.print("How many hours do you want "+pet.name+" to sleep? ");
          int hours = scan.nextInt();
          pet.sleep(hours);
          break;
        case 6: //Exercise
          pet.exercise();
          break;
        case 7: //cuddle pet
          pet.cuddlePet();
          break;
        case 8:
          main.action = 3;
          break;
        default:
          System.out.println("Option "+input+" is not available.");
          break;
      }
    }else{
      Evil.crisisMenu(pet);
    }
  }


  public static void openInventory(Pet pet){
    System.out.println("\n-------{INVENTORY}-------\n-------------------------\nPRESS 0 TO EXIT");
    if(!pet.isEvil){ //if pet isn't evil, display this inventory
      for (int i = 0; i < invItems.length; i++) {
        if(itemInvAmount[i] == 0){
          invItems[i] = null;
        }
        if (invItems[i] != null) {
          System.out.println("  "+(i + 1) + ". " + invItems[i]+" x"+itemInvAmount[i]);
        }
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
        default:
          System.out.println("Unavailable");
          break;
      }
    }else{ //---CRISIS INVENTORY---//
      for (int i = 0; i < invItems.length; i++) {
        if(crisisInvAmount[i] == 0){
          crisisInvItems[i] = null;
        }
        if (crisisInvItems[i] != null) {
          System.out.println("  "+(i + 1) + ". " + crisisInvItems[i]+" x"+crisisInvAmount[i]);
        }
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

  public static void openShop(Pet pet){
    Scanner scan = new Scanner(System.in);
    if(pet.name.trim().equals("ferg")){
      System.out.println("dev mode");
      int max = Integer.MAX_VALUE;
      pet.money = max;
    }
    if(!pet.isEvil){ //if pet is not evil...
      System.out.println("\n\n     [{SHOP}]          Wallet: "+pet.money+" mon\n------------------------------------------");
      for (int i = 0; i < shopItems.length; i++){ //load the item list
        if(itemStock[i] > 0){
          System.out.println("  "+(i+1)+". "+shopItems[i]+ " x" + itemStock[i]+"  -  Price: "+itemPrice[i]);
        }
      }
      System.out.print("  0. Return to Menu\nSELECT: ");
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
          Menu.openShop(pet);
        }
        if (quant<1){ //if user inputs a quantity less than 1, return them back to the main shopping thing or smth idk
          Menu.openShop(pet);
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
      System.out.println("     [{SHOP}]          Wallet: "+pet.money+" mon\n------------------------------------------");
      for (int i = 0; i < crisisShopItems.length; i++){ //load the item list
        if(crisisItemStock[i]>0){
          System.out.println("  "+(i+1)+". "+crisisShopItems[i]+ " x" + crisisItemStock[i]+"  -  Price: "+crisisPrice[i]);
        }
      }
      System.out.print("  0. Return to Menu\nSELECT: ");
      int sel = scan.nextInt(); //allow user to select item to purchase
  
      while (sel>11 || sel<0){ //ensure the user makes a selection within the correct range
        System.out.print("This input is NO BUENO. Try again fella or gal: ");
        sel = scan.nextInt();
      }
      if (sel == 0){
        return;
      }else{
        System.out.print("How many? ");
        int quant = scan.nextInt();
        if(crisisItemStock[(sel - 1)] < quant){
          System.out.println("Not enough stock.");
          Lazy.waitForEnter();
          Menu.openShop(pet);
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
}

package main;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import display.GameWindow;
import utils.Lazy;

import java.awt.Image;
import java.text.DecimalFormat;

public class Pet{
    public int age, difficulty, money, relationship;
    public int evilCount = 0, petDefeats = 0, calming = 0, exercised = 4;
    /*
     * Exercised: Determines how fit pet is
     * Calming: Effect that reduces P(Evil) - # of rounds left of the effect 
     */
    public double satiety; //satiety, or fullness. Range is between 0 and 1, with 0 being starving to death. Energy's range is 0 to 2, 1 being normal and anything above being insanity.
    public double health=1.0, energy = 1.00;
    public String name, difficultyName, mood;
    public boolean isHealthy=true, isEnergized=false, isSleeping=false; //when pet is energized, it loses little to no energy.
    public boolean isEvil, isAlive;
    private DecimalFormat statDF = new DecimalFormat("#.##");
    private DecimalFormat statPctDF = new DecimalFormat("##");

    Random ran = new Random();
    
    
    public Pet(){ //default constructor - set to normal difficulty :D
        isAlive = true;
        difficulty = 1;
        satiety = 0.85;
        Inventory.setWallet(750);
        difficultyName = "Normal";
        mood = "normal";

        // Name Pet
        Scanner scanName = new Scanner(System.in);
        System.out.println("Congrats on your new pet! What will you name it? (9 characters or less)");
        String n = scanName.next().trim();
        while (n.length() > 9) { // make sure name isn't too long
            System.out.print("Too long! Try again: ");
            n = scanName.next();
        }
        while (n.length() < 1) { // make sure name isn't too short
            System.out.print("Invalid name. Try again: ");
            n = scanName.next();
        }
        this.name = n;
        scanName.close();
    }

    public Pet(String name){
        this.name = name;
        isAlive = true;
        difficulty = 1;
        satiety = 0.85;
        Inventory.setWallet(750);
        difficultyName = "Normal";
        mood = "normal";
    }

    public Pet(int difficulty){ //Difficulties: 0=Easy, 1=Normal, 2=Hard, 3=Impossible
        isAlive = true;
        this.difficulty = difficulty;
        switch(difficulty){
            case 0: //easy
                satiety = 1;
                money = 1000;
                difficultyName = "Easy";
                break;
            case 1: //normal
                satiety = 0.85;
                money = 750;
                difficultyName = "Normal";
                break;
            case 2: //hard
                satiety = 0.70;
                money = 500;
                difficultyName = "Hard";
                break;
            case 3: //impossible
                if(ran.nextInt(50)==0){ //2% chance of starting with a dead pet.
                    killPet("miscarriage");
                }else{
                    satiety = 0.6;
                    if(ran.nextInt(4) == 0){ //25% chance of starting w/ evil pet
                        isEvil = true;
                    }
                    if(ran.nextInt(3)==0){ //33% of starting w/ sick pet
                        isHealthy = false;
                    }
                    money = 500;
                    difficultyName = "Impossible";
                }
                break;
            default:
                System.out.println("No clue what happened but something went wrong");
        }
    }

    
    public void killPet(String cause){ // TODO - make this a window
        long startTime = Main.startTime;
        long endTime = System.nanoTime();
        System.out.println("\n\n------------------------------------");
        Grave grave = new Grave();
        grave.printGrave(this.name);
        if (cause.equalsIgnoreCase("pop")){ //popped to death
            System.out.print(this.name+" popped");
            Lazy.hold(1400);
            System.out.print("... Better get a mop.");
        }else if(cause.equals("wasted away")){ //wasted away
            System.out.print(name+" wasted away.");
        }else{
            System.out.print(this.name+" died to "+cause+".");
        }
        long elapsed = (endTime-startTime);
        System.out.println("    -<>-    Time Alive: "+Lazy.timeFormat(elapsed));
        //idea: add a score?
        System.exit(0); //end the program
    }

    public void ageUp(){
        this.age++;
    }
    
    public void namePet(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public void cuddlePet(){
        Grave grave = new Grave();
        int cdl = ran.nextInt(99)+1;
        System.out.println("You decide to cuddle with "+name+".");
        Lazy.hold(1000);
        System.out.println(name+" gives you a look.");
        Lazy.waitForEnter();
        System.out.println("You grab "+name+" in your loving embrace!");
        Lazy.waitForEnter();
        if (cdl <= 10){ //p(10) of killing pet
            System.out.println("You squeeze "+name+" in your arms. You had a really stressful\nday, so this is exactly what you needed.");
            Lazy.waitForEnter();
            System.out.println(name+" squirms in your arms. How cute!");
            Lazy.hold(1000);
            System.out.println("You really hate your boss. You feel all that suppressed anger\n"+
                               "bubbling up in your chest. Luckily, you have "+name+" here to help you\n"+
                               "handle all those negative emotions. You squeeze "+name+" tighter,"+
                               "forcing out all those pent-up emotions.");
            Lazy.waitForEnter();
            System.out.println("Silly little "+name+" lets out an adorable little shriek!");
            Lazy.waitForEnter();
            System.out.println("...");
            Lazy.waitForEnter();
            System.out.println("...");
            Lazy.waitForEnter();
            this.killPet("pop");
        }else{
            System.out.println(name+" shrieks at you, lunging forward. Claws emerge from the creature's\n"+
                              "hands! "+name+" slashes your face. With it's tiny little demon\n"+
                              "teeth, it bites out your left eye. You grow dizzy, and the world turns upside down.");
            Lazy.waitForEnter();
            System.out.print("The world ");
            Lazy.hold(400);
            System.out.print("slowly fades");
            Lazy.hold(400);
            System.out.println(" to the sound of "+name+"'s demonic screeching.");
            Lazy.hold(2400);
            Lazy.waitForEnter();
            grave.playerDeath("loving too much",name);
        }
        Main.action++;
    }

    public void feed(){
        if (sleepBlock()) {
            return;
        }
        String[] invItems = Inventory.getInventoryList();
        if (this.satiety>1.0){ //too full to eat
            JOptionPane.showMessageDialog(null, name+" is too full to eat.", "Feed "+name,JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean foundFood = false;
        // TODO - update to use proper findItem inventory methods. Below is obselete and unneccessary
        for(int i = 0; i < Inventory.getInventoryAmount().length; i++){
            if(invItems[i].equalsIgnoreCase("Food")){ //once it finds food
                int fAmount = Inventory.getInventoryAmount(i);
                foundFood = true;
                System.out.println("Feeding pet...");
                
                ImageIcon imgIcon = new ImageIcon("Resources/MessageIcons/FeedPetIcon.png"); //Get feed message icon
                //Format icon to size:
                Image img = imgIcon.getImage();
                img = img.getScaledInstance(80, 67, java.awt.Image.SCALE_SMOOTH);
                imgIcon = new ImageIcon(img);


                String[] feedOptions = new String[fAmount]; //Initialize the list for the user to choose feed amount
                for (int j=0; j<fAmount; j++){ //populate list with options
                    feedOptions[j] = String.valueOf(j+1);
                }
                //Get amount to be fed:
                Object selected = JOptionPane.showInputDialog(null,"How much food do you want to feed "+name+"?", "Feed "+name,JOptionPane.INFORMATION_MESSAGE, imgIcon,feedOptions, feedOptions[0]);
                int amount=0; //initialize amount variable
                //Get amount:
                if (selected != null) {
                    amount = Integer.parseInt(selected.toString());
                } else {
                    return;
                }

                Inventory.remove("Food", amount);
                
                double feed = (0.15*amount); //Max possible feed: 0.2/food | Min possible feed 0.1/food
                satiety += feed;

                if (satiety > 1.7) {
                    killPet("gastrointestinal perforation");
                }
                
                Main.action++;
                JOptionPane.showMessageDialog(null, "You fed "+name+" "+amount+" food (+"+statDF.format(feed)+" satiety).\nCurrent Satiety: "+statDF.format(satiety), "Feed "+name, JOptionPane.INFORMATION_MESSAGE, imgIcon);
                GameWindow.updateAllPanels();
                return;
            }
        }
        if(!foundFood){
            JOptionPane.showMessageDialog(null, "You have no food in your inventory","Feed "+name,JOptionPane.INFORMATION_MESSAGE);
            //TODO - Add an option to visit shop and buy food
        }
    }

    public void sleep(int hours){
        if (isSleeping) {
            JOptionPane.showMessageDialog(null, "Click " + name + " to wake it.", "Pet is sleeping.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String hrs="";
        if (hours > 1){hrs = "s";} //pluralization

        System.out.println("Sleeping for "+hours+" hour"+hrs+"...");
        String name = this.name;

        switch(this.difficulty){
            case 0:
                // Easy difficulty
                break;
            case 1:
                // Normal Difficulty
                this.energy += (hours*0.093);
                break;
            case 2:
                // Hard Difficulty
                break;
            case 3:System.out.println(name+" is too busy stalking its prey (you) to sleep.");//impossible
        }
        if(this.difficulty!=3){Main.action++;} //since the pet would be able to sleep (ex. impossible diff.), action++
        System.out.println(); //spacer
        if (this.energy>2.0){
                this.killPet("too much sleep");
        }
    }

    public void exercise(){
        if (sleepBlock()) {
            return;
        }
        if (this.exercised>=15){
            this.killPet("protein powder overdose");
        }else if (this.exercised>=10){
            System.out.println(this.name+" is dangerously jacked... is it snorting protein powder?");
            this.energy -= 0.5;
            this.satiety -= 0.5;
            this.exercised += 3;
            Lazy.waitForEnter();
        }else{
            String [] exLoc = {"to the gym","to the park","out on a walk","to a wrestling match","to bootcamp","on a marathon","to train with broadswords"};
            System.out.println("You took "+name+" "+Lazy.ranStringArray(exLoc)+".");
            this.energy -= 0.5;
            this.satiety -= 0.4;
            this.exercised += 5;
            addMoney();
            Lazy.waitForEnter();
        }
        Main.action++;
    }

    // TODO - perhaps make a specific class extending this one for pet utilities?
    public String[] getStatsArray() {
        // Determine length of stats array
        int s = 6; // age, energy, satiation, is healthy, health stat, exercise = 6
        if (isEnergized){s++;}
        if (calming>0){s++;}
        if (evilCount>0){s++;}
        String[] stats = new String[s];
        
        // Age
        stats[0] = name+" is "+age+" year"+Lazy.autoPlural(age)+" old.";
        // Energy
        if (isSleeping) {
            stats[1] = "Energy: --"+name+" is Currently Sleeping--";
        } else {
            stats[1] = "Energy: "+statPctDF.format(energy*100)+"%"; //display as percentage
        }
        // Satiation
        stats[2] = name + " is "+statPctDF.format(satiety*100)+"% full (satiety).";
        // isHealthy
        if (isHealthy) {
            stats[3] = name + " is healthy.";
        } else {
            stats[3] = name + " isn't feeling to good."; //TODO: Create vet system??
        }
        // Health Level
        stats[4] = name + "'s health is at "+statPctDF.format(health*100)+"%";
        // Exercised
        if (exercised <= -4) {
            stats[5] = name + " is obese."; // TODO: Obese pet art?
        } else if (exercised<0){
            stats[5] = name + " is looking kinda chubby. Might wanna hit the gym...";
        } else if (exercised < 7) {
            stats[5] = name + " is looking nice and fit.";
        } else {
            stats[5] = name + " is looking crazy buff."; // TODO - Create buff pet art?
        }
        // Conditional Stats
        if (stats.length > 6) {
            int i = 6;
            // isEnergized
            if (isEnergized) {
                stats[i] = name + " is energized.";
                i++;
            }
            // Calming (Incense effect)
            if (calming > 0) {
                stats[i] = "Incense is calming "+name+" and reducing its chance of\n"+
                           "turning evil. The incense will last for "+calming+" more cycle"+Lazy.autoPlural(calming)+".";
                i++;
            }
            // How many times turned evil, Cycles since evil
            if (evilCount > 0) {
                int cse = Cycle.cyclesSinceEvil;
                stats[i] = name+" has turned evil "+evilCount+" time"+Lazy.autoPlural(evilCount)+".\n"+
                          "It has been "+cse+" cycle"+Lazy.autoPlural(cse)+" since "+name+" was evil.";
                i++;
            }
        }
        return stats;
    }

    public void birthday(){
        this.age++;
        System.out.println("Happy Birthday! "+this.name+" is "+this.age+" years old! (+300 mon)"); //idea: gift feature
        this.money += 300;
    }

    // TO DO - if pet is already healthy...
    // TODO - create seperate class extending this one specifically for item use?
    public void medicate(boolean isStrong){ //could be optimized, but we can do that later
        if (sleepBlock()) {
            return;
        }
        if (isHealthy == true){
            System.out.println(name+" is already healthy!");
            Lazy.waitForEnter();
            return;
        }
        Random ran = new Random(); 
        boolean foundMeds = false;
        if (isStrong){ //strong meds
            for(int j=0; j<Menu.itemInvAmount.length; j++){
                if(Menu.invItems[j] == null){continue;} //skip the nulls
                if(Menu.invItems[j].equalsIgnoreCase("Strong Medication")){ //once it finds medication
                    foundMeds = true;
                    Menu.itemInvAmount[j] -= 1;
                    break;
                }
            }
            if(foundMeds == true){
                if(ran.nextInt(100) < 85){//85% chance
                    this.isHealthy = true;
                    System.out.print("The medication worked! "+this.name+" is already looking... ");
                    Lazy.hold(800);
                    System.out.println("looking...");
                    Lazy.hold(1100);
                    System.out.println(this.name+" is no longer sick.");
                    //addMoney();
                }else{
                    System.out.println("You gave "+this.name+" some medication.");
                    Lazy.waitForEnter();
                    System.out.println("It's not very effective...");
                }
            }else{
                System.out.println("You have no Strong Medicine");
            }
        }else{ //normie meds
            for(int i=0; i<Menu.itemInvAmount.length; i++){
                foundMeds=false;
                if(Menu.invItems[i] == null){continue;} //skip the nulls
                if(Menu.invItems[i].equalsIgnoreCase("Medication") && Menu.itemInvAmount[i]>0){ //once it finds medication
                    foundMeds = true;
                    Menu.itemInvAmount[i] -= 1;
                    break;
                }
            }
            if(foundMeds == true){
                if(ran.nextInt(100) < 30){//30% chance
                    this.isHealthy = true;
                    System.out.print("The medication worked! "+this.name+" is already looking... ");
                    Lazy.hold(800);
                    System.out.println("looking...");
                    Lazy.hold(1100);
                    System.out.println(this.name+" is no longer sick.");
                    //addMoney();
                }else{
                    System.out.println("You gave "+this.name+" some medication.");
                    Lazy.waitForEnter();
                    System.out.println("It's not very effective...");
                }
            }else{
                System.out.println("You have no Medicine");
            }
        }
        Main.action++;
        Lazy.waitForEnter();
    }
    
    public void addMoney(){
        Random ran = new Random();
        this.money += ran.nextInt(101) +50;
    }
    
    public void useVitamins(){
        if (sleepBlock()) {
            return;
        }
        if(!Inventory.isInInventory("Vitamins")) {
            return;
        }

        int invLoc = Inventory.findInventoryLocation("Vitamins");
        int amount = Inventory.getInventoryAmount(invLoc);
        // TODO - Eventually make this use an Inventory Action Panel. Maybe a useItem(String item) method that returns the selected amount or smth idk

        String[] vOptions = new String[amount]; //get drop-down options ready
        for (int i = 0; i < amount; i++) {
            vOptions[i] = String.valueOf(i+1);
        }
        // Get amount to use
        Object selected = JOptionPane.showInputDialog(null,"How many vitamins do you want to give "+name+"?", "Use Vitamins",JOptionPane.INFORMATION_MESSAGE, null, vOptions, vOptions[0]);
        amount = 0; // reusing this
        // Get amount:
        if (selected != null) {
            amount = Integer.parseInt(selected.toString());
        }

        // Use vitamins
        Inventory.remove("Vitamins", amount);
        health += 0.12*amount;
        System.out.println("Giving " + name + " " + amount + " vitamins... health: " + health);

        // Check vitamin effects
        if (this.health>1.5){
            this.killPet("hypervitaminosis");
        }else if(this.health>1.0){
            this.health = 1.0;
            System.out.println(this.name+" has totally recovered!");
            addMoney();
        }else{
            System.out.println(this.name+" is feeling a little better...");
        }
        //TODO - Transfer these outputs to become JOptionPanes

        Main.action++;
        JOptionPane.showMessageDialog(null, "You gave " + name + " " + amount + " vitamin" + Lazy.autoPlural(amount) + ".\nCurrent Satiety: "+statDF.format(satiety), "Use Vitamins", JOptionPane.INFORMATION_MESSAGE);
    }

    public void energyDrink(){
        if (sleepBlock()) {
            return;
        }
        System.out.println();
        if (energy >= 1.6){
            System.out.println(name+" is too energized.");
            return;
        }
        boolean foundED = false;
        for(int j=0; j<Menu.itemInvAmount.length; j++){ //locate Energy Drink
            if(Menu.invItems[j] == null){continue;} //skip the nulls
            if(Menu.invItems[j].equalsIgnoreCase("Energy Drink")){ //once it finds incense
                foundED = true;
                Menu.itemInvAmount[j] -= 1;
                break;
            }
        }
        if(foundED == true){
            System.out.println(name+" drinks the energy drink!");
            energy += 0.6;
            if (energy <= 1.0 && energy >= 0.75){
                System.out.println(name+" got its strength back!");
            }else if (energy > 1.0 && energy <= 1.5){
                System.out.println(name+" is super energized!");
            }else if (energy > 1.5){
                System.out.println(name+" is jittering in a concerning way...");
            }else{
                System.out.println("The energy drink worked.");
            }
            if (energy>=2.0){
                killPet("a heart attack");
            }
            System.out.println("Energy: "+statDF.format(energy));
            isEnergized = true;
            Main.action++;
        }else{
            System.out.println("You have no Energy Drinks.");
        }
        Lazy.waitForEnter();
    }

    public void incense(){
        if(calming > 10){
            System.out.println("The amount of incense in "+name+"'s room is overwhelming. You\nwalk in to place a few more, but lose\nyour vision as your eyes tear up. The scent is overwhelming.\nThrough the thick haze of burning incense, you spot\n"+name+"'s form lying still on the floor...");
            Lazy.waitForEnter();
            killPet("incense asphyxiation");
        }
        boolean foundIncense = false;
        for(int j=0; j<Menu.itemInvAmount.length; j++){ //locate Incense
            if(Menu.invItems[j] == null){continue;} //skip the nulls
            if(Menu.invItems[j].equalsIgnoreCase("Incense")){ //once it finds incense
                foundIncense = true;
                Menu.itemInvAmount[j] -= 1;
                break;
            }
        }
        if (foundIncense == true){
            System.out.println("You place incense in "+name+"'s room.\n"+name+" is feeling calmer now.");
            calming += 4;
        }else{
            System.out.println("You have no incense.");
        }
        Lazy.waitForEnter();
        if(calming >= 8){
            System.out.println("You have placed a dangerously excessive amount of incense in "+name+"'s room...");
            Lazy.waitForEnter();
        }
        Main.action++;
    }

    private boolean sleepBlock () {
        if (isSleeping) {
            JOptionPane.showMessageDialog(null, "This action cannot be performed while " + name + " is sleeping.", "Pet is sleeping.", JOptionPane.NO_OPTION);
            return true;
        }
        return false;
    }

}

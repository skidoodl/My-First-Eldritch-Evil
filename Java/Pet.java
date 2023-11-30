import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Pet{
    public int age, difficulty, money, relationship;
    public int evilCount = 0;
    public int calming = 0; //calming is an effect that reduces pet's evil likelihood. Number corresponds to rounds until calming affect wears off.
    public int petDefeats = 0;
    public int exercised = 4; //determines how fit pet is
    public double satiety, energy; //satiety, or fullness. Range is between 0 and 1, with 0 being starving to death. Energy's range is 0 to 2, 1 being normal and anything above being insanity.
    public double health=1.0;
    public String name, difficultyName, relationshipName;
    public boolean isHealthy=true;
    public boolean isEvil, isAlive;
    public boolean isEnergized=false; //when pet is energized, it loses little to no energy.
    private DecimalFormat statDF = new DecimalFormat("#.##");

    Random ran = new Random();
    public Pet(){ //default constructor
       //gyguyg
    }
    public Pet(int difficulty){ //Difficulties: 0=Easy, 1=Normal, 2=Hard, 3=Impossible
        isAlive = true;
        this.difficulty = difficulty;
        this.energy = 1.00;
        switch(difficulty){
            case 0: //easy
                satiety = 1;
                money = 1000;
                difficultyName = "Easy";
            case 1: //normal
                satiety = 0.85;
                money = 750;
                difficultyName = "Normal";
            case 2: //hard
                satiety = 0.70;
                money = 500;
                difficultyName = "Hard";
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
            default:
                System.out.println("No clue what happened but something went wrong");
        }
    }

    
    public void killPet(String cause){
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
        Scanner scan = new Scanner(System.in);
        String[] invItems = Menu.invItems;
        if (this.satiety>1.0){ //too full to eat
            System.out.println("\n"+name+" is too full to eat.");
            Lazy.waitForEnter();
            return;
        }
        System.out.println("\n----Feed "+name+"----");
        boolean foundFood = false;
        for(int i=0; i<Menu.itemInvAmount.length; i++){
            if(invItems[i] == null){continue;} //skip the nulls
            if(invItems[i].equalsIgnoreCase("Food")){ //once it finds food
                int fAmount = Menu.itemInvAmount[i];
                foundFood = true;
                System.out.print("How much food do you want to feed "+name+"? (x"+fAmount+" food): ");
                int amount=scan.nextInt();
                if(amount<=0){ //0 or less not allowed
                    System.out.println("Not Allowed. Nope. Nuh uh.");
                    Lazy.waitForEnter();
                    return;
                }
                if (amount > Menu.itemInvAmount[i]){ //more than inv amount
                    System.out.println("You ain't got that much food.");
                    Lazy.waitForEnter();
                    return;
                }
                //And then if you have the right amount...
                Menu.itemInvAmount[i] -= amount;
                switch(difficulty){
                    case 0: //easy
                        satiety += (0.25*amount);
                        break;
                    case 1: //norm
                        satiety += (0.1*amount);
                        if(satiety>1.8){killPet("gastrointestinal perforation");}
                        break;
                    case 2: //hard
                        satiety += (0.04*amount);
                        if(satiety>1.1){killPet("gastrointestinal perforation");}
                        break;
                    case 3: //impos.
                        satiety += (0.001*amount);
                        if(satiety>1){killPet("gastrointestinal perforation");}
                        break;
                }
                Main.action++;
                System.out.println("Satiety: "+statDF.format(satiety));
                Lazy.waitForEnter();
                return;
            }
        }
        if(!foundFood){
        System.out.println("You have no food in your inventory");
        Lazy.waitForEnter();
        }
    }

    public void sleep(int hours){
        String hrs="";
        if (hours > 1){hrs = "s";} //pluralization

        System.out.println("Sleeping for "+hours+" hour"+hrs+"...");
        String name = this.name;
        if (this.energy>1.5){ // if already too much energy... - move to in front of "Sleeping for hours"?
            System.out.println(name+" is too energized to sleep.");
            return; //go back to menu
        }else{ //if the energy is less enough, then run
            switch(this.difficulty){
                case 0:
                    this.energy += (hours*0.25);
                    break; //easy  
                case 1:
                    this.energy += (hours*0.1);
                    break; //normal
                case 2:
                    this.energy += (hours*0.02);
                    break; // hard
                case 3:System.out.println(name+" is too busy stalking its prey (you) to sleep.");//impossible
            }
            if(this.difficulty!=3){Main.action++;} //since the pet would be able to sleep (ex. impossible diff.), action++
            System.out.println(); //spacer
        }
        if (this.energy>2.0){
            Scanner scan = new Scanner(System.in);
            System.out.println(name+" has been sleeping for a long time...");
            System.out.println(name+" isn't moving...");
            System.out.print("Poke "+name+"? (y/n): ");
            String yn = scan.nextLine();
            while (!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")) {
                System.out.print("Poke "+name+"? (y/n): ");
                yn = scan.nextLine();
            }
            Lazy.hold(900);//pause for .9 sec
            if (yn.equalsIgnoreCase("y")){
                System.out.println(name+" didn't move. "+name+"'s body feels... cold.");
                Lazy.waitForEnter(); //press enter to continue
                this.killPet("too much sleep");
            }else{
                System.out.println("You walk away from "+name+". No sense in trying to get your face ripped off.");
                Lazy.hold(900); //pause for 0.9s
                System.out.println("You notice a weird smell in the air as you walk away. Hopefully, "+name+" didn't drag in another corpse."); //hopefully this isn't too... problematic
                Lazy.waitForEnter();
                this.killPet("neglect");
            }
        }
        Lazy.waitForEnter();
    }

    public void exercise(){
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
    
    public void checkStats(){
        System.out.println(this.name+"'s Stats:\n------------------------------------------");
        String yrs = Lazy.autoPlural(age);
        System.out.print(name+" is "+age+" year"+yrs+" old.\nDifficulty: "+difficultyName+"\nEnergy: "+statDF.format(energy)+"\nSatiation: "+statDF.format(satiety)+"\n"+name);
        if(isHealthy){
            System.out.println(" is healthy.");
        }else{
            System.out.println(" is sick.");
        }
        System.out.println("Health: "+statDF.format(health));
        if(isEnergized){
            System.out.println(name+" is energized.");
        }
        if (exercised<1){
            System.out.println(name+" is looking kinda chubby. Might wanna hit the gym...");
        }else if (exercised < 7){
            System.out.println(name+" is looking nice and fit.");
        }else{
            System.out.println(name +" is looking crazy buff.");
        }
        if(calming>0){
            String calmin = Lazy.autoPlural(calming);
            System.out.println("Incense is calming "+name+" and reducing its chance of\nturning evil. The incense will last for "+calming+" more cycle"+calmin+".");
        }
        if(evilCount>0){
            int cSE = Cycle.cyclesSinceEvil;
            String ecPlural = Lazy.autoPlural(evilCount);
            String dsePlural = Lazy.autoPlural(cSE);
            System.out.println(name+" has turned evil "+evilCount+"time"+ecPlural+".\nIt has been "+cSE+" cycle"+dsePlural+" since "+name+" was evil.");
        }
        Lazy.waitForEnter();
        return;
    }

    public void birthday(){
        this.age++;
        System.out.println("Happy Birthday! "+this.name+" is "+this.age+" years old! (+300 mon)"); //idea: gift feature
        this.money += 300;
    }

    // TO DO - if pet is already healthy...
    public void medicate(boolean isStrong){ //could be optimized, but we can do that later
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
    
    public void vitamins(){
        Scanner scan = new Scanner(System.in);
        boolean foundVitamins = false;
        int iRef=0;
        for(int i=0; i<Menu.itemInvAmount.length; i++){
            if(Menu.invItems[i] == null){continue;} //skip the nulls
            if(Menu.invItems[i].equalsIgnoreCase("Vitamins")){ //once it finds food
                foundVitamins = true;
                iRef = i;
                break;
            }
        }
        if(foundVitamins == true){
            int amount;
            System.out.println("How many vitamins do you want to give "+this.name+"?");
            amount=scan.nextInt();
            if(amount<=0){ //0 or less not allowed
                System.out.println("Not Allowed. Nope. Nuh uh.");
                Lazy.waitForEnter();
                return;
            }
            if (amount > Menu.itemInvAmount[iRef]){ //more than inv amount
                System.out.println("You do not have that many vitamins");
                Lazy.waitForEnter();
                return;
            }
            Menu.itemInvAmount[iRef] -= amount;
            System.out.println("You feed "+this.name+" "+amount+" vitamin"+Lazy.autoPlural(amount)+".");
            this.health += 0.15*amount;
            Lazy.waitForEnter();
            if (this.health>1.5){
                this.killPet("hypervitaminosis");
            }else if(this.health>1.0){
                this.health = 1.0;
                System.out.println(this.name+" has totally recovered!");
                addMoney();
            }else{
                System.out.println(this.name+" is feeling a little better...");
            }
            Lazy.waitForEnter();
            Main.action++;
        }else{
            System.out.println("You have no vitamins.");
            Lazy.waitForEnter();
        }
    }

    public void energyDrink(){
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
}

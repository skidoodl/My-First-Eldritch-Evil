import java.util.Scanner;
import java.util.Random;

public class Evil{
    public static int playerHP, petHP, maxHP;
    public static int bonusHP = 0;
    public static int prot = 0; //prot = protection level - 0 is not, 1 is a little, 2 is great, 3 is 100%, 4 is parry
    public static String activeWeapon = "none";
    public static String activeShield = "none";
    public static int armor = 0; //may change to a String later
    private static int action=0;
    
    public static void main(Pet pet){
        String name = pet.name;
        pet.evilCount++;
        System.out.println(name+" has turned EVIL!");
        switch(pet.difficulty){//set up!
            case 0: //easy
                playerHP = 200;
                maxHP = 200;
                petHP = 600;
                break;
            case 1: //norm
                playerHP = 150;
                maxHP = 150;
                petHP = 700;
                break;
            case 2: //hard
                playerHP = 100;
                maxHP = 100;
                petHP = 900;
                break;
            case 3: //impos
                playerHP = 70;
                maxHP = 70;
                petHP = 900;
                break;
            default:
        }
        if(pet.evilCount<=1){ //tutorial for EVIL PET
            if(!name.equalsIgnoreCase("evil")){
                playerHP += 15;
                System.out.println("\nPanic fills your body. Nothing like this has ever happened\nbefore! What do you do when your pet turns evil??");
                Lazy.waitForEnter();
                System.out.println(name+"'s eyes glow a fierce red, the color of red jelly beans\ndrenched in magma and covered in blood... "+name+" lunges at you!");
                Lazy.waitForEnter();
                System.out.println("You were distracted by the Lovecraftian beauty of "+name+"'s eyes,\nso you were slow to move. However, you manage to just barely\nroll out of the way of "+name+"'s attack.");
                Lazy.waitForEnter();
                System.out.println("You breath a sigh of relief, but the moment is immediatly cut short as\nthe gravity of the situation hits you like the asteroid that killed the dinosaurs.");
                Lazy.hold(1100);
                System.out.print("Darkness ");
                Lazy.hold(850);
                System.out.print("creeps ");
                Lazy.hold(850);
                System.out.print("into the edges ");
                Lazy.hold(500);
                System.out.println("of your vision...");
                Lazy.hold(1800);
                System.out.print("Looking down, you realize "+name+"'s... ");
                Lazy.hold(650);
                System.out.println("claws slashed your leg open.");
                Lazy.waitForEnter();
                System.out.println("What are you going to do now...?");
                Lazy.waitForEnter();
                petAttackMessage(15,name);
                crisisMenu(pet);
            }
        }
        while(pet.isEvil){ //Evil pet loop
            while(action<1){
                crisisMenu(pet);
            }
            action=0;
            if(!pet.isEvil){break;} //if pet isn't evil...
            petAttack(pet);
        }
    }

    public static void crisisMenu(Pet pet) { //evil pet menu
        String name = pet.name;
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nPLAYER HP: "+(playerHP-bonusHP)); //display player hp
        if (bonusHP>0){
            System.out.print(" + "+bonusHP); // + bonus hp
        }
        System.out.println("   {MENU}   "+name+" HP: "+petHP+"\n-------------------------------------------------------"); //main menu display
        //Pet health bar?
        System.out.println("  1. Inventory\n  2. Shop\n  3. Stats\n  4. Attack"); //menu options
        int input = scan.nextInt();
        switch(input){
            case 1: 
                Menu.openInventory(pet);
                break;
            case 2:
                Menu.openShop(pet);
                break;
            case 3: //stats
                stats(pet);
                break;            
            case 4: //atack
                attack(pet);
                break;
            default:
                System.out.println("Option "+input+" is not available.");
        } 
    }

    public static void petAttackMessage(int damage,String name){
        Grave grave = new Grave();
        System.out.println(name+" attacked! ");
        Lazy.hold(600);
        System.out.println("The attack dealt "+damage+" damage.");
        playerHP -= damage;
        Lazy.waitForEnter();
        if (playerHP <= 0){ //if player DIES
            grave.playerDeath(name,name);
        }
    }

    private static void petAttack(Pet pet){
        Grave grave = new Grave(); //create grave
        String name = pet.name; //get pet name
        Random ran = new Random();
        int atk = ran.nextInt(11)+20; //pet attack value
        atk += (pet.evilCount*6); //more times evil, more attack damage - adjust vals?
        atk += pet.age; //age also somewhat increases attack
        //TO DO: Add to attack: for each .1 energy over 1.0, add 1 damage
        int rando = ran.nextInt(100); //random num for protect calculations
        System.out.println(name+" attacked!");
        switch (armor){
            case 1: //lv1 armor
                atk = (int) (atk*0.85);
                break;
            case 2: //lv2 armour
                atk = (int) (atk*0.70);
                break;
            case 3: //lv3 armoure
                atk = (int) (atk*0.55); // may be too much of a damage decrease - change?
                break;
            default: //no armor
                //none
        }

        switch(prot){ //protection
            case 0: //not protected
                break;
            case 1: //slightly protected
                //10% it totally def, 35% it halfs damage, 55 it fails
                if(rando<20){ //20% chance to totally protect
                    System.out.println("You were totally protected from "+name+"'s attack!");
                    return;
                }else if(rando<55){ //35% it halfs damage
                    System.out.println("Most of "+name+"'s attack was blocked!");
                    atk = atk/2;
                }else{
                    System.out.println("You failed to block "+name+"'s attack.");
                }
                break;
            case 2: //good protection
                //25% it totally def, 45% it halfs damage, 30% it fails
                if(rando<25){ //total protec
                    System.out.println("You were totally protected from "+name+"'s attack!");
                    return;
                }else if(rando<50){ //fail
                    System.out.println("You failed to block "+name+"'s attack.");
                }else{ //halfs damage
                    System.out.println("Most of "+name+"'s attack was blocked!");
                    atk = atk/2;
                }
                break;
            case 3: //Total protection
                if(rando<20){ //20% chance to 1/4 damage
                    System.out.println("Most of "+name+"'s attack was blocked!");
                    atk = atk/4;
                }else{
                    System.out.println("You were totally protected from "+name+"'s attack!");
                }
                break;
            case 4: //parry
                //40% parry, 60% to fail
                if(rando<60){ //fail
                    System.out.println("Your parry failed!\n+10 "+name+"'s attack!");
                    atk += 10;
                }else{ //parry
                    System.out.println("Is that a platypus... PARRY the PLATYPUS??\nYou successfully parried "+name+"'s attack.");
                    int rtdmg = atk+(atk/2);
                    atk = 0;
                    System.out.println(name+" took "+rtdmg+" damage.");
                    petHP -= rtdmg;
                }
        }
        Lazy.hold(600);
        System.out.println("The attack dealt "+atk+" damage.");
        playerHP -= atk;
        Lazy.waitForEnter();
        
        if (playerHP<maxHP){ //handle bonus HP value
            bonusHP = 0;
        }else{
            bonusHP = playerHP - maxHP;
        }
        if (playerHP <= 0){
            grave.playerDeath(name,name);
        }
    }

    private static void attack(Pet pet){
        Random ran = new Random();
        Scanner scan = new Scanner(System.in);
        String[] attackOpts = {"Punch","Block","Strong Slash","Guarded Slash","Sword Block","Shield Gaurd","Shield Parry","Shield Bash"};
        String[] attacks = new String[8];
        int mCnt = 0;
        System.out.println("--------[ATTACK]--------\nActive Weapon: "+activeWeapon+"\nActive Shield: "+activeShield);
        for (int i = 0; i < attackOpts.length; i++) { //print the menu, determine available attack options
            if(activeWeapon.equalsIgnoreCase("none")){ //if no weapon equipped
                if(i>1 && i<5){continue;} //...and i != punch, continue - also prevents from skipping shield-related items
            }else{ //if there is an active weapon...
                if(i<2){continue;}//...skip punch and block
            }
            if(activeShield.equalsIgnoreCase("none")){//if no shield equipped
                if(i>4){continue;} //..and i != block, continue - doesn't skip sword-conditional itmes
            }else{ //if there is an active shield
                if(i<2){continue;}//...skip punch and block
            }
            System.out.println((mCnt+1) + ". " + attackOpts[i]); //display menu option
            attacks[mCnt] = attackOpts[i]; //creates the attacks array
            mCnt++; //increment menuCount
        }
        System.out.print("[Press 0 to Cancel]\nSelect Attack: ");
        int input = scan.nextInt();
        while (input < 0 || input > 7){
            System.out.print("Not a valid option. Try again: ");
            input = scan.nextInt();
        }
        if(input==0){
            return;
        }
        String sel = attacks[input-1]; //used this while trying to fix bugs and i was too lazy to revert things back
        String name = pet.name; //get pet name
        int damage=0; //initialize damage counter
        prot = 0; // set protection to zero

        System.out.println(); //spacer
        /*--ATTACKS--*/
        int rando = ran.nextInt(100);
        if(sel.equalsIgnoreCase("Punch")){
            if(rando<40){ //40% chance of missing
                System.out.print("You swung at "+name);
                Lazy.hold(1000);
                System.out.println("... and missed!");
            }else{
                System.out.println("You punched "+name+"!");
                Lazy.hold(800);
                damage = ran.nextInt(6)+20;
            }
        }else if(sel.equalsIgnoreCase("Block")){
            System.out.println("You raise your wrists to block "+name+"'s next attack!");
            if(rando<40){//40% chance of failure
                Lazy.hold(1000);
                System.out.println("You have terrible form!");
            }else{
                prot=1;
            }
        }else if(sel.equalsIgnoreCase("Strong Slash")){
            if(rando<15){ //15% chance of missing
                System.out.println("You swung... but your sword bounced off "+name+"'s skin!");
            }else{
                System.out.println("You landed hit on "+name+"!");
                damage = ran.nextInt(26)+40;
                if (activeWeapon.equalsIgnoreCase("Wood Sword")){                
                    if(rando<8){
                        System.out.println("It's a critical hit!");
                        damage = (int) (damage*1.35);
                    }
                }else{
                    damage += 5;
                    if(rando<13){
                        System.out.println("It's a critical hit!");
                        damage = (int) (damage*1.5);
                    }else if (rando<30){
                        damage+=5;
                    }
                }
            }
        }else if(sel.equalsIgnoreCase("Guarded Slash")){
            if(rando<35){ //35% chance of missing
                System.out.println("Your swing missed!");
            }else{
                System.out.println("You carefully slash "+name+"!");
                damage = ran.nextInt(36)+15;
                if (activeWeapon.equalsIgnoreCase("Wood Sword")){                
                    if(rando<5){
                        System.out.println("It's a critical hit!");
                        damage = (int) (damage*1.2);
                    }
                }else{
                    damage += 5;
                    if(rando<9){
                        System.out.println("It's a critical hit!");
                        damage = (int) (damage*1.4);
                    }else if (rando<30){
                        damage+=5;
                    }
                
                    if(rando<6){
                    System.out.println("It's a critical hit!");
                    damage = (int) (damage*1.2);
                    }
                }
            }
        }else if(sel.equalsIgnoreCase("Sword Block")){
            System.out.println("You raise your sword in a defensive position");
            if(rando<20){//20% chance of failure
                System.out.println("If you intended to block, you should have put your sword in front of you.");
            }else{
                prot=1;
            }
        }else if(sel.equalsIgnoreCase("Shield Guard")){
            System.out.println("You use your shield... like a shield!");
            if(rando<20){//20% chance of failure
                System.out.println("...Although if you wanted it to work you would have faced TOWARDS "+name+"...");
            }else{
                prot=3;
            }
        }else if(sel.equalsIgnoreCase("Shield Parry")){
            System.out.println("You prepare to parry "+name+"'s attack back at it");
            if(rando<30){//30% chance of failure
                System.out.println("You dropped your shield! You silly goose!");
            }else{
                prot=4; //parry value
            }
        }else if(sel.equalsIgnoreCase("Shield Bash")){
            if(rando<50){ //50% chance of missing
                System.out.println("You failed your bash and landed on your face like a LOSER!\nPride: -15");
            }else{
                System.out.println("You bonk "+name+" with the edge your shield!");
                damage = ran.nextInt(11)+18;
                if(rando<4){
                    System.out.println("It's a critical hit!");
                    damage = (int) (damage*1.35);
                }
            }
        }else{ //something went wrong lol
            return;
        }
        if(damage != 0){System.out.println("Damage: "+damage);}
        petHP -= damage;
        Lazy.waitForEnter();
        Boolean submitted = false;
        rando = ran.nextInt(100);
        if (petHP<=10){
            System.out.println(name+" is basically dead at this point.");
            if (rando < 70){
                submitted = true;
            }
        }else if (petHP <= 35){
            System.out.println(name+" is on the brink of death!");
            if (rando < 50){
                submitted = true;
            }
        }else if (petHP <= 50){
            System.out.println(name+" is pretty badly beat.");
            if (rando < 35){
                submitted = true;
            }
        }else if (petHP <= 100){
            System.out.println("You are close to defeating "+name+"!");
            if (rando < 20){
                submitted = true;
            }
        }else if (petHP <=160){
            if (rando < 15){
                submitted = true;
            }
        }
        if (submitted == true){
            System.out.println("You 'coerced' "+name+" into submission...");
            Lazy.waitForEnter();
            System.out.println(name+" is no longer evil");
            Lazy.hold(800);
            System.out.println("...For now");
            Lazy.waitForEnter();
            pet.isEvil = false;
        }
        action++; //increment action
    }

    public static void foodBribe(Pet pet){
        Random ran = new Random();
        boolean foundBribe = false;
        for(int i=0; i<Menu.crisisInvAmount.length; i++){
            if(Menu.crisisInvItems[i]==null){continue;} //skip nulls
            if(Menu.crisisInvItems[i].equalsIgnoreCase("Food Bribe")){
                foundBribe = true;
                Menu.crisisInvAmount[i] -= 1;
                break;
            }
        }
        if(foundBribe == true){ //if the bibe is found
            System.out.println("You prepare to throw a bundle of food at "+pet.name);
            Lazy.waitForEnter();
            int rando = ran.nextInt(10);
            if (rando<45){ //45% chance of bribe working
                System.out.println("The food flies through the air and lands at "+pet.name+"'s feet.");
                Lazy.waitForEnter();
                System.out.println(pet.name+" sniffs it...");
                Lazy.hold(600);
                System.out.print("takes a bite... ");
                Lazy.hold(800);
                System.out.println(pet.name+" likes it!");
                pet.isEvil = false;
                Lazy.waitForEnter();
                System.out.println(pet.name+" is no longer evil!");
                pet.isEvil = false;
            }else{
                System.out.println("The bribe FAILED.");
            }
            action++;
        }else{
            System.out.println("You have no bribes.");
        }
        Lazy.waitForEnter();
    }

    public static void sedate(Pet pet){
        String name = pet.name;
        Random ran = new Random();
        boolean foundSedative = false;
        for(int i=0; i<Menu.crisisInvAmount.length; i++){
            if(Menu.crisisInvItems[i]==null){continue;} //skip nulls
            if(Menu.crisisInvItems[i].equalsIgnoreCase("Sedatives")){
                foundSedative = true;
                Menu.crisisInvAmount[i] -= 1;
                break;
            }
        }
        if(foundSedative == true){
            int rando = ran.nextInt(100);
            System.out.println("You attempt to give "+name+" sedatives");
            Lazy.waitForEnter();
            if (rando<85){
                System.out.println(name+" begins to look very sleepy...");
                Lazy.hold(750);
                System.out.print(name+"'s eyes begin to...");
                Lazy.hold(600);
                System.out.println("  do SOMETHING equivalent to closing...");
                Lazy.hold(1000);
                Lazy.waitForEnter();
                System.out.println(name+" was successfully sedated");
                pet.isEvil = false;
            }else{
                System.out.println("The sedatives have no effect.");
            }
            action++;
        }else{
            System.out.println("You have no sedatives.");
        }
        Lazy.waitForEnter();
    }

    public static void magicJuice(boolean isSuper){
        Random ran = new Random();
        boolean foundJuice = false;
        if(!isSuper){ //normie juice
            for(int i=0; i<Menu.crisisInvAmount.length; i++){
                if(Menu.crisisInvItems[i]==null){continue;} //skip nulls
                if(Menu.crisisInvItems[i].equalsIgnoreCase("Magic Juice")){
                    foundJuice = true;
                    Menu.crisisInvAmount[i] -= 1;
                    break;
                }
            }
            if(foundJuice==true){
                int rando = ran.nextInt(21);
                int heal = 25 + rando;
                if ((heal + playerHP)>playerHP){ //if heal goes over, BONUS HP
                    bonusHP = (playerHP + heal) - maxHP;
                    if (bonusHP>20){ //max amount of bonus HP for normie heal is 20
                        bonusHP = 20;
                    }
                    playerHP = maxHP + bonusHP;
                    System.out.println("You are at full health! You now have "+bonusHP+" extra HP");
                }else{
                    playerHP += heal; //heal player
                    System.out.println("You recovered "+heal+" health!");
                }
                action++;
            }else{
                System.out.println("You have no Magic Juice.");
            }
        }else{ //super juice
            for(int i=0; i<Menu.crisisInvAmount.length; i++){
                if(Menu.crisisInvItems[i]==null){continue;} //skip nulls
                if(Menu.crisisInvItems[i].equalsIgnoreCase("Magic Super Juice")){
                    foundJuice = true;
                    Menu.crisisInvAmount[i] -= 1;
                    break;
                }
            }
            if(foundJuice==true){
                int rando = ran.nextInt(26);
                int heal = 40 + rando;
                if ((heal + playerHP)>playerHP){ //if heal goes over, BONUS HP
                    bonusHP = (playerHP + heal) - maxHP;
                    if (bonusHP>35){ //max amount of bonus HP for super juice is 35
                        bonusHP = 35;
                    }
                    playerHP = maxHP + bonusHP;
                    System.out.println("You are at full health! You now have "+bonusHP+" extra HP");
                }else{
                    playerHP += heal; //heal player
                    System.out.println("You recovered "+heal+" health!");
                }
                action++;
            }else{
                System.out.println("You have no Magic Super Juice.");
            }
        }
        Lazy.waitForEnter();
    }

    public static void stats(Pet pet){
        String checkShield;
        if (activeShield.equalsIgnoreCase("none")){
            checkShield = "none";
        }else{
            checkShield = "equipped";
        }
        System.out.print("-----------STATS-----------\nYour Health: "+(playerHP-bonusHP));
        if(bonusHP>0){System.out.print(" + "+bonusHP);} //if bonus hp, display
        System.out.println("\nActive Weapon: "+activeWeapon+"\nShield: "+checkShield+"\nArmor Equipped: "+armor+"\n----------"+pet.name+" STATS----------\nHealth: "+petHP+"\n"+pet.name+" has turned evil "+pet.evilCount+" time"+Lazy.autoPlural(pet.evilCount)+".\nTimes Defeated: "+pet.petDefeats);
        Lazy.waitForEnter();
    }
}

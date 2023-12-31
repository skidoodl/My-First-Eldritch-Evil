import java.util.Random;

public class Cycle{
    public static int cycle,cyclesSinceEvil;
    public static void nextCycle(Pet pet){
        cycle++; //cycle counter increase
        System.out.println("\n\n------------------[Cycle "+cycle+"]-----------------");
        String name = pet.name;
        String langs[] = {"Hebrew","Ancient Egyptian","Mandarin","Japanese","English","Pig Latin","Klingon","C++","Breadlish","Japanese","French","Amharic","Russian","Arabic","Giraffe","Sarati","the language of LOVE","to the manager","Vulcan","Sindarin","Newspeak","Hindi","Spanish","Portugese","Turkish","Urdu","Italian","Sign Language","Sugondese"};
        String petActions[] = {name+" cleans its... tentacles.",name+" is growing a third eye.",name+" is growing a fourth eye.","You catch "+name+" plotting an assasination.","You tripped over "+name+" walking down the stairs last night... again...",name+" was named a suspect in murder.",name+"'s teeth are looking extra sharp today!","You got banned from your local PetCo... and dog park... and elementary school.",name+" seems to have taken a liking to ketchup... but where is "+name+" getting it?",name+" fails another DNA test. Maybe it should study next time.",name+"'s BAC is found to be well over the legal limit.",name+" learns to speak "+Lazy.ranStringArray(langs)+".",name+" is learning to speak "+Lazy.ranStringArray(langs)+".",name+"'s new lawyer immediately quits.",name+" quits its job at Spirit Halloween after one day.",name+" thinks MatLab is a real programming language.",name+" put semicolons after all your if statements.",name+" ate your left sock.",name+" ate your right sock.",name+" joined Twitter.",name+" didn't return its shopping cart.",name+" trapped you at an Amy Schumer show.",name+" is running for president. It'll probably win."};
        Random ran = new Random();
        
        if (cycle % 3 == 0 && cycle != 0){pet.birthday();} //age pet up, if necessary
        pet.money+=50;

        int rando = ran.nextInt(99);
        int evilchance;
        if (!pet.isEvil && cycle>4){ //evil pet chances
            int evilCount = pet.evilCount;
            cyclesSinceEvil++; //cycles since evil counter
            if (cyclesSinceEvil<=8){
                evilchance = cyclesSinceEvil+5;
            }else if (cyclesSinceEvil<=14){
                evilchance = (int) (cyclesSinceEvil*1.5);
            }else{
                evilchance = cyclesSinceEvil * 2;
            }
            if(evilCount > 2 && evilCount<6){
                evilchance += 6;
            }else if(evilCount<14){
                evilchance += 15;
            }else{
                evilchance += 22;
            }

            if (pet.energy>1.5){
                evilchance+=20;
            }else if (pet.energy>1.3){
                evilchance+=11;
            }
            evilchance += cycle;

            if (pet.calming > 0 && pet.calming <= 5){
                System.out.println(name+" is feeling calm lately...");
                pet.calming--;
                evilchance = (int) (evilchance*0.65);
            }else if (pet.calming > 5){
                System.out.println(name+" is feeling extremely calm...");
                pet.calming--;
                evilchance = (int) (evilchance*0.4);
            }
            if(pet.name.equals("ferg") || pet.name.equals("evil")){System.out.println("Evil Chance: "+evilchance);}
            if (rando<evilchance || pet.name.equals("evil")){
                pet.isEvil=true;
                return;
            }
        }
        while (pet.exercised>-12 && pet.energy>0 && pet.satiety>0 && pet.health>0){
            pet.exercised--;
            if (pet.exercised<=-4){ //pet fatness
                System.out.println(name+" is obese.");
            }else if(pet.exercised < 0){
                System.out.println(name+" is getting kinda fat.");
            }
            

            rando = ran.nextInt(100);
            switch(pet.difficulty){ //various cycle functions, vary by difficulty
                case 0: //easy
                    if (rando < 20){ //pet hunger
                        pet.satiety -= .08;
                        }else if(rando > 70){
                        pet.satiety -= .02;
                        }else{
                        pet.satiety -= .05;
                    }
                    
                    if(!pet.isEnergized){pet.energy -= .07;} //energy
                    pet.money += 100; //earn money
                    if (rando <4){ //player lose money
                        pet.money = (int) (pet.money*.85);
                        System.out.println(name+" ate some of your money.");
                    }
                    break;
                case 1: //normal
                    if (rando < 30){ //pet hunger
                        pet.satiety -= .18;
                        }else if(rando > 75){
                        pet.satiety -= .06;
                        }else{
                        pet.satiety -= .10;
                    }
                    if(pet.isEnergized){ //energy
                        pet.energy -= .08;
                    }else{
                        pet.energy -= (.06 + (rando*.004));
                    }
                    pet.money += 75; //earn money
                    if (rando <10){//player loses money
                        pet.money = (int) (pet.money*.80);
                        System.out.println(name+" ate some of your money.");
                    }
                    break;
                case 2: //hard
                    if (rando < 35){ //pet hunger
                        pet.satiety -= .28;
                        }else if(rando > 80){
                        pet.satiety -= .10;
                        }else{
                        pet.satiety -= .18;
                    }
                    if(pet.isEnergized){ //energy
                        pet.energy -= .13;
                    }else{
                        pet.energy -= (.08 + (rando*.005));
                    }
                    pet.money += 50; //earn money
                    if (rando <15){//player loses money
                        pet.money = (int) (pet.money*.75);
                        System.out.println(name+" ate some of your money.");
                    }
                    break;
                case 3: //impossible
                    if (rando < 45){ //pet hunger
                            pet.satiety -= .28;
                            }else if(rando > 90){
                            pet.satiety -= .10;
                            }else{
                            pet.satiety -= .18;
                        }
                    if(pet.isEnergized){ //energy
                        pet.energy -= .1;
                    }else{
                        pet.energy -= (.12 + (rando*.004));
                    }
                    pet.money += 30; //earn money
                    if (rando <25){//player loses money
                        pet.money = (int) (pet.money*.70);
                        System.out.println(name+" ate some of your money.");
                    }
                    break;
                default:
            }
            
            

            System.out.println(Lazy.ranStringArray(petActions)); //fun little pet actions
            if (pet.isHealthy){ //health check
                rando = ran.nextInt(99); //get new random num
                if (pet.health<1.0 && rando <=80){ //chance for pet to heal
                    pet.health+=0.16;
                    System.out.println(name+" has been feeling a little better.");
                    if(pet.health>1){pet.health=1;} //pet cannot be healthier than 1
                }
                switch(pet.difficulty){ //pet has a chance of getting sick
                    case 0: //easy
                        if (pet.energy<0.2 || pet.satiety<0.2 || pet.exercised<=-4){
                            if (rando < 25){pet.isHealthy = false;}
                        }else{
                            if (rando<4){pet.isHealthy = false;}
                        }
                        break;
                    case 1: //norm
                        if (pet.energy<0.2 || pet.satiety<0.2 || pet.exercised<=-4){
                            if (rando < 35){pet.isHealthy = false;}
                        }else{
                            if (rando<6){pet.isHealthy = false;}
                        }
                        break;
                    case 2: //hard
                        if (pet.energy<0.4 || pet.satiety<0.3 || pet.exercised<=-4){
                            if (rando < 45){pet.isHealthy = false;}
                        }else{
                            if (rando<10){pet.isHealthy = false;}
                        }
                        break;
                    case 3: //impossible
                        if (pet.energy<0.4 || pet.satiety<0.4 || pet.exercised<=-4){
                            if (rando < 60){pet.isHealthy = false;}
                        }else{
                            if (rando<20){pet.isHealthy = false;}
                        }
                        break;
                }
                if (!pet.isHealthy){
                    System.out.println(name+" is no longer healthy.");
                    Menu.statsAlert = true;
                }
            }else{ //if pet is sick
                rando = ran.nextInt(45);
                if (rando <= 30 && rando != 0){
                    pet.health -= (Double.valueOf(rando)/100);
                }
            }
            if (pet.health < 0.1){ //how pet is feeling
                    System.out.println(name+" is literally about to die.");
                    Menu.statsAlert = true;
                }else if (pet.health <= 0.3){
                    System.out.println(name+" is in the hospital.");
                    Menu.statsAlert = true;
                }else if (pet.health<0.6){
                    System.out.println(name+" is feeling really sick");
            }else if (pet.health<=1 && !pet.isHealthy){
                    System.out.println(name+" has a tummyache.");
            }
            break;
        }

        if (pet.exercised<-12){pet.killPet("morbid obesity");}
        if (pet.health <= 0){pet.killPet("the plague.");}
        if (pet.satiety <=0){pet.killPet("starvation");}
        if (pet.energy <= 0){pet.killPet("wasted away");}

        if(pet.isEnergized){ //chance to lose energized status
            rando = ran.nextInt(5);
            if(rando<2){ //2/5 chance of no longer being energized
                System.out.println(name+" is no longer energized");
            }
        }
        
        //---Pet Hunger Alerts:---//
        double hng = pet.satiety;
        if(hng <0.2){
            System.out.println(name+" is starving...");
        }else if(hng<=0.45){
            System.out.println(name+" really wants to know when dinner is.");
        }else if(pet.satiety<0.6){
            System.out.println(name+" needs a snack.");
        }

        //---Pet Energy Alerts---//
        double en = pet.energy;
        if(en<0.2){
            System.out.println(name+" is dragging itself along the floor");
        }else if(en<=0.45){
            System.out.print(name+" can barely keep its eyes open");
            Lazy.hold(600);
            System.out.println("... not that you're complaining, or anything");
        }else if(en<=0.6){
            System.out.println(name+" needs a nap.");
        }

        //pet find thing//
        double randomValue = ran.nextDouble();
        double probability = .3;
        if (randomValue < probability) {
            int ifFoundItem = ran.nextInt(5);
            int amountFound = 0;
            String[] itemFound = {"Food", "Medication", "Strong Medication", "Vitamins", "money"};
            switch (ifFoundItem) {
                case 0:
                    amountFound = ran.nextInt(11);
                    break;
                case 1:
                    amountFound = ran.nextInt(3);
                    break;
                case 2:
                    amountFound = ran.nextInt(2);
                    break;
                case 3:
                    amountFound = ran.nextInt(3);
                    break;
                case 4:
                    amountFound = ran.nextInt(351);
                    pet.money += amountFound;
                    break;
            }
            if (amountFound>0){
                System.out.println(pet.name + " found " + amountFound + " " + itemFound[ifFoundItem] + "!");
                if (!itemFound[ifFoundItem].equals("money")) {
                    boolean duplicate = false;
                    int emptySlot = -1;
                    for (int i = 0; i < Menu.invItems.length; i++) {
                        if (Menu.invItems[i] != null && Menu.invItems[i].equals(itemFound[ifFoundItem])) {
                            duplicate = true;
                            emptySlot = i;
                            break;
                        }
                    }
                    if (!duplicate) {
                        for (int i = 0; i < Menu.invItems.length; i++) {
                            if (Menu.invItems[i] == null) {
                                emptySlot = i;
                                break;
                            }
                        }
                    }
                    Menu.invItems[emptySlot] = itemFound[ifFoundItem];
                    Menu.itemInvAmount[emptySlot] += amountFound;
                }
            }
        }
        System.out.println();
        Lazy.waitForEnter();

    }
}

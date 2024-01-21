# My First Eldritch Evil
My First Eldritch Evil (may be referred to as MFEE in the following document) is designed to be the worst possible Tamagatchi-style game — in the best possible way. MFEE takes all of the joy out of pet care and puts in all of the frustration of doing your taxes. 

## Description
MFEE is a text-based program where you raise the worst pet ever imaginable. It was created as a submission for the Congressional App Challenge by two idiots in about a month. Embark on a console-based... "experience" where you can raise your very own Lovecraftian creature! Some of the things you can do include:
* Naming your pet
* Feeding your pet
* Exercising your pet
* Cuddling your pet (Note: This option is highly discouraged).
* Healing your pet if it gets sick

Due to your pet's... sinister nature, there is a possibility that it may turn evil. You must endeavor to calm down your pet before it destroys you (and the world). However, you must not kill it because killing animals is bad (even if your's definitely isn't innocent).

[Section Unfinished]
<!-- TO DO: FINISH WRITING THIS SECTION -->

## Storyline
You have been forcefully tasked with taking care of your aunt's horrible pet of unknown origin. Apparently, she bought the egg from Facebook Marketplace?

## Getting Started
### Dependencies
* Powershell
* Bash
* JavaFX

### Installing
* Download the MyFirstEldritchEvil.zip file from the release page, then extract its contents.

### Executing Program
* Windows: Right-click the .ps1 file named "Run This File With Powershell to Play.ps1" and run it with Powershell to play.
* Mac: Double-click the "Run My First Eldritch Evil - Mac" file to run with Terminal.

## Play Guide
### Items - Using the Inventory and Shop
Blabla

### Feeding Your Pet
Just as you need to eat food to stay alive, so too must your pet obtain nutrients by consuming its own... "food." As time goes on, your pet will become hungry. Your pet's hunger level is measured by "Satiation." Low satiation (<1.0) means your pet is hungry. Luckily for you, feeding it is not difficult. Once you purchase food from the shop, it will appear in your inventory. Then, there are two ways to feed it: Either select food from your inventory and then choose "Use" from the list of options it gives you, or select "Feed [Pet Name]" from the main menu. A pop-up will appear where you can then select the amount of food to feed your pet. You can give it as much or as little food as you want, but be careful: feed it too much food and your pet will become unhealthy, or worse, dead.

### Sleeping the Pet
Once you put the pet to sleep, you will see it begin to snooze in the pet display panel. The pet will continue to sleep until woken by the player (clicking on the pet).
<!-- May add more once features get add -->

## Help
If you are having trouble getting this to work, make sure [Java](https://www.java.com/en/) is installed. This is a somewhat buggy mess and may crash. Also, the only way to end the game is to die or press ctrl+c in the command prompt line.

## Authors
* Kline Shimp  
* Aidan Young

## Version History
* 1.0  - Release of the first version
* 1.1 - Evil Fix
   * Fixed initiation bug when pet turned Evil
   * Other bugfixes
   * Minor optimizations
* 1.2 - Mac Release
   * Created Mac release
   * Minor Windows release changes
   * Added Energy Drink and Incense
   * Calming pet now rewards money
   * Improved stats menu
   * Pet now ages up after 4 cycles
   * Cleaned up some UI elements
   * Optimizations
* 1.2.1
   * Critical hits are now possible
   * Adjusted success rates for attacks
   * You can now get rewards in Evil Cycle
   * Guarded slash now works
   * Food bundles now work
   * Cycles since evil should now reset
* 1.2.2
   * Fixed pet death display error
* 1.2.3
   * Pet no longer attacks after player uses health juice
   * Equipped items are now marked in inventory
   * Crisis Items with a stock of 1x no longer prompt how many
   * Typo fixes
   * Adjusted order of events in cycle
   * Earning items in evil cycle should now display message
   * Crisis shop item stock decreases after purchase
   * Magic juice works now
   * Shield Guard now works
   * UI updates
   * Other minor bugfixes
* 1.2.4
   * UI standardization
   * Pet ages every 3 cycles
   * Fixed "Pet Died to Wasted Away"
   * Fixed Gravestone Display Error
   * Added Menu Alerts
   * Inventory item sort method added to solve inventory numbering bug
   * Price adjustments
   * Changed file directory system.

### Graphics Update
* __2.0.0__ (in progress)
   * Added number formatting for stats.
   * Time alive now tracked and displayed on death. (not working)
   * Hatching is much easier now.
   * Removed difficulty - games run on normal.
   * Added start screen.
   * Added menu window with visual pet display.
   * Feeding the pet uses a drop-down menu now.
   * Reworked the pet sleep mechanic.
      * Sleeping the pet actually takes time.
      * Player can perform some actions while pet is sleeping.
   * Added easily togglable stats menu.
   * Added responsive menu GUI.
   * Finally made moods an actual thing.
   * Shopping system completely reworked.
      * Shop opens in seperate window to allow multitasking.
      * Information about items and use now visible (unfinished).
   * Bugfixes

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments
Death screen ASCII art was found [here](https://ascii.co.uk/art/rip).  
Loads of thanks to Mr. C for dealing with us.

<!---- IDK what all this stuff is or what to do with it: ---->

<!--
## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies). 
-->

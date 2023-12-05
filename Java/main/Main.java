package main;
import display.GameWindow;
import utils.GameTimer;

public class Main {
  public static int action;
  public static long startTime;
  public static boolean gameStart = false;

  public static void main(String[] args) {
    GameWindow gw = new GameWindow();

    Pet pet = new Pet(gw.startScreen()); // create pet
    String name = pet.name;

    // Begin Timer
    GameTimer playTimer = new GameTimer();
    playTimer.startTimer();

    System.out.println("Welcome to the world, " + name + "!");
    if (name.equals("evil")) {
      Evil.main(pet);
    }

    while (true) {
      if (pet.isEvil) {
        Evil.main(pet);
      }
      while (action < 2) { // two actions will perform before moving on to cycle.
        Menu.displayMenu(pet,gw);
      }
      action = 0;
      Cycle.nextCycle(pet);
    }
  }
}

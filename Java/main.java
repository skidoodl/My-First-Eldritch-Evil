import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;


public class Main{
  public static int action;

  public static void main(String[] args) {
    System.out.println("\n"); //spacer
    Scanner scan = new Scanner(System.in);
    try{
      final int[] isHatch = {0};
      System.out.println("DIFFICULTY\n------------------------\n  1. Easy\n  2. Normal\n  3. Hard\n  4. Impossible"); //print difficulty menu
      System.out.print("Set Difficulty: ");
      int d = scan.nextInt();
      while (!(d > 0 && d <5)){ //greater than 0, less than 5
        System.out.print("Not an option. Try again: ");
        d = scan.nextInt();
      } 
      Pet pet = new Pet(d-1); //create pet
      
      System.out.println("Enter 1 to Hatch");
      int hatchinput = scan.nextInt();
      if (hatchinput == 1 && isHatch[0] == 0) {
        isHatch[0]++;
      }else{
      System.out.println("Input does not equal 1. Please try again \nEnter 1 to Hatch :egg:");
      hatchinput = scan.nextInt();
      }
      
      if (isHatch[0] == 1){
        Random rand = new Random();
        System.out.println("Congrats on your new pet! What will you name it? (9 characters or less)");
        String name = scan.next().trim();
        while (name.length() > 9){ // make sure name isn't too long
          System.out.print("Too long! Try again: ");
          name = scan.next();
        }
        while (name.length()<1){ //make sure name isn't too short
          System.out.print("Invalid name. Try again: ");
          name = scan.next();
        }
        pet.namePet(name);
        isHatch[0]++;
        System.out.println("Welcome to the world, " + name + "!");
        if(name.equals("evil")){
          Evil.main(pet);
        }        

        while (true){
          if(pet.isEvil){Evil.main(pet);}
          while(action<2){ //two actions will perform before moving on to cycle.
            Menu.displayMenu(pet);
          }
          action=0;
          Cycle.nextCycle(pet);
        }
      }
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Invalid item selection. Please choose a valid item.");
      scan.next();
    }catch(InputMismatchException f){
      System.out.println("Invalid input");
    }
  }
}

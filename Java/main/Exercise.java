package main;


public class Exercise {
    private static boolean proteinPowder = false;

    public static boolean getProteinPowder() {
        return proteinPowder;
    }
    public static void useProteinPowder() {
        proteinPowder = true;
    }
}

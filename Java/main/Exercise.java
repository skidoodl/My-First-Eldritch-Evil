package main;


public class Exercise {
    // Exercise Plans
    private static String[] trainingPlans = {
        "Weight Lifting",
        "Bulking",
        "Weight Loss",
        "Stamina Training",
        "Sprinting",
        "Sparring"
    };
    public static String getPlanString (int plan) {
        return trainingPlans[plan];
    }

    private static boolean[] plansUnlocked = {false, false, false, false, false, false};
    public static int countUnlockedPlans() {
        int x = 0;
        for (int i = 0; i<plansUnlocked.length; i++) {
            if (plansUnlocked[i]) {
                x++;
            }
        }
        return x;
    }
    public static boolean isUnlocked (int plan) {
        try {
            return plansUnlocked[plan];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Attempted to access unknown plan (# = " + plan + ")");
            return false;
        }
    }

    private static Integer selectedPlan;
    public static String getSelectedPlanString() {
        try {
            return trainingPlans[selectedPlan];
        } catch (NullPointerException e) {
            return null;
        }
    }
    public static int getSelectedPlanInt() {
        return selectedPlan;
    }

    public static final int WEIGHT_LIFTING = 0; // trains strenght
    public static final int BULKING = 1; // trains muscle
    public static final int WEIGHT_LOSS = 2; // fat loss
    public static final int STAMINA_TRAINING = 3; // trains stamina
    public static final int SPRINTING = 4; // trains speed
    public static final int SPARRING = 5; // trains attack

    private static boolean proteinPowder = false;

    public static boolean getProteinPowder() {
        return proteinPowder;
    }
    public static void useProteinPowder() {
        proteinPowder = true;
        Inventory.remove("Protein Powder", 1);
    }

    public static void train() { // train without plan

    }
    public static void train(int trainingPlan) {
        switch(trainingPlan) {
            case WEIGHT_LIFTING: tStrength();
            case BULKING: tMuscle();
            case WEIGHT_LOSS: weightLoss();
            case STAMINA_TRAINING: tStamina();
            case SPRINTING: tSpeed();
            case SPARRING: tAttack();
        }
    }

    // training plans
    private static void tStrength() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tStrength'");
    }
    private static void tMuscle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tMuscle'");
    }
    private static void weightLoss() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'weightLoss'");
    }
    private static void tStamina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tStamina'");
    }
    private static void tSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tSpeed'");
    }
    private static void tAttack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tAttack'");
    }
}

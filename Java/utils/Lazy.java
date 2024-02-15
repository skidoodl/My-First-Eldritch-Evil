package utils;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Lazy {
    public static void waitForEnter() {
        System.out.print("Press Enter to Continue");
        Console console = System.console();
        if (console != null) {
            console.readLine(); // Wait for Enter
        } else {
            System.out.println("System console is not available. Press Enter to continue...");
            try {
                System.in.read(); // Wait for Enter
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitForEnter(String message) {
        System.out.print(message);
        Console console = System.console();
        if (console != null) {
            console.readLine(); // Wait for Enter
        } else {
            System.out.println("System console is not available. Press Enter to continue...");
            try {
                System.in.read(); // Wait for Enter
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitForEnter(boolean blankMessage) {
        if (blankMessage) {
            Console console = System.console();
            if (console != null) {
                console.readLine(); // Wait for Enter
            } else {
                System.out.println("System console is not available. Press Enter to continue...");
                try {
                    System.in.read(); // Wait for Enter
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            waitForEnter();
        }
    }

    public static void hold(int time) {
        try {
            Thread.sleep(time); // Pause for (time/1000) seconds
        } catch (InterruptedException e) { // To do - what happens if I get rid of this? Is it necessary?
            // Handle any exceptions that may occur while sleeping
        }
    }

    public static String ranStringArray(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String autoPlural(int n) {
        String plural = "";
        if (n != 1) {
            plural = "s";
        }
        return plural;
    }

    public static int findInArray(String[] array, String item) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            if (array[i].equals(item)) {
                return i;
            }
        }
        System.out.println("\"" + item + "\" could not be found.");
        return i;
    }

    public static int findInArray(int[] array, int integer) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == integer) {
                return i;
            }
        }
        System.out.println("'" + integer + "' could not be found.");
        return i;
    }

    public static boolean isInArray(String[] array, String item) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return true;
            }
        }
        System.out.println("\"" + item + "\" could not be found.");
        return false;
    }

    public static boolean isInArray(int[] array, int integer) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == integer) {
                return true;
            }
        }
        System.out.println("'" + integer + "' could not be found.");
        return false;
    }

    public static String timeFormat(long time) {
        DecimalFormat df = new DecimalFormat("##");
        long s = time / 1_000_000_000; // thats 1 billion
        String tString = "";
        if (s > 86_400) { // day
            double day = s / 86_400;
            s = (long) (s % day);
            tString += (day + ":");
        }
        if (s > 3600) { // hr
            double h = s / 3600;
            s = (long) (s % h);
            tString += (df.format(h) + ":");
        } else if (tString.length() > 0) {
            tString += ("00:");
        }
        if (s > 60) { // min
            double m = s / 60;
            s = (long) (s % m);
            tString += (df.format(m) + ":");
        } else if (tString.length() > 0) {
            tString += ("00:");
        }
        if (s > 0) { // sec
            df = new DecimalFormat("##.##");
            tString += (df.format(s));
        } else if (tString.length() > 0) {
            tString += ("00");
        }
        return tString;
    }

    public static int findMinCell(int[] array) {
        int min = array[0];
        int minCell = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= min)
                continue;
            min = array[i];
            minCell = i;
        }

        return minCell;
    }

    public static int findMinCell(double[] array) {
        double min = array[0];
        int minCell = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= min)
                continue;
            min = array[i];
            minCell = i;
        }

        return minCell;
    }

    public static void printArray(String[] array) {
        System.out.print("Printing array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printArray(String[] array, boolean printNulls) {
        System.out.print("Printing array: [");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null && !printNulls) {
                continue;
            }

            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.println("]");
    }

    public static void playSound(String s) {
        try {
            File soundFile = new File("Resources/sound/"+s);
            System.out.print("Now Playing: Resources/sound/"+s);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
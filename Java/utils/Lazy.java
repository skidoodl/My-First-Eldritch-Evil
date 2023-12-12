package utils;
import java.io.Console;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JOptionPane;

public class Lazy{
    public static void waitForEnter() {
        System.out.print("Press Enter to Continue");
        Console console = System.console();
        if (console != null){
            console.readLine(); // Wait for Enter
        }else{
            System.out.println("System console is not available. Press Enter to continue...");
            try {
                System.in.read(); // Wait for Enter
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void waitForEnter(String message){
        System.out.print(message);
        Console console = System.console();
        if (console != null){
            console.readLine(); // Wait for Enter
        }else{
            System.out.println("System console is not available. Press Enter to continue...");
            try {
                System.in.read(); // Wait for Enter
            }catch (Exception e){
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
        }else{
            waitForEnter();
        }
    }

    public static void hold(int time){
        try{
            Thread.sleep(time); // Pause for (time/1000) seconds
        }catch (InterruptedException e){ //To do - what happens if I get rid of this? Is it necessary?
            // Handle any exceptions that may occur while sleeping
        }
    }

    public static String ranStringArray(String[] array){
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String autoPlural(int n){
        String plural = "";
        if (n != 1){
            plural = "s";
        }
        return plural;
    }

    public static int findInArray(String[] array, String item) {
        int i;
        for(i = 0; i<array.length; i++){
            if (array[i] == item){
                return i;
            }
        }

        System.out.println("\""+item+"\" could not be found.");
        return -1;
    }
    
    public static int findInArray(int[] array, int integer) {
        int i;
        for(i = 0; i<array.length; i++){
            if (array[i] == integer){
                return i;
            }
        }

        System.out.println("\""+integer+"\" could not be found.");
        return -1;
    }

    public static String timeFormat(long time){
        DecimalFormat df = new DecimalFormat("##");
        long s = time/1_000_000_000; //thats 1 billion
        String tString = "";
        if (s>86_400){ //day
            double day = s/86_400;
            s = (long) (s % day);
            tString += (day+":");
        }
        if(s>3600){ //hr
            double h = s/3600;
            s = (long) (s % h);
            tString += (df.format(h)+":");
        }else if(tString.length()>0){
            tString+= ("00:");
        }
        if(s>60){ //min
            double m = s/60;
            s = (long) (s % m);
            tString += (df.format(m)+":");
        }else if(tString.length()>0){
            tString+= ("00:");
        }
        if(s>0){ //sec
            df = new DecimalFormat("##.##");
            tString += (df.format(s));
        }else if (tString.length()>0){
            tString+= ("00");
        }
        return tString;
    }
}
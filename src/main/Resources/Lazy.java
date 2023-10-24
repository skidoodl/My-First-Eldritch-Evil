import java.io.Console;
import java.util.Random;

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
    public static void hold(int time){
        try{
            Thread.sleep(time); // Pause for (time/1000) seconds
        }catch (InterruptedException e){ //To do - what happens if I get rid of this? Is it necessary?
            // Handle any exceptions that may occur while sleeping
        }
    }

    public final static void clearConsole(){ //doesn't work -- find out WHY
        try{
            final String os = System.getProperty("os.name");
            
            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }else{
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
            //  Handle any exceptions.
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
}
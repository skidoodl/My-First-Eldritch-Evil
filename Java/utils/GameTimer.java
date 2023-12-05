package utils;
public class GameTimer {
    private long time,startTime;
    private boolean timerActive = false;

    public GameTimer(){ //default constructor
        time = 0;
    }

    public void startTimer(){
        if (timerActive){
            System.out.println("   >>Could not start game timer because\n"+
                               "     it was already running.\n");
            Lazy.waitForEnter(true);
            return;
        }
        startTime = System.nanoTime();
        timerActive = true;
    }

    public void pauseTimer(){
        if(!timerActive){
            System.out.println("   >>Could not pause game timer because\n"+
                               "     it was already paused.");
            return;
        }
        timerActive = false;
        time += System.nanoTime() - startTime;
    }

    public long getTime(){
        long t = time + (System.nanoTime() - startTime);
        return t;
    }

    public void resetTimer(){
        timerActive = false;
        time = 0;
        startTime = 0;
    }
}

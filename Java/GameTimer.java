public class GameTimer {
    private long ingameTime,allTime,startTime;
    private boolean timerActive = false;

    public GameTimer(){ //default constructor
        ingameTime = 0;
        allTime = 0;
    }

    public void startTimer(){
        if (timerActive){
            System.out.println("   >>Could not start game timer because\n"+
                               "     it was already running.\n");
            Lazy.waitForEnter(true);
            return;
        }
        startTime = System.nanoTime();
    }

    public void pauseTimer(){
        if(!timerActive){
            System.out.println("   >>Could not pause game timer because\n"+
                               "     it was already paused.");
            return;
        }
        timerActive = false;
        ingameTime += System.nanoTime() - startTime;
    }

    public void pauseTimer(boolean pauseAllTime){
        //
    }

    public void stopTimer(){
        //
    }

    public void getActiveTime(){
        //
    }

    public void getAllTime(){
        //
    }
}

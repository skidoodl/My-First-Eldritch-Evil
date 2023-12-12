package utils;

import javax.swing.JOptionPane;

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
            //TODO - Transfer to seperate Alerts class (keeping this here for now though)
            JOptionPane.showMessageDialog(null,"Could not start game timer because it was already running.","Game Timer Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        startTime = System.nanoTime();
        timerActive = true;
    }

    public void pauseTimer(){
        if(!timerActive){
            System.out.println("   >>Could not pause game timer because\n"+
                               "     it was already paused.");
            //TODO - Transfer to seperate Alerts class (keeping this here for now though)
            JOptionPane.showMessageDialog(null,"Could not pause game timer because it was already paused.","Game Timer Error",JOptionPane.ERROR_MESSAGE);
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

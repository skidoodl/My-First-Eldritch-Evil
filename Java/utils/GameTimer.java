package utils;

import javax.swing.JOptionPane;

public class GameTimer {
    private long time,startTime;
    private boolean isActive = false;

    public GameTimer(){ //default constructor
        time = 0;
    }

    public void startTimer(){
        if (isActive){
            JOptionPane.showMessageDialog(null,"Could not start game timer because it was already running.","Game Timer Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        startTime = System.nanoTime();
        isActive = true;
    }

    public void pauseTimer(){
        if(!isActive){
            JOptionPane.showMessageDialog(null,"Could not pause game timer because it was already paused.","Game Timer Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        isActive = false;
        time += System.nanoTime() - startTime;
    }

    public long getTime(){
        long t = time + (System.nanoTime() - startTime);
        return t;
    }

    public void resetTimer(){
        isActive = false;
        time = 0;
        startTime = 0;
    }

    public boolean isActive() {
        return isActive;
    }
}

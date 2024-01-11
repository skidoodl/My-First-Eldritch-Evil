package display.petPanels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import main.Main;
import utils.Lazy;

public class PetSleep extends PetPanel {
    private int sleepLength;
    private JProgressBar sleepBar;
    private Timer sleepTimer;

    public PetSleep() {
        pet.isSleeping = true;
        petLabel.setIcon(new ImageIcon("Resources/PetGraphics/SleepingPet.png"));


        sleepBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 25);
        sleepBar.setValue(++sleepLength);
        sleepBar.setString("Sleeping...");
        sleepBar.setStringPainted(true);
        add(sleepBar, BorderLayout.SOUTH);

        sleepTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleepBar.setValue(++sleepLength);
                sleepBar.setString("Sleeping " + String.valueOf(sleepLength-1) + " hour" + Lazy.autoPlural(sleepLength-1) + "...");
            }

        });
        sleepTimer.start();

        
    }

    protected void petLabelClick() {
        sleepTimer.stop();
        pet.isSleeping = false;
        Main.gw.endSleep();
        pet.sleep(sleepLength-1);
    }
}

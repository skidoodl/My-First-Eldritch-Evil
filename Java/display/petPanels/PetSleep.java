package display.petPanels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import main.Main;

public class PetSleep extends PetPanel {
    private int sleepLength;
    private JProgressBar sleepBar;
    Timer sleepTimer;

    public PetSleep() {
        pet.isSleeping = true;
        petLabel.setIcon(new ImageIcon("Resources/PetGraphics/SleepingPet.png"));

        sleepBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 24);
        sleepBar.setValue(sleepLength);
        add(sleepBar, BorderLayout.SOUTH);

        sleepTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleepLength++;
                sleepBar.setValue(sleepLength);
            }

        });
        sleepTimer.start();
    }

    protected void petLabelClick() {
        sleepTimer.stop();
        Main.gw.endSleep();
        System.out.println("sleepLength: "+sleepLength);
        System.out.println("W A K E   U P");
        pet.isSleeping = false;
        pet.sleep(sleepLength);
    }
}

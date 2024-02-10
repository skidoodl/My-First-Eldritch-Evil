package display.petPanels;

import java.awt.BorderLayout;
import java.awt.Cursor;
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
        petLabel.setIcon(new ImageIcon("Resources/petGraphics/SleepingPet.png"));


        sleepBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 25);
        sleepBar.setValue(++sleepLength);
        //TODO - Finish below commented code
        // sleepBar.setBounds(x, y, length, height);
        // sleepBar.setForeground(Color.purple);
        // TODO - create function that changes color of the bar
        /* Color of bar can change based on how much energy the sleeping function will provide
         * and some other stuff too, I hope. */
        sleepBar.setString("Sleeping...");
        sleepBar.setStringPainted(true);
        add(sleepBar, BorderLayout.SOUTH);

        sleepTimer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleepBar.setValue(++sleepLength);
                sleepBar.setString("Sleeping " + String.valueOf(sleepLength-1) + " hour" + Lazy.autoPlural(sleepLength-1) + "...");
            }

        });
        sleepTimer.start();

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    protected void petLabelClick() {
        sleepTimer.stop();
        pet.isSleeping = false;
        Main.gw.endSleep();
        pet.sleep(sleepLength-1);
    }
}

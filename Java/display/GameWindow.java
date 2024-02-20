package display;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import display.frames.GameFrame;
import display.frames.InventoryFrame;
import display.mainPanels.ExerciseIconsPanel;
import display.mainPanels.ExercisePanel;
import display.mainPanels.MenuPanel;
import display.mainPanels.StatsPanel;
import display.mainPanels.TrainingPlansList;
import display.petPanels.PetPanel;
import display.petPanels.PetSleep;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.Lazy;

public class GameWindow {
    private static final CountDownLatch latch = new CountDownLatch(1);
    
    private static GameFrame gFrame;
    InventoryFrame iFrame;
    boolean gameStart;

    private static boolean statsOn = false;

    // PANELS //
    private static MenuPanel menuPanel;
    private static StatsPanel sPanel;
    private static PetPanel petPanel;
    private static PetSleep sleepPanel;
    private static JPanel gameFeed;

    public GameWindow() {
        gFrame = new GameFrame();
        gFrame.setVisible(true);
    }

    public String startScreen() {
        ImageIcon bg = new ImageIcon("Resources/startScreen/StartScreen.jpg");

        JLabel label = new JLabel();
        label.setIcon(bg);

        JButton b = new JButton(new ImageIcon("Resources/startScreen/StartButton.png"));
        b.setBounds(380, 535, 200, 73);
        b.setFocusable(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameStart = true;
                // Sound temporarily disabled cuz it was ANNOYING - just remove the if statement to make it work
                /* if (!gameStart)  */Lazy.playSound("shutup.wav");
            }

        });

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 960, 720);
        panel.add(label);

        gFrame.add(b);
        gFrame.add(panel);
        gFrame.revalidate();
        gFrame.repaint();

        while (!gameStart) { // wait for the button to be clicked
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }

        // Naming function will go here
        String name = "Bread";

        gFrame.remove(b);
        gFrame.remove(panel);
        return name;
    }

    public void menuScreen() {

        // MENU PANEL
        menuPanel = new MenuPanel();

        // PET DISPLAY PANEL
        petPanel = new PetPanel();

        // MFEE FEED
        gameFeed = new JPanel(); // Where the game feed is displayed
        gameFeed.setBackground(Color.gray);
        gameFeed.setBounds(480, 360, 480, 380);

        // Add Panels
        gFrame.add(menuPanel);
        gFrame.add(petPanel);
        gFrame.add(gameFeed);
        gFrame.revalidate();
        gFrame.repaint();
        gFrame.setVisible(true);

        try { // wait for mouse click
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupted");
        }
    }
    public static void signalMenuScreen() {
        latch.countDown();
    }

    public static boolean statsVisible(boolean visible) { 
        boolean isSleeping = main.Main.pet.isSleeping;
        if (visible) {
            sPanel = new StatsPanel();
            if (isSleeping) {
                gFrame.remove(sleepPanel);
            } else {
                gFrame.remove(petPanel);
            }
            gFrame.add(sPanel);
            gFrame.revalidate();
            gFrame.repaint();
            gFrame.setVisible(true);
        } else {
            gFrame.remove(sPanel);
            if (isSleeping) {
                gFrame.add(sleepPanel);
            } else {
                gFrame.add(petPanel);
            }
            gFrame.revalidate();
            gFrame.repaint();
        }
        return visible;
    }
    
    public static void toggleStats() {
        if (statsOn) {
            statsOn = false;
            GameWindow.statsVisible(false);
        } else {
            statsOn = true;
            GameWindow.statsVisible(true);
        }
    }

    //---EXERCISE---//
    private static boolean exercisePanelOpen;
    private static ExercisePanel exercisePanel = new ExercisePanel();
    private static ExerciseIconsPanel exerciseIcons = new ExerciseIconsPanel();
    private static TrainingPlansList plansPanel = new TrainingPlansList();

    public static void toggleExercisePanel() {
        if (exercisePanelOpen) {
            System.out.println("Removing Exercise Panels");
            gFrame.remove(exercisePanel);
            gFrame.remove(exerciseIcons);
            exercisePanelOpen = false;
            if (ExercisePanel.isPlansOpen()) showExercisePlans(false);
            gameFeed.setVisible(true);
            gFrame.revalidate();
            gFrame.repaint();
            return;
        }
        
        System.out.println("Adding exercise panels");
        exercisePanelOpen = true;
        gFrame.add(exerciseIcons);
        gFrame.add(exercisePanel);
        exerciseIcons.update();
        if (ExercisePanel.isPlansOpen()) showExercisePlans(true);
        gameFeed.setVisible(false);
        updateAllPanels();
    }

    public static void showExercisePlans(boolean b) {
        if (b) {
            gFrame.remove(petPanel);
            plansPanel.update();
            gFrame.add(plansPanel);
        } else {
            gFrame.remove(plansPanel);
            gFrame.add(petPanel);
        }
        gFrame.revalidate();
        gFrame.repaint();
        updateAllPanels();
        exercisePanel.update();
    }


    //---SLEEP---//
    public void petSleep() { // TODO - Convert this to static
        sleepPanel = new PetSleep();
        menuPanel.update();
        gFrame.remove(petPanel);
        gFrame.add(sleepPanel);
        gFrame.revalidate();
        gFrame.repaint();
    }

    public void endSleep() {
        System.out.println("End sleep");
        gFrame.remove(sleepPanel);
        petPanel.updateDisplay();
        gFrame.add(petPanel);
        menuPanel.update();
        gFrame.revalidate();
        gFrame.repaint();
    }

    public void setGameFrameVisible(boolean setVis) {
        gFrame.setVisible(setVis);
    }

    public void updatePetDisplay() {
        petPanel.updateDisplay();
    }

    public static void updateAllPanels() {
        System.out.print("Updating All Panels... ");
        petPanel.updateDisplay();
        menuPanel.update();
        if (exercisePanelOpen) {
            exercisePanel.update();
        }
        if (sPanel != null) {
            sPanel.update();
        }
        gFrame.revalidate();
        System.out.println("Done.");
    }

    public static void updateStats() {
        sPanel.update();
    }

}

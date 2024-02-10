package display;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import display.frames.GameFrame;
import display.frames.InventoryFrame;
import display.mainPanels.ExerciseIconsPanel;
import display.mainPanels.ExercisePanel;
import display.mainPanels.MenuPanel;
import display.mainPanels.StatsPanel;
import display.petPanels.PetPanel;
import display.petPanels.PetSleep;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GameWindow {
    private static GameFrame gFrame;
    InventoryFrame iFrame;
    boolean gameStart;
    public boolean statsVisible = false;
    private static MenuPanel menuPanel;
    private static StatsPanel sPanel;
    private static PetPanel petPanel;
    private PetSleep sleepPanel;
    

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
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                gameStart = true;
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
        String name="Bread";

        gFrame.remove(b);
        gFrame.remove(panel);
        return name;
    }

    public void menuScreen() {
        
        //MENU PANEL
        menuPanel = new MenuPanel();    

        //PET DISPLAY PANEL
        petPanel = new PetPanel();

        //MFEE FEED
        JPanel gameFeed = new JPanel(); // Where the game feed is displayed
        gameFeed.setBackground(Color.gray);
        gameFeed.setBounds(480, 360, 480, 380);
        
        // Add Panels
        gFrame.add(menuPanel);
        gFrame.add(petPanel);
        gFrame.add(gameFeed);
        gFrame.revalidate();
        gFrame.repaint();
        gFrame.setVisible(true);

        try { //wait for mouse click
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean statsVisible (boolean visible) { // TODO - adjust so stats can be viewed while pet sleeping
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

    public void petSleep () {
        sleepPanel = new PetSleep();
        menuPanel.update();
        gFrame.remove(petPanel);
        gFrame.add(sleepPanel);
        gFrame.revalidate();
        gFrame.repaint();
    }

    private static boolean exercisePanelOpen;
    private static ExercisePanel exercisePanel = new ExercisePanel();
    private static ExerciseIconsPanel exerciseIcons = new ExerciseIconsPanel();
    public static void toggleExercisePanel() {
        if (exercisePanelOpen) {
            System.out.println("Removing Exercise Panels");
            gFrame.remove(exercisePanel);
            gFrame.remove(exerciseIcons);
            exercisePanelOpen = false;
            gFrame.revalidate();
            gFrame.repaint();
            return;
        }
        System.out.println("Adding exercise panels");
        exercisePanelOpen = true;
        gFrame.add(exerciseIcons);
        gFrame.add(exercisePanel);
        exerciseIcons.update();
        updateAllPanels();
    }

    public void endSleep () {
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

    public static void updateAllPanels () {
        System.out.print("Updating All Panels... ");
        petPanel.updateDisplay();
        menuPanel.update();
        exercisePanel.update();
        if (sPanel != null) {
            sPanel.update();
        }
        gFrame.revalidate();
        System.out.println("Done.");
    }

    public void updateStats () {
        sPanel.update();
    }

}

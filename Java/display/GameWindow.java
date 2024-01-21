package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import display.frames.GameFrame;
import display.frames.InventoryFrame;
import display.mainPanels.MenuPanel;
import display.mainPanels.StatsPanel;
import display.petPanels.PetPanel;
import display.petPanels.PetSleep;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GameWindow {
    GameFrame gFrame;
    InventoryFrame iFrame;
    boolean gameStart;
    public boolean statsVisible = false;
    private MenuPanel menuPanel;
    private StatsPanel sPanel;
    private PetPanel petPanel;
    private PetSleep sleepPanel;
    

    public GameWindow() {
        gFrame = new GameFrame();
        gFrame.setVisible(true);
    }

    public String startScreen() {
        ImageIcon bg = new ImageIcon("Resources/StartScreen/StartScreen.jpg");

        JLabel label = new JLabel();
        label.setIcon(bg);

        JButton b = new JButton(new ImageIcon("Resources/StartScreen/StartButton.png"));
        b.setBounds(380, 535, 200, 73);
        b.setFocusable(true);
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
        gameFeed.setBounds(480, 360, 480, 360);
        
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

    public void endSleep () {
        System.out.println("End sleep");
        gFrame.remove(sleepPanel);
        petPanel.updateDisplay();
        gFrame.add(petPanel);
        menuPanel.update();
        gFrame.revalidate();
        gFrame.repaint();
    }

    public void inventory(){
        iFrame = new InventoryFrame();


        JPanel invContents = new JPanel(); // Displays items in player's inventory
        invContents.setBackground(Color.black);
        invContents.setBounds(0, 0, 480, 680);

        JPanel monInfo = new JPanel(); // Displays player's wallet info
        monInfo.setBackground(Color.red);
        monInfo.setBounds(0,680,480,40);

        JPanel itemInfo = new JPanel(); // Displays info about items or, if not item is selected, tips or other game-related info
        itemInfo.setBackground(Color.green);
        itemInfo.setBounds(480,0,480,720);

        // Add Panels
        iFrame.add(invContents);
        iFrame.add(monInfo);
        iFrame.add(itemInfo);
        iFrame.revalidate();
        iFrame.repaint();
        iFrame.setVisible(true);

    }

    public void setGameFrameVisible(boolean setVis) {
        gFrame.setVisible(setVis);
    }

    public void updatePetDisplay() {
        petPanel.updateDisplay();
    }

    public void updateAllPanels () {
        petPanel.updateDisplay();
        menuPanel.update();
        sPanel.update();
    }

    public void updateStats () {
        sPanel.update();
    }
}

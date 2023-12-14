package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import display.mainPanels.MenuPanel;
import display.mainPanels.StatsPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameWindow {
    GameFrame gFrame;
    InventoryFrame iFrame;
    boolean gameStart;
    public boolean statsVisible = false;
    private StatsPanel sPanel;
    private JPanel petDisplay;

    public GameWindow() {
        gFrame = new GameFrame();
        gFrame.setVisible(true);
    }

    public String startScreen() {
        ImageIcon bg = new ImageIcon("Resources/StartScreen/StartScreen.jpg");

        JButton b = new JButton(new ImageIcon("Resources/StartScreen/StartButton.png"));
        b.setBounds(300, 475, 200, 73);
        b.setFocusable(true);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Unimplemented method
                // 'actionPerformed'");
                gameStart = true;
            }

        });

        JLabel label = new JLabel();
        label.setIcon(bg);

        JPanel panel = new JPanel();
        panel.setBounds(gFrame.getBounds());
        panel.add(label);
        gFrame.add(panel);
        gFrame.add(b);
        gFrame.setVisible(true);

        while (!gameStart) { // wait for the button to be clicked
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }

        // Naming function will go here
        String name="Bread";

        gFrame.remove(panel);
        gFrame.remove(b);
        return name;
    }

    public void menuScreen(String opts[]) {
        
        //MENU PANEL
        MenuPanel mPanel = new MenuPanel(opts);

        //PET DISPLAY PANEL
        //TODO - I added two graphics for pet into the resources folder - ready to implement
        petDisplay = new JPanel(); // Where the pet gets displayed
        petDisplay.setBackground(Color.white);
        petDisplay.setBounds(480, 0, 480, 360);

        //MFEE FEED
        JPanel feedP = new JPanel(); // Where the game feed is displayed
        feedP.setBackground(Color.gray);
        feedP.setBounds(480, 360, 480, 360);
        
        // Add Panels
        gFrame.add(mPanel);
        gFrame.add(petDisplay);
        gFrame.add(feedP);
        gFrame.revalidate();
        gFrame.repaint();
        gFrame.setVisible(true);
    }

    public void statsVisible (boolean b) {
        if (b) {
            sPanel = new StatsPanel();
            gFrame.remove(petDisplay);
            gFrame.add(sPanel);
            gFrame.revalidate();
            gFrame.repaint();
            gFrame.setVisible(true);
        } else {
            gFrame.remove(sPanel);
            gFrame.revalidate();
            gFrame.repaint();
        }
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
}

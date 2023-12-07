package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameWindow {
    GameFrame gFrame;
    GameFrame iFrame;
    boolean gameStart;

    public GameWindow() {
        gFrame = new GameFrame();
        gFrame.setVisible(true);
    }

    public String startScreen() { //TODO - Frame got sized up, adjust contents to fit new dimensions
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

    public void menuScreen() {
        gFrame.revalidate();
        gFrame.repaint();
        
        JPanel menuP = new JPanel(); // Where the menu sits
        menuP.setBackground(Color.black);
        menuP.setBounds(0, 0, 400, 300);
        menuP.setLayout(null); // idk what this is about but we'll see

        JPanel petDisp = new JPanel(); // Where the pet gets displayed
        petDisp.setBackground(Color.white);
        petDisp.setBounds(400, 0, 400, 300);
        petDisp.setLayout(null); // idk what this is about but we'll see

        JPanel feedP = new JPanel(); // Where the game feed is displayed
        feedP.setBackground(Color.gray);
        feedP.setBounds(0, 300, 800, 300);
        feedP.setLayout(null); // idk what this is about but we'll see

        // Add Panels
        gFrame.add(menuP);
        gFrame.add(petDisp);
        gFrame.add(feedP);
        gFrame.setVisible(true);
    }

    public void inventory(){
        if(iFrame == null){
            iFrame = new GameFrame("Inventory");
        }

    }

    public void setGameFrameVisible(boolean setVis) {
        gFrame.setVisible(setVis);
    }
}

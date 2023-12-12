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
        JPanel petDisp = new JPanel(); // Where the pet gets displayed
        petDisp.setBackground(Color.white);
        petDisp.setBounds(480, 0, 480, 360);
        //petDisp.setLayout(null); // idk what this is about but we'll see


        //MFEE FEED
        JPanel feedP = new JPanel(); // Where the game feed is displayed
        feedP.setBackground(Color.gray);
        feedP.setBounds(480, 360, 480, 360);
        //feedP.setLayout(null); // idk what this is about but we'll see

        
        // Add Panels
        gFrame.add(mPanel);
        gFrame.add(petDisp);
        gFrame.add(feedP);
        gFrame.revalidate();
        gFrame.repaint();
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

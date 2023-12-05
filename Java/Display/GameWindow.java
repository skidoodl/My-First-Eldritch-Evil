package Display;

import java.awt.Color;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameWindow {
    MyFrame frame;

    public GameWindow() {
        frame = new MyFrame();
        frame.setVisible(true);
    }

    public void startScreen() {
        ImageIcon bg = new ImageIcon("Resources/StartScreen/StartScreen.jpg");

        JButton b = new JButton(new ImageIcon("Resources/StartScreen/StartButton.png"));
        b.setBounds(300, 475, 200, 73);
        b.setFocusable(true);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Unimplemented method
                // 'actionPerformed'");
                main.Main.gameStart=true;
            }

        });

        JLabel label = new JLabel();
        label.setIcon(bg);

        JPanel panel = new JPanel();
        panel.setBounds(frame.getBounds());
        panel.add(label);
        frame.add(panel);
        frame.add(b);
        frame.setVisible(true);
    }

    public void menuScreen() {
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
        frame.add(menuP);
        frame.add(petDisp);
        frame.add(feedP);
        frame.setVisible(true);
    }

    public void setVisible(boolean setVis) {
        frame.setVisible(setVis);
    }
}

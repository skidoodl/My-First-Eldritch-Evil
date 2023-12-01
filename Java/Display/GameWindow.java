package Display;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GameWindow {
    MyFrame frame;

    public GameWindow(){
        frame = new MyFrame();
        frame.setVisible(true);
        //default constructor
    }

    public void startScreen(){
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("Resources/StartScreen.jpg");
        label.setIcon(img);
        frame.setVisible(true);
    }

    public void menuScreen(){
        JPanel menuP = new JPanel(); //Where the menu sits
        menuP.setBackground(Color.black);
        menuP.setBounds(0, 0, 400, 300);
        menuP.setLayout(null); //idk what this is about but we'll see

        JPanel petDisp = new JPanel(); //Where the pet gets displayed
        petDisp.setBackground(Color.white);
        petDisp.setBounds(400, 0, 400, 300);
        petDisp.setLayout(null); //idk what this is about but we'll see

        JPanel feedP = new JPanel(); //Where the game feed is displayed
        feedP.setBackground(Color.gray);
        feedP.setBounds(0, 300, 800, 300);
        feedP.setLayout(null); //idk what this is about but we'll see

        //Add Panels
        frame.add(menuP);
        frame.add(petDisp);
        frame.add(feedP);
        frame.setVisible(true);
    }

    
}

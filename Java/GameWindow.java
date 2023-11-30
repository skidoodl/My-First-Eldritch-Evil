import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {
    public GameWindow(){
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

        
        JFrame frame = new JFrame("My First Eldritch Evil");
        frame.setSize(800,600); //set frame dimensions
        frame.setResizable(false); //may change this later but for now its false
        frame.setLayout(null); //might change later when I learn
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hitting the X ends program
        ImageIcon img = new ImageIcon("Resources/Icon.jpg"); //create image icon -- I could just stuff this into the next line, but i don't feel like it
        frame.setIconImage(img.getImage()); //change frame icon
        //Add Panels
        frame.add(menuP);
        frame.add(petDisp);
        frame.add(feedP);
        frame.setVisible(true); //Figured it out I have to place the setVisible *after* I add the panels

        return;
    }
}

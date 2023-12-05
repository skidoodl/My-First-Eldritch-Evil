package display;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class GameFrame extends JFrame{

    GameFrame(){
        this.setTitle("My First Eldritch Evil");
        this.setSize(800,600); //set frame dimensions
        this.setResizable(false); //may change this later but for now its false
        this.setLayout(null); //might change later when I learn
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hitting the X ends program
        ImageIcon img = new ImageIcon("Resources/Icon.jpg"); //create image icon -- I could just stuff this into the next line, but i don't feel like it
        this.setIconImage(img.getImage()); //change frame icon
    }

}

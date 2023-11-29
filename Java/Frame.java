import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Frame extends JFrame {
    
    Frame(){
        this.setTitle("My First Eldritch Evil"); //set frame title
        this.setSize(800,600); //set frame dimensions
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hitting the X ends this program
        ImageIcon img = new ImageIcon("Resources/Icon.jpg"); //create image icon
        this.setIconImage(img.getImage()); //change frame icon
    
    }

}

package Display;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{

    JButton button;

    MyFrame(){

        button = new JButton();
        button.setBounds(100, 100, 100, 20);
        button.addActionListener(this);
        this.setTitle("My First Eldritch Evil");
        this.setSize(800,600); //set frame dimensions
        this.setResizable(false); //may change this later but for now its false
        this.setLayout(null); //might change later when I learn
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hitting the X ends program
        ImageIcon img = new ImageIcon("Resources/Icon.jpg"); //create image icon -- I could just stuff this into the next line, but i don't feel like it
        this.setIconImage(img.getImage()); //change frame icon
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.out.println("test");
        }
    }
    
}

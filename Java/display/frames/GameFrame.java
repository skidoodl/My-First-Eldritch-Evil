package display.frames;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class GameFrame extends JFrame implements MouseListener{
    ImageIcon img = new ImageIcon("Resources/Icon.jpg"); //create image icon -- I could just stuff this into the next line, but i don't feel like it

    public GameFrame(){
        this.setTitle("My First Eldritch Evil");
        this.setSize(960,720);
        this.setResizable(false); //may change this later but for now its false
        this.setLayout(null); //might change later when I learn
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hitting the X ends program
        this.setIconImage(img.getImage()); //change frame icon
    }

    GameFrame(String windowName) {
        this.setTitle("MFEE - "+windowName);
        this.setSize(960,720);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setIconImage(img.getImage());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}

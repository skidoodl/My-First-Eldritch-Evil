package display.frames;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class InventoryFrame extends JFrame{
    ImageIcon img = new ImageIcon("Resources/Icon.jpg");

    public InventoryFrame(){
        this.setTitle("Inventory - My First Eldritch Evil");
        this.setSize(960,720);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setIconImage(img.getImage());
    }
}
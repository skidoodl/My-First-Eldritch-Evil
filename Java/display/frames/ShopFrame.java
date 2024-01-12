package display.frames;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import main.Main;

public class ShopFrame extends JFrame {
    ImageIcon icon = new ImageIcon("Resources/Icons/Shop_Icon.png");

    public ShopFrame() {
        setTitle("Shop - My First Eldritch Evil - Wallet: "+Main.pet.money+" mon");
        setSize(960, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // May change to hide on close? Idk yet
        setIconImage(icon.getImage());
    }
    
}

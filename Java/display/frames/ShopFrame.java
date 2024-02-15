package display.frames;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Inventory;

public class ShopFrame extends JFrame {
    ImageIcon icon = new ImageIcon("Resources/icons/Shop_Icon.png");
    private static boolean frameOpen;

    public ShopFrame() {
        setTitle("Shop - My First Eldritch Evil - Wallet: " + Inventory.getWallet() + " mon");
        setSize(960, 720);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // May change to hide on close? Idk yet
        setIconImage(icon.getImage()); // Icon features a Mi-go, a Lovecraftian creature
    }

    public void open() {
        if (frameOpen) {
            requestFocus();
            return;
        }
        setTitle("Shop - My First Eldritch Evil - Wallet: " + Inventory.getWallet() + " mon");
        setVisible(true);
    }

    
}

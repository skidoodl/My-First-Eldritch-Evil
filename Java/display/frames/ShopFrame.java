package display.frames;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Inventory;
import main.Shop;

public class ShopFrame extends JFrame implements WindowListener {
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
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        Shop.deselectItem();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }
    
}

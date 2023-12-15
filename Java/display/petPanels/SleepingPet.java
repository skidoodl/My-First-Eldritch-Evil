package display.petPanels;

import main.Pet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SleepingPet extends JPanel {
    
    public SleepingPet() {
        ImageIcon img = new ImageIcon("Resources/PetGraphics/SleepingPet.png");

        JLabel petPic = new JLabel(img);
        petPic.addMouseListener(new WakePetListener());
        add(petPic);
        setBounds(480, 0, 480, 360);
    }

    private class WakePetListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            //Action performed when mouse clicked
        }
    }

}

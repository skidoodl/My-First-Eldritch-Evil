package display.petPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import main.Pet;
import main.Main;

public class PetPanel extends JPanel {
    private Pet pet = Main.pet;
    ImageIcon petIcon;
    JLabel petLabel = new JLabel();
    private String trackedMood;

    public PetPanel(String[] options) {
        setBounds(480, 0, 480, 360);
        setLayout(null); // just for now
        setBackground(Color.white);

    }

    public void updatePetDisplay() {
        if ((!pet.mood.equalsIgnoreCase(trackedMood)) || trackedMood == null) {
            //if pet mood has changed - cuz otherwise just skip
            trackedMood = pet.mood;

            switch (pet.mood) {
                case "normal": moodNormal();
                case "hungry": moodHungry();
                default: JOptionPane.showMessageDialog(null, pet.name+" attempted to feel emotions not available to\nthis present plain of reality. (Mood "+pet.mood+" does not exist)","Internal Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void moodNormal() {
        petIcon = new ImageIcon("Resources/PetGraphics/PetNormal.png");
    }

    private void moodHungry() {
        // hungry mood
    }
}

package display.petPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import main.Pet;
import main.Main;

/* List of Potential Moods:
 * Afraid
 * Aggravated
 * Angry
 * Anxious
 * Dominant
 * Calm
 * Depressed
 * Guilty
 * Grumpy
 * Indifferent
 * Insecure
 * Irritated
 * Lonely
 * Sad
 * 
 * List of Potential Actions:
 * Crying
 * Stretching
 * Performing satanic rituals
 */

public class PetPanel extends JPanel {
    private final Pet pet = Main.pet;
    ImageIcon petIcon;
    JLabel petLabel = new JLabel();
    private String trackedMood;

    public PetPanel() {
        setBounds(480, 0, 480, 360);
        setLayout(null); // just for now
        setBackground(Color.white);

    }

    public void updatePetDisplay() {
        if ((!pet.mood.equalsIgnoreCase(trackedMood)) || trackedMood == null) {
            //if pet mood has changed - cuz otherwise just skip
            trackedMood = pet.mood; // Update mood tracker
            String moodFile = "Resources/PetGraphics/Moods/" + pet.mood + ".png"; // Get file path based on mood
            try {
                petLabel = new JLabel(new ImageIcon(moodFile)); // Create the label
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, pet.name+" attempted to experience an mood that does not yet exist on present plane of reality.", "Error", JOptionPane.ERROR_MESSAGE);
                pet.mood = "transcendental";
                // May occur if the file path does not represent a valid image file
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An unknown mood error occured", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

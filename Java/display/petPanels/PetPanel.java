package display.petPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import main.Pet;
import main.Main;

/* List of Moods Icons Needed:
 * Sleepy
 * Exhausted
 * Starving
 * Buff (Gym Bro)
 * Chubby (opt.)
 * Obese
 * Overstuffed (Not obese, just really full belly)
 * Sick
 * Extremely Sick
 * Dying
 * Energized
 * Calm
 */

/* List of Additional Possible Moods:
 * Afraid
 * Aggravated
 * Angry
 * Anxious
 * Dominant
 * Depressed
 * Guilty
 * Grumpy
 * Indifferent
 * Insecure
 * Irritated
 * Lonely
 * Sad
 */

public class PetPanel extends JPanel {
    protected final Pet pet = Main.pet;
    protected JLabel petLabel;

    private ImageIcon getPetImage() {
        String moodFile = "Resources/PetGraphics/Moods/" + pet.mood + ".png";
        System.out.println(moodFile);
        try {
            return new ImageIcon(moodFile);
        } catch (IllegalArgumentException e) {
            // May occur if the file path does not represent a valid image file
            JOptionPane.showMessageDialog(null, pet.name+" attempted to experience an mood that does not yet exist on present plane of reality.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("PetPanel Error: " + pet.name + " attempted to experience a mood beyond the scope of its present reality.");
            pet.mood = "transcendental";
            return new ImageIcon("Resources/PetGraphics/Moods/transcendental.png");
        }
    } 

    public PetPanel() {
        petLabel = new JLabel(getPetImage()); // Initialize pet label
        petLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBounds(480, 0, 480, 360);
        setLayout(new BorderLayout()); // just for now
        setBackground(Color.pink);
        petLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                petLabelClick();
            }
        });

        add(petLabel, BorderLayout.CENTER);
    }

    public void updateDisplay() {
        petLabel.setIcon(getPetImage());
    }

    protected void petLabelClick() {
        System.out.println ("Pet Clicked");
    }

}

package display.generalPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemDisplay extends JPanel {
    private JLabel itemLabel;

    private static ImageIcon getItemImage(String item) {
        String itemFile = "Resources/items/" + item + ".png";
        System.out.println(itemFile);
        try {
            ImageIcon image = new ImageIcon(itemFile);
            Image img = image.getImage();
            Image newImg = img.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (IllegalArgumentException e) {
            // May occur if the file path does not represent a valid image file
            System.out.println("Item image could not be found.");
            return new ImageIcon("Resources/icons/noImage.png");
        }
    }

    public ItemDisplay() {
        itemLabel = new JLabel();
        setBounds(480, 0, 240, 240);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        add(itemLabel, BorderLayout.CENTER);
    }
    
    public void showImage (String item) {
        itemLabel.setIcon(getItemImage(item));
    }

    public void removeImage () {
        itemLabel.setIcon(null);   
    }
}

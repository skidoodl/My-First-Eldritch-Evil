package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utils.Lazy;

import java.io.File;

public class Grave {
    public Grave() {
        // Default constructor
    }

    public void printGrave(String name) {

        String sourceFilePath = "Resources/Gravestone.txt"; //specify path to gravestone.txt
        String modifiedFilePath = "Resources/ModifiedGravestone.txt"; // specify modified copy path

        try {
            // yoink gravestone's contents into a string builder
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            StringBuilder fileContent = new StringBuilder();
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 12) {
                    //Calculate the lenght of name
                    int nameLength = name.length();

                    // Calculate starting column to center the name
                    int startColumn = 29 + ((38 - 29 - nameLength) / 2);

                    // Replace characters in columns 29-38 with name
                    if (line.length() >= 38) {
                        StringBuilder modifiedLine = new StringBuilder(line);
                        modifiedLine.replace(startColumn, startColumn + nameLength, name);
                        line = modifiedLine.toString();
                    }

                    // Add the "|" character at column 40
                    if (line.length() >= 40) {
                        line = line.substring(0, 40) + "|" + line.substring(41);
                    }
                }
                fileContent.append(line).append(System.lineSeparator()); // Append the modified or unmodified line
            }

            reader.close();

            // save the modified content to the new file
            BufferedWriter writer = new BufferedWriter(new FileWriter(modifiedFilePath));
            writer.write(fileContent.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader(modifiedFilePath);

            // Wrap the FileReader in BufferedReader (apparently it's more efficient? - look up why later)
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Print each line of the file until the end is reached
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the BufferedReader and Filereader when done so we don't keep getting those annoying yellow dots
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File modifiedGravestone = new File(modifiedFilePath);
        if (modifiedGravestone.exists()) {
            modifiedGravestone.delete();
        }
    }

    public void playerDeath(String cause, String name){
        long elapsed = System.nanoTime() - Main.startTime;
        System.out.println("\n \nYOU DIED.");
        System.out.println("Player died to "+cause+".    -<>-    Time spent with "+name+": "+Lazy.timeFormat(elapsed));
        Lazy.waitForEnter();
        System.out.println(name+" would go on to inhabit your skin and become your replacement in\nthe sorry world you used to inhabit.");
        Lazy.waitForEnter();
        System.exit(0);
    }
}

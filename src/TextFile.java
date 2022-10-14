/* Created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
 * Purpose: Demonstrate your ability to work in a team while creating an Object Oriented program in Java.
 */
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.IOException;

// Handles text file used to store/access game data
public class TextFile {
	
	private final String saveFile = "save.txt";
	// Get the directory the program is currently running in
	private String workingDirectory = System.getProperty("user.dir");
	// Write game data to a text file
	public void Save(Player player, Map map, Inventory inventory) {
		// Create or write to save.txt file
		File textFile = new File(workingDirectory + "/" + saveFile);
		try {
			if (!textFile.exists()) {
				System.out.println("\nSAVE: New game save file has been created!");
			} 
			else {
                System.out.println("\nSAVE: Game progress has been saved to file!");
			}
			// Write save data strings to specified text file
			PrintWriter outputToFile = new PrintWriter(textFile);
			outputToFile.println(player.getName());
			outputToFile.println(player.getXPosition());
			outputToFile.println(player.getYPosition());
			outputToFile.println(map.getRoomString());
			// Save each item symbol from the inventory list
			for (Item item: inventory.getInventory()) {
				outputToFile.println(item.getSymbol());
			}
			outputToFile.close();
		}
		catch (IOException e) {
			System.out.println("\nERROR: Saving game data has failed!\n\t" + e.getMessage());
		}
	}
	
	// Access game data from text file
	public void Load(Player player, Map map, Inventory inventory) {
		// Load save.txt file
		File textFile = new File(workingDirectory + "/" + saveFile);
		try {
			// Parse strings from text file
			Scanner inputFromFile = new Scanner(textFile, StandardCharsets.UTF_8.name());
			player.setName(inputFromFile.nextLine());
			player.setXPosition(Integer.parseInt(inputFromFile.nextLine()));
			player.setYPosition(Integer.parseInt(inputFromFile.nextLine()));
			FinalProject.setRoomDescription(inputFromFile.nextLine(), map);
			// Set inventory items while there is a next line
			while (inputFromFile.hasNext()) {
				Item item = new Item();
				item = inventory.getSpecificGameItem(inputFromFile.nextLine());
				inventory.addItemToInventory(item);
			}
			inputFromFile.close();
			System.out.println("\nSAVE: Game progress was successfully loaded from save file!");
		}
		catch (IOException e) {
			System.out.println("\nERROR: Loading save data has failed!\n\t" + e.getMessage());
		}
	}
}
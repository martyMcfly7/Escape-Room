// created by Victor Ortiz & Diana Nguyen  CS142  11/14/19
// purpose: demonstrate your ability to work in a team while creating an Object Oriented program in Java.

// required to create instances of File & PrintWriter
import java.io.File;
import java.io.PrintWriter;
// used to specify character set UTF-8 & to read text file from current directory
import java.nio.charset.StandardCharsets;
// required to read data from text file
import java.util.Scanner;
// used to show exception message if IOException occurs
import java.io.IOException;

// used to handle the file used to store game data
public class TextFile {
	
	// constant for filename to save player data
	private final String playerData = "save.txt";
	// used to get the directory the program is currently running in
	private String workDirectory = System.getProperty("user.dir");
		
	// method used to write game data to a text file
	public void Save(Player player, Inventory inventory) {
		// create instance of File class with save file in working directory
		File file = new File(workDirectory + "/" + playerData);
		
		try {
			// if text file doesn't exist
			if (!file.exists()) {
				System.out.println("\nSAVE: New game save file has been created!");
			} 
			else {
                System.out.println("\nSAVE: Game progress has been saved to file!");
			}
			// create instance of PrintWriter using filename constant
			PrintWriter outputFile = new PrintWriter(file);
			// save player data (name, x, y)
			outputFile.println(player.getName());
			outputFile.println(player.getXPosition());
			outputFile.println(player.getYPosition());
			// save each item symbol from the inventory list
			for (Item item: inventory.getInventory()) {
				outputFile.println(item.getSymbol());
			}
			outputFile.close();
		}
		catch (IOException e) {
			System.out.println("\nERROR: Saving game data has failed!\n\t" + e.getMessage());
		}
	}
	
	// reads game data from a file
	public void Load(Player player, Inventory inventory) {
		// load file named Save.txt in current directory
		File file = new File(workDirectory + "/" + playerData);
		
		try {
			// open file
			Scanner inputFile = new Scanner(file, StandardCharsets.UTF_8.name());
			// set each line to appropriate field (name, x, y)
			player.setName(inputFile.nextLine());
			player.setXPosition(Integer.parseInt(inputFile.nextLine()));
			player.setYPosition(Integer.parseInt(inputFile.nextLine()));
			// set inventory while there is a next line
			while (inputFile.hasNext()) {
				Item item = new Item();
				// send item symbol as parameter to getSpecificGameItem() & assign the Item to item variable
				item = inventory.getSpecificGameItem(inputFile.nextLine());
				inventory.addItemToInventory(item);
			}
			inputFile.close();
			System.out.println("\nSAVE: Game progress was successfully loaded from save file!");
		}
		catch (IOException e) {
			System.out.println("\nERROR: Loading save data has failed!\n\t" + e.getMessage());
		}
	}
	
}